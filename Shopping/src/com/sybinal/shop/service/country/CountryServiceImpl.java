package com.sybinal.shop.service.country;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sybinal.shop.mapper.countryCodeMapper;
import com.sybinal.shop.model.countryCode;

@Service
public class CountryServiceImpl implements CountryService {
	@Autowired
	countryCodeMapper countryMapper;
	
	/**
	 * 查询所有的物流方式
	 */
	@Override
	public List<countryCode> selectAll() {
		// TODO Auto-generated method stub
		return countryMapper.selectAll();
	}
	/**
	 * 条件查找
	 */
	@Override
	public List<countryCode> selectCountryText(String test) {
		// TODO Auto-generated method stub
		return countryMapper.selectCountryText(test);
	}
	/**
	 * 添加国家
	 */
	@Override
	public int adds(countryCode countrys) {
		// TODO Auto-generated method stub
		return countryMapper.insertSelective(countrys);
	}
	/**
	 * 修改国家
	 */
	@Override
	public int updates(countryCode countrys) {
		// TODO Auto-generated method stub
		return countryMapper.updateByPrimaryKeySelective(countrys);
	}
	/**
	 * 删除国家
	 */
	@Override
	public int deletes(List<countryCode> list) {
		// TODO Auto-generated method stub
		return countryMapper.deleteByPrimaryKeys(list);
	}
}
