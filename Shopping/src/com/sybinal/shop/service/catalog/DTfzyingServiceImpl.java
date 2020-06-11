package com.sybinal.shop.service.catalog;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sybinal.shop.common.AjaxResult;
import com.sybinal.shop.mapper.DTfzyingMapper;
import com.sybinal.shop.mapper.DTfzyingsMapper;
import com.sybinal.shop.model.DTfzying;
import com.sybinal.shop.model.DTfzyingWithBLOBs;
import com.sybinal.shop.model.DTfzyings;

@Service
public class DTfzyingServiceImpl implements DTfzyingService {
	@Autowired
	DTfzyingMapper dTfzyingMapper;
	@Autowired
	DTfzyingsMapper dTfzyingsMapper;

	@Override
	public List<DTfzying> selectAll() {
		// TODO Auto-generated method stub
		return dTfzyingMapper.selectAll();
	}

	@Override
	public AjaxResult setinsert(DTfzyingWithBLOBs dtfzing) {
		// TODO Auto-generated method stub

		int c = dTfzyingMapper.insertSelective(dtfzing);
		if (c == 1) {
			return new AjaxResult(true, "保存成功!");
		}

		return new AjaxResult(false, "保存失败!");
	}

	@Override
	public DTfzyingWithBLOBs selectByid(Integer id) {
		// TODO Auto-generated method stub
		return dTfzyingMapper.selectByPrimaryKey(id);
	}

	@Override 
	public List<DTfzying> selectdTstockAtAll(DTfzyings dtfzing,String fen) {
		// TODO Auto-generated method stub
		List<String> strings=null;
		if(!fen.equals("") && !fen.equals(null)){
			String[] split = fen.split(",");
			strings = Arrays.asList(split);
    		return dTfzyingMapper.selectdTstockAtAll(dtfzing,strings);
		}
		return dTfzyingMapper.selectdTstockAtAll(dtfzing,strings);
	}

	@Override
	@Transactional
	public boolean setinsertY(DTfzyingWithBLOBs dtfzing) {
		// TODO Auto-generated method stub
		return dtfzing != null;
	}

	@Override
	public int updateProduct(DTfzyingWithBLOBs dTfzying) {
		// TODO Auto-generated method stub
		return dTfzyingMapper.updateByPrimaryKeySelective(dTfzying);
	}

}
