package com.farerboy.framework.boot.core.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.farerboy.framework.boot.core.log.Method;
import com.farerboy.framework.boot.core.log.pojo.ReqLog;
import com.farerboy.framework.boot.core.properties.ProjectProperties;
import com.farerboy.framework.boot.core.properties.SwaggerProperties;
import com.farerboy.framework.boot.core.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * 日志拦截器
 * @author farerboy
 */
public class LogInterceptor implements HandlerInterceptor {

    private Logger logger=LoggerFactory.getLogger(LogInterceptor.class);

    @Value("${spring.application.name:farerboy-application}")
    private String application;

    @Autowired
    private ProjectProperties projectProperties;

    @Autowired
    private SwaggerProperties swaggerProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String systemFlag = projectProperties.getName();
        if(StringUtils.isBlank(systemFlag)){
            systemFlag = application;
        }
        // 创建请求日志对象
        ReqLog reqLog=new ReqLog();
        reqLog.setSystemFlag(systemFlag);
        String reqLogId=UUID.randomUUID().toString().replaceAll("-", "");
        reqLog.setReqLogId(reqLogId);
        reqLog.setStartTime(new Timestamp(System.currentTimeMillis()));
        String requestMethod = request.getMethod();
        String requestUrl = request.getScheme() //当前链接使用的协议
                +"://" + request.getServerName()//服务器地址
                + ":" + request.getServerPort() //端口号
                + request.getContextPath() //应用名称，如果应用名称为
                + request.getServletPath(); //请求的相对url ;
        if(Method.GET.toString().equals(requestMethod)){
            if(!StringUtils.isEmpty(request.getQueryString())){
                requestUrl+= "?" + request.getQueryString(); //请求参数
            }
        }
        reqLog.setReqUrl(requestUrl);
        reqLog.setReqType(requestMethod);
        String remoteAddr = HttpUtil.getIPAddress(request);
        reqLog.setClientIp(remoteAddr);
        request.setAttribute("reqLog",reqLog);
        response.setHeader("reqLogId",reqLog.getReqLogId());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ReqLog reqLog=(ReqLog)request.getAttribute("reqLog");
        long start=reqLog.getStartTime().getTime();
        response.setHeader("useTime",reqLog.getUseTime());
        long end=System.currentTimeMillis();
        reqLog.setEndTime(new Timestamp(end));
        reqLog.setUseTime(end-start+"ms");
        if(reqLog.getReqFlag() == null){
            reqLog.setReqFlag(1);
        }
        if(1 == reqLog.getReqFlag()){
            logger.info(JSONObject.toJSONString(reqLog));
        }else {
            logger.error(JSONObject.toJSONString(reqLog));
        }
    }
}
