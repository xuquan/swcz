package com.jm.swcz.ui.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.jm.swcz.R;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.Dept;
import com.jm.swcz.model.Material;
import com.jm.swcz.model.MaterialType;
import com.jm.swcz.model.Storage;
import com.jm.swcz.service.DeptService;
import com.jm.swcz.service.LoginService;
import com.jm.swcz.service.MaterialService;
import com.jm.swcz.service.MaterialTypeService;
import com.jm.swcz.service.StorageService;

public class MaterialDetailActivity extends Activity {
	private MaterialTypeService materialTypeService;
	private MaterialService materialService;
	private DeptService deptService;
	private StorageService storageService;
	private LoginService loginService;
	private List<MaterialType> materialTypeList;
	private List<Dept> deptList;
	private List<Storage> storageList;
	private ArrayAdapter<MaterialType> materialTypeAdapter;
	private ArrayAdapter<Dept> deptAdapter;
	private ArrayAdapter<Storage> storageAdapter;
	private Material material;
	private Spinner sp_material_type;
	private EditText et_material_code;
	private EditText et_material_name_cn;
	private EditText et_material_name_en;
	private EditText et_specifications;
	private EditText et_material_no;
	private EditText et_size_weight;
	private EditText et_legal_storage;
	private EditText et_reality_storage;
	private EditText et_amount;
	private EditText et_storage_up_limit;
	private EditText et_storage_down_limit;
	private Spinner sp_storage;
	private EditText et_manufacturer_code;
	private EditText et_manufacturer_name;
	private Spinner sp_dept;
	private EditText et_duty_person;
	private EditText et_remark;
	private EditText et_user_id;
	private EditText et_operate_time;
	private Button btn_save_material;
	private MenuItem menuItem;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.material_detail_activity);
		setTitle("物料备件");
		
		materialTypeService = (MaterialTypeService)BeanFactory.getInstance().getBean(MaterialTypeService.class);
		materialService = (MaterialService) BeanFactory.getInstance().getBean(MaterialService.class);
		deptService = (DeptService) BeanFactory.getInstance().getBean(DeptService.class);
		storageService = (StorageService) BeanFactory.getInstance().getBean(StorageService.class);
		loginService = (LoginService) BeanFactory.getInstance().getBean(LoginService.class);
		
		String materialId = "";
		Intent intent = getIntent();
		materialId = intent.getStringExtra("material_id");
		
		material = materialService.findMaterialById(materialId);
		materialTypeList = materialTypeService.findMaterialTypeList();
		materialTypeAdapter = new ArrayAdapter<MaterialType>(this,android.R.layout.simple_spinner_item,materialTypeList);
		materialTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		deptList = deptService.findDeptList();
		deptAdapter = new ArrayAdapter<Dept>(this,android.R.layout.simple_spinner_item,deptList);
		deptAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		storageList = storageService.findStorageList();
		storageAdapter = new ArrayAdapter<Storage>(this,android.R.layout.simple_spinner_item,storageList);
		storageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		initView();
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
			menuItem = item;
			if(material!=null){
				boolean flag = materialService.deleteMaterial(material.getMaterial_id());
				if(flag){
					finish();
				}
			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void initView() {
		sp_material_type = (Spinner) findViewById(R.id.sp_material_type);
		sp_material_type.setAdapter(materialTypeAdapter);
		sp_material_type.setOnItemSelectedListener(new MaterialTypeSelectedListener());
		sp_material_type.setVisibility(View.VISIBLE);
		sp_dept = (Spinner) findViewById(R.id.sp_dept);
		sp_dept.setAdapter(deptAdapter);
		sp_dept.setOnItemSelectedListener(new DeptSelectedListener());
		sp_dept.setVisibility(View.VISIBLE);
		sp_storage = (Spinner) findViewById(R.id.sp_storage);
		sp_storage.setAdapter(storageAdapter);
		sp_storage.setOnItemSelectedListener(new StorageSelectedListener());
		sp_storage.setVisibility(View.VISIBLE);
		et_material_code = (EditText) findViewById(R.id.et_material_code);
		et_material_name_cn = (EditText) findViewById(R.id.et_material_name_cn);
		et_material_name_en = (EditText) findViewById(R.id.et_material_name_en);
		et_specifications = (EditText) findViewById(R.id.et_specifications);
		et_material_no = (EditText) findViewById(R.id.et_material_no);
		et_size_weight = (EditText) findViewById(R.id.et_size_weight);
		et_reality_storage = (EditText) findViewById(R.id.et_reality_storage);
		et_legal_storage = (EditText) findViewById(R.id.et_legal_storage);
		et_amount = (EditText) findViewById(R.id.et_amount);
		et_storage_up_limit = (EditText) findViewById(R.id.et_storage_up_limit);
		et_storage_down_limit = (EditText) findViewById(R.id.et_storage_down_limit);
		et_manufacturer_code = (EditText) findViewById(R.id.et_manufacturer_code);
		et_manufacturer_name = (EditText) findViewById(R.id.et_manufacturer_name);
		et_duty_person = (EditText) findViewById(R.id.et_duty_person);
		et_remark = (EditText) findViewById(R.id.et_remark);
		et_user_id = (EditText) findViewById(R.id.et_user_id);
		et_operate_time = (EditText) findViewById(R.id.et_operate_time);
		
		if (material != null) {
			sp_material_type.setSelection(materialTypeAdapter.getPosition(material.getMaterialType()));
			sp_storage.setSelection(storageAdapter.getPosition(material.getStorage()));
			sp_dept.setSelection(deptAdapter.getPosition(material.getDept()));
			et_material_code.setText(material.getMaterial_code());
			et_material_name_cn.setText(material.getMaterial_name_cn());
			et_material_name_en.setText(material.getMaterial_name_en());
			et_specifications.setText(material.getSpecifications());
			et_material_no.setText(material.getMaterial_no());
			et_size_weight.setText(material.getSize_weight());
			et_legal_storage.setText(material.getLegal_storage());
			et_reality_storage.setText(material.getReality_storage());
			et_amount.setText(material.getAmount());
			et_storage_up_limit.setText(material.getStorage_up_limit());
			et_storage_down_limit.setText(material.getStorage_down_limit());
			et_manufacturer_code.setText(material.getManufacturer_code());
			et_manufacturer_name.setText(material.getManufacturer_name());
			et_duty_person.setText(material.getDuty_person());
			et_remark.setText(material.getRemark());
			et_user_id.setText(material.getUser_id());
			et_operate_time.setText(material.getOperate_time());
		}
		
		btn_save_material = (Button) findViewById(R.id.btn_save_material);
		btn_save_material.setOnClickListener(new ButtonOnClickListener());
	}
	
	class MaterialTypeSelectedListener implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
			MaterialType materialType = materialTypeAdapter.getItem(position);
			if(material!=null && materialType!=null){
				material.setMaterial_type_id(materialType.getMaterial_type_id());
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			
		}
		
	}
	
	class DeptSelectedListener implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			Dept dept = deptAdapter.getItem(position);
			if(material!=null && dept!=null){
				material.setDept_id(dept.getDept_id());
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			
		}
		
	}
	
	class StorageSelectedListener implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			Storage storage = storageAdapter.getItem(position);
			if(material!=null && storage!=null){
				material.setStorage_id(storage.getStorage_id());
			}
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
			material.setReality_storage(et_reality_storage.getText().toString());
			material.setLegal_storage(et_legal_storage.getText().toString());
			material.setAmount(et_amount.getText().toString());
			material.setStorage_up_limit(et_storage_up_limit.getText().toString());
			material.setStorage_down_limit(et_storage_down_limit.getText().toString());
			material.setManufacturer_code(et_manufacturer_code.getText().toString());
			material.setManufacturer_name(et_manufacturer_name.getText().toString());
			material.setDuty_person(et_duty_person.getText().toString());
			material.setRemark(et_remark.getText().toString());
			
			Map<String,?> map = loginService.getSharePreference("login");
			if(map!=null && !map.isEmpty()){
				material.setUser_id(map.get("user_id").toString());
			}
			material.setOperate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			
			boolean flag = materialService.saveMaterial(material);
			if(flag){
				finish();
			}
		}
	}
	
}

