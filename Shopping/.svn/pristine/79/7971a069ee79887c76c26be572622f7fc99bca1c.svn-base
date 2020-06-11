package com.sybinal.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sybinal.shop.model.countryCode;

public interface countryCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(countryCode record);

    int insertSelective(countryCode record);

    countryCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(countryCode record);

    int updateByPrimaryKey(countryCode record);
    /**
     * 获取所有的国家
    *    
    * 项目名称：Shopping   
    * 类描述：   
    * 创建人：PC1   
    * 创建时间：2020年3月21日 上午11:21:38   
    * @version
     */
	List<countryCode> selectAll();
	/**
	 * 条件查找
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月21日 下午3:57:19   
	* @version
	 */
	List<countryCode> selectCountryText(String test);
	

	/**
	 * 删除渠道
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月14日 下午5:45:32   
	* @version
	 */
	int deleteByPrimaryKeys(@Param("lists")List<countryCode> list);
}