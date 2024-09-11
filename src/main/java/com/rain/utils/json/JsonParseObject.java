package com.rain.utils.json;

import java.lang.reflect.*;
import java.text.ParseException;
import java.util.*;

/**
 * JSON 转 对象
 *
 * @author rain
 * @date 2024/08/18
 */
public final class JsonParseObject {

    private JsonParseObject() {

    }

    /**
     * 解析
     *
     * @param json  JSON
     * @param clazz 类
     * @return {@link T }
     * @throws InvocationTargetException 调用目标异常
     * @throws NoSuchMethodException     没有这样方法例外
     * @throws InstantiationException    实例化异常
     * @throws IllegalAccessException    非法访问异常
     * @throws ParseException            解析异常
     */
    public static <T> T parse(String json, Class<T> clazz) throws ParseException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (List.class.isAssignableFrom(clazz)) {
            return clazz.cast(parseList(json));
        } else if (Map.class.isAssignableFrom(clazz)) {
            return clazz.cast(parseMap(json));
        } else if (clazz.isArray()) {
            return parseArray(json, clazz);
        } else if (clazz.isPrimitive() || clazz == String.class || Number.class.isAssignableFrom(clazz) || clazz == Boolean.class || clazz == Date.class) {
            return parsePrimitive(json, clazz);
        } else {
            return parseObject(json, clazz);
        }
    }

    public static List<Object> parseList(String json) {
        json = json.trim();
        if (json.startsWith("[") && json.endsWith("]")) {
            json = json.substring(1, json.length() - 1).trim();
            String[] values = splitJson(json, ',');
            List<Object> list = new ArrayList<>();

            for (String value : values) {
                list.add(parseValue(value.trim()));
            }

            return list;
        } else {
            throw new IllegalArgumentException("Invalid JSON array: " + json);
        }
    }

    public static Map<String, Object> parseMap(String json) {
        json = json.trim();
        if (json.startsWith("{") && json.endsWith("}")) {
            json = json.substring(1, json.length() - 1).trim(); // Remove curly braces
            String[] fields = splitJson(json, ',');
            Map<String, Object> map = new HashMap<>();

            for (String field : fields) {
                String[] keyValue = splitJson(field, ':');
                String key = keyValue[0].trim().substring(1, keyValue[0].length() - 1); // Remove quotes
                String value = keyValue[1].trim();

                map.put(key, parseValue(value));
            }

            return map;
        } else {
            throw new IllegalArgumentException("Invalid JSON object: " + json);
        }
    }

    /**
     * 解析值
     *
     * @param json JSON
     * @return {@link Object }
     */
    private static Object parseValue(String json) {
        json = json.trim();
        if (json.startsWith("{")) {
            // 如果是对象，递归解析
            return parseMap(json);
        } else if (json.startsWith("[")) {
            // 如果是数组，递归解析
            return parseList(json);
        } else if (json.startsWith("\"") && json.endsWith("\"")) {
            // 如果是字符串，去掉引号
            return json.substring(1, json.length() - 1);
        } else if (json.equals("true") || json.equals("false")) {
            // 如果是布尔值，转换为布尔类型
            return Boolean.parseBoolean(json);
        } else {
            if (json.contains(".")) {
                // 如果是小数，转换为 double 类型
                return Double.parseDouble(json);
            } else {
                // 如果是整数，转换为 int 类型
                return Integer.parseInt(json);
            }
        }
    }

    /**
     * 解析数组
     *
     * @param json  JSON
     * @param clazz 类
     * @return {@link T }
     * @throws InvocationTargetException 调用目标异常
     * @throws NoSuchMethodException     没有这样方法例外
     * @throws InstantiationException    实例化异常
     * @throws IllegalAccessException    非法访问异常
     * @throws ParseException            解析异常
     */
    private static <T> T parseArray(String json, Class<T> clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ParseException {
        Class<?> componentType = clazz.getComponentType();
        json = json.trim();
        if (json.startsWith("[") && json.endsWith("]")) {
            json = json.substring(1, json.length() - 1).trim(); // Remove square brackets
            String[] values = splitJson(json, ',');
            Object array = Array.newInstance(componentType, values.length);

            for (int i = 0; i < values.length; i++) {
                Array.set(array, i, parse(values[i].trim(), componentType));
            }

            return clazz.cast(array);
        } else {
            throw new IllegalArgumentException("Invalid JSON array: " + json);
        }
    }

    /**
     * 解析原始类型
     *
     * @param json  JSON
     * @param clazz 类
     * @return {@link T }
     */
    private static <T> T parsePrimitive(String json, Class<T> clazz) {
        if (clazz == String.class) {
            return clazz.cast(json.substring(1, json.length() - 1)); // Remove quotes
        } else if (clazz == int.class) {
            return (T) Integer.valueOf(json);
        } else if (clazz == Integer.class) {
            return clazz.cast(Integer.valueOf(json));
        } else if (clazz == long.class) {
            return (T) Long.valueOf(json);
        } else if (clazz == Long.class) {
            return clazz.cast(Long.valueOf(json));
        } else if (clazz == double.class) {
            return (T) Double.valueOf(json);
        } else if (clazz == Double.class) {
            return clazz.cast(Double.valueOf(json));
        } else if (clazz == boolean.class) {
            return (T) Boolean.valueOf(json);
        } else if (clazz == Boolean.class) {
            return clazz.cast(Boolean.valueOf(json));
        } else {
            throw new IllegalArgumentException("Unsupported primitive type: " + clazz);
        }
    }


    /**
     * 解析对象
     *
     * @param json  JSON
     * @param clazz 类
     * @return {@link T }
     * @throws NoSuchMethodException     没有这样方法例外
     * @throws InvocationTargetException 调用目标异常
     * @throws InstantiationException    实例化异常
     * @throws IllegalAccessException    非法访问异常
     * @throws ParseException            解析异常
     */
    private static <T> T parseObject(String json, Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ParseException {
        T instance = clazz.getDeclaredConstructor().newInstance();
        json = json.trim();
        if (json.startsWith("{") && json.endsWith("}")) {
            json = json.substring(1, json.length() - 1).trim();
            String[] fields = splitJson(json, ',');

            for (String field : fields) {
                String[] keyValue = splitJson(field, ':');
                String key = keyValue[0].trim().substring(1, keyValue[0].length() - 1);
                String value = keyValue[1].trim();

                Field classField = getField(clazz, key);
                if (classField != null) {
                    classField.setAccessible(true);
                    classField.set(instance, parse(value, classField.getType()));
                }
            }

            return instance;
        } else {
            throw new IllegalArgumentException("Invalid JSON object: " + json);
        }
    }

    /**
     * 获取字段
     *
     * @param clazz     类
     * @param fieldName 字段名称
     * @return {@link Field }
     */
    private static Field getField(Class<?> clazz, String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            // 如果当前类没有该字段，则递归查找父类
            if (clazz.getSuperclass() != null) {
                return getField(clazz.getSuperclass(), fieldName);
            }
            return null;
        }
    }

    /**
     * 拆分 JSON
     *
     * @param json      JSON
     * @param delimiter 分隔符
     * @return {@link String[] }
     */
    private static String[] splitJson(String json, char delimiter) {
        List<String> tokens = new ArrayList<>();
        int start = 0;
        int braceCount = 0;
        int bracketCount = 0;
        boolean inQuotes = false;

        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            switch (c) {
                case '{':
                    if (!inQuotes) braceCount++;
                    break;
                case '}':
                    if (!inQuotes) braceCount--;
                    break;
                case '[':
                    if (!inQuotes) bracketCount++;
                    break;
                case ']':
                    if (!inQuotes) bracketCount--;
                    break;
                case '"':
                    if (i == 0 || json.charAt(i - 1) != '\\') {
                        inQuotes = !inQuotes;
                    }
                    break;
                default:
                    if (c == delimiter && braceCount == 0 && bracketCount == 0 && !inQuotes) {
                        tokens.add(json.substring(start, i).trim());
                        start = i + 1;
                    }
                    break;
            }
        }
        tokens.add(json.substring(start).trim());
        return tokens.toArray(new String[0]);
    }
}
