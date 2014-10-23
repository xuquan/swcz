package com.jm.swcz.ui.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.jm.swcz.R;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.MaterialType;
import com.jm.swcz.model.User;
import com.jm.swcz.service.LoginService;
import com.jm.swcz.service.MaterialTypeService;

/**
 * 部门的界面
 * @author lenovo
 *
 */
public class MaterialTypeDetailActivity extends Activity implements OnClickListener {
	private LoginService loginService;
	private MaterialTypeService materialTypeService;
	private MaterialType materialType;
	private EditText et_material_type_name;
	private EditText et_remark;
	private EditText et_username;
	private EditText et_operate_time;
	private Button btn_save_material_type;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.material_type_detail_activity);
		this.setTitle("物料类别");
		loginService = (LoginService) BeanFactory.getInstance().getBean(LoginService.class);
		materialTypeService = (MaterialTypeService) BeanFactory.getInstance().getBean(MaterialTypeService.class);
		Intent intent = getIntent();
		String materialTypeId = intent.getStringExtra("materialTypeId");
		materialType = materialTypeService.findMaterialTypeById(materialTypeId);
		initView();
	}
	
	private void initView(){
		et_material_type_name = (EditText) findViewById(R.id.et_material_type_name);
		et_remark = (EditText) findViewById(R.id.et_remark);
		et_username = (EditText) findViewById(R.id.et_username);
		et_operate_time = (EditText) findViewById(R.id.et_operate_time);
		btn_save_material_type = (Button) findViewById(R.id.btn_save_material_type);
		btn_save_material_type.setOnClickListener(this);
		if(materialType!=null){
			et_material_type_name.setText(materialType.getMaterial_type_name());
			et_remark.setText(materialType.getRemark());
			User user = materialType.getUser();
			if(user!=null){
				et_username.setText(user.getUsername());
			}
			et_operate_time.setText(materialType.getOperate_time());
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.action_bar_del, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_bar_del:
			if(materialType!=null){
				boolean flag = materialTypeService.deleteMaterialType(materialType.getMaterial_type_id());
				if(flag){
					finish();
				}
			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		if(materialType==null){
			materialType = new MaterialType();
		}
		materialType.setMaterial_type_name(et_material_type_name.getText().toString());
		materialType.setRemark(et_remark.getText().toString());
		Map<String,?> map = loginService.getSharePreference("login");
		if(map!=null && !map.isEmpty()){
			materialType.setUser_id(map.get("user_id").toString());
		}
		materialType.setOperate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		materialTypeService.saveMaterialType(materialType);
		finish();
	}
	
}
