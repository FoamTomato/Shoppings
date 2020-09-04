package com.sybinal.shop.service.catalog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sybinal.shop.mapper.DTstockMapper;
import com.sybinal.shop.model.DTstock;

@Service
public class DTstockServiceImpl implements DTstockService {
	@Autowired
	DTstockMapper dTstockMapper;

	@Override
	public int insertStock(List<DTstock> dTstock) {
		// TODO Auto-generated method stub
		return dTstockMapper.insertall(dTstock);
	}

	@Override
	public List<DTstock> selectBystockSku(String fsku) {
		// TODO Auto-generated method stub
		return dTstockMapper.selectBystockSku(fsku);
	}

	@Override
	public void delstock(String fsku) {
		// TODO Auto-generated method stub
		dTstockMapper.delstock(fsku);
	}

}
