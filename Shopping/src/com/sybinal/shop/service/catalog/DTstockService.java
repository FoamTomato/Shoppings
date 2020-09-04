package com.sybinal.shop.service.catalog;

import java.util.List;

import com.sybinal.shop.model.DTstock;

public interface DTstockService {

	/**
	 * 批量添加变体列表
	 * 
	 * @param user
	 * @return
	 */
	int insertStock(List<DTstock> list);

	/*
	 * 查询变体列表
	 */
	List<DTstock> selectBystockSku(String fsku);
	/*
	 * 删除所有的变体重新添加
	 */
	void delstock(String fsku);

}
