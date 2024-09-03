package com.rain.utils.Http.enums;

/**
 * 平台枚举
 *
 * @author rain
 * @date 2024/09/03
 */
public enum PlatformEnum {

    /**
     * Windows
     */
    WINDOWS("Windows", "windows", false),

    /**
     * Mac
     */
    MAC("Mac", "(macintosh|darwin)", false),

    /**
     * Linux
     */
    LINUX("Linux", "linux", false),

    /**
     * Wii
     */
    WII("Wii", "wii", false),

    /**
     * Playstation
     */
    PLAYSTATION("Playstation", "playstation", false),

    /**
     * Java
     */
    JAVA("Java", "java", false),


    /**
     * Windows Phone
     */
    WINDOWS_PHONE("Windows Phone", "windows (ce|phone|mobile)( os)?", true),

    /**
     * IPAD
     */
    IPAD("IPad", "ipad", true),

    /**
     * IPOD
     */
    IPOD("IPod", "ipod", true),

    /**
     * IPHONE
     */
    IPHONE("IPhone", "iphone", true),

    /**
     * Android
     */
    ANDROID("Android", "android", true),

    /**
     * Android XiaoMi
     */
    ANDROID_XIAOMI("Android XiaoMi", "XiaoMi|MI\\s+", true),

    /**
     * GoogleTV
     */
    GOOGLE_TV("GoogleTV", "googletv", true),

    /**
     * htcFlyer
     */
    HTC_FLYER("htcFlyer", "htc_flyer", true),

    /**
     * Symbian
     */
    SYMBIAN("Symbian", "symbian(os)?", true),

    /**
     * Blackberry
     */
    BLACKBERRY("Blackberry", "blackberry", true),

    /**
     * 未知
     */
    UNKNOWN("Unknown", null, false);

    /**
     * 平台 名称
     */
    private final String name;

    /**
     * 平台 versionRegex
     */
    private final String regex;

    /**
     * 是否是移动端
     */
    private final Boolean isMobile;

    PlatformEnum(String name, String regex, Boolean isMobile) {
        this.name = name;
        this.regex = regex;
        this.isMobile = isMobile;
    }

    public String getName() {
        return name;
    }

    public String getRegex() {
        return regex;
    }

    public Boolean getMobile() {
        return isMobile;
    }
}
