package com.farerboy.framework.boot.util;

import com.farerboy.framework.boot.common.constant.Constant;
import com.farerboy.framework.boot.common.exception.BaseException;

import java.io.UnsupportedEncodingException;

/**
 * 字节工具类
 *
 * @author farerboy
 */
public class ByteUtil {

    public static byte[] getByte(String content){
        try {
            return content.getBytes(Constant.CHARSET_UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new BaseException("UnsupportedEncodingException",e);
        }
    }

}
