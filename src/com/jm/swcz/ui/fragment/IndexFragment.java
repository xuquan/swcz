package com.jm.swcz.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.jm.swcz.R;
import com.jm.swcz.ui.activity.DeptActivity;
import com.jm.swcz.ui.activity.MaterialTypeActivity;

public class IndexFragment extends Fragment {
	
	private SimpleAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle("首页");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.index_fragment, null);
		GridView gv_index = (GridView) view.findViewById(R.id.gv_index);
		
		List<Map<String, Object>> data = initData();
		String[] from = new String[]{"icon","name"};
		int[] to = new int[]{R.id.iv_index_item,R.id.tv_index_item};
		adapter = new SimpleAdapter(getActivity(),data,R.layout.index_grid_item, from, to);
		
		gv_index.setAdapter(adapter);
		gv_index.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Map<String,Object> map = (Map<String,Object>) adapter.getItem(position);
				Integer layout = (Integer) map.get("layout");
				Intent intent = null;
				switch (layout) {
				case R.layout.dept_activity:
					intent = new Intent(getActivity(),DeptActivity.class);
					break;
				case R.layout.material_type_activity:
					intent = new Intent(getActivity(),MaterialTypeActivity.class);
					break;
				default:
					break;
				}
				startActivity(intent);
			}
		});
		return view;
	}
	
	private List<Map<String,Object>> initData(){
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		for(int i=0;i<icons.length;i++){
			Map<String, Object>  map = new HashMap<String,Object>();
			map.put("layout", layouts[i]);
			map.put("icon", icons[i]);
			map.put("name", names[i]);
			data.add(map);
		}
		return data;
	}
	
	private Integer[] layouts = {
		R.layout.dept_activity,
		R.layout.material_type_activity,
		R.id.rb_dismounting,
		R.id.rb_material,
		R.id.rb_more,
		R.id.rg_tab
	};
	
	private Integer[] icons = {
			R.drawable.wdgf_150_icon,
			R.drawable.wdgp_150_icon,
			R.drawable.hqzx_150_icon,
			R.drawable.jrcs_150_icon,
			R.drawable.jyzx_150_icon,
			R.drawable.zxzx_150_icon
	};
	
	private String[] names = {
			"部门","物料类别","决策","拆装","设置","备忘"
	};
	
}
