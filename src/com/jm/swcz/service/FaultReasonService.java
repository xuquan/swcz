package com.jm.swcz.service;

import java.util.List;
import java.util.UUID;

import android.text.TextUtils;

import com.jm.swcz.dao.FaultReasonDao;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.FaultReason;

/**
 * 故障原因服务层
 * @author lenovo
 *
 */
public class FaultReasonService {
	
	private FaultReasonDao reasonDao = (FaultReasonDao) BeanFactory.getInstance().getBean(FaultReasonDao.class);
	
	public boolean saveFaultReason(FaultReason reason){
		boolean flag = false;
		String reasonId = reason.getReason_id();
		if(TextUtils.isEmpty(reasonId)){
			reason.setReason_id(UUID.randomUUID().toString());
			flag = reasonDao.saveFaultReason(reason);
		}else{
			flag = reasonDao.updateFaultReason(reason);
		}
		return flag;
	}
	
	public boolean updateFaultReason(FaultReason reason){
		return reasonDao.updateFaultReason(reason);
	}
	
	public boolean deleteFaultReason(String reasonId){
		return reasonDao.deleteFaultReason(reasonId);
	}
	
	public FaultReason findFaultReasonById(String reasonId){
		return reasonDao.findFaultReasonById(reasonId);
	}
	
	public List<FaultReason> findFaultReasonList(){
		return reasonDao.findFaultReasonList();
	}
}
