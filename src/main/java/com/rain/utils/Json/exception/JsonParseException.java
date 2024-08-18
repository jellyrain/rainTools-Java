package com.rain.utils.Json.exception;

/**
 * JSON 解析异常
 *
 * @author rain
 * @date 2024/08/18
 */
public class JsonParseException extends RuntimeException {
    public JsonParseException(String message) {
        super(message);
    }
}
