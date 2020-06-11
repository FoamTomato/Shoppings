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

import com.sybinal.shop.model.countryCode;
import com.sybinal.shop.service.country.CountryService;
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
@RequestMapping("Logistics/country")
public class CountryController {
	
	private static Logger logger=Logger.getLogger(CountryController.class);
	

	@Autowired
	CountryService country;
	
	@RequestMapping(value = "transform", method = RequestMethod.GET)
	public ModelAndView productList() {
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/country/CountryMain");
		return model;
	}
	/**
	 * 查找国家
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月21日 上午10:58:33   
	* @version
	 */
	@RequestMapping(value = "selectCountry", method = RequestMethod.POST)
	@ResponseBody
	public List<countryCode> selectChannel() {
		return country.selectAll();
	}
	/**
	 * 条件查找
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月21日 下午3:55:35   
	* @version
	 */
	@RequestMapping(value = "selectCountryText", method = RequestMethod.POST)
	@ResponseBody
	public List<countryCode> selectCountryText(@RequestBody String test) {
		return country.selectCountryText(test.substring(1, test.length()-1));
	}
	/**
	 * 添加国家
	*    
	* 项目名称：Shopping   
	* 类描述：   
	* 创建人：PC1   
	* 创建时间：2020年4月14日 下午5:42:03   
	* @version
	 */
	@RequestMapping(value = "adds", method = RequestMethod.POST)
	@ResponseBody
	public int adds(@RequestBody countryCode countrys) {
		return country.adds(countrys);
	}
	
	/**
	 * 删除国家
	 */
	@RequestMapping(value = "deletes", method = RequestMethod.POST)
	@ResponseBody
	public int deletes(@RequestBody List<countryCode> list) {
		return country.deletes(list);
	}
	/**
	 * 修改渠道
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public int updates(@RequestBody countryCode countrys) {
		return country.updates(countrys);
	}
}
