package com.sybinal.shop.mapper;

import com.sybinal.shop.model.DTkeys;

public interface DTkeysMapper {
    int deleteByPrimaryKey(String keys);

    int insert(DTkeys record);

    int insertSelective(DTkeys record);

    DTkeys selectByPrimaryKey(String keys);

    int updateByPrimaryKeySelective(DTkeys record);

    int updateByPrimaryKey(DTkeys record);
}