package com.farerboy.framework.boot.common.dto;

import com.farerboy.framework.boot.common.Transformation;
import com.farerboy.framework.boot.common.enums.ResponseCode;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 通用的数据响应对象
 * @Author farerboy
 * @Date 2019/7/2 0002 15:01
 * @Version 1.0
 */
public class ServerResponse<T> implements Transformation {

	private String code;
    private String msg;
    private boolean success = true;
    private T data;
    /**
     * 响应头，用于存放扩展信息
     */
    private Map<String, Object> headers;

    private ServerResponse(boolean success) {
        this.success = success;
    }

    private ServerResponse(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    private ServerResponse(boolean success, String code, String msg) {
        this.success = success;
        this.msg = msg;
        this.code = code;
    }

    private ServerResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    private ServerResponse(boolean success, String msg, T data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    private ServerResponse(boolean success, String code, String msg, T data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
        this.code = code;
    }

    private ServerResponse(boolean success, ResponseCode responseCode){
        this.success = success;
        this.code = responseCode.getCode();
        this.msg = responseCode.getCode();
    }
    public String getCode() {
        return code;
    }

    public static <T> ServerResponse<T> createBy(boolean success,String code,String msg,T data){
        return new ServerResponse<>(success,code,msg,data);
    }

    public static <T> ServerResponse<T> createBySuccess() {
        return new ServerResponse<T>(true,ResponseCode.SUCCESS);
    }

    public static <T> ServerResponse<T> createBySuccess(ResponseCode responseCode) {
        return new ServerResponse<T>(true,responseCode);
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg) {
        return new ServerResponse<T>(true,ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data) {
        return new ServerResponse<T>(true, data);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
        return new ServerResponse<T>(true,msg,data);
    }

    public static <T> ServerResponse<T> createByError() {
        return new ServerResponse<T>(false,ResponseCode.SYSTEM_ERROR);
    }

    public static <T> ServerResponse<T> createByError(ResponseCode responseCode) {
        return new ServerResponse<T>(false,responseCode);
    }

    public static <T> ServerResponse<T> createByErrorMessage(String msg) {
        return new ServerResponse<T>(false, ResponseCode.SYSTEM_ERROR.getCode(), msg);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(String code, String msg) {
        return new ServerResponse<T>(false,code, msg);
    }


    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public Object getHeader(String name){
        if (this.headers == null || this.headers.size() == 0){
            return null;
        }
        return this.headers.get(name);
    }

    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }

    public void setHeader(String name,Object value){
        if(this.headers == null){
            synchronized (ServerResponse.class){
                if(this.headers == null){
                    this.headers = new HashMap<>(8);
                }
            }
        }
        this.headers.put(name,value);
    }

    public void setErrorHeader(String error){
        setHeader("error",error);
    }

    public void setErrorStackTrace(String errorStackTrace){
        setHeader("error_stack_trace",errorStackTrace);
    }
    public void setErrorHeader(Throwable e){
        setErrorHeader(e.getMessage());
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        String errorStackTrace = sw.getBuffer().toString();
        setErrorStackTrace(errorStackTrace);
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

}
