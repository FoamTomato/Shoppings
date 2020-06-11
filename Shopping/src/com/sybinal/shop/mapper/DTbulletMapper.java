package com.sybinal.shop.mapper;

import com.sybinal.shop.model.DTbullet;

public interface DTbulletMapper {
    int deleteByPrimaryKey(String bullet);

    int insert(DTbullet record);

    int insertSelective(DTbullet record);

    DTbullet selectByPrimaryKey(String bullet);

    int updateByPrimaryKeySelective(DTbullet record);

    int updateByPrimaryKey(DTbullet record);
}