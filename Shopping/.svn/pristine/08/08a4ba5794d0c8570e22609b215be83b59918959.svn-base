package com.sybinal.shop.service.email;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sybinal.shop.common.sends.MailSenderInfo;
import com.sybinal.shop.mapper.emailDetailsMapper;
import com.sybinal.shop.mapper.emailMethodMapper;
import com.sybinal.shop.mapper.userEmailMapper;
import com.sybinal.shop.model.emailDetailsWithBLOBs;
import com.sybinal.shop.model.emailMethod;
import com.sybinal.shop.model.userEmail;
@Service
public class UserEmailServerImpl implements UserEmailServer {
	@Autowired
	userEmailMapper UserEmailMapper;
	
	@Autowired
	emailDetailsMapper emails;

	@Autowired
	emailMethodMapper methodemail;
	/**
	 * 查找客服邮箱
	 */
	@Override
	public List<userEmail> selectAllCatelist(String text) {
		// TODO Auto-generated method stub
		return UserEmailMapper.selectAll(text);
	}
	/**
	 * 添加客服
	 * add
	 */
	@Override
	public int add(userEmail user) {
		// TODO Auto-generated method stub
		return UserEmailMapper.insertSelective(user);
	}
	
	/**
	 * 添加客服
	 * del
	 */
	@Override
	public int del(userEmail user) {
		// TODO Auto-generated method stub
		user.setState("2");//删除
		return UserEmailMapper.updateByPrimaryKeySelective(user);
	}
	/**
	 * 查询单个根据id
	 * edit
	 */
	@Override
	public userEmail edit(userEmail user) {
		// TODO Auto-generated method stub
		return UserEmailMapper.selectByPrimaryKey(user.getId());
	}
	/**
	 * 保存修改
	 * save
	 */
	@Override
	public int save(userEmail user) {
		// TODO Auto-generated method stub
		return UserEmailMapper.updateByPrimaryKeySelective(user);
	}
	/**
	 * 添加信件内容
	 * @param parseMessage2
	 */
	@Override
	public int addEmailContent(List<emailDetailsWithBLOBs> parseMessage2) {
		// TODO Auto-generated method stub
		return emails.addEmailContent(parseMessage2);
	}
	/**
	 * 查找所有邮件的信息
	 * @return
	 */
	@Override
	public List<emailDetailsWithBLOBs> emailList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return emails.emailList(map);
	}
	/**
	 * 查找详情
	 * get/emailstream
	 */
	@Override
	public emailDetailsWithBLOBs emailstream(int parseInt) {
		// TODO Auto-generated method stub
		return emails.selectByPrimaryKey(parseInt);
	}
	/* (non-Javadoc) 查询所有的邮箱
	 * @see com.sybinal.shop.service.email.UserEmailServer#methodList()
	 */
	@Override
	public List<emailMethod> methodList() {
		// TODO Auto-generated method stub
		return methodemail.selectAll();
	}
	/* (non-Javadoc)
	 * @see com.sybinal.shop.service.email.UserEmailServer#selectAlllist(java.lang.String)
	 */
	@Override
	public List<userEmail> selectAlllist(String list) {
		// TODO Auto-generated method stub
		//把所有的值截取
		String[] split = list.split(",");
		//存为list
        List<String> strings = Arrays.asList(split);
		return UserEmailMapper.selectAllUser(strings);
	}
	/* (non-Javadoc)
	 * @see com.sybinal.shop.service.email.UserEmailServer#inserter(com.sybinal.shop.common.sends.MailSenderInfo)
	 */
	@Override
	public int inserter(MailSenderInfo sender) {
		// TODO Auto-generated method stub
		emailDetailsWithBLOBs email=new emailDetailsWithBLOBs();
		email.setEmailPostman(sender.getUserName()+"<"+sender.getFromAddress()+">");
		email.setEmailGetman(sender.getToAddress());
		email.setEmailTimes(new Date());
		email.setText(sender.getContent());
		email.setSpare1(sender.getSubject());
		String [] sen=sender.getAttachFileNames();
		String sp4="";
		for(String se:sen){
			sp4+=se+",";
		}
		email.setSpare3(sp4.substring(0, sp4.length()-1));
		email.setSpare4("1");
		return emails.insert(email);
	}
}
