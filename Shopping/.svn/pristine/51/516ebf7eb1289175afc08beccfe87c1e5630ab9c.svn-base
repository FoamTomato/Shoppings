package com.sybinal.shop.common;

import java.util.concurrent.CountDownLatch;

public class TaskPDF implements Runnable {
	private String string,path,uuid;
	private CountDownLatch countDownLatch;
	public TaskPDF(String lists, String path, String uuid, CountDownLatch lat) {
		super();
		this.string = lists;
		this.path = path;
		this.countDownLatch=lat;
		this.uuid = uuid;
	}

	@Override
	public void run() {
		SrcUrlImg src=new SrcUrlImg();
		try {
			/**
			 * 闭锁同步
			 */
			synchronized (countDownLatch) {
                /**
                 * 每次减少一个容量*/
				src.downloadPDF(path, uuid, string);
                countDownLatch.countDown();
            }
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}