package com.sybinal.shop.mapper;

import com.sybinal.shop.model.DTkeyss;

public interface DTkeyssMapper {
    int deleteByPrimaryKey(String keys);

    int insert(DTkeyss record);

    int insertSelective(DTkeyss record);

    DTkeyss selectByPrimaryKey(String keys);

    int updateByPrimaryKeySelective(DTkeyss record);

    int updateByPrimaryKey(DTkeyss record);
}