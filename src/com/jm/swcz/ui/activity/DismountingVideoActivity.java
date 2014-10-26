package com.jm.swcz.ui.activity;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.jm.swcz.R;

/**
 * 拆装视频界面
 * @author lenovo
 *
 */
public class DismountingVideoActivity extends Activity {
	private VideoView videoView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dismounting_video_activity);
		videoView = (VideoView)findViewById(R.id.videoView1);
		videoView.setMediaController(new MediaController(this));
		/**
         * VideoView控制视频播放的功能相对较少，具体而言，它只有start和pause方法。为了提供更多的控制，
         * 可以实例化一个MediaController，并通过setMediaController方法把它设置为VideoView的控制器。
         */
		//String path = this.getActivity().getDatabasePath("1.mp4").getAbsolutePath();
		Intent intent = getIntent();
		String fileName = intent.getStringExtra("fileName");
		String path =Environment.getExternalStorageDirectory().getPath() + "/" + fileName;
		File file = new File(path);
		if(!file.exists()){
			Toast.makeText(this, "文件["+fileName+"]不存在", Toast.LENGTH_LONG).show();
			finish();
		}
		Uri videoUri = Uri.parse(path);
		videoView.setVideoURI(videoUri);
		videoView.requestFocus();
		videoView.start();
	}
	
}
