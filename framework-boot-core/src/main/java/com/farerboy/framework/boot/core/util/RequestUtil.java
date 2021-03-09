package com.farerboy.framework.boot.core.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * request 工具类
 *
 * @author linjianbin
 */
public class RequestUtil {

    private static Logger log = LoggerFactory.getLogger(ResponseUtil.class);

    public static String getHeader(String headerName){
        if(StringUtils.isBlank(headerName)){
            return null;
        }
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String header = (request.getHeader(headerName));
        log.info("Request[{}] header {}= {}", request.getRequestURI(),headerName,header);
        return header;
    }
}
