package com.sybinal.shop.mapper;

import com.sybinal.shop.model.DTtitles;

public interface DTtitlesMapper {
    int deleteByPrimaryKey(String title);

    int insert(DTtitles record);

    int insertSelective(DTtitles record);

    DTtitles selectByPrimaryKey(String title);

    int updateByPrimaryKeySelective(DTtitles record);

    int updateByPrimaryKey(DTtitles record);
}