package com.jm.swcz.service;

import java.util.List;
import java.util.UUID;

import android.text.TextUtils;

import com.jm.swcz.dao.DecisionDao;
import com.jm.swcz.dao.FaultDao;
import com.jm.swcz.dao.FaultReasonDao;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.Decision;
import com.jm.swcz.model.Fault;
import com.jm.swcz.model.FaultReason;

/**
 * 决策服务层
 * @author lenovo
 *
 */
public class DecisionService {
	
	private DecisionDao decisionDao = (DecisionDao) BeanFactory.getInstance().getBean(DecisionDao.class);
	private FaultDao faultDao = (FaultDao) BeanFactory.getInstance().getBean(FaultDao.class);
	private FaultReasonDao faultReasonDao = (FaultReasonDao) BeanFactory.getInstance().getBean(FaultReasonDao.class);
	
	public boolean saveDecision(Decision decision){
		boolean flag = false;
		String decisionId = decision.getDecision_id();
		if(TextUtils.isEmpty(decisionId)){
			decision.setDecision_id(UUID.randomUUID().toString());
			flag = decisionDao.saveDecision(decision);
		}else{
			flag = decisionDao.updateDecision(decision);
		}
		return flag;
	}
	
	public boolean updateDecision(Decision decision){
		return decisionDao.updateDecision(decision);
	}
	
	public boolean deleteDecision(String decisionId){
		return decisionDao.deleteDecision(decisionId);
	}
	
	public Decision findDecisionById(String decisionId){
		Decision decision = decisionDao.findDecisionById(decisionId);
		loadData(decision);
		return decision;
	}
	
	public List<Decision> findDecisionList(){
		List<Decision> list = decisionDao.findDecisionList();
		if(list!=null && list.size()>0){
			for(Decision decision : list){
				loadData(decision);
			}
		}
		return list;
	}
	
	public List<Decision> findDecisionList(String fault_id1,String fault_id2,String level){
		List<Decision> list = decisionDao.findDecisionList(fault_id1,fault_id2,level);
		if(list!=null && list.size()>0){
			for(Decision decision : list){
				loadData(decision);
			}
		}
		return list;
	}
	
	private void loadData(Decision decision){
		if(decision!=null){
			String faultId = decision.getFault_id();
			String faultId2 = decision.getFault_id2();
			String reasonId = decision.getReason_id();
			Fault fault = faultDao.findFaultById(faultId);
			Fault fault2 = faultDao.findFaultById(faultId2);
			FaultReason reason = faultReasonDao.findFaultReasonById(reasonId);
			decision.setFault(fault);
			decision.setFault2(fault2);
			decision.setReason(reason);
		}
	}
}
