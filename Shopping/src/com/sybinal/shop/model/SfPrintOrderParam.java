package com.sybinal.shop.model;

import java.io.Serializable;

/**
 * 打印订单所需要的参数 
 * */
public class SfPrintOrderParam implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
   
    //集拼单号
    private String mailNo = "";
    //物流渠道
    private String logisticsChannel="";
    //渠道袋序
    private String channelOrder = "";
    //袋内件数
    private String packNum = "";
    //净重
    private String jweight = "";
    //毛重
    private String mweight= "";
    //操作员
    private String adminUser = "";
    //time
    private String time = "";
    
    
    public SfPrintOrderParam() {
        super();
        // TODO Auto-generated constructor stub
    }


	public String getMailNo() {
		return mailNo;
	}


	public void setMailNo(String mailNo) {
		this.mailNo = mailNo;
	}


	public String getLogisticsChannel() {
		return logisticsChannel;
	}


	public void setLogisticsChannel(String logisticsChannel) {
		this.logisticsChannel = logisticsChannel;
	}


	public String getChannelOrder() {
		return channelOrder;
	}


	public void setChannelOrder(String channelOrder) {
		this.channelOrder = channelOrder;
	}


	public String getPackNum() {
		return packNum;
	}


	public void setPackNum(String packNum) {
		this.packNum = packNum;
	}


	public String getJweight() {
		return jweight;
	}


	public void setJweight(String jweight) {
		this.jweight = jweight;
	}


	public String getMweight() {
		return mweight;
	}


	public void setMweight(String mweight) {
		this.mweight = mweight;
	}


	public String getAdminUser() {
		return adminUser;
	}


	public void setAdminUser(String adminUser) {
		this.adminUser = adminUser;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "SfPrintOrderParam [mailNo=" + mailNo + ", logisticsChannel=" + logisticsChannel + ", channelOrder="
				+ channelOrder + ", packNum=" + packNum + ", jweight=" + jweight + ", mweight=" + mweight
				+ ", adminUser=" + adminUser + ", time=" + time + "]";
	}
    
    
}