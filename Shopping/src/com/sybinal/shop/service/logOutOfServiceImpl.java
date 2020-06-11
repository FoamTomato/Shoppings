package com.sybinal.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sybinal.shop.mapper.AstrictMapper;
import com.sybinal.shop.mapper.logOutOfMapper;
import com.sybinal.shop.model.Astrict;
import com.sybinal.shop.model.logOutOf;

@Service
public class logOutOfServiceImpl implements logOutOfService {
	@Autowired
	logOutOfMapper logMapper;
	

	@Autowired
	AstrictMapper astrictMapper;
	/**
	 * 查询所有的物流方式
	 */
	@Override
	public List<logOutOf> selectAll() {
		// TODO Auto-generated method stub
		return logMapper.selectAll();
	}
	/**
	 * 物流添加的main函数
	 * @param shorts 简称
	 * @param name 名称
	 * @param logisticname 集拼名称
	 * @return
	 */
	@Override
	public int addlogistic(String shorts, String name, String logisticname) {
		// TODO Auto-generated method stub
		logOutOf out=new logOutOf();
		out.setShortName(shorts);
		out.setName(name);
		out.setLogisticsName(logisticname);
		return logMapper.insertSelective(out);
	}
	/* (non-Javadoc) 拦截收件人名称 拦截商品中文名称 拦截商品英文名称
	 * @see com.sybinal.shop.service.logOutOfService#namese(java.lang.String)
	 */
	@Override
	public String namese(List<Astrict> lists,String sj,String cn,String en) {
		// TODO Auto-generated method stub
		 //List<Astrict> lists= astrictMapper.selectAll();
		 for(int i=0;i<lists.size();i++) {
			if(sj.indexOf(lists.get(i).getNames()) !=-1 && lists.get(i).getProduct().equals("0")) {
				return "敏感词：收货人不能包含"+lists.get(i).getNames();
			}
			if(cn.indexOf(lists.get(i).getNames()) !=-1 && lists.get(i).getProduct().equals("1")) {
				return "敏感词：中英文不允许出现"+lists.get(i).getNames();
			}
			if(en.equals(lists.get(i).getNames()) && lists.get(i).getProduct().equals("2")) {
				return "敏感词：中英文只允许复合出现："+lists.get(i).getNames();
			}
		 }
		 return "0";
	}
	/**
	 * 添加渠道
	 */
	@Override
	public int adds(logOutOf logof) {
		// TODO Auto-generated method stub
		return logMapper.insertSelective(logof);
	}
	/**
	 * 删除渠道
	 */
	@Override
	public int deleteChannel(List<logOutOf> list) {
		// TODO Auto-generated method stub
		
		return logMapper.deleteByPrimaryKeys(list);
	}
	/**
	 * 修改渠道
	 */
	@Override
	public int updates(logOutOf logof) {
		// TODO Auto-generated method stub
		return logMapper.updateByPrimaryKeySelective(logof);
	}
	/**&
	 * 查询所有渠道
	 */
	@Override
	public List<logOutOf> selectAlls() {
		// TODO Auto-generated method stub
		return logMapper.selectAlls();
	}
	
}
