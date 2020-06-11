package com.sybinal.shop.service.catalog;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sybinal.shop.common.AjaxResult;
import com.sybinal.shop.mapper.DTfzyingsMapper;
import com.sybinal.shop.model.DTfzyings;
import com.sybinal.shop.model.DTfzyingsWithBLOBs;

@Service
public class DTfzyingsServiceImpl implements DTfzyingsService {
	@Autowired
	DTfzyingsMapper dTfzyingsMapper;

	@Override
	public List<DTfzyingsWithBLOBs> selectAll() {
		// TODO Auto-generated method stub
		return dTfzyingsMapper.selectAll();
	}

	@Override
	public AjaxResult setinsert(DTfzyingsWithBLOBs dtfzing) {
		// TODO Auto-generated method stub

		int c = dTfzyingsMapper.insertSelective(dtfzing);
		if (c == 1) {
			return new AjaxResult(true, "保存成功!");
		}

		return new AjaxResult(false, "保存失败!");
	}

	@Override
	public DTfzyingsWithBLOBs selectByid(Integer id) {
		// TODO Auto-generated method stub
		return dTfzyingsMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public boolean setinsertY(DTfzyingsWithBLOBs dtfzing) {
		// TODO Auto-generated method stub
		return dtfzing != null;
	}

	@Override
	public int updateProduct(DTfzyingsWithBLOBs dTfzying) {
		// TODO Auto-generated method stub
		return dTfzyingsMapper.updateByPrimaryKeySelective(dTfzying);
	}

	@Override
	public int deleteProduct(Integer id) {
		// TODO Auto-generated method stub
		return dTfzyingsMapper.deleteByPrimaryKey(id);
	}
	@Override 
	public List<DTfzyingsWithBLOBs> selectdTstockAtAll(DTfzyings dtfzing,String fen) {
		// TODO Auto-generated method stub
		List<String> strings=null;
		if(!fen.equals("") && !fen.equals(null)){
			String[] split = fen.split(",");
			strings = Arrays.asList(split);
		}
		//System.out.println(dtfzing);
		return dTfzyingsMapper.selectdTstockAtAlls(dtfzing,strings);
	}
	/**
	 * 批量删除
	 */
	@Override
	public int deleteProductList(List<String> map) {
		// TODO Auto-generated method stub
		return dTfzyingsMapper.deleteProductList(map);
	}
}
