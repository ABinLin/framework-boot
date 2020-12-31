package com.farerboy.framework.boot.util.encryption;

import com.farerboy.framework.boot.util.AssertUtil;
import com.farerboy.framework.boot.util.ByteUtil;
import com.farerboy.framework.boot.util.encryption.exception.EncryptionException;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * MD5 加密
 * @author farerboy
 * @date 2020/12/29 7:46 下午
 */
public class Md5Util {
    public static final String MD5 = "MD5";
    public static final String HMACMD5 = "HMACMD5";

    private static String toHexString(byte[] byteArray) {
        AssertUtil.notEmpty(byteArray,"Md5 to hex string param byte array can not be null !");
        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            //0~F前面不零
            if ((byteArray[i] & 0xff) < 0x10){
                hexString.append("0");
            }
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return hexString.toString();
    }

    private static String encodeToString(byte[] encodeBytes,boolean upperCase){
        String md5 =  toHexString(encodeBytes);
        if(upperCase){
            md5 = md5.toUpperCase();
        }
        return md5;
    }

    public static String encodeToString(String content){
        return encodeToString(content,false);
    }

    public static String encodeToString(String content,boolean upperCase){
        byte[] encodeBytes = encode(content);
        return encodeToString(encodeBytes,upperCase);
    }

    public static String encodeToString(String content, String key){
        return encodeToString(content,key,false);
    }

    public static String encodeToString(String content, String key,boolean upperCase) {
        byte[] encodeBytes = encode(content,key);
        return encodeToString(encodeBytes,upperCase);
    }

    public static byte[] encode(byte[] contentBytes){
        MessageDigest md ;
        try {
            md = MessageDigest.getInstance(MD5);
        }catch (NoSuchAlgorithmException e){
            throw new EncryptionException("NoSuchAlgorithmException",e);
        }
        return md.digest(contentBytes);
    }

    public static byte[] encode(String content) {
        return encode(ByteUtil.getByte(content));
    }

    public static byte[] encode(byte[] contentBytes,String key){
        try {
            SecretKey sk = null;
            if (key == null) {
                KeyGenerator kg = KeyGenerator.getInstance(HMACMD5);
                sk = kg.generateKey();
            } else {
                byte[] keyBytes = ByteUtil.getByte(key);
                sk = new SecretKeySpec(keyBytes, HMACMD5);
            }
            Mac mac = Mac.getInstance(HMACMD5);
            mac.init(sk);
            byte[] result = mac.doFinal(contentBytes);
            return result;
        }catch (NoSuchAlgorithmException e){
            throw new EncryptionException("NoSuchAlgorithmException",e);
        }catch (InvalidKeyException e) {
            throw new EncryptionException("InvalidKeyException",e);
        }
    }

    public static byte[] encode(String content, String key) {
        return encode(ByteUtil.getByte(content),key);
    }

}
