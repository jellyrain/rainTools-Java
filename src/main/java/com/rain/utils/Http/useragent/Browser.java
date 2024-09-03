package com.rain.utils.Http.useragent;

import com.rain.utils.Http.enums.BrowserEnum;

import java.util.List;

/**
 * 浏览器对象
 *
 * @author rain
 * @date 2024/09/03
 */
public final class Browser {

    private Browser() {

    }

    /**
     * 浏览器列表 （想支持更多浏览器，但是又想默认的时候走枚举结果）
     */
    public static List<BrowserType> browerList = List.of(
            new BrowserType(BrowserEnum.WECHAT_WORK),
            new BrowserType(BrowserEnum.MICRO_MESSENGER),
            new BrowserType(BrowserEnum.MINI_PROGRAM),
            new BrowserType(BrowserEnum.QQ_BROWSER),
            new BrowserType(BrowserEnum.DING_TALK_WIN),
            new BrowserType(BrowserEnum.DING_TALK),
            new BrowserType(BrowserEnum.ALIPAY),
            new BrowserType(BrowserEnum.TAOBAO),
            new BrowserType(BrowserEnum.UC_BROWSER),
            new BrowserType(BrowserEnum.MIUI_BROWSER),
            new BrowserType(BrowserEnum.MS_EDGE),
            new BrowserType(BrowserEnum.CHROME),
            new BrowserType(BrowserEnum.FIREFOX),
            new BrowserType(BrowserEnum.IE_MOBILE),
            new BrowserType(BrowserEnum.ANDROID_BROWSER),
            new BrowserType(BrowserEnum.SAFARI),
            new BrowserType(BrowserEnum.OPERA),
            new BrowserType(BrowserEnum.KONQUEROR),
            new BrowserType(BrowserEnum.PS3),
            new BrowserType(BrowserEnum.PSP),
            new BrowserType(BrowserEnum.LOTUS),
            new BrowserType(BrowserEnum.THUNDERBIRD),
            new BrowserType(BrowserEnum.NETSCAPE),
            new BrowserType(BrowserEnum.SEAMONKEY),
            new BrowserType(BrowserEnum.OUTLOOK),
            new BrowserType(BrowserEnum.EVOLUTION),
            new BrowserType(BrowserEnum.MSIE),
            new BrowserType(BrowserEnum.MSIE11),
            new BrowserType(BrowserEnum.GABBLE),
            new BrowserType(BrowserEnum.YAMMER_DESKTOP),
            new BrowserType(BrowserEnum.YAMMER_MOBILE),
            new BrowserType(BrowserEnum.APACHE_HTTP_CLIENT),
            new BrowserType(BrowserEnum.BLACK_BERRY),
            new BrowserType(BrowserEnum.BAIDU)
    );

    /**
     * 添加自定义浏览器
     *
     * @param name         名字
     * @param regex        正则表达式
     * @param versionRegex 版本 Regex
     */
    synchronized public static void addCustomBrowser(String name, String regex, String versionRegex) {
        browerList.add(new BrowserType(name, regex, versionRegex));
    }

    /**
     * 判断浏览器类型
     *
     * @param userAgentString 用户代理字符串
     * @return {@link BrowserType }
     */
    public static BrowserType getBrowserType(String userAgentString) {
        for (BrowserType browserType : browerList) {
            if (browserType.isMatch(userAgentString)) {
                return browserType;
            }
        }
        return new BrowserType(BrowserEnum.UNKNOWN);
    }
}
