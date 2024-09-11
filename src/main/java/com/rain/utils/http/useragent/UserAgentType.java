package com.rain.utils.http.useragent;

import java.util.regex.Pattern;

/**
 * 用户代理抽象类
 *
 * @author rain
 * @date 2024/09/03
 */
abstract class UserAgentType {

    /**
     * 名字
     */
    private final String name;

    /**
     * 正则表达式
     */
    private final String regex;

    /**
     * 版本正则表达式
     */
    private final String versionRegex;

    /**
     * 版本
     */
    private String version;

    /**
     * 模式
     */
    private Pattern pattern;

    /**
     * 版本模式
     */
    private Pattern versionPattern;

    /**
     * 用户代理字符串
     */
    private String userAgentString;

    public UserAgentType(String name, String regex, String versionRegex) {
        this.name = name;
        this.regex = regex;
        this.versionRegex = this.setVersionRegex(versionRegex);
        this.setPattern();
    }

    /**
     * 获取名称
     *
     * @return {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * 获取版本
     *
     * @return {@link String }
     */
    public String getVersion() {
        return version;
    }

    /**
     * 获取用户代理字符串
     *
     * @return {@link String }
     */
    public String getUserAgentString() {
        return userAgentString;
    }

    // 继承类使用
    protected String getRegex() {
        return regex;
    }

    // 继承类使用
    protected String getVersionRegex() {
        return versionRegex;
    }

    // 继承类使用
    protected void setUserAgentString(String userAgentString) {
        this.userAgentString = userAgentString;
    }

    // 继承类使用
    protected Pattern getVersionPattern() {
        return versionPattern;
    }

    // 继承类使用
    protected void setVersionPattern(Pattern versionPattern) {
        this.versionPattern = versionPattern;
    }

    // 继承类使用
    protected Pattern getPattern() {
        return pattern;
    }

    // 继承类使用
    protected void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    // 继承类使用
    protected void setVersion(String version) {
        this.version = version;
    }

    /**
     * 设置版本正则表达式
     *
     * @param versionRegex 版本 Regex
     * @return {@link String }
     */
    abstract protected String setVersionRegex(String versionRegex);

    /**
     * 设置匹配模式
     */
    abstract protected void setPattern();

    /**
     * 是否为本类型
     *
     * @param userAgentString 用户代理字符串
     * @return boolean
     */
    abstract public boolean isMatch(String userAgentString);
}
