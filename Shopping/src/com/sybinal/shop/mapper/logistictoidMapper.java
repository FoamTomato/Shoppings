package com.sybinal.shop.mapper;

import java.util.List;

import com.sybinal.shop.model.logistictoid;

public interface logistictoidMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(logistictoid record);

    int insertSelective(logistictoid record);

    logistictoid selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(logistictoid record);

    int updateByPrimaryKey(logistictoid record);

	int BatchAll(List<logistictoid> list);

	logistictoid selectFids(String getfIds);
}