package com.farerboy.framework.boot.core.helper;

import com.farerboy.framework.boot.core.util.HttpUtil;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author linjianbin
 */
public class RequestHelper {


    public static String getRequestIp(){
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return HttpUtil.getIPAddress(request);
    }

    public static String getRequestURL(HttpServletRequest request) {
        StringBuffer url = new StringBuffer();
        String scheme = request.getScheme();
        int port = request.getServerPort();
        if (port < 0) {
            port = 80;
        }

        url.append(scheme);
        url.append("://");
        url.append(request.getServerName());
        if (scheme.equals("http") && port != 80 || scheme.equals("https") && port != 443) {
            url.append(':');
            url.append(port);
        }

        url.append(request.getRequestURI());
        return url.toString();
    }

//    private static String PROJECT_ID = "project-id";
//
//    public static Long getHeaderProjectId(){
//        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
//        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//        String projectId = (request.getHeader(PROJECT_ID));
//        log.info("Request[{}] header project-id= {}", request.getRequestURI(),projectId);
//        if (StringUtils.isBlank(projectId)) {
//            return null;
//        }
//        return Long.valueOf(projectId);
//    }
}
