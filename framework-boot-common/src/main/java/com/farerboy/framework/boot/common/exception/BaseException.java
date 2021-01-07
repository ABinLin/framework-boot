package com.farerboy.framework.boot.common.exception;

/**
 * 自定义基础异常
 *
 * @author farerboy
 */
public class BaseException extends RuntimeException{

    private String msg;

    private String code;

    public BaseException(String code) {
        super(code);
        this.msg = code;
        this.code = code;
    }

    public BaseException(String code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public BaseException(String code, Throwable e) {
        super(e);
        this.msg = code;
        this.code = code;
    }

    public BaseException(String code, String msg, Throwable e) {
        super(e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
