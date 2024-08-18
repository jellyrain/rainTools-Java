package com.rain.utils.Date.LocalDateTime;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 时间差值工具类
 *
 * @author rain
 * @date 2024/08/17
 */
public final class BetweenUtils {

    private BetweenUtils() {

    }


    /**
     * 获取两个时间的毫秒差值
     *
     * @param start 开始
     * @param end   结束
     * @return {@link Long }
     */
    public static Long betweenMillis(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.MILLIS.between(start, end);
    }

    /**
     * 获取两个时间的秒差值
     *
     * @param start 开始
     * @param end   结束
     * @return {@link Long }
     */
    public static Long betweenSeconds(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.SECONDS.between(start, end);
    }

    /**
     * 获取两个时间的分钟差值
     *
     * @param start 开始
     * @param end   结束
     * @return {@link Long }
     */
    public static Long betweenMinutes(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.MINUTES.between(start, end);
    }

    /**
     * 获取两个时间的小时差值
     *
     * @param start 开始
     * @param end   结束
     * @return {@link Long }
     */
    public static Long betweenHours(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.HOURS.between(start, end);
    }

    /**
     * 获取两个时间的天数差值
     *
     * @param start 开始
     * @param end   结束
     * @return {@link Long }
     */
    public static Long betweenDays(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.DAYS.between(start, end);
    }

    /**
     * 获取两个时间的周数差值
     *
     * @param start 开始
     * @param end   结束
     * @return {@link Long }
     */
    public static Long betweenWeeks(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.WEEKS.between(start, end);
    }

    /**
     * 获取两个时间的月数差值
     *
     * @param start 开始
     * @param end   结束
     * @return {@link Long }
     */
    public static Long betweenMonths(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.MONTHS.between(start, end);
    }

    /**
     * 获取两个时间的年数差值
     *
     * @param start 开始
     * @param end   结束
     * @return {@link Long }
     */
    public static Long betweenYears(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.YEARS.between(start, end);
    }
}
