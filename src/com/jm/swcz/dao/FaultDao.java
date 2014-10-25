package com.jm.swcz.dao;

import java.util.List;

import com.jm.swcz.db.DBMgr;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.Fault;

/**
 * 故障现象持久层
 * @author lenovo
 *
 */
public class FaultDao{
	
	private DBMgr dbMgr = (DBMgr) BeanFactory.getInstance().getBean(DBMgr.class);

	public boolean saveFault(Fault fault){
		String sql = "insert into t_fault (fault_id,fault_name) values (?,?)";
		Object[] bindArgs = new Object[]{fault.getFault_id(),fault.getFault_name()};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public boolean updateFault(Fault fault){
		String sql = "update t_fault set fault_name=? where fault_id=?";
		Object[] bindArgs = new Object[]{fault.getFault_name(),fault.getFault_id()};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public boolean deleteFault(String faultId){
		String sql = "delete from t_fault where fault_id=?";
		Object[] bindArgs = new Object[]{faultId};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public Fault findFaultById(String faultId){
		Fault fault = null;
		String sql = "select * from t_fault where fault_id=?";
		String[] selectionArgs = new String[]{};
		if(faultId!=null){
			selectionArgs = new String[]{faultId};
		}
		fault = dbMgr.querySingleCursor(sql, selectionArgs, Fault.class);
		return fault;
	}

	public List<Fault> findFaultList() {
		List<Fault> list = null;
		String sql = "select t1.* from t_fault t1";
		list = dbMgr.queryMultiCursor(sql, null, Fault.class);
		return list;
	}
	
}
