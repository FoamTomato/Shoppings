package com.sybinal.shop.common;

class ThreadID extends ThreadLocal {
	private int nextID;

	public ThreadID() {
		nextID = 10001;
	}

	private synchronized Integer getNewID() {
		Integer id = new Integer(nextID);
		nextID++;
		return id;
	}

	@Override
	protected Object initialValue() {
		print("in initialValue()");
		return getNewID();
	}

	public int getThreadID() {
		Integer id = (Integer) get();
		return id.intValue();
	}

	private static void print(String msg) {
		String name = Thread.currentThread().getName();
		System.out.println(name + ": " + msg);
	}
}