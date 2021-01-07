package com.farerboy.framework.boot.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import java.beans.Transient;
import java.io.Serializable;
import java.util.Map;

/**
 * 对象转换器
 * @author farerboy
 */
public interface Transformation extends Serializable {

    /**
     * 转Map
     * @author farerboy
     */
    @Transient
    @JSONField(serialize = false)
    default Map<String,Object> toMap() {
        return JSON.parseObject(JSON.toJSONString(this), Map.class);
    }

    /**
     * 转json对象
     * @author farerboy
     */
    default JSONObject toJsonObject(){
        return JSON.parseObject(JSON.toJSONString(this));
    }

    /**
     * 转 json 字符串
     * @author farerboy
     */
    default String toJsonString(){
        return JSON.toJSONString(this);
    }

}
