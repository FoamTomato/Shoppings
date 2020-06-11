package com.sybinal.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sybinal.shop.model.roles;

public interface rolesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(roles record);

    int insertSelective(roles record);

    roles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(roles record);

    int updateByPrimaryKeyWithBLOBs(roles record);

    int updateByPrimaryKey(roles record);
    /**
     * 查找权限
     * @param role
     * @return
     */
	List<roles> selAll(@Param("roles") roles role);
	/**
	 * 添加职称
	 * @param role
	 * @return
	 */
	int adds(roles role);
	/**
	 * 职称是否存在
	 * @param role
	 * @return
	 */
	int checkRolename(@Param("rolename")String string);
	/**
	 * 查找角色
	 */
	List<roles> uasername();
	/**
	 * 查找权限
	 * @param userName
	 * @return
	 */
	roles jurisdiction(@Param("name")String userName);
}