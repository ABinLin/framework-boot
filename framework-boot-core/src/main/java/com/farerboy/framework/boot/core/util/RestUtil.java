package com.farerboy.framework.boot.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.framework.boot.common.dto.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

/**
 * @Description Rest 接口操作工具类
 * @Author linjb
 * @Date 2019/7/2 0002 17:44
 * @Version 1.0
 */
public class RestUtil {

    private Logger logger=LoggerFactory.getLogger(getClass());

    @Autowired
    RestTemplate restTemplate;

    /**
     * @Description 操作.net接口 get方法
     * @Date 2019/7/3 0003 11:03
     * @Author linjb
     * @param url
     * @return com.alibaba.fastjson.JSONObject
     */
    public JSONObject doGetJsonNet(String url){

        JSONObject resultJson=null;
        try {
            String resultStr= restTemplate.getForObject(url,String.class);
            resultStr=resultStr.substring(1,resultStr.length()-1).replace("\\","");
            resultJson=JSONObject.parseObject(resultStr);
        }catch (Exception e){
            logger.error("异常接口："+url,e);
        }
        return resultJson;
    }

    public <T> T doPos(String url,MultiValueMap<String, String> requestEntity,Class<T> responseType){
        return restTemplate.postForObject(url,requestEntity,responseType);
    }

    public <T> ServerResponse<List<T>> parseList(String body, Class<T> clazz){
        JSONObject jsonObject=JSON.parseObject(body);
        Integer status=jsonObject.getInteger("status");
        ServerResponse serverResponse = new ServerResponse(status);
        serverResponse.setMsg(jsonObject.getString("msg"));
        if(status != 200){
            return ServerResponse.createByErrorCodeMessage(status,jsonObject.getString("msg"));
        }
        JSONArray data=jsonObject.getJSONArray("data");
        if( data== null || data.size()==0){
            return ServerResponse.createBySuccess();
        }
        List<T> list=JSONArray.parseArray(data.toJSONString(),clazz);
        return ServerResponse.createBySuccess(list);
    }

    public <T> ServerResponse<T> parseObject(String body,Class<T> clazz){
        JSONObject jsonObject = JSONObject.parseObject(body);
        ServerResponse serverResponse = new ServerResponse(jsonObject.getInteger("status"));
        serverResponse.setMsg(jsonObject.getString("msg"));
        if(serverResponse.getStatus() != 200){
            return serverResponse;
        }
        if (clazz == String.class){
            serverResponse.setData(jsonObject.getString("data"));
            return serverResponse;
        }
        JSONObject data=jsonObject.getJSONObject("data");
        if( data== null ){
            return serverResponse;
        }
        T t=JSONObject.parseObject(data.toJSONString(),clazz);
        serverResponse.setData(t);
        return serverResponse;
    }

    public ServerResponse<Map<String,Object>> parseMap(String body){
        JSONObject jsonObject=JSON.parseObject(body);
        Integer status=jsonObject.getInteger("status");
        if(status != 200){
            return ServerResponse.createByErrorCodeMessage(status,jsonObject.getString("msg"));
        }
        ServerResponse<Map<String,Object>> serverResponse = new ServerResponse(status);
        serverResponse.setMsg(jsonObject.getString("msg"));
        JSONObject data=jsonObject.getJSONObject("data");
        if( data== null ){
            return serverResponse;
        }
        Map<String, Object> map = data;
        serverResponse.setData(map);
        return serverResponse;
    }
}
