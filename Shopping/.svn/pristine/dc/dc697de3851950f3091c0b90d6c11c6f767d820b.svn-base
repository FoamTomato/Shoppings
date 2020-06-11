package com.sybinal.shop.mapper;

import java.util.List;
import java.util.TreeSet;

import org.apache.ibatis.annotations.Param;

import com.sybinal.shop.model.OnlineDownload;
import com.sybinal.shop.model.OnlineDownloadWithBLOBs;

public interface OnlineDownloadMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(OnlineDownloadWithBLOBs record);

	int insertSelective(OnlineDownloadWithBLOBs record);

	OnlineDownloadWithBLOBs selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(OnlineDownloadWithBLOBs record);

	int updateByPrimaryKeyWithBLOBs(OnlineDownloadWithBLOBs record);

	int updateByPrimaryKey(OnlineDownload record);

	List<OnlineDownloadWithBLOBs> selectALLImages();
	
	List<OnlineDownloadWithBLOBs> selectByImagesTolast(@Param("sku") String getfSku);

	List<OnlineDownloadWithBLOBs> select(@Param("sku") String getfSku);

	List<OnlineDownloadWithBLOBs> ImagesUnique(@Param("sku") String getfSku);

	List<OnlineDownloadWithBLOBs> ImageLocal(@Param("f_online_url") String online);

	int delete(@Param("ids")List<String> online);

	List<OnlineDownloadWithBLOBs> selectOnline(@Param("online")List<OnlineDownloadWithBLOBs> online);

	List<OnlineDownloadWithBLOBs> selectlo(@Param("getfOnlineUrl") String getfOnlineUrl, @Param("getfSku") String getfSku);

	/**  
	 * @user: spor-wen10
	 * @Title: insertall  
	 * @Description: TODO 批量添加主图
	 * @date: 2020年2月15日 下午4:46:45
	 * @param: @param t
	 * @param: @param fsku    参数  
	 * @return: void    返回类型  
	 * @throws  
	 */
	void insertall(@Param("tree")TreeSet<String> t,@Param("string")String string);
}