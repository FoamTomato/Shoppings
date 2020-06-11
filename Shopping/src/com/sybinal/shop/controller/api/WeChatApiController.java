package com.sybinal.shop.controller.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.sybinal.shop.common.HttpUtilss;
import com.sybinal.shop.controller.admin.EnumContentType;

@RestController
//@Controller
@RequestMapping("/api/v2/")
public class WeChatApiController {
	
	private static Logger log= Logger.getLogger(WeChatApiController.class);
	
	@RequestMapping(value = "/getOpenId",method=RequestMethod.POST)
	public List<Object> openid(@RequestBody  Map<String,String> map) throws Exception {
		
		String CODE=map.get("code");
		String status=map.get("status");
		String APPID = null;
		String SECRET=null;
		Map<String, Object> head = Maps.newHashMap();
		//存储结果
		List<Object> result=new ArrayList<Object>();

		System.out.println(status);
		if(status.equals("gzh")) {
			APPID="wx328d14ceea42d1dd";
			SECRET="808cb1b0bc88fc9498b8e1b1217314af";
		}else if(status.equals("xcx")) {
			APPID="wx87e74b678591e320";
			SECRET="6320e4242d02291a7f386905e71b0f83";
		}else {
			head.put("code", "401");
			head.put("data","status参数错误");
			result.add(head);
			return result;
		}
		String apiurl="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APPID+"&secret="+SECRET+"&code="+CODE+"&grant_type=authorization_code";
		
		String doPost;
		doPost= HttpUtilss.doPost(apiurl, "", "",EnumContentType.JSON, 30000);
		JSONObject jso = JSONObject.parseObject(doPost);
		if(doPost!=""){
			head.put("code", "200");
			head.put("data",jso);
			result.add(head);
		}
		return result;
	}
}
