package com.rain.utils.Http.useragent;

import com.rain.utils.Http.enums.PlatformEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 平台类型
 *
 * @author rain
 * @date 2024/09/03
 */
public class PlatformType extends UserAgentType {

    /**
     * 是否是移动端
     */
    private final Boolean isMobile;

    PlatformType(String name, String regex, Boolean isMobile) {
        super(name, regex, null);
        this.isMobile = isMobile;
    }

    PlatformType(PlatformEnum platformEnum) {
        super(platformEnum.getName(), platformEnum.getRegex(), null);
        this.isMobile = platformEnum.getMobile();
    }

    /**
     * 设置版本正则表达式
     *
     * @param versionRegex 版本 Regex
     * @return {@link String }
     */
    @Override
    protected String setVersionRegex(String versionRegex) {
        return versionRegex;
    }

    /**
     * 设置匹配模式
     */
    @Override
    protected void setPattern() {
        if (null != super.getRegex()) {
            super.setPattern(Pattern.compile(super.getRegex(), Pattern.CASE_INSENSITIVE));
        }
    }

    /**
     * 是否为本平台
     *
     * @param userAgentString 用户代理字符串
     * @return boolean
     */
    @Override
    public boolean isMatch(String userAgentString) {
        // 如果已经匹配过了，直接返回
        if (super.getUserAgentString() != null && super.getUserAgentString().equals(userAgentString)) {
            return true;
        }

        // 解析平台
        if (null != super.getPattern()) {
            Matcher matcher = super.getPattern().matcher(userAgentString);
            if (matcher.find()) {
                super.setUserAgentString(userAgentString);
                return true;
            }
        }

        return false;
    }

    /**
     * 是否是 移动端
     *
     * @return {@link Boolean }
     */
    public Boolean isMobile() {
        return isMobile;
    }

    /**
     * 是否是 Windows
     *
     * @return {@link Boolean }
     */
    public Boolean isWindows() {
        return "Windows".equals(super.getName());
    }

    /**
     * 是否是 Mac
     */
    public Boolean isMac() {
        return "Mac".equals(super.getName());
    }

    /**
     * 是否是 Linux
     */
    public Boolean isLinux() {
        return "Linux".equals(super.getName());
    }

    /**
     * 是否是 Android
     */
    public Boolean isAndroid() {
        return "Android".contains(super.getName());
    }

    /**
     * 是否是 iphone
     */
    public Boolean isIPhone() {
        return "IPhone".contains(super.getName());
    }

    /**
     * 是否是 IPad
     */
    public Boolean isIPad() {
        return "IPad".contains(super.getName());
    }
}
