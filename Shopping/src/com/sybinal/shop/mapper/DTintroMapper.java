package com.sybinal.shop.mapper;

import com.sybinal.shop.model.DTintro;

public interface DTintroMapper {
    int deleteByPrimaryKey(String intro);

    int insert(DTintro record);

    int insertSelective(DTintro record);

    DTintro selectByPrimaryKey(String intro);

    int updateByPrimaryKeySelective(DTintro record);

    int updateByPrimaryKey(DTintro record);
}