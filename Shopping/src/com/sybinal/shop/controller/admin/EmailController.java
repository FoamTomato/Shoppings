package com.sybinal.shop.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sybinal.shop.common.sends.IMAPReceiveMailTest;
import com.sybinal.shop.common.sends.MailSenderInfo;
import com.sybinal.shop.common.sends.SimpleMailSender;
import com.sybinal.shop.model.User;
import com.sybinal.shop.model.emailDetailsWithBLOBs;
import com.sybinal.shop.model.emailMethod;
import com.sybinal.shop.model.hjBase;
import com.sybinal.shop.model.userEmail;
import com.sybinal.shop.service.email.UserEmailServer;
import com.sybinal.shop.service.logistics.LogisticsService;
import com.sybinal.shop.service.user.UserService;

@Controller
@RequestMapping("email")
public class EmailController {

	private static Logger logger = Logger.getLogger(EmailController.class); 
	
	@Autowired
	UserEmailServer userEmailServer;
	
	@Autowired
	UserService userService;

	@Autowired
	LogisticsService fLogisticService;
	
	
	//getEmail
	@RequestMapping(value = "getEmail")
	@ResponseBody
	public ModelAndView selectOrderById() {
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/email/mainsGet");
		return model;
	} 
	//postEmail
	@RequestMapping(value = "postEmail")
	@ResponseBody
	public ModelAndView postEmail() {
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/email/postEmail");
		return model;
	} 

	/**
	 * 查询所有的邮箱客服
	 */
	@RequestMapping(value = "Customer", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo Customer(@RequestBody Map<String,Object> map) {
		PageHelper.startPage(Integer.parseInt(map.get("page").toString()),Integer.parseInt(map.get("pagelength").toString()));
		List<userEmail> List=userEmailServer.selectAllCatelist(map.get("selectText").toString());
		PageInfo pageInfo = new PageInfo(List);
		return pageInfo;
	}
	/**
	 * 查询所有的邮箱客服2
	 */
	@RequestMapping(value = "Customer2", method = RequestMethod.POST)
	@ResponseBody
	public List<userEmail> Customer2() {
		return userEmailServer.selectAllCatelist("");
	}
	/**
	 * 查询所有的客服人员
	 */
	@RequestMapping(value = "user", method = RequestMethod.POST)
	@ResponseBody
	public List<User> user() {
		return userService.user();
	}
	/**
	 * 添加客服
	 * add
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public int add(@RequestBody userEmail user) {
		return userEmailServer.add(user);
	} 
	/**
	 * methodList
	 * 添加邮箱方式
	 */
	@RequestMapping(value = "methodList", method = RequestMethod.POST)
	@ResponseBody
	public List<emailMethod> methodList() {
		return userEmailServer.methodList();
	} 
	/**
	 * 删除客服
	 * del
	 */
	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public int del(@RequestBody userEmail user) {
		return userEmailServer.del(user);
	}
	/**
	 * 查询单个根据id
	 * edit 
	 */
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public userEmail edit(@RequestBody userEmail user) {
		return userEmailServer.edit(user);
	}
	/**
	 * 保存修改
	 * save
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public int save(@RequestBody userEmail user) {
		return userEmailServer.save(user);
	}
	/**
	 * 收件箱
	 * giveEmail
	 */
	@RequestMapping(value = "giveEmail")
	@ResponseBody
	public ModelAndView giveEmail() {
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/email/giveEmail");
		return model;
	}
	/**
	 * 回复邮件
	 */
	@RequestMapping(value = "reportEmail", method = RequestMethod.POST)
	@ResponseBody
	public String reportEmail(@RequestBody Map<String,Object> map) {
		MailSenderInfo sender=new MailSenderInfo();
		/*
		 * 查询客服
		 */
		Pattern p = Pattern.compile("<.*>");

		Matcher m1=p.matcher(map.get("postMan").toString());
		String[]data=new String[1];
		while (m1.find()) {  
			userEmailServer.selectAllCatelist(m1.group().substring(1,m1.group().length()-1)).forEach(list->{
				sender.setSubject("Re:"+map.get("title").toString());
				sender.setFromAddress(list.getUserEmail());
				sender.setPassword(list.getPwdEmail());
				Matcher m2=p.matcher(map.get("getMan").toString());
				while (m2.find()) {
					sender.setToAddress(m2.group().substring(1,m2.group().length()-1));
				}
				sender.setContent(map.get("content").toString());
				sender.setUserName(list.getUserEmail()); 
				//务必添加这个  开启验证
				sender.setValidate(true);
				String[] lists=map.get("fjEmail").toString().split(",");
				sender.setAttachFileNames(lists);
				SimpleMailSender simp=new SimpleMailSender();
				sender.setMailServerHost(list.getSpare3());
				sender.setMailServerPort(list.getPort1());
				boolean result=simp.sendHtmlMail(sender,map.get("csEmail").toString());
				if(result==true){
					String[] lists2=map.get("fjEmail2").toString().split(",");
					sender.setAttachFileNames(lists2);
					userEmailServer.inserter(sender);
					data[0]="1";
				}else{
					data[0]="0";
				}
				return;
			});
		}
		return data[0];
	}
	/**
	 * 接受附件
	 */
	@RequestMapping(value = "CommonsMultipartFile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> CommonsMultipartFile(@RequestParam List<MultipartFile> file,HttpServletRequest request,
			HttpServletResponse response) {
		FileUploadController filee=new FileUploadController();
		Map<String, String> map=new HashMap<String, String>();
		
		try {
			map=filee.newUploadFile(file,request,response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 接收邮件
	 * @throws Exception 
	 */
	@RequestMapping(value = "get/Email", method = RequestMethod.POST)
	@ResponseBody
	public int Emails(@RequestBody Map<String,Object> map,HttpServletRequest request){
		IMAPReceiveMailTest mes=new IMAPReceiveMailTest();
		List<userEmail> list =userEmailServer.selectAlllist(map.get("list").toString());
		//接收邮箱
		int count=0;
		List<emailDetailsWithBLOBs>p=new ArrayList<emailDetailsWithBLOBs>();
		for(userEmail s:list) {
			try { 
				p=mes.getEmailMessage(request,s); 
				logger.info(p);
				logger.info(StringUtils.isEmpty(p.getClass()));
				/**
				 * 判断是否接收到邮件  为空则未接收到
				 */
				if(p==null) {
					count=-1;
				}else if(!p.isEmpty()) {
					count+=userEmailServer.addEmailContent(p);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("邮件接收错误",e); 
			}
		}
		return count;
	}
	/**
	 * 查找所有邮件的信息
	 * get/emailList
	 */
	@RequestMapping(value = "get/emailList", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo emailList(@RequestBody Map<String,Object> map){
		PageHelper.startPage(Integer.parseInt(map.get("page").toString()),Integer.parseInt(map.get("pagelength").toString()));
		List<emailDetailsWithBLOBs> List=userEmailServer.emailList(map);
		PageInfo pageInfo = new PageInfo(List);
		return pageInfo;
	}
	/**
	 * 获取所有运单列表get/logticslist
	 */
	@RequestMapping(value = "get/logticslist", method = RequestMethod.POST)
	@ResponseBody
	public List<hjBase> logticslist(@RequestBody Map<String,Object> map){
		return fLogisticService.logticslist(Integer.parseInt(map.get("id").toString()));
	}
	/**
	 * 查找详情
	 * get/emailstream
	 */
	@RequestMapping(value = "get/emailstream", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> emailstream(@RequestBody Map<String,Object> map){
		Map<String,Object>result=new HashMap<String,Object>();
		emailDetailsWithBLOBs emailstream = userEmailServer.emailstream(Integer.parseInt(map.get("id").toString()));
		/**
		 * 亚马逊编号正则
		 */
		Pattern pattern = Pattern.compile("\\d{3}-\\d{7}-\\d{7}");;
		Matcher matcher = pattern.matcher(emailstream.getSpare1());
		if (matcher.find()) {
			/**
			 * 如果标题有亚马逊编号则用编号进行匹配返回为list
			 */
			result.put("orderstream", fLogisticService.stream(matcher.group(0)));
		}else {
			Matcher matcher2 = pattern.matcher(emailstream.getText());
			/**
			 * 如果内容有亚马逊编号则用编号进行匹配返回为list
			 */
			if(matcher2.find()){
				result.put("orderstream", fLogisticService.stream(matcher2.group(0)));
			}else {
				/**
				 * 如果如果以上都没有则用邮箱匹配返回为list
				 */
				Pattern pattern2 = Pattern.compile("\\+.{21}");
				Matcher matcher3 = pattern2.matcher(emailstream.getEmailPostman());
				if(matcher3.find()){
					/**
					 * 去除无关数据
					 */
					String t=emailstream.getEmailPostman().replace(matcher3.group(0), "");
					int c=t.lastIndexOf(">");
					int d=t.indexOf("<")+1;
					/**
					 * 去除空格
					 */
					result.put("orderstream", fLogisticService.streamEmail(t.substring(d,c).replace(" ","")));
				}else {
					/**
					 * 若都没找到则为null
					 */
					result.put("orderstream", null);
				}
			}
		}
		//String poststream=emailstream.getSpare1().substring(emailstream.getEmailPostman().indexOf("<")+1, emailstream.getEmailPostman().indexOf(">"));
		result.put("emailstream", emailstream);
		return result;
	}
}
