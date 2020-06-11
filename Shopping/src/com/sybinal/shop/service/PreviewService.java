package com.sybinal.shop.service;

import java.util.List;
import java.util.Map;

import com.sybinal.shop.model.freightPreview;

public interface PreviewService {
	/**
	 * 查找全部
	 * @param map
	 * @return
	 */
	List<freightPreview> selectAll(Map<String, String> map);

	/**
	 * 删除物流
	 * @param map
	 * @return
	 */
	int delets(List<Integer> map);
	/**
	 * 采购费
	 * @param map
	 * @return
	 */
	int procurements(Map<String, String> map);

}
