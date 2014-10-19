package com.jm.swcz.service;

import android.text.TextUtils;

import com.jm.swcz.dao.UserDao;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.User;

/**
 * 用户服务层
 * @author lenovo
 *
 */
public class UserService {
	
	private UserDao userDao = (UserDao) BeanFactory.getInstance().getBean(UserDao.class);
	
	public boolean saveUser(User user){
		boolean flag = false;
		String userId = user.getUser_id();
		if(TextUtils.isEmpty(userId)){
			flag = userDao.saveUser(user);
		}else{
			flag = userDao.updateUser(user);
		}
		return flag;
	}
	
	public boolean updateUser(User user){
		return userDao.updateUser(user);
	}
	
	public boolean deleteUser(String userId){
		return userDao.deleteUser(userId);
	}
	
	public User findUserById(String userId){
		return userDao.findUserById(userId);
	}
	
	public User findUser(String username){
		return userDao.findUser(username);
	}
}
