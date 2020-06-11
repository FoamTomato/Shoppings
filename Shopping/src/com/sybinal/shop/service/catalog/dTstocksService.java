package com.sybinal.shop.service.catalog;

import java.util.List;

import com.sybinal.shop.model.DTstocks;

public interface dTstocksService {

	/*
	 * 查询变体列表
	 */
	List<DTstocks> selectBystockSku(String fsku);

	int addStock(DTstocks stocks);

	int nullStock(DTstocks stocks);
	/*
	 * 修改变体
	 */
	int updateStocks(DTstocks dtstocks);

	DTstocks selectStocks(DTstocks dtstocks);

}
