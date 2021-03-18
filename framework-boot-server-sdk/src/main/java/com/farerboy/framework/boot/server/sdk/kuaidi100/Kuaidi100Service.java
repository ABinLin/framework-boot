package com.farerboy.framework.boot.server.sdk.kuaidi100;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.farerboy.framework.boot.common.exception.BaseException;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import java.util.UUID;

/**
 * TODO description
 * 2021/3/18 3:10 下午
 *
 * @author linjianbin
 */
@Component
public class Kuaidi100Service {

    private static final String ERROR_CODE = "KUAIDI100_ERROR";

    private String queryUrl = "https://www.kuaidi100.com/query?";

    private String autonumberUrl = "https://www.kuaidi100.com/autonumber/autoComNum?text=";

    private String host = "cdn.kuaidi100.com";

    private String referer = "https://www.kuaidi100.com/?from=openv";

    public JSONObject query(String com,String code,String phone){
        if(code == null || "".equals(code.trim())){
            return null;
        }
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        String url = new StringBuilder("https://www.kuaidi100.com/query?type=").append(com).append("&postid=").append(code).append("&phone=").append(phone).append("&temp=").append(Math.random()).toString();
        HttpGet httpGet = new HttpGet(url);
        setHeader(httpGet);
        JSONObject jsonObject = doExecuteForJson(httpGet);
        return jsonObject;
    }

    public JSONObject autoComNum(String code) {
        if(code == null || "".equals(code.trim())){
            return null;
        }
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        HttpPost httpPost = new HttpPost(autonumberUrl+code);
        setHeader(httpPost);
        JSONObject jsonObject = doExecuteForJson(httpPost);
        return jsonObject;
    }

    private JSONObject doExecuteForJson(HttpUriRequest request){
        CloseableHttpResponse response = null;
        JSONObject jsonObject = null;
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            response = client.execute(request);
            byte[] responseByte = EntityUtils.toByteArray(response.getEntity());
            jsonObject = JSON.parseObject(new String(responseByte));
        }catch (Exception e){
            throw new BaseException(ERROR_CODE,request.getURI().getQuery(),e);
        }
        return jsonObject;
    }


    private void setHeader(HttpRequestBase httpRequestBase){
        httpRequestBase.setHeader("Accept","*/*");
        httpRequestBase.setHeader("Accept-Encoding","gzip, deflate, br");
        httpRequestBase.setHeader("Accept-Language","zh-CN,zh;q=0.9");
        httpRequestBase.setHeader("Cache-Control","no-cache");
        httpRequestBase.setHeader("Connection","keep-alive");
        String token = UUID.randomUUID().toString().replaceAll("-","");
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        long now = System.currentTimeMillis();
        String start = (now+"").substring(0,11);
        String end = ((now+1000*60*60*2)+"").substring(0,11);
        String cookie = new StringBuilder("csrftoken=").append(token).append("; Hm_lvt_").append(uuid).append("=").append(start).append("; Hm_lpvt_").append(uuid).append("=").append(end).toString();
        httpRequestBase.setHeader("Cookie",cookie);
        httpRequestBase.setHeader("Host",host);
        httpRequestBase.setHeader("Pragma","no-cache");
        httpRequestBase.setHeader("Referer",referer);
        httpRequestBase.setHeader("sec-ch-ua","Google Chrome\";v=\"89\", \"Chromium\";v=\"89\", \";Not A Brand\";v=\"99\"");
        httpRequestBase.setHeader("sec-ch-ua-mobile","?0");
        httpRequestBase.setHeader("Sec-Fetch-Dest","script");
        httpRequestBase.setHeader("Sec-Fetch-Mode","no-cors");
        httpRequestBase.setHeader("Sec-Fetch-Site","same-site");
        httpRequestBase.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36");
    }
}
