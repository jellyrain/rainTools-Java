package com.rain.utils.Http.useragent;

import com.rain.utils.Http.enums.EngineEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 引擎类型
 *
 * @author rain
 * @date 2024/09/03
 */
public class EngineType extends UserAgentType {

    public EngineType(String name, String versionRegex) {
        super(name, null, versionRegex);
    }

    public EngineType(EngineEnum engineEnum) {
        super(engineEnum.getName(), null, engineEnum.getVersionRegex());
    }

    /**
     * 设置版本正则表达式
     *
     * @param versionRegex 版本 Regex
     * @return {@link String }
     */
    @Override
    protected String setVersionRegex(String versionRegex) {
        if (null == versionRegex) {
            return null;
        } else {
            return super.getName() + "[/\\- ]([\\d\\w.\\-]+)";
        }
    }

    /**
     * 设置匹配模式
     */
    @Override
    protected void setPattern() {
        if (null != super.getVersionRegex()) {
            super.setVersionPattern(Pattern.compile(super.getVersionRegex(), Pattern.CASE_INSENSITIVE));
        }
    }

    /**
     * 是否为本引擎
     *
     * @param userAgentString 用户代理字符串
     * @return boolean
     */
    @Override
    public boolean isMatch(String userAgentString) {
        // 如果已经匹配过了，直接返回
        if (super.getUserAgentString() != null && super.getUserAgentString().equals(userAgentString)) {
            return true;
        }

        // 解析版本
        if (null != super.getVersionPattern()) {
            Matcher matcher = super.getVersionPattern().matcher(userAgentString);
            if (matcher.find()) {
                super.setUserAgentString(userAgentString);
                super.setVersion(matcher.group(1));
                return true;
            }
        }

        return false;
    }
}
