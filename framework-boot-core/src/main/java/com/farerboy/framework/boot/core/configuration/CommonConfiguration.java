package com.farerboy.framework.boot.core.configuration;

import com.farerboy.framework.boot.core.filter.XssFilter;
import com.farerboy.framework.boot.core.interceptor.SignInterceptor;
import com.farerboy.framework.boot.core.util.RestUtil;
import com.farerboy.framework.boot.core.util.WebDownloadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.servlet.DispatcherType;
import java.util.Collections;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2020/12/22 11:18 上午
 */
@Configuration
public class CommonConfiguration {

    @Value("${farerboy.api.sign.secret.key:fyjysignkey12019}")
    private String secretKey;
    @Value("${farerboy.api.sign.switch:false}")
    private boolean apiSignSwitch;
    @Bean
    RestUtil restUtil(){
        return new RestUtil();
    }

    @Bean
    WebDownloadUtil webDownloadUtil(){
        return new WebDownloadUtil();
    }

    @Bean
    RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(new SignInterceptor(secretKey,apiSignSwitch)));
        return restTemplate;
    }

    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns("/*");
        registration.setName("xssFilter");
        return registration;
    }
}
