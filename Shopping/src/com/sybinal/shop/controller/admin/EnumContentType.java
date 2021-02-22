package com.sybinal.shop.controller.admin;

public enum EnumContentType {
	URLEncoder("application/x-www-form-urlencoded;charset=UTF-8"), JSON("application/json"), XML(
			"text/xml;charset=UTF-8"), OCTET("application/octet-stream"),PDF("application/pdf");

	String contentType;

	private EnumContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentType() {
		return this.contentType;
	}
}