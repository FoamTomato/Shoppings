package com.sybinal.shop.mapper;

import com.sybinal.shop.model.DTintros;

public interface DTintrosMapper {
    int deleteByPrimaryKey(String intro);

    int insert(DTintros record);

    int insertSelective(DTintros record);

    DTintros selectByPrimaryKey(String intro);

    int updateByPrimaryKeySelective(DTintros record);

    int updateByPrimaryKey(DTintros record);
}