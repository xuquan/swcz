package com.jm.swcz.ui.fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.jm.swcz.R;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.Material;
import com.jm.swcz.model.MaterialType;
import com.jm.swcz.service.LoginService;
import com.jm.swcz.service.MaterialService;
import com.jm.swcz.service.MaterialTypeService;

public class MaterialDetailFragment extends Fragment {
	private MaterialTypeService materialTypeService;
	private MaterialService materialService;
	private LoginService loginService;
	private List<MaterialType> materialTypeList;
	private Material material;
	private Spinner sp_material_type;
	private EditText et_material_code;
	private EditText et_material_name_cn;
	private EditText et_material_name_en;
	private EditText et_specifications;
	private EditText et_material_no;
	private EditText et_size_weight;
	private EditText et_other_tech_data;
	private EditText et_safe_storage;
	private EditText et_legal_storage;
	private EditText et_amount;
	private EditText et_storage_up_limit;
	private EditText et_storage_down_limit;
	private EditText et_unit;
	private EditText et_storage_id;
	private EditText et_manufacturer_code;
	private EditText et_manufacturer_name;
	private EditText et_manufacturer_reference_number;
	private EditText et_internal_price;
	private EditText et_internal_price_unit;
	private EditText et_enter_price;
	private EditText et_enter_price_unit;
	private EditText et_dept_id;
	private EditText et_duty_person;
	private EditText et_remark;
	private EditText et_user_id;
	private EditText et_operate_time;
	private Button btn_save_material;
	private FragmentManager fm;
	private ArrayAdapter<MaterialType> materialTypeAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		materialTypeService = (MaterialTypeService)BeanFactory.getInstance().getBean(MaterialTypeService.class);
		materialService = (MaterialService) BeanFactory.getInstance().getBean(MaterialService.class);
		loginService = (LoginService) BeanFactory.getInstance().getBean(LoginService.class);
		String materialId = getArguments().getString("material_id");
		material = materialService.findMaterialById(materialId);
		materialTypeList = materialTypeService.findMaterialTypeList();
		if(materialTypeList==null || materialTypeList.size()==0){
			for(int i=1;i<=20;i++){
				MaterialType mt = new MaterialType();
				mt.setMaterial_type_name("物料类别"+i);
				mt.setParent_material_type_id("1");
				mt.setRemark("备注"+i);
				mt.setUser_id("1");
				mt.setOperate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				materialTypeService.saveMaterialType(mt);
			}
		}
		materialTypeAdapter = new ArrayAdapter<MaterialType>(getActivity(),
				android.R.layout.simple_spinner_item,materialTypeList);
		materialTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		fm = getFragmentManager();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.material_detail_fragment, null);
		sp_material_type = (Spinner) view.findViewById(R.id.sp_material_type);
		sp_material_type.setAdapter(materialTypeAdapter);
		sp_material_type.setOnItemSelectedListener(new SpinnerSelectedListener());
		sp_material_type.setVisibility(View.VISIBLE);
		et_material_code = (EditText) view.findViewById(R.id.et_material_code);
		et_material_name_cn = (EditText) view.findViewById(R.id.et_material_name_cn);
		et_material_name_en = (EditText) view.findViewById(R.id.et_material_name_en);
		et_specifications = (EditText) view.findViewById(R.id.et_specifications);
		et_material_no = (EditText) view.findViewById(R.id.et_material_no);
		et_size_weight = (EditText) view.findViewById(R.id.et_size_weight);
		et_other_tech_data = (EditText) view.findViewById(R.id.et_other_tech_data);
		et_safe_storage = (EditText) view.findViewById(R.id.et_safe_storage);
		et_legal_storage = (EditText) view.findViewById(R.id.et_legal_storage);
		et_amount = (EditText) view.findViewById(R.id.et_amount);
		et_storage_up_limit = (EditText) view.findViewById(R.id.et_storage_up_limit);
		et_storage_down_limit = (EditText) view.findViewById(R.id.et_storage_down_limit);
		et_unit = (EditText) view.findViewById(R.id.et_unit);
		et_storage_id = (EditText) view.findViewById(R.id.et_storage_id);
		et_manufacturer_code = (EditText) view.findViewById(R.id.et_manufacturer_code);
		et_manufacturer_name = (EditText) view.findViewById(R.id.et_manufacturer_name);
		et_manufacturer_reference_number = (EditText) view.findViewById(R.id.et_manufacturer_reference_number);
		et_internal_price = (EditText) view.findViewById(R.id.et_internal_price);
		et_internal_price_unit = (EditText) view.findViewById(R.id.et_internal_price_unit);
		et_enter_price = (EditText) view.findViewById(R.id.et_enter_price);
		et_enter_price_unit = (EditText) view.findViewById(R.id.et_enter_price_unit);
		et_dept_id = (EditText) view.findViewById(R.id.et_dept_id);
		et_duty_person = (EditText) view.findViewById(R.id.et_duty_person);
		et_remark = (EditText) view.findViewById(R.id.et_remark);
		et_user_id = (EditText) view.findViewById(R.id.et_user_id);
		et_operate_time = (EditText) view.findViewById(R.id.et_operate_time);
		
		if (material != null) {
			sp_material_type.setSelection(materialTypeAdapter.getPosition(material.getMaterialType()));
			et_material_code.setText(material.getMaterial_code());
			et_material_name_cn.setText(material.getMaterial_name_cn());
			et_material_name_en.setText(material.getMaterial_name_en());
			et_specifications.setText(material.getSpecifications());
			et_material_no.setText(material.getMaterial_no());
			et_size_weight.setText(material.getSize_weight());
			et_other_tech_data.setText(material.getOther_tech_data());
			et_safe_storage.setText(material.getSafe_storage());
			et_legal_storage.setText(material.getLegal_storage());
			et_amount.setText(material.getAmount());
			et_storage_up_limit.setText(material.getStorage_up_limit());
			et_storage_down_limit.setText(material.getStorage_down_limit());
			et_unit.setText(material.getUnit());
			et_storage_id.setText(material.getStorage_id());
			et_manufacturer_code.setText(material.getManufacturer_code());
			et_manufacturer_name.setText(material.getManufacturer_name());
			et_manufacturer_reference_number.setText(material.getManufacturer_reference_number());
			et_internal_price.setText(material.getInternal_price());
			et_internal_price_unit.setText(material.getInternal_price_unit());
			et_enter_price.setText(material.getEnter_price());
			et_enter_price_unit.setText(material.getEnter_price_unit());
			et_dept_id.setText(material.getDept_id());
			et_duty_person.setText(material.getDuty_person());
			et_remark.setText(material.getRemark());
			et_user_id.setText(material.getUser_id());
			et_operate_time.setText(material.getOperate_time());
		}
		
		btn_save_material = (Button) view.findViewById(R.id.btn_save_material);
		btn_save_material.setOnClickListener(new ButtonOnClickListener());
		return view;
	}
	
	class SpinnerSelectedListener implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
			MaterialType materialType = materialTypeAdapter.getItem(position);
			material.setMaterial_type_id(materialType.getMaterial_type_id());
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			
		}
		
	}
	
	class ButtonOnClickListener implements OnClickListener{
			
		@Override
		public void onClick(View v) {
			if(material==null){
				material = new Material();
			}
			material.setMaterial_code(et_material_code.getText().toString());
			material.setMaterial_name_cn(et_material_name_cn.getText().toString());
			material.setMaterial_name_en(et_material_name_en.getText().toString());
			material.setSpecifications(et_specifications.getText().toString());
			material.setMaterial_no(et_material_no.getText().toString());
			material.setSize_weight(et_size_weight.getText().toString());
			material.setOther_tech_data(et_other_tech_data.getText().toString());
			material.setSafe_storage(et_safe_storage.getText().toString());
			material.setLegal_storage(et_legal_storage.getText().toString());
			material.setAmount(et_amount.getText().toString());
			material.setStorage_up_limit(et_storage_up_limit.getText().toString());
			material.setStorage_down_limit(et_storage_down_limit.getText().toString());
			material.setUnit(et_unit.getText().toString());
			material.setStorage_id(et_storage_id.getText().toString());
			material.setManufacturer_code(et_manufacturer_code.getText().toString());
			material.setManufacturer_name(et_manufacturer_name.getText().toString());
			material.setManufacturer_reference_number(et_manufacturer_reference_number.getText().toString());
			material.setInternal_price(et_internal_price.getText().toString());
			material.setInternal_price_unit(et_internal_price_unit.getText().toString());
			material.setEnter_price(et_enter_price.getText().toString());
			material.setEnter_price_unit(et_enter_price_unit.getText().toString());
			material.setDept_id(et_dept_id.getText().toString());
			material.setDuty_person(et_duty_person.getText().toString());
			material.setRemark(et_remark.getText().toString());
			
			Map<String,?> map = loginService.getSharePreference("login");
			if(map!=null && !map.isEmpty()){
				material.setUser_id(map.get("user_id").toString());
			}
			material.setOperate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			
			boolean flag = materialService.saveMaterial(material);
			if(flag){
				fm.popBackStack();
			}
		}
	}
	
}

