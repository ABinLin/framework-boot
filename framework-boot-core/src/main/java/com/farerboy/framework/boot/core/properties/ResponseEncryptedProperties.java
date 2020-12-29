package com.farerboy.framework.boot.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 返回值加密 属性配置项
 *
 * @author linjianbin
 * @date 2020/12/26 7:29 下午
 */
@ConfigurationProperties(prefix="farerboy.response.encrypted")
public class ResponseEncryptedProperties {

    /**
     * controller层返回数据对称加密密钥
     * @author farerboy
     * @date 2020/12/26 5:25 下午
     */
    private String key;

    /**
     * 返回数据是否加密
     * @author farerboy
     * @date 2020/12/26 5:25 下午
     */
    private Boolean enable;

    /**
     * 当 enable 为 true 时，排查的路由集合，多个用逗号隔开
     * @author farerboy
     * @date 2020/12/26 5:25 下午
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
