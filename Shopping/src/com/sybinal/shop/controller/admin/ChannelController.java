package com.sybinal.shop.controller.admin;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sybinal.shop.model.ExcelExport;
import com.sybinal.shop.model.User;
import com.sybinal.shop.model.logOutOf;
import com.sybinal.shop.service.logOutOfService;
import com.sybinal.shop.service.user.UserService;
/**
 * 添加渠道
*    
* 项目名称：Shopping   
* 类名称：ChannelController   
* 类描述：   
* 创建人：PC1   
* 创建时间：2020年4月9日 下午4:47:44   
* @version
 */
@Controller
@RequestMapping("Logistics/channel")
public class ChannelController {
	
	private static Logger logger=Logger.getLogger(ChannelController.class);

	@Autowired
	UserService userService;

	@Autowired
	logOutOfService logout;
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView productList() {
		ModelAndView model = new ModelAndView();
		User user=new User();
		user.setUserName(FLogisticsController.username());
		model.addObject("jurisdiction", userService.jurisdiction(user));
		model.setViewName("admin/channel/ChannelMain");
		return model;
	}

	@RequestMapping(value = "export", method = RequestMethod.GET)
	public ModelAndView export() {
		ModelAndView model = new ModelAndView();
		User user=new User();
		user.setUserName(FLogisticsController.username());
		model.addObject("jurisdiction", userService.jurisdiction(user));
		model.setViewName("admin/channel/Exports");
		return model;
	}
	
	@RequestMapping(value = "selectExports", method = RequestMethod.POST)
	@ResponseBody
	public List<ExcelExport> selectExports() {
		return logout.selectExports();
	}
	
	
	@RequestMapping(value = "selectChannel", method = RequestMethod.POST)
	@ResponseBody
	public List<logOutOf> selectChannel() {
		return logout.selectAll();
	}
	
	@RequestMapping(value = "selectChannels", method = RequestMethod.POST)
	@ResponseBody
	public List<logOutOf> selectChannels() {
		return logout.selectAlls();
	}
	/**
	 * 添加渠道
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月14日 下午5:42:03   
	* @version
	 */
	@RequestMapping(value = "adds", method = RequestMethod.POST)
	@ResponseBody
	public int adds(@RequestBody logOutOf logof) {
		return logout.adds(logof);
	}
	
	@RequestMapping(value = "addExpory", method = RequestMethod.POST)
	@ResponseBody
	public int addExpory(@RequestBody ExcelExport logof) {
		return logout.addExpory(logof);
	}
	/**
	 * 删除渠道
	 */
	@RequestMapping(value = "deleteChannel", method = RequestMethod.POST)
	@ResponseBody
	public int deleteChannel(@RequestBody List<logOutOf> list) {
		return logout.deleteChannel(list);
	}
	

	@RequestMapping(value = "deleteExport", method = RequestMethod.POST)
	@ResponseBody
	public int deleteExport(@RequestBody List<ExcelExport> list) {
		return logout.deleteExport(list);
	}
	/**
	 * 修改渠道
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public int updates(@RequestBody logOutOf logof) {
		return logout.updates(logof);
	}
	
	@RequestMapping(value = "updateExport", method = RequestMethod.POST)
	@ResponseBody
	public int updateExport(@RequestBody ExcelExport logof) {
		return logout.updateExport(logof);
	}
}
