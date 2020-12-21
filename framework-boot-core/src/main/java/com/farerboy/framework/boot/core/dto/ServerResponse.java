package com.farerboy.framework.boot.core.dto;

import com.farerboy.framework.boot.core.enums.ResponseCode;

import java.io.Serializable;

/**
 * @Description 通用的数据响应对象
 * @Author linjb
 * @Date 2019/7/2 0002 15:01
 * @Version 1.0
 */
public class ServerResponse<T> implements Serializable {

	private static final long serialVersionUID = 7026815170601873950L;
	private int status;
    private String msg;
    private T data;

    public ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static <T> ServerResponse<T> createBySuccess() {
        return new ServerResponse<T>(ResponseCode.Success.getStatus(),ResponseCode.Success.getMsg());
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg) {
        return new ServerResponse<T>(ResponseCode.Success.getStatus(), msg);
    }

    public static <T> ServerResponse<T> createBySuccessNoData(String msg) {
        return new ServerResponse<T>(ResponseCode.NoData.getStatus(), msg);
    }

    public static <T> ServerResponse<T> createBySuccessNoData() {
        return new ServerResponse<T>(ResponseCode.NoData.getStatus(),ResponseCode.NoData.getMsg());
    }

    public static <T> ServerResponse<T> createBySuccess(T data) {
        return new ServerResponse<T>(ResponseCode.Success.getStatus(), data);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
        return new ServerResponse<T>(ResponseCode.Success.getStatus(), msg, data);
    }

    public static <T> ServerResponse<T> createByError() {
        return new ServerResponse<T>(ResponseCode.Error.getStatus(), ResponseCode.Error.getMsg());
    }

    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage) {
        return new ServerResponse<T>(ResponseCode.Error.getStatus(), errorMessage);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
        return new ServerResponse<T>(errorCode, errorMessage);
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
