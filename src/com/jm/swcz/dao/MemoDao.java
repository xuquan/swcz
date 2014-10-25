package com.jm.swcz.dao;

import java.util.List;

import com.jm.swcz.db.DBMgr;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.Memo;

/**
 * 备忘录持久层
 * @author lenovo
 *
 */
public class MemoDao{
	
	private DBMgr dbMgr = (DBMgr) BeanFactory.getInstance().getBean(DBMgr.class);

	public boolean saveMemo(Memo memo){
		String sql = "insert into t_memo (memo_id,content,user_id,operate_time) values (?,?,?,?)";
		Object[] bindArgs = new Object[]{memo.getMemo_id(),memo.getContent(),memo.getUser_id(),memo.getOperate_time()};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public boolean updateMemo(Memo memo){
		String sql = "update t_memo set content=?,user_id=?,operate_time=? where memo_id=?";
		Object[] bindArgs = new Object[]{memo.getContent(),memo.getUser_id(),memo.getOperate_time(),memo.getMemo_id()};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public boolean deleteMemo(String memoId){
		String sql = "delete from t_memo where memo_id=?";
		Object[] bindArgs = new Object[]{memoId};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public Memo findMemoById(String memoId){
		Memo memo = null;
		String sql = "select * from t_memo where memo_id=?";
		String[] selectionArgs = new String[]{};
		if(memoId!=null){
			selectionArgs = new String[]{memoId};
		}
		memo = dbMgr.querySingleCursor(sql, selectionArgs, Memo.class);
		return memo;
	}

	public List<Memo> findMemoList() {
		List<Memo> list = null;
		String sql = "select t1.* from t_memo t1";
		list = dbMgr.queryMultiCursor(sql, null, Memo.class);
		return list;
	}
	
}
