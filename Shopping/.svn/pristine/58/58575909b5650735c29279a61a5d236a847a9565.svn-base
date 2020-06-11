package com.sybinal.shop.mapper;

import java.util.List;
import java.util.TreeSet;

import org.apache.ibatis.annotations.Param;

import com.sybinal.shop.model.DTpic;

public interface DTpicMapper {
    int deleteByPrimaryKey(String id);

    int insert(DTpic record);

    int insertSelective(DTpic record);

    List<DTpic> selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DTpic record);

    int updateByPrimaryKey(DTpic record);

	/**
	 * @param string   
	 * @user: spor-wen10
	 * @Title: insertall  批量添加主图
	 * @Description: TODO
	 * @date: 2020年2月15日 下午4:22:54
	 * @param: @param t    参数  
	 * @return: void    返回类型  
	 * @throws  
	 */
	void insertall(@Param("tree")TreeSet<String> t,@Param("string")String string);
}