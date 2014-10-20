package com.jm.swcz.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.jm.swcz.R;
import com.jm.swcz.factory.FragmentFactory;

public class IndexFragment extends Fragment {
	
	private SimpleAdapter adapter;
	private FragmentManager fm;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle("首页");
		fm = getFragmentManager();
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
				Integer fragmentId = (Integer) map.get("fragmentId");
				Fragment fragment = FragmentFactory.getInstanceByIndex(fragmentId);
				
				FragmentTransaction transaction = fm.beginTransaction();
				transaction.replace(R.id.content, fragment);
				transaction.addToBackStack(null);
				transaction.commit();
			}
		});
		return view;
	}
	
	private List<Map<String,Object>> initData(){
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		for(int i=0;i<icons.length;i++){
			Map<String, Object>  map = new HashMap<String,Object>();
			map.put("fragmentId", fragmentIds[i]);
			map.put("icon", icons[i]);
			map.put("name", names[i]);
			data.add(map);
		}
		return data;
	}
	
	private Integer[] fragmentIds = {
		R.id.rb_index,
		R.id.rb_decision,
		R.id.rb_dismounting,
		R.id.rb_material,
		R.id.rb_more,
		R.id.rg_tab
	};
	
	private Integer[] icons = {
			R.drawable.hqzx_150_icon,
			R.drawable.jrcs_150_icon,
			R.drawable.jyzx_150_icon,
			R.drawable.wdgp_150_icon,
			R.drawable.zxzx_150_icon,
			R.drawable.wdgf_150_icon
	};
	
	private String[] names = {
			"物料","决策","拆装","物料类别","设置","备忘"
	};
	
	class GridItem{
		private int fragmentId;
		private int icon;
		private String name;
	}
}
