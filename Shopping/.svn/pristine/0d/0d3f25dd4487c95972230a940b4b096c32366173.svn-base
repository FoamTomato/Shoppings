package com.sybinal.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sybinal.shop.model.logOutOf;

public interface logOutOfMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(logOutOf record);

    int insertSelective(logOutOf record);

    logOutOf selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(logOutOf record);

    int updateByPrimaryKey(logOutOf record);
    /**
	 * 查询所有的物流方式
	 */
	List<logOutOf> selectAll();
	/**
	 * 删除渠道
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月14日 下午5:45:32   
	* @version
	 */
	int deleteByPrimaryKeys(@Param("lists")List<logOutOf> list);

	List<logOutOf> selectAlls();
}