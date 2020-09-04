package com.sybinal.shop.service.catalog;

import java.util.List;
import java.util.Map;

import com.sybinal.shop.common.AjaxResult;
import com.sybinal.shop.model.DTfzyings;
import com.sybinal.shop.model.DTfzyingsWithBLOBs;

public interface DTfzyingsService {
	List<DTfzyingsWithBLOBs> selectAll();

	AjaxResult setinsert(DTfzyingsWithBLOBs dtfzing);
	/**
	 * 查询sku
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月26日 下午3:41:17   
	* @version
	 */
	DTfzyingsWithBLOBs selectByid(Integer id);

	boolean setinsertY(DTfzyingsWithBLOBs dtfzing);

	int updateProduct(DTfzyingsWithBLOBs dTfzying);

	int deleteProduct(Integer id);
	/**
	 * 查询产品
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月26日 上午10:50:21   
	* @version
	 */
	List<DTfzyingsWithBLOBs> selectdTstockAtAll(DTfzyings dTfzying, String fen);
	/**
	 * 批量删除
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月8日 下午4:13:14   
	* @version
	 */
	int deleteProductList(List<String> map);
	//根据id查询
	List<DTfzyingsWithBLOBs> selectBYid(List<Integer> list);

	//数据库根据id条件查询产品
	List<DTfzyingsWithBLOBs> selectByListId(List<Integer> list, Map<String, Object> map);
	//根据分类查询
	List<DTfzyingsWithBLOBs> selectByCate(Map<String, Object> map);
}
