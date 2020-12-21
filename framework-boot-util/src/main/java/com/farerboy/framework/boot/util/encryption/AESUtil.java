package com.farerboy.framework.boot.util.encryption;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Description: AES 加密
 * @Author: linjb
 * @Date: 2019/6/4 0004 11:07
 */
public class AESUtil {
    public static final String AES = "AES";
    /**
     * 算法
     */
    private static final String ALGORITHMSTR_ECB = "AES/ECB/PKCS5Padding";

    private static final String ALGORITHMSTR_CBC = "AES/CBC/PKCS5Padding";

    private static final String IV = "898d7b5c5bff40fa";

    /**
     * base 64 encode
     *
     * @param bytes
     *            待编码的byte[]
     * @return 编码后的base 64 code
     */
    private static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }
    /**
     * base 64 decode
     *
     * @param base64Code
     * @return 解码后的byte[]
     * @throws Exception
     */
    private static byte[] base64Decode(String base64Code) throws Exception {
        return StringUtils.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }
    /**
     * AES加密
     *
     * @param content
     *            待加密的内容
     * @param encryptKey
     *            加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    private static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(AES);
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR_ECB);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), AES));
        return cipher.doFinal(content.getBytes("utf-8"));
    }

    private static byte[] aesEncryptToBytesCBC(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(AES);
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR_CBC);
        IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes("UTF-8"), AES), iv);
        return cipher.doFinal(content.getBytes("utf-8"));
    }
    /**
     * AES加密为base 64 code
     *
     * @param content
     *            待加密的内容
     * @param encryptKey
     *            加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String encrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }
    public static String encryptCBC(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytesCBC(content, encryptKey));
    }
    /**
     * AES解密
     * @param encryptBytes
     *            待解密的byte[]
     * @param decryptKey
     *            解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    private static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(AES);
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR_ECB);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), AES));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }
    private static String aesDecryptByBytesCBC(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(AES);
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR_CBC);
        IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes("UTF-8"), AES), iv);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }
    /**
     * 将base 64 code AES解密
     * @param encryptStr
     *            待解密的base 64 code
     * @param decryptKey
     *            解密密钥
     * @return 解密后的string
     * @throws Exception
     */
    public static String decrypt(String encryptStr, String decryptKey) throws Exception {
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }
    public static String decryptCBC(String encryptStr, String decryptKey) throws Exception {
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytesCBC(base64Decode(encryptStr), decryptKey);
    }

    public static void main(String[] args) throws Exception{
        String content = encryptCBC("1577339243612", "fyjysignkey12019");
        System.out.println(content);
        System.out.println(decryptCBC(content, "fyjysignkey12019"));

        System.out.println(decryptCBC("0v385G7OcVY4YNDYnymu9w==","fyjysignkey12019"));
    }
}
