package com.rain.utils.uuid;

import java.util.UUID;

/**
 * id util
 *
 * @author rain
 * @date 2024/09/11
 */
public final class IdUtils {

    private static final SnowFlake snowFlake = new SnowFlake(1, 1);

    private IdUtils() {

    }

    /**
     * 生成的UUID是带 - 的字符串
     *
     * @return {@link String }
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成的是不带 - 的字符串
     *
     * @return {@link String }
     */
    public static String simpleUUID() {
        return randomUUID().replace("-", "");
    }

    /**
     * 生成 ObjectId
     *
     * @return {@link String }
     */
    public static String objectID() {
        return ObjectId.generate().toHexString();
    }

    /**
     * 雪花 ID
     * <p>
     * 使用内部自带的 new SnowFlake(1, 1)生成
     *
     * @return long
     */
    public static long snowFlakeId() {
        return snowFlake.nextId();
    }

    /**
     * 创建雪花
     * <p>
     * 如果工作机器ID和数据中心ID都是1，则返回 内部自带的 new SnowFlake(1, 1)
     *
     * @param workerId     工作机器ID
     * @param datacenterId 数据中心ID
     * @return {@link SnowFlake }
     */
    public static SnowFlake createSnowFlake(long workerId, long datacenterId) {
        return workerId == 1 && datacenterId == 1 ? snowFlake : new SnowFlake(workerId, datacenterId);
    }
}
