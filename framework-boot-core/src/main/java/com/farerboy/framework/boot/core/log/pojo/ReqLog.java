package com.farerboy.framework.boot.core.log.pojo;

import java.sql.Timestamp;

public class ReqLog {
	private String systemFlag;// 系统标志
	private String reqLogId; //请求Id
	private String reqUrl;// 请求路径
	private String clientIp;// 客户端Ip
	private String reqClassName;// 类路ing
	private String reqMethodName;//方法
	private String reqParameter;// 请求参数
	private String useTime;// 耗时
	private Timestamp startTime;//开始时间
	private Timestamp endTime;// 结束时间
	private String reqType;// 请求类型
	private String reqResult;// 请求结果
	private Integer reqFlag;// 请求状态 成功，失败
	private String pReqLogId;// 父级请求Id
	private String reqException;// 异常信息
	private String sysCode; //系统标志
	public String getReqLogId() {
		return reqLogId;
	}
	public void setReqLogId(String reqLogId) {
		this.reqLogId = reqLogId;
	}
	public String getReqUrl() {
		return reqUrl;
	}
	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getReqClassName() {
		return reqClassName;
	}
	public void setReqClassName(String reqClassName) {
		this.reqClassName = reqClassName;
	}
	public String getReqMethodName() {
		return reqMethodName;
	}
	public void setReqMethodName(String reqMethodName) {
		this.reqMethodName = reqMethodName;
	}
	public String getReqParameter() {
		return reqParameter;
	}
	public void setReqParameter(String reqParameter) {
		this.reqParameter = reqParameter;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getReqType() {
		return reqType;
	}
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	public String getReqResult() {
		return reqResult;
	}
	public void setReqResult(String reqResult) {
		this.reqResult = reqResult;
	}
	public String getpReqLogId() {
		return pReqLogId;
	}
	public void setpReqLogId(String pReqLogId) {
		this.pReqLogId = pReqLogId;
	}
	public String getUseTime() {
		return useTime;
	}
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	public Integer getReqFlag() {
		return reqFlag;
	}
	public void setReqFlag(Integer reqFlag) {
		this.reqFlag = reqFlag;
	}
	public String getReqException() {
		return reqException;
	}
	public void setReqException(String reqException) {
		this.reqException = reqException;
	}

	public String getSystemFlag() {
		return systemFlag;
	}

	public void setSystemFlag(String systemFlag) {
		this.systemFlag = systemFlag;
	}
	public String getSysCode() {
		return sysCode;
	}
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	@Override
	public String toString() {
		return "ReqLog{" +
				"reqLogId='" + reqLogId + '\'' +
				", reqUrl='" + reqUrl + '\'' +
				", clientIp='" + clientIp + '\'' +
				", reqClassName='" + reqClassName + '\'' +
				", reqMethodName='" + reqMethodName + '\'' +
				", reqParameter='" + reqParameter + '\'' +
				", useTime='" + useTime + '\'' +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", reqType='" + reqType + '\'' +
				", reqResult='" + reqResult + '\'' +
				", reqFlag=" + reqFlag +
				", pReqLogId='" + pReqLogId + '\'' +
				", reqException='" + reqException + '\'' +
				", sysCode='" + sysCode + '\'' +
				'}';
	}
}
