package com.farerboy.framework.boot.core.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description 预定义常量加载处理器
 * @Author linjb
 * @Date 2019/7/2 0002 15:01
 * @Version 1.0
 */
@Component
@Order(value = 1)
public class ConstantHandle implements ApplicationRunner {

    // 返回数据强制不加密的路径配置
    @Value("${yiyu.response.url.noEnc:swagger-resources,api-docs}")
    private String response_url_noEnc;
    // 返回数据强制不做格式统一处理的路径
    @Value("${yiyu.response.url.noFormat:swagger-resources,api-docs}")
    private String response_url_noFormat;

    private static Set<String> responseUnencryptedUrls;

    private static Set<String> responseUnFormatUrls;

    static {
        responseUnencryptedUrls = new HashSet<>();
        responseUnencryptedUrls.add("swagger-resources");
        responseUnencryptedUrls.add("api-docs");
        responseUnFormatUrls = new HashSet<>();
        responseUnFormatUrls.add("swagger-resources");
        responseUnFormatUrls.add("api-docs");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        responseUnencryptedUrls.addAll(Arrays.asList(response_url_noEnc.split(",")));
        responseUnFormatUrls.addAll(Arrays.asList(response_url_noFormat.split(",")));
    }

    /**
     * @Description 判断该路径返回值是否需要加密
     * @Date 2019/7/2 0002 15:57
     * @Author linjb
     * @param url
     * @return boolean
     */
    public static boolean hasResponseUnencrypted(String url){
        return has(responseUnencryptedUrls,url);
    }

    /**
     * @Description 判断该路径返回值是否需要统一格式化
     * @Date 2019/10/17 0017 20:36
     * @Author linjb
     * @param url
     * @return boolean
     */
    public static boolean hasResponseFormat(String url){
        return has(responseUnFormatUrls,url);
    }

    private static boolean has(Set<String> urlList,String url){
        if(null == url || null == urlList || urlList.size()==0){
            return false;
        }
        for(String s:urlList){
            if(url.contains(s)){
                return true;
            }
        }
        return false;
    }


}
