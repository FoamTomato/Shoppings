package com.sybinal.shop.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sybinal.shop.mapper.userShortRoleMapper;
import com.sybinal.shop.model.userShortRole;

@Service
public class userShortRoleServiceImpl implements userShortRoleService {
	
	@Autowired
	userShortRoleMapper usersShortRoleMapper;
	
	@Override
	public List<userShortRole> selAllroles() {
		// TODO Auto-generated method stub
		return usersShortRoleMapper.selAllroles();
	}
	/**
	 * 查找所有父节点
	 */
	@Override
	public List<userShortRole> sellParent(String string) {
		// TODO Auto-generated method stub
		return usersShortRoleMapper.sellParent(string);
	}
	/**
	 * 查找需要修改的职称edits
	 */
	@Override
	public userShortRole edits(String userId) {
		// TODO Auto-generated method stub
		return usersShortRoleMapper.selectByPrimaryKey(Integer.parseInt(userId));
	}

}
