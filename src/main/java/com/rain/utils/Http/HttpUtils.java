package com.rain.utils.Http;

import com.rain.utils.Http.enums.MethodEnum;
import com.rain.utils.Http.response.HttpFileResponse;
import com.rain.utils.Http.response.HttpResponse;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * http 工具类
 *
 * @author rain
 * @date 2024/09/10
 */
public final class HttpUtils {

    private HttpUtils() {

    }

    /**
     * get 请求
     *
     * @param url 请求地址
     * @return {@link HttpResponse } HTTP 响应
     * @throws IOException IO异常
     */
    public static HttpResponse get(String url) throws IOException {
        return get(url, null, null, 0);
    }

    /**
     * get 请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return {@link HttpResponse } HTTP 响应
     * @throws IOException IO异常
     */
    public static HttpResponse get(String url, Map<String, String> params) throws IOException {
        return get(url, params, null, 0);
    }

    /**
     * get 请求
     *
     * @param url     请求地址
     * @param params  请求参数
     * @param headers 请求头
     * @return {@link HttpResponse } HTTP 响应
     * @throws IOException IO异常
     */
    public static HttpResponse get(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
        return get(url, params, headers, 0);
    }


    /**
     * get 请求
     *
     * @param url     请求地址
     * @param params  请求参数
     * @param headers 请求头
     * @param timeout 超时时间（毫秒）
     * @return {@link HttpResponse } HTTP 响应
     * @throws IOException IO异常
     */
    public static HttpResponse get(String url, Map<String, String> params, Map<String, String> headers, int timeout) throws IOException {
        HttpURLConnection connection = HttpRequest.request(url, MethodEnum.GET, params, null, headers, timeout, null);
        return HttpRequest.send(connection);
    }

    /**
     * post 请求
     *
     * @param url 请求地址
     * @return {@link HttpResponse } HTTP 响应
     * @throws IOException IO异常
     */
    public static HttpResponse post(String url) throws IOException {
        return post(url, null, null, null, 0);
    }

    /**
     * post 请求
     *
     * @param url  请求地址
     * @param data 请求参数
     * @return {@link HttpResponse } HTTP 响应
     * @throws IOException IO异常
     */
    public static HttpResponse post(String url, Map<String, Object> data) throws IOException {
        return post(url, null, data, null, 0);
    }

    /**
     * post 请求
     *
     * @param url     请求地址
     * @param data    请求体
     * @param headers 请求头
     * @return {@link HttpResponse } HTTP 响应
     * @throws IOException IO异常
     */
    public static HttpResponse post(String url, Map<String, Object> data, Map<String, String> headers) throws IOException {
        return post(url, null, data, headers, 0);
    }

    /**
     * post 请求
     *
     * @param url     请求地址
     * @param params  请求参数
     * @param data    请求体
     * @param headers 请求头
     * @return {@link HttpResponse } HTTP 响应
     * @throws IOException IO异常
     */
    public static HttpResponse post(String url, Map<String, String> params, Map<String, Object> data, Map<String, String> headers) throws IOException {
        return post(url, params, data, headers, 0);
    }

    /**
     * post 请求
     *
     * @param url     请求地址
     * @param params  请求参数
     * @param data    请求体
     * @param headers 请求头
     * @param timeout 超时时间（毫秒）
     * @return {@link HttpResponse } HTTP 响应
     * @throws IOException IO异常
     */
    public static HttpResponse post(String url, Map<String, String> params, Map<String, Object> data, Map<String, String> headers, int timeout) throws IOException {
        HttpURLConnection connection = HttpRequest.request(url, MethodEnum.POST, params, data, headers, timeout, null);
        return HttpRequest.send(connection);
    }

    /**
     * 上传文件
     *
     * @param url      请求地址
     * @param filePath 文件路径
     * @return {@link HttpResponse } HTTP 响应
     * @throws IOException IO异常
     */
    public static HttpResponse uploadFile(String url, String filePath) throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("file", new File(filePath));
        return post(url, data);
    }

    /**
     * 上传文件
     *
     * @param url      请求地址
     * @param filePath 文件路径
     * @param headers  请求头
     * @return {@link HttpResponse } HTTP 响应
     * @throws IOException IO异常
     */
    public static HttpResponse uploadFile(String url, String filePath, Map<String, String> headers) throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("file", new File(filePath));
        return post(url, data, headers);
    }

    /**
     * 下载文件
     *
     * @param url      网址
     * @param filePath 文件路径
     * @return {@link HttpFileResponse } HTTP 文件响应
     * @throws IOException io异常
     */
    public static HttpFileResponse downloadFile(String url, String filePath) throws IOException {
        return downloadFile(url, filePath, null, null, null);
    }


    /**
     * 下载文件
     *
     * @param url            网址
     * @param filePath       文件路径
     * @param streamProgress 流进度
     * @return {@link HttpFileResponse } HTTP 文件响应
     * @throws IOException io异常
     */
    public static HttpFileResponse downloadFile(String url, String filePath, StreamProgress streamProgress) throws IOException {
        return downloadFile(url, filePath, null, null, streamProgress);
    }


    /**
     * 下载文件
     *
     * @param url            网址
     * @param filePath       文件路径
     * @param params         参数
     * @param headers        头
     * @param streamProgress 流进度
     * @return {@link HttpFileResponse } HTTP 文件响应
     * @throws IOException io异常
     */
    public static HttpFileResponse downloadFile(String url, String filePath, Map<String, String> params, Map<String, String> headers, StreamProgress streamProgress) throws IOException {
        return HttpRequest.downloadFile(url, filePath, MethodEnum.GET, params, null, headers, null, streamProgress);
    }
}
