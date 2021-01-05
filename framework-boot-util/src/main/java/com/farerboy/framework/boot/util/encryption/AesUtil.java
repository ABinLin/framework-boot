package com.farerboy.framework.boot.util.encryption;

import com.farerboy.framework.boot.util.AssertUtil;
import com.farerboy.framework.boot.util.encryption.enums.AesModeEnum;
import com.farerboy.framework.boot.util.encryption.enums.AesPaddingEnum;
import com.farerboy.framework.boot.common.constant.Constant;
import com.farerboy.framework.boot.common.exception.BaseException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * AES 加密工具类
 * @author farerboy
 * @date 2020/12/29 7:41 下午
 */
public class AesUtil {
    public static final String AES = "AES";

    /**
     * byte[] 补全 16 位
     * @author farerboy
     * @date 2020/12/28 3:10 下午
     * @param key
     * @return byte[]
     */
    private static byte[] completionBytes16(String key) throws UnsupportedEncodingException {
        byte[] keyBytes = key.getBytes(Constant.CHARSET_UTF_8);
        int base = 16;
        if (keyBytes.length % base != 0) {
            int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
            keyBytes = temp;
        }
        return keyBytes;
    }

    private static Cipher getCipherInstance(AesModeEnum aesModeEnum, AesPaddingEnum aesPaddingEnum, boolean isEncrypt, String iv, String key) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException {
        if(AesModeEnum.CBC.getMode().equals(aesModeEnum.getMode())){
            AssertUtil.notBlank(iv,"IV offset can not be null in CBC mode !");
        }
        String algorithm = "%s/%s/%s";
        algorithm = String.format(algorithm,AES,aesModeEnum.getMode(),aesPaddingEnum.getPadding());
        Cipher cipher = Cipher.getInstance(algorithm);
        int i ;
        if(isEncrypt){
            i = Cipher.ENCRYPT_MODE;
        }else {
            i = Cipher.DECRYPT_MODE;
        }
        if(AesModeEnum.CBC.getMode().equals(aesModeEnum.getMode())){
            IvParameterSpec ivParameterSpec = new IvParameterSpec(completionBytes16(iv));
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(completionBytes16(key), AES), ivParameterSpec);
        }else {
            cipher.init(i, new SecretKeySpec(completionBytes16(key), AES));
        }
        return cipher;
    }

    private static Cipher defaultGetCipherInstance(boolean isEncrypt,String key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException {
        return getCipherInstance(AesModeEnum.ECB,AesPaddingEnum.PKCS_5_PADDING,isEncrypt,null,key);
    }

    public static byte[] encrypt(byte[] content, String encryptKey){
        try {
            Cipher cipher = defaultGetCipherInstance(true,encryptKey);
            return cipher.doFinal(content);
        } catch (Exception e) {
            throw new BaseException("AES encrypt error:"+e.getMessage(),e);
        }
    }

    public static byte[] encrypt(String content, String encryptKey){
        try {
            return encrypt(content.getBytes(Constant.CHARSET_UTF_8),encryptKey);
        } catch (Exception e) {
            throw new BaseException("AES encrypt error:"+e.getMessage(),e);
        }
    }

    public static String encryptToString(String content, String encryptKey){
        AssertUtil.notBlank(content,"AES ECB encrypt string can not be null !");
        AssertUtil.notBlank(encryptKey,"AES ECB encrypt key can not be null !");
        return Base64Util.encodeToString(encrypt(content, encryptKey));
    }

    public static byte[] decrypt(byte[] encryptBytes, String decryptKey) {
        try {
            Cipher cipher = defaultGetCipherInstance(false,decryptKey);
            byte[] decryptBytes = cipher.doFinal(encryptBytes);
            return decryptBytes;
        }catch (Exception e){
            throw new BaseException("AES decrypt error:"+e.getMessage(),e);
        }
    }

    public static byte[] decrypt(String encryptStr, String decryptKey){
        return decrypt(Base64Util.decode(encryptStr),decryptKey);
    }

    public static String decryptToString(String encryptStr, String decryptKey) {
        AssertUtil.notBlank(encryptStr,"AES decrypt string can not be null !");
        AssertUtil.notBlank(decryptKey,"AES decrypt key can not be null !");
        try {
            return new String(decrypt(Base64Util.decode(encryptStr), decryptKey),Constant.CHARSET_UTF_8);
        }catch (Exception e){
            throw new BaseException("AES decrypt error:"+e.getMessage(),e);
        }
    }


    public static byte[] doAes(String content,String key,AesModeEnum aesModeEnum,AesPaddingEnum aesPaddingEnum,boolean isEncrypt,String iv){
        AssertUtil.notBlank(content,"AES operate content can not be null !");
        AssertUtil.notBlank(key,"AES operate key can not be null !");
        if(aesModeEnum == null){
            aesModeEnum = AesModeEnum.ECB;
        }
        if (aesPaddingEnum == null){
            aesPaddingEnum = AesPaddingEnum.PKCS_5_PADDING;
        }
        if(AesModeEnum.CBC.getMode().equals(aesModeEnum.getMode())){
            AssertUtil.notBlank(iv,"IV offset can not be null in CBC mode !");
        }
        try {
            Cipher cipher = getCipherInstance(aesModeEnum,aesPaddingEnum,isEncrypt,iv,key);
            if(isEncrypt){
                return cipher.doFinal(content.getBytes(Constant.CHARSET_UTF_8));
            }else {
                return cipher.doFinal(Base64Util.decode(content));
            }
        }catch (Exception e){
            throw new BaseException("AES operate error:"+e.getMessage(),e);
        }
    }

    public static String doAesToString(String content,String key,AesModeEnum aesModeEnum,AesPaddingEnum aesPaddingEnum,boolean isEncrypt,String iv){
        byte[] bytes = doAes(content,key,aesModeEnum,aesPaddingEnum,isEncrypt,iv);
        if(isEncrypt){
            return Base64Util.encodeToString(bytes);
        }else {
            try {
                return new String(bytes,Constant.CHARSET_UTF_8);
            }catch (Exception e){
                throw new BaseException("Do aes byte array to string operate error:"+e.getMessage(),e);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        System.out.println(encryptToString("你好，我真的好想你","123456"));
    }



}
