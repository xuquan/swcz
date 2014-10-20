package com.jm.swcz.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.jm.swcz.R;
import com.jm.swcz.ui.adapter.IndexImageAdapter;

public class IndexFragment extends Fragment {
	
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
		gv_index.setAdapter(new IndexImageAdapter(getActivity()));
		gv_index.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.i("----TAG:", position+"");
			}
		});
		return view;
	}
}
