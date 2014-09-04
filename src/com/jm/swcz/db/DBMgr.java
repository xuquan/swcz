package com.jm.swcz.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBMgr {
	private DBHelper helper;
	private SQLiteDatabase db;
	
	public DBMgr(Context context){
		helper = new DBHelper(context);
		db = helper.getWritableDatabase();
	}
	
	public boolean updateBySQL(String sql,Object[] bindArgs){
		boolean flag = false;
		try {
			db.execSQL(sql);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(db!=null && db.isOpen()){
				db.close();
			}
		}
		return flag;
	}
}
