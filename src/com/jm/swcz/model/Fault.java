package com.jm.swcz.model;

/**
 * 故障现象
 * @author lenovo
 *
 */
public class Fault {
	private String fault_id;
	private String fault_name;
	
	public String getFault_id() {
		return fault_id;
	}
	public void setFault_id(String fault_id) {
		this.fault_id = fault_id;
	}
	public String getFault_name() {
		return fault_name;
	}
	public void setFault_name(String fault_name) {
		this.fault_name = fault_name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fault_id == null) ? 0 : fault_id.hashCode());
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
		Fault other = (Fault) obj;
		if (fault_id == null) {
			if (other.fault_id != null)
				return false;
		} else if (!fault_id.equals(other.fault_id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return fault_name;
	}
}
