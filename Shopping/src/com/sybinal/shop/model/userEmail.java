package com.sybinal.shop.model;

public class userEmail {
    private Integer id;

    private String userEmail;

    private String pwdEmail;

    private String types;

    private String state;

    private String spare1;

    private String spare2;

    private String spare3;

    private String spare4;

    private String spare5;

    private String spare6;

    private String spare7;

    private String spare8;

    private String spare9;

    private String spare10;

    private String port1;

    private String port2;

    
    public String getSpare7() {
		return spare7;
	}

	public void setSpare7(String spare7) {
		this.spare7 = spare7;
	}

	public String getSpare8() {
		return spare8;
	}

	public void setSpare8(String spare8) {
		this.spare8 = spare8;
	}

	public String getSpare9() {
		return spare9;
	}

	public void setSpare9(String spare9) {
		this.spare9 = spare9;
	}

	public String getSpare10() {
		return spare10;
	}

	public void setSpare10(String spare10) {
		this.spare10 = spare10;
	}

	public String getPort1() {
		return port1;
	}

	public void setPort1(String port1) {
		this.port1 = port1;
	}

	public String getPort2() {
		return port2;
	}

	public void setPort2(String port2) {
		this.port2 = port2;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getPwdEmail() {
        return pwdEmail;
    }

    public void setPwdEmail(String pwdEmail) {
        this.pwdEmail = pwdEmail == null ? null : pwdEmail.trim();
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types == null ? null : types.trim();
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

	@Override
	public String toString() {
		return "userEmail [id=" + id + ", userEmail=" + userEmail + ", pwdEmail=" + pwdEmail + ", types=" + types
				+ ", state=" + state + ", spare1=" + spare1 + ", spare2=" + spare2 + ", spare3=" + spare3 + ", spare4="
				+ spare4 + ", spare5=" + spare5 + ", spare6=" + spare6 + ", spare7=" + spare7 + ", spare8=" + spare8
				+ ", spare9=" + spare9 + ", spare10=" + spare10 + ", port1=" + port1 + ", port2=" + port2 + "]";
	}
    
}