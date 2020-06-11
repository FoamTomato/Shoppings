package com.sybinal.shop.common;

import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class imgTest extends Thread {
	String urlList;
	String imageNames;
	String fsku;
	private ThreadID var;
	public String result;
	SrcUrlImg srcUrlImg = new SrcUrlImg();
	// 计时器超时时间
	private long timeout;
	/*
	 * 计时是否被取消
	 */
	private boolean isCanceled = false;
	/*
	 * 当计时器超时时抛出的异常
	 */
	private TimeoutException timeoutException;
	private Logger log = LoggerFactory.getLogger(SrcUrlImg.class);

	/*
	 * 构造器
	 * 
	 * @param timeout 指定超时的时间
	 */
	public imgTest(String urlList, String imageNames, String fsku) {
		super();
		this.urlList = urlList;
		this.imageNames = imageNames;
		this.fsku = fsku;
		// 设置本线程为守护线程
		this.setDaemon(true);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			srcUrlImg.downloadPicture(urlList, imageNames, fsku);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("下载异常", imageNames);
			e.printStackTrace();
		}

	}
}
