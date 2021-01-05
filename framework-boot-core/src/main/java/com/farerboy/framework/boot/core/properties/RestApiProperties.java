package com.farerboy.framework.boot.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * rest api 相关配置
 *
 * @author linjianbin
 * @date 2021/1/5 7:50 下午
 */
@ConfigurationProperties(prefix="farerboy.rest.api")
public class RestApiProperties {

    /**
     * 是否开启接口签名
     * @author farerboy
     * @date 2021/1/5 7:52 下午
     */
    private Boolean signEnable;

    /**
     * 接口签名秘钥
     * @author farerboy
     * @date 2021/1/5 7:52 下午
     */
    private String signKey;

    /**
     * 接口是否跨域
     * @author farerboy
     * @date 2021/1/5 7:52 下午
     */
    private Boolean crossEnable;

    public Boolean getSignEnable() {
        return signEnable;
    }

    public void setSignEnable(Boolean signEnable) {
        this.signEnable = signEnable;
    }

    public String getSignKey() {
        return signKey;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
    }

    public Boolean getCrossEnable() {
        return crossEnable;
    }

    public void setCrossEnable(Boolean crossEnable) {
        this.crossEnable = crossEnable;
    }
}
