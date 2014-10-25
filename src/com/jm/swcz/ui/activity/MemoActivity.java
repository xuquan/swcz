package com.jm.swcz.ui.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.jm.swcz.R;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.Memo;
import com.jm.swcz.service.MemoService;

/**
 * 备忘录界面
 * @author lenovo
 *
 */
public class MemoActivity extends ListActivity {
	private MemoService memoService;
	private SimpleAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memo_activity);
		this.setTitle("备忘录");
		memoService = (MemoService) BeanFactory.getInstance().getBean(MemoService.class);
		loadData();
	}
	
	private void loadData(){
		List<Memo> memoList = memoService.findMemoList();
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		if(memoList!=null && memoList.size()>0){
			Map<String,Object> map = null;
			for(Memo memo : memoList){
				map = new HashMap<String,Object>();
				map.put("tv_memo_id", memo.getMemo_id());
				map.put("tv_content", memo.getContent());
				String operate_time = memo.getOperate_time().split(" ")[1];
				map.put("tv_operate_time", operate_time);
				data.add(map);
			}
		}
		
		String[] from = new String[]{"tv_content","tv_operate_time"};
		int[] to = new int[]{R.id.tv_content,R.id.tv_operate_time};
		adapter = new SimpleAdapter(this, data, R.layout.memo_item_activity, from, to);
		setListAdapter(adapter);
	}
	
	@Override
	protected void onResume() {
		loadData();
		super.onResume();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.action_bar_add, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_bar_add:
			Intent intent = new Intent(MemoActivity.this,MemoDetailActivity.class);
			startActivity(intent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Map<String,Object> data = (Map<String, Object>) adapter.getItem(position);
		String memoId = (String) data.get("tv_memo_id");
		Intent intent = new Intent(MemoActivity.this,MemoDetailActivity.class);
		intent.putExtra("memoId", memoId);
		startActivity(intent);
		super.onListItemClick(l, v, position, id);
	}
}
