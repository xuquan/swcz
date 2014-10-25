package com.jm.swcz.ui;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jm.swcz.AppContext;
import com.jm.swcz.R;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.User;
import com.jm.swcz.service.LoginService;

/**
 * 登陆界面activity
 */
public class LoginActivity extends Activity implements OnClickListener{
	public static final int MENU_PWD_BACK = 1;
	public static final int MENU_HELP = 2;
	public static final int MENU_EXIT = 3;
	
	private Button btn_login;//登录按钮
	private TextView tv_new_user; // 新用户
	private EditText et_username; // 用户名
	private EditText et_password; // 密码
	
	private Map<String,?> map = null;
	
	private LoginService loginService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppContext.setAppContext(getApplicationContext());
		loginService = (LoginService) BeanFactory.getInstance().getBean(LoginService.class);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_activity);
		initView();
		restoreUsernameAndPassword();
	}
	
	private void initView(){
		et_username = (EditText) findViewById(R.id.username);
		et_username.setOnKeyListener(new OnKeyListener(){

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(keyCode==KeyEvent.KEYCODE_DEL){
					et_password.setText("");
				}
				return false;
			}
			
		});
		et_password = (EditText) findViewById(R.id.password);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
		tv_new_user = (TextView) findViewById(R.id.tv_new_user);
		tv_new_user.setOnClickListener(this);
	}
	
	private void restoreUsernameAndPassword() {
		map = loginService.getSharePreference("login");
		if(map!=null && !map.isEmpty()){
			et_username.setText(map.get("username").toString());
			et_password.setText(map.get("password").toString());
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			String username = et_username.getText().toString();
			String password = et_password.getText().toString();
			if(null==username || "".equals(username)){
				et_username.setError("请输入用户名!");
				return;
			}
			if(null==password || "".equals(password)){
				et_password.setError("请输入密码!");
				return;
			}
			
			User user = loginService.login(username.trim(), password.trim());
			if(user==null){
				Toast toast = Toast.makeText(this, "用户名或者密码错误", Toast.LENGTH_LONG);
				toast.show();
				return;
			}
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("user_id", user.getUser_id());
			map.put("username", username);
			map.put("password", password);
			loginService.saveSharePreference("login", map);
			
			final Intent intent = new Intent(LoginActivity.this,MainActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.tv_new_user:
			final Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
			startActivity(registerIntent);
			break;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {//创建系统功能菜单
		menu.add(0, MENU_EXIT, 3, "退出").setIcon(R.drawable.menu_exit);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case MENU_PWD_BACK:
			break;
		case MENU_HELP:
			break;
		case MENU_EXIT:
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
