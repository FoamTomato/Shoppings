package com.sybinal.shop.service.cate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sybinal.shop.mapper.CateListMapper;
import com.sybinal.shop.model.CateList;
@Service
public class CateListServiceImpl implements CateListService {
	@Autowired
	CateListMapper cateListMapper;
	
	@Override
	public List<CateList> selectAllCatelist() {
		// TODO Auto-generated method stub
		return cateListMapper.selectAllCatelist();
	}

	@Override
	public int setValue(CateList cate1) {
		// TODO Auto-generated method stub
		return cateListMapper.insertSelective(cate1);
	}

}
