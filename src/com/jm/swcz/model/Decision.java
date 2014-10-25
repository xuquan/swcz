package com.jm.swcz.model;

/**
 * 决策实体
 * @author lenovo
 *
 */
public class Decision {
	private String decision_id;
	private String fault_id;
	private Fault fault;
	private String level;
	private String reason_id;
	private FaultReason reason;
	private String proportion;
	
	public String getDecision_id() {
		return decision_id;
	}
	public void setDecision_id(String decision_id) {
		this.decision_id = decision_id;
	}
	public String getFault_id() {
		return fault_id;
	}
	public void setFault_id(String fault_id) {
		this.fault_id = fault_id;
	}
	public Fault getFault() {
		return fault;
	}
	public void setFault(Fault fault) {
		this.fault = fault;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getReason_id() {
		return reason_id;
	}
	public void setReason_id(String reason_id) {
		this.reason_id = reason_id;
	}
	public FaultReason getReason() {
		return reason;
	}
	public void setReason(FaultReason reason) {
		this.reason = reason;
	}
	public String getProportion() {
		return proportion;
	}
	public void setProportion(String proportion) {
		this.proportion = proportion;
	}
}
