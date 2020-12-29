package com.framework.boot.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import java.beans.Transient;
import java.io.Serializable;
import java.util.Map;

/**
 * 对象转换器
 * @author farerboy
 * @date 2020/12/26 3:43 下午
 */
public interface Transformation extends Serializable {

    /**
     * 转Map
     * @author farerboy
     * @date 2020/12/26 3:44 下午
     * @param
     * @return
     */
    @Transient
    @JSONField(serialize = false)
    default Map<String,Object> toMap() {
        return JSON.parseObject(JSON.toJSONString(this), Map.class);
    }

    /**
     * 转json对象
     * @author farerboy
     * @date 2020/12/26 3:44 下午
     * @param
     * @return
     */
    default JSONObject toJsonObject(){
        return JSON.parseObject(JSON.toJSONString(this));
    }

    /**
     * 转 json 字符串
     * @author farerboy
     * @date 2020/12/26 3:44 下午
     * @param
     * @return
     */
    default String toJsonString(){
        return JSON.toJSONString(this);
    }

}
