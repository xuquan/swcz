package com.jm.swcz.dao;

import java.util.List;

import com.jm.swcz.db.DBMgr;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.FaultReason;

/**
 * 故障原因持久层
 * @author lenovo
 *
 */
public class FaultReasonDao{
	
	private DBMgr dbMgr = (DBMgr) BeanFactory.getInstance().getBean(DBMgr.class);

	public boolean saveFaultReason(FaultReason reason){
		String sql = "insert into t_fault_reason (reason_id,reason_name) values (?,?)";
		Object[] bindArgs = new Object[]{reason.getReason_id(),reason.getReason_name()};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public boolean updateFaultReason(FaultReason reason){
		String sql = "update t_fault_reason set reason_name=? where reason_id=?";
		Object[] bindArgs = new Object[]{reason.getReason_name(),reason.getReason_id()};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public boolean deleteFaultReason(String reasonId){
		String sql = "delete from t_fault_reason where reason_id=?";
		Object[] bindArgs = new Object[]{reasonId};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public FaultReason findFaultReasonById(String reasonId){
		FaultReason reason = null;
		String sql = "select * from t_fault_reason where reason_id=?";
		String[] selectionArgs = new String[]{};
		if(reasonId!=null){
			selectionArgs = new String[]{reasonId};
		}
		reason = dbMgr.querySingleCursor(sql, selectionArgs, FaultReason.class);
		return reason;
	}

	public List<FaultReason> findFaultReasonList() {
		List<FaultReason> list = null;
		String sql = "select t1.* from t_fault_reason t1";
		list = dbMgr.queryMultiCursor(sql, null, FaultReason.class);
		return list;
	}
	
}
