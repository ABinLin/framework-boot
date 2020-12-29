package com.farerboy.framework.boot.core.advice;

import com.framework.boot.common.dto.ServerResponse;
import com.framework.boot.common.enums.ResponseCode;
import com.framework.boot.common.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;

/**
 * 全局异常处理器
 * @author farerboy
 * @date 2020/12/26 5:23 下午
 */
@RestControllerAdvice
public class ExceptionAdvice {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BaseException.class)
    public <T> ServerResponse<T> handleBaseException(BaseException e){
        logger.error("Base exception: "+e.getMessage(),e);
        return buildResponse(e.getCode(),e.getMsg(),e);
    }

    @ExceptionHandler(RuntimeException.class)
    public <T> ServerResponse<T> handleRuntimeException(RuntimeException e){
        logger.error("Runtime exception: "+e.getMessage(),e);
        return buildResponse(ResponseCode.SYSTEM_ERROR.getCode(),e.getMessage(),e);
    }

    @ExceptionHandler(Exception.class)
    public <T> ServerResponse<T> handleException(Exception e) {
        logger.error("System exception: ",e);
        return buildResponse(ResponseCode.SYSTEM_ERROR,e);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public <T> ServerResponse<T> request405(HttpRequestMethodNotSupportedException e) {
        return buildResponse(ResponseCode.HTTP_METHOD_NOT_SUPPORTED,e);
    }

    @ExceptionHandler(BindException.class)
    public <T> ServerResponse<T> handleBindException(BindException e){
        List<FieldError> lt = e.getBindingResult().getFieldErrors();
        StringBuffer sBuffer =  new StringBuffer();
        for(FieldError fieldError:lt) {
            sBuffer.append(fieldError.getDefaultMessage()+",");
        }
        if(sBuffer.length()>0) {
            sBuffer.deleteCharAt(sBuffer.length() - 1);
        }
        return buildResponse(ResponseCode.PARAMETER_BINDING_FAIL.getCode(),sBuffer.toString(),e);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public <T> ServerResponse<T> requestMissingServletRequest(MissingServletRequestParameterException e) {
        return buildResponse(ResponseCode.MESSING_PARAMETER.getCode(),e.getMessage(),e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public <T> ServerResponse<T> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        logger.error(e.getMessage(),e);
        StringBuilder sb = new StringBuilder();
        BindingResult result = e.getBindingResult();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(p -> {
                FieldError fieldError = (FieldError) p;
                sb.append(fieldError.getDefaultMessage());
            });
        }
        return buildResponse(ResponseCode.MESSING_PARAMETER.getCode(), sb.toString(),e);
    }

    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public <T> ServerResponse<T> request406(HttpMediaTypeNotAcceptableException e) {
        return buildResponse(ResponseCode.HTTP_MEDIA_TYPE_NOT_ACCEPTABLE_EXCEPTION,e);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public <T> ServerResponse<T> request400(HttpMessageNotReadableException e) {
        return buildResponse(ResponseCode.HTTP_MESSAGE_NOT_READABLE,e);
    }

    @ExceptionHandler({TypeMismatchException.class})
    public <T> ServerResponse<T> requestTypeMismatch(TypeMismatchException e) {
        return buildResponse(ResponseCode.TYPE_MISMATCH_EXCEPTION,e);
    }

    private <T> ServerResponse<T> buildResponse(ResponseCode responseCode,Throwable e){
        return buildResponse(responseCode.getCode(),responseCode.getMsg(),e);
    }

    private <T> ServerResponse<T> buildResponse(String code, String msg,Throwable e){
        ServerResponse response = ServerResponse.createByErrorCodeMessage(code,msg);
        response.setErrorHeader(e);
        return response;
    }




}
