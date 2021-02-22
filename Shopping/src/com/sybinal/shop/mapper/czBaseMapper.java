package com.sybinal.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sybinal.shop.model.czBase;
import com.sybinal.shop.model.czBaseWithBLOBs;

public interface czBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(czBaseWithBLOBs record);

    int insertSelective(czBaseWithBLOBs czbase);

    czBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(czBaseWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(czBaseWithBLOBs record);

    int updateByPrimaryKey(czBase record);
    /**
     * 批量添加创志
    *    
    * 项目名称：Shopping   
    * 类描述：   
    * 创建人：PC1   
    * 创建时间：2020年4月1日 下午3:04:01   
    * @version
     */
	int insertSelectives(@Param("czb")List<czBaseWithBLOBs> czbase);
	/**
	 * 批量清空默认
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月1日 下午4:39:42   
	* @version
	 */
	/*int defaultHj0Plus(@Param("hjList")List<hjBase> cz);*/
	/**
	 * 批量清空默认
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月1日 下午4:39:42   
	* @version
	 */
	int default2cz(czBase cz);
	
	/**
	 * 批量根据id查找
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月1日 下午5:06:06   
	* @version
	 */
	List<czBaseWithBLOBs> selectIDList(@Param("lists")List<String> list);
	/**
	 * 批量添加创志跟踪号
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月1日 下午7:35:04   
	* @version
	 */
	int updateCz(czBase base);
	/**
	 * 查询运单
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月1日 下午7:53:57   
	* @version
	 */
	List<czBase> selectOrder(@Param("valueOf")String valueOf,@Param("standy1") String standby1);
	/**
	 * 创志删除
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月2日 下午4:46:06   
	* @version
	 */
	int deleteOrder(czBase czbase);
}