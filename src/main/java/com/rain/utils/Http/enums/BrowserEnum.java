package com.rain.utils.Http.enums;

/**
 * 浏览器枚举
 *
 * @author rain
 * @date 2024/09/03
 */
public enum BrowserEnum {

    /**
     * 企业微信
     */
    WECHAT_WORK("wxwork", "wxwork", "wxwork\\/([\\d\\w\\.\\-]+)"),

    /**
     * 微信
     */
    MICRO_MESSENGER("MicroMessenger", "MicroMessenger", "[\\/ ]([\\d\\w\\.\\-]+)"),

    /**
     * 微信小程序
     */
    MINI_PROGRAM("miniProgram", "miniProgram", "[\\/ ]([\\d\\w\\.\\-]+)"),

    /**
     * QQ浏览器
     */
    QQ_BROWSER("QQBrowser", "QQBrowser", "QQBrowser\\/([\\d\\w\\.\\-]+)"),

    /**
     * 钉钉PC端浏览器
     */
    DING_TALK_WIN("DingTalk-win", "dingtalk-win", "DingTalk\\(([\\d\\w\\.\\-]+)\\)"),

    /**
     * 钉钉内置浏览器
     */
    DING_TALK("DingTalk", "DingTalk", "AliApp\\(DingTalk\\/([\\d\\w\\.\\-]+)\\)"),

    /**
     * 支付宝内置浏览器
     */
    ALIPAY("Alipay", "AlipayClient", "AliApp\\(AP\\/([\\d\\w\\.\\-]+)\\)"),

    /**
     * 淘宝内置浏览器
     */
    TAOBAO("Taobao", "taobao", "AliApp\\(TB\\/([\\d\\w\\.\\-]+)\\)"),

    /**
     * UC浏览器
     */
    UC_BROWSER("UCBrowser", "UC?Browser", "UC?Browser\\/([\\d\\w\\.\\-]+)"),

    /**
     * XiaoMi 浏览器
     */
    MIUI_BROWSER("MiuiBrowser", "MiuiBrowser|mibrowser", "MiuiBrowser\\/([\\d\\w\\.\\-]+)"),

    /**
     * 夸克浏览器
     */
    QUARK("Quark", "Quark", "[\\/ ]([\\d\\w\\.\\-]+)"),

    /**
     * 联想浏览器
     */
    LENOVO("Lenovo", "SLBrowser", "SLBrowser/([\\d\\w\\.\\-]+)"),

    /**
     * Edge浏览器
     */
    MS_EDGE("MSEdge", "Edge|Edg", "(?:edge|Edg|EdgA)\\/([\\d\\w\\.\\-]+)"),

    /**
     * Chrome浏览器
     */
    CHROME("Chrome", "chrome|(iphone.*crios.*safari)", "(?:Chrome|CriOS)\\/([\\d\\w\\.\\-]+)"),

    /**
     * Firefox浏览器
     */
    FIREFOX("Firefox", "firefox", "[\\/ ]([\\d\\w\\.\\-]+)"),

    /**
     * IE Mobile浏览器
     */
    IE_MOBILE("IEMobile", "iemobile", "[\\/ ]([\\d\\w\\.\\-]+)"),

    /**
     * Android浏览器
     */
    ANDROID_BROWSER("Android Browser", "android", "version\\/([\\d\\w\\.\\-]+)"),

    /**
     * Safari浏览器
     */
    SAFARI("Safari", "safari", "version\\/([\\d\\w\\.\\-]+)"),

    /**
     * Opera浏览器
     */
    OPERA("Opera", "opera", "[\\/ ]([\\d\\w\\.\\-]+)"),

    /**
     * Konqueror浏览器
     */
    KONQUEROR("Konqueror", "konqueror", "[\\/ ]([\\d\\w\\.\\-]+)"),

    /**
     * PS3浏览器
     */
    PS3("PS3", "playstation 3", "([\\d\\w\\.\\-]+)\\)\\s*$"),

    /**
     * PSP浏览器
     */
    PSP("PSP", "playstation portable", "([\\d\\w\\.\\-]+)\\)?\\s*$"),

    /**
     * Lotus浏览器
     */
    LOTUS("Lotus", "lotus.notes", "Lotus-Notes\\/([\\w.]+)"),

    /**
     * Thunderbird浏览器
     */
    THUNDERBIRD("Thunderbird", "thunderbird", "[\\/ ]([\\d\\w\\.\\-]+)"),

    /**
     * Netscape浏览器
     */
    NETSCAPE("Netscape", "netscape", "[\\/ ]([\\d\\w\\.\\-]+)"),

    /**
     * Seamonkey浏览器
     */
    SEAMONKEY("Seamonkey", "seamonkey", "[\\/ ]([\\d\\w\\.\\-]+)"),

    /**
     * Outlook浏览器
     */
    OUTLOOK("Outlook", "microsoft.outlook", "[\\/ ]([\\d\\w\\.\\-]+)"),

    /**
     * Evolution浏览器
     */
    EVOLUTION("Evolution", "evolution", "[\\/ ]([\\d\\w\\.\\-]+)"),

    /**
     * MSIE浏览器
     */
    MSIE("MSIE", "msie", "msie ([\\d\\w\\.\\-]+)"),

    /**
     * MSIE11浏览器
     */
    MSIE11("MSIE11", "rv:11", "rv:([\\d\\w\\.\\-]+)"),

    /**
     * Gabble浏览器
     */
    GABBLE("Gabble", "Gabble", "[\\/ ]([\\d\\w\\.\\-]+)"),

    /**
     * 雅虎 浏览器
     */
    YAMMER_DESKTOP("Yammer Desktop", "AdobeAir", "([\\d\\w\\.\\-]+)\\/Yammer"),

    /**
     * 雅虎手机浏览器
     */
    YAMMER_MOBILE("Yammer Mobile", "Yammer[\\s]+([\\d\\w\\.\\-]+)", "Yammer[\\s]+([\\d\\w\\.\\-]+)"),

    /**
     * Apache HTTP Client浏览器
     */
    APACHE_HTTP_CLIENT("Apache HTTP Client", "Apache\\\\-HttpClient", "Apache\\-HttpClient\\/([\\d\\w\\.\\-]+)"),

    /**
     * BlackBerry浏览器
     */
    BLACK_BERRY("BlackBerry", "BlackBerry", "BlackBerry[\\d]+\\/([\\d\\w\\.\\-]+)"),

    /**
     * 百度浏览器
     */
    BAIDU("Baidu", "Baidu", "baiduboxapp\\/([\\d\\w\\.\\-]+)"),

    /**
     * 未知浏览器
     */
    UNKNOWN("Unknown", null, null);


    /**
     * 浏览器 名称
     */
    private final String name;

    /**
     * 浏览器 Regex
     */
    private final String regex;

    /**
     * 浏览器版本 Regex
     */
    private final String versionRegex;

    BrowserEnum(String name, String regex, String versionRegex) {
        this.name = name;
        this.regex = regex;
        this.versionRegex = versionRegex;
    }

    public String getName() {
        return name;
    }

    public String getRegex() {
        return regex;
    }

    public String getVersionRegex() {
        return versionRegex;
    }
}
