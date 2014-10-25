package com.jm.swcz.ui.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.jm.swcz.R;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.Decision;
import com.jm.swcz.model.Fault;
import com.jm.swcz.model.FaultReason;
import com.jm.swcz.service.DecisionService;
import com.jm.swcz.service.FaultReasonService;
import com.jm.swcz.service.FaultService;

/**
 * 决策的界面
 * @author lenovo
 *
 */
public class DecisionDetailActivity extends Activity implements OnClickListener {
	private FaultService faultService;
	private FaultReasonService faultReasonService;
	private DecisionService decisionService;
	private Decision decision;
	private List<Fault> faultList;
	private List<FaultReason> faultReasonList;
	private ArrayAdapter<Fault> faultAdapter;
	private ArrayAdapter<Fault> fault2Adapter;
	private ArrayAdapter<FaultReason> faultReasonAdapter;
	private ArrayAdapter<String> levelAdapter;
	private Spinner sp_fault,sp_fault2,sp_fault_level,sp_fault_reason;
	private EditText et_proportion;
	private Button btn_save_decision;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.decision_detail_activity);
		this.setTitle("决策");
		decisionService = (DecisionService) BeanFactory.getInstance().getBean(DecisionService.class);
		faultService = (FaultService) BeanFactory.getInstance().getBean(FaultService.class);
		faultReasonService = (FaultReasonService) BeanFactory.getInstance().getBean(FaultReasonService.class);
		Intent intent = getIntent();
		String decisionId = intent.getStringExtra("decisionId");
		decision = decisionService.findDecisionById(decisionId);
		if(decision==null){
			decision = new Decision();
		}
		faultList = faultService.findFaultList();
		Fault fault = new Fault();
		fault.setFault_id("");
		fault.setFault_name("请选择");
		faultList.add(fault);
		Collections.reverse(faultList);
		faultAdapter = new ArrayAdapter<Fault>(this,android.R.layout.simple_spinner_item,faultList);
		faultAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		fault2Adapter = new ArrayAdapter<Fault>(this,android.R.layout.simple_spinner_item,faultList);
		fault2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		faultReasonList = faultReasonService.findFaultReasonList();
		faultReasonAdapter = new ArrayAdapter<FaultReason>(this,android.R.layout.simple_spinner_item,faultReasonList);
		faultReasonAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		List<String> levelList = new ArrayList<String>();
		levelList.add("请选择");
		levelList.add("Low");
		levelList.add("High");
		levelAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,levelList);
		levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		initView();
	}
	
	private void initView(){
		sp_fault = (Spinner) findViewById(R.id.sp_fault);
		sp_fault.setAdapter(faultAdapter);
		sp_fault.setOnItemSelectedListener(new FaultSelectedListener());
		sp_fault.setVisibility(View.VISIBLE);
		sp_fault2 = (Spinner) findViewById(R.id.sp_fault2);
		sp_fault2.setAdapter(fault2Adapter);
		sp_fault2.setOnItemSelectedListener(new Fault2SelectedListener());
		sp_fault2.setVisibility(View.VISIBLE);
		
		sp_fault_level = (Spinner) findViewById(R.id.sp_fault_level);
		sp_fault_level.setAdapter(levelAdapter);
		sp_fault_level.setOnItemSelectedListener(new LevelSelectedListener());
		sp_fault_level.setVisibility(View.VISIBLE);
		
		sp_fault_reason = (Spinner) findViewById(R.id.sp_fault_reason);
		sp_fault_reason.setAdapter(faultReasonAdapter);
		sp_fault_reason.setOnItemSelectedListener(new FaultReasonSelectedListener());
		sp_fault_reason.setVisibility(View.VISIBLE);
		
		et_proportion = (EditText) findViewById(R.id.et_proportion);
		btn_save_decision = (Button) findViewById(R.id.btn_save_decision);
		btn_save_decision.setOnClickListener(this);
		if(decision!=null){
			sp_fault.setSelection(faultAdapter.getPosition(decision.getFault()));
			sp_fault2.setSelection(fault2Adapter.getPosition(decision.getFault2()));
			sp_fault_level.setSelection(levelAdapter.getPosition(decision.getLevel()));
			sp_fault_reason.setSelection(faultReasonAdapter.getPosition(decision.getReason()));
			et_proportion.setText(decision.getProportion());
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
			if(decision!=null){
				boolean flag = decisionService.deleteDecision(decision.getDecision_id());
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
		if(decision==null){
			decision = new Decision();
		}
		decision.setProportion(et_proportion.getText().toString());
		
		boolean flag = decisionService.saveDecision(decision);
		if(flag){
			finish();
		}
	}
	
	class FaultSelectedListener implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
			Fault fault = faultAdapter.getItem(position);
			if(decision!=null && fault!=null){
				decision.setFault_id(fault.getFault_id());
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			
		}
		
	}
	
	class Fault2SelectedListener implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
			Fault fault = fault2Adapter.getItem(position);
			if(decision!=null && fault!=null){
				decision.setFault_id2(fault.getFault_id());
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			
		}
		
	}
	
	class FaultReasonSelectedListener implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
			FaultReason faultReason = faultReasonAdapter.getItem(position);
			if(decision!=null && faultReason!=null){
				decision.setReason_id(faultReason.getReason_id());
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			
		}
		
	}
	
	class LevelSelectedListener implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
			String level = levelAdapter.getItem(position);
			if(decision!=null && !TextUtils.isEmpty(level)){
				decision.setLevel(level);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			
		}
		
	}
}
