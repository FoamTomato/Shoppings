package com.sybinal.shop.service.user;

import java.util.List;

import com.sybinal.shop.model.roles;

/**
 * 权限
 * @author PC1
 *
 */
public interface roleService {
	/**
	 * 查询所有的权限用户名称
	 * @param role
	 * @return
	 */
	public List<roles> selAll(roles role);
	/**
	 * 添加职称
	 * @param role
	 * @return
	 */
	public  int adds(roles role);
	/**
	 * 判断职称是否存在
	 * @param string
	 * @return
	 */
	public int checkRolename(String string);

	/**
	 * 删除职称
	 */
	public int del(String string);
	/**
	 * 查找需要修改的职称edits
	 */
	public roles edits(String string);

	/**
	 * 修改职称
	 */
	public int update(roles role);
	/**
	 * 查询所有的权限用户名称
	 * @param role
	 * @return
	 */
	public List<roles> uasername();
		

}
