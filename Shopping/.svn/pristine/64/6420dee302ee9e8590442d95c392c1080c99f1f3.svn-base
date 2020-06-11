package com.sybinal.shop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sybinal.shop.model.jpOrder;

public interface jpOrderMapper {
	int deleteByPrimaryKey(Integer id);

    jpOrder selectByPrimaryKey(@Param("id")Integer id,@Param("jpStandby2")String username);

    int updateByPrimaryKeySelective(jpOrder record);

    int updateByPrimaryKeyWithBLOBs(jpOrder record);

    int updateByPrimaryKey(jpOrder record);
	
	int insert(jpOrder record);

    int insertSelective(jpOrder record);

	List<jpOrder> selectAll(@Param("seles")Map <String,String> map,@Param("listke") List strings);

	int deleteByPrimaryKeys(@Param("strings")List<String> strings,@Param("jpStandby2")String strin2);

	List<jpOrder> selectPrint(@Param("print")List<String> strings,@Param("jpStandby2")String strin2);

	void updateStatue(@Param("string2s")List<String> string2s,@Param("jpStandby2")String strin2);

	List<jpOrder> postOut(@Param("strings")List<String> strings,@Param("jpStandby2") String standby1);

}