package com.sybinal.shop.service.user;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sybinal.shop.mapper.UserMapper;
import com.sybinal.shop.mapper.UserRolesMapper;
import com.sybinal.shop.mapper.rolesMapper;
import com.sybinal.shop.mapper.userShortRoleMapper;
import com.sybinal.shop.model.User;
import com.sybinal.shop.model.UserExample;
import com.sybinal.shop.model.UserRoles;
import com.sybinal.shop.model.roles;
import com.sybinal.shop.model.userShortRole;
import com.sybinal.shop.utils.Constants;
import com.sybinal.shop.utils.PagingTool;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	UserRolesMapper userRolesMapper;

	@Autowired
	PagingTool pagingTool;
	
	@Autowired
	userShortRoleMapper shortrole;
	
	@Autowired
	rolesMapper rolw;
	@Override
	@Transactional
	public int saveUser(User user) throws IllegalAccessException, InvocationTargetException {
		int cnt = 0;
		if (user != null) {
			Md5PasswordEncoder md5PasswordEncode = new Md5PasswordEncoder();
			user.setEnabled(1);
			user.setRegisterTime(new Date());
			user.setLastLoginTime(new Date());
			String encodePassword = md5PasswordEncode.encodePassword(user.getPwd(), null);
			user.setPwd(encodePassword);
			cnt += userMapper.insert(user);
			
			UserRoles userRoles = new UserRoles();
			userRoles.setUsername(user.getUserName());
			userRoles.setRole(Constants.ROLE_USER);
			cnt += userRolesMapper.insert(userRoles);

		}
		return cnt;
	}


	@Override
	@Transactional
	public int modUser(User user) {
		int cnt = 0;
		if (user != null) {
			if (StringUtils.isEmpty(user.getPwd()) == false) {
				Md5PasswordEncoder md5PasswordEncode = new Md5PasswordEncoder();
				String encodePassword = md5PasswordEncode.encodePassword(user.getPwd(), null);
				user.setPwd(encodePassword);
			}
			UserExample example = new UserExample();
			UserExample.Criteria criteria = example.createCriteria();
			criteria.andIdEqualTo(user.getId());
			cnt = userMapper.updateByExampleSelective(user, example);
		}
		return cnt;
	}


	@Override
	@Transactional
	public int modUserPassword(Map<String, Object> reqMap) {
		int cnt = 0;
		User user = new User();
		if (reqMap != null) {
			if (!StringUtils.isEmpty((String) reqMap.get("rpassword"))) {
				Md5PasswordEncoder md5PasswordEncode = new Md5PasswordEncoder();
				String encodePassword = md5PasswordEncode.encodePassword((String) reqMap.get("rpassword"), null);
				user.setPwd(encodePassword);
			}
			String oldpassword = null;
			if (!StringUtils.isEmpty((String) reqMap.get("oldpassword"))) {
				Md5PasswordEncoder md5PasswordEncode = new Md5PasswordEncoder();
				oldpassword = md5PasswordEncode.encodePassword((String) reqMap.get("oldpassword"), null);
			}
			UserExample example = new UserExample();
			UserExample.Criteria criteria = example.createCriteria();
			criteria.andIdEqualTo((int) reqMap.get("userId"));
			criteria.andPwdEqualTo(oldpassword);
			cnt = userMapper.updateByExampleSelective(user, example);
		}
		return cnt;
	}

	@Override
	@Transactional
	public int recoveryUserPassword(User user) {
		int cnt = 0;
		if (user != null) {
			if (!StringUtils.isEmpty(user.getPwd()) && user.getPwd().equals(user.getConfirmPassword())) {
				Md5PasswordEncoder md5PasswordEncode = new Md5PasswordEncoder();
				String encodePassword = md5PasswordEncode.encodePassword(user.getPwd(), null);
				user.setPwd(encodePassword);
			}
			UserExample example = new UserExample();
			UserExample.Criteria criteria = example.createCriteria();
			criteria.andUserNameEqualTo(user.getUserName());
			cnt = userMapper.updateByExampleSelective(user, example);
		}
		return cnt;
	}
	
	@Override
	@Transactional
	public Map<String, Object> delUser(Map<String, Object> data) {

		return null;
	}

	@Override
	public User getUser(User user) {
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andUserNameEqualTo(user.getUserName());
		if (StringUtils.isEmpty(user.getPwd()) == false) {
			Md5PasswordEncoder md5PasswordEncode = new Md5PasswordEncoder();
			String encodePassword = md5PasswordEncode.encodePassword(user.getPwd(), null);
			criteria.andPwdEqualTo(encodePassword);
			User updateUser = new User();
			updateUser.setLastLoginTime(new Date());
			userMapper.updateByExampleSelective(updateUser, example);
		}
		List<User> userlist = userMapper.selectByExample(example);
		if (userlist != null && userlist.size() > 0) {
			return userlist.get(0);
		}
		return null;
	}

	@Override
	public User getUser(Map<String, Object> reqMap) {
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo((int) reqMap.get("userId"));
		List<User> userlist = userMapper.selectByExample(example);
		if (userlist != null && userlist.size() > 0) {
			User user = new User();
			user.setId(userlist.get(0).getId());
			user.setNickname(userlist.get(0).getNickname());
			user.setEmail(userlist.get(0).getEmail());
			return user;
		}
		return null;
	}

	@Override
	public List<User> getUserInfoByCondition(User user) {
		
		return userMapper.selectUserByCondition(user);
	}
	@Override
	public User getUserById(Integer userId) {
		return userMapper.selectById(userId);
	}

	@Override
	@Transactional
	public int saveUserForManage(User user) throws IllegalAccessException, InvocationTargetException {
		int cnt = 0;
		if (user != null) {
			Md5PasswordEncoder md5PasswordEncode = new Md5PasswordEncoder();
			user.setEnabled(1);
			user.setRegisterTime(new Date());
			user.setLastLoginTime(new Date());
			String encodePassword = md5PasswordEncode.encodePassword(user.getPwd(), null);
			user.setPwd(encodePassword);
			cnt += userMapper.insert(user);
			
			UserRoles userRoles=new UserRoles();
			userRoles.setUsername(user.getUserName());
			//userRoles.setRole(userRoles.getRole());
			userRoles.setRole(user.getRole());
			cnt += userRolesMapper.insert(userRoles);

		}
		return cnt;
	}

	@Override
	@Transactional
	public int updateUserForManage(User user) {
		int cnt = 0;
		if (user != null) {
			UserExample example = new UserExample();
			UserExample.Criteria criteria = example.createCriteria();
			criteria.andIdEqualTo(user.getId());
			Md5PasswordEncoder md5PasswordEncode = new Md5PasswordEncoder();
			String encodePassword = md5PasswordEncode.encodePassword(user.getPwd(), null);
			user.setPwd(encodePassword);
			cnt = userMapper.updateByExampleSelective(user, example);
		}
		return cnt;
	}
	
	
	
	/**
	 * 添加时验证用户名是否重复
	 */
	@Override
	public int checkAddUserName(String userName) {
		return userMapper.selectCountUserNameByCondition(userName);
	}
	
	
	/**
	 * 修改时验证用户名是否重复
	 */
	@Override
	public int checkUpdateUserName(User record) {
		return userMapper.selectCountUserNameByUpdate(record);
	}

	/**
	 * 查找权限
	 */
	@Override
	public User jurisdiction(User user) {
		// TODO Auto-generated method stub.
		/*
		 * 查找所有的权限
		 */
		roles role=rolw.jurisdiction(user.getUserName());
		System.out.println(role);
		String[] split = role.getRoleRoles().split(",");
        List<String> strings = Arrays.asList(split);
        //存储
        String result="";
        List<userShortRole> stors=shortrole.selall(strings);
        for(userShortRole c:stors) {
        	result+=c.getShortRole()+",";
        }
        user.setJurisdiction(result);
		return user;
	}


	@Override
	public User Justiactions(String username) {
		// TODO Auto-generated method stub
		return userMapper.Justiactions(username);
	}

	/**
	 * 删除用户
	 */
	@Override
	public int delUser(User user) {
		// TODO Auto-generated method stub
		int cnt = 0;
		if (user != null) {
			
			cnt += userMapper.deleteByPrimaryKey(user.getId());
			UserRoles userRoles=new UserRoles();
			userRoles.setUsername(user.getUserName());
			cnt += userRolesMapper.delUserRoles(userRoles);

		}
		return cnt;
	}

	/**
	 * 查找角色名
	 * @param user
	 * @return
	 */
	@Override
	public List<User>  uasername() {
		// TODO Auto-generated method stub
		return userMapper.uasername();
	}


	@Override
	public List<User> user() {
		// TODO Auto-generated method stub
		return userMapper.user();
	}

	
}
