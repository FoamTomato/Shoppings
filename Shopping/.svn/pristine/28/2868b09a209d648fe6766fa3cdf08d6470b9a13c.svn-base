package com.sybinal.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sybinal.shop.model.DTstock;

public interface DTstockMapper {
	int insert(DTstock record);
	/**
	 * 批量添加变体
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月7日 下午3:53:29   
	* @version
	 */
	int insertSelective(List<DTstock> dTstock);

	List<DTstock> selectBystockSku(@Param("fskus") String fsku);

	void delstock(@Param("fsku")String fsku);


	/**
	 * @return   
	 * @user: spor-wen10
	 * @Title: insertall  
	 * @Description: TODO批量添加变体
	 * @date: 2020年2月11日 下午4:22:33
	 * @param: @param dTstock    参数  
	 * @return: void    返回类型  
	 * @throws  
	 */
	int insertall(@Param("dTstock")List<DTstock> dTstock);


}