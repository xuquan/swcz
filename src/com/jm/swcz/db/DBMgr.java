package com.jm.swcz.db;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.jm.swcz.AppContext;

/**
 * 数据库管理器
 * @author lenovo
 *
 */
public class DBMgr {
	private DBHelper helper;
	private SQLiteDatabase db;
	
	public DBMgr(){
		helper = new DBHelper(AppContext.getAppContext());
		db = helper.getWritableDatabase();
	}
	
	/**
	 * 实现对数据库的添加、删除和修改功能
	 * @param sql
	 * @param bindArgs
	 * @return
	 */
	public boolean updateBySQL(String sql,Object[] bindArgs){
		boolean flag = false;
		try {
			db.execSQL(sql,bindArgs);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return flag;
	}
	
	public Map<String,String> queryBySQL(String sql,String[] selectionArgs){
		Map<String,String> map = new HashMap<String,String>();
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		int columnCount = cursor.getColumnCount();
		while(cursor.moveToNext()){
			for(int i=0;i<columnCount;i++){
				String columnName = cursor.getColumnName(i);
				String columnValue = cursor.getString(cursor.getColumnIndex(columnName));
				if(columnValue==null){
					columnValue = "";
				}
				map.put(columnName, columnValue);
			}
		}
		return map;
	}
	
	public List<Map<String,String>> queryMultiMaps(String sql,String[] selectionArgs){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		int columnCount = cursor.getColumnCount();
		while(cursor.moveToNext()){
			Map<String,String> map = new HashMap<String,String>();
			for(int i=0;i<columnCount;i++){
				String columnName = cursor.getColumnName(i);
				String columnValue = cursor.getString(cursor.getColumnIndex(columnName));
				if(columnValue==null){
					columnValue = "";
				}
				map.put(columnName, columnValue);
			}
			list.add(map);
		}
		return list;
	}
	
	public Cursor queryMultiCursor(String sql,String[] selectionArgs){
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		return cursor;
	}
	
	/**
	 * 通过反射获得数据库的查询记录
	 * 声明Class 的属性必须都是String类型
	 * @param sql
	 * @param selectionArgs
	 * @param cls
	 * @return
	 */
	public <T> T querySingleCursor(String sql,String[] selectionArgs,Class<T> cls){
		T t = null;
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		int columnCount = cursor.getColumnCount();
		while(cursor.moveToNext()){
			try {
				t = cls.newInstance();
				for(int i=0;i<columnCount;i++){
					String columnName = cursor.getColumnName(i);
					String columnValue = cursor.getString(cursor.getColumnIndex(columnName));
					if(columnValue==null){
						columnValue = "";
					}
					Field field = cls.getDeclaredField(columnName);
					field.setAccessible(true);
					field.set(t, columnValue);
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
		return t;
	}
	
	public <T> List<T> queryMultiCursor(String sql,String[] selectionArgs,Class<T> cls){
		List<T> list = new ArrayList<T>();
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		int columnCount = cursor.getColumnCount();
		while(cursor.moveToNext()){
			try {
				T t = cls.newInstance();
				for(int i=0;i<columnCount;i++){
					String columnName = cursor.getColumnName(i);
					String columnValue = cursor.getString(cursor.getColumnIndex(columnName));
					if(columnValue==null){
						columnValue = "";
					}
					Field field = cls.getDeclaredField(columnName);
					field.setAccessible(true);
					field.set(t, columnValue);
					list.add(t);
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public boolean insert(String table,String nullColumnHack,ContentValues values){
		boolean flag = false;
		// insert into tableName(a,b,c) values (?,?,?);
		long id = db.insert(table, nullColumnHack, values);
		flag = (id>0?true:false);
		return flag;
	}
	
	/**
	 * update tableName set name=?,address=?,age=? where pid = ?
	 * @param table
	 * @param values
	 * @param whereClause
	 * @param whereArgs
	 * @return
	 */
	public boolean update(String table,ContentValues values,
			String whereClause,String[] whereArgs){
		boolean flag = false;
		int count = db.update(table, values, whereClause, whereArgs);
		flag = (count>0?true:false);
		return flag;
	}
	
	/**
	 * delete from tableName where pid = ?
	 * @param table
	 * @param whereClause
	 * @param whereArgs
	 * @return
	 */
	public boolean delete(String table,String whereClause,String[] whereArgs){
		boolean flag = false;
		int count = db.delete(table, whereClause, whereArgs);
		flag = (count>0?true:false);
		return flag;
	}
	
	/**
	 * sql标准写法:select [distinct][columnName]，... from tableName
	 * [where][selection][selectionArgs] [groupBy][having][order by][limit];
	 * 
	 * @param distinct 去掉重复的记录
	 * @param table 表名
	 * @param columns 列名
	 * @param selection 查询的过滤条件
	 * @param selectionArgs 过滤条件的值
	 * @param groupBy 分组
	 * @param having 对分组进行条件过滤
	 * @param orderBy 排序
	 * @param limit 分页
	 * @return
	 */
	public Cursor query(String table,String[] columns,String selection,
			String[] selectionArgs,String groupBy,String having,
			String orderBy,String limit){
		Cursor cursor = null;
		cursor = db.query(table, columns, selection, selectionArgs, 
				groupBy, having, orderBy, limit);
		return cursor;
	}
}
