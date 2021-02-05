package com.farerboy.framework.boot.orm.helper;

import org.apache.commons.collections.MapUtils;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author linjianbin
 */
public class DefaultColumnHelper {

    private static Map<String,Object> defaultColumn = null;

    public static boolean contains(String column){
        if(MapUtils.isEmpty(defaultColumn)){
            return false;
        }
        return defaultColumn.containsKey(column);
    }

    public static Object getDefaultColumn(String column){
        if(MapUtils.isEmpty(defaultColumn)){
            return null;
        }
        return defaultColumn.get(column);
    }

    public static void setDefaultColumn(Map<String,Object> map){
        defaultColumn = map;
    }
}
