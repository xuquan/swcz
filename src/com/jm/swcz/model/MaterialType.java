package com.jm.swcz.model;

/**
 * 物料类别
 * @author lenovo
 *
 */
public class MaterialType {
	private String material_type_id;
	private String parent_material_type_id;
	private String material_type_name;
	private String remark;
	private String user_id;
	private String operate_time;
	
	public String getMaterial_type_id() {
		return material_type_id;
	}
	public void setMaterial_type_id(String material_type_id) {
		this.material_type_id = material_type_id;
	}
	public String getParent_material_type_id() {
		return parent_material_type_id;
	}
	public void setParent_material_type_id(String parent_material_type_id) {
		this.parent_material_type_id = parent_material_type_id;
	}
	public String getMaterial_type_name() {
		return material_type_name;
	}
	public void setMaterial_type_name(String material_type_name) {
		this.material_type_name = material_type_name;
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
	
}
