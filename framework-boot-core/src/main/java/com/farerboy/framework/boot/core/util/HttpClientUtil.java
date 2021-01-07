package com.farerboy.framework.boot.core.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 *
 * @author farerboy
 */
@Component
public class HttpClientUtil {

    private Logger logger=LoggerFactory.getLogger(getClass());

    /*public ServerResponse doPostForXml(String reqUrl, String xmlParamStr) throws Exception{
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        ServerResponse serverResponse = null;
        try {
            HttpPost httpPost = new HttpPost(reqUrl);
            StringEntity entityParams = new StringEntity(xmlParamStr,"utf-8");
            httpPost.setEntity(entityParams);
            httpPost.setHeader("Content-Type", "text/xml;charset=utf-8");
            client = HttpClients.createDefault();
            response = client.execute(httpPost);
            serverResponse = new ServerResponse(response.getStatusLine().getStatusCode());
            serverResponse.setData(EntityUtils.toByteArray(response.getEntity()));
        }finally {
            try {
                if(response != null){
                    response.close();
                    response=null;
                }
                if(client != null){
                    client.close();
                    client=null;
                }
            }finally {
                response=null;
                client=null;
            }
        }
        return serverResponse;

    }*/


    public Map<String,String> doPostForXML(String reqUrl,String xmlParamStr){
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        Map<String,String> resultMap =null;
        try {
            HttpPost httpPost = new HttpPost(reqUrl);
            StringEntity entityParams = new StringEntity(xmlParamStr,"utf-8");
            httpPost.setEntity(entityParams);
            httpPost.setHeader("Content-Type", "text/xml;charset=utf-8");
            client = HttpClients.createDefault();
            response = client.execute(httpPost);
            byte[] xmlResultByteArray = EntityUtils.toByteArray(response.getEntity());
            String xmlResult = new String(xmlResultByteArray);
            logger.debug("接口："+reqUrl+" || 请求入参："+xmlParamStr+" || 返回数据："+xmlResult);
            resultMap = XmlUtils.toMap(xmlResultByteArray, "utf-8");
        } catch (Exception e) {
            logger.error("调用第三方支付处理异常：",e);
        } finally {
            try {
                if(response != null){
                    response.close();
                    response=null;
                }
                if(client != null){
                    client.close();
                    client=null;
                }
            }catch (Exception e){
                logger.error("关闭response操作失败，原因：",e);
            }
        }
        return resultMap;

    }
}
