package com.jm.swcz.dao;

import java.util.List;

import com.jm.swcz.db.DBMgr;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.Dept;

/**
 * 部门持久层
 * @author lenovo
 *
 */
public class DeptDao{
	
	private DBMgr dbMgr = (DBMgr) BeanFactory.getInstance().getBean(DBMgr.class);

	public boolean saveDept(Dept dept){
		String sql = "insert into t_dept (dept_id,parent_dept_id,dept_name,dept_contactor,telephone,address)" +
				" values (?,?,?,?,?,?)";
		Object[] bindArgs = new Object[]{dept.getDept_id(),dept.getParent_dept_id(),dept.getDept_name(),
				dept.getDept_contactor(),dept.getTelephone(),dept.getAddress()};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public boolean updateDept(Dept dept){
		String sql = "update t_dept set parent_dept_id=?,dept_name=?,dept_contactor=?,telephone=?,address=? where dept_id=?";
		Object[] bindArgs = new Object[]{dept.getParent_dept_id(),dept.getDept_name(),
				dept.getDept_contactor(),dept.getTelephone(),dept.getAddress(),dept.getDept_id()};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public boolean deleteDept(String deptId){
		String sql = "delete from t_dept where dept_id=?";
		Object[] bindArgs = new Object[]{deptId};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public Dept findDeptById(String deptId){
		Dept dept = null;
		String sql = "select * from t_dept where dept_id=?";
		String[] selectionArgs = new String[]{};
		if(deptId!=null){
			selectionArgs = new String[]{deptId};
		}
		dept = dbMgr.querySingleCursor(sql, selectionArgs, Dept.class);
		return dept;
	}

	public List<Dept> findDeptList() {
		List<Dept> list = null;
		String sql = "select t1.* from t_dept t1";
		list = dbMgr.queryMultiCursor(sql, null, Dept.class);
		return list;
	}
	
}
