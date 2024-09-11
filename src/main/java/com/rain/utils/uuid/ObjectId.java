package com.rain.utils.uuid;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Random;

/**
 * 对象 ID
 *
 * @author rain
 * @date 2024/09/11
 */
public class ObjectId {

    /**
     * 时间戳
     */
    private final int timestamp;
    /**
     * 机器标识符
     */
    private final int machineIdentifier;
    /**
     * 进程标识符
     */
    private final short processIdentifier;
    /**
     * 计数器
     */
    private final int counter;


    /**
     * 低序 3 字节
     */
    private static final int LOW_ORDER_THREE_BYTES = 0x00ffffff;
    /**
     * 机器标识符
     */
    private static final int MACHINE_IDENTIFIER;
    /**
     * 进程标识符
     */
    private static final short PROCESS_IDENTIFIER;
    /**
     * 计数器
     */
    private static int nextCounter = new Random().nextInt();

    static {
        try {
            MACHINE_IDENTIFIER = createMachineIdentifier();
            PROCESS_IDENTIFIER = createProcessIdentifier();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ObjectId() {
        this(new Date());
    }

    public ObjectId(Date date) {
        this(dateToTimestampSeconds(date), MACHINE_IDENTIFIER, PROCESS_IDENTIFIER, nextCounter++);
    }

    private ObjectId(int timestamp, int machineIdentifier, short processIdentifier, int counter) {
        this.timestamp = timestamp;
        this.machineIdentifier = machineIdentifier;
        this.processIdentifier = processIdentifier;
        this.counter = counter & LOW_ORDER_THREE_BYTES;
    }

    /**
     * 生成
     *
     * @return {@link ObjectId }
     */
    public static ObjectId generate() {
        return new ObjectId();
    }

    /**
     * 将 ObjectId 转换为十六进制字符串
     *
     * @return {@link String }
     */
    public String toHexString() {
        ByteBuffer buffer = ByteBuffer.allocate(12);
        buffer.putInt(timestamp);
        buffer.putInt(machineIdentifier);
        buffer.putShort(processIdentifier);
        buffer.putInt(counter);
        StringBuilder hexString = new StringBuilder();
        for (byte b : buffer.array()) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    /**
     * 从十六进制字符串创建 ObjectId 实例
     *
     * @param hexString 十六进制字符串
     * @return {@link ObjectId }
     */
    public static ObjectId fromHexString(String hexString) {
        if (hexString.length() != 24) {
            throw new IllegalArgumentException("Invalid hex string length");
        }
        ByteBuffer buffer = ByteBuffer.allocate(12);
        for (int i = 0; i < hexString.length(); i += 2) {
            buffer.put((byte) Integer.parseInt(hexString.substring(i, i + 2), 16));
        }
        buffer.flip();
        int timestamp = buffer.getInt();
        int machineIdentifier = buffer.getInt();
        short processIdentifier = buffer.getShort();
        int counter = buffer.getInt();
        return new ObjectId(timestamp, machineIdentifier, processIdentifier, counter);
    }

    /**
     * 生成机器标识符（例如基于网络接口）
     *
     * @return int
     */
    private static int createMachineIdentifier() {
        return new Random().nextInt();
    }

    /**
     * 生成进程标识符（例如基于进程ID）
     *
     * @return short
     */
    private static short createProcessIdentifier() {
        return (short) new Random().nextInt(Short.MAX_VALUE + 1);
    }

    /**
     * 日期转时间戳（秒）
     *
     * @param date 日期
     * @return int
     */
    private static int dateToTimestampSeconds(Date date) {
        return (int) (date.getTime() / 1000);
    }

}
