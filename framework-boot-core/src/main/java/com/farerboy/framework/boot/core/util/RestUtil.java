package com.farerboy.framework.boot.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @Description Rest 接口操作工具类
 * @Author farerboy
 * @Date 2019/7/2 0002 17:44
 * @Version 1.0
 */
public class RestUtil {

    private Logger logger=LoggerFactory.getLogger(getClass());

    @Autowired
    RestTemplate restTemplate;

    public <T> T doPos(String url,MultiValueMap<String, String> requestEntity,Class<T> responseType){
        return restTemplate.postForObject(url,requestEntity,responseType);
    }


}
