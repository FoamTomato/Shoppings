package com.sybinal.shop.model;

import org.springframework.web.multipart.MultipartFile;

public class OnlineDownload {
	private Integer id;

	private String fSku;
	
	private MultipartFile files;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getfSku() {
		return fSku;
	}

	public void setfSku(String fSku) {
		this.fSku = fSku == null ? null : fSku.trim();
	}

	@Override
	public String toString() {
		return "OnlineDownload [id=" + id + ", fSku=" + fSku + ", files=" + files + "]";
	}

	public MultipartFile getFiles() {
		return files;
	}

	public void setFiles(MultipartFile files) {
		this.files = files;
	}

	

}