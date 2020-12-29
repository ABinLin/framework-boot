package com.framework.boot.common.enums;

/**
 *@Description 请求放回编码
 *@Author farerboy
 *@Date 2019/7/2 0002 14:01
 *@Version 1.0
 */
public enum ResponseCode {
    /**
     * 请求返回编码
     * @author farerboy
     * @date 2020/12/26 1:53 下午
     */
    SUCCESS("success","操作成功"),

    NO_DATA("NO_DATA","无数据"),

    SYSTEM_ERROR("SYSTEM_ERROR","系统异常"),

    HTTP_METHOD_NOT_SUPPORTED("HTTP_METHOD_NOT_SUPPORTED","HTTP方法类型错误"),

    PARAMETER_BINDING_FAIL("PARAMETER_BINDING_FAIL","参数绑定失败"),

    MESSING_PARAMETER("MESSING_PARAMETER","缺少参数"),

    ILLEGAL_ARGUMENT("ILLEGAL_ARGUMENT","参数不合法"),

    DATA_VALIDATE_ERROR("DATA_VALIDATE_ERROR","数据校验失败"),

    DATA_ERROR("DATA_ERROR","数据异常"),

    HTTP_INTERFACE_ERROR("HTTP_INTERFACE_ERROR","接口异常"),

    HTTP_MESSAGE_NOT_READABLE("HTTP_MESSAGE_NOT_READABLE","请求消息体异常"),

    CONTENT_TYPE_ERROR("CONTENT_TYPE_ERROR","Content type error"),

    HTTP_MEDIA_TYPE_NOT_ACCEPTABLE_EXCEPTION("HTTP_MEDIA_TYPE_NOT_ACCEPTABLE_EXCEPTION","客户端无法解析服务端返回的内容"),

    TYPE_MISMATCH_EXCEPTION("TYPE_MISMATCH_EXCEPTION","参数类型不匹配"),

    RESPONSE_ENCRYPT_ERROR("RESPONSE_ENCRYPT_ERROR","返回数据加密异常")
    ;

    private final String code;
    private final String msg;

    private ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
