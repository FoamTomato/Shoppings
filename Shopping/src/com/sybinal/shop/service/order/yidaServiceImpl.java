package com.sybinal.shop.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sybinal.shop.mapper.ydBaseMapper;
import com.sybinal.shop.model.hjBase;
import com.sybinal.shop.model.ydBase;

@Service
public class yidaServiceImpl implements yidaService {
	@Autowired
	ydBaseMapper ydBases;
	/**
	 * 添加义达产品
	 */
	@Override
	public int addOrder(ydBase base) {
		// TODO Auto-generated method stub
		return ydBases.insertSelective(base);
	}
	/**
	 * 查找义达订单
	 */
	@Override
	public List<ydBase> selectOrder(String id, String standby1, String getfIds) {
		// TODO Auto-generated method stub
		return ydBases.selectOrder(id,standby1,getfIds);
	}
	/**
	 * 修改默认
	 */
	@Override
	public void defaultHj0(String hjShipperhawbcode) {
		// TODO Auto-generated method stub
		ydBases.defaultHj0(hjShipperhawbcode);
	}
	/**
	 * 批量修改默认
	 */
	@Override
	public int defaultHj0Plus(List<hjBase> hjList) {
		// TODO Auto-generated method stub
		return ydBases.defaultHj0Plus(hjList);
	}
	@Override
	public int default2(Integer valueOf) {
		// TODO Auto-generated method stub
		return ydBases.default2(valueOf);
	}
	@Override
	public ydBase selectYd(String string) {
		// TODO Auto-generated method stub
		return ydBases.selectYd(string);
	}
	/**
	 * 添加义达跟踪号
	 */
	@Override
	public int updateYd(ydBase base) {
		// TODO Auto-generated method stub
		return ydBases.updateYd(base);
	}
	@Override
	public ydBase yidaSelectOne(ydBase base) {
		// TODO Auto-generated method stub
		return ydBases.selectByPrimaryKey(base.getId());
	}
	@Override
	public int ydUpdateOrder(ydBase base) {
		// TODO Auto-generated method stub
		return ydBases.updateByPrimaryKeySelective(base);
	}
	@Override
	public int ydDelOrder(ydBase base) {
		// TODO Auto-generated method stub
		return ydBases.ydDelOrder(base.getId());
	}
	@Override
	public int addOrders(List<ydBase> lists) {
		// TODO Auto-generated method stub
		return ydBases.addOrders(lists);
	}
	@Override
	public List<ydBase> selectYds(List<String> list) {
		// TODO Auto-generated method stub
		return ydBases.selectYds(list);
	}
	@Override
	public List<String> selectIDList(List<String> list) {
		// TODO Auto-generated method stub
		return ydBases.selectIDList(list);
	}
	@Override
	public List<ydBase> selectAll() {
		// TODO Auto-generated method stub
		return ydBases.selectAll();
	}
	@Override
	public List<ydBase> selectYdsId(List<Object> data) {
		// TODO Auto-generated method stub
		return ydBases.selectYdsId(data);
	}
	
}
