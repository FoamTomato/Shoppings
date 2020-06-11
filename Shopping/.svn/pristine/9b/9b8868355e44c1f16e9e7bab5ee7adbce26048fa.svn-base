package com.sybinal.shop.service.onlineDown;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sybinal.shop.mapper.OnlineDownloadMapper;
import com.sybinal.shop.model.OnlineDownloadWithBLOBs;

@Service
public class OnlineDownServiceImpl implements OnlineDownService {
	@Autowired
	OnlineDownloadMapper onlineDownloadMapper;
	/*
	 * 插入图片
	 * (non-Javadoc)
	 * @see com.sybinal.shop.service.onlineDown.OnlineDownService#getAndset(com.sybinal.shop.model.OnlineDownloadWithBLOBs)
	 */
	@Override
	public int getAndset(OnlineDownloadWithBLOBs onlineDownload) {
		return onlineDownloadMapper.insertSelective(onlineDownload);
	}

	@Override
	public List<OnlineDownloadWithBLOBs> selectALLImages() {
		// TODO Auto-generated method stub
		return onlineDownloadMapper.selectALLImages();
	}

	@Override
	public List<OnlineDownloadWithBLOBs> selectByImagesTolast(String getfSku) {
		// TODO Auto-generated method stub
		return onlineDownloadMapper.selectByImagesTolast(getfSku);
	}

	@Override
	public List<OnlineDownloadWithBLOBs> select(String getfSku) {
		// TODO Auto-generated method stub
		return onlineDownloadMapper.select(getfSku);
	}

	@Override
	public List<OnlineDownloadWithBLOBs> ImagesUnique(String getfSku) {
		// TODO Auto-generated method stub
		return onlineDownloadMapper.ImagesUnique(getfSku);
	}

	@Override
	public List<OnlineDownloadWithBLOBs> ImageLocal(String online) {
		// TODO Auto-generated method stub
		return onlineDownloadMapper.ImageLocal(online);
	}

	@Override
	public int delet(List<String> online) {
		// TODO Auto-generated method stub
		return onlineDownloadMapper.delete(online);
	}

	@Override
	public List<OnlineDownloadWithBLOBs> selectOnline(List<OnlineDownloadWithBLOBs> online) {
		// TODO Auto-generated method stub
		return onlineDownloadMapper.selectOnline(online);
	}

	@Override
	public List<OnlineDownloadWithBLOBs> selectlo(String getfOnlineUrl, String getfSku) {
		// TODO Auto-generated method stub
		return onlineDownloadMapper.selectlo(getfOnlineUrl,getfSku);
	}

	
}
