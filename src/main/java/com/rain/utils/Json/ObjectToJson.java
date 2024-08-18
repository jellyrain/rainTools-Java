package com.rain.utils.Json;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 对象 转 JSON
 *
 * @author rain
 * @date 2024/08/18
 */
public final class ObjectToJson {

    private ObjectToJson() {

    }

    /**
     * 转为 json
     *
     * @param object 对象
     * @return {@link String }
     */
    public static String toJson(Object object) throws IllegalAccessException {
        // 如果对象为空，则返回 null
        if (object == null) {
            return null;
        }
        // 如果对象是数字或者布尔类型，则直接返回字符串
        if (object instanceof Number || object instanceof Boolean) {
            return object.toString();
        }
        // 如果对象是字符串，则直接返回字符串
        if (object instanceof String) {
            return "\"" + escapeString((String) object) + "\"";
        }
        // 如果对象是 Map 类型，则调用 mapToJson 方法
        if (object instanceof Map) {
            return mapToJson((Map<?, ?>) object);
        }
        // 如果对象是 List 类型，则调用 listToJson 方法
        if (object instanceof List) {
            return listToJson((List<?>) object);
        }
        // 如果对象是数组类型，则调用 arrayToJson 方法
        if (object.getClass().isArray()) {
            return arrayToJson(object);
        }
        // 如果对象是其他类型，则调用 objectToJson 方法
        return objectToJson(object);
    }

    /**
     * 转义
     *
     * @param s s
     * @return {@link String }
     */
    private static String escapeString(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    if (c < ' ') {
                        sb.append(String.format("\\u%04x", (int) c));
                    } else {
                        sb.append(c);
                    }
            }
        }
        return sb.toString();
    }

    /**
     * map 转 json
     *
     * @param map map
     * @return {@link String }
     */
    private static String mapToJson(Map<?, ?> map) throws IllegalAccessException {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        int mapSize = map.size();
        int currentIndex = 0;

        for (Map.Entry<?, ?> entry : map.entrySet()) {
            jsonBuilder.append(toJson(entry.getKey().toString()));
            jsonBuilder.append(":");
            jsonBuilder.append(toJson(entry.getValue()));

            if (currentIndex < mapSize - 1) {
                jsonBuilder.append(",");
            }
            currentIndex++;
        }

        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }

    /**
     * list 转 json
     *
     * @param list 列表
     * @return {@link String }
     */
    private static String listToJson(List<?> list) throws IllegalAccessException {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        int listSize = list.size();
        int currentIndex = 0;

        for (Object item : list) {
            jsonBuilder.append(toJson(item));

            if (currentIndex < listSize - 1) {
                jsonBuilder.append(",");
            }
            currentIndex++;
        }

        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }

    private static String arrayToJson(Object array) throws IllegalAccessException {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        int length = Array.getLength(array);

        for (int i = 0; i < length; i++) {
            jsonBuilder.append(toJson(Array.get(array, i)));

            if (i < length - 1) {
                jsonBuilder.append(",");
            }
        }

        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }

    private static String objectToJson(Object object) throws IllegalAccessException {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        Field[] fields = object.getClass().getDeclaredFields();
        int fieldCount = fields.length;
        int currentIndex = 0;

        for (Field field : fields) {
            field.setAccessible(true);

            jsonBuilder.append(toJson(field.getName()));
            jsonBuilder.append(":");
            jsonBuilder.append(toJson(field.get(object)));

            if (currentIndex < fieldCount - 1) {
                jsonBuilder.append(",");
            }
            currentIndex++;
        }

        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }
}
