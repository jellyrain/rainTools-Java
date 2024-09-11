package com.rain.utils.http.useragent;

import com.rain.utils.http.enums.PlatformEnum;

import java.util.List;

/**
 * 平台对象
 *
 * @author rain
 * @date 2024/09/03
 */
public final class Platform {

    private Platform() {

    }

    /**
     * 平台列表 （想支持更多平台，但是又想默认的时候走枚举结果）
     */
    public static List<PlatformType> platformList = List.of(
            new PlatformType(PlatformEnum.WINDOWS),
            new PlatformType(PlatformEnum.MAC),
            new PlatformType(PlatformEnum.LINUX),
            new PlatformType(PlatformEnum.WII),
            new PlatformType(PlatformEnum.PLAYSTATION),
            new PlatformType(PlatformEnum.JAVA),

            new PlatformType(PlatformEnum.WINDOWS_PHONE),
            new PlatformType(PlatformEnum.IPAD),
            new PlatformType(PlatformEnum.IPOD),
            new PlatformType(PlatformEnum.IPHONE),
            new PlatformType(PlatformEnum.ANDROID),
            new PlatformType(PlatformEnum.ANDROID_XIAOMI),
            new PlatformType(PlatformEnum.GOOGLE_TV),
            new PlatformType(PlatformEnum.HTC_FLYER),
            new PlatformType(PlatformEnum.SYMBIAN),
            new PlatformType(PlatformEnum.BLACKBERRY)
    );

    /**
     * 添加自定义平台
     *
     * @param name     名字
     * @param regex    regex
     * @param isMobile 是否是移动端
     */
    synchronized public static void addCustomPlatform(String name, String regex, Boolean isMobile) {
        platformList.add(new PlatformType(name, regex, isMobile));
    }

    /**
     * 添加自定义桌面平台
     *
     * @param name  名字
     * @param regex regex
     */
    public static void addCustomDesktopPlatform(String name, String regex) {
        addCustomPlatform(name, regex, false);
    }

    /**
     * 添加自定义移动平台
     *
     * @param name  名字
     * @param regex regex
     */
    public static void addCustomMobilePlatform(String name, String regex) {
        addCustomPlatform(name, regex, true);
    }

    /**
     * 获取平台类型
     *
     * @param userAgentString 用户代理字符串
     * @return {@link PlatformType }
     */
    public static PlatformType getPlatformType(String userAgentString) {
        for (PlatformType platformType : platformList) {
            if (platformType.isMatch(userAgentString)) {
                return platformType;
            }
        }
        return new PlatformType(PlatformEnum.UNKNOWN);
    }
}
