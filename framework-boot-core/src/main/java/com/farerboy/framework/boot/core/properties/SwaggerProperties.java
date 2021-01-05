package com.farerboy.framework.boot.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * swagger 相关配置
 *
 * @author linjianbin
 * @date 2021/1/5 8:12 下午
 */
@ConfigurationProperties(prefix="farerboy.swagger")
public class SwaggerProperties {

    /**
     * 文档名称
     * @author farerboy
     * @date 2021/1/5 8:16 下午
     */
    private String title;

    /**
     * 文档描述
     * @author farerboy
     * @date 2021/1/5 8:16 下午
     */
    private String description;

    /**
     * 服务条款URL
     * @author farerboy
     * @date 2021/1/5 8:16 下午
     */
    private String termsOfServiceUrl;

    /**
     * 文档版本
     * @author farerboy
     * @date 2021/1/5 8:16 下午
     */
    private String version;

    /**
     * 请求header头安全认证
     * @author farerboy
     * @date 2021/1/5 8:16 下午
     */
    private String securitySchemes;

    /**
     * 联系人
     * @author farerboy
     * @date 2021/1/5 8:16 下午
     */
    private String contactName;

    /**
     * 联系人地址
     * @author farerboy
     * @date 2021/1/5 8:16 下午
     */
    private String contactUrl;

    /**
     * 联系人邮箱
     * @author farerboy
     * @date 2021/1/5 8:16 下午
     */
    private String contactEmail;

    /**
     * 是否使用
     * @author farerboy
     * @date 2021/1/5 8:19 下午
     */
    private Boolean enable;

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
}
