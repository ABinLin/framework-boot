package com.farerboy.framework.boot.core.advice;

import com.alibaba.fastjson.JSON;
import com.farerboy.framework.boot.core.annotation.EncryptedResponse;
import com.farerboy.framework.boot.core.properties.ResponseEncryptedProperties;
import com.farerboy.framework.boot.util.encryption.AesUtil;
import com.farerboy.framework.boot.common.enums.ResponseCode;
import com.farerboy.framework.boot.common.exception.BaseException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import java.util.ArrayList;
import java.util.List;

/**
 * Response 对象数据返回加密处理
 * @author farerboy
 */
@RestControllerAdvice
public class ResponseEncryptionAdvice implements ResponseBodyAdvice {

    private static List<String> defaultUnencryptedUrls = new ArrayList<>();

    static {
        defaultUnencryptedUrls.add("health");
        defaultUnencryptedUrls.add("swagger-resources");
        defaultUnencryptedUrls.add("api-docs");
    }
    private Logger logger=LoggerFactory.getLogger(ResponseEncryptionAdvice.class);

    @Autowired
    private ResponseEncryptedProperties responseEncryptedProperties;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // false 时不处理
        EncryptedResponse encryptedResponse = returnType.getMethod().getAnnotation(EncryptedResponse.class);
        if(encryptedResponse != null){
            return encryptedResponse.value();
        }
        return responseEncryptedProperties.getEnable() == null ? false : responseEncryptedProperties.getEnable();
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        String url=((ServletServerHttpRequest) request).getServletRequest().getRequestURL().toString();
        if(isUnencryptedUrl(url)){
           return body;
        }
        if (body == null) {
            return null;
        }
        String bodyJsonString ;
        try {
            bodyJsonString = JSON.toJSONString(body);
        }catch (Exception e){
            // 返回数据非json，暂时不做处理
            return body;
        }
        if(StringUtils.isBlank(bodyJsonString)){
            return body;
        }
        if(StringUtils.isBlank(responseEncryptedProperties.getKey())){
            throw new BaseException(ResponseCode.RESPONSE_ENCRYPT_ERROR.getCode()
                    ,"Property farerboy.response.encrypted.key can not be null when need to encrypt response !");
        }
        String encodeBody = AesUtil.encryptToString(bodyJsonString,responseEncryptedProperties.getKey());
        return encodeBody;
    }

    private boolean isUnencryptedUrl(String url){
        if(StringUtils.isBlank(url)){
            return false;
        }
        // 默认内置不加密
        if(CollectionUtils.isNotEmpty(defaultUnencryptedUrls)){
            for(String s:defaultUnencryptedUrls){
                if(url.contains(s)){
                    return true;
                }
            }
        }
        // 自定义配置不加密
        if(StringUtils.isNotBlank(responseEncryptedProperties.getExclude())){
            String[] excludesUrls = responseEncryptedProperties.getExclude().split(",");
            for(String excludeUrl:excludesUrls){
                if(url.contains(excludeUrl)){
                    return true;
                }
            }
        }
        return false;
    }
}
