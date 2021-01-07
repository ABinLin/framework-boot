package com.farerboy.framework.boot.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 项目配置
 *
 * @author linjianbin
 */
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
