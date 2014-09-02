package com.jm.swcz.login;

public class UserService {
	
	public boolean checkUser(String username,String password){
		boolean flag = false;
		if("admin".equals(username) && "sa".equals(password)){
			flag = true;
		}
		return flag;
	}
}
