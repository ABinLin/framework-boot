package com.farerboy.framework.boot.common.exception;

import com.farerboy.framework.boot.common.enums.ResponseCode;

public class ValidException extends RuntimeException{

    private String msg;

    private String code;

    public ValidException(String msg) {

        super(msg);
        this.msg = msg;
        this.code = ResponseCode.DATA_VALIDATE_ERROR.getCode();
    }

    public ValidException(String code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public ValidException(String msg, Throwable e) {
        super(e);
        this.msg = msg;
        this.code = ResponseCode.DATA_VALIDATE_ERROR.getCode();
    }

    public ValidException(String code, String msg, Throwable e) {
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
