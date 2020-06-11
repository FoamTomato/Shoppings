package com.sybinal.shop.mapper;

import java.util.List;

import com.sybinal.shop.model.Astrict;

public interface AstrictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Astrict record);

    int insertSelective(Astrict record);

    Astrict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Astrict record);

    int updateByPrimaryKey(Astrict record);

	/**  
	 * @user: spor-wen10
	 * @Title: selectAll  
	 * @Description: TODO 查询所有
	 * @date: 2020年2月25日 下午7:10:51
	 * @param: @return    参数  
	 * @return: String    返回类型  
	 * @throws  
	 */
	List<Astrict> selectAll();
}