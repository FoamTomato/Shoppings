package com.sybinal.shop.model;

import java.util.Date;

public class FLogistics {
	
    public FLogistics() {
		super();
	}

	public FLogistics( String fIds, String fOldOrder, String fOldPurchase, String fClientOrderCode,
			Integer limit, String fStore, Date fPayTime, String fCurrency, String fTotalPrice, String fChinaShort,
			String fFirstName, String fLastName, String fCountry, String fProvince, String fCity, String fPostCode,
			String fEmail, String fTelephone, String fAddress1, String fAddress2, String fAddress3, String fLogistics,
			String fEnglishShort, String fChannel, String fSheet, String fStatue, String fFreight, String fCustoms,
			Double fWeight, String fRemark, String standby1, String standby2, String standby3, String standby4,
			String standby5, String standby6, String standby7, String standby8, String standby9) {
		super();
		this.fIds = fIds;
		this.fOldOrder = fOldOrder;
		this.fOldPurchase = fOldPurchase;
		this.fClientOrderCode = fClientOrderCode;
		this.limit = limit;
		this.fStore = fStore;
		this.fPayTime = fPayTime;
		this.fCurrency = fCurrency;
		this.fTotalPrice = fTotalPrice;
		this.fChinaShort = fChinaShort;
		this.fFirstName = fFirstName;
		this.fLastName = fLastName;
		this.fCountry = fCountry;
		this.fProvince = fProvince;
		this.fCity = fCity;
		this.fPostCode = fPostCode;
		this.fEmail = fEmail;
		this.fTelephone = fTelephone;
		this.fAddress1 = fAddress1;
		this.fAddress2 = fAddress2;
		this.fAddress3 = fAddress3;
		this.fLogistics = fLogistics;
		this.fEnglishShort = fEnglishShort;
		this.fChannel = fChannel;
		this.fSheet = fSheet;
		this.fStatue = fStatue;
		this.fFreight = fFreight;
		this.fCustoms = fCustoms;
		this.fWeight = fWeight;
		this.fRemark = fRemark;
		this.standby1 = standby1;
		this.standby2 = standby2;
		this.standby3 = standby3;
		this.standby4 = standby4;
		this.standby5 = standby5;
		this.standby6 = standby6;
		this.standby7 = standby7;
		this.standby8 = standby8;
		this.standby9 = standby9;
	}
	
	//时间
	private String T;
	//价格
	private String P;
	//中文转
	private String z;
	//代替fid
	private String ffids;
	//代替fCountry
	private String ffCountry;
	
	
	
	public String getFfCountry() {
		return ffCountry;
	}

	public void setFfCountry(String ffCountry) {
		this.ffCountry = ffCountry;
	}

	public String getFfids() {
		return ffids;
	}

	public void setFfids(String ffids) {
		this.ffids = ffids;
	}

	public String getZ() {
		return z;
	}

	public void setZ(String z) {
		this.z = z;
	}

	public String getP() {
		return P;
	}

	public void setP(String p) {
		P = p;
	}

	public String getT() {
		return T;
	}

	public void setT(String t) {
		T = t;
	}

	private Integer id;

    private String fIds;

    private String fOldOrder;

    private String fOldPurchase;

    private String fClientOrderCode;
    
    private Integer limit;

    private Integer page;
    
    public Integer getPage() {
		return page; 
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	private String fStore;

    private Date fPayTime;
    
    private String endDatas;
    
    private String startDatas;

    public String getEndDatas() {
		return endDatas;
	}

	public void setEndDatas(String endDatas) {
		this.endDatas = endDatas;
	}

	public String getStartDatas() {
		return startDatas;
	}

	public void setStartDatas(String startDatas) {
		this.startDatas = startDatas;
	}

	private String fCurrency;

    private String fTotalPrice;

    private String fChinaShort;

    private String fFirstName;

    private String fLastName;

    private String fCountry;

    private String fProvince;

    private String fCity;

    private String fPostCode;

    private String fEmail;

    private String fTelephone;

    private String fAddress1;

    private String fAddress2;

    private String fAddress3;

    private String fLogistics;

    private String fEnglishShort;

    private String fChannel;

    private String fSheet;

    private String fStatue;

    private String fFreight;

    private String fCustoms;

    private Double fWeight;

    private String fRemark;

    private String standby1;

    private String standby2;

    private String standby3;

    private String standby4;

    private String standby5;

    private String standby6;

    private String standby7;

    private String standby8;

    private String standby9;

    private String standby10;

    private String standby11;

    private String standby12;

    private String standby13;

    private String standby14;

    private String standby15;

    private String standby16;
    private String standby17;
    private String standby18;
    private String standby19;
    
    public String getStandby17() {
		return standby17;
	}

	public void setStandby17(String standby17) {
		this.standby17 = standby17;
	}

	public String getStandby18() {
		return standby18;
	}

	public void setStandby18(String standby18) {
		this.standby18 = standby18;
	}

	public String getStandby19() {
		return standby19;
	}

	public void setStandby19(String standby19) {
		this.standby19 = standby19;
	}

	private String updateTime;

    public String getStandby15() {
		return standby15;
	}

	public void setStandby15(String standby15) {
		this.standby15 = standby15;
	}

	public String getStandby16() {
		return standby16;
	}

	public void setStandby16(String standby16) {
		this.standby16 = standby16;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getfOldOrder() {
        return fOldOrder;
    }

    public void setfOldOrder(String fOldOrder) {
        this.fOldOrder = fOldOrder == null ? null : fOldOrder.trim();
    }

    public String getfOldPurchase() {
        return fOldPurchase;
    }

    public void setfOldPurchase(String fOldPurchase) {
        this.fOldPurchase = fOldPurchase == null ? null : fOldPurchase.trim();
    }

    public String getfClientOrderCode() {
        return fClientOrderCode;
    }

    public void setfClientOrderCode(String fClientOrderCode) {
        this.fClientOrderCode = fClientOrderCode == null ? null : fClientOrderCode.trim();
    }

    public String getfStore() {
        return fStore;
    }

    public void setfStore(String fStore) {
        this.fStore = fStore == null ? null : fStore.trim();
    }

    public Date getfPayTime() {
        return fPayTime;
    }

    public void setfPayTime(Date fPayTime) {
        this.fPayTime = fPayTime;
    }

    public String getfCurrency() {
        return fCurrency;
    }

    public void setfCurrency(String fCurrency) {
        this.fCurrency = fCurrency == null ? null : fCurrency.trim();
    }

    public String getfTotalPrice() {
        return fTotalPrice;
    }

    public void setfTotalPrice(String fTotalPrice) {
        this.fTotalPrice = fTotalPrice == null ? null : fTotalPrice.trim();
    }

    public String getfChinaShort() {
        return fChinaShort;
    }

    public void setfChinaShort(String fChinaShort) {
        this.fChinaShort = fChinaShort == null ? null : fChinaShort.trim();
    }

    public String getfFirstName() {
        return fFirstName;
    }

    public void setfFirstName(String fFirstName) {
        this.fFirstName = fFirstName == null ? null : fFirstName.trim();
    }

    public String getfLastName() {
        return fLastName;
    }

    public void setfLastName(String fLastName) {
        this.fLastName = fLastName == null ? null : fLastName.trim();
    }

    public String getfCountry() {
        return fCountry;
    }

    public void setfCountry(String fCountry) {
        this.fCountry = fCountry == null ? null : fCountry.trim();
    }

    public String getfProvince() {
        return fProvince;
    }

    public void setfProvince(String fProvince) {
        this.fProvince = fProvince == null ? null : fProvince.trim();
    }

    public String getfCity() {
        return fCity;
    }

    public void setfCity(String fCity) {
        this.fCity = fCity == null ? null : fCity.trim();
    }

    public String getfPostCode() {
        return fPostCode;
    }

    public void setfPostCode(String fPostCode) {
        this.fPostCode = fPostCode == null ? null : fPostCode.trim();
    }

    public String getfEmail() {
        return fEmail;
    }

    public void setfEmail(String fEmail) {
        this.fEmail = fEmail == null ? null : fEmail.trim();
    }

    public String getfTelephone() {
        return fTelephone;
    }

    public void setfTelephone(String fTelephone) {
        this.fTelephone = fTelephone == null ? null : fTelephone.trim();
    }

    public String getfAddress1() {
        return fAddress1;
    }

    public void setfAddress1(String fAddress1) {
        this.fAddress1 = fAddress1 == null ? null : fAddress1.trim();
    }

    public String getfAddress2() {
        return fAddress2;
    }

    public void setfAddress2(String fAddress2) {
        this.fAddress2 = fAddress2 == null ? null : fAddress2.trim();
    }

    public String getfAddress3() {
        return fAddress3;
    }

    public void setfAddress3(String fAddress3) {
        this.fAddress3 = fAddress3 == null ? null : fAddress3.trim();
    }

    public String getfLogistics() {
        return fLogistics;
    }

    public void setfLogistics(String fLogistics) {
        this.fLogistics = fLogistics == null ? null : fLogistics.trim();
    }

    public String getfEnglishShort() {
        return fEnglishShort;
    }

    public void setfEnglishShort(String fEnglishShort) {
        this.fEnglishShort = fEnglishShort == null ? null : fEnglishShort.trim();
    }

    public String getfChannel() {
        return fChannel;
    }

    public void setfChannel(String fChannel) {
        this.fChannel = fChannel == null ? null : fChannel.trim();
    }

    public String getfSheet() {
        return fSheet;
    }

    public void setfSheet(String fSheet) {
        this.fSheet = fSheet == null ? null : fSheet.trim();
    }

    public String getfStatue() {
        return fStatue;
    }

    public void setfStatue(String fStatue) {
        this.fStatue = fStatue == null ? null : fStatue.trim();
    }

    public String getfFreight() {
        return fFreight;
    }

    public void setfFreight(String fFreight) {
        this.fFreight = fFreight == null ? null : fFreight.trim();
    }

    public String getfCustoms() {
        return fCustoms;
    }

    public void setfCustoms(String fCustoms) {
        this.fCustoms = fCustoms == null ? null : fCustoms.trim();
    }

    public Double getfWeight() {
        return fWeight;
    }

    public void setfWeight(Double fWeight) {
        this.fWeight = fWeight;
    }

    public String getfRemark() {
        return fRemark;
    }

    public void setfRemark(String fRemark) {
        this.fRemark = fRemark == null ? null : fRemark.trim();
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

    public String getStandby6() {
        return standby6;
    }

    public void setStandby6(String standby6) {
        this.standby6 = standby6 == null ? null : standby6.trim();
    }

    public String getStandby7() {
        return standby7;
    }

    public void setStandby7(String standby7) {
        this.standby7 = standby7 == null ? null : standby7.trim();
    }

    public String getStandby8() {
        return standby8;
    }

    public void setStandby8(String standby8) {
        this.standby8 = standby8 == null ? null : standby8.trim();
    }

    public String getStandby9() {
        return standby9;
    }

    public void setStandby9(String standby9) {
        this.standby9 = standby9 == null ? null : standby9.trim();
    }

	public String getStandby10() {
		return standby10;
	}

	public void setStandby10(String standby10) {
		this.standby10 = standby10;
	}

	public String getStandby11() {
		return standby11;
	}

	public void setStandby11(String standby11) {
		this.standby11 = standby11;
	}

	public String getStandby12() {
		return standby12;
	}

	public void setStandby12(String standby12) {
		this.standby12 = standby12;
	}

	public String getStandby13() {
		return standby13;
	}

	public void setStandby13(String standby13) {
		this.standby13 = standby13;
	}

	public String getStandby14() {
		return standby14;
	}

	public void setStandby14(String standby14) {
		this.standby14 = standby14;
	}

	public String getfIds() {
		return fIds;
	}

	public void setfIds(String fIds) {
		this.fIds = fIds;
	}

	@Override
	public String toString() {
		return "FLogistics [T=" + T + ", P=" + P + ", z=" + z + ", id=" + id + ", fIds=" + fIds + ", fOldOrder="
				+ fOldOrder + ", fOldPurchase=" + fOldPurchase + ", fClientOrderCode=" + fClientOrderCode + ", limit="
				+ limit + ", page=" + page + ", fStore=" + fStore + ", fPayTime=" + fPayTime + ", endDatas=" + endDatas
				+ ", startDatas=" + startDatas + ", fCurrency=" + fCurrency + ", fTotalPrice=" + fTotalPrice
				+ ", fChinaShort=" + fChinaShort + ", fFirstName=" + fFirstName + ", fLastName=" + fLastName
				+ ", fCountry=" + fCountry + ", fProvince=" + fProvince + ", fCity=" + fCity + ", fPostCode="
				+ fPostCode + ", fEmail=" + fEmail + ", fTelephone=" + fTelephone + ", fAddress1=" + fAddress1
				+ ", fAddress2=" + fAddress2 + ", fAddress3=" + fAddress3 + ", fLogistics=" + fLogistics
				+ ", fEnglishShort=" + fEnglishShort + ", fChannel=" + fChannel + ", fSheet=" + fSheet + ", fStatue="
				+ fStatue + ", fFreight=" + fFreight + ", fCustoms=" + fCustoms + ", fWeight=" + fWeight + ", fRemark="
				+ fRemark + ", standby1=" + standby1 + ", standby2=" + standby2 + ", standby3=" + standby3
				+ ", standby4=" + standby4 + ", standby5=" + standby5 + ", standby6=" + standby6 + ", standby7="
				+ standby7 + ", standby8=" + standby8 + ", standby9=" + standby9 + ", standby10=" + standby10
				+ ", standby11=" + standby11 + ", standby12=" + standby12 + ", standby13=" + standby13 + ", standby14="
				+ standby14 + ", standby15=" + standby15 + ", standby16=" + standby16 + ", updateTime=" + updateTime
				+ "]";
	}
    
}