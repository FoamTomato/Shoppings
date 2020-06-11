package com.sybinal.shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sybinal.shop.mapper.freightPreviewMapper;
import com.sybinal.shop.model.freightPreview;

@Service
public class PreviewServiceImpl implements PreviewService {
	
	@Autowired
	freightPreviewMapper privew;
	/**
	 * 查找
	 */
	@Override
	public List<freightPreview> selectAll(Map<String, String> map) {
		// TODO Auto-generated method stub
		return privew.selectAll(map);
	}
	/**
	 * 删除物流
	 */
	@Override
	public int delets(List<Integer> string) {
		// TODO Auto-generated method stub
		/*String[] list = string.split(",");
		List<String> strings = Arrays.asList(list);
		System.out.println(strings);*/
		return privew.delets(string);
	}
	/**
	 * 采购费
	 */
	@Override
	public int procurements(Map<String, String> map) {
		// TODO Auto-generated method stub
		return privew.procurements(map);
	}
}
