package com.jm.swcz.dao;

import android.text.TextUtils;

import com.jm.swcz.db.DBMgr;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.User;

/**
 * 用户持久层
 * @author lenovo
 *
 */
public class UserDao{
	
	private DBMgr dbMgr = (DBMgr) BeanFactory.getInstance().getBean(DBMgr.class);

	public boolean saveUser(User user){
		String sql = "insert into t_user (user_id,username,password,real_name,email,address,mobile_phone) values (?,?,?,?,?,?,?)";
		Object[] bindArgs = new Object[]{user.getUser_id(),user.getUsername(),user.getPassword(),
				user.getReal_name(),user.getEmail(),user.getAddress(),user.getMobile_phone()};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public boolean updateUser(User user){
		String sql = "update t_user set real_name=?,email=?,address=?,mobile_phone=? where user_id=?";
		Object[] bindArgs = new Object[]{user.getReal_name(),user.getEmail(),
				user.getAddress(),user.getMobile_phone(),user.getUser_id()};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public boolean deleteUser(String userId){
		String sql = "delete from t_user where user_id=?";
		Object[] bindArgs = new Object[]{userId};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public User findUserById(String userId){
		User user = null;
		String sql = "select * from t_user where user_id=?";
		String[] selectionArgs = new String[]{};
		if(!TextUtils.isEmpty(userId)){
			selectionArgs = new String[]{userId};
		}
		user = dbMgr.querySingleCursor(sql, selectionArgs, User.class);
		return user;
	}
	
	public User findUser(String username){
		User user = null;
		String sql = "select * from t_user where username=?";
		String[] selectionArgs = new String[]{username};
		user = dbMgr.querySingleCursor(sql, selectionArgs, User.class);
		return user;
	}
}
