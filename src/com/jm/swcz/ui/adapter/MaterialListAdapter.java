package com.jm.swcz.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jm.swcz.R;
import com.jm.swcz.model.Material;

public class MaterialListAdapter extends BaseAdapter {
	private LayoutInflater inflater = null;
	List<Material> materialList = null;
	public MaterialListAdapter(Context context,List<Material> materialList){
		super();
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.materialList = materialList;
	}

	@Override
	public int getCount() {
		return materialList.size();
	}

	@Override
	public Object getItem(int position) {
		Material material = materialList.get(position);
		return material;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MaterialViewHolder holder = null;
		if(convertView==null){
			holder = new MaterialViewHolder();
			convertView = inflater.inflate(R.layout.material_list_item, null);
			holder.tv_material_code = (TextView) convertView.findViewById(R.id.tv_material_code);
			holder.tv_material_name_cn = (TextView) convertView.findViewById(R.id.tv_material_name_cn);
			holder.tv_material_name_en = (TextView) convertView.findViewById(R.id.tv_material_name_en);
			holder.tv_amount = (TextView) convertView.findViewById(R.id.tv_amount);
			holder.iv_more = (ImageView) convertView.findViewById(R.id.iv_more);
			convertView.setTag(holder);
		}else{
			holder = (MaterialViewHolder) convertView.getTag();
		}
		
		Material material = (Material) getItem(position);
		holder.tv_material_code.setText(material.getMaterial_code());
		holder.tv_material_name_cn.setText(material.getMaterial_name_cn());
		holder.tv_material_name_en.setText(material.getMaterial_name_en());
		holder.tv_amount.setText(material.getAmount());
		return convertView;
	}

	public List<Material> getMaterialList() {
		return materialList;
	}

}

class MaterialViewHolder{
	public TextView tv_material_code;
	public TextView tv_material_name_cn;
	public TextView tv_material_name_en;
	public TextView tv_amount;
	public ImageView iv_more;
}
