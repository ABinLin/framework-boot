package com.farerboy.framework.boot.util.encryption.exception;

import com.framework.boot.common.exception.BaseException;

/**
 * 数据加密处理异常
 *
 * @author farerboy
 * @date 2020/12/29 8:04 下午
 */
public class EncryptionException extends BaseException {

    public EncryptionException(String msg) {
        super(msg);
    }

    public EncryptionException(String msg, Throwable e) {
        super(msg, e);
    }

    public EncryptionException(String code, String msg) {
        super(code, msg);
    }

    public EncryptionException(String code, String msg, Throwable e) {
        super(code, msg, e);
    }
}
