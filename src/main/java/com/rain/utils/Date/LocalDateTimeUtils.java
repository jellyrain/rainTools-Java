package com.rain.utils.Date;

import com.rain.utils.Date.LocalDateTime.OffSetUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author rain
 * @date 2024/08/17
 */
public final class LocalDateTimeUtils {

    private LocalDateTimeUtils() {

    }

    /**
     * 获取当前时间
     *
     * @return {@link LocalDateTime }
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    /**
     * 获取当前时间戳
     *
     * @return {@link Long }
     */
    public static Long timestamp() {
        return System.currentTimeMillis();
    }

    /**
     * LocalDateTime 转时间戳
     *
     * @param localDateTime 本地日期时间
     * @return {@link Long }
     */
    public static Long timestamp(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * LocalDateTime 转 Date
     *
     * @param localDateTime 本地日期时间
     * @return {@link Date }
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date 转 LocalDateTime
     *
     * @param date 日期
     * @return {@link LocalDateTime }
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 获取昨天
     *
     * @return {@link LocalDateTime }
     */
    public static LocalDateTime yesterday() {
        return OffSetUtils.offSetDay(LocalDateTime.now(), -1);
    }

    /**
     * 获取明天
     *
     * @return {@link LocalDateTime }
     */
    public static LocalDateTime tomorrow() {
        return OffSetUtils.offSetDay(LocalDateTime.now(), 1);
    }

    /**
     * 获取上周
     *
     * @return {@link LocalDateTime }
     */
    public static LocalDateTime lastWeek() {
        return OffSetUtils.offSetDay(LocalDateTime.now(), -7);
    }

    /**
     * 获取下周
     *
     * @return {@link LocalDateTime }
     */
    public static LocalDateTime nextWeek() {
        return OffSetUtils.offSetDay(LocalDateTime.now(), 7);
    }

    /**
     * 获取上月
     *
     * @return {@link LocalDateTime }
     */
    public static LocalDateTime lastMonth() {
        return OffSetUtils.offSetMonth(LocalDateTime.now(), -1);
    }

    /**
     * 获取下月
     *
     * @return {@link LocalDateTime }
     */
    public static LocalDateTime nextMonth() {
        return OffSetUtils.offSetMonth(LocalDateTime.now(), 1);
    }

    /**
     * 获取上年
     *
     * @return {@link LocalDateTime }
     */
    public static LocalDateTime lastYear() {
        return OffSetUtils.offSetYear(LocalDateTime.now(), -1);
    }

    /**
     * 获取下年
     *
     * @return {@link LocalDateTime }
     */
    public static LocalDateTime nextYear() {
        return OffSetUtils.offSetYear(LocalDateTime.now(), 1);
    }

    /**
     * 获取当前日期开始时间
     * <p>
     * yyyy-MM-dd 00:00:00
     *
     * @param date 日期
     * @return {@link LocalDateTime }
     */
    public static LocalDateTime startOfDay(LocalDateTime date) {
        return date.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    /**
     * 获取当前日期结束时间
     * <p>
     * yyyy-MM-dd 23:59:59
     *
     * @param date 日期
     * @return {@link LocalDateTime }
     */
    public static LocalDateTime endOfDay(LocalDateTime date) {
        return date.withHour(23).withMinute(59).withSecond(59).withNano(999999999);
    }

    /**
     * 获取年份
     *
     * @param date 日期
     * @return {@link Long }
     */
    public static Long getYear(LocalDateTime date) {
        return (long) date.getYear();
    }

    /**
     * 获取季度
     *
     * @param date 日期
     * @return {@link Long }
     */
    public static Long getQuarter(LocalDateTime date) {
        return (long) ((date.getMonthValue() + 2) / 3);
    }

    /**
     * 获取月份
     *
     * @param date 日期
     * @return {@link Long }
     */
    public static Long getMonth(LocalDateTime date) {
        return (long) date.getMonthValue();
    }

    /**
     * 获取日
     *
     * @param date 日期
     * @return {@link Long }
     */
    public static Long getDay(LocalDateTime date) {
        return (long) date.getDayOfMonth();
    }

    /**
     * 获取小时
     *
     * @param date 日期
     * @return {@link Long }
     */
    public static Long getHour(LocalDateTime date) {
        return (long) date.getHour();
    }

    /**
     * 获取分钟
     *
     * @param date 日期
     * @return {@link Long }
     */
    public static Long getMinute(LocalDateTime date) {
        return (long) date.getMinute();
    }

    /**
     * 获取秒
     *
     * @param date 日期
     * @return {@link Long }
     */
    public static Long getSecond(LocalDateTime date) {
        return (long) date.getSecond();
    }
}
