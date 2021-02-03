package com.farerboy.framework.boot.core.configuration;

import com.farerboy.framework.boot.core.helper.spring.ApplicationContextHelper;
import com.farerboy.framework.boot.core.interceptor.InterceptorOrder;
import com.farerboy.framework.boot.core.interceptor.LogInterceptor;
import com.farerboy.framework.boot.core.properties.RestLogProperties;
import com.farerboy.framework.boot.common.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.annotation.Resource;
import java.util.*;

/**
 * web mvc 配置
 *
 * @author farerboy
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Resource
    private ApplicationContextHelper applicationContextHelper;

    @Autowired
    private RestLogProperties restLogProperties;

    private static List<String> excludePath = new ArrayList<>();

    static {
        excludePath.add("/**/*.css");
        excludePath.add("/**/*.png");
        excludePath.add("/**/*.js");
        excludePath.add("/**/*.jpg");
        excludePath.add("/**/*.jpeg");
        excludePath.add("/*.html");
        excludePath.add("/**/*.html");
        excludePath.add("/swagger-resources/**");
        excludePath.add("/error");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        if(restLogProperties.getEnable() != null && restLogProperties.getEnable()){
            registry.addInterceptor(logInterceptor()).addPathPatterns("/**")
                    .excludePathPatterns(excludePath);
        }
        // 自定义拦截器
        Map<String, HandlerInterceptor> map =applicationContextHelper.getBeansOfType(HandlerInterceptor.class);
        map.remove("logInterceptor");
        if(map == null || map.size()==0){
            return;
        }
        HandlerInterceptor[] handlerInterceptorArr=new HandlerInterceptor[map.size()];
        Collection<HandlerInterceptor> handlerInterceptors = map.values();
        int count=0;
        Iterator<HandlerInterceptor> iterator = handlerInterceptors.iterator();
        while (iterator.hasNext()){
            count++;
            HandlerInterceptor handlerInterceptor =iterator.next();
            InterceptorOrder interceptorOrder =handlerInterceptor.getClass().getAnnotation(InterceptorOrder.class);
            if(interceptorOrder == null){
                handlerInterceptorArr[handlerInterceptorArr.length-count]=handlerInterceptor;
            }else {
                if(handlerInterceptorArr[interceptorOrder.value()] != null){
                    throw new BaseException("自定义拦截器顺序值"+interceptorOrder.value()+"【InterceptorOrder("+interceptorOrder.value()+")】重复");
                }else {
                    handlerInterceptorArr[interceptorOrder.value()] = handlerInterceptor;
                }
            }
        }
        for(int i = 0; i<handlerInterceptorArr.length; i++){
            if(handlerInterceptorArr[i] != null){
                registry.addInterceptor(handlerInterceptorArr[i]).addPathPatterns("/**")
                        .excludePathPatterns(excludePath);
            }
        }
    }

    @Bean
    public LogInterceptor logInterceptor(){
        return new LogInterceptor();
    }

}
