package com.jm.swcz.ui.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.jm.swcz.R;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.Decision;
import com.jm.swcz.model.Fault;
import com.jm.swcz.service.DecisionService;
import com.jm.swcz.service.FaultService;
import com.jm.swcz.ui.activity.DecisionDetailActivity;

public class DecisionFragment extends ListFragment {
	
	private SimpleAdapter adapter;
	//private List<Decision> decisionList;
	private List<Fault> faultList;
	private DecisionService decisionService;
	private FaultService faultService;
	private ArrayAdapter<Fault> faultAdapter;
	private ArrayAdapter<Fault> fault2Adapter;
	private ArrayAdapter<String> levelAdapter;
	private ArrayAdapter<String> level2Adapter;
	private Spinner sp_fault,sp_fault2,sp_fault_level,sp_fault_level2;
	private Button btn_search_decision;
	private String query_fault_id1;
	private String query_fault_id2;
	private String query_level;
	private String query_level2;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		decisionService = (DecisionService) BeanFactory.getInstance().getBean(DecisionService.class);
		faultService = (FaultService) BeanFactory.getInstance().getBean(FaultService.class);
		//decisionList = decisionService.findDecisionList();
		Activity activity = getActivity();
		activity.setTitle("决策");
		setHasOptionsMenu(true);
		
		faultList = faultService.findFaultList();
		Fault fault = new Fault();
		fault.setFault_id("");
		fault.setFault_name("请选择");
		faultList.add(fault);
		Collections.reverse(faultList);
		faultAdapter = new ArrayAdapter<Fault>(getActivity(),android.R.layout.simple_spinner_item,faultList);
		faultAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		fault2Adapter = new ArrayAdapter<Fault>(getActivity(),android.R.layout.simple_spinner_item,faultList);
		fault2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		List<String> levelList = new ArrayList<String>();
		levelList.add("请选择");
		levelList.add("Low");
		levelList.add("High");
		levelAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,levelList);
		levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		level2Adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,levelList);
		level2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		//loadData(decisionList);
	}
	
	private void loadData(List<Decision> decisionList){
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		if(decisionList!=null && decisionList.size()>0){
			Map<String,Object> map = null;
			for(Decision decision : decisionList){
				map = new HashMap<String,Object>();
				map.put("tv_decision_id", decision.getDecision_id());
				Fault fault = decision.getFault();
				if(fault!=null){
					map.put("tv_decision_fault_reason", decision.getReason().getReason_name());
				}
				map.put("tv_level", decision.getLevel());
				map.put("tv_proportion", decision.getProportion()+"%");
				data.add(map);
			}
		}
		
		String[] from = new String[]{"tv_decision_fault_reason","tv_proportion"};
		int[] to = new int[]{R.id.tv_decision_fault_reason,R.id.tv_proportion};
		adapter = new SimpleAdapter(getActivity(), data, R.layout.decision_item_activity, from, to);
		setListAdapter(adapter);
	}
	
	@Override
	public void onResume() {
		//search(query_fault_id1,query_fault_id2,query_level,query_level2);
		super.onResume();
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.action_bar_add, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_bar_add:
			Intent intent = new Intent(getActivity(),DecisionDetailActivity.class);
			startActivity(intent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.decision_fragment, null);
		
		sp_fault = (Spinner) view.findViewById(R.id.sp_fault);
		sp_fault.setAdapter(faultAdapter);
		sp_fault.setOnItemSelectedListener(new FaultSelectedListener());
		sp_fault.setVisibility(View.VISIBLE);
		sp_fault2 = (Spinner) view.findViewById(R.id.sp_fault2);
		sp_fault2.setAdapter(fault2Adapter);
		sp_fault2.setOnItemSelectedListener(new Fault2SelectedListener());
		sp_fault2.setVisibility(View.VISIBLE);
		
		sp_fault_level = (Spinner) view.findViewById(R.id.sp_fault_level);
		sp_fault_level.setAdapter(levelAdapter);
		sp_fault_level.setOnItemSelectedListener(new LevelSelectedListener());
		sp_fault_level.setVisibility(View.VISIBLE);
		sp_fault_level2 = (Spinner) view.findViewById(R.id.sp_fault_level2);
		sp_fault_level2.setAdapter(level2Adapter);
		sp_fault_level2.setOnItemSelectedListener(new Level2SelectedListener());
		sp_fault_level2.setVisibility(View.VISIBLE);
		
		btn_search_decision = (Button) view.findViewById(R.id.btn_search_decision);
		btn_search_decision.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				search(query_fault_id1,query_fault_id2,query_level,query_level2);
			}
		});
		
		return view;
	}
	
	private void search(String query_fault_id1,String query_fault_id2,String query_level,String query_level2){
		List<Decision> decisionList = decisionService.findDecisionList(query_fault_id1, query_fault_id2, query_level,query_level2);
		loadData(decisionList);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Map<String,Object> map = (Map<String, Object>) adapter.getItem(position);
		Intent intent = new Intent(getActivity(),DecisionDetailActivity.class);
		String decisionId = (String) map.get("tv_decision_id");
		intent.putExtra("decisionId", decisionId);
		startActivity(intent);
	}
	
	class FaultSelectedListener implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
			Fault fault = faultAdapter.getItem(position);
			query_fault_id1 = fault.getFault_id();
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			query_fault_id1 = "";
		}
		
	}
	
	class Fault2SelectedListener implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
			Fault fault = faultAdapter.getItem(position);
			query_fault_id2 = fault.getFault_id();
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			query_fault_id2 = "";
		}
		
	}
	
	class LevelSelectedListener implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
			String level = levelAdapter.getItem(position);
			if("请选择".equals(level)){
				query_level = "";
			}else{
				query_level = level;
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			query_level = "";
		}
		
	}
	
	class Level2SelectedListener implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
			String level = level2Adapter.getItem(position);
			if("请选择".equals(level)){
				query_level2 = "";
			}else{
				query_level2 = level;
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			query_level2 = "";
		}
		
	}
	
}
