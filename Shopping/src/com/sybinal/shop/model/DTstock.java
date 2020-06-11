package com.sybinal.shop.model;

public class DTstock {
	private Integer id;

	private String fcolor;
	private String color;

	private String fno;
	private String no;

	private String fadd;
	private String add;

	private Integer fnum;
	private Integer num;

	private String fimg;
	private String img;

	private String fid;
	
	private String fonline;
	
	

	public String getFonline() {
		return fonline;
	}

	public void setFonline(String fonline) {
		this.fonline = fonline;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	private String fsize;
	private String size;

	public Integer getId() {
		return id;
	}


	public String getFcolor() {
		return fcolor;
	}

	public void setFcolor(String fcolor) {
		this.fcolor = fcolor;
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

	public String getFimg() {
		return fimg;
	}

	public void setFimg(String fimg) {
		this.fimg = fimg == null ? null : fimg.trim();
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

	@Override
	public String toString() {
		return "DTstock [id=" + id + ", fcolor=" + fcolor + ", color=" + color + ", fno=" + fno + ", no=" + no
				+ ", fadd=" + fadd + ", add=" + add + ", fnum=" + fnum + ", num=" + num + ", fimg=" + fimg + ", img="
				+ img + ", fid=" + fid + ", fonline=" + fonline + ", fsize=" + fsize + ", size=" + size + "]";
	}

}