package com.jm.swcz.service;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.jm.swcz.AppContext;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.User;

public class LoginService {

	private Context context;
	
	private UserService userSerive = (UserService) BeanFactory.getInstance().getBean(UserService.class);
	
	public LoginService(){
		this.context = AppContext.getAppContext();
	}
	
	public boolean saveLoginMsg(String username,String password){
		boolean flag = false;
		// 不要加后缀名，系统自动以.xml的文件保存
		SharedPreferences preferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString("username", username);
		editor.putString("password", password);
		flag = editor.commit();
		return flag;
	}
	
	/**
	 * 
	 * @param fileName
	 * @param map
	 * @return
	 */
	public boolean saveSharePreference(String fileName,Map<String,Object> map){
		boolean flag = false;
		SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		for(Map.Entry<String, Object> entry:map.entrySet()){
			String key = entry.getKey();
			Object obj = entry.getValue();
			if(obj instanceof Boolean){
				Boolean bl = (Boolean) obj;
				editor.putBoolean(key, bl);
			}else if(obj instanceof Integer){
				Integer integer = (Integer) obj;
				editor.putInt(key, integer);
			}else if(obj instanceof Float){
				Float f = (Float) obj;
				editor.putFloat(key, f);
			}else if(obj instanceof Long){
				Long l = (Long) obj;
				editor.putLong(key, l);
			}else if(obj instanceof String){
				String s = (String) obj;
				editor.putString(key, s);
			}
		}
		flag = editor.commit();
		return flag;
	}
	
	public Map<String,?> getSharePreference(String fileName){
		Map<String,?> map = null;
		SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		map = preferences.getAll();
		return map;
	}
	
	/**
	 * 登录，如果用户名与密码跟数据库的相同，则登录成功
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean login(String username,String password){
		boolean flag = false;
		User user = userSerive.findUser(username);
		if(user!=null && username.equals(user.getUsername()) 
				&& password.equals(user.getPassword())){
			flag = true;
		}
		return flag;
	}
}
