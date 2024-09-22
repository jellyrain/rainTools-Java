package com.rain.utils.captcha;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.random.RandomGenerator;

public abstract class AbstractCaptcha {

    /**
     * 图片的宽度
     */
    protected int width;

    /**
     * 图片的高度
     */
    protected int height;

    /**
     * 验证码干扰元素个数
     */
    protected int interfereCount;

    /**
     * 字体
     */
    protected Font font;

    /**
     * 字符个数
     */
    protected int codeCount;

    /**
     * 验证码
     */
    protected String code;

    /**
     * 验证码图片
     */
    protected byte[] imageBytes;


    /**
     * 背景色
     */
    protected Color background = Color.WHITE;

    /**
     * 文字透明度
     */
    protected AlphaComposite textAlpha;

    /**
     * 构造，使用随机验证码生成器生成验证码
     *
     * @param width          图片宽
     * @param height         图片高
     * @param codeCount      字符个数
     * @param interfereCount 验证码干扰元素个数
     */
    public AbstractCaptcha(int width, int height, int codeCount, int interfereCount) {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        this.interfereCount = interfereCount;
        // 字体高度设为验证码高度-2，留边距
        this.font = new Font(Font.SANS_SERIF, Font.PLAIN, (int) (this.height * 0.75));
    }

    /**
     * 设置背景色
     *
     * @param background 背景色
     */
    public void setBackground(Color background) {
        this.background = background;
    }

    /**
     * 设置字体
     *
     * @param font 字体
     */
    public void setFont(Font font) {
        this.font = font;
    }

    /**
     * 获取验证码
     *
     * @return {@link String }
     */
    public String getCode() {
        return code;
    }

    /**
     * 获取验证码图片的byte数组
     *
     * @return 图片的byte数组
     */
    public byte[] getImageBytes() {
        return imageBytes;
    }

    /**
     * 生成验证码
     */
    public abstract void generateCode();

    /**
     * 校验验证码
     *
     * @param code 用户输入的验证码
     * @return boolean
     */
    public abstract boolean verify(String code);

    /**
     * 写出验证码
     *
     * @param out 输出流
     * @throws IOException io异常
     */
    public void write(OutputStream out) throws IOException {
        out.write(this.imageBytes);
    }

    public void write(String path) throws IOException {
        try (FileOutputStream out = new FileOutputStream(path)) {
            out.write(this.imageBytes);
        }
    }
}
