package com.sybinal.shop.model;

import java.util.Date;

public class emailDetails {
    private Integer id;
    /**
     * 邮件亚马逊编号
     */
    private String emailOrders;
    /**
     * 发件人名称
     */
    private String emailPostman;
    /**
     * 发件人地址
     */
    private String emailPostEmail;
    /**
     * 收件人名称
     */
    private String emailGetman;
    /**
     * 收件人地址
     */
    private String emailGetEmail;
    /**
     * 备注
     */
    private String emailRemark;
    /**
     * 发件时间
     */
    private Date emailTimes;
    /**
     * 是否已读
     */
    private String state;
    /**
     * 发件主题
     */
    private String spare1;
    /**
     * 内容
     */
    private String spare2;
    
    private String spare3;

    private String spare4;

    private String spare5;

    private String spare6;

    private String spare7;

    private String spare8;

    private String spare9;

    private String spare10;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailOrders() {
        return emailOrders;
    }

    public void setEmailOrders(String emailOrders) {
        this.emailOrders = emailOrders == null ? null : emailOrders.trim();
    }

    public String getEmailPostman() {
        return emailPostman;
    }

    public void setEmailPostman(String emailPostman) {
        this.emailPostman = emailPostman == null ? null : emailPostman.trim();
    }

    public String getEmailPostEmail() {
        return emailPostEmail;
    }

    public void setEmailPostEmail(String emailPostEmail) {
        this.emailPostEmail = emailPostEmail == null ? null : emailPostEmail.trim();
    }

    public String getEmailGetman() {
        return emailGetman;
    }

    public void setEmailGetman(String emailGetman) {
        this.emailGetman = emailGetman == null ? null : emailGetman.trim();
    }

    public String getEmailGetEmail() {
        return emailGetEmail;
    }

    public void setEmailGetEmail(String emailGetEmail) {
        this.emailGetEmail = emailGetEmail == null ? null : emailGetEmail.trim();
    }

    public String getEmailRemark() {
        return emailRemark;
    }

    public void setEmailRemark(String emailRemark) {
        this.emailRemark = emailRemark == null ? null : emailRemark.trim();
    }

    public Date getEmailTimes() {
        return emailTimes;
    }

    public void setEmailTimes(Date emailTimes) {
        this.emailTimes = emailTimes;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getSpare1() {
        return spare1;
    }

    public void setSpare1(String spare1) {
        this.spare1 = spare1 == null ? null : spare1.trim();
    }

    public String getSpare2() {
        return spare2;
    }

    public void setSpare2(String spare2) {
        this.spare2 = spare2 == null ? null : spare2.trim();
    }

    public String getSpare3() {
        return spare3;
    }

    public void setSpare3(String spare3) {
        this.spare3 = spare3 == null ? null : spare3.trim();
    }

    public String getSpare4() {
        return spare4;
    }

    public void setSpare4(String spare4) {
        this.spare4 = spare4 == null ? null : spare4.trim();
    }

    public String getSpare5() {
        return spare5;
    }

    public void setSpare5(String spare5) {
        this.spare5 = spare5 == null ? null : spare5.trim();
    }

    public String getSpare6() {
        return spare6;
    }

    public void setSpare6(String spare6) {
        this.spare6 = spare6 == null ? null : spare6.trim();
    }

    public String getSpare7() {
        return spare7;
    }

    public void setSpare7(String spare7) {
        this.spare7 = spare7 == null ? null : spare7.trim();
    }

    public String getSpare8() {
        return spare8;
    }

    public void setSpare8(String spare8) {
        this.spare8 = spare8 == null ? null : spare8.trim();
    }

    public String getSpare9() {
        return spare9;
    }

    public void setSpare9(String spare9) {
        this.spare9 = spare9 == null ? null : spare9.trim();
    }

    public String getSpare10() {
        return spare10;
    }

    public void setSpare10(String spare10) {
        this.spare10 = spare10 == null ? null : spare10.trim();
    }

	@Override
	public String toString() {
		return "emailDetails [id=" + id + ", emailOrders=" + emailOrders + ", emailPostman=" + emailPostman
				+ ", emailPostEmail=" + emailPostEmail + ", emailGetman=" + emailGetman + ", emailGetEmail="
				+ emailGetEmail + ", emailRemark=" + emailRemark + ", emailTimes=" + emailTimes + ", state=" + state
				+ ", spare1=" + spare1 + ", spare2=" + spare2 + ", spare3=" + spare3 + ", spare4=" + spare4
				+ ", spare5=" + spare5 + ", spare6=" + spare6 + ", spare7=" + spare7 + ", spare8=" + spare8
				+ ", spare9=" + spare9 + ", spare10=" + spare10 + "]";
	}
    
}