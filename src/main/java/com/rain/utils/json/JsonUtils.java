package com.rain.utils.json;


import com.rain.utils.json.exception.JsonParseException;
import com.rain.utils.json.exception.TransformJsonException;

import java.util.List;
import java.util.Map;

/**
 * JSON工具类
 *
 * @author rain
 * @date 2024/08/18
 */
public final class JsonUtils {

    private JsonUtils() {

    }

    /**
     * 解析
     *
     * @param json  JSON
     * @param clazz 类
     * @return {@link T }
     */
    public static <T> T parse(String json, Class<T> clazz) {
        try {
            return JsonParseObject.parse(json, clazz);
        } catch (Exception e) {
            throw new JsonParseException(e.getMessage());
        }
    }

    /**
     * 解析为 Map
     *
     * @param json JSON格式
     * @return {@link Map }<{@link String }, {@link Object }>
     */
    public static Map<String, Object> parseMap(String json) {
        try {
            return JsonParseObject.parseMap(json);
        } catch (Exception e) {
            throw new JsonParseException(e.getMessage());
        }
    }

    /**
     * 解析为 List
     *
     * @param json JSON格式
     * @return {@link List }<{@link Object }>
     */
    public static List<Object> parseList(String json) {
        try {
            return JsonParseObject.parseList(json);
        } catch (Exception e) {
            throw new JsonParseException(e.getMessage());
        }
    }

    /**
     * 转为 json
     *
     * @param object 对象
     * @return {@link String }
     */
    public static String toJson(Object object) {
        try {
            return ObjectToJson.toJson(object);
        } catch (IllegalAccessException e) {
            throw new TransformJsonException(e.getMessage());
        }
    }

}
