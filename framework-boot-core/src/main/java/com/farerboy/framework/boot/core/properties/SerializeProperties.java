package com.farerboy.framework.boot.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * TODO description
 * 2021/3/16 11:01 上午
 *
 * @author linjianbin
 */
@Configuration
@ConfigurationProperties(prefix="farerboy.project.serialize")
public class SerializeProperties {

    private boolean long2string = false;

    private boolean date2format = false;

    public boolean isLong2string() {
        return long2string;
    }

    public void setLong2string(boolean long2string) {
        this.long2string = long2string;
    }

    public boolean isDate2format() {
        return date2format;
    }

    public void setDate2format(boolean date2format) {
        this.date2format = date2format;
    }
}
