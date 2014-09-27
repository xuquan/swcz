package com.jm.swcz.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jm.swcz.R;

public class IndexFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.index_fragment, null);
		TextView tv = (TextView) view.findViewById(R.id.txt_content);
		tv.setText("这是首页界面");
		return view;
	}
}
