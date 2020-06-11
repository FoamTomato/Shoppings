package com.sybinal.shop.controller.admin;

import java.util.Arrays;

public class Results_hj {
	private String lableKey;
	private String status;
	private String errorCode;
	private String errorMessage;
	private String trackNum1;
	private String trackNum2;
	private String lableURL;
	private byte[] lableData;
	public String getLableKey() {
		return lableKey;
	}
	public void setLableKey(String lableKey) {
		this.lableKey = lableKey;
	}
	
	public String getTrackNum1() {
		return trackNum1;
	}
	public void setTrackNum1(String trackNum1) {
		this.trackNum1 = trackNum1;
	}
	public String getTrackNum2() {
		return trackNum2;
	}
	public void setTrackNum2(String trackNum2) {
		this.trackNum2 = trackNum2;
	}
	public String getLableURL() {
		return lableURL;
	}
	public void setLableURL(String lableURL) {
		this.lableURL = lableURL;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	@Override
	public String toString() {
		return "Results_hj [lableKey=" + lableKey + ", status=" + status + ", errorCode=" + errorCode
				+ ", errorMessage=" + errorMessage + ", lableData=" + Arrays.toString(lableData) + "]";
	}
	
}
