package com.rain.utils.uuid;


/**
 * 可配置的雪花算法实现
 *
 * @author rain
 * @date 2024/09/11
 */
public class ConfigurableSnowFlake {
    private final long twepoch;
    private final long workerIdBits;
    private final long datacenterIdBits;
    private final long sequenceBits;
    private final long maxWorkerId;
    private final long maxDatacenterId;
    private final long sequenceMask;
    private final long workerIdShift;
    private final long datacenterIdShift;
    private final long timestampLeftShift;

    private long workerId;
    private long datacenterId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    /**
     * 构造函数
     *
     * @param twepoch          起始时间戳
     * @param workerIdBits     机器ID所占的位数
     * @param datacenterIdBits 数据中心ID所占的位数
     * @param sequenceBits     序列号所占的位数
     * @param workerId         机器ID
     * @param datacenterId     数据中心ID
     */
    public ConfigurableSnowFlake(long twepoch, long workerIdBits, long datacenterIdBits, long sequenceBits, long workerId, long datacenterId) {
        this.twepoch = twepoch;
        this.workerIdBits = workerIdBits;
        this.datacenterIdBits = datacenterIdBits;
        this.sequenceBits = sequenceBits;

        this.maxWorkerId = -1L ^ (-1L << workerIdBits);
        this.maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
        this.sequenceMask = -1L ^ (-1L << sequenceBits);

        this.workerIdShift = sequenceBits;
        this.datacenterIdShift = sequenceBits + workerIdBits;
        this.timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

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
     * @return 唯一ID的字符串表示
     */
    public synchronized String nextId() {
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

        long id = ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;

        return Long.toString(id);
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
