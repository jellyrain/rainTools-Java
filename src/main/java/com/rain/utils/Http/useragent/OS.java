package com.rain.utils.Http.useragent;

import com.rain.utils.Http.enums.OSEnum;

import java.util.List;

/**
 * 系统对象
 *
 * @author rain
 * @date 2024/09/03
 */
public final class OS {

    private OS() {

    }

    /**
     * 系统列表 （想支持更多系统，但是又想默认的时候走枚举结果）
     */
    public static List<OSType> osList = List.of(
            new OSType(OSEnum.WINDOWS_10),
            new OSType(OSEnum.WINDOWS_8_1),
            new OSType(OSEnum.WINDOWS_8),
            new OSType(OSEnum.WINDOWS_VISTA),
            new OSType(OSEnum.WINDOWS_7),
            new OSType(OSEnum.WINDOWS_2003),
            new OSType(OSEnum.WINDOWS_XP),
            new OSType(OSEnum.WINDOWS_2000),
            new OSType(OSEnum.WINDOWS_PHONE),
            new OSType(OSEnum.WINDOWS),
            new OSType(OSEnum.OSX),
            new OSType(OSEnum.ANDROID),
            new OSType(OSEnum.ANDROID_XIAOMI),
            new OSType(OSEnum.LINUX),
            new OSType(OSEnum.WII),
            new OSType(OSEnum.PS3),
            new OSType(OSEnum.PSP),
            new OSType(OSEnum.IPAD),
            new OSType(OSEnum.IPHONE),
            new OSType(OSEnum.IPOD_Y),
            new OSType(OSEnum.IPAD_Y),
            new OSType(OSEnum.IPHONE_Y),
            new OSType(OSEnum.SYMBIAN),
            new OSType(OSEnum.DARWIN),
            new OSType(OSEnum.ADOBE_AIR),
            new OSType(OSEnum.JAVA)
    );

    /**
     * 添加自定义系统
     *
     * @param name         名字
     * @param regex        正则表达式
     * @param versionRegex 版本 Regex
     */
    public static void addCustomOS(String name, String regex, String versionRegex) {
        osList.add(new OSType(name, regex, versionRegex));
    }

    /**
     * 判断系统类型
     *
     * @param userAgentString 用户代理字符串
     * @return {@link OSType }
     */
    public static OSType getOSType(String userAgentString) {
        for (OSType osType : osList) {
            if (osType.isMatch(userAgentString)) {
                return osType;
            }
        }
        return new OSType(OSEnum.UNKNOWN);
    }
}
