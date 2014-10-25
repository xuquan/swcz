package com.jm.swcz.model;

/**
 * 故障原因
 * @author lenovo
 *
 */
public class FaultReason {
	private String reason_id;
	private String reason_name;
	public String getReason_id() {
		return reason_id;
	}
	public void setReason_id(String reason_id) {
		this.reason_id = reason_id;
	}
	public String getReason_name() {
		return reason_name;
	}
	public void setReason_name(String reason_name) {
		this.reason_name = reason_name;
	}
	
	@Override
	public String toString() {
		return reason_name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((reason_id == null) ? 0 : reason_id.hashCode());
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
		FaultReason other = (FaultReason) obj;
		if (reason_id == null) {
			if (other.reason_id != null)
				return false;
		} else if (!reason_id.equals(other.reason_id))
			return false;
		return true;
	}
	
}
