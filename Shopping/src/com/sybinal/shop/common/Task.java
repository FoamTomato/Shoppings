package com.sybinal.shop.common;

import java.util.concurrent.CountDownLatch;

public class Task implements Runnable {
	private String string,path,uuid;
	private CountDownLatch countDownLatch;
	public Task(String string, String path, String uuid, CountDownLatch lat) {
		super();
		this.string = string;
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
				src.downloadPictures(path, uuid, string);
                countDownLatch.countDown();
            }
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}