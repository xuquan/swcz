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
import android.widget.SimpleAdapter;

import com.jm.swcz.R;
import com.jm.swcz.ui.fragment.MaterialDetailFragment;

/**
 * 部门的界面
 * @author lenovo
 *
 */
public class DeptDetailActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setTitle("部门");
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tv_dept_name", "省公司");
		data.add(map);
		map = new HashMap<String,Object>();
		String[] from = new String[]{"tv_dept_name"};
		map.put("tv_dept_name", "广州公司");
		data.add(map);
		int[] to = new int[]{R.id.tv_dept_name};
		SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.dept_activity, from, to);
		setListAdapter(adapter);
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
			Intent intent = new Intent(DeptDetailActivity.this,MaterialDetailFragment.class);
			startActivity(intent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
