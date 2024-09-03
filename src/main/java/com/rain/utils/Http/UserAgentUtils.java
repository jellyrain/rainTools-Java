package com.rain.utils.Http;

import com.rain.utils.Http.useragent.*;

/**
 * 用户代理工具类
 *
 * @author rain
 * @date 2024/09/03
 */
public final class UserAgentUtils {

    private UserAgentUtils() {

    }

    /**
     * 解析用户代理
     *
     * @param userAgentString 用户代理字符串
     * @return {@link UserAgent }
     */
    public static UserAgent parse(String userAgentString) {
        return new UserAgent(userAgentString);
    }

    /**
     * 添加自定义浏览器
     *
     * @param name         名字
     * @param regex        正则表达式
     * @param versionRegex 版本 Regex
     */
    public static void addCustomBrowser(String name, String regex, String versionRegex) {
        Browser.addCustomBrowser(name, regex, versionRegex);
    }

    /**
     * 添加自定义引擎
     *
     * @param name         名字
     * @param versionRegex 版本 Regex
     */
    public static void addCustomEngine(String name, String versionRegex) {
        Engine.addCustomEngine(name, versionRegex);
    }

    /**
     * 添加自定义系统
     *
     * @param name         名字
     * @param regex        正则表达式
     * @param versionRegex 版本 Regex
     */
    public static void addCustomOS(String name, String regex, String versionRegex) {
        OS.addCustomOS(name, regex, versionRegex);
    }

    /**
     * 添加自定义桌面平台
     *
     * @param name  名字
     * @param regex regex
     */
    public static void addCustomDesktopPlatform(String name, String regex) {
        Platform.addCustomPlatform(name, regex, false);
    }

    /**
     * 添加自定义移动平台
     *
     * @param name  名字
     * @param regex regex
     */
    public static void addCustomMobilePlatform(String name, String regex) {
        Platform.addCustomPlatform(name, regex, true);
    }
}
