package com.sybinal.shop.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DTfzying {
	
	private Integer price1;
	
	private Integer price2;
	
	private String updateTime1;
	
	private String updateTime2;
	
	public Integer getPrice1() {
		return price1;
	}

	public void setPrice1(Integer price1) {
		this.price1 = price1;
	}

	public Integer getPrice2() {
		return price2;
	}

	public void setPrice2(Integer price2) {
		this.price2 = price2;
	}

	public String getUpdateTime1() {
		return updateTime1;
	}

	public void setUpdateTime1(String updateTime1) {
		this.updateTime1 = updateTime1;
	}

	public String getUpdateTime2() {
		return updateTime2;
	}

	public void setUpdateTime2(String updateTime2) {
		this.updateTime2 = updateTime2;
	}

	@Override
	public String toString() {
		return "DTfzying [price1=" + price1 + ", price2=" + price2 + ", updateTime1=" + updateTime1 + ", updateTime2="
				+ updateTime2 + ", Dsstock=" + Dsstock + ", sz=" + Arrays.toString(sz) + ", y=" + Arrays.toString(y)
				+ ", pic=" + Arrays.toString(pic) + ", id=" + id + ", fids=" + fids + ", ids=" + ids + ", fsku=" + fsku
				+ ", sku=" + sku + ", fbrand=" + fbrand + ", brand=" + brand + ", fkind=" + fkind + ", kind=" + kind
				+ ", fkeys=" + fkeys + ", keys=" + keys + ", fbullet=" + fbullet + ", bullet=" + bullet + ", fintro="
				+ fintro + ", intro=" + intro + ", fcur=" + fcur + ", cur=" + cur + ", fprice=" + fprice + ", price="
				+ price + ", fcost=" + fcost + ", cost=" + cost + ", ffreight=" + ffreight + ", freight=" + freight
				+ ", fdeclare=" + fdeclare + ", declare=" + declare + ", fshipid=" + fshipid + ", shipid=" + shipid
				+ ", fquantity=" + fquantity + ", quantity=" + quantity + ", fitems=" + fitems + ", items=" + items
				+ ", fweight=" + fweight + ", weight=" + weight + ", flength=" + flength + ", length=" + length
				+ ", fwidth=" + fwidth + ", width=" + width + ", fheight=" + fheight + ", height=" + height
				+ ", fbattery=" + fbattery + ", battery=" + battery + ", fspecial=" + fspecial + ", special=" + special
				+ ", fstock=" + fstock + ", stock=" + stock + ", forigin=" + forigin + ", origin=" + origin
				+ ", ffacturer=" + ffacturer + ", facturer=" + facturer + ", fnumber=" + fnumber + ", number=" + number
				+ ", fdepartment=" + fdepartment + ", department=" + department + ", fmaterial=" + fmaterial
				+ ", material=" + material + ", fmetal=" + fmetal + ", metal=" + metal + ", fgem=" + fgem + ", gem="
				+ gem + ", fpackages=" + fpackages + ", packages=" + packages + ", fhscode=" + fhscode + ", hscode="
				+ hscode + ", fenglish=" + fenglish + ", english=" + english + ", fchinese=" + fchinese + ", chinese="
				+ chinese + ", fsource=" + fsource + ", source=" + source + ", fmemo=" + fmemo + ", memo=" + memo
				+ ", frs1=" + frs1 + ", frs2=" + frs2 + ", frs3=" + frs3 + ", frs4=" + frs4 + ", frs5=" + frs5
				+ ", frs6=" + frs6 + ", frs7=" + frs7 + ", frs8=" + frs8 + ", frs9=" + frs9 + ", frs10=" + frs10 + "]";
	}

	private List<DTstock> Dsstock;

	public List<DTstock> getDsstock() {
		return Dsstock;
	}

	public void setDsstock(List<DTstock> dsstock) {
		Dsstock = dsstock;
	}

	private String[] sz;

	private DTstock[] y;
	private DTpic[] pic;

	public DTpic[] getPic() {
		return pic;
	}

	public void setPic(DTpic[] pic) {
		this.pic = pic;
	}

	public DTstock[] getY() {
		return y;
	}

	public void setY(DTstock[] y) {
		this.y = y;
	}

	public String[] getSz() {
		return sz;
	}

	public void setSz(String[] sz) {
		this.sz = sz;
	}

	private Integer id;

	private Integer fids;
	private Integer ids;

	private String fsku;
	private String sku;

	private String fbrand;
	private String brand;

	private String fkind;
	private String kind;

	private String fkeys;
	private String keys;

	private String fbullet;
	private String bullet;

	private String fintro;
	private String intro;

	private String fcur;
	private String cur;

	private Long fprice;
	private Long price;

	private Long fcost;
	private Long cost;

	private Double ffreight;
	private Double freight;

	private Double fdeclare;
	private Double declare;

	private Integer fshipid;
	private Integer shipid;

	private Integer fquantity;
	private Integer quantity;

	private Integer fitems;
	private Integer items;

	private Double fweight;
	private Double weight;

	private Double flength;
	private Double length;

	private Double fwidth;
	private Double width;

	private Double fheight;
	private Double height;

	private String fbattery;
	private String battery;

	private String fspecial;
	private String special;

	private String fstock;
	private String stock;

	private String forigin;
	private String origin;

	private String ffacturer;
	private String facturer;

	private Integer fnumber;
	private Integer number;

	private String fdepartment;
	private String department;

	private String fmaterial;
	private String material;

	private String fmetal;
	private String metal;

	private String fgem;
	private String gem;

	private String fpackages;
	private String packages;

	private String fhscode;
	private String hscode;

	private String fenglish;
	private String english;

	private String fchinese;
	private String chinese;

	private String fsource;
	private String source;

	private String fmemo;
	private String memo;

	private String frs1;

	public String getSku() {
		return sku;
	}

	public String getBrand() {
		return brand;
	}

	public String getKind() {
		return kind;
	}

	public String getKeys() {
		return keys;
	}

	public String getBullet() {
		return bullet;
	}

	public String getIntro() {
		return intro;
	}

	public String getCur() {
		return cur;
	}

	public Long getPrice() {
		return price;
	}

	public Long getCost() {
		return cost;
	}

	public Double getFreight() {
		return freight;
	}

	public Double getDeclare() {
		return declare;
	}

	public Integer getShipid() {
		return shipid;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Integer getItems() {
		return items;
	}

	public Double getWeight() {
		return weight;
	}

	public Double getLength() {
		return length;
	}

	public Double getWidth() {
		return width;
	}

	public Double getHeight() {
		return height;
	}

	public String getBattery() {
		return battery;
	}

	public String getSpecial() {
		return special;
	}

	public String getStock() {
		return stock;
	}

	public String getOrigin() {
		return origin;
	}

	public String getFacturer() {
		return facturer;
	}

	public Integer getNumber() {
		return number;
	}

	public String getDepartment() {
		return department;
	}

	public String getMaterial() {
		return material;
	}

	public String getMetal() {
		return metal;
	}

	public String getGem() {
		return gem;
	}

	public String getPackages() {
		return packages;
	}

	public String getHscode() {
		return hscode;
	}

	public String getEnglish() {
		return english;
	}

	public String getChinese() {
		return chinese;
	}

	public String getSource() {
		return source;
	}

	public String getMemo() {
		return memo;
	}

	private String frs2;

	private String frs3;

	private String frs4;

	private String frs5;

	private String frs6;

	private String frs7;

	private String frs8;

	private String frs9;

	private Date frs10;

	public Integer getId() {
		return fids;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFids() {
		return id;
	}

	public void setFids(Integer fids) {
		this.fids = fids;
	}

	public Integer getIds() {
		return ids;
	}

	public String getFsku() {
		return sku;
	}

	public void setFsku(String fsku) {
		this.fsku = fsku == null ? null : fsku.trim();
	}

	public String getFbrand() {
		return brand;
	}

	public void setFbrand(String fbrand) {
		this.fbrand = fbrand == null ? null : fbrand.trim();
	}

	public String getFkind() {
		return kind;
	}

	public void setFkind(String fkind) {
		this.fkind = fkind == null ? null : fkind.trim();
	}

	public String getFkeys() {
		return keys;
	}

	public void setFkeys(String fkeys) {
		this.fkeys = fkeys == null ? null : fkeys.trim();
	}

	public String getFbullet() {
		return bullet;
	}

	public void setFbullet(String fbullet) {
		this.fbullet = fbullet == null ? null : fbullet.trim();
	}

	public String getFintro() {
		return intro;
	}

	public void setFintro(String fintro) {
		this.fintro = fintro == null ? null : fintro.trim();
	}

	public String getFcur() {
		return cur;
	}

	public void setFcur(String fcur) {
		this.fcur = fcur == null ? null : fcur.trim();
	}

	public Long getFprice() {
		return price;
	}

	public void setFprice(Long fprice) {
		this.fprice = fprice;
	}

	public Long getFcost() {
		return cost;
	}

	public void setFcost(Long fcost) {
		this.fcost = fcost;
	}

	public Double getFfreight() {
		return freight;
	}

	public void setFfreight(Double ffreight) {
		this.ffreight = ffreight;
	}

	public Double getFdeclare() {
		return declare;
	}

	public void setFdeclare(Double fdeclare) {
		this.fdeclare = fdeclare;
	}

	public Integer getFshipid() {
		return shipid;
	}

	public void setFshipid(Integer fshipid) {
		this.fshipid = fshipid;
	}

	public Integer getFquantity() {
		return quantity;
	}

	public void setFquantity(Integer fquantity) {
		this.fquantity = fquantity;
	}

	public Integer getFitems() {
		return fitems;
	}

	public void setFitems(Integer fitems) {
		this.fitems = fitems;
	}

	public Double getFweight() {
		return weight;
	}

	public void setFweight(Double fweight) {
		this.fweight = fweight;
	}

	public Double getFlength() {
		return length;
	}

	public void setFlength(Double flength) {
		this.flength = flength;
	}

	public Double getFwidth() {
		return width;
	}

	public void setFwidth(Double fwidth) {
		this.fwidth = fwidth;
	}

	public Double getFheight() {
		return height;
	}

	public void setFheight(Double fheight) {
		this.fheight = fheight;
	}

	public String getFbattery() {
		return battery;
	}

	public void setFbattery(String fbattery) {
		this.fbattery = fbattery == null ? null : fbattery.trim();
	}

	public String getFspecial() {
		return special;
	}

	public void setFspecial(String fspecial) {
		this.fspecial = fspecial == null ? null : fspecial.trim();
	}

	public String getFstock() {
		return stock;
	}

	public void setFstock(String fstock) {
		this.fstock = fstock == null ? null : fstock.trim();
	}

	public String getForigin() {
		return origin;
	}

	public void setForigin(String forigin) {
		this.forigin = forigin == null ? null : forigin.trim();
	}

	public String getFfacturer() {
		return facturer;
	}

	public void setFfacturer(String ffacturer) {
		this.ffacturer = ffacturer == null ? null : ffacturer.trim();
	}

	public Integer getFnumber() {
		return number;
	}

	public void setFnumber(Integer fnumber) {
		this.fnumber = fnumber;
	}

	public String getFdepartment() {
		return department;
	}

	public void setFdepartment(String fdepartment) {
		this.fdepartment = fdepartment == null ? null : fdepartment.trim();
	}

	public String getFmaterial() {
		return material;
	}

	public void setFmaterial(String fmaterial) {
		this.fmaterial = fmaterial == null ? null : fmaterial.trim();
	}

	public String getFmetal() {
		return metal;
	}

	public void setFmetal(String fmetal) {
		this.fmetal = fmetal == null ? null : fmetal.trim();
	}

	public String getFgem() {
		return gem;
	}

	public void setFgem(String fgem) {
		this.fgem = fgem == null ? null : fgem.trim();
	}

	public String getFpackages() {
		return packages;
	}

	public void setFpackages(String fpackages) {
		this.fpackages = fpackages == null ? null : fpackages.trim();
	}

	public String getFhscode() {
		return hscode;
	}

	public void setFhscode(String fhscode) {
		this.fhscode = fhscode == null ? null : fhscode.trim();
	}

	public String getFenglish() {
		return english;
	}

	public void setFenglish(String fenglish) {
		this.fenglish = fenglish == null ? null : fenglish.trim();
	}

	public String getFchinese() {
		return chinese;
	}

	public void setFchinese(String fchinese) {
		this.fchinese = fchinese == null ? null : fchinese.trim();
	}

	public String getFsource() {
		return source;
	}

	public void setFsource(String fsource) {
		this.fsource = fsource == null ? null : fsource.trim();
	}

	public String getFmemo() {
		return memo;
	}

	public void setFmemo(String fmemo) {
		this.fmemo = fmemo == null ? null : fmemo.trim();
	}

	public String getFrs1() {
		return frs1;
	}

	public void setFrs1(String frs1) {
		this.frs1 = frs1 == null ? null : frs1.trim();
	}

	public String getFrs2() {
		return frs2;
	}

	public void setFrs2(String frs2) {
		this.frs2 = frs2 == null ? null : frs2.trim();
	}

	public String getFrs3() {
		return frs3;
	}

	public void setFrs3(String frs3) {
		this.frs3 = frs3 == null ? null : frs3.trim();
	}

	public String getFrs4() {
		return frs4;
	}

	public void setFrs4(String frs4) {
		this.frs4 = frs4 == null ? null : frs4.trim();
	}

	public String getFrs5() {
		return frs5;
	}

	public void setFrs5(String frs5) {
		this.frs5 = frs5 == null ? null : frs5.trim();
	}

	public String getFrs6() {
		return frs6;
	}

	public void setFrs6(String frs6) {
		this.frs6 = frs6 == null ? null : frs6.trim();
	}

	public String getFrs7() {
		return frs7;
	}

	public void setFrs7(String frs7) {
		this.frs7 = frs7 == null ? null : frs7.trim();
	}

	public String getFrs8() {
		return frs8;
	}

	public void setFrs8(String frs8) {
		this.frs8 = frs8 == null ? null : frs8.trim();
	}

	public String getFrs9() {
		return frs9;
	}

	public void setFrs9(String frs9) {
		this.frs9 = frs9 == null ? null : frs9.trim();
	}

	public Date getFrs10() {
		return frs10;
	}

	public void setFrs10(Date frs10) {
		this.frs10 = frs10;
	}

}