package com.jm.swcz.model;

import java.io.Serializable;

/**
 * 库位
 * @author lenovo
 *
 */
public class Storage implements Serializable{

	private static final long serialVersionUID = -3173049357620733933L;

	private String storage_id;
	private String parent_storage_id;
	private String storage_name;
	private String remark;
	private String user_id;
	private String user_name;
	private String ship_code;
	private String operate_time;
	
	public String getStorage_id() {
		return storage_id;
	}
	public void setStorage_id(String storage_id) {
		this.storage_id = storage_id;
	}
	public String getParent_storage_id() {
		return parent_storage_id;
	}
	public void setParent_storage_id(String parent_storage_id) {
		this.parent_storage_id = parent_storage_id;
	}
	public String getStorage_name() {
		return storage_name;
	}
	public void setStorage_name(String storage_name) {
		this.storage_name = storage_name;
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
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getShip_code() {
		return ship_code;
	}
	public void setShip_code(String ship_code) {
		this.ship_code = ship_code;
	}
	public String getOperate_time() {
		return operate_time;
	}
	public void setOperate_time(String operate_time) {
		this.operate_time = operate_time;
	}
	
}
