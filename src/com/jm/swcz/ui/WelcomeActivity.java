package com.jm.swcz.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.jm.swcz.R;
/**
 * 欢迎动画activity
 */
public class WelcomeActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.welcome_activity);
        
        final Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
        //系统会为需要启动的activity寻找与当前activity不同的task;
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //创建一个新的线程来显示欢迎动画，指定时间后结束，跳转至指定界面
        new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					//获取应用的上下文，生命周期是整个应用，应用结束才会结束
					getApplicationContext().startActivity(intent);
					finish();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
    }
}