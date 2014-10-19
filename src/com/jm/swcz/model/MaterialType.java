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
	@Override
	public String toString() {
		return material_type_name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((material_type_id == null) ? 0 : material_type_id.hashCode());
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
		MaterialType other = (MaterialType) obj;
		if (material_type_id == null) {
			if (other.material_type_id != null)
				return false;
		} else if (!material_type_id.equals(other.material_type_id))
			return false;
		return true;
	}
	
}
