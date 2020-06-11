package com.sybinal.shop.service;

import java.util.List;
import java.util.Map;

import com.sybinal.shop.model.jpOrder;

public interface JpService {
	/*
	 * 添加集拼记录
	 */
	int addd(jpOrder jporder);
	/*
	 * 查找所有的集拼单
	 */
	List <jpOrder> selectAll(Map<String, String> map);
	/*
	 * 根据id查询参考编号
	 */
	jpOrder selectIds(String string, String username);
	/*
	 * 
	 * 根据id删除集合
	 */
	int dellist(String string, String string2);
	/*
	 * 根据id查询需要打印集拼
	 */
	List<jpOrder> selectPrint(Map<String, String> map);
	/**
	 * 批量发送集拼
	 * @param string
	 * @param standby1
	 * @return
	 */
	String postOut(String string, String standby1);
}
