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
import com.jm.swcz.model.FaultReason;
import com.jm.swcz.service.FaultReasonService;

/**
 * 故障原因界面
 * @author lenovo
 *
 */
public class FaultReasonDetailActivity extends Activity implements OnClickListener {

	private FaultReasonService reasonService;
	private FaultReason reason;
	private EditText et_reason_name;
	private Button btn_save_fault_reason;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fault_reason_detail_activity);
		this.setTitle("故障原因");
		reasonService = (FaultReasonService) BeanFactory.getInstance().getBean(FaultReasonService.class);
		Intent intent = getIntent();
		String reasonId = intent.getStringExtra("reasonId");
		reason = reasonService.findFaultReasonById(reasonId);
		initView();
	}
	
	private void initView(){
		et_reason_name = (EditText) findViewById(R.id.et_reason_name);
		btn_save_fault_reason = (Button) findViewById(R.id.btn_save_fault_reason);
		btn_save_fault_reason.setOnClickListener(this);
		if(reason!=null){
			et_reason_name.setText(reason.getReason_name());
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
			if(reason!=null){
				boolean flag = reasonService.deleteFaultReason(reason.getReason_id());
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
		if(reason==null){
			reason = new FaultReason();
		}
		reason.setReason_name(et_reason_name.getText().toString());
		
		boolean flag = reasonService.saveFaultReason(reason);
		if(flag){
			finish();
		}
	}
	
}
