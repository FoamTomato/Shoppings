package com.sybinal.shop.service.onlineDown;

import java.util.List;

import com.sybinal.shop.model.OnlineDownloadWithBLOBs;

public interface OnlineDownService {
	/*
	 * 插入图片
	 */
	int getAndset(OnlineDownloadWithBLOBs onlineDownload);

	// 查询所有的图片
	List<OnlineDownloadWithBLOBs> selectALLImages();

	// 根据sku开始查询
	List<OnlineDownloadWithBLOBs> selectByImagesTolast(String getfSku);

	// 查询该sku的图片
	List<OnlineDownloadWithBLOBs> select(String getfSku);

	// 获取唯一的在线图片 
	List<OnlineDownloadWithBLOBs> ImagesUnique(String getfSku);

	/*
	 * 查询图片单个
	 */
	List<OnlineDownloadWithBLOBs> ImageLocal(String online);

	int delet(List<String> online);

	List<OnlineDownloadWithBLOBs> selectOnline(List<OnlineDownloadWithBLOBs> online);


	List<OnlineDownloadWithBLOBs> selectlo(String getfOnlineUrl, String getfSku);

}
