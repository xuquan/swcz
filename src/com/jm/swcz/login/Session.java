package com.jm.swcz.login;

import java.util.HashMap;
import java.util.Map;

import com.jm.swcz.model.User;

public class Session {

	private static Map<String,User> sessions = new HashMap<String,User>();
	
	private static Session instance = new Session();
	
	private Session(){
		
	}
	
	public static Session getInstance(){
		return instance;
	}
	
	public void putSession(String username,User user){
		sessions.put(username, user);
	}
	
	public User getSession(String username){
		return sessions.get(username);
	}
}
