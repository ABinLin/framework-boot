package com.farerboy.framework.boot.core.log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.farerboy.framework.boot.core.log.pojo.ReqLog;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

@Component
@Scope("prototype")
@Aspect
public class ControllerAopLog {

    private Logger logger=LoggerFactory.getLogger(getClass());
    private ReqLog reqLog;

//    @Pointcut("execution(* com.yiyu..*Controller.*(..))") // 包
    @Around("@within(org.springframework.web.bind.annotation.RestController)")
	public void executeService(){}
	
	@Before(value = "executeService()")
	public void beforeAdvice(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        this.reqLog=(ReqLog) request.getAttribute("reqLog");
        if(this.reqLog ==null ){
            this.reqLog=new ReqLog();
        }
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        this.reqLog.setReqClassName(declaringTypeName);
        this.reqLog.setReqMethodName(methodName);
        if(!ArrayUtils.isEmpty(args)){
            try {
                this.reqLog.setReqParameter(JSONArray.toJSONString(args));
            }catch (Exception e){
                //  入参可能包含request
                this.reqLog.setReqParameter(args.toString());
            }
        }
	}
	
//	@After(value = "executeService()")
//	public void afterAdvice(JoinPoint joinPoint){
		/*endTime=System.currentTimeMillis();
		reqLog.setEndTime(new Timestamp(endTime));
		reqLog.setUseTime((endTime-startTime)+"");
		req.setEndTime(new Timestamp(endTime));
		req.setUseTime((endTime-startTime)+"");
        logger.debug("-----------afterAdvice start----------");
        logger.debug(reqLog.toString());
        logger.debug(req.toString());
        logger.debug("-----------afterAdvice end----------");*/
//	}
	
	/**
	 * obj 执行切点方法的返回值
	 */
	@AfterReturning(value = "executeService()" , returning="obj")
	public void afterAdviceReturn(Object obj){
		if(null!=obj){
		    this.reqLog.setReqResult(JSONObject.toJSONString(obj));
		}
	}
	
	@AfterThrowing(value= "executeService()" ,throwing="exception")
	public void afterAdviceThrowing(JoinPoint joinPoint , Throwable exception){
		this.reqLog.setReqFlag(0);
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw, true));
        String str = sw.toString();
        reqLog.setReqException(str);
	}
	
//	@Around("executeService()")
/*    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        
        Object result = null;
        String methodName = proceedingJoinPoint.getSignature().getName();
        //执行目标方法
        try {
            //前置通知
            System.out.println("The method " + methodName + "begin with" + Arrays.asList(proceedingJoinPoint.getArgs()));
            //执行代理方法
            result = proceedingJoinPoint.proceed();
            //后置通知
            System.out.println("The method " + methodName + "end with" + result);
        } catch (Throwable e) {
            //异常通知
            System.out.println("The method occurs exception : " + e);
            throw new RuntimeException();
        }
            //后置通知
        System.out.println("The method " + methodName + "end with" + result);
        //构造统一返回对象
        return result;
        
    }*/
	
}
