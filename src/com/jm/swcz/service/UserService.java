package com.jm.swcz.service;

import com.jm.swcz.model.User;

public class UserService {
	
	public boolean checkUser(String username,String password){
		boolean flag = false;
		if("admin".equals(username) && "sa".equals(password)){
			flag = true;
		}
		return flag;
	}
	
	public User findUser(String username){
		return null;
	}
}
