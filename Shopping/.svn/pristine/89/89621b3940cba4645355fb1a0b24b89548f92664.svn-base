package com.sybinal.shop.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sybinal.shop.mapper.rolesMapper;
import com.sybinal.shop.model.roles;
@Service
public class roleServiceImpl implements roleService {
	
	@Autowired
	rolesMapper roleMapper;
	/**
	 * 查找权限用户
	 */
	@Override
	public List<roles> selAll(roles role) {
		// TODO Auto-generated method stub
		return roleMapper.selAll(role);
	}
	/**
	 * 添加职称
	 */
	@Override
	public int adds(roles role) {
		// TODO Auto-generated method stub
		return roleMapper.insertSelective(role);
	}
	/**
	 * 职称是否存在
	 */
	@Override
	public int checkRolename(String string) {
		// TODO Auto-generated method stub
		return roleMapper.checkRolename(string);
	}

	/**
	 * 删除职称
	 */
	@Override
	public int del(String string) {
		// TODO Auto-generated method stub
		return roleMapper.deleteByPrimaryKey(Integer.parseInt(string));
	}
	/**
	 * 查找需要修改的职称edits
	 */
	@Override
	public roles edits(String string) {
		// TODO Auto-generated method stub
		return roleMapper.selectByPrimaryKey(Integer.parseInt(string));
	}
	/**
	 * 修改职称
	 */
	@Override
	public int update(roles role) {
		// TODO Auto-generated method stub
		return roleMapper.updateByPrimaryKeySelective(role);
	}
	/**
	 * 查找角色
	 */
	@Override
	public List<roles> uasername() {
		// TODO Auto-generated method stub
		return roleMapper.uasername();
	}

}
