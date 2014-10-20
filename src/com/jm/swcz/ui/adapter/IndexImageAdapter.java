package com.jm.swcz.ui.adapter;

import com.jm.swcz.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class IndexImageAdapter extends BaseAdapter {
	
	private Context context;
	
	private Integer[] imageIds = {
			R.drawable.hqzx_150_icon,
			R.drawable.jrcs_150_icon,
			R.drawable.jyzx_150_icon,
			R.drawable.wdgp_150_icon,
			R.drawable.zxzx_150_icon
	};
	
	private Integer[] imageBgs = {
			R.drawable.hqzx_bg,
			R.drawable.jrcs_bg,
			R.drawable.jyzx_bg,
			R.drawable.wdgp_bg,
			R.drawable.zxzx_bg
	};
	
	public IndexImageAdapter(Context context){
		this.context = context;
	}

	@Override
	public int getCount() {
		return imageIds.length;
	}

	@Override
	public Object getItem(int position) {
		return imageIds[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if(convertView==null){
			imageView = new ImageView(context);
			imageView.setLayoutParams(new GridView.LayoutParams(150,150));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);
			imageView.setBackgroundResource(imageBgs[position]);
			imageView.setMinimumWidth(200);
			imageView.setMinimumHeight(200);
		}else{
			imageView = (ImageView) convertView;
		}
		imageView.setImageResource(imageIds[position]);
		return imageView;
	}

}
