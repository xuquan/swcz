package com.jm.swcz.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;

import com.jm.swcz.R;
import com.jm.swcz.factory.FragmentFactory;

public class MainActivity extends Activity {
	public static final int MENU_EXIT = 3;
	private FragmentManager fm;
	private RadioGroup rg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		fm = getFragmentManager();
		rg = (RadioGroup) findViewById(R.id.rg_tab);
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				FragmentTransaction transaction = fm.beginTransaction();
				Fragment fragment = FragmentFactory.getInstanceByIndex(checkedId);
				transaction.replace(R.id.content, fragment);
				transaction.addToBackStack(null);
				transaction.commit();
			}
		});
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, MENU_EXIT, 3, "退出").setIcon(R.drawable.menu_exit);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case MENU_EXIT:
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
