package com.farerboy.framework.boot.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 项目配置
 *
 * @author linjianbin
 */
@Configuration
@ConfigurationProperties(prefix="farerboy.project")
public class ProjectProperties {

    /**
     * 项目名称
     * @author farerboy
     */
    private String name;

    /**
     * 域名
     * @author farerboy
     */
    private String domain;

    /**
     * 项目是否需要使用环境
     */
    private boolean envEnable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
