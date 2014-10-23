package com.jm.swcz.ui.activity;

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
import com.jm.swcz.model.Dept;
import com.jm.swcz.service.DeptService;

/**
 * 部门的界面
 * @author lenovo
 *
 */
public class DeptDetailActivity extends Activity implements OnClickListener {

	private DeptService deptService;
	private Dept dept;
	private EditText et_dept_name;
	private EditText et_dept_contactor;
	private EditText et_telephone;
	private EditText et_address;
	private Button btn_save_dept;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dept_detail_activity);
		this.setTitle("部门");
		deptService = (DeptService) BeanFactory.getInstance().getBean(DeptService.class);
		Intent intent = getIntent();
		String deptId = intent.getStringExtra("deptId");
		dept = deptService.findDeptById(deptId);
		initView();
	}
	
	private void initView(){
		et_dept_name = (EditText) findViewById(R.id.et_dept_name);
		et_dept_contactor = (EditText) findViewById(R.id.et_dept_contactor);
		et_telephone = (EditText) findViewById(R.id.et_telephone);
		et_address = (EditText) findViewById(R.id.et_address);
		btn_save_dept = (Button) findViewById(R.id.btn_save_dept);
		btn_save_dept.setOnClickListener(this);
		if(dept!=null){
			et_dept_name.setText(dept.getDept_name());
			et_dept_contactor.setText(dept.getDept_contactor());
			et_telephone.setText(dept.getTelephone());
			et_address.setText(dept.getAddress());
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
			if(dept!=null){
				boolean flag = deptService.deleteDept(dept.getDept_id());
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
		if(dept==null){
			dept = new Dept();
		}
		dept.setDept_name(et_dept_name.getText().toString());
		dept.setDept_contactor(et_dept_contactor.getText().toString());
		dept.setTelephone(et_telephone.getText().toString());
		dept.setAddress(et_address.getText().toString());
		
		deptService.saveDept(dept);
		finish();
	}
	
}
