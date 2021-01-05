package com.farerboy.framework.boot.core.interceptor;

import com.farerboy.framework.boot.core.annotation.OpenRouting;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token 权限抽象拦截器
 * @author farerboy
 * @date 2020/12/29 7:40 下午
 */
public abstract class AbstractTokenInterceptor implements HandlerInterceptor {

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
        //公共
        return doHandle(request,response,handler);
    }

    /**
     * 执行token拦截器具体业务
     * @author farerboy
     * @date 2021/1/5 8:49 下午
     * @param request
     * @param response
     * @param handler
     * @return boolean
     */
    public abstract boolean doHandle(HttpServletRequest request, HttpServletResponse response, Object handler);

}
