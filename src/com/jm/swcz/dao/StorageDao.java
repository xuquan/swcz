package com.jm.swcz.dao;

import com.jm.swcz.db.DBMgr;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.Storage;

/**
 * 库位持久层
 * @author lenovo
 *
 */
public class StorageDao {
	
	private DBMgr dbMgr = (DBMgr) BeanFactory.getInstance().getBean(DBMgr.class);
	
	public boolean saveStorage(Storage storage){
		String sql = "insert into t_storage (storage_id,parent_storage_id,storage_name,remark,user_id,ship_code,operate_time) "+
				"values (?,?,?,?,?,?,?)";
		Object[] bindArgs = new Object[]{storage.getStorage_id(),storage.getParent_storage_id(),
				storage.getStorage_name(),storage.getRemark(),storage.getUser_id(),storage.getShip_code(),storage.getOperate_time()};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public boolean updateStorage(Storage storage){
		String sql = "update t_storage set storage_name=?,remark=?,user_id=?,ship_code=?,operate_time=? "+
				"where storage_id=?";
		Object[] bindArgs = new Object[]{storage.getStorage_name(),storage.getRemark(),storage.getUser_id(),storage.getShip_code(),
				storage.getOperate_time(),storage.getStorage_id()};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public boolean deleteStorage(String storage_id){
		String sql = "delete from t_storage where storage_id=?";
		Object[] bindArgs = new Object[]{storage_id};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public Storage findStorage(String storage_id){
		String sql = "select * from t_storage where storage_id=?";
		String[] selectionArgs = new String[]{storage_id};
		Storage storage = dbMgr.querySingleCursor(sql, selectionArgs, Storage.class);
		return storage;
	}
}
