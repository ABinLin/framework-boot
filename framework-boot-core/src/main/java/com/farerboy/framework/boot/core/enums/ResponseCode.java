package com.farerboy.framework.boot.core.enums;

/**
 *@Description 请求放回编码
 *@Author linjb
 *@Date 2019/7/2 0002 14:01
 *@Version 1.0
 */
public enum ResponseCode {

    Success(200,"操作成功"),
    NoData(201,"暂无数据"),

    InvalidToken(401,"无权限"),
    Error(500,"处理异常"),

    // 运行时异常定义
    NullPointerException(601,"空指针异常"),
    ClassCastException(602,"类型转换异常"),
    IOException(603,"IO异常"),
    NoSuchMethodException(604,"未知方法异常"),
    IndexOutOfBoundsException(605,"数组越界异常"),
    StackOverflowError(606,"栈溢出异常"),
    //Request method 'GET' not supported
    HttpRequestMethodNotSupportedException(607,"HTTP方法类型错误"),
    FailureOfParameterBinding(608,"参数绑定失败"),
    MissingServletRequestParameterException(609,"缺少参数"),

    // 校验结果定义
    ValidateError(1000,"数据校验失败"),

    // 数据异常
    DataError(2000,"数据异常"),

    // 接口异常定义
    InterfaceException(3000,"接口异常"),

    // 非法SQL
    SQLINERROR(800,"SQL包含非法字符");




    private final int status;
    private final String msg;

    private ResponseCode(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
