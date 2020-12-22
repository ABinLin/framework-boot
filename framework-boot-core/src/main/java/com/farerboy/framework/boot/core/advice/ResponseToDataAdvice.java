package com.farerboy.framework.boot.core.advice;

import com.alibaba.fastjson.JSONObject;
import com.farerboy.framework.boot.core.annotation.NotFormatResponse;
import com.farerboy.framework.boot.core.annotation.UnencryptedResponse;
import com.farerboy.framework.boot.core.handler.ConstantHandle;
import com.farerboy.framework.boot.util.encryption.AESUtil;
import com.framework.boot.common.dto.ServerResponse;
import com.framework.boot.common.enums.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;

/**
 * @Description: Response 对象数据返回强制统一格式
 * @Author: linjb
 * @Date: 2019/6/2 0002 17:24
 */
@ControllerAdvice
public class ResponseToDataAdvice implements ResponseBodyAdvice {

    private Logger logger=LoggerFactory.getLogger(ResponseToDataAdvice.class);

    // controller层返回数据对称加密密钥，长度必须是16
    @Value("${yiyu.key.data.transmission:qwertyuiopasdfgh}")
    private String yiy_key_data_transmission;

    // 返回数据是否加密
    @Value("${yiyu.response.enc:false}")
    private boolean yiyu_response_enc;

    // Response 对象数据返回强制统一格式
    @Value("${yiyu.response.format:true}")
    private boolean yiyu_response_format;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // false 时不处理 ，增加 @NotResponseAnnotation  或者注释该注解  @ControllerAdvice
        Annotation notResponseAnnotation = returnType.getMethod().getAnnotation(NotFormatResponse.class);
        if(notResponseAnnotation != null){
            return false;
        }
        return yiyu_response_format;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        String url=((ServletServerHttpRequest) request).getServletRequest().getRequestURL().toString();
        // 路径在不格式化置中
        if(ConstantHandle.hasResponseFormat(url)){
            return body;
        }
        if (body == null) {
            body= ServerResponse.createBySuccessMessage(ResponseCode.Success.getMsg());
        }
        JSONObject json=null;
        // body 已经是 ServerResponse 则只需要加密
        if (body instanceof ServerResponse) {
            json=(JSONObject)JSONObject.toJSON(body);
        }
        if(null == json){
            Object o;
            try {
                o = JSONObject.toJSON(body);
            }catch (Exception e){
                // 返回数据非json，暂时不做处理
                return body;
            }
            o=ServerResponse.createBySuccess(o);
            json=(JSONObject) JSONObject.toJSON(o);
        }
        // 不加密
        if(!yiyu_response_enc){
            return json;
        }
        // 路径在不加密配置中
        if(ConstantHandle.hasResponseUnencrypted(url)){
            return json;
        }
        // 是否有强制不加密注解
        Annotation unencryptedResponse = returnType.getMethod().getAnnotation(UnencryptedResponse.class);
        if(unencryptedResponse != null){
            return json;
        }
        String jsonEncoded;
        try{
            jsonEncoded= AESUtil.encrypt(json.toString(),yiy_key_data_transmission);
        }catch (Exception e){
            logger.error("数据无法加密："+json.toString(),e);
            return json;
        }
        if(jsonEncoded == null ){
            return json;
        }
        return jsonEncoded;
    }
}
