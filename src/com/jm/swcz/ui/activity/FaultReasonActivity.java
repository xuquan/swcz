package com.jm.swcz.ui.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.jm.swcz.R;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.FaultReason;
import com.jm.swcz.service.FaultReasonService;

/**
 * 故障原因的界面
 * @author lenovo
 *
 */
public class FaultReasonActivity extends ListActivity {
	private FaultReasonService reasonService;
	private SimpleAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fault_reason_activity);
		this.setTitle("故障原因");
		reasonService = (FaultReasonService) BeanFactory.getInstance().getBean(FaultReasonService.class);
		loadData();
	}
	
	private void loadData(){
		List<FaultReason> reasonList = reasonService.findFaultReasonList();
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		if(reasonList!=null && reasonList.size()>0){
			Map<String,Object> map = null;
			for(FaultReason reason : reasonList){
				map = new HashMap<String,Object>();
				map.put("tv_reason_id", reason.getReason_id());
				map.put("tv_reason_name", reason.getReason_name());
				data.add(map);
			}
		}
		
		String[] from = new String[]{"tv_reason_name"};
		int[] to = new int[]{R.id.tv_reason_name};
		adapter = new SimpleAdapter(this, data, R.layout.fault_reason_item_activity, from, to);
		setListAdapter(adapter);
	}
	
	@Override
	protected void onResume() {
		loadData();
		super.onResume();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.action_bar_add, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_bar_add:
			Intent intent = new Intent(FaultReasonActivity.this,FaultReasonDetailActivity.class);
			startActivity(intent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Map<String,Object> data = (Map<String, Object>) adapter.getItem(position);
		String reasonId = (String) data.get("tv_reason_id");
		Intent intent = new Intent(FaultReasonActivity.this,FaultReasonDetailActivity.class);
		intent.putExtra("reasonId", reasonId);
		startActivity(intent);
		super.onListItemClick(l, v, position, id);
	}
}
