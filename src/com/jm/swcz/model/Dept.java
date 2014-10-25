package com.jm.swcz.model;

/**
 * 部门
 * @author lenovo
 *
 */
public class Dept {
	private String dept_id;
	private String parent_dept_id;
	private String dept_name;
	private String dept_contactor;
	private String telephone;
	private String address;
	
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	public String getParent_dept_id() {
		return parent_dept_id;
	}
	public void setParent_dept_id(String parent_dept_id) {
		this.parent_dept_id = parent_dept_id;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getDept_contactor() {
		return dept_contactor;
	}
	public void setDept_contactor(String dept_contactor) {
		this.dept_contactor = dept_contactor;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dept_id == null) ? 0 : dept_id.hashCode());
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
		Dept other = (Dept) obj;
		if (dept_id == null) {
			if (other.dept_id != null)
				return false;
		} else if (!dept_id.equals(other.dept_id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return dept_name;
	}
}
