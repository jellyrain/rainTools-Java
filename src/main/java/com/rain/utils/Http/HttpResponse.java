package com.rain.utils.Http;

import java.util.List;
import java.util.Map;

/**
 * HTTP 响应
 *
 * @author rain
 * @date 2024/08/18
 */
public class HttpResponse {
    /**
     * 响应代码
     */
    private int responseCode;
    /**
     * 响应头
     */
    private Map<String, List<String>> headers;
    /**
     * 响应体
     */
    private byte[] body;

    public HttpResponse() {

    }

    public HttpResponse(int responseCode, Map<String, List<String>> headers, byte[] body) {
        this.responseCode = responseCode;
        this.headers = headers;
        this.body = body;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public byte[] getBody() {
        return body;
    }
}
