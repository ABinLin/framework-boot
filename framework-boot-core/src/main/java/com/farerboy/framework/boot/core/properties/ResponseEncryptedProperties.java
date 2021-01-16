package com.farerboy.framework.boot.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 返回值加密 属性配置项
 *
 * @author linjianbin
 */
@Configuration
@ConfigurationProperties(prefix="farerboy.response.encrypted")
public class ResponseEncryptedProperties {

    /**
     * controller层返回数据对称加密密钥
     * @author farerboy
     */
    private String key;

    /**
     * 返回数据是否加密
     * @author farerboy
     */
    private Boolean enable;

    /**
     * 当 enable 为 true 时，排查的路由集合，多个用逗号隔开
     * @author farerboy
     */
    private String exclude;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getExclude() {
        return exclude;
    }

    public void setExclude(String exclude) {
        this.exclude = exclude;
    }
}
