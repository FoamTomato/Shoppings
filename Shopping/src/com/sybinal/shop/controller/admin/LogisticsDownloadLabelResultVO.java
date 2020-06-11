package com.sybinal.shop.controller.admin;

import java.io.Serializable;

/**
 * 下载面单返回结果
 * 
 * @author wuly
 *
 */
public class LogisticsDownloadLabelResultVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String lableKey;
	private Integer status;
	private String errorCode;
	private String errorMessage;
	private byte[] lableData;

	public String getLableKey() {
		return lableKey;
	}

	public void setLableKey(String lableKey) {
		this.lableKey = lableKey;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public byte[] getLableData() {
		return lableData;
	}

	public void setLableData(byte[] lableData) {
		this.lableData = lableData;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
