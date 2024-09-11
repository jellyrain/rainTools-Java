package com.rain.utils.date;

import com.rain.utils.date.LocalDateTime.FormatUtils;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author rain
 * @date 2024/08/17
 */
public class DateTime {

    private final LocalDateTime dateTime;

    public DateTime() {
        this.dateTime = LocalDateTimeUtils.now();
    }

    public DateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public DateTime(Date date) {
        this.dateTime = LocalDateTimeUtils.toLocalDateTime(date);
    }

    /**
     * 获取当前日期开始时间
     *
     * @return {@link Date }
     */
    public Date startOfDay() {
        return LocalDateTimeUtils.toDate(LocalDateTimeUtils.startOfDay(this.dateTime));
    }

    /**
     * 获取当前日期结束时间
     *
     * @return {@link Date }
     */
    public Date endOfDay() {
        return LocalDateTimeUtils.toDate(LocalDateTimeUtils.endOfDay(this.dateTime));
    }

    /**
     * 获取时间戳
     *
     * @return {@link Long }
     */
    public Long getTimestamp() {
        return LocalDateTimeUtils.timestamp(this.dateTime);
    }

    /**
     * 获取年份
     *
     * @return {@link Long }
     */
    public Long getYear() {
        return LocalDateTimeUtils.getYear(this.dateTime);
    }

    /**
     * 获取季度
     *
     * @return {@link Long }
     */
    public Long getQuarter() {
        return LocalDateTimeUtils.getQuarter(this.dateTime);
    }

    /**
     * 获取月份
     *
     * @return {@link Long }
     */
    public Long getMonth() {
        return LocalDateTimeUtils.getMonth(this.dateTime);
    }

    /**
     * 获取日
     *
     * @return {@link Long }
     */
    public Long getDay() {
        return LocalDateTimeUtils.getDay(this.dateTime);
    }

    /**
     * 获取小时
     *
     * @return {@link Long }
     */
    public Long getHour() {
        return LocalDateTimeUtils.getHour(this.dateTime);
    }

    /**
     * 获取分钟
     *
     * @return {@link Long }
     */
    public Long getMinute() {
        return LocalDateTimeUtils.getMinute(this.dateTime);
    }

    /**
     * 获取秒
     *
     * @return {@link Long }
     */
    public Long getSecond() {
        return LocalDateTimeUtils.getSecond(this.dateTime);
    }

    /**
     * 时间格式化
     *
     * @param pattern 模式
     * @return {@link String }
     */
    public String format(String pattern) {
        return FormatUtils.format(this.dateTime, pattern);
    }

    /**
     * 日期时间格式
     *
     * @return {@link String }
     */
    public String formatDateTime() {
        return FormatUtils.formatDateTime(this.dateTime);
    }

    /**
     * 日期格式
     *
     * @return {@link String }
     */
    public String formatDate() {
        return FormatUtils.formatDate(this.dateTime);
    }
}
