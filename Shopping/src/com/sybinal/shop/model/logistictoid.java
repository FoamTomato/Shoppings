package com.sybinal.shop.model;

public class logistictoid {
    private Integer id;

    private String refereno;

    private String logisticmethod;

    private String standby1;

    private String standby2;

    private String standby3;

    private String standby4;

    private String standby5;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRefereno() {
        return refereno;
    }

    public void setRefereno(String refereno) {
        this.refereno = refereno == null ? null : refereno.trim();
    }

    public String getLogisticmethod() {
        return logisticmethod;
    }

    public void setLogisticmethod(String logisticmethod) {
        this.logisticmethod = logisticmethod == null ? null : logisticmethod.trim();
    }

    public String getStandby1() {
        return standby1;
    }

    public void setStandby1(String standby1) {
        this.standby1 = standby1 == null ? null : standby1.trim();
    }

    public String getStandby2() {
        return standby2;
    }

    public void setStandby2(String standby2) {
        this.standby2 = standby2 == null ? null : standby2.trim();
    }

    public String getStandby3() {
        return standby3;
    }

    public void setStandby3(String standby3) {
        this.standby3 = standby3 == null ? null : standby3.trim();
    }

    public String getStandby4() {
        return standby4;
    }

    public void setStandby4(String standby4) {
        this.standby4 = standby4 == null ? null : standby4.trim();
    }

    public String getStandby5() {
        return standby5;
    }

    public void setStandby5(String standby5) {
        this.standby5 = standby5 == null ? null : standby5.trim();
    }

	@Override
	public String toString() {
		return "logistictoid [id=" + id + ", refereno=" + refereno + ", logisticmethod=" + logisticmethod
				+ ", standby1=" + standby1 + ", standby2=" + standby2 + ", standby3=" + standby3 + ", standby4="
				+ standby4 + ", standby5=" + standby5 + "]";
	}
    
}