package com.sybinal.shop.model;

public class HJ_result {
	/*
	 * 订单编号
	 */
	private String id;
	/*
	 * 状态 0失败 1成功
	 */
	private String status;
	/*
	 * 错误描述
	 */
	private String errormsg;
	/*
	 * 错误码
	 */
	private String errorcode;
	/*
	 * 花费时间
	 */
	private String spenttime;
	/*
	 * 处理结果 0失败 1成功
	 */
	private String result;
	/*
	 * 错误等级（环金那边提供）
	 */
	private String errorlevel;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	public String getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}
	public String getSpenttime() {
		return spenttime;
	}
	public void setSpenttime(String spenttime) {
		this.spenttime = spenttime;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getErrorlevel() {
		return errorlevel;
	}
	public void setErrorlevel(String errorlevel) {
		this.errorlevel = errorlevel;
	}
	@Override
	public String toString() {
		return "HJ_result [id=" + id + ", status=" + status + ", errormsg=" + errormsg + ", errorcode=" + errorcode
				+ ", spenttime=" + spenttime + ", result=" + result + ", errorlevel=" + errorlevel + "]";
	}
	
	
}
