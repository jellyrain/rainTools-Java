package com.rain.utils.http.response;

import com.rain.utils.json.JsonUtils;

import java.io.UnsupportedEncodingException;
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
     * 获取响应体（字节）
     *
     * @return {@link byte[] }
     */
    public byte[] getBody() {
        return body;
    }

    /**
     * 获取响应体（字符串）
     *
     * @return {@link String }
     */
    public String toString() {
        return new String(this.body);
    }

    /**
     * 获取响应体（字符串）
     *
     * @param charset 字符集
     * @return {@link String }
     */
    public String toString(String charset) throws UnsupportedEncodingException {
        return new String(this.body, charset);
    }

    /**
     * 获取响应体（JSON）
     *
     * @param clazz 类
     * @return {@link T }
     */
    public <T> T toJson(Class<T> clazz) {
        return JsonUtils.parse(this.toString(), clazz);
    }
}
