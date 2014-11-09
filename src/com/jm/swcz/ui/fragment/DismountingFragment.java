package com.jm.swcz.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.jm.swcz.R;
import com.jm.swcz.ui.activity.DismountingVideoActivity;

public class DismountingFragment extends Fragment implements OnClickListener{
	private Button btn_apyq,btn_cpyb,btn_cpyq,btn_zpyb;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle("拆装");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dismounting_fragment, null);
		btn_apyq = (Button) view.findViewById(R.id.btn_apyq);
		btn_apyq.setOnClickListener(this);
		btn_cpyb = (Button) view.findViewById(R.id.btn_cpyb);
		btn_cpyb.setOnClickListener(this);
		btn_cpyq = (Button) view.findViewById(R.id.btn_cpyq);
		btn_cpyq.setOnClickListener(this);
		btn_zpyb = (Button) view.findViewById(R.id.btn_azpyb);
		btn_zpyb.setOnClickListener(this);
		
		return view;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(getActivity(),DismountingVideoActivity.class);
		switch(v.getId()){
		case R.id.btn_apyq:
			intent.putExtra("fileName", "apyq.mp4");
			break;
		case R.id.btn_cpyb:
			intent.putExtra("fileName", "cpyb.mp4");
			break;
		case R.id.btn_cpyq:
			intent.putExtra("fileName", "cpyq.mp4");
			break;
		case R.id.btn_azpyb:
			intent.putExtra("fileName", "apyb.mp4");
			break;
		}
		startActivity(intent);
	}
}
