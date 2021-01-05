package com.farerboy.framework.boot.core.configuration;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.farerboy.framework.boot.core.helper.spring.ApplicationContextHelper;
import com.farerboy.framework.boot.core.interceptor.InterceptorOrder;
import com.farerboy.framework.boot.core.interceptor.LogInterceptor;
import com.farerboy.framework.boot.core.properties.RestLogProperties;
import com.framework.boot.common.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.annotation.Resource;
import java.util.*;

/**
 * web mvc 配置
 *
 * @author farerboy
 * @date 2020/12/22 11:26 上午
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


    /**
     * 序列化
     * @author farerboy
     * @date 2021/1/5 8:39 下午
     */
    @Bean
    public HttpMessageConverters useConverters() {
        // 1.需要定义一个convert转换消息的对象;
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        // 2:添加fastJson的配置信息;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
        // 3处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        // 4.在convert中添加配置信息.
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;
        return new HttpMessageConverters(converter);
    }

    @ConditionalOnExpression("${farerboy.rest.api.cross-enable:false}")
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 设置允许跨域请求的域名
        config.addAllowedOrigin("*");
        // 是否允许证书 不再默认开启
        // config.setAllowCredentials(true);
        // 设置允许的方法
        config.addAllowedMethod("*");
        // 允许任何头
        config.addAllowedHeader("*");
        //预检请求头信息描述
        //config.addExposedHeader("token");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }
}
