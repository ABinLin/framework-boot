package com.farerboy.framework.boot.util;

import com.farerboy.framework.boot.common.constant.Constant;
import com.farerboy.framework.boot.common.exception.BaseException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;

/**
 * url 相关工具类
 *
 * @author farerboy
 * @date 2020/12/30 3:24 下午
 */
public class UrlUtil {

    /**
     * 根据对象构造url参数拼接字符串
     * a=1&b=2
     * @author farerboy
     * @date 2020/12/30 9:21 下午
     * @param o
     * @return String
     */
    public static String buildUrlParams(Object o,boolean sort) {
        List<String> list = new ArrayList<>();
        Class cls = o.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            Object fieldObject = null;
            try {
                fieldObject = f.get(o);
            } catch (IllegalAccessException e) {
                throw new BaseException("IllegalAccessException",e);
            }
            if (fieldObject == null) {
                continue;
            }
            if(fieldObject instanceof Long || fieldObject instanceof Integer || fieldObject instanceof  Byte
                    || fieldObject instanceof Boolean || fieldObject instanceof Double || fieldObject instanceof Float
                    || fieldObject instanceof Short || fieldObject instanceof Character){
                list.add(f.getName() + "=" + fieldObject + "&");
            }else if(fieldObject instanceof String){
                String fieldObjectString = fieldObject.toString();
                if(fieldObjectString.length() > 0 && !"".equals(fieldObjectString.trim())){
                    list.add(f.getName() + "=" + fieldObject.toString().trim() + "&");
                }
            }else if(fieldObject instanceof Date){
                Date fieldObjectDate = (Date)fieldObject;
                list.add(f.getName() + "=" + fieldObjectDate.getTime() + "&");
            }else if(fieldObject instanceof Timestamp){
                Timestamp fieldObjectDate = (Timestamp)fieldObject;
                list.add(f.getName() + "=" + fieldObjectDate.getTime() + "&");
            }else if(fieldObject instanceof AbstractCollection){
                AbstractCollection abstractCollection = (AbstractCollection)fieldObject;
                if(abstractCollection.size() > 0){
                    list.add(f.getName() + "=" + abstractCollection.toString() + "&");
                }
            }else if(fieldObject instanceof AbstractMap){
                AbstractMap abstractMap = (AbstractMap)fieldObject;
                if(abstractMap.size() > 0){
                    list.add(f.getName() + "=" + abstractMap.toString() +  "&");
                }
            }else {
                continue;
            }
        }
        int size = list.size();
        if(size==0){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if(sort){
            String [] arrayToSort = list.toArray(new String[size]);
            Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
            for(int i = 0; i < size; i ++) {
                sb.append(arrayToSort[i]);
            }
        }else {
            for(int i = 0; i < size; i ++) {
                sb.append(list.get(i));
            }
        }
        sb.deleteCharAt(sb.length()-1);
        String result = sb.toString();
        return result;
    }

    /**
     * 根据Map构造url参数拼接字符串
     * @author farerboy
     * @date 2020/12/30 10:30 下午
     * @param
     * @return
     */
    public static String buildUrlParams(Map<String, String> map,boolean sort) {
        StringBuilder sb = new StringBuilder();
        List<String> keys = new ArrayList<>(map.keySet());
        if(sort){
            Collections.sort(keys);
        }
        for (String key : keys) {
            String value=map.get(key);
            if (null !=value && value.trim().length() > 0) {
                sb.append(key).append("=");
                sb.append(map.get(key));
                sb.append("&");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        String result = sb.toString();
        return result;
    }

    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, Constant.CHARSET_UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new BaseException("UnsupportedEncodingException",e);
        }
    }

}
