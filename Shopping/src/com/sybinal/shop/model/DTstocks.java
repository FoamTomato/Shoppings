package com.sybinal.shop.model;

import org.springframework.web.multipart.MultipartFile;

public class DTstocks {
	private Integer id;

	private String fcolor;

	private String fno;

	private String fadd;

	private Integer fnum;

	private String fid;

	private String fsize;

	private String fimg;

	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFcolor() {
		return fcolor;
	}

	public void setFcolor(String fcolor) {
		this.fcolor = fcolor == null ? null : fcolor.trim();
	}

	public String getFno() {
		return fno;
	}

	public void setFno(String fno) {
		this.fno = fno == null ? null : fno.trim();
	}

	public String getFadd() {
		return fadd;
	}

	public void setFadd(String fadd) {
		this.fadd = fadd == null ? null : fadd.trim();
	}

	public Integer getFnum() {
		return fnum;
	}

	public void setFnum(Integer fnum) {
		this.fnum = fnum;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid == null ? null : fid.trim();
	}

	public String getFsize() {
		return fsize;
	}

	public void setFsize(String fsize) {
		this.fsize = fsize == null ? null : fsize.trim();
	}

	public String getFimg() {
		return fimg;
	}

	public void setFimg(String fimg) {
		this.fimg = fimg == null ? null : fimg.trim();
	}

	@Override
	public String toString() {
		return "DTstocks [id=" + id + ", fcolor=" + fcolor + ", fno=" + fno + ", fadd=" + fadd + ", fnum=" + fnum
				+ ", fid=" + fid + ", fsize=" + fsize + ", fimg=" + fimg + ", file=" + file + "]";
	}

}