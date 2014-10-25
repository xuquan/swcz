package com.jm.swcz.service;

import java.util.List;
import java.util.UUID;

import android.text.TextUtils;

import com.jm.swcz.dao.FaultDao;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.Fault;

/**
 * 故障现象服务层
 * @author lenovo
 *
 */
public class FaultService {
	
	private FaultDao faultDao = (FaultDao) BeanFactory.getInstance().getBean(FaultDao.class);
	
	public boolean saveFault(Fault fault){
		boolean flag = false;
		String faultId = fault.getFault_id();
		if(TextUtils.isEmpty(faultId)){
			fault.setFault_id(UUID.randomUUID().toString());
			flag = faultDao.saveFault(fault);
		}else{
			flag = faultDao.updateFault(fault);
		}
		return flag;
	}
	
	public boolean updateFault(Fault fault){
		return faultDao.updateFault(fault);
	}
	
	public boolean deleteFault(String faultId){
		return faultDao.deleteFault(faultId);
	}
	
	public Fault findFaultById(String faultId){
		return faultDao.findFaultById(faultId);
	}
	
	public List<Fault> findFaultList(){
		return faultDao.findFaultList();
	}
}
