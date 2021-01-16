package com.farerboy.framework.boot.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * swagger 相关配置
 *
 * @author linjianbin
 */
@Configuration
@ConfigurationProperties(prefix="farerboy.swagger")
public class SwaggerProperties {

    /**
     * 文档名称
     * @author farerboy
     */
    private String title;

    /**
     * 文档描述
     * @author farerboy
     */
    private String description;

    /**
     * 服务条款URL
     * @author farerboy
     */
    private String termsOfServiceUrl;

    /**
     * 文档版本
     * @author farerboy
     */
    private String version;

    /**
     * 请求header头安全认证
     * @author farerboy
     */
    private String securitySchemes;

    /**
     * 联系人
     * @author farerboy
     */
    private String contactName;

    /**
     * 联系人地址
     * @author farerboy
     */
    private String contactUrl;

    /**
     * 联系人邮箱
     * @author farerboy
     */
    private String contactEmail;

    /**
     * 是否使用
     * @author farerboy
     */
    private Boolean enable;

    /**
     * 扫描的注解-高优先级
     */
    private String scanAnnotation;

    /**
     * 扫描的基础包-低优先级
     */
    private String scanBasePackage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTermsOfServiceUrl() {
        return termsOfServiceUrl;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSecuritySchemes() {
        return securitySchemes;
    }

    public void setSecuritySchemes(String securitySchemes) {
        this.securitySchemes = securitySchemes;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactUrl() {
        return contactUrl;
    }

    public void setContactUrl(String contactUrl) {
        this.contactUrl = contactUrl;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getScanAnnotation() {
        return scanAnnotation;
    }

    public void setScanAnnotation(String scanAnnotation) {
        this.scanAnnotation = scanAnnotation;
    }

    public String getScanBasePackage() {
        return scanBasePackage;
    }

    public void setScanBasePackage(String scanBasePackage) {
        this.scanBasePackage = scanBasePackage;
    }
}
