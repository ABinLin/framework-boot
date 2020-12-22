package com.farerboy.framework.boot.core.advice;

import com.framework.boot.common.dto.ServerResponse;
import com.framework.boot.common.enums.ResponseCode;
import com.framework.boot.common.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;

/**
 * @Description 全局异常处理器
 * @Author linjb
 * @Date 2019/7/2 0002 16:04
 * @Version 1.0
 */
@RestControllerAdvice
public class ExceptionAdvice {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BaseException.class)
    public <T> ServerResponse<T> handleRPException(BaseException e){
        logger.error("自定义异常："+e.getMessage(),e);
        return buildResponse(ResponseCode.Error.getStatus(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public <T> ServerResponse<T> handleRuntimeException(RuntimeException e){
        logger.error("运行时异常："+e.getMessage(),e);
        return buildResponse(ResponseCode.Error.getStatus(), e.getMessage());
    }
    //其他异常
    @ExceptionHandler(Exception.class)
    public <T> ServerResponse<T> handleException(Exception e){
        logger.error("系统异常：",e);
        return buildResponse(ResponseCode.Error.getStatus(), ResponseCode.Error.getMsg());
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public <T> ServerResponse<T> request405(HttpRequestMethodNotSupportedException e) {
        return buildResponse(ResponseCode.HttpRequestMethodNotSupportedException.getStatus(),e.getMessage());
    }

    private <T> ServerResponse<T> buildResponse(int code,String errorMsage){
        return ServerResponse.createByErrorCodeMessage(code, errorMsage);
    }

    //参数验证不通过异常
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
        return buildResponse(ResponseCode.FailureOfParameterBinding.getStatus(),sBuffer.toString());
    }

    //缺少参数异常
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public <T> ServerResponse<T> requestMissingServletRequest(MissingServletRequestParameterException e) {
        return buildResponse(ResponseCode.MissingServletRequestParameterException.getStatus(),e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public <T> ServerResponse<T> handleRPException(MethodArgumentNotValidException e){
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
        return ServerResponse.createByErrorCodeMessage(ResponseCode.MissingServletRequestParameterException.getStatus(), sb.toString());
    }


/*
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public <T> ServerResponse<T> handleRPException(MethodArgumentNotValidException e){
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
        return ServerResponse.createByErrorCodeMessage(ResponseCode.Error.getStatus(), sb.toString());
    }

    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    public <T> ServerResponse<T> nullPointerExceptionHandler(NullPointerException e) {
        return backExptionRes(700,e,"空指针异常 ");
    }

    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    public <T> ServerResponse<T> classCastExceptionHandler(ClassCastException e) {
        return backExptionRes(701,e,"类型转换异常");
    }

    //IO异常
    @ExceptionHandler(IOException.class)
    public <T> ServerResponse<T> iOExceptionHandler(IOException e) {
        return backExptionRes(702,e,"未知方法异常");
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    public <T> ServerResponse<T> noSuchMethodExceptionHandler(NoSuchMethodException e) {
        return backExptionRes(703,e,"未知方法异常");
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public <T> ServerResponse<T> indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException e) {
        return backExptionRes(704,e,"数组越界异常");
    }
    //栈溢出
    @ExceptionHandler({StackOverflowError.class})
    public <T> ServerResponse<T> requestStackOverflow(StackOverflowError e) {
        StackTraceElement[] st = e.getStackTrace();
        String exclass = st[0].getClassName();
        String method = st[0].getMethodName();
        int errorNum = st[0].getLineNumber();
        String errorMessage = "[类:" + exclass + "]调用"
                + method + "时在第" + errorNum
                + "行代码处发生栈溢出:" + e.getClass().getName();

        return  ServerResponse.createByErrorCodeMessage(705, errorMessage);
    }


    //运算异常
    @ExceptionHandler(ArithmeticException.class)
    public <T> ServerResponse<T> arithmeticException(ArithmeticException e){
        return backExptionRes(708,e," 运算异常");
    }
    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public <T> ServerResponse<T> requestNotReadable(HttpMessageNotReadableException e) {
        return backExptionRes(400,e," 400");
    }

    //参数类型不匹配
    @ExceptionHandler({TypeMismatchException.class})
    public <T> ServerResponse<T> requestTypeMismatch(TypeMismatchException e) {
        return backExptionRes(4001,e," 参数类型不匹配");
    }



    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public <T> ServerResponse<T> request406(HttpMediaTypeNotAcceptableException e) {
        return backExptionRes(406,e," Not Acceptable");
    }

    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public <T> ServerResponse<T> server500(RuntimeException e) {
        return backExptionRes(500,e,"500");
    }



    private <T> ServerResponse<T> backExptionRes(int errorCode,Exception e,String exceptionType){
        *//*String errorMessage = getErrorMessage(e,exceptionType);*//*
        return ServerResponse.createByErrorCodeMessage(errorCode, exceptionType);
    }*/
    /*private String getErrorMessage(Exception e,String exceptionType) {
        logger.error(e.getMessage(),e);
        StackTraceElement[] st = e.getStackTrace();
        String exclass = st[0].getClassName();
        String method = st[0].getMethodName();
        int errorNum = st[0].getLineNumber();
        return "[类:" + exclass + "]调用"
                + method + "时在第" + errorNum
                + "行代码处发生"+exceptionType+"!异常类型:" + e.getClass().getName();
    }*/
}
