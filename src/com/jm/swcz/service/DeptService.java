package com.jm.swcz.service;

import java.util.UUID;

import android.text.TextUtils;

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
		boolean flag = false;
		String deptId = dept.getDept_id();
		if(TextUtils.isEmpty(deptId)){
			dept.setDept_id(UUID.randomUUID().toString());
			flag = deptDao.saveDept(dept);
		}else{
			flag = deptDao.updateDept(dept);
		}
		return flag;
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
