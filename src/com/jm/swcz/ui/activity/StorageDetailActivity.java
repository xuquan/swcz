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
import com.jm.swcz.model.Storage;
import com.jm.swcz.model.User;
import com.jm.swcz.service.LoginService;
import com.jm.swcz.service.StorageService;

/**
 * 库位详情的界面
 * @author lenovo
 *
 */
public class StorageDetailActivity extends Activity implements OnClickListener {
	private LoginService loginService;
	private StorageService storageService;
	private Storage storage;
	private EditText et_storage_name;
	private EditText et_ship_code;
	private EditText et_remark;
	private EditText et_real_name;
	private EditText et_operate_time;
	private Button btn_save_storage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.storage_detail_activity);
		this.setTitle("库位管理");
		loginService = (LoginService) BeanFactory.getInstance().getBean(LoginService.class);
		storageService = (StorageService) BeanFactory.getInstance().getBean(StorageService.class);
		Intent intent = getIntent();
		String storageId = intent.getStringExtra("storageId");
		storage = storageService.findStorageById(storageId);
		initView();
	}
	
	private void initView(){
		et_storage_name = (EditText) findViewById(R.id.et_storage_name);
		et_ship_code = (EditText) findViewById(R.id.et_ship_code);
		et_remark = (EditText) findViewById(R.id.et_remark);
		et_real_name = (EditText) findViewById(R.id.et_real_name);
		et_operate_time = (EditText) findViewById(R.id.et_operate_time);
		btn_save_storage = (Button) findViewById(R.id.btn_save_storage);
		btn_save_storage.setOnClickListener(this);
		if(storage!=null){
			et_storage_name.setText(storage.getStorage_name());
			et_ship_code.setText(storage.getShip_code());
			et_remark.setText(storage.getRemark());
			User user = storage.getUser();
			if(user!=null){
				et_real_name.setText(user.getReal_name());
			}
			et_operate_time.setText(storage.getOperate_time());
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
			if(storage!=null){
				boolean flag = storageService.deleteStorage(storage.getStorage_id());
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
		if(storage==null){
			storage = new Storage();
		}
		storage.setStorage_name(et_storage_name.getText().toString());
		storage.setShip_code(et_ship_code.getText().toString());
		storage.setRemark(et_remark.getText().toString());
		Map<String,?> map = loginService.getSharePreference("login");
		if(map!=null && !map.isEmpty()){
			storage.setUser_id(map.get("user_id").toString());
		}
		storage.setOperate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		boolean flag = storageService.saveStorage(storage);
		if(flag){
			finish();
		}
	}
	
}
