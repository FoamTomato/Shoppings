package com.sybinal.shop.service.cate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sybinal.shop.mapper.CateMapper;
import com.sybinal.shop.model.Cate;
@Service
public class CateServiceImpl implements CateService {
	@Autowired
	CateMapper catemapper;
	@Override
	public List<Cate> selectAllCate(Cate cate) {
		// TODO Auto-generated method stub
		return catemapper.selectAll(cate);
	}
	@Override
	public List<Cate> Seach_cate(Cate cate) {
		// TODO Auto-generated method stub
		return catemapper.Seach_cate(cate);
	}
	@Override
	public int AddCates(Cate cates) {
		// TODO Auto-generated method stub
		return catemapper.insertSelective(cates);
	}
	@Override
	public int DelCates(Cate cates) {
		// TODO Auto-generated method stub
		return catemapper.updateDelet(cates);
	}
	@Override
	public List<Cate> select_modal_cate(Cate cates) {
		// TODO Auto-generated method stub
		return catemapper.select_modal_cate(cates);
	}
	@Override
	public int modal_cate(Cate cateF) {
		// TODO Auto-generated method stub
		return catemapper.updateByPrimaryKeySelective(cateF);
	}

}
