package com.jm.swcz.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库帮助类
 * @author lenovo
 *
 */
public class DBHelper extends SQLiteOpenHelper {
	
	private final static String DB_NAME = "swcz.db";
	
	private final static int VERSION = 1; // 数据库版本
	
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	
	public DBHelper(Context context, String name,int version) {
		super(context, name, null, version);
	}
	
	public DBHelper(Context context, String name){
		this(context,name,VERSION);
	}
	
	public DBHelper(Context context){
		this(context,DB_NAME,VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 部门表
		String deptCreatSql = "create table t_dept (dept_id varchar2 primary key,parent_dept_id varchar2," +
				"dept_name varchar2,dept_contactor varchar2,telephone varchar2,address varchar2)";
		db.execSQL(deptCreatSql);
		
		// 用户表
		String userCreateSql = "create table t_user (user_id varchar2 primary key, username varchar2, password varchar2, " +
				"real_name varchar2, email varchar2, address varchar2, mobile_phone varchar2)";
		db.execSQL(userCreateSql);
		String userInsertSql = "insert into t_user values('1','admin','sa','管理员','admin@qq.com','集美大学','13564323532')";
		db.execSQL(userInsertSql);
		
		// 库位表
		String storageCreateSql = "create table t_storage (storage_id varchar2 primary key,parent_storage_id varchar2,"+
				"storage_name varchar2,remark varchar2,user_id varchar2,ship_code varchar2,operate_time varchar2)";
		db.execSQL(storageCreateSql);
		
		// 物料类别表
		String materialTypeCreateSql = "create table t_material_type (material_type_id varchar2 primary key," +
				"parent_material_type_id varchar2,material_type_name varchar2,remark varchar2,user_id varchar2," +
				"operate_time varchar2)";
		db.execSQL(materialTypeCreateSql);
		
		// 物料表
		String materialCreateSql = "create table t_material (material_id varchar2 primary key," +
				"material_type_id varchar2,material_code varchar2,material_name_cn varchar2," +
				"material_name_en varchar2,specifications varchar2,material_no varchar2," +
				"size_weight varchar2,reality_storage varchar2," +
				"legal_storage varchar2,amount varchar2,storage_up_limit varchar2," +
				"storage_down_limit varchar2,storage_id varchar2," +
				"manufacturer_code varchar2,manufacturer_name varchar2," +
				"dept_id varchar2,duty_person varchar2,remark varchar2,user_id varchar2,operate_time varchar2)";
		db.execSQL(materialCreateSql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//String sql = "alter table t_user add mobile_phone integer";
		//db.execSQL(sql);
	}

}
