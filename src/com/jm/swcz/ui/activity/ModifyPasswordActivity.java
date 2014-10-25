package com.jm.swcz.ui.activity;

import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jm.swcz.R;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.User;
import com.jm.swcz.service.LoginService;
import com.jm.swcz.service.UserService;

/**
 * 修改密码界面
 * @author lenovo
 *
 */
public class ModifyPasswordActivity extends Activity implements OnClickListener {
	private LoginService loginService;
	private UserService userService;
	private User user;
	private EditText et_old_password;
	private EditText et_new_password;
	private Button btn_save_password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modify_password_activity);
		this.setTitle("修改密码");
		loginService = (LoginService) BeanFactory.getInstance().getBean(LoginService.class);
		userService = (UserService) BeanFactory.getInstance().getBean(UserService.class);
		initView();
	}
	
	private void initView(){
		et_old_password = (EditText) findViewById(R.id.et_old_password);
		et_new_password = (EditText) findViewById(R.id.et_new_password);
		btn_save_password = (Button) findViewById(R.id.btn_save_password);
		btn_save_password.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		Map<String,?> map = loginService.getSharePreference("login");
		if(map!=null && !map.isEmpty()){
			String userId = map.get("user_id").toString();
			String oldPassword = et_old_password.getText().toString().trim();
			String newPassword = et_new_password.getText().toString().trim();
			
			user = userService.findUserById(userId);
			if(TextUtils.isEmpty(oldPassword)){
				et_old_password.setError("请输入原密码");
				return;
			}
			if(TextUtils.isEmpty(newPassword)){
				et_new_password.setError("请输入新密码");
				return;
			}
			if(!oldPassword.equals(user.getPassword())){
				et_old_password.setError("原密码不正确");
				return;
			}
			
			boolean flag = userService.modifyPassword(userId, newPassword);
			if(flag){
				Toast.makeText(this, "修改密码成功", Toast.LENGTH_SHORT);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				finish();
			}else{
				Toast.makeText(this, "修改密码失败", Toast.LENGTH_SHORT);
			}
		}
	}
	
}
