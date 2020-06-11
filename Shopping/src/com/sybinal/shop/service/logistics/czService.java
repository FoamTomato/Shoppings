package com.sybinal.shop.service.logistics;

import java.util.List;

import com.sybinal.shop.model.czBase;
import com.sybinal.shop.model.czBaseWithBLOBs;
import com.sybinal.shop.model.hjBase;

public interface czService {
	/**
	 * 根据id查询
	 */
	czBase SelectAll(czBase hjbases);
	/**
	 * 添加创志
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月31日 下午6:24:24   
	* @version
	 */
	int addOrder(List<czBaseWithBLOBs> czbase);
	/**
	 * 批量清除默认
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月1日 下午4:38:15   
	* @version
	 */
	int defaultHj0Plus(List<hjBase> hjbasee);
	/**
	 * 
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月1日 下午5:01:28   
	* @version
	 */
	List<czBaseWithBLOBs> selectIDList(List<String> list);
	/**
	 * 批量添加创志跟踪号
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月1日 下午7:31:40   
	* @version
	 */
	int updateCz(czBase base);
	/**
	 * 查询所有的未删运单
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月1日 下午7:52:46   
	* @version
	 */
	List<czBase> selectOrder(String string, String standby1);
	
	/**
	 * 创志选为默认
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月1日 下午8:12:14   
	* @version
	 */
	int default2cz(czBase cse);
	/**
	 * 创志修改
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月2日 上午10:58:38   
	* @version
	 */
	int updateOrder(czBaseWithBLOBs base2);
	/**
	 * 创志删除
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月2日 下午4:45:13   
	* @version
	 */
	int dels(czBase czbase);
	/**
	 * 根据id查找
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月2日 下午5:11:18   
	* @version
	 */
	czBase selectID(Integer id);

}
