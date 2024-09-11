package com.rain.utils.http.enums;

/**
 * 引擎枚举
 *
 * @author rain
 * @date 2024/09/03
 */
public enum EngineEnum {
    /**
     * Trident
     */
    TRIDENT("Trident", "trident"),

    /**
     * Webkit
     */
    WEBKIT("Webkit", "webkit"),

    /**
     * Chrome
     */
    CHROME("Chrome", "chrome"),

    /**
     * Opera
     */
    OPERA("Opera", "opera"),

    /**
     * Presto
     */
    PRESTO("Presto", "presto"),

    /**
     * Gecko
     */
    GECKO("Gecko", "gecko"),

    /**
     * KHTML
     */
    KHTML("KHTML", "khtml"),

    /**
     * Konqueror
     */
    KONQUEROR("Konqueror", "konqueror"),

    /**
     * MIDP
     */
    MIDP("MIDP", "MIDP"),

    /**
     * 未知引擎
     */
    UNKNOWN("Unknown",null);


    /**
     * 引擎 名称
     */
    private final String name;

    /**
     * 引擎 versionRegex
     */
    private final String versionRegex;

    EngineEnum(String name, String versionRegex) {
        this.name = name;
        this.versionRegex = versionRegex;
    }

    public String getName() {
        return name;
    }

    public String getVersionRegex() {
        return versionRegex;
    }
}
