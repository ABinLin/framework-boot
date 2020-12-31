package com.farerboy.framework.boot.util;

import java.util.Collection;
import java.util.Map;

/**
 * 断言机制
 *
 * @author linjianbin
 * @date 2020/12/21 10:21 下午
 */
public class AssertUtil {

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Collection collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Object[] array, String message) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(byte[] byteArray,String message){
        if (byteArray == null || byteArray.length == 0){
            throw new IllegalArgumentException(message);
        }
    }
    public static void notEmpty(Map map, String message) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notBlank(String string, String message) {
        if (string == null || string.trim().equals("")) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 是正整数
     */
    public static void isPositiveInteger(Integer object, String message) {
        isTrue(object != null && object > 0, message);
    }

    /**
     * 是正整数
     */
    public static void isPositiveLong(Long object, String message) {
        isTrue(object != null && object > 0, message);
    }

}
