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
import com.jm.swcz.model.Fault;
import com.jm.swcz.service.FaultService;

/**
 * 故障现象界面
 * @author lenovo
 *
 */
public class FaultDetailActivity extends Activity implements OnClickListener {

	private FaultService faultService;
	private Fault fault;
	private EditText et_fault_name;
	private Button btn_save_fault;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fault_detail_activity);
		this.setTitle("故障现象");
		faultService = (FaultService) BeanFactory.getInstance().getBean(FaultService.class);
		Intent intent = getIntent();
		String faultId = intent.getStringExtra("faultId");
		fault = faultService.findFaultById(faultId);
		initView();
	}
	
	private void initView(){
		et_fault_name = (EditText) findViewById(R.id.et_fault_name);
		btn_save_fault = (Button) findViewById(R.id.btn_save_fault);
		btn_save_fault.setOnClickListener(this);
		if(fault!=null){
			et_fault_name.setText(fault.getFault_name());
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
			if(fault!=null){
				boolean flag = faultService.deleteFault(fault.getFault_id());
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
		if(fault==null){
			fault = new Fault();
		}
		fault.setFault_name(et_fault_name.getText().toString());
		
		boolean flag = faultService.saveFault(fault);
		if(flag){
			finish();
		}
	}
	
}
