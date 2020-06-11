package com.sybinal.shop.service.catalog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sybinal.shop.common.AjaxResult;
import com.sybinal.shop.mapper.DTpicMapper;
import com.sybinal.shop.model.DTpic;

@Service
public class DTpicServiceImpl implements DTpicService {
	@Autowired
	DTpicMapper dtpicMapper;

	@Override
	public AjaxResult setinsert(DTpic dtfzing) {
		// TODO Auto-generated method stub

		int c = dtpicMapper.insertSelective(dtfzing);
		if (c == 1) {
			return new AjaxResult(true, "保存成功!");
		}

		return new AjaxResult(false, "保存失败!");
	}

	@Override
	public List<DTpic> selectByPicIds(String id) {
		// TODO Auto-generated method stub
		return dtpicMapper.selectByPrimaryKey(id);
	}
}
