package com.sybinal.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sybinal.shop.model.DTstocks;

public interface DTstocksMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(DTstocks record);

	int insertSelective(DTstocks record);

	DTstocks selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(DTstocks record);

	int updateByPrimaryKeyWithBLOBs(DTstocks record);

	int updateByPrimaryKey(DTstocks record);

	List<DTstocks> selectBystockSku(@Param("fskus") String fsku);

	int updateStocks(DTstocks dtstocks);

	DTstocks selectStocks(DTstocks dtstocks);

}