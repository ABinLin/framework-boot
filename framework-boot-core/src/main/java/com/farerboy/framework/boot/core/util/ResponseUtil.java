package com.farerboy.framework.boot.core.util;

import com.alibaba.fastjson.JSON;
import com.farerboy.framework.boot.common.dto.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 基于 HttpServletResponse 的工具类
 *
 * @author linjianbin
 */
public class ResponseUtil {

    private static Logger log = LoggerFactory.getLogger(ResponseUtil.class);
    /**
     * 将对象转换成JSON字符串，并响应回前台
     *
     */
    public static void writeJson(HttpServletResponse response, ServerResponse object) {
        try {
            if(response == null || object == null){
                return;
            }
            String json = JSON.toJSONString(object);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(json);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            log.error("Response util write response json error:"+e.getMessage(),e);
        }
    }
}
