package com.jm.swcz.service;

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
		return userDao.saveUser(user);
	}
	
	public User findUserById(String userId){
		return userDao.findUserById(userId);
	}
	
	public User findUser(String username){
		return userDao.findUser(username);
	}
}
