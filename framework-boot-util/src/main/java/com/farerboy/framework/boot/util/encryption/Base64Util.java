package com.farerboy.framework.boot.util.encryption;

import com.farerboy.framework.boot.util.AssertUtil;
import com.framework.boot.common.constant.Constant;
import com.framework.boot.common.exception.BaseException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Base64加解密算法
 * @Author: linjianbin
 * @Date: 2019/6/2 0002 16:41
 */
public class Base64Util {

    public static String encode(String src){
		AssertUtil.notBlank(src,"Base64 encode string can not be blank !");
		try {
			return Base64.getEncoder().encodeToString(src.getBytes(Constant.CHARSET_UTF_8));
		} catch (UnsupportedEncodingException e) {
			throw new BaseException("Base64 encode error",e.getMessage(),e);
		}
	}

	public static String decode(String decode){
		AssertUtil.notBlank(decode,"Base64 decode string can not be blank !");
		try {
			return new String(Base64.getDecoder().decode(decode),Constant.CHARSET_UTF_8);
		} catch (UnsupportedEncodingException e) {
			throw new BaseException("Base64 decode error",e.getMessage(),e);
		}
	}

    
    public static void main(String[] args) throws Exception{
        System.out.println(encode("123456"));
        System.out.println(decode("MTIzNDU2"));
    }
}