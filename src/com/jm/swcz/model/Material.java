package com.jm.swcz.model;

/**
 * 物料
 * @author lenovo
 *
 */
public class Material {
	private String material_id;
	private String material_type_id;
	private MaterialType materialType;
	private String material_code;
	private String material_name_cn;
	private String material_name_en;
	private String specifications;
	private String material_no;
	private String size_weight;
	private String other_tech_data;
	private String safe_storage;
	private String legal_storage;
	private String amount;
	private String storage_up_limit;
	private String storage_down_limit;
	private String unit;
	private String storage_id;
	private Storage storage;
	private String manufacturer_code;
	private String manufacturer_name;
	private String manufacturer_reference_number;
	private String internal_price;
	private String internal_price_unit;
	private String enter_price;
	private String enter_price_unit;
	private String dept_id;
	private Dept dept;
	private String duty_person;
	private String remark;
	private String user_id;
	private User user;
	private String operate_time;
	
	public String getMaterial_id() {
		return material_id;
	}
	public void setMaterial_id(String material_id) {
		this.material_id = material_id;
	}
	public String getMaterial_type_id() {
		return material_type_id;
	}
	public void setMaterial_type_id(String material_type_id) {
		this.material_type_id = material_type_id;
	}
	public String getMaterial_code() {
		return material_code;
	}
	public void setMaterial_code(String material_code) {
		this.material_code = material_code;
	}
	public String getMaterial_name_cn() {
		return material_name_cn;
	}
	public void setMaterial_name_cn(String material_name_cn) {
		this.material_name_cn = material_name_cn;
	}
	public String getMaterial_name_en() {
		return material_name_en;
	}
	public void setMaterial_name_en(String material_name_en) {
		this.material_name_en = material_name_en;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public String getMaterial_no() {
		return material_no;
	}
	public void setMaterial_no(String material_no) {
		this.material_no = material_no;
	}
	public String getSize_weight() {
		return size_weight;
	}
	public void setSize_weight(String size_weight) {
		this.size_weight = size_weight;
	}
	public String getOther_tech_data() {
		return other_tech_data;
	}
	public void setOther_tech_data(String other_tech_data) {
		this.other_tech_data = other_tech_data;
	}
	public String getSafe_storage() {
		return safe_storage;
	}
	public void setSafe_storage(String safe_storage) {
		this.safe_storage = safe_storage;
	}
	public String getLegal_storage() {
		return legal_storage;
	}
	public void setLegal_storage(String legal_storage) {
		this.legal_storage = legal_storage;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getStorage_up_limit() {
		return storage_up_limit;
	}
	public void setStorage_up_limit(String storage_up_limit) {
		this.storage_up_limit = storage_up_limit;
	}
	public String getStorage_down_limit() {
		return storage_down_limit;
	}
	public void setStorage_down_limit(String storage_down_limit) {
		this.storage_down_limit = storage_down_limit;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getStorage_id() {
		return storage_id;
	}
	public void setStorage_id(String storage_id) {
		this.storage_id = storage_id;
	}
	public String getManufacturer_code() {
		return manufacturer_code;
	}
	public void setManufacturer_code(String manufacturer_code) {
		this.manufacturer_code = manufacturer_code;
	}
	public String getManufacturer_name() {
		return manufacturer_name;
	}
	public void setManufacturer_name(String manufacturer_name) {
		this.manufacturer_name = manufacturer_name;
	}
	public String getManufacturer_reference_number() {
		return manufacturer_reference_number;
	}
	public void setManufacturer_reference_number(
			String manufacturer_reference_number) {
		this.manufacturer_reference_number = manufacturer_reference_number;
	}
	public String getInternal_price() {
		return internal_price;
	}
	public void setInternal_price(String internal_price) {
		this.internal_price = internal_price;
	}
	public String getInternal_price_unit() {
		return internal_price_unit;
	}
	public void setInternal_price_unit(String internal_price_unit) {
		this.internal_price_unit = internal_price_unit;
	}
	public String getEnter_price() {
		return enter_price;
	}
	public void setEnter_price(String enter_price) {
		this.enter_price = enter_price;
	}
	public String getEnter_price_unit() {
		return enter_price_unit;
	}
	public void setEnter_price_unit(String enter_price_unit) {
		this.enter_price_unit = enter_price_unit;
	}
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	public String getDuty_person() {
		return duty_person;
	}
	public void setDuty_person(String duty_person) {
		this.duty_person = duty_person;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getOperate_time() {
		return operate_time;
	}
	public void setOperate_time(String operate_time) {
		this.operate_time = operate_time;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((material_id == null) ? 0 : material_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Material other = (Material) obj;
		if (material_id == null) {
			if (other.material_id != null)
				return false;
		} else if (!material_id.equals(other.material_id))
			return false;
		return true;
	}
	public MaterialType getMaterialType() {
		return materialType;
	}
	public void setMaterialType(MaterialType materialType) {
		this.materialType = materialType;
	}
	public Storage getStorage() {
		return storage;
	}
	public void setStorage(Storage storage) {
		this.storage = storage;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
