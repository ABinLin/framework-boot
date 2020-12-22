package com.farerboy.framework.boot.core.interceptor;

import com.farerboy.framework.boot.core.annotation.OpenRouting;
import com.framework.boot.common.enums.ResponseCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description token 权限抽象拦截器
 * @Author linjb
 * @Date 2019/10/27 0027 16:15
 * @Version 1.0
 */
public abstract class AbstractTokenInterceptor implements HandlerInterceptor {

    @Value("${server.servlet.context-path}")
    private String projectPath;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        OpenRouting openRouting;
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }else {
            openRouting = ((HandlerMethod) handler).getMethodAnnotation(OpenRouting.class);
        }
        // 判断是否是公开路由
        if (openRouting != null) {
            return true;
        }
        String wxMiniProgram = request.getHeader("wxMiniProgramToken");
        if( null != wxMiniProgram ){
            return doHandle(request,response,handler);
        }
        String token = request.getHeader("token");
        if( null == token || "".equals(token) ){
            response.setStatus(ResponseCode.InvalidToken.getStatus());
            return false;
        }
        //公共
        return doHandle(request,response,handler);
    }

    public abstract boolean doHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

}
