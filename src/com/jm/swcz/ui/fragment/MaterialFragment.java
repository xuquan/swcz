package com.jm.swcz.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import com.jm.swcz.R;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.Material;
import com.jm.swcz.model.PageModel;
import com.jm.swcz.service.MaterialService;
import com.jm.swcz.ui.activity.MaterialDetailActivity;
import com.jm.swcz.ui.adapter.MaterialListAdapter;

public class MaterialFragment extends ListFragment {
	private ListView listView;
	private SearchView searchView;
	private MaterialListAdapter adapter;
	private List<Material> materialList;
	private ProgressBar progressBar;
	private MaterialService materialService;
	private PageModel<Material> pageModel;
	private int pageSize = 10;
	private FragmentManager fm;
	private MenuItem menuItem;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fm = getFragmentManager();
		materialService = (MaterialService) BeanFactory.getInstance().getBean(MaterialService.class);
		Activity activity = getActivity();
		activity.setTitle("物料备件");
		setHasOptionsMenu(true);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.action_bar_add, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_bar_add:
			menuItem = item;
			Intent intent = new Intent(getActivity(),MaterialDetailActivity.class);
			startActivity(intent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.material_fragment, null);
		listView = (ListView) view.findViewById(android.R.id.list);
		listView.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if(firstVisibleItem+visibleItemCount==totalItemCount){
					Message msg = new Message();
					msg.what=1;
					handler.sendMessage(msg);
				}
			}
		});
		
		searchView = (SearchView) view.findViewById(R.id.sv_material);
		searchView.setSubmitButtonEnabled(false);
		searchView.setOnQueryTextListener(new OnQueryTextListener() {
			
			@Override
			public boolean onQueryTextSubmit(String query) {
				return false;
			}
			
			@Override
			public boolean onQueryTextChange(String newText) {
				List<Material> foundMaterialList = new ArrayList<Material>();
				if(TextUtils.isEmpty(newText)){
					if(materialList!=null && materialList.size()>0){
						foundMaterialList.addAll(materialList);
					}
				}else{
					if(materialList!=null && materialList.size()>0){
						for(Material m : materialList){
							String material_code = m.getMaterial_code();
							String material_name_cn = m.getMaterial_name_cn();
							String material_name_en = m.getMaterial_name_en();
							String manufacturer_name = m.getManufacturer_name();
							boolean flag = false;
							newText = newText.toLowerCase();
							if(!TextUtils.isEmpty(material_code) && material_code.toLowerCase().contains(newText)){
								flag = true;
							}
							if(!TextUtils.isEmpty(material_name_cn) && material_name_cn.toLowerCase().contains(newText)){
								flag = true;
							}
							if(!TextUtils.isEmpty(material_name_en) && material_name_en.toLowerCase().contains(newText)){
								flag = true;
							}
							if(!TextUtils.isEmpty(manufacturer_name) && manufacturer_name.toLowerCase().contains(newText)){
								flag = true;
							}
							if(flag){
								foundMaterialList.add(m);
							}
						}
					}
				}
				adapter = new MaterialListAdapter(getActivity(),foundMaterialList);
				setListAdapter(adapter);
				return true;
			}
			
		});
		
		progressBar = new ProgressBar(getActivity());
		progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		progressBar.setIndeterminate(true);
		listView.setEmptyView(progressBar);
		container.addView(progressBar);
		getLoaderManager().initLoader(0, null, new LoaderCallbacks<PageModel<Material>>() {

			@Override
			public Loader<PageModel<Material>> onCreateLoader(int id, Bundle args) {
				progressBar.setVisibility(ProgressBar.VISIBLE);
				
				return null;
			}

			@Override
			public void onLoadFinished(Loader<PageModel<Material>> loader, PageModel<Material> data) {
				progressBar.setVisibility(ProgressBar.INVISIBLE);
			}

			@Override
			public void onLoaderReset(Loader<PageModel<Material>> loader) {
				
			}
			
		});
		
		loadData();
		return view;
	}
	
	private void loadData(){
		pageModel = materialService.findMaterialList(1,pageSize);
		materialList = pageModel.getList();
		adapter = new MaterialListAdapter(getActivity(),materialList);
		setListAdapter(adapter);
	}
	
	@Override
	public void onResume() {
		loadData();
		super.onResume();
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Material material = (Material) adapter.getItem(position);
		Intent intent = new Intent(getActivity(),MaterialDetailActivity.class);
		intent.putExtra("material_id", material.getMaterial_id());
		startActivity(intent);
	}
	
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch(msg.what){
			case 1:
				PageModel<Material> pm = materialService.findMaterialList(pageModel.getNextPageNo(),pageSize);
				List<Material> tempList = pm.getList();
				for(Material m : tempList){
					if(!materialList.contains(m)){
						materialList.add(m);
					}
				}
				adapter.notifyDataSetChanged();
				break;
			}
		};
	};
	
}
