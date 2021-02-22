package com.sybinal.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sybinal.shop.model.hjBase;
import com.sybinal.shop.model.ydBase;

public interface ydBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ydBase record);

    int insertSelective(ydBase record);

    ydBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ydBase record);

    int updateByPrimaryKey(ydBase record);
    //查找义达订单
	List<ydBase> selectOrder(@Param("id")String id, @Param("standby1")String standby1, @Param("getfIds")String getfIds);
	//修改默认未0
	/*void defaultHj0(@Param("ids")String hjShipperhawbcode);*/
	//修改默认未1
	int default2(@Param("id")Integer valueOf);
	//查找默认订单
	ydBase selectYd(@Param("strings")String string);
	/**
	 * 添加义达跟踪号
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月7日 下午2:09:55   
	* @version
	 */
	int updateYd(ydBase base);
	/*
	 * 删除
	 */
	int ydDelOrder(Integer id);
	/**
	 * 批量添加义达
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月9日 下午4:14:07   
	* @version
	 */
	int addOrders(@Param("lists")List<ydBase> lists);
	/**
	 * 批量查询
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月9日 下午5:34:22   
	* @version
	 */
	List<ydBase> selectYds(@Param("lists")List<Integer> list);
	/**
	 * 查询参考号
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月10日 上午10:59:03   
	* @version
	 */
	List<String> selectIDList(@Param("lists")List<String> list);

	List<ydBase> selectAll();
	/**
	 * 根据id查询
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月17日 下午6:07:42   
	* @version
	 */
	List<ydBase> selectYdsId(@Param("lists")List<Object> data);
	/**
	 * 批量修改默认
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年3月18日 下午5:31:37   
	* @version
	 */
	int defaultHj0Plus(@Param("hjList")List<hjBase> hjList);

	ydBase findIds(@Param("fids") String fids);
	ydBase findIds2(@Param("fids") String fids);
}