package com.farerboy.framework.boot.core.interceptor;

import com.farerboy.framework.boot.util.encryption.AesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import java.io.IOException;

/**
 * 给RestTemplate请求加上请求头sign
 * @author farerboy
 * @date 2021/1/5 9:05 下午
 * @param
 * @return
 */
public class SignInterceptor implements ClientHttpRequestInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private String key;
    private boolean enable;

    public SignInterceptor(boolean enable,String key){
        this.key = key;
        this.enable =  enable;
    }

    /**
     * 暂时只是时间戳签名
     * @author farerboy
     * @date 2021/1/5 7:59 下午
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if(enable){
            HttpHeaders headers = request.getHeaders();
            Long timeStamp = System.currentTimeMillis()+2000;
            try {
                headers.add("sign", AesUtil.encryptToString(timeStamp.toString(), key));
            } catch (Exception e) {
                logger.error("Http header add sign error:"+e.getMessage(),e);
            }
        }
        return execution.execute(request, body);
    }
}
