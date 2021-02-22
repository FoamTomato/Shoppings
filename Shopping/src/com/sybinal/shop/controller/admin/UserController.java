package com.sybinal.shop.controller.admin;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sybinal.shop.common.PageInformation;
import com.sybinal.shop.model.Contect;
import com.sybinal.shop.model.User;
import com.sybinal.shop.model.roles;
import com.sybinal.shop.service.user.ContectService;
import com.sybinal.shop.service.user.UserService;
import com.sybinal.shop.service.user.roleService;
import com.sybinal.shop.utils.Constants;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	ContectService contectService;

	@Autowired
	roleService roless;

	@RequestMapping(value = "/admin/user", method = RequestMethod.GET)
	public ModelAndView userManage() {
		ModelAndView model = new ModelAndView();
		User user=new User();

		user.setUserName(FLogisticsController.username());
		model.addObject("jurisdiction", userService.jurisdiction(user));
		model.setViewName("admin/user/userInfoMain");
		return model;
	}
	
	/**
	 * 查找角色名admin/username
	 * @param userId
	 * @param roleStr
	 * @return
	 */
	@RequestMapping(value = "/admin/admin/username", method = RequestMethod.POST)
	@ResponseBody
	public List<roles>  uasername() {
		return roless.uasername();
	}
	@RequestMapping(value = "/admin/user/edit", method = RequestMethod.POST)
	public ModelAndView editUser(Integer userId,String roleStr) {
		ModelAndView model = new ModelAndView();
		User edituser = userService.getUserById(userId);

		User user=new User();
		user.setUserName(FLogisticsController.username());
		/**
		 * 查找所有的职称
		 */
		model.addObject("role", roless.uasername()); 
		model.addObject("jurisdiction", userService.jurisdiction(user));
		model.addObject("user", edituser);
		model.addObject("but", "修改"); 
		if("ROLE_USER".equals(roleStr)){
			model.setViewName("admin/user/userInfoEdit");
		}else{
			model.setViewName("admin/user/userInfoEditAdmin");
		}
		return model;
	}	

	@RequestMapping(value = "/admin/user/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveUser(@RequestBody User user) throws IllegalAccessException, InvocationTargetException {
		int i = 0;
		user.setJurisdiction(user.getRole());
		if (user.getId() != null) {
			i = userService.updateUserForManage(user);
		} else {
			user.setEnabled(1);
			user.setRole(Constants.ROLE_ADMIN);
			i = userService.saveUserForManage(user);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (i > 0) {
			map.put("error", "0"); // 成功
			map.put("data", user); // 数据
		} else {
			map.put("error", "-1"); // 失败
			map.put("data", user); // 数据成
		}
		return map;
	}
	/*
	 * 查询当前账户有哪些权限
	 */
	@RequestMapping(value = "/admin/user/jurisdiction", method = RequestMethod.POST)
	@ResponseBody
	public User selUser(@RequestBody User user){
		return userService.jurisdiction(user);
	}
	/*
	 * 删除账户
	 */
	@RequestMapping(value = "/admin/user/del", method = RequestMethod.POST)
	@ResponseBody
	public int delUser(@RequestBody User user){
		return userService.delUser(user);
	}
	
	@RequestMapping(value = "/admin/adduser", method = RequestMethod.POST)
	public ModelAndView adduserManage() {
		ModelAndView model = new ModelAndView();
		User user=new User();

		/**
		 * 查找所有的职称
		 */
		model.addObject("role", roless.uasername()); 
		user.setUserName(FLogisticsController.username());
		model.addObject("but","添加");
		model.addObject("jurisdiction", userService.jurisdiction(user));
		model.setViewName("admin/user/userInfoEditAdmin");
		return model;
	}
	
	
	
	
	/**
	 * 查找用户列表
	 * @param pageInfo
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/admin/userInfoDataTable", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo userInfoDataTable(@RequestBody Map<String,String>map) {
		Integer page=Integer.parseInt(map.get("page"));
		Integer limit=Integer.parseInt(map.get("limit"));
		User user=new User();
		user.setUserName(map.get("selectText"));
		user.setRole(map.get("role"));
		
		PageHelper.startPage(page,limit);
		PageInfo pageInfo = new PageInfo(userService.getUserInfoByCondition(user));
		return pageInfo;
	}
	
	/**
	 * 查找所有用户
	 * @param pageInfo
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/admin/selectAllUser", method = RequestMethod.POST)
	@ResponseBody
	public List<User> selectAllUser() {
		
		return userService.user();
	}
	
	@RequestMapping(value = "/admin/contect", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userInfoDataTable(PageInformation pageInfo, Contect contect) {
		return contectService.getContectByUserId(pageInfo,contect.getUserId());
	}
	
	
	
	@RequestMapping(value = "/admin/user/checkUserName", method = RequestMethod.POST)
	@ResponseBody
	public String checkUserName(HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, IOException {
		int i = 0;
		String id=request.getParameter("id");
		String userName=request.getParameter("userName");
		User user = new User();
		if (!StringUtils.isEmpty(id)) {
			user.setId(Integer.parseInt(id));
			user.setUserName(userName);
			i = userService.checkUpdateUserName(user);
		} else {
			i = userService.checkAddUserName(userName);
		}
		if (i > 0) {
			response.getWriter().write("false");
		} else {
			response.getWriter().write("true");
		}
		return null;
	}
}
