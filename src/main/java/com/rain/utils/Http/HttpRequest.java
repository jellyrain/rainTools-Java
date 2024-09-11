package com.rain.utils.Http;

import com.rain.utils.Http.enums.MethodEnum;
import com.rain.utils.Http.response.HttpFileResponse;
import com.rain.utils.Http.response.HttpResponse;
import com.rain.utils.Json.JsonUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.List;
import java.util.Map;

public final class HttpRequest {

    private HttpRequest() {

    }

    /**
     * 发送请求
     *
     * @param urlString 请求地址
     * @param method    请求方法
     * @param params    请求参数
     * @param data      请求体
     * @param headers   请求头
     * @param timeout   超时时间（毫秒）
     * @param proxy     代理
     * @return {@link HttpURLConnection } 连接
     * @throws IOException IO异常
     */
    public static HttpURLConnection request(String urlString, MethodEnum method, Map<String, String> params, Map<String, Object> data, Map<String, String> headers, int timeout, Proxy proxy) throws IOException {
        URL url = URLUtils.toURL(urlString, params);
        HttpURLConnection connection;

        // 如果代理为空，则直接打开连接
        if (proxy == null) {
            connection = (HttpURLConnection) url.openConnection();
        } else {
            connection = (HttpURLConnection) url.openConnection(proxy);
        }

        // 设置请求方法
        connection.setRequestMethod(method.name());

        // 设置请求头
        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                connection.setRequestProperty(header.getKey(), header.getValue());
            }
        }

        // 设置超时时间
        if (timeout > 0) {
            connection.setConnectTimeout(timeout);
        }

        // POST 和 PUT 和 PATCH 请求需要设置数据
        if (method.name().equals("POST") || method.name().equals("PUT") || method.name().equals("PATCH")) {
            // 启用向服务器发送数据的功能
            connection.setDoOutput(true);

            if (data != null) {

                // 判断是否是文件上传
                if (isFileUpload(data)) {
                    // 设置 Content-Type 为 multipart/form-data
                    String boundary = "----WebKitFormBoundary" + System.currentTimeMillis();
                    connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

                    try (DataOutputStream out = new DataOutputStream(connection.getOutputStream())) {
                        for (Map.Entry<String, Object> entry : data.entrySet()) {
                            if (entry.getValue() instanceof File file) {
                                addFilePart(out, boundary, entry.getKey(), file);
                            } else if (entry.getValue() instanceof List<?> list) {
                                for (Object item : list) {
                                    if (item instanceof File) {
                                        addFilePart(out, boundary, entry.getKey(), (File) item);
                                    } else {
                                        addFormField(out, boundary, entry.getKey(), item.toString());
                                    }
                                }
                            } else {
                                addFormField(out, boundary, entry.getKey(), entry.getValue().toString());
                            }
                        }
                        out.writeBytes("--" + boundary + "--\r\n");
                    }
                } else {
                    // 如果数据不为空，则写入数据，并且设置 Content-Type 为 application/json
                    connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                    connection.getOutputStream().write(JsonUtils.toJson(data).getBytes());
                }
            }
        }

        return connection;
    }

    /**
     * 发送
     *
     * @param connection 连接
     * @return {@link HttpResponse }
     * @throws IOException io异常
     */
    public static HttpResponse send(HttpURLConnection connection) throws IOException {
        // 获取响应代码
        int responseCode = connection.getResponseCode();

        // 获取响应头
        Map<String, List<String>> headers = connection.getHeaderFields();

        // 获取响应体
        byte[] body = connection.getInputStream().readAllBytes();

        return new HttpResponse(responseCode, headers, body);
    }

    /**
     * 下载文件
     *
     * @param urlString      请求地址
     * @param method         请求方法
     * @param params         请求参数
     * @param data           请求体
     * @param headers        请求头
     * @param streamProgress 流进度
     * @return {@link HttpResponse }
     * @throws IOException io异常
     */
    public static HttpFileResponse downloadFile(String urlString, String filePath, MethodEnum method, Map<String, String> params, Map<String, Object> data, Map<String, String> headers, Proxy proxy, StreamProgress streamProgress) throws IOException {
        HttpURLConnection connection = request(urlString, method, params, data, headers, 0, proxy);

        // 获取响应代码
        int responseCode = connection.getResponseCode();

        // 获取响应头
        Map<String, List<String>> responseHeaders = connection.getHeaderFields();

        long totalBytesRead = 0;
        try (InputStream inputStream = connection.getInputStream(); FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            long totalSize = connection.getContentLengthLong();

            if (streamProgress != null) {
                streamProgress.start();
            }

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;

                if (streamProgress != null) {
                    streamProgress.progress(totalSize, totalBytesRead);
                }
            }

            if (streamProgress != null) {
                streamProgress.finish();
            }
        }

        return new HttpFileResponse(responseCode, responseHeaders, totalBytesRead, filePath);
    }


    /**
     * 判断是否是文件上传
     *
     * @param data 数据
     * @return boolean
     */
    private static boolean isFileUpload(Map<String, Object> data) {
        for (Object value : data.values()) {
            if (value instanceof File || (value instanceof List && ((List<?>) value).stream().anyMatch(item -> item instanceof File))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 添加表单字段
     *
     * @param out      输出流
     * @param boundary 分隔符
     * @param name     名字
     * @param value    值
     * @throws IOException ioException
     */
    private static void addFormField(DataOutputStream out, String boundary, String name, String value) throws IOException {
        out.writeBytes("--" + boundary + "\r\n");
        out.writeBytes("Content-Disposition: form-data; name=\"" + name + "\"\r\n");
        out.writeBytes("\r\n");
        out.writeBytes(value + "\r\n");
    }


    /**
     * 添加文件
     *
     * @param out        输出流
     * @param boundary   分隔符
     * @param fieldName  文件名字
     * @param uploadFile 文件
     * @throws IOException ioException
     */
    private static void addFilePart(DataOutputStream out, String boundary, String fieldName, File uploadFile) throws IOException {
        String fileName = uploadFile.getName();
        out.writeBytes("--" + boundary + "\r\n");
        out.writeBytes("Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"" + fileName + "\"\r\n");
        out.writeBytes("Content-Type: " + HttpURLConnection.guessContentTypeFromName(fileName) + "\r\n");
        out.writeBytes("\r\n");

        try (FileInputStream inputStream = new FileInputStream(uploadFile)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
        out.writeBytes("\r\n");
    }
}