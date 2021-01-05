package com.farerboy.framework.boot.core.configuration;

import com.farerboy.framework.boot.core.filter.XssFilter;
import com.farerboy.framework.boot.core.interceptor.SignInterceptor;
import com.farerboy.framework.boot.core.properties.RestApiProperties;
import com.farerboy.framework.boot.core.util.RestUtil;
import com.farerboy.framework.boot.core.util.WebDownloadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import javax.servlet.DispatcherType;
import java.util.Collections;

/**
 *
 *
 * @author farerboy
 * @date 2020/12/22 11:18 上午
 */
@Configuration
public class CommonConfiguration {

    @Autowired
    private RestApiProperties restApiProperties;

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
        restTemplate.setInterceptors(
                Collections.singletonList(
                        new SignInterceptor(
                                restApiProperties.getSignEnable()==null?false:restApiProperties.getSignEnable(),
                                restApiProperties.getSignKey())));
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
