package com.farerboy.framework.boot.util.encryption;

/**
 * @Description:
 * @Author: linjb
 * @Date: 2019/6/2 0002 16:42
 */

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

public class MD5Util {
    public static final String MD5 = "MD5";
    public static final String HmacMD5 = "HmacMD5";
    public static final String charset = "UTF-8"; // 编码格式；默认null为GBK

    private static MD5Util instance;

    private MD5Util() {
    }

    public static MD5Util getInstance() {
        if (instance == null) {
            synchronized (MD5Util.class) {
                if (instance == null) {
                    instance = new MD5Util();
                }
            }
        }
        return instance;
    }

    private static String toHexString(byte[] byteArray) {
        if (byteArray == null || byteArray.length < 1)
            throw new IllegalArgumentException("this byteArray must not be null or empty");

        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if ((byteArray[i] & 0xff) < 0x10)//0~F前面不零
                hexString.append("0");
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return hexString.toString();
    }

    /**
     * 使用 MD5 方法加密（无密码） 默认转大写
     */
    public String encode(String res){
        return encode(res,true);
    }

    /**
     * @Description 加密
     * @Date 2019/10/28 0028 16:47
     * @Author linjb
     * @param res
     * @param upperCase
     * @return java.lang.String
     */
    public String encode(String res,boolean upperCase){
        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            byte[] resBytes = charset == null ? res.getBytes() : res.getBytes(charset);
            String md5 =  toHexString(md.digest(resBytes));
            if(upperCase){
                md5 = md5.toUpperCase();
            }
            return md5;
        }catch (Exception e){
            throw new RuntimeException("md5加密异常",e);
        }
    }

    /**
     * 使用 MD5 方法加密（可以设密码）
     */
    public String encode(String res, String key) {
        return encode(res,key,true);
    }

    public String encode(String res, String key, boolean upperCase) {
        try {
            SecretKey sk = null;
            if (key == null) {
                KeyGenerator kg = KeyGenerator.getInstance(HmacMD5);
                sk = kg.generateKey();
            } else {
                byte[] keyBytes = charset == null ? key.getBytes() : key.getBytes(charset);
                sk = new SecretKeySpec(keyBytes, HmacMD5);
            }
            Mac mac = Mac.getInstance(HmacMD5);
            mac.init(sk);
            byte[] resBytes = charset == null ? res.getBytes() : res.getBytes(charset);
            byte[] result = mac.doFinal(resBytes);
            String md5 = toHexString(result);
            if(upperCase){
                md5 = md5.toUpperCase();
            }
            return md5;
        }catch (Exception e){
            throw new RuntimeException("md5加密异常",e);
        }
    }

    public static void main(String[] args) throws Exception{
    	System.out.println(MD5Util.getInstance().encode("createTime=Sat Jul 27 22:26:51 CST 2019&orderCode=112&ordersId=383bcbdc1510480598b34f1d178b6e9f&orderSourceId=001&payId=414722952f4344a5ac9e63c325d5cc97&payMoney=1&payStatus=0&payType=2&payUrl=weixin://wxpay/bizpayurl?pr=EJ45zGv&","pay123456"));
    }
}
