package com.jm.swcz.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jm.swcz.AppContext;
import com.jm.swcz.R;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.service.LoginService;
import com.jm.swcz.ui.tabs.MainTab;

/**
 * 登陆界面activity
 */
public class LoginActivity extends Activity implements OnClickListener{
	private Button btn_login;//登录按钮
	
	public static final int MENU_PWD_BACK = 1;
	public static final int MENU_HELP = 2;
	public static final int MENU_EXIT = 3;
	
	private LoginService loginSerive;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppContext.setAppContext(getApplicationContext());
		loginSerive = (LoginService) BeanFactory.getInstance().getBean(LoginService.class);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		initView();
	}
	
	private void initView(){
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			EditText username_et = (EditText) findViewById(R.id.username);
			EditText password_et = (EditText) findViewById(R.id.password);
			String username = username_et.getText().toString();
			String password = password_et.getText().toString();
			if(null==username || "".equals(username)){
				username_et.setError("请输入用户名!");
				return;
			}
			if(null==password || "".equals(password)){
				password_et.setError("请输入密码!");
				return;
			}
			
			boolean isLogin = loginSerive.login(username.trim(), password.trim());
			if(!isLogin){
				Toast toast = Toast.makeText(this, "用户名或者密码错误", Toast.LENGTH_LONG);
				toast.show();
				return;
			}
			
			loginSerive.saveLoginMsg(username, password);
			
			final Intent intent = new Intent(LoginActivity.this,MainTab.class);
			startActivity(intent);
			break;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {//创建系统功能菜单
		//menu.add(0, MENU_PWD_BACK, 1, "密码找回").setIcon(R.drawable.menu_findkey);
		//menu.add(0,MENU_HELP,2,"帮助").setIcon(R.drawable.menu_setting);
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
