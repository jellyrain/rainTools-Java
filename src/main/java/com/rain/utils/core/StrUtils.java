package com.rain.utils.core;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StrUtils {

    private static final char C_BACKSLASH = '\\';

    private StrUtils() {

    }

    /**
     * 是否是空字符串
     *
     * @param str str
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 是否不是空字符串
     *
     * @param str str
     * @return boolean
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 是否空白符
     * <p>
     * 空白符包括空格、制表符、全角空格和不间断空格
     *
     * @param c c
     * @return boolean
     */
    public static boolean isBlankChar(int c) {
        return Character.isWhitespace(c)
                || Character.isSpaceChar(c)
                || c == '\ufeff'
                || c == '\u202a'
                || c == '\u0000'
                || c == '\u3164'
                || c == '\u2800'
                || c == '\u180e';
    }

    /**
     * 是否空白符
     * <p>
     * 空白符包括空格、制表符、全角空格和不间断空格
     *
     * @param c c
     * @return boolean
     */
    public static boolean isBlankChar(char c) {
        return isBlankChar((int) c);
    }

    /**
     * 是否是空字符串
     * <p>
     * 空白符包括空格、制表符、全角空格和不间断空格
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isBlank(String str) {
        final int length;
        if (str == null || (length = str.length()) == 0) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!isBlankChar(str.charAt(i))) {
                return false;
            }
        }


        return true;
    }

    /**
     * 是否不是空字符串
     * <p>
     * 空白符包括空格、制表符、全角空格和不间断空格
     *
     * @param str str
     * @return boolean
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 是否是空数组
     *
     * @param array 数组
     * @return boolean
     */
    private static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 将对象转为字符串
     *
     * @param obj     对象
     * @param charset 字符集
     * @return {@link String }
     */
    public static String str(Object obj, Charset charset) {
        if (null == obj) {
            return null;
        }

        if (obj instanceof String) {
            return (String) obj;
        } else if (obj instanceof byte[]) {
            return str((byte[]) obj, charset);
        } else if (obj instanceof Byte[]) {
            return str((Byte[]) obj, charset);
        } else if (obj instanceof ByteBuffer) {
            return str((ByteBuffer) obj, charset);
        }

        return obj.toString();
    }

    /**
     * 将对象转为字符串
     *
     * @param obj 对象
     * @return 字符串
     */
    public static String utf8Str(Object obj) {
        return str(obj, StandardCharsets.UTF_8);
    }

    /**
     * 格式化字符串
     * <p>
     * 默认占位符 ${}，如果不想用这个占位符，可以使用 {@link #formatWith(String, String, Object...)}
     * <p>
     * 转义占位符 在占位符前加上转义符（\\）即可，如：\\${}
     * <p>
     * 转义转义符 在转义符前加上转义符（\\）即可，如：\\\\
     *
     * @param template 字符串模板
     * @param params   参数列表
     * @return {@link String }
     */
    public static String format(String template, Object... params) {
        if (isEmpty(template) || isEmpty(params)) {
            return template;
        }
        return formatWith(template, "${}", params);
    }

    /**
     * 格式化字符串,根据模版里面的占位符替换成对应的值，对应的key是占位符的值
     * <p>
     * 默认占位符 ${} : ${key} {key: xxx} 替换后就是 xxx
     * <p>
     * 如果不想用这个占位符，可以使用 {@link #formatKV(String, String, Map)}
     *
     * @param template 模板
     * @param values   值
     * @return {@link String }
     */
    public static String formatKV(String template, Map<String, Object> values) {
        return formatKV(template, "\\$\\{([^\\}]+)\\}", values);
    }


    /**
     * 格式化字符串,根据模版里面的占位符替换成对应的值，对应的key是占位符的值
     *
     * @param template           模板
     * @param placeholderPattern 占位符读取正则表达式
     * @param values             值
     * @return {@link String }
     */
    public static String formatKV(String template, String placeholderPattern, Map<String, Object> values) {
        // 使用传入的占位符模式构建正则表达式
        Pattern pattern = Pattern.compile(placeholderPattern);
        Matcher matcher = pattern.matcher(template);
        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            // 获取占位符中的键
            String key = matcher.group(1);
            // 获取替换值，如果没有则使用原占位符
            String replacement = values.getOrDefault(key, matcher.group(0)).toString();
            matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    /**
     * 格式化字符串
     * <p>
     * 转义占位符 在占位符前加上转义符（\\）即可，如：\\<占位符>
     * <p>
     * 转义转义符 在转义符前加上转义符（\\）即可，如：\\\\
     *
     * @param strPattern  字符串模板
     * @param placeHolder 占位符
     * @param argArray    参数列表
     * @return {@link String }
     */
    public static String formatWith(String strPattern, String placeHolder, Object... argArray) {
        if (isBlank(strPattern) || isBlank(placeHolder) || isEmpty(argArray)) {
            return strPattern;
        }
        final int strPatternLength = strPattern.length();
        final int placeHolderLength = placeHolder.length();

        // 初始化定义好的长度以获得更好的性能
        final StringBuilder sbuf = new StringBuilder(strPatternLength + 50);

        int handledPosition = 0;// 记录已经处理到的位置
        int delimIndex;// 占位符所在位置
        for (int argIndex = 0; argIndex < argArray.length; argIndex++) {
            delimIndex = strPattern.indexOf(placeHolder, handledPosition);
            if (delimIndex == -1) {// 剩余部分无占位符
                if (handledPosition == 0) { // 不带占位符的模板直接返回
                    return strPattern;
                }
                // 字符串模板剩余部分不再包含占位符，加入剩余部分后返回结果
                sbuf.append(strPattern, handledPosition, strPatternLength);
                return sbuf.toString();
            }

            // 转义符
            if (delimIndex > 0 && strPattern.charAt(delimIndex - 1) == C_BACKSLASH) {// 转义符
                if (delimIndex > 1 && strPattern.charAt(delimIndex - 2) == C_BACKSLASH) {// 双转义符
                    // 转义符之前还有一个转义符，占位符依旧有效
                    sbuf.append(strPattern, handledPosition, delimIndex - 1);
                    sbuf.append(utf8Str(argArray[argIndex]));
                    handledPosition = delimIndex + placeHolderLength;
                } else {
                    // 占位符被转义
                    argIndex--;
                    sbuf.append(strPattern, handledPosition, delimIndex - 1);
                    sbuf.append(placeHolder.charAt(0));
                    handledPosition = delimIndex + 1;
                }
            } else {// 正常占位符
                sbuf.append(strPattern, handledPosition, delimIndex);
                sbuf.append(utf8Str(argArray[argIndex]));
                handledPosition = delimIndex + placeHolderLength;
            }
        }

        // 加入最后一个占位符后所有的字符
        sbuf.append(strPattern, handledPosition, strPatternLength);

        return sbuf.toString();
    }
}
