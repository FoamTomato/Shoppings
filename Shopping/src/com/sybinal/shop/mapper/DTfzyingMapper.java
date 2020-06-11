package com.sybinal.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sybinal.shop.model.DTfzying;
import com.sybinal.shop.model.DTfzyingWithBLOBs;
import com.sybinal.shop.model.DTfzyings;

public interface DTfzyingMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(DTfzyingWithBLOBs record);

	int insertSelective(DTfzyingWithBLOBs record);

	DTfzyingWithBLOBs selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(DTfzyingWithBLOBs record);

	int updateByPrimaryKeyWithBLOBs(DTfzyingWithBLOBs record);

	int updateByPrimaryKey(DTfzying record);

	List<DTfzying> selectAll();

	List<DTfzying> selectdTstockAtAll(@Param("dtfzying") DTfzyings dtfzying,@Param("lists")List<String> strings);

	int updateProduct(DTfzyingWithBLOBs dTfzying);

}