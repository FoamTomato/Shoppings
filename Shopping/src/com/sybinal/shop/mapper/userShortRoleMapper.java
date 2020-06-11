package com.sybinal.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sybinal.shop.model.userShortRole;

public interface userShortRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(userShortRole record);

    int insertSelective(userShortRole record);

    userShortRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(userShortRole record);

    int updateByPrimaryKey(userShortRole record);

	List<userShortRole> selAllroles();
	/**
	 * 查找所有父节点 standy1
	 * @param string
	 * @return
	 */
	List<userShortRole> sellParent(String string);
	/**
	 * 根据id查找权限
	 */
	List<userShortRole> selall(@Param("sellall")List<String> strings);
}