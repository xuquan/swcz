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
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//String sql = "alter table t_user add mobile_phone integer";
		//db.execSQL(sql);
	}

}
