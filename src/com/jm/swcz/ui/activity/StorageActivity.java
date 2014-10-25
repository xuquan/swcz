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
import com.jm.swcz.model.Storage;
import com.jm.swcz.service.StorageService;

/**
 * 库位的界面
 * @author lenovo
 *
 */
public class StorageActivity extends ListActivity {
	private StorageService storageService;
	private SimpleAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.storage_activity);
		this.setTitle("库位");
		storageService = (StorageService) BeanFactory.getInstance().getBean(StorageService.class);
		loadData();
	}
	
	private void loadData(){
		List<Storage> storageList = storageService.findStorageList();
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		if(storageList!=null && storageList.size()>0){
			Map<String,Object> map = null;
			for(Storage storage : storageList){
				map = new HashMap<String,Object>();
				map.put("tv_storage_id", storage.getStorage_id());
				map.put("tv_storage_name", storage.getStorage_name());
				data.add(map);
			}
		}
		
		String[] from = new String[]{"tv_storage_name"};
		int[] to = new int[]{R.id.tv_storage_name};
		adapter = new SimpleAdapter(this, data, R.layout.storage_item_activity, from, to);
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
			Intent intent = new Intent(StorageActivity.this,StorageDetailActivity.class);
			startActivity(intent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Map<String,Object> data = (Map<String, Object>) adapter.getItem(position);
		String storageId = (String) data.get("tv_storage_id");
		Intent intent = new Intent(StorageActivity.this,StorageDetailActivity.class);
		intent.putExtra("storageId", storageId);
		startActivity(intent);
		super.onListItemClick(l, v, position, id);
	}
}
