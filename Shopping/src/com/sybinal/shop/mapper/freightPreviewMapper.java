package com.sybinal.shop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sybinal.shop.model.freightPreview;

public interface freightPreviewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(freightPreview record);

    int insertSelective(freightPreview record);

    freightPreview selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(freightPreview record);

    int updateByPrimaryKey(freightPreview record);
    /**
     * 批量插入
     * @param preivew 插入的数值
     * @return
     */
	int insertAll(@Param("preivew")List<freightPreview> preivew);
	/**
	 * 查找全部
	 * @return
	 */
	List<freightPreview> selectAll(@Param("map")Map<String, String> map);
	/**
	 * 批量删除
	 * @param string
	 * @return
	 */
	int delets(@Param("del")List<Integer> string);
	/**
	 * 查询需要导出的数据
	 * @param list
	 * @return
	 */
	List<freightPreview> selectAlllist(@Param("list")List<String> list);
	/**
	 * 采购费
	 * @param map
	 * @return
	 */
	int procurements(@Param("map")Map<String, String> map);
}