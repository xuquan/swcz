package com.jm.swcz.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jm.swcz.R;
import com.jm.swcz.util.CheckUtil;

/**
 * 注册界面activity
 */
public class RegisterActivity extends Activity implements android.view.View.OnClickListener{
	public static final int REGION_SELECT = 1;
	private TextView tv_top_title;
	private Button btn_title_left,btn_title_right,btn_send_code;
	private CheckBox chk_agree;
	private EditText et_mobileNo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register_activity);
		initView();
	}
	
	private void initView(){
		btn_send_code = (Button) findViewById(R.id.btn_save);
		btn_send_code.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_save:
			String mobiles = et_mobileNo.getText().toString();
			if(chk_agree.isChecked()== false)//若没勾选checkbox无法后续操作
				Toast.makeText(this, "请确认是否已经阅读《腾讯QQ服务条款》", Toast.LENGTH_LONG).show();
			if(CheckUtil.isMobileNO(mobiles)==false)//对手机号码严格验证，参见工具类中的正则表达式
				Toast.makeText(this, "正确填写手机号，我们将向您发送一条验证码短信", Toast.LENGTH_LONG).show();
			if(CheckUtil.isMobileNO(mobiles)==true&&chk_agree.isChecked()==true){
				//当勾选中且号码正确，点击进行下一步操作
				Toast.makeText(this, "已经向您手机发送验证码，请查看", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(RegisterActivity.this, RegisterConfirmActivity.class);
				startActivity(intent);
			}
		}
		
	}

	

}
