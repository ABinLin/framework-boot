package com.farerboy.framework.boot.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 请求日志配置
 *
 * @author linjianbin
 * @date 2021/1/5 8:31 下午
 */
@ConfigurationProperties(prefix="farerboy.rest.log")
public class RestLogProperties {

    /**
     * rest 接口请求日志开启配置
     * @author farerboy
     * @date 2021/1/5 7:52 下午
     */
    private Boolean enable;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
