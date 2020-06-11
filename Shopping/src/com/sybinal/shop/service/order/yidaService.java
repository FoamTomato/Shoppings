package com.sybinal.shop.service.order;

import java.util.List;

import com.sybinal.shop.model.hjBase;
import com.sybinal.shop.model.ydBase;

public interface yidaService {

	int addOrder(ydBase base);

	List<ydBase> selectOrder(String string, String standby1, String getfIds);
	//修改为0
	void defaultHj0(String hjShipperhawbcode);
	//修改为1
	int default2(Integer valueOf);
	//获取默认的
	ydBase selectYd(String string);
	/**
	 * 添加义达跟踪号
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月7日 下午2:08:58   
	* @version
	 */
	int updateYd(ydBase base);
	/**
	 * 根据id查询单个
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月7日 下午5:43:03   
	* @version
	 */
	ydBase yidaSelectOne(ydBase base);
	/**
	 * 修改订单
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月7日 下午6:06:33   
	* @version
	 */
	int ydUpdateOrder(ydBase base);
	/**
	 * 删除义达
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月7日 下午6:25:27   
	* @version
	 */
	int ydDelOrder(ydBase base);
	/**
	 * 批量添加义达
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月9日 下午4:13:14   
	* @version
	 */
	int addOrders(List<ydBase> lists);
	/**
	 * 批量查询
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月9日 下午5:33:32   
	* @version
	 */
	List<ydBase> selectYds(List<String> list);
	/**
	 * 获取义达的参考号
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月10日 上午10:58:08   
	* @version
	 */
	List<String> selectIDList(List<String> list);
	/**
	 * 查询所有的义达
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月16日 下午7:52:50   
	* @version
	 */
	List<ydBase> selectAll();
	/**
	 * 根据id查询
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月17日 下午6:07:03   
	* @version
	 */
	List<ydBase> selectYdsId(List<Object> data);
	/**
	 * 批量修改yida的默认
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月18日 下午5:30:34   
	* @version
	 */
	int defaultHj0Plus(List<hjBase> hjList);

}
