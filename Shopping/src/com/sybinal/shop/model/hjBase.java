package com.sybinal.shop.model;

import java.util.Date;

public class hjBase {
    @Override
	public String toString() {
		return "hjBase [id=" + id + ", hjReferenceno=" + hjReferenceno + ", hjShipperhawbcode=" + hjShipperhawbcode
				+ ", hjShippingmethod=" + hjShippingmethod + ", hjCountrycode=" + hjCountrycode + ", hjShipzip="
				+ hjShipzip + ", hjOrderweight=" + hjOrderweight + ", hjOrderpieces=" + hjOrderpieces
				+ ", hjTotalprice=" + hjTotalprice + ", hjMailcargotype=" + hjMailcargotype + ", hjLength=" + hjLength
				+ ", hjWidth=" + hjWidth + ", hjHeight=" + hjHeight + ", hjIsreturn=" + hjIsreturn + ", hjIsbattery="
				+ hjIsbattery + ", hjConsignee=" + hjConsignee + ", hjConsigneecompany=" + hjConsigneecompany
				+ ", hjConsigneeprovince=" + hjConsigneeprovince + ", hjConsigneecity=" + hjConsigneecity
				+ ", hjConsigneestreet=" + hjConsigneestreet + ", hjConsigneestreet2=" + hjConsigneestreet2
				+ ", hjConsigneestreet3=" + hjConsigneestreet3 + ", hjConsigneepostcode=" + hjConsigneepostcode
				+ ", hjConsigneename=" + hjConsigneename + ", hjConsigneetelephone=" + hjConsigneetelephone
				+ ", hjConsigneemobile=" + hjConsigneemobile + ", hjConsigneeemail=" + hjConsigneeemail
				+ ", hjConsigneecountryename=" + hjConsigneecountryename + ", hjConsigneecountrycname="
				+ hjConsigneecountrycname + ", hjItemarr=" + hjItemarr + ", hjInvoiceenname=" + hjInvoiceenname
				+ ", hjInvoicecnname=" + hjInvoicecnname + ", hjInvoiceweight=" + hjInvoiceweight
				+ ", hjInvoicequantity=" + hjInvoicequantity + ", hjUnitcode=" + hjUnitcode + ", hjInvoiceunitcharge="
				+ hjInvoiceunitcharge + ", hjInvoicecurrencycode=" + hjInvoicecurrencycode + ", hjHscode=" + hjHscode
				+ ", hjInvoicenote=" + hjInvoicenote + ", hjSku=" + hjSku + ", hjIscontainsbattery="
				+ hjIscontainsbattery + ", hjIsaneroidmarkup=" + hjIsaneroidmarkup + ", hjIsonlybattery="
				+ hjIsonlybattery + ", hjIsbreakable=" + hjIsbreakable + ", hjIscharged=" + hjIscharged
				+ ", hjSaleprice=" + hjSaleprice + ", hjSalecurrency=" + hjSalecurrency + ", hjCategoryname="
				+ hjCategoryname + ", hjParentenname=" + hjParentenname + ", hjStandy1=" + hjStandy1 + ", hjStandy2="
				+ hjStandy2 + ", hjStandy3=" + hjStandy3 + ", hjStandy4=" + hjStandy4 + ", hjStandy5=" + hjStandy5
				+ ", hjStandy6=" + hjStandy6 + ", hjStandy7=" + hjStandy7 + ", hjStandy8=" + hjStandy8 + ", hjStandy9="
				+ hjStandy9 + ", hjStandy10=" + hjStandy10 + ", hjStandy11=" + hjStandy11 + ", hjStandy12=" + hjStandy12
				+ ", hjStandy13=" + hjStandy13 + ", hjStandy14=" + hjStandy14 + "]";
	}

	private Integer id;

    private String hjReferenceno;

    private String hjShipperhawbcode;

    private String hjShippingmethod;

    private String hjCountrycode;

    private String hjShipzip;

    private String hjOrderweight;

    private String hjOrderpieces;

    private String hjTotalprice;

    private String hjMailcargotype;

    private String hjLength;

    private String hjWidth;

    private String hjHeight;

    private String hjIsreturn;

    private String hjIsbattery;

    private String hjConsignee;

    private String hjConsigneecompany;

    private String hjConsigneeprovince;

    private String hjConsigneecity;

    private String hjConsigneestreet;

    private String hjConsigneestreet2;

    private String hjConsigneestreet3;

    private String hjConsigneepostcode;

    private String hjConsigneename;

    private String hjConsigneetelephone;

    private String hjConsigneemobile;

    private String hjConsigneeemail;

    private String hjConsigneecountryename;

    private String hjConsigneecountrycname;

    private String hjItemarr;

    private String hjInvoiceenname;

    private String hjInvoicecnname;

    private String hjInvoiceweight;

    private String hjInvoicequantity;

    private String hjUnitcode;

    private String hjInvoiceunitcharge;

    private String hjInvoicecurrencycode;

    private String hjHscode;

    private String hjInvoicenote;

    private String hjSku;

    private String hjIscontainsbattery;

    private String hjIsaneroidmarkup;

    private String hjIsonlybattery;

    private String hjIsbreakable;

    private String hjIscharged;

    private String hjSaleprice;

    private String hjSalecurrency;

    private String hjCategoryname;

    private String hjParentenname;

    private Date hjStandy1;

    public String getHjOrderweight() {
		return hjOrderweight;
	}

	public void setHjOrderweight(String hjOrderweight) {
		this.hjOrderweight = hjOrderweight;
	}

	public String getHjTotalprice() {
		return hjTotalprice;
	}

	public void setHjTotalprice(String hjTotalprice) {
		this.hjTotalprice = hjTotalprice;
	}

	public Date getHjStandy1() {
		return hjStandy1;
	}

	public void setHjStandy1(Date hjStandy1) {
		this.hjStandy1 = hjStandy1;
	}

	private String hjStandy2;

    private String hjStandy3;

    private String hjStandy4;

    private String hjStandy5;

    private String hjStandy6;

    private String hjStandy7;

    private String hjStandy8;

    private String hjStandy9;

    private String hjStandy10;

    private String hjStandy11;

    private String hjStandy12;

    private String hjStandy13;
    
    private String hjStandy14;

    public String getHjStandy14() {
		return hjStandy14;
	}

	public void setHjStandy14(String hjStandy14) {
		this.hjStandy14 = hjStandy14;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHjReferenceno() {
        return hjReferenceno;
    }

    public void setHjReferenceno(String hjReferenceno) {
        this.hjReferenceno = hjReferenceno == null ? null : hjReferenceno.trim();
    }

    public String getHjShipperhawbcode() {
        return hjShipperhawbcode;
    }

    public void setHjShipperhawbcode(String hjShipperhawbcode) {
        this.hjShipperhawbcode = hjShipperhawbcode == null ? null : hjShipperhawbcode.trim();
    }

    public String getHjShippingmethod() {
        return hjShippingmethod;
    }

    public void setHjShippingmethod(String hjShippingmethod) {
        this.hjShippingmethod = hjShippingmethod == null ? null : hjShippingmethod.trim();
    }

    public String getHjCountrycode() {
        return hjCountrycode;
    }

    public void setHjCountrycode(String hjCountrycode) {
        this.hjCountrycode = hjCountrycode == null ? null : hjCountrycode.trim();
    }

    public String getHjShipzip() {
        return hjShipzip;
    }

    public void setHjShipzip(String hjShipzip) {
        this.hjShipzip = hjShipzip == null ? null : hjShipzip.trim();
    }


    public String getHjOrderpieces() {
        return hjOrderpieces;
    }

    public void setHjOrderpieces(String hjOrderpieces) {
        this.hjOrderpieces = hjOrderpieces == null ? null : hjOrderpieces.trim();
    }


    public String getHjMailcargotype() {
        return hjMailcargotype;
    }

    public void setHjMailcargotype(String hjMailcargotype) {
        this.hjMailcargotype = hjMailcargotype == null ? null : hjMailcargotype.trim();
    }

    public String getHjLength() {
        return hjLength;
    }

    public void setHjLength(String hjLength) {
        this.hjLength = hjLength;
    }

    public String getHjWidth() {
        return hjWidth;
    }

    public void setHjWidth(String hjWidth) {
        this.hjWidth = hjWidth;
    }

    public String getHjHeight() {
        return hjHeight;
    }

    public void setHjHeight(String hjHeight) {
        this.hjHeight = hjHeight;
    }

    public String getHjIsreturn() {
        return hjIsreturn;
    }

    public void setHjIsreturn(String hjIsreturn) {
        this.hjIsreturn = hjIsreturn == null ? null : hjIsreturn.trim();
    }

    public String getHjIsbattery() {
        return hjIsbattery;
    }

    public void setHjIsbattery(String hjIsbattery) {
        this.hjIsbattery = hjIsbattery == null ? null : hjIsbattery.trim();
    }

    public String getHjConsignee() {
        return hjConsignee;
    }

    public void setHjConsignee(String hjConsignee) {
        this.hjConsignee = hjConsignee == null ? null : hjConsignee.trim();
    }

    public String getHjConsigneecompany() {
        return hjConsigneecompany;
    }

    public void setHjConsigneecompany(String hjConsigneecompany) {
        this.hjConsigneecompany = hjConsigneecompany == null ? null : hjConsigneecompany.trim();
    }

    public String getHjConsigneeprovince() {
        return hjConsigneeprovince;
    }

    public void setHjConsigneeprovince(String hjConsigneeprovince) {
        this.hjConsigneeprovince = hjConsigneeprovince == null ? null : hjConsigneeprovince.trim();
    }

    public String getHjConsigneecity() {
        return hjConsigneecity;
    }

    public void setHjConsigneecity(String hjConsigneecity) {
        this.hjConsigneecity = hjConsigneecity == null ? null : hjConsigneecity.trim();
    }

    public String getHjConsigneestreet() {
        return hjConsigneestreet;
    }

    public void setHjConsigneestreet(String hjConsigneestreet) {
        this.hjConsigneestreet = hjConsigneestreet == null ? null : hjConsigneestreet.trim();
    }

    public String getHjConsigneestreet2() {
        return hjConsigneestreet2;
    }

    public void setHjConsigneestreet2(String hjConsigneestreet2) {
        this.hjConsigneestreet2 = hjConsigneestreet2 == null ? null : hjConsigneestreet2.trim();
    }

    public String getHjConsigneestreet3() {
        return hjConsigneestreet3;
    }

    public void setHjConsigneestreet3(String hjConsigneestreet3) {
        this.hjConsigneestreet3 = hjConsigneestreet3 == null ? null : hjConsigneestreet3.trim();
    }

    public String getHjConsigneepostcode() {
        return hjConsigneepostcode;
    }

    public void setHjConsigneepostcode(String hjConsigneepostcode) {
        this.hjConsigneepostcode = hjConsigneepostcode == null ? null : hjConsigneepostcode.trim();
    }

    public String getHjConsigneename() {
        return hjConsigneename;
    }

    public void setHjConsigneename(String hjConsigneename) {
        this.hjConsigneename = hjConsigneename == null ? null : hjConsigneename.trim();
    }

    public String getHjConsigneetelephone() {
        return hjConsigneetelephone;
    }

    public void setHjConsigneetelephone(String hjConsigneetelephone) {
        this.hjConsigneetelephone = hjConsigneetelephone == null ? null : hjConsigneetelephone.trim();
    }

    public String getHjConsigneemobile() {
        return hjConsigneemobile;
    }

    public void setHjConsigneemobile(String hjConsigneemobile) {
        this.hjConsigneemobile = hjConsigneemobile == null ? null : hjConsigneemobile.trim();
    }

    public String getHjConsigneeemail() {
        return hjConsigneeemail;
    }

    public void setHjConsigneeemail(String hjConsigneeemail) {
        this.hjConsigneeemail = hjConsigneeemail == null ? null : hjConsigneeemail.trim();
    }

    public String getHjConsigneecountryename() {
        return hjConsigneecountryename;
    }

    public void setHjConsigneecountryename(String hjConsigneecountryename) {
        this.hjConsigneecountryename = hjConsigneecountryename == null ? null : hjConsigneecountryename.trim();
    }

    public String getHjConsigneecountrycname() {
        return hjConsigneecountrycname;
    }

    public void setHjConsigneecountrycname(String hjConsigneecountrycname) {
        this.hjConsigneecountrycname = hjConsigneecountrycname == null ? null : hjConsigneecountrycname.trim();
    }

    public String getHjItemarr() {
        return hjItemarr;
    }

    public void setHjItemarr(String hjItemarr) {
        this.hjItemarr = hjItemarr == null ? null : hjItemarr.trim();
    }

    public String getHjInvoiceenname() {
        return hjInvoiceenname;
    }

    public void setHjInvoiceenname(String hjInvoiceenname) {
        this.hjInvoiceenname = hjInvoiceenname == null ? null : hjInvoiceenname.trim();
    }

    public String getHjInvoicecnname() {
        return hjInvoicecnname;
    }

    public void setHjInvoicecnname(String hjInvoicecnname) {
        this.hjInvoicecnname = hjInvoicecnname == null ? null : hjInvoicecnname.trim();
    }

    public String getHjInvoiceweight() {
        return hjInvoiceweight;
    }

    public void setHjInvoiceweight(String hjInvoiceweight) {
        this.hjInvoiceweight = hjInvoiceweight;
    }

    public String getHjInvoicequantity() {
        return hjInvoicequantity;
    }

    public void setHjInvoicequantity(String hjInvoicequantity) {
        this.hjInvoicequantity = hjInvoicequantity == null ? null : hjInvoicequantity.trim();
    }

    public String getHjUnitcode() {
        return hjUnitcode;
    }

    public void setHjUnitcode(String hjUnitcode) {
        this.hjUnitcode = hjUnitcode == null ? null : hjUnitcode.trim();
    }

    public String getHjInvoiceunitcharge() {
        return hjInvoiceunitcharge;
    }

    public void setHjInvoiceunitcharge(String hjInvoiceunitcharge) {
        this.hjInvoiceunitcharge = hjInvoiceunitcharge;
    }

    public String getHjInvoicecurrencycode() {
        return hjInvoicecurrencycode;
    }

    public void setHjInvoicecurrencycode(String hjInvoicecurrencycode) {
        this.hjInvoicecurrencycode = hjInvoicecurrencycode == null ? null : hjInvoicecurrencycode.trim();
    }

    public String getHjHscode() {
        return hjHscode;
    }

    public void setHjHscode(String hjHscode) {
        this.hjHscode = hjHscode == null ? null : hjHscode.trim();
    }

    public String getHjInvoicenote() {
        return hjInvoicenote;
    }

    public void setHjInvoicenote(String hjInvoicenote) {
        this.hjInvoicenote = hjInvoicenote == null ? null : hjInvoicenote.trim();
    }

    public String getHjSku() {
        return hjSku;
    }

    public void setHjSku(String hjSku) {
        this.hjSku = hjSku == null ? null : hjSku.trim();
    }

    public String getHjIscontainsbattery() {
        return hjIscontainsbattery;
    }

    public void setHjIscontainsbattery(String hjIscontainsbattery) {
        this.hjIscontainsbattery = hjIscontainsbattery == null ? null : hjIscontainsbattery.trim();
    }

    public String getHjIsaneroidmarkup() {
        return hjIsaneroidmarkup;
    }

    public void setHjIsaneroidmarkup(String hjIsaneroidmarkup) {
        this.hjIsaneroidmarkup = hjIsaneroidmarkup == null ? null : hjIsaneroidmarkup.trim();
    }

    public String getHjIsonlybattery() {
        return hjIsonlybattery;
    }

    public void setHjIsonlybattery(String hjIsonlybattery) {
        this.hjIsonlybattery = hjIsonlybattery == null ? null : hjIsonlybattery.trim();
    }

    public String getHjIsbreakable() {
        return hjIsbreakable;
    }

    public void setHjIsbreakable(String hjIsbreakable) {
        this.hjIsbreakable = hjIsbreakable == null ? null : hjIsbreakable.trim();
    }

    public String getHjIscharged() {
        return hjIscharged;
    }

    public void setHjIscharged(String hjIscharged) {
        this.hjIscharged = hjIscharged == null ? null : hjIscharged.trim();
    }

    public String getHjSaleprice() {
        return hjSaleprice;
    }

    public void setHjSaleprice(String hjSaleprice) {
        this.hjSaleprice = hjSaleprice;
    }

    public String getHjSalecurrency() {
        return hjSalecurrency;
    }

    public void setHjSalecurrency(String hjSalecurrency) {
        this.hjSalecurrency = hjSalecurrency == null ? null : hjSalecurrency.trim();
    }

    public String getHjCategoryname() {
        return hjCategoryname;
    }

    public void setHjCategoryname(String hjCategoryname) {
        this.hjCategoryname = hjCategoryname == null ? null : hjCategoryname.trim();
    }

    public String getHjParentenname() {
        return hjParentenname;
    }

    public void setHjParentenname(String hjParentenname) {
        this.hjParentenname = hjParentenname == null ? null : hjParentenname.trim();
    }


    public String getHjStandy2() {
        return hjStandy2;
    }

    public void setHjStandy2(String hjStandy2) {
        this.hjStandy2 = hjStandy2 == null ? null : hjStandy2.trim();
    }

    public String getHjStandy3() {
        return hjStandy3;
    }

    public void setHjStandy3(String hjStandy3) {
        this.hjStandy3 = hjStandy3 == null ? null : hjStandy3.trim();
    }

    public String getHjStandy4() {
        return hjStandy4;
    }

    public void setHjStandy4(String hjStandy4) {
        this.hjStandy4 = hjStandy4 == null ? null : hjStandy4.trim();
    }

    public String getHjStandy5() {
        return hjStandy5;
    }

    public void setHjStandy5(String hjStandy5) {
        this.hjStandy5 = hjStandy5 == null ? null : hjStandy5.trim();
    }

    public String getHjStandy6() {
        return hjStandy6;
    }

    public void setHjStandy6(String hjStandy6) {
        this.hjStandy6 = hjStandy6 == null ? null : hjStandy6.trim();
    }

    public String getHjStandy7() {
        return hjStandy7;
    }

    public void setHjStandy7(String hjStandy7) {
        this.hjStandy7 = hjStandy7 == null ? null : hjStandy7.trim();
    }

    public String getHjStandy8() {
        return hjStandy8;
    }

    public void setHjStandy8(String hjStandy8) {
        this.hjStandy8 = hjStandy8 == null ? null : hjStandy8.trim();
    }

    public String getHjStandy9() {
        return hjStandy9;
    }

    public void setHjStandy9(String hjStandy9) {
        this.hjStandy9 = hjStandy9 == null ? null : hjStandy9.trim();
    }

    public String getHjStandy10() {
        return hjStandy10;
    }

    public void setHjStandy10(String hjStandy10) {
        this.hjStandy10 = hjStandy10 == null ? null : hjStandy10.trim();
    }

    public String getHjStandy11() {
        return hjStandy11;
    }

    public void setHjStandy11(String hjStandy11) {
        this.hjStandy11 = hjStandy11 == null ? null : hjStandy11.trim();
    }

    public String getHjStandy12() {
        return hjStandy12;
    }

    public void setHjStandy12(String hjStandy12) {
        this.hjStandy12 = hjStandy12 == null ? null : hjStandy12.trim();
    }

    public String getHjStandy13() {
        return hjStandy13;
    }

    public void setHjStandy13(String hjStandy13) {
        this.hjStandy13 = hjStandy13 == null ? null : hjStandy13.trim();
    }
}