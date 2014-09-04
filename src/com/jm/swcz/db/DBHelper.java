package com.jm.swcz.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

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
		String sql = "create table if not exists user"+
				"(user_id,username,password,real_name,email,address)";

		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "alter table user add mobile_phone integer";
		db.execSQL(sql);
	}

}
