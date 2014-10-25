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
import com.jm.swcz.ui.activity.FaultActivity;
import com.jm.swcz.ui.activity.FaultReasonActivity;
import com.jm.swcz.ui.activity.ModifyPasswordActivity;

public class SettingFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle("设置");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.setting_fragment, null);
		Button btn_modify_password = (Button) view.findViewById(R.id.btn_modify_password);
		Button btn_fault = (Button) view.findViewById(R.id.btn_fault);
		Button btn_fault_reason = (Button) view.findViewById(R.id.btn_fault_reason);
		
		btn_modify_password.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),ModifyPasswordActivity.class);
				startActivity(intent);
			}
		});
		
		btn_fault.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),FaultActivity.class);
				startActivity(intent);
			}
		});
		
		btn_fault_reason.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),FaultReasonActivity.class);
				startActivity(intent);
			}
		});
		
		return view;
	}
}
