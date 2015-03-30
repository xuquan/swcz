package com.jm.swcz.ui.fragment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;

import com.jm.swcz.R;
import com.jm.swcz.factory.FragmentFactory;
import com.jm.swcz.ui.activity.DeptActivity;
import com.jm.swcz.ui.activity.MaterialTypeActivity;
import com.jm.swcz.ui.activity.MemoActivity;
import com.jm.swcz.ui.activity.StorageActivity;

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
				boolean isStartActivity = true;
				FragmentManager fm = getFragmentManager();
				FragmentTransaction transaction = fm.beginTransaction();
				Fragment fragment = null;
				switch (layout) {
				case R.layout.dept_activity:
					intent = new Intent(getActivity(),DeptActivity.class);
					break;
				case R.layout.dismounting_fragment:
					//intent = new Intent(getActivity(),MaterialFragment.class);
					fragment = FragmentFactory.getInstanceByIndex(2131099705);
					transaction.replace(R.id.content, fragment);
					transaction.commit();
					isStartActivity = false;
					break;
				case R.layout.material_type_activity:
					intent = new Intent(getActivity(),MaterialTypeActivity.class);
					break;
				case R.layout.decision_fragment:
					//intent = new Intent(getActivity(),MaterialFragment.class);
					fragment = FragmentFactory.getInstanceByIndex(2131099707);
					transaction.replace(R.id.content, fragment);
					transaction.commit();
					isStartActivity = false;
					break;
				case R.layout.storage_activity:
					intent = new Intent(getActivity(),StorageActivity.class);
					break;
				case R.layout.memo_activity:
					intent = new Intent(getActivity(),MemoActivity.class);
					break;
				case R.layout.manual_activity:
					//intent = new Intent(getActivity(),ManualActivity.class);
					intent = new Intent(Intent.ACTION_VIEW);
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					String path =Environment.getExternalStorageDirectory().getPath() + "/swcz/s60mc.pdf";
			        Uri uri = Uri.fromFile(new File(path));
			        intent.setDataAndType(uri, "application/pdf");
					break;
				default:
					break;
				}
				if(isStartActivity){
					startActivity(intent);
				}
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
		R.layout.material_fragment,
		R.layout.storage_activity,
		R.layout.memo_activity,
		R.layout.manual_activity,
		R.layout.dismounting_fragment,
		R.layout.decision_fragment
	};
	
	private Integer[] icons = {
			R.drawable.wdgf_150_icon,
			R.drawable.wdgp_150_icon,
			R.drawable.jrcs_150_icon,
			R.drawable.yjfk_150_icon,
			R.drawable.kfrx_150_icon,
			R.drawable.yjfk_150_icon,
			R.drawable.yjfk_150_icon
	};
	
	private String[] names = {
			"部门信息","备件/物料","存放位置","备忘录","设备说明书","设备拆装","故障决策"
	};
	
}
