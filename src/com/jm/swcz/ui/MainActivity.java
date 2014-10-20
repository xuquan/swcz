package com.jm.swcz.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jm.swcz.R;
import com.jm.swcz.factory.FragmentFactory;

public class MainActivity extends Activity {
	public static final int MENU_EXIT = 3;
	private FragmentManager fm;
	private RadioGroup rg;
	private long exitTime = 0;
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
				//transaction.addToBackStack(null);
				transaction.commit();
			}
		});
		initIndexFragment();
	}
	
	private void initIndexFragment(){
		FragmentTransaction transaction = fm.beginTransaction();
		Fragment fragment = FragmentFactory.getInstanceByIndex(R.id.rb_index);
		transaction.replace(R.id.content, fragment);
		transaction.commit();
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
			System.exit(0);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
			if(System.currentTimeMillis()-exitTime > 2000){
				Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_LONG).show();                                
	            exitTime = System.currentTimeMillis();  
			}else{
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
