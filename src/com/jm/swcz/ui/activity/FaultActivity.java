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
import com.jm.swcz.model.Fault;
import com.jm.swcz.service.FaultService;

/**
 * 故障现象的界面
 * @author lenovo
 *
 */
public class FaultActivity extends ListActivity {
	private FaultService faultService;
	private SimpleAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fault_activity);
		this.setTitle("故障现象");
		faultService = (FaultService) BeanFactory.getInstance().getBean(FaultService.class);
		loadData();
	}
	
	private void loadData(){
		List<Fault> faultList = faultService.findFaultList();
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		if(faultList!=null && faultList.size()>0){
			Map<String,Object> map = null;
			for(Fault fault : faultList){
				map = new HashMap<String,Object>();
				map.put("tv_fault_id", fault.getFault_id());
				map.put("tv_fault_name", fault.getFault_name());
				data.add(map);
			}
		}
		
		String[] from = new String[]{"tv_fault_name"};
		int[] to = new int[]{R.id.tv_fault_name};
		adapter = new SimpleAdapter(this, data, R.layout.fault_item_activity, from, to);
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
			Intent intent = new Intent(FaultActivity.this,FaultDetailActivity.class);
			startActivity(intent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Map<String,Object> data = (Map<String, Object>) adapter.getItem(position);
		String faultId = (String) data.get("tv_fault_id");
		Intent intent = new Intent(FaultActivity.this,FaultDetailActivity.class);
		intent.putExtra("faultId", faultId);
		startActivity(intent);
		super.onListItemClick(l, v, position, id);
	}
}
