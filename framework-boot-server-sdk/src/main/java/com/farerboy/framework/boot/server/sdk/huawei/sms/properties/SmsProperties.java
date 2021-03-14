package com.farerboy.framework.boot.server.sdk.huawei.sms.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * TODO description
 * 2021/3/14 5:12 下午
 *
 * @author linjianbin
 */
@Configuration
@ConfigurationProperties(prefix="farerboy.server.sdk.huawei.sms")
public class SmsProperties {

    private String appKey;

    private String appSecret;

    private String url = "https://rtcsms.cn-north-1.myhuaweicloud.com:10743/sms/batchSendSms/v1";

    /**
     * 签名通道号
     */
    private String sender;

    /**
     * 模板ID
     */
    private String templateId;

    /**
     * 签名
     */
    private String signature;

    private String smsKey;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSmsKey() {
        return smsKey;
    }

    public void setSmsKey(String smsKey) {
        this.smsKey = smsKey;
    }
}
