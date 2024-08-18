package com.rain.utils.Date.LocalDateTime;

import java.time.LocalDateTime;

/**
 * 时间偏移设置工具类
 *
 * @author rain
 * @date 2024/08/17
 */
public final class OffSetUtils {

    private OffSetUtils() {

    }

    /**
     * 设置偏移年
     * <p>
     * 正数为向后偏移，负数为向前偏移
     *
     * @param date 日期
     * @param year 年
     * @return {@link LocalDateTime }
     */
    public static LocalDateTime offSetYear(LocalDateTime date, int year) {
        return date.plusYears(year);
    }

    /**
     * 设置偏移月
     * <p>
     * 正数为向后偏移，负数为向前偏移
     *
     * @param date  日期
     * @param month 月
     * @return {@link LocalDateTime }
     */
    public static LocalDateTime offSetMonth(LocalDateTime date, int month) {
        return date.plusMonths(month);
    }

    /**
     * 设置偏移日
     *
     * @param date 日期
     * @param day  日
     * @return {@link LocalDateTime }
     */
    public static LocalDateTime offSetDay(LocalDateTime date, int day) {
        return date.plusDays(day);
    }

    /**
     * 设置偏移小时
     *
     * @param date 日期
     * @param hour 小时
     * @return {@link LocalDateTime }
     */
    public static LocalDateTime offSetHour(LocalDateTime date, int hour) {
        return date.plusHours(hour);
    }

    /**
     * 设置偏移分钟
     *
     * @param date   日期
     * @param minute 分钟
     * @return {@link LocalDateTime }
     */
    public static LocalDateTime offSetMinute(LocalDateTime date, int minute) {
        return date.plusMinutes(minute);
    }

    /**
     * 设置偏移秒
     *
     * @param date   日期
     * @param second 秒
     * @return {@link LocalDateTime }
     */
    public static LocalDateTime offSetSecond(LocalDateTime date, int second) {
        return date.plusSeconds(second);
    }
}
