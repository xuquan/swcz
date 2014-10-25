package com.jm.swcz.ui.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.jm.swcz.R;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.Memo;
import com.jm.swcz.model.User;
import com.jm.swcz.service.LoginService;
import com.jm.swcz.service.MemoService;

/**
 * 部门的界面
 * @author lenovo
 *
 */
public class MemoDetailActivity extends Activity implements OnClickListener {
	private LoginService loginService;
	private MemoService memoService;
	private Memo memo;
	private EditText et_content;
	private EditText et_real_name;
	private EditText et_operate_time;
	private Button btn_save_memo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memo_detail_activity);
		this.setTitle("备忘录");
		loginService = (LoginService) BeanFactory.getInstance().getBean(LoginService.class);
		memoService = (MemoService) BeanFactory.getInstance().getBean(MemoService.class);
		Intent intent = getIntent();
		String memoId = intent.getStringExtra("memoId");
		memo = memoService.findMemoById(memoId);
		initView();
	}
	
	private void initView(){
		et_content = (EditText) findViewById(R.id.et_content);
		et_real_name = (EditText) findViewById(R.id.et_real_name);
		et_operate_time = (EditText) findViewById(R.id.et_operate_time);
		btn_save_memo = (Button) findViewById(R.id.btn_save_memo);
		btn_save_memo.setOnClickListener(this);
		if(memo!=null){
			et_content.setText(memo.getContent());
			User user = memo.getUser();
			if(user!=null){
				et_real_name.setText(user.getReal_name());
			}
			et_operate_time.setText(memo.getOperate_time());
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.action_bar_del, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_bar_del:
			if(memo!=null){
				boolean flag = memoService.deleteMemo(memo.getMemo_id());
				if(flag){
					finish();
				}
			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		if(memo==null){
			memo = new Memo();
		}
		memo.setContent(et_content.getText().toString());
		Map<String,?> map = loginService.getSharePreference("login");
		if(map!=null && !map.isEmpty()){
			memo.setUser_id(map.get("user_id").toString());
		}
		memo.setOperate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		memoService.saveMemo(memo);
		finish();
	}
	
}
