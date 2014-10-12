package com.jm.swcz.ui;

import java.util.UUID;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jm.swcz.R;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.User;
import com.jm.swcz.service.UserService;

/**
 * 注册界面activity
 */
public class RegisterActivity extends Activity implements android.view.View.OnClickListener{
	public static final int REGION_SELECT = 1;
	private Button btn_save_user;
	private EditText et_username,et_password,et_repeat_password,
		et_mobile_phone,et_real_name,et_email;
	private UserService userService = (UserService) BeanFactory.getInstance().getBean(UserService.class);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_activity);
		initView();
	}
	
	private void initView(){
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		et_repeat_password = (EditText) findViewById(R.id.et_repeat_password);
		et_mobile_phone = (EditText) findViewById(R.id.et_mobile_phone);
		et_real_name = (EditText) findViewById(R.id.et_real_name);
		et_email = (EditText) findViewById(R.id.et_email);
		btn_save_user = (Button) findViewById(R.id.btn_save_user);
		btn_save_user.setOnClickListener(this);
	}
	
	private User checkAndGetInput(){
		String username = et_username.getText().toString();
		String password = et_password.getText().toString();
		String repeat_password = et_repeat_password.getText().toString();
		String real_name = et_real_name.getText().toString();
		String mobile_phone = et_mobile_phone.getText().toString();
		String email = et_email.getText().toString();
		
		if(username==null || "".equals(username)){
			et_username.setError("用户名必填");
			return null;
		}
		User userIsExist = userService.findUser(username);
		if(userIsExist!=null){
			et_username.setError("用户名已存在，请输入其他用户名");
			return null;
		}
		if(password==null || "".equals(password)){
			et_password.setError("密码必填");
			return null;
		}
		if(!password.equals(repeat_password)){
			et_repeat_password.setError("重复密码与密码不一致");
			return null;
		}
		if(real_name==null || "".equals(real_name)){
			et_real_name.setError("姓名必填");
			return null;
		}
		User user = new User();
		user.setUser_id(UUID.randomUUID().toString());
		user.setUsername(username.trim());
		user.setPassword(password.trim());
		user.setReal_name(real_name.trim());
		user.setMobile_phone(mobile_phone.trim());
		user.setEmail(email.trim());
		return user;
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_save_user:
			User user = checkAndGetInput();
			if(user!=null){
				boolean flag = userService.saveUser(user);
				String msg = "";
				if(flag){
					msg = "注册成功";
					Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
					this.finish();
				}else{
					msg = "注册失败";
					Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
				}
			}
		}
		
	}

	

}
