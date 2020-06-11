package com.sybinal.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sybinal.shop.model.userEmail;

public interface userEmailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(userEmail record);

	/**
	 * 添加客服
	 * add
	 */
    int insertSelective(userEmail record);

    userEmail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(userEmail record);

    int updateByPrimaryKey(userEmail record);
    /**
     * 查找客服邮箱
     * @return
     */
	List<userEmail> selectAll(@Param("text")String text);

	/**  
	 * @user: spor-wen10
	 * @Title: selectAllUser  
	 * @Description: TODO 查找选中的用户
	 * @date: 2020年2月24日 下午5:21:05
	 * @param: @param strings
	 * @param: @return    参数  
	 * @return: List<userEmail>    返回类型  
	 * @throws  
	 */
	List<userEmail> selectAllUser(@Param("ids")List<String> strings);

}