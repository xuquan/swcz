package com.jm.swcz.service;

import com.jm.swcz.dao.UserDao;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.User;

public class UserService {
	
	private UserDao userDao = (UserDao) BeanFactory.getInstance().getBean(UserDao.class);
	
	public User findUserById(String userId){
		return userDao.findUserById(userId);
	}
	
	public User findUser(String username){
		return userDao.findUser(username);
	}
}
