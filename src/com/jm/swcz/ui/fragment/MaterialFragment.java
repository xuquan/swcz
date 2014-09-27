package com.jm.swcz.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jm.swcz.R;

public class MaterialFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.material_fragment, null);
		TextView tv = (TextView) view.findViewById(R.id.txt_content);
		tv.setText("这是物料界面");
		return view;
	}
}
