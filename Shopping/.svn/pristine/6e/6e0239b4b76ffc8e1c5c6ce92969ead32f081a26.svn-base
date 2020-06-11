package com.sybinal.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sybinal.shop.model.Xlogistics;
import com.sybinal.shop.model.hjBase;

public interface XlogisticsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Xlogistics record);

    int insertSelective(Xlogistics record);

    List<Xlogistics> selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Xlogistics record);

    int updateByPrimaryKey(Xlogistics record);

	List<Xlogistics> selectSA(@Param("id")Integer id,@Param("username")String name);
	
	Xlogistics selLogisticsQuantity(Integer id);

	int default0(@Param("xp") String s);

	int default1(@Param("id") Integer id);

	int default0Plus(@Param("hjList")List<hjBase> hjList);

}