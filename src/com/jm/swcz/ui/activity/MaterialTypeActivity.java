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
import com.jm.swcz.model.MaterialType;
import com.jm.swcz.service.MaterialTypeService;

/**
 * 物料类别界面
 * @author lenovo
 *
 */
public class MaterialTypeActivity extends ListActivity {

	private MaterialTypeService materialTypeService;
	private SimpleAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setTitle("物料类别");
		materialTypeService = (MaterialTypeService) BeanFactory.getInstance().getBean(MaterialTypeService.class);
		loadData();
	}
	
	private void loadData(){
		List<MaterialType> materialTypeList = materialTypeService.findMaterialTypeList();
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		if(materialTypeList!=null && materialTypeList.size()>0){
			Map<String,Object> map = null;
			for(MaterialType materialType : materialTypeList){
				map = new HashMap<String,Object>();
				map.put("tv_material_type_id", materialType.getMaterial_type_id());
				map.put("tv_material_type_name", materialType.getMaterial_type_name());
				data.add(map);
			}
		}
		
		String[] from = new String[]{"tv_material_type_name"};
		int[] to = new int[]{R.id.tv_material_type_name};
		adapter = new SimpleAdapter(this, data, R.layout.material_type_activity, from, to);
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
			Intent intent = new Intent(MaterialTypeActivity.this,MaterialTypeDetailActivity.class);
			startActivity(intent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Map<String,Object> data = (Map<String, Object>) adapter.getItem(position);
		String materialTypeId = (String) data.get("tv_material_type_id");
		Intent intent = new Intent(MaterialTypeActivity.this,MaterialTypeDetailActivity.class);
		intent.putExtra("materialTypeId", materialTypeId);
		startActivity(intent);
		super.onListItemClick(l, v, position, id);
	}
	
}
