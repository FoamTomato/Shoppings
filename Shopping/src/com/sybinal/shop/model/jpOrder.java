package com.sybinal.shop.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class jpOrder {
    private Integer id;

    private String jpLaks;

    private Date jpTime;

    private String jpLogistic;

    private String jpLength;

    private String jpSuttle;

    private String jpRoughweight;

    private String jpAdmin;

    private String jpStandby1;

    private String jpStandby2;

    private String jpStandby3;

    private String jpStandby4;

    private String jpStandby5;

    private String jpStandby6;

    private String jpResult;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJpLaks() {
        return jpLaks;
    }

    public void setJpLaks(String jpLaks) {
        this.jpLaks = jpLaks == null ? null : jpLaks.trim();
    }

    public Date getJpTime() {
        return jpTime;
    }

    public void setJpTime(Date date) {
        this.jpTime = date;
    }

    public String getJpLogistic() {
        return jpLogistic;
    }

    public void setJpLogistic(String jpLogistic) {
        this.jpLogistic = jpLogistic == null ? null : jpLogistic.trim();
    }

    public String getJpLength() {
        return jpLength;
    }

    public void setJpLength(String jpLength) {
        this.jpLength = jpLength == null ? null : jpLength.trim();
    }

    public String getJpSuttle() {
        return jpSuttle;
    }

    public void setJpSuttle(String jpSuttle) {
        this.jpSuttle = jpSuttle == null ? null : jpSuttle.trim();
    }

    public String getJpRoughweight() {
        return jpRoughweight;
    }

    public void setJpRoughweight(String jpRoughweight) {
        this.jpRoughweight = jpRoughweight == null ? null : jpRoughweight.trim();
    }

    public String getJpAdmin() {
        return jpAdmin;
    }

    public void setJpAdmin(String jpAdmin) {
        this.jpAdmin = jpAdmin == null ? null : jpAdmin.trim();
    }

    public String getJpStandby1() {
        return jpStandby1;
    }

    public void setJpStandby1(String jpStandby1) {
        this.jpStandby1 = jpStandby1 == null ? null : jpStandby1.trim();
    }

    public String getJpStandby2() {
        return jpStandby2;
    }

    public void setJpStandby2(String jpStandby2) {
        this.jpStandby2 = jpStandby2 == null ? null : jpStandby2.trim();
    }

    public String getJpStandby3() {
        return jpStandby3;
    }

    public void setJpStandby3(String jpStandby3) {
        this.jpStandby3 = jpStandby3 == null ? null : jpStandby3.trim();
    }

    public String getJpStandby4() {
        return jpStandby4;
    }

    public void setJpStandby4(String jpStandby4) {
        this.jpStandby4 = jpStandby4 == null ? null : jpStandby4.trim();
    }

    public String getJpStandby5() {
        return jpStandby5;
    }

    public void setJpStandby5(String jpStandby5) {
        this.jpStandby5 = jpStandby5 == null ? null : jpStandby5.trim();
    }

    public String getJpStandby6() {
        return jpStandby6;
    }

    public void setJpStandby6(String jpStandby6) {
        this.jpStandby6 = jpStandby6 == null ? null : jpStandby6.trim();
    }

    public String getJpResult() {
        return jpResult;
    }

    public void setJpResult(String jpResult) {
        this.jpResult = jpResult == null ? null : jpResult.trim();
    }
}