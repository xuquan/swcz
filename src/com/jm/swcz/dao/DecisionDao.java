package com.jm.swcz.dao;

import java.util.List;

import android.text.TextUtils;

import com.jm.swcz.db.DBMgr;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.Decision;

/**
 * 决策持久层
 * @author lenovo
 *
 */
public class DecisionDao{
	
	private DBMgr dbMgr = (DBMgr) BeanFactory.getInstance().getBean(DBMgr.class);

	public boolean saveDecision(Decision decision){
		String sql = "insert into t_decision (decision_id,fault_id,fault_id2,level,reason_id,proportion) values (?,?,?,?,?,?)";
		Object[] bindArgs = new Object[]{decision.getDecision_id(),decision.getFault_id(),decision.getFault_id2(),decision.getLevel(),
				decision.getReason_id(),decision.getProportion()};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public boolean updateDecision(Decision decision){
		String sql = "update t_decision set fault_id=?,fault_id2=?,level=?,reason_id=?,proportion=? where decision_id=?";
		Object[] bindArgs = new Object[]{decision.getFault_id(),decision.getFault_id2(),decision.getLevel(),
				decision.getReason_id(),decision.getProportion(),decision.getDecision_id()};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public boolean deleteDecision(String decisionId){
		String sql = "delete from t_decision where decision_id=?";
		Object[] bindArgs = new Object[]{decisionId};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public Decision findDecisionById(String decisionId){
		Decision decision = null;
		String sql = "select * from t_decision where decision_id=?";
		String[] selectionArgs = new String[]{};
		if(decisionId!=null){
			selectionArgs = new String[]{decisionId};
		}
		decision = dbMgr.querySingleCursor(sql, selectionArgs, Decision.class);
		return decision;
	}

	public List<Decision> findDecisionList() {
		List<Decision> list = null;
		String sql = "select t1.* from t_decision t1";
		list = dbMgr.queryMultiCursor(sql, null, Decision.class);
		return list;
	}

	public List<Decision> findDecisionList(String fault_id1, String fault_id2,String level) {
		List<Decision> list = null;
		StringBuffer sbSql = new StringBuffer("select t1.* from t_decision t1 where 1=1 ");
		if(!TextUtils.isEmpty(fault_id1)){
			sbSql.append(" and t1.fault_id='").append(fault_id1).append("'");
		}
		if(!TextUtils.isEmpty(fault_id2)){
			sbSql.append(" and t1.fault_id2='").append(fault_id2).append("'");
		}
		if(!TextUtils.isEmpty(level)){
			sbSql.append(" and t1.level='").append(level).append("'");
		}
		sbSql.append(" order by t1.proportion desc");
		list = dbMgr.queryMultiCursor(sbSql.toString(), null, Decision.class);
		return list;
	}
	
}
