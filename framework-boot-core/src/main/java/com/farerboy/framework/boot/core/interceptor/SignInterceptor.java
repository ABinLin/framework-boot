package com.farerboy.framework.boot.core.interceptor;

import com.farerboy.framework.boot.util.encryption.AesUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import java.io.IOException;

/**
 * @ClassName SignInterceptor
 * @Description: 给RestTemplate请求加上请求头sign
 * @Author lyy
 * @Date 2019-12-27
 * @Version V1.0
 **/
public class SignInterceptor implements ClientHttpRequestInterceptor {

    private String key;
    private boolean sendSwitch;

    public SignInterceptor(String key, boolean sendSwitch){
        this.key = key;
        this.sendSwitch =  sendSwitch;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if(sendSwitch){
            HttpHeaders headers = request.getHeaders();
            Long timeStamp = System.currentTimeMillis()+2000;
            try {
                headers.add("sign", AesUtil.encryptCBC(timeStamp.toString(), key));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return execution.execute(request, body);
    }
}
