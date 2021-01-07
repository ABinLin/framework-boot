package com.farerboy.framework.boot.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.farerboy.framework.boot.common.dto.ServerResponse;
import java.util.List;
import java.util.Map;

/**
 *
 * @author farerboy
 */
public class ServerResponseUtil {

    private <T> ServerResponse<T> buildServerResponse(JSONObject bodyJson,T data){
        ServerResponse<T> serverResponse = ServerResponse.createBy(bodyJson.getBooleanValue("success"),
                bodyJson.getString("code"), bodyJson.getString("msg"), data);
        serverResponse.setHeaders(JSON.parseObject(bodyJson.getString("headers"), Map.class));
        return serverResponse;
    }
    public <T> ServerResponse<List<T>> parseList(String body, Class<T> clazz){
        JSONObject bodyJson= JSON.parseObject(body);
        JSONArray dataJson=bodyJson.getJSONArray("data");
        List<T> data = null;
        if( dataJson != null && dataJson.size() > 0){
            data=JSONArray.parseArray(dataJson.toJSONString(),clazz);
        }
        return buildServerResponse(bodyJson,data);
    }

    public <T> ServerResponse<T> parseObject(String body,Class<T> clazz){
        JSONObject bodyJson = JSONObject.parseObject(body);
        JSONObject dataJson=bodyJson.getJSONObject("data");
        T t = null;
        if( dataJson != null && dataJson.size() > 0){
            t=JSONObject.parseObject(dataJson.toJSONString(),clazz);
        }
        return buildServerResponse(bodyJson,t);
    }

    public ServerResponse<Map<String,Object>> parseMap(String body){
        JSONObject bodyJson=JSON.parseObject(body);
        JSONObject dataJson=bodyJson.getJSONObject("data");
        Map<String, Object> map = null;
        if( dataJson != null && dataJson.size() > 0){
            map=JSON.parseObject(dataJson.toJSONString(), Map.class);
        }
        return buildServerResponse(bodyJson,map);
    }

}
