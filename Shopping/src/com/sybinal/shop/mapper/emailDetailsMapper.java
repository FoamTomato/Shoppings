package com.sybinal.shop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sybinal.shop.model.emailDetails;
import com.sybinal.shop.model.emailDetailsWithBLOBs;

public interface emailDetailsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(emailDetailsWithBLOBs record);

    int insertSelective(emailDetailsWithBLOBs record);
    /**
	 * 查找详情
	 * get/emailstream
	 */
    emailDetailsWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(emailDetailsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(emailDetailsWithBLOBs record);

    int updateByPrimaryKey(emailDetails record);
    /**
     * 批量添加
     * @param parseMessage2
     * @return
     */
	int addEmailContent(@Param("list")List<emailDetailsWithBLOBs> parseMessage2);
	/**
	 * 查找所有邮件的信息
	 * @return
	 */
	List<emailDetailsWithBLOBs> emailList(@Param("map")Map<String, Object> map);
}