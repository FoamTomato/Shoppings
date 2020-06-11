package com.sybinal.shop.controller.admin;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sybinal.shop.common.AjaxResult;
import com.sybinal.shop.common.ApiResponseEnum;
import com.sybinal.shop.common.ApiResponseObject;
import com.sybinal.shop.common.SrcUrlImg;
import com.sybinal.shop.common.imgTest;
import com.sybinal.shop.controller.api.AbstractApiController;
import com.sybinal.shop.model.OnlineDownload;
import com.sybinal.shop.model.OnlineDownloadWithBLOBs;
import com.sybinal.shop.service.onlineDown.OnlineDownService;

@RestController
@RequestMapping("api/v1")
public class DownImages extends AbstractApiController {

	@Autowired
	OnlineDownService onlineDownService;
	private Logger log = LoggerFactory.getLogger(DownImages.class);

	// 所有图片下载
	@RequestMapping(value = "run/image", method = RequestMethod.POST, headers = "Accept=application/json")
	public ApiResponseObject receiveMain(@RequestBody OnlineDownload onli) {
		List<OnlineDownloadWithBLOBs> DownloadWithBLOBs = onlineDownService.selectALLImages();
		OnlineDownloadWithBLOBs Onlines = new OnlineDownloadWithBLOBs();
		SrcUrlImg img = new SrcUrlImg();
		ExecutorService newCachedThreadPool = Executors.newFixedThreadPool(300);
		for (int c = 0; c < DownloadWithBLOBs.size(); c++) {
			System.out.println("第" + c + "条====" + DownloadWithBLOBs.get(c).getfImagesUrl());
			int threadCount = ((ThreadPoolExecutor) newCachedThreadPool).getActiveCount();

			System.out.println("1.threadCount====" + threadCount);

			if (threadCount >= 300) {
				System.out.println("线程可能会卡住,休眠3秒");
				try {

					Thread.sleep(3000);
					if (threadCount >= 200) {
						System.out.println("线程过多,休眠3秒");

						Thread.sleep(3000);
						if (threadCount >= 100) {
							System.out.println("网络不给力,再休眠2秒");
							Thread.sleep(2000);
						}
					}
				} catch (InterruptedException e) { // TODO Auto-generated catch block
					System.out.println("休眠失败==" + e);
					e.printStackTrace();
				}
			} else {

				System.out.println("添加一条线程");
				newCachedThreadPool.submit(new imgTest(DownloadWithBLOBs.get(c).getfOnlineUrl(),
						DownloadWithBLOBs.get(c).getfImagesUrl(), DownloadWithBLOBs.get(c).getfSku()));
			}

			// img.downloadPicture(DownloadWithBLOBs.get(c).getfOnlineUrl(),
			// DownloadWithBLOBs.get(c).getfImagesUrl());
		}
		newCachedThreadPool.shutdown();
		return this.reponseJSON(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(),
				new AjaxResult(true));
	}

	// 从sku多少以后开始下载
	@RequestMapping(value = "run/images/sku", method = RequestMethod.POST, headers = "Accept=application/json")
	public ApiResponseObject imagesSkuDownload(@RequestBody OnlineDownload onli) {
		System.out.println(onli);
		List<OnlineDownloadWithBLOBs> DownloadWithBLOBs = onlineDownService.selectByImagesTolast(onli.getfSku());
		/*
		 * for (int i = 0; i < DownloadWithBLOBs.size(); i++) { System.out.println("获取的"
		 * + DownloadWithBLOBs.get(i)); }
		 */

		OnlineDownloadWithBLOBs Onlines = new OnlineDownloadWithBLOBs();
		SrcUrlImg img = new SrcUrlImg();
		ExecutorService newCachedThreadPool = Executors.newFixedThreadPool(300);
		for (int c = 0; c < DownloadWithBLOBs.size(); c++) {
			System.out.println("第" + c + "条====" + DownloadWithBLOBs.get(c).getfImagesUrl());
			int threadCount = ((ThreadPoolExecutor) newCachedThreadPool).getActiveCount();

			System.out.println("1.threadCount====" + threadCount);

			if (threadCount >= 300) {
				System.out.println("线程可能会卡住,休眠3秒");
				try {

					Thread.sleep(3000);
					if (threadCount >= 200) {
						System.out.println("线程过多,休眠3秒");

						Thread.sleep(3000);
						if (threadCount >= 100) {
							System.out.println("网络不给力,再休眠2秒");
							Thread.sleep(2000);
						}
					}
				} catch (InterruptedException e) { // TODO Auto-generated catch block
					System.out.println("休眠失败==" + e);
					e.printStackTrace();
				}
			} else {

				System.out.println("添加一条线程");
				newCachedThreadPool.submit(new imgTest(DownloadWithBLOBs.get(c).getfOnlineUrl(),
						DownloadWithBLOBs.get(c).getfImagesUrl(), DownloadWithBLOBs.get(c).getfSku()));
			}

			// img.downloadPicture(DownloadWithBLOBs.get(c).getfOnlineUrl(),
			// DownloadWithBLOBs.get(c).getfImagesUrl());

			// img.downloadPicture(DownloadWithBLOBs.get(c).getfOnlineUrl(),
			// DownloadWithBLOBs.get(c).getfImagesUrl());
		}
		newCachedThreadPool.shutdown();
		return this.reponseJSON(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(),
				new AjaxResult(true));
	}

	// 下载当前sku
	@RequestMapping(value = "run/imageBy/sku", method = RequestMethod.POST, headers = "Accept=application/json")
	public ApiResponseObject selectList(@RequestBody OnlineDownload onli) {
		System.out.println(onli);
		List<OnlineDownloadWithBLOBs> DownloadWithBLOBs = onlineDownService.select(onli.getfSku());
		/*
		 * for (int i = 0; i < DownloadWithBLOBs.size(); i++) { System.out.println("获取的"
		 * + DownloadWithBLOBs.get(i)); }
		 */

		OnlineDownloadWithBLOBs Onlines = new OnlineDownloadWithBLOBs();
		SrcUrlImg img = new SrcUrlImg();
		ExecutorService newCachedThreadPool = Executors.newFixedThreadPool(50);
		for (int c = 0; c < DownloadWithBLOBs.size(); c++) {
			System.out.println(
					"正在下载" + DownloadWithBLOBs.get(c).getfSku() + "====" + DownloadWithBLOBs.get(c).getfImagesUrl());
			int threadCount = ((ThreadPoolExecutor) newCachedThreadPool).getActiveCount();

			System.out.println("1.threadCount====" + threadCount);
			if (threadCount >= 50) {
				System.out.println("线程已满,休眠3秒");
				try {
					Thread.sleep(3000);
					newCachedThreadPool.submit(new imgTest(DownloadWithBLOBs.get(c).getfOnlineUrl(),
							DownloadWithBLOBs.get(c).getfImagesUrl(), DownloadWithBLOBs.get(c).getfSku()));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("休眠失败==" + e);
					e.printStackTrace();
				}
			} else {
				System.out.println("添加一条线程");
				newCachedThreadPool.submit(new imgTest(DownloadWithBLOBs.get(c).getfOnlineUrl(),
						DownloadWithBLOBs.get(c).getfImagesUrl(), DownloadWithBLOBs.get(c).getfSku()));
			}

			// img.downloadPicture(DownloadWithBLOBs.get(c).getfOnlineUrl(),
			// DownloadWithBLOBs.get(c).getfImagesUrl());
		}
		newCachedThreadPool.shutdown();
		return this.reponseJSON(ApiResponseEnum.SUCCESS.getCode(), ApiResponseEnum.SUCCESS.getName(),
				new AjaxResult(true));
	}
}
