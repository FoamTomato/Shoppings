package com.sybinal.shop.model;

public class CateList {
    private Integer id;

    private String cates;

    private Integer pid;
    
    private String statue;

    public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCates() {
        return cates;
    }

    public void setCates(String cates) {
        this.cates = cates == null ? null : cates.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

	@Override
	public String toString() {
		return "CateList [id=" + id + ", cates=" + cates + ", pid=" + pid + ", statue=" + statue + "]";
	}
    
}