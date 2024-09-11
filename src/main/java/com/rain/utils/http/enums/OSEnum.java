package com.rain.utils.http.enums;

/**
 * 系统枚举
 *
 * @author rain
 * @date 2024/09/03
 */
public enum OSEnum {
    /**
     * Windows 10 or Windows Server 2016
     */
    WINDOWS_10("Windows 10 or Windows Server 2016", "windows nt 10\\.0", "windows nt (10\\.0)"),

    /**
     * Windows 8.1 or Windows Server 2012R2
     */
    WINDOWS_8_1("Windows 8.1 or Windows Server 2012R2", "windows nt 6\\.3", "windows nt (6\\.3)"),

    /**
     * Windows 8 or Windows Server 2012
     */
    WINDOWS_8("Windows 8 or Windows Server 2012", "windows nt 6\\.2", "windows nt (6\\.2)"),

    /**
     * Windows Vista
     */
    WINDOWS_VISTA("Windows Vista", "windows nt 6\\.0", "windows nt (6\\.0)"),

    /**
     * Windows 7 or Windows Server 2008R2
     */
    WINDOWS_7("Windows 7 or Windows Server 2008R2", "windows nt 6\\.1", "windows nt (6\\.1)"),

    /**
     * Windows 2003
     */
    WINDOWS_2003("Windows 2003", "windows nt 5\\.2", "windows nt (5\\.2)"),

    /**
     * Windows XP
     */
    WINDOWS_XP("Windows XP", "windows nt 5\\.1", "windows nt (5\\.1)"),

    /**
     * Windows 2000
     */
    WINDOWS_2000("Windows 2000", "windows nt 5\\.0", "windows nt (5\\.0)"),

    /**
     * Windows Phone
     */
    WINDOWS_PHONE("Windows Phone", "windows (ce|phone|mobile)( os)?", "windows (?:ce|phone|mobile) (\\d+([._]\\d+)*)"),

    /**
     * Windows
     */
    WINDOWS("Windows", "windows", null),

    /**
     * OSX
     */
    OSX("OSX", "os x (\\d+)[._](\\d+)", "os x (\\d+([._]\\d+)*)"),

    /**
     * Android
     */
    ANDROID("Android", "Android", "Android (\\d+([._]\\d+)*)"),

    /**
     * Android
     */
    ANDROID_XIAOMI("Android", "XiaoMi|MI\\s+", "\\(X(\\d+([._]\\d+)*)"),

    /**
     * Linux
     */
    LINUX("Linux", "linux", null),

    /**
     * Wii
     */
    WII("Wii", "wii", "wii libnup/(\\d+([._]\\d+)*)"),

    /**
     * PS3
     */
    PS3("PS3", "playstation 3", "playstation 3; (\\d+([._]\\d+)*)"),

    /**
     * PSP
     */
    PSP("PSP", "playstation portable", "Portable\\); (\\d+([._]\\d+)*)"),

    /**
     * iPad
     */
    IPAD("iPad", "\\(iPad.*os (\\d+)[._](\\d+)", "\\(iPad.*os (\\d+([._]\\d+)*)"),

    /**
     * iPhone
     */
    IPHONE("iPhone", "\\(iPhone.*os (\\d+)[._](\\d+)", "\\(iPhone.*os (\\d+([._]\\d+)*)"),

    /**
     * iPod
     */
    IPOD_Y("YPod", "iPod touch[\\s\\;]+iPhone.*os (\\d+)[._](\\d+)", "iPod touch[\\s\\;]+iPhone.*os (\\d+([._]\\d+)*)"),

    /**
     * iPad
     */
    IPAD_Y("YPad", "iPad[\\s\\;]+iPhone.*os (\\d+)[._](\\d+)", "iPad[\\s\\;]+iPhone.*os (\\d+([._]\\d+)*)"),

    /**
     * iPhone
     */
    IPHONE_Y("YPhone", "iPhone[\\s\\;]+iPhone.*os (\\d+)[._](\\d+)", "iPhone[\\s\\;]+iPhone.*os (\\d+([._]\\d+)*)"),

    /**
     * Symbian
     */
    SYMBIAN("Symbian", "symbian(os)?", null),

    /**
     * Darwin
     */
    DARWIN("Darwin", "Darwin\\/([\\d\\w\\.\\-]+)", "Darwin\\/([\\d\\w\\.\\-]+)"),

    /**
     * Adobe Air
     */
    ADOBE_AIR("Adobe Air", "AdobeAir\\/([\\d\\w\\.\\-]+)", "AdobeAir\\/([\\d\\w\\.\\-]+)"),

    /**
     * Java
     */
    JAVA("Java", "Java[\\s]+([\\d\\w\\.\\-]+)", "Java[\\s]+([\\d\\w\\.\\-]+)"),

    /**
     * 未知
     */
    UNKNOWN("Unknown", null, null);

    /**
     * 系统 名称
     */
    private final String name;

    /**
     * 系统 Regex
     */
    private final String regex;

    /**
     * 系统版本 Regex
     */
    private final String versionRegex;

    OSEnum(String name, String regex, String versionRegex) {
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
