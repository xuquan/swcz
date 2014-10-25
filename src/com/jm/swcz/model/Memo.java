package com.jm.swcz.model;

/**
 * 备忘录
 * @author lenovo
 *
 */
public class Memo {
	private String memo_id;
	private String content;
	private String user_id;
	private User user;
	private String operate_time;
	
	public String getMemo_id() {
		return memo_id;
	}
	public void setMemo_id(String memo_id) {
		this.memo_id = memo_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getOperate_time() {
		return operate_time;
	}
	public void setOperate_time(String operate_time) {
		this.operate_time = operate_time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
