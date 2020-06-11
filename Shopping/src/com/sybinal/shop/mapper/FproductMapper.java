package com.sybinal.shop.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Bean;

import com.sybinal.shop.model.Fproduct;

public interface FproductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Fproduct record);

    int insertSelective(Fproduct record);
    
    Fproduct selectByPrimaryKey(Integer id);

    List<Fproduct> selectProAll();
    
    int updateByPrimaryKeySelective(Fproduct record); 

    int updateByPrimaryKey(Fproduct record);
    
    List<Fproduct> selectProductListAtAll(@Param("fproduct") Fproduct fproduct,@Param("price1") Integer price1,@Param("price2") Integer price2,@Param("updateTime1") String updateTime1,@Param("updateTime2") String updateTime2);
}