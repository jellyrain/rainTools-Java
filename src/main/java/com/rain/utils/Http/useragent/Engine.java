package com.rain.utils.Http.useragent;

import com.rain.utils.Http.enums.EngineEnum;

import java.util.List;

/**
 * 引擎对象
 *
 * @author rain
 * @date 2024/09/03
 */
public final class Engine {

    private Engine() {

    }

    /**
     * 引擎列表 （想支持更多引擎，但是又想默认的时候走枚举结果）
     */
    public static List<EngineType> engineList = List.of(
            new EngineType(EngineEnum.TRIDENT),
            new EngineType(EngineEnum.WEBKIT),
            new EngineType(EngineEnum.CHROME),
            new EngineType(EngineEnum.OPERA),
            new EngineType(EngineEnum.PRESTO),
            new EngineType(EngineEnum.GECKO),
            new EngineType(EngineEnum.KHTML),
            new EngineType(EngineEnum.KONQUEROR),
            new EngineType(EngineEnum.MIDP)
    );

    /**
     * 添加自定义引擎
     *
     * @param name         名字
     * @param versionRegex 版本 Regex
     */
    synchronized public static void addCustomEngine(String name, String versionRegex) {
        engineList.add(new EngineType(name, versionRegex));
    }

    /**
     * 获取引擎类型
     *
     * @param userAgentString 用户代理字符串
     * @return {@link EngineType }
     */
    public static EngineType getEngineType(String userAgentString) {
        for (EngineType engineType : engineList) {
            if (engineType.isMatch(userAgentString)) {
                return engineType;
            }
        }
        return new EngineType(EngineEnum.UNKNOWN);
    }
}
