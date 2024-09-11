package com.rain.utils.http.response;

import java.util.List;
import java.util.Map;


/**
 * HTTP 文件响应
 *
 * @author rain
 * @date 2024/08/18
 */
public class HttpFileResponse {
    /**
     * 响应代码
     */
    private int responseCode;
    /**
     * 响应头
     */
    private Map<String, List<String>> headers;
    /**
     * 文件大小
     */
    private long fileSize;
    /**
     * 文件路径
     */
    private String filePath;

    public HttpFileResponse() {

    }

    public HttpFileResponse(int responseCode, Map<String, List<String>> headers, long fileSize, String filePath) {
        this.responseCode = responseCode;
        this.headers = headers;
        this.fileSize = fileSize;
        this.filePath = filePath;
    }

    /**
     * 获取响应 http code
     *
     * @return int
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * 获取响应头
     *
     * @return {@link Map }<{@link String }, {@link List }<{@link String }>>
     */
    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    /**
     * 获取文件大小
     *
     * @return long
     */
    public long getFileSize() {
        return fileSize;
    }

    /**
     * 获取文件路径
     *
     * @return {@link String }
     */
    public String getFilePath() {
        return filePath;
    }
}
