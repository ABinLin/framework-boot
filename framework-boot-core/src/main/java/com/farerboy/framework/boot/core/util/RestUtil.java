package com.farerboy.framework.boot.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Rest 接口操作工具类
 * @author farerboy
 */
public class RestUtil {

    private Logger logger=LoggerFactory.getLogger(getClass());

    @Autowired
    RestTemplate restTemplate;

    public <T> T doPos(String url,MultiValueMap<String, String> requestEntity,Class<T> responseType){
        return restTemplate.postForObject(url,requestEntity,responseType);
    }


}
