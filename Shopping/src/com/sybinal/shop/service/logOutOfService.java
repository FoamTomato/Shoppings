package com.sybinal.shop.service;

import java.util.List;

import com.sybinal.shop.model.Astrict;
import com.sybinal.shop.model.ExcelExport;
import com.sybinal.shop.model.logOutOf;

public interface logOutOfService {
	/**
	 * 查询所有的物流方式
	 */
	List <logOutOf> selectAll();
	/**
	 * 物流添加的main函数
	 * @param shorts 简称
	 * @param name 名称
	 * @param logisticname 集拼名称
	 * @return
	 */
	int addlogistic(String shorts, String name, String logisticname);
	/**
	 * @param listsew 
	 * @param string3 拦截商品英文名称
	 * @param string2    拦截商品中文名称
	 * @user: spor-wen10
	 * @Title: namese  
	 * @Description: TODO  
	 * @date: 2020年2月25日 下午7:09:12
	 * @param: @param string 拦截收件人名称
	 * @param: @return    参数  
	 * @return: String    返回类型  
	 * @throws  
	 */
	String namese(List<Astrict> listsew, String string, String string2, String string3);
	/**
	 * 添加商品
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月14日 下午5:23:50   
	* @version
	 */
	int adds(logOutOf logof);
	/**
	 * 删除渠道
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月14日 下午5:42:41   
	* @version
	 */
	int deleteChannel(List<logOutOf> list);
	/**
	 * 修改渠道
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月14日 下午6:41:43   
	* @version
	 */
	int updates(logOutOf logof);
	/**
	 * 查找所有
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月14日 下午6:46:21   
	* @version
	 */
	List<logOutOf> selectAlls();
	/**
	 * 
	* @Title Shopping   
	* @Package com.sybinal.shop.service
	* @Description: TODO 查找导出
	* @author PC1  
	* @date 2021年3月1日 下午2:57:56   
	* @version V1.0
	 */
	List<ExcelExport> selectExports();
	/**
	 * 
	* @Title Shopping   
	* @Package com.sybinal.shop.service
	* @Description: TODO 添加导出分类
	* @author PC1  
	* @date 2021年3月1日 下午3:07:46   
	* @version V1.0
	 */
	int addExpory(ExcelExport logof);
	/**
	 * 
	* @Title Shopping   
	* @Package com.sybinal.shop.service
	* @Description: TODO 修改导出分类
	* @author PC1  
	* @date 2021年3月1日 下午3:07:46   
	* @version V1.0
	 */
	int updateExport(ExcelExport logof);
	/**
	 * 
	* @Title Shopping   
	* @Package com.sybinal.shop.service
	* @Description: TODO 批量删除
	* @author PC1  
	* @date 2021年3月1日 下午3:11:20   
	* @version V1.0
	 */
	int deleteExport(List<ExcelExport> list);
	
}
