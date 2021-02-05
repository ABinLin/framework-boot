package com.farerboy.framework.boot.test.config;

import com.farerboy.framework.boot.test.callback.SpringCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/2/5 2:51 下午
 */
@Configuration
public class TestConfig {

//    @Bean
//    public PropertyPlaceholderConfigurerAndCallback propertyPlaceholderConfigurerAndCallback(){
//        PropertyPlaceholderConfigurerAndCallback propertyPlaceholderConfigurerAndCallback = new PropertyPlaceholderConfigurerAndCallback();
//        propertyPlaceholderConfigurerAndCallback.setCallbackClass(SpringCallback.class);
//        Resource resource = new ClassPathResource("application.yml");
//        propertyPlaceholderConfigurerAndCallback.setLocation(resource);
//        return propertyPlaceholderConfigurerAndCallback;
//    }

}
