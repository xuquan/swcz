package com.jm.swcz.dao;

import com.jm.swcz.AppContext;
import com.jm.swcz.db.DBMgr;
import com.jm.swcz.model.User;

public class UserDao{
	
	private DBMgr dbMgr = new DBMgr(AppContext.getAppContext());

	public void saveUser(User user){
		String sql = "insert into user (user_id,username,password,real_name,email,address,mobile_phone) values (?,?,?,?,?,?,?)";
		Object[] bindArgs = new Object[]{user.getUserId(),user.getUsername(),user.getPassword(),
				user.getRealName(),user.getEmail(),user.getAddress(),user.getMobilePhone()};
		dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public void updateUser(User user){
		String sql = "update user set username=?,real_name=?,email=?,address=?,mobile_phone=? where user_id=?";
		Object[] bindArgs = new Object[]{user.getUsername(),user.getRealName(),user.getEmail(),
				user.getAddress(),user.getMobilePhone(),user.getUserId()};
		dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public void deleteUser(int userId){
		String sql = "delete from user where user_id=?";
		Object[] bindArgs = new Object[]{userId};
		dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public User findUser(int userId){
		User user = null;
		
		return user;
	}
}
