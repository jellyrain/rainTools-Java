package com.rain.utils.http.useragent;

public class UserAgent {

    /**
     * 浏览器类型
     */
    private final BrowserType browser;

    /**
     * 引擎类型
     */
    private final EngineType engine;

    /**
     * 系统类型
     */
    private final OSType os;

    /**
     * 平台类型
     */
    private final PlatformType platform;

    public UserAgent(String userAgentString) {
        this.browser = Browser.getBrowserType(userAgentString);
        this.engine = Engine.getEngineType(userAgentString);
        this.os = OS.getOSType(userAgentString);
        this.platform = Platform.getPlatformType(userAgentString);
    }

    /**
     * 是否是 移动端
     *
     * @return {@link Boolean }
     */
    public Boolean isMobile() {
        return this.platform.isMobile();
    }

    /**
     * 获取浏览器名称
     *
     * @return {@link String }
     */
    public String getBrowserName() {
        return this.browser.getName();
    }

    /**
     * 获取浏览器版本
     *
     * @return {@link String }
     */
    public String getBrowserVersion() {
        return this.browser.getVersion();
    }

    /**
     * 获取引擎名称
     *
     * @return {@link String }
     */
    public String getEngineName() {
        return this.engine.getName();
    }

    /**
     * 获取引擎版本
     *
     * @return {@link String }
     */
    public String getEngineVersion() {
        return this.engine.getVersion();
    }

    /**
     * 获取平台名称
     *
     * @return {@link String }
     */
    public String getPlatformName() {
        return this.platform.getName();
    }

    /**
     * 获取平台版本
     *
     * @return {@link String }
     */
    public String getPlatformVersion() {
        return this.os.getVersion();
    }

    /**
     * 是否是 Windows
     *
     * @return {@link Boolean }
     */
    public Boolean isWindows() {
        return this.platform.isWindows();
    }

    /**
     * 是否是 Mac
     */
    public Boolean isMac() {
        return this.platform.isMac();
    }

    /**
     * 是否是 Linux
     */
    public Boolean isLinux() {
        return this.platform.isLinux();
    }

    /**
     * 是否是 Android
     */
    public Boolean isAndroid() {
        return this.platform.isAndroid();
    }

    /**
     * 是否是 iphone
     */
    public Boolean isIPhone() {
        return this.platform.isIPhone();
    }

    /**
     * 是否是 IPad
     */
    public Boolean isIPad() {
        return this.platform.isIPad();
    }
}
