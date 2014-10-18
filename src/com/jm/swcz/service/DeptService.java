package com.jm.swcz.service;

import com.jm.swcz.dao.DeptDao;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.Dept;

/**
 * 部门服务层
 * @author lenovo
 *
 */
public class DeptService {
	
	private DeptDao deptDao = (DeptDao) BeanFactory.getInstance().getBean(DeptDao.class);
	
	public boolean saveDept(Dept dept){
		return deptDao.saveDept(dept);
	}
	
	public boolean updateDept(Dept dept){
		return deptDao.updateDept(dept);
	}
	
	public boolean deleteDept(String deptId){
		return deptDao.deleteDept(deptId);
	}
	
	public Dept findDeptById(String deptId){
		return deptDao.findDeptById(deptId);
	}
}
