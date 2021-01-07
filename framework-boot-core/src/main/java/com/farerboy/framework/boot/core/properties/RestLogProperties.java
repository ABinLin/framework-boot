package com.farerboy.framework.boot.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 请求日志配置
 *
 * @author linjianbin
 */
@ConfigurationProperties(prefix="farerboy.rest.log")
public class RestLogProperties {

    /**
     * rest 接口请求日志开启配置
     * @author farerboy
     */
    private Boolean enable;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
