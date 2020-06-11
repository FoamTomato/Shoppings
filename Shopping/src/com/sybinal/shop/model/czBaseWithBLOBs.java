package com.sybinal.shop.model;

public class czBaseWithBLOBs extends czBase {
    private String toAddress1;

    private String formAddress1;

    private String saleUrl;

    private String photoUrl;

    private String remark;

    public String getToAddress1() {
        return toAddress1;
    }

    public void setToAddress1(String toAddress1) {
        this.toAddress1 = toAddress1 == null ? null : toAddress1.trim();
    }

    public String getFormAddress1() {
        return formAddress1;
    }

    public void setFormAddress1(String formAddress1) {
        this.formAddress1 = formAddress1 == null ? null : formAddress1.trim();
    }

    public String getSaleUrl() {
        return saleUrl;
    }

    public void setSaleUrl(String saleUrl) {
        this.saleUrl = saleUrl == null ? null : saleUrl.trim();
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl == null ? null : photoUrl.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}