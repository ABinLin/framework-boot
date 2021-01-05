package com.farerboy.framework.boot.util.encryption;

import com.farerboy.framework.boot.util.AssertUtil;
import com.farerboy.framework.boot.common.constant.Constant;
import com.farerboy.framework.boot.common.exception.BaseException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Base64加解密算法
 * @Author: farerboy
 * @Date: 2019/6/2 0002 16:41
 */
public class Base64Util {

    public static String encodeToString(String src){
		AssertUtil.notBlank(src,"Base64 encode src can not be blank !");
		try {
			return encodeToString(src.getBytes(Constant.CHARSET_UTF_8));
		} catch (UnsupportedEncodingException e) {
			throw new BaseException("Base64 encode error",e.getMessage(),e);
		}
	}

	public static String encodeToString(byte[] bytes){
		return Base64.getEncoder().encodeToString(bytes);
	}

	public static byte[] encode(String src){
		AssertUtil.notBlank(src,"Base64 encode src can not be blank !");
		try {
			return encode(src.getBytes(Constant.CHARSET_UTF_8));
		} catch (UnsupportedEncodingException e) {
			throw new BaseException("Base64 encode error",e.getMessage(),e);
		}
	}

	public static byte[] encode(byte[] bytes){
    	return Base64.getEncoder().encode(bytes);
	}


	public static String decodeToString(String decode){
		AssertUtil.notBlank(decode,"Base64 decode string can not be blank !");
		try {
			return new String(decode(decode),Constant.CHARSET_UTF_8);
		} catch (UnsupportedEncodingException e) {
			throw new BaseException("Base64 decode error",e.getMessage(),e);
		}
	}

	public static String decodeToString(byte[] decode){
		try {
			return new String(decode(decode),Constant.CHARSET_UTF_8);
		} catch (UnsupportedEncodingException e) {
			throw new BaseException("Base64 decode error",e.getMessage(),e);
		}
	}

	public static byte[] decode(String decode){
		AssertUtil.notBlank(decode,"Base64 decode string can not be blank !");
		return Base64.getDecoder().decode(decode);
	}

	public static byte[] decode(byte[] decode){
		return Base64.getDecoder().decode(decode);
	}

    
    public static void main(String[] args) throws Exception{
        System.out.println(encodeToString("123456"));
        System.out.println(decodeToString("MTIzNDU2"));
    }
}