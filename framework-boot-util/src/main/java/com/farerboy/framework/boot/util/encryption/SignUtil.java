//package com.farerboy.framework.boot.util.encryption;
//
//import java.lang.reflect.Field;
//import java.net.URLEncoder;
//import java.util.*;
//
///**
// * 签名工具类
// * @author farerboy
// * @date 2020/12/29 9:23 下午
// * @param
// * @return
// */
//public class SignUtil {
//
//    /***********************与业务系统交互的签名工具**********************************/
//
//    public boolean validParamSign(Object o,String secretKey,String sign){
//        try {
//            return generateParamSign(o,secretKey).equals(sign);
//        }catch (Exception e){
//            return false;
//        }
//    }
//
//    public boolean validParamSignByMap(Map<String,String> map, String secretKey, String sign){
//        try {
//            return generateParamSignByMap(map,secretKey).equals(sign);
//        }catch (Exception e){
//            return false;
//        }
//    }
//
//    /**
//     * @Description 生成与业务系统交互的参数签名
//     * @Date 2019/7/26 0026 17:05
//     * @Author linjb
//     * @param o
//     * @param secretKey
//     * @return java.lang.String
//     */
//    public String generateParamSign(Object o,String secretKey){
//        StringBuffer sb=new StringBuffer();
//        sb.append(getSignTempString(o))
//                .append("secretKey=").append(secretKey);
//        String sign= Md5Util.getInstance().encode(sb.toString());
//        return sign;
//    }
//
//    /**
//     * @Description 生成与业务系统交互的参数签名
//     * @Date 2019/7/26 0026 19:44
//     * @Author linjb
//     * @param map
//     * @param secretKey
//     * @return java.lang.String
//     */
//    public String generateParamSignByMap(Map<String,String> map,String secretKey){
//        StringBuffer sb=new StringBuffer();
//        sb.append(getSignTempStringByMap(map))
//                .append("secretKey=").append(secretKey);
//        String sign= Md5Util.getInstance().encode(sb.toString());
//        return sign;
//    }
//
//
//
//    /***********************验证数据库记录签名工具**********************************/
//    /**
//     * @Description 验证数据库记录真实性
//     * @Date 2019/7/25 0025 19:24
//     * @Author linjb
//     * @param o
//     * @param sign
//     * @param encodeRules
//     * @return boolean
//     */
//    public boolean validateRecordSign(Object o,String sign,String encodeRules) {
//        if(sign==null){
//            return false;
//        }
//        try {
//            return sign.equals(generateRecordSign(o,encodeRules));
//        }catch (Exception e){
//            throw new RuntimeException("方法com.yiyu.core.utils.SignUtil.validateRecordSign中数据加密异常:",e);
//        }
//    }
//
//    /**
//     * @Description 生成数据库记录签名
//     * @Date 2019/7/25 0025 19:25
//     * @Author linjb
//     * @param
//     * @return java.lang.String
//     */
//    public String generateRecordSign(Object o,String encodeRules) {
//        return Md5Util.getInstance().encode(getSignTempString(o),encodeRules);
//    }
//
//
//    /***********************通用方法，生成待签名的字符串**********************************/
//
//
//
//
//}
//
