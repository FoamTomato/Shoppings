package com.sybinal.shop.service.catalog;

import java.util.List;

import com.sybinal.shop.common.AjaxResult;
import com.sybinal.shop.model.DTfzying;
import com.sybinal.shop.model.DTfzyingWithBLOBs;
import com.sybinal.shop.model.DTfzyings;

public interface DTfzyingService {
	List<DTfzying> selectAll();

	AjaxResult setinsert(DTfzyingWithBLOBs dtfzing);

	DTfzyingWithBLOBs selectByid(Integer id);

	List<DTfzying> selectdTstockAtAll(DTfzyings dtfzing,String fen);

	boolean setinsertY(DTfzyingWithBLOBs dtfzing);

	int updateProduct(DTfzyingWithBLOBs dTfzying);
}
