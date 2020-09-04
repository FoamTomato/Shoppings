package com.sybinal.shop.model;

public class Result {
	
	private Object msg;
	
	private int statue;

	public Object getMsg() {
		return msg;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}

	public int getStatue() {
		return statue;
	}

	public void setStatue(int statue) {
		this.statue = statue;
	}

	@Override
	public String toString() {
		return "Result [msg=" + msg + ", statue=" + statue + "]";
	}

	public Result(Object msg, int statue) {
		super();
		this.msg = msg;
		this.statue = statue;
	}

	public Result() {
		super();
	}
	
	

}
