package com.rain.utils.Http.useragent;

import com.rain.utils.Http.enums.BrowserEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 浏览器类型
 *
 * @author rain
 * @date 2024/09/03
 */
public class BrowserType extends UserAgentType {

    BrowserType(String name, String regex, String versionRegex) {
        super(name, regex, versionRegex);
    }

    BrowserType(BrowserEnum browserEnum) {
        super(browserEnum.getName(), browserEnum.getRegex(), browserEnum.getVersionRegex());
    }

    /**
     * 设置版本正则表达式
     *
     * @param versionRegex 版本 Regex
     * @return {@link String }
     */
    @Override
    protected String setVersionRegex(String versionRegex) {
        if (null == versionRegex) {
            return null;
        } else if ("[\\\\/ ]([\\\\d\\\\w\\\\.\\\\-]+)".equals(versionRegex)) {
            return super.getName() + "[\\\\/ ]([\\\\d\\\\w\\\\.\\\\-]+)";
        } else {
            return versionRegex;
        }
    }

    /**
     * 设置匹配模式
     */
    @Override
    protected void setPattern() {
        // 设置浏览器匹配模式
        if (null != super.getRegex()) {
            super.setPattern(Pattern.compile(super.getRegex(), Pattern.CASE_INSENSITIVE));
        }

        // 设置浏览器版本匹配模式
        if (null != super.getVersionRegex()) {
            super.setVersionPattern(Pattern.compile(super.getVersionRegex(), Pattern.CASE_INSENSITIVE));
        }
    }

    /**
     * 是否为本浏览器
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
            //解析浏览器版本
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
     * 是否是移动端
     *
     * @return boolean
     */
    public boolean isMobile() {
        return "PSP".equals(super.getName()) ||
                "Yammer Mobile".equals(super.getName()) ||
                "Android Browser".equals(super.getName()) ||
                "IEMobile".equals(super.getName()) ||
                "MicroMessenger".equals(super.getName()) ||
                "miniProgram".equals(super.getName()) ||
                "DingTalk".equals(super.getName());
    }
}
