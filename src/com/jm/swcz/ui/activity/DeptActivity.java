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
import com.jm.swcz.model.Dept;
import com.jm.swcz.service.DeptService;

/**
 * 部门的界面
 * @author lenovo
 *
 */
public class DeptActivity extends ListActivity {
	private DeptService deptService;
	private SimpleAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setTitle("部门");
		deptService = (DeptService) BeanFactory.getInstance().getBean(DeptService.class);
		loadData();
	}
	
	private void loadData(){
		List<Dept> deptList = deptService.findDeptList();
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		if(deptList!=null && deptList.size()>0){
			Map<String,Object> map = null;
			for(Dept dept : deptList){
				map = new HashMap<String,Object>();
				map.put("tv_dept_id", dept.getDept_id());
				map.put("tv_dept_name", dept.getDept_name());
				data.add(map);
			}
		}
		
		String[] from = new String[]{"tv_dept_name"};
		int[] to = new int[]{R.id.tv_dept_name};
		adapter = new SimpleAdapter(this, data, R.layout.dept_activity, from, to);
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
			Intent intent = new Intent(DeptActivity.this,DeptDetailActivity.class);
			startActivity(intent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Map<String,Object> data = (Map<String, Object>) adapter.getItem(position);
		String deptId = (String) data.get("tv_dept_id");
		Intent intent = new Intent(DeptActivity.this,DeptDetailActivity.class);
		intent.putExtra("deptId", deptId);
		startActivity(intent);
		super.onListItemClick(l, v, position, id);
	}
}
