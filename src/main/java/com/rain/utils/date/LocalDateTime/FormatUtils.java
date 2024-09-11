package com.rain.utils.date.LocalDateTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间格式化工具类
 *
 * @author rain
 * @date 2024/08/17
 */
public final class FormatUtils {

    private FormatUtils() {

    }

    /**
     * 格式化时间
     *
     * @param localDateTime {@link LocalDateTime}
     * @param pattern       格式
     * @return {@link String}
     */
    public static String format(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 格式化时间
     *
     * @param localDateTime {@link LocalDateTime}
     * @param formatter     {@link DateTimeFormatter}
     * @return {@link String}
     */
    public static String format(LocalDateTime localDateTime, DateTimeFormatter formatter) {
        return localDateTime.format(formatter);
    }

    /**
     * 格式化时间 yyyy-MM-dd HH:mm:ss
     *
     * @param localDateTime {@link LocalDateTime}
     * @return {@link String}
     */
    public static String formatDateTime(LocalDateTime localDateTime) {
        return format(localDateTime, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化时间 yyyy-MM-dd
     *
     * @param localDateTime {@link LocalDateTime}
     * @return {@link String}
     */
    public static String formatDate(LocalDateTime localDateTime) {
        return format(localDateTime, "yyyy-MM-dd");
    }

    /**
     * 格式化时间 HH:mm:ss
     *
     * @param localDateTime {@link LocalDateTime}
     * @return {@link String}
     */
    public static String formatTime(LocalDateTime localDateTime) {
        return format(localDateTime, "HH:mm:ss");
    }

    /**
     * 解析时间
     *
     * @param dateStr 日期 str
     * @param pattern 模式
     * @return {@link LocalDateTime }
     */
    public LocalDateTime parse(String dateStr, String pattern) {
        return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 解析时间
     *
     * @param dateStr   日期 str
     * @param formatter 格式化程序
     * @return {@link LocalDateTime }
     */
    public LocalDateTime parse(String dateStr, DateTimeFormatter formatter) {
        return LocalDateTime.parse(dateStr, formatter);
    }

    /**
     * 解析时间 yyyy-MM-dd HH:mm:ss
     *
     * @param dateStr 日期 str
     * @return {@link LocalDateTime }
     */
    public LocalDateTime parseDateTime(String dateStr) {
        return parse(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 解析时间 yyyy-MM-dd
     *
     * @param dateStr 日期 str
     * @return {@link LocalDateTime }
     */
    public LocalDateTime parseDate(String dateStr) {
        return parse(dateStr, "yyyy-MM-dd");
    }

    /**
     * 解析时间 HH:mm:ss
     *
     * @param dateStr 日期 str
     * @return {@link LocalDateTime }
     */
    public LocalDateTime parseTime(String dateStr) {
        return parse(dateStr, "HH:mm:ss");
    }
}
