package com.sybinal.shop.controller.admin;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sybinal.shop.model.User;
import com.sybinal.shop.model.freightPreview;
import com.sybinal.shop.model.logOutOf;
import com.sybinal.shop.service.PreviewService;
import com.sybinal.shop.service.logOutOfService;
import com.sybinal.shop.service.excel.ExcelService;
import com.sybinal.shop.service.user.UserService;

@Controller
@RequestMapping("preview")
public class PreviewController {
	@Autowired
	PreviewService previewServices;
 
	@Autowired
	UserService userService;

	@Autowired
	logOutOfService logout;
	
	@Autowired
	ExcelService excelService;
	
	/**
	 * 跳转至运单预览
	 * @return
	 */
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		User user=new User();
		user.setUserName(FLogisticsController.username());
		model.addObject("jurisdiction", userService.jurisdiction(user));
		model.setViewName("admin/preview/previewMain");
		return model;
	}
	
	/**
	 * 查询界面数据
	 * @throws ParseException 
	 */
	@RequestMapping(value = "selectAll",method=RequestMethod.POST)
	@ResponseBody
	public PageInfo selectAll(@RequestBody Map <String,String> map) throws ParseException {
		int limit=Integer.parseInt(map.get("limit"));
		int page=Integer.parseInt(map.get("page"));
		map.put("spare1", userService.Justiactions(FLogisticsController.username()).getStandby1());
		PageHelper.startPage(page,limit);
		List<freightPreview> List=previewServices.selectAll(map);
		PageInfo pageInfo = new PageInfo(List);
		return pageInfo;
	}
	/**
	 * 查询物流方式
	 */
	@RequestMapping(value = "logoutlist",method=RequestMethod.POST)
	@ResponseBody
	public List<logOutOf> logoutlist(@RequestBody Map <String,String> map) {
		return logout.selectAll();
	}
	/**
	 * 删除数据
	 */
	@RequestMapping(value = "delets",method=RequestMethod.POST)
	@ResponseBody
	public int delets(@RequestBody List <Integer> map) {
		return previewServices.delets(map);
	}
	/**
	 * procurements添加采购费用
	 */
	@RequestMapping(value = "procurements",method=RequestMethod.POST)
	@ResponseBody
	public int procurements(@RequestBody Map <String,String> map) {
		return previewServices.procurements(map);
	}
}
