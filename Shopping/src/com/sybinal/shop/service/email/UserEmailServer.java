package com.sybinal.shop.service.email;

import java.util.List;
import java.util.Map;

import com.sybinal.shop.common.sends.MailSenderInfo;
import com.sybinal.shop.model.emailDetailsWithBLOBs;
import com.sybinal.shop.model.emailMethod;
import com.sybinal.shop.model.userEmail;

public interface UserEmailServer {
	/**
	 * 查找客服邮箱
	 * @param object 
	 */
	List<userEmail> selectAllCatelist(String object);

	/**
	 * 添加客服
	 * add
	 */
	int add(userEmail user);
	/**
	 * 添加客服
	 * del
	 */
	int del(userEmail user);
	/**
	 * 查询单个根据id
	 * edit
	 */
	userEmail edit(userEmail user);
	/**
	 * 保存修改
	 * save
	 */
	int save(userEmail user);
	/**
	 * 添加信件内容
	 * @param list
	 * @param list2 
	 */
	int addEmailContent(List<emailDetailsWithBLOBs> list);
	/**
	 * 查找所有邮件的信息
	 * @param map 
	 * @return
	 */
	List<emailDetailsWithBLOBs> emailList(Map<String, Object> map);
	/**
	 * 查找详情
	 * get/emailstream
	 */
	emailDetailsWithBLOBs emailstream(int parseInt);

	/**  
	 * @user: spor-wen10
	 * @Title: methodList  
	 * @Description: TODO 添加邮箱方式
	 * @date: 2020年2月18日 下午7:04:17
	 * @param: @return    参数  
	 * @return: int    返回类型  
	 * @throws  
	 */
	List<emailMethod> methodList();

	/**  
	 * @user: spor-wen10
	 * @Title: selectAlllist  
	 * @Description: TODO 去逗号查询邮箱账号
	 * @date: 2020年2月24日 下午5:18:24
	 * @param: @param string
	 * @param: @return    参数  
	 * @return: List<userEmail>    返回类型  
	 * @throws  
	 */
	List<userEmail> selectAlllist(String string);

	/**  
	 * @user: spor-wen10
	 * @Title: inserter  发送成功保存发件箱
	 * @Description: TODO
	 * @date: 2020年2月28日 下午5:49:20
	 * @param: @param sender    参数  
	 * @return: void    返回类型  
	 * @throws  
	 */
	int inserter(MailSenderInfo sender);
	
}
