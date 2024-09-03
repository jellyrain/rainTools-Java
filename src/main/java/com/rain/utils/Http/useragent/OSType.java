package com.rain.utils.Http.useragent;

import com.rain.utils.Http.enums.OSEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 系统类型
 *
 * @author rain
 * @date 2024/09/03
 */
public class OSType extends UserAgentType {

    OSType(String name, String regex, String versionRegex) {
        super(name, regex, versionRegex);
    }

    OSType(OSEnum osEnum) {
        super(osEnum.getName(), osEnum.getRegex(), osEnum.getVersionRegex());
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
        // 设置系统匹配模式
        if (null != super.getRegex()) {
            super.setPattern(Pattern.compile(super.getRegex(), Pattern.CASE_INSENSITIVE));
        }

        // 设置系统版本匹配模式
        if (null != super.getVersionRegex()) {
            super.setVersionPattern(Pattern.compile(super.getVersionRegex(), Pattern.CASE_INSENSITIVE));
        }
    }

    /**
     * 是否为本系统
     *
     * @param userAgentString 用户代理字符串
     * @return boolean
     */
    @Override
    public boolean isMatch(String userAgentString) {
        // 如果用户代理字符串相同则直接返回true
        if (super.getUserAgentString() != null && super.getUserAgentString().equals(userAgentString)) {
            return true;
        }

        boolean b = super.getPattern().matcher(userAgentString).find();
        // 如果匹配成功则设置用户代理字符串
        if (b) {
            super.setUserAgentString(userAgentString);
            //解析系统版本
            if (null != super.getVersionPattern()) {
                Matcher matcher = super.getVersionPattern().matcher(userAgentString);
                if (matcher.find()) {
                    super.setVersion(matcher.group(1));
                }
            }
        }
        return b;
    }

    /**
     * 是否为 Linux
     *
     * @return boolean
     */
    public boolean isLinux() {
        return "Linux".contains(super.getName());
    }

    /**
     * 是否为 MacOS
     *
     * @return boolean
     */
    public boolean isMacOS() {
        return super.getName().contains("OSX");
    }

    /**
     * 是否为 Windows
     *
     * @return boolean
     */
    public boolean isWindows() {
        return super.getName().contains("Windows");
    }

    /**
     * 是否为 IPhone
     *
     * @return boolean
     */
    public boolean isIPhone() {
        return super.getName().contains("IPhone");
    }

    /**
     * 是否为 Android
     *
     * @return boolean
     */
    public boolean isAndroid() {
        return super.getName().contains("Android");
    }
}
