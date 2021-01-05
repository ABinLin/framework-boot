package com.farerboy.framework.boot.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 项目配置
 *
 * @author linjianbin
 * @date 2021/1/5 8:57 下午
 */
@ConfigurationProperties(prefix="farerboy.project")
public class ProjectProperties {

    /**
     * 项目名称
     * @author farerboy
     * @date 2021/1/5 8:59 下午
     */
    private String name;

    /**
     * 域名
     * @author farerboy
     * @date 2021/1/5 8:59 下午
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
