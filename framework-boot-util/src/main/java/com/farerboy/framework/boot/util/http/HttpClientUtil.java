//package com.farerboy.framework.boot.server.sdk.util;//package com.farerboy.framework.boot.core.util;
//
//import org.apache.http.Header;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Map;
//
///**
// *
// * @author farerboy
// */
//public class HttpClientUtil {
//
//    private Logger logger=LoggerFactory.getLogger(getClass());
//
//    public static void main(String[] args) throws IOException {
//        CloseableHttpResponse response = null;
//        CloseableHttpClient client = null;
////        HttpGet httpGet = new HttpGet("https://www.kuaidi100.com/query?type=shunfeng&postid=SF1339172949508&phone=0517&temp="+Math.random());
//        HttpGet httpGet = new HttpGet("https://www.kuaidi100.com/query?type=jtexpress&postid=JT5031271076511&phone=0517&temp="+Math.random());
//        httpGet.setHeader("Accept","*/*");
//        httpGet.setHeader("Accept-Encoding","gzip, deflate, br");
//        httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.9");
//        httpGet.setHeader("Cache-Control","no-cache");
//        httpGet.setHeader("Connection","keep-alive");
////        httpGet.setHeader("Cookie","__gads=ID=48a2c74ca2bcbd21-226a9f438dc4009a:T=1604476009:RT=1604476009:S=ALNI_MZQEZYiRaGz71z-hpfWpJOb3S6Qgw; Hm_lvt_22ea01af58ba2be0fec7c11b25e88e6c=1615786754; checkCode_JT5031271076511=0517; checkCode_SF1339172949508=0517; Hm_lpvt_22ea01af58ba2be0fec7c11b25e88e6c=1615787091");
//        httpGet.setHeader("Cookie","csrftoken=qHLN60vP5FwEf9qaDlq1gZ9wio6mqB_wXCaS4iaV1xY; WWWID=WWW4EB499BB341719963703B400BB3557F2; Hm_lvt_22ea01af58ba2be0fec7c11b25e88e6c=1615783499; checkCode_SF1339172949508=0517; checkCode_JT5031271076511=0517; Hm_lpvt_22ea01af58ba2be0fec7c11b25e88e6c=1615791116");
//        httpGet.setHeader("Host","cdn.kuaidi100.com");
//        httpGet.setHeader("Pragma","no-cache");
//        httpGet.setHeader("Referer","https://www.kuaidi100.com/");
//        httpGet.setHeader("sec-ch-ua","Google Chrome\";v=\"89\", \"Chromium\";v=\"89\", \";Not A Brand\";v=\"99\"");
//        httpGet.setHeader("sec-ch-ua-mobile","?0");
//        httpGet.setHeader("Sec-Fetch-Dest","script");
//        httpGet.setHeader("Sec-Fetch-Mode","no-cors");
//        httpGet.setHeader("Sec-Fetch-Site","same-site");
//        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36");
//        client = HttpClients.createDefault();
//        response = client.execute(httpGet);
//        byte[] xmlResultByteArray = EntityUtils.toByteArray(response.getEntity());
//        String xmlResult = new String(xmlResultByteArray);
//        System.out.println(xmlResult);
//    }
//
//    /*public ServerResponse doPostForXml(String reqUrl, String xmlParamStr) throws Exception{
//        CloseableHttpResponse response = null;
//        CloseableHttpClient client = null;
//        ServerResponse serverResponse = null;
//        try {
//            HttpPost httpPost = new HttpPost(reqUrl);
//            StringEntity entityParams = new StringEntity(xmlParamStr,"utf-8");
//            httpPost.setEntity(entityParams);
//            httpPost.setHeader("Content-Type", "text/xml;charset=utf-8");
//            client = HttpClients.createDefault();
//            response = client.execute(httpPost);
//            serverResponse = new ServerResponse(response.getStatusLine().getStatusCode());
//            serverResponse.setData(EntityUtils.toByteArray(response.getEntity()));
//        }finally {
//            try {
//                if(response != null){
//                    response.close();
//                    response=null;
//                }
//                if(client != null){
//                    client.close();
//                    client=null;
//                }
//            }finally {
//                response=null;
//                client=null;
//            }
//        }
//        return serverResponse;
//
//    }*/
//
////
////    public Map<String,String> doPostForXML(String reqUrl,String xmlParamStr){
////        CloseableHttpResponse response = null;
////        CloseableHttpClient client = null;
////        Map<String,String> resultMap =null;
////        try {
////            HttpPost httpPost = new HttpPost(reqUrl);
////            StringEntity entityParams = new StringEntity(xmlParamStr,"utf-8");
////            httpPost.setEntity(entityParams);
////            httpPost.setHeader("Content-Type", "text/xml;charset=utf-8");
////            client = HttpClients.createDefault();
////            response = client.execute(httpPost);
////            byte[] xmlResultByteArray = EntityUtils.toByteArray(response.getEntity());
////            String xmlResult = new String(xmlResultByteArray);
////            logger.debug("接口："+reqUrl+" || 请求入参："+xmlParamStr+" || 返回数据："+xmlResult);
////            resultMap = XmlUtils.toMap(xmlResultByteArray, "utf-8");
////        } catch (Exception e) {
////            logger.error("调用第三方支付处理异常：",e);
////        } finally {
////            try {
////                if(response != null){
////                    response.close();
////                    response=null;
////                }
////                if(client != null){
////                    client.close();
////                    client=null;
////                }
////            }catch (Exception e){
////                logger.error("关闭response操作失败，原因：",e);
////            }
////        }
////        return resultMap;
////
////    }
//}
