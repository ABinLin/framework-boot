package com.farerboy.framework.boot.util;

import com.framework.boot.common.constant.Constant;
import com.framework.boot.common.exception.BaseException;

import java.io.UnsupportedEncodingException;

/**
 * 字节工具类
 *
 * @author farerboy
 * @date 2020/12/29 8:44 下午
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
