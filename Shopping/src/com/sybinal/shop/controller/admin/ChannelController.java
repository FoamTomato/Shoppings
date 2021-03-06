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

import com.sybinal.shop.model.logOutOf;
import com.sybinal.shop.service.logOutOfService;
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
	logOutOfService logout;
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView productList() {
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/channel/ChannelMain");
		return model;
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
	
	/**
	 * 删除渠道
	 */
	@RequestMapping(value = "deleteChannel", method = RequestMethod.POST)
	@ResponseBody
	public int deleteChannel(@RequestBody List<logOutOf> list) {
		return logout.deleteChannel(list);
	}
	/**
	 * 修改渠道
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public int updates(@RequestBody logOutOf logof) {
		return logout.updates(logof);
	}
}
