package com.jm.swcz.ui.activity;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import com.jm.swcz.R;

/**
 * 说明书界面
 * @author lenovo
 *
 */
public class ManualActivity extends Activity {
	private TextView tv_manual;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manual_activity);
		tv_manual = (TextView) findViewById(R.id.tv_manual);
		this.setTitle("说明书");
		String path =Environment.getExternalStorageDirectory().getPath() + "/s60mc.pdf";
		getText(path);
	}
	
	public void getText(String file){
//		try {
//			//PDDocument doc = PDDocument.load(file);
//			//PDFTextStripper stripper = new PDFTextStripper();
//			//String text = stripper.getText(doc);
//			//tv_manual.setText(text);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
