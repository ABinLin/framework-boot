package com.farerboy.framework.boot.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.Map;

/**
 * 对象转换器
 *
 * @author linjianbin
 * @date 2020/12/26 4:20 下午
 */
public class TransformationUtil {

    public static Map<String,Object> objectToMap(Object O){
        return JSON.parseObject(JSON.toJSONString(O), Map.class);
    }

    public static JSONObject toJsonObject(Object o){
        return JSON.parseObject(JSON.toJSONString(o));
    }

}
