package com.rain.utils.captcha;

import com.rain.utils.captcha.enums.OperatorsEnum;

/**
 * 验证码工具类
 *
 * @author rain
 * @date 2024/09/22
 */
public final class CaptchaUtils {

    private CaptchaUtils() {

    }

    /**
     * 创建图片验证码
     *
     * @param width  宽度
     * @param height 高度
     * @return {@link AbstractCaptcha }
     */
    public static AbstractCaptcha createShearCaptcha(int width, int height) {
        return new ShearCaptcha(width, height, 4, 4, null, null);
    }

    /**
     * 创建图片验证码
     *
     * @param width     宽度
     * @param height    高度
     * @param codeCount 字符个数
     * @param thickness 干扰线宽度
     * @return {@link AbstractCaptcha }
     */
    public static AbstractCaptcha createShearCaptcha(int width, int height, int codeCount, int thickness) {
        return new ShearCaptcha(width, height, codeCount, thickness, null, null);
    }

    /**
     * 创建数学计算验证码
     *
     * @param width  宽度
     * @param height 高度
     * @return {@link AbstractCaptcha }
     */
    public static AbstractCaptcha createMathCaptcha(int width, int height) {
        return new MathCaptcha(width, height, 4, 4, null, null, OperatorsEnum.ADDITION);
    }

    /**
     * 创建 Math Captcha
     *
     * @param width         宽度
     * @param height        高度
     * @param thickness     干扰线宽度
     * @param operatorsEnum 运算符
     * @return {@link AbstractCaptcha }
     */
    public static AbstractCaptcha createMathCaptcha(int width, int height, int thickness, OperatorsEnum operatorsEnum) {
        return new MathCaptcha(width, height, 4, thickness, null, null, operatorsEnum);
    }
}
