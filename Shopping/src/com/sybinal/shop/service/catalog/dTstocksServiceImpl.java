package com.sybinal.shop.service.catalog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sybinal.shop.mapper.DTstocksMapper;
import com.sybinal.shop.model.DTstocks;

@Service
public class dTstocksServiceImpl implements dTstocksService {
	@Autowired
	DTstocksMapper dTstocksMapper;

	@Override
	public List<DTstocks> selectBystockSku(String fsku) {
		// TODO Auto-generated method stub
		return dTstocksMapper.selectBystockSku(fsku);

	}

	@Override
	public int addStock(DTstocks stocks) {
		// TODO Auto-generated method stub
		return dTstocksMapper.updateByPrimaryKeySelective(stocks);
	}

	@Override
	public int nullStock(DTstocks stocks) {
		// TODO Auto-generated method stub
		return dTstocksMapper.insertSelective(stocks);
	}

	@Override
	public int updateStocks(DTstocks dtstocks) {
		// TODO Auto-generated method stub
		return dTstocksMapper.updateStocks(dtstocks);
	}

	@Override
	public DTstocks selectStocks(DTstocks dtstocks) {
		// TODO Auto-generated method stub
		return dTstocksMapper.selectStocks(dtstocks);
	}

}
