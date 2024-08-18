package com.rain.utils.Date.LocalDateTime;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 计时器
 *
 * @author rain
 * @date 2024/08/17
 */
public class TimeInterval {

    private final Map<String, LocalDateTime> intervalMap = new HashMap<>();

    /**
     * 开始
     *
     * @param id 计时器 ID
     */
    public void intervalStart(String id) {
        intervalMap.put(id, LocalDateTime.now());
    }

    /**
     * 计时结束返回相差时间（毫秒）
     *
     * @param id 计时器 ID
     * @return {@link Long }
     */
    public Long intervalEndMillis(String id) {
        LocalDateTime start = intervalMap.get(id);
        LocalDateTime end = LocalDateTime.now();
        intervalMap.remove(id);
        return BetweenUtils.betweenMillis(start, end);
    }

    /**
     * 计时结束返回相差时间（秒）
     *
     * @param id 计时器 ID
     * @return {@link Long }
     */
    public Long intervalEndSeconds(String id) {
        LocalDateTime start = intervalMap.get(id);
        LocalDateTime end = LocalDateTime.now();
        intervalMap.remove(id);
        return BetweenUtils.betweenSeconds(start, end);
    }

    /**
     * 删除计时器
     *
     * @param id 计时器 ID
     */
    public void intervalRemove(String id) {
        intervalMap.remove(id);
    }
}
