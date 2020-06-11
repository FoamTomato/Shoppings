package com.sybinal.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sybinal.shop.model.Glogistics;

public interface GlogisticsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Glogistics record);

    int insertSelective(Glogistics record);

    List<Glogistics>  selectByPrimaryKey(@Param("id")List<String> strings,@Param("username") String username);//Integer id

    int updateByPrimaryKeySelective(Glogistics record);

    int updateByPrimaryKey(Glogistics record);

    List<Glogistics> seljps(@Param("jplist")List<String> string2s,@Param("username") String standby9);
}