package com.rain.utils.uuid;

/**
 * 雪花算法
 *
 * @author rain
 * @date 2024/09/11
 */
public class SnowFlake {
    /**
     * 起始时间戳
     */
    private final long twepoch = 1288834974657L;

    /**
     * 机器ID所占的位数
     */
    private final long workerIdBits = 5L;

    /**
     * 数据中心ID所占的位数
     */
    private final long datacenterIdBits = 5L;

    /**
     * 机器ID的最大值
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 数据中心ID的最大值
     */
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /**
     * 序列号所占的位数
     */
    private final long sequenceBits = 12L;

    /**
     * 机器ID左移位数
     */
    private final long workerIdShift = sequenceBits;

    /**
     * 数据中心ID左移位数
     */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间戳左移位数
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /**
     * 序列号掩码
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 机器ID
     */
    private long workerId;

    /**
     * 数据中心ID
     */
    private long datacenterId;

    /**
     * 序列号
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间戳
     */
    private long lastTimestamp = -1L;

    /**
     * 构造函数
     *
     * @param workerId     机器ID
     * @param datacenterId 数据中心ID
     */
    public SnowFlake(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("机器ID不能大于 %d 或小于 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("数据中心ID不能大于 %d 或小于 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 生成下一个ID的方法
     *
     * @return 唯一ID
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("时钟向后移动。拒绝生成 %d 毫秒的ID", lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    /**
     * 获取当前时间的辅助方法
     *
     * @return 当前时间的毫秒数
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 等待直到下一毫秒的辅助方法
     *
     * @param lastTimestamp 上次生成ID的时间戳
     * @return 当前时间的毫秒数
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }
}
