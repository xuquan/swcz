package com.jm.swcz.service;

import java.util.List;
import java.util.UUID;

import android.text.TextUtils;

import com.jm.swcz.dao.MemoDao;
import com.jm.swcz.dao.UserDao;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.Memo;
import com.jm.swcz.model.User;

/**
 * 备忘录服务层
 * @author lenovo
 *
 */
public class MemoService {
	
	private MemoDao memoDao = (MemoDao) BeanFactory.getInstance().getBean(MemoDao.class);
	private UserDao userDao = (UserDao) BeanFactory.getInstance().getBean(UserDao.class);
	
	public boolean saveMemo(Memo memo){
		boolean flag = false;
		String memoId = memo.getMemo_id();
		if(TextUtils.isEmpty(memoId)){
			memo.setMemo_id(UUID.randomUUID().toString());
			flag = memoDao.saveMemo(memo);
		}else{
			flag = memoDao.updateMemo(memo);
		}
		return flag;
	}
	
	public boolean updateMemo(Memo memo){
		return memoDao.updateMemo(memo);
	}
	
	public boolean deleteMemo(String memoId){
		return memoDao.deleteMemo(memoId);
	}
	
	public Memo findMemoById(String memoId){
		Memo memo = memoDao.findMemoById(memoId);
		loadUser(memo);
		return memo;
	}
	
	public List<Memo> findMemoList(){
		List<Memo> list = memoDao.findMemoList();
		if(list!=null && list.size()>0){
			for(Memo memo : list){
				loadUser(memo);
			}
		}
		return list;
	}
	
	private void loadUser(Memo memo){
		if(memo!=null){
			String userId = memo.getUser_id();
			User user = userDao.findUserById(userId);
			memo.setUser(user);
		}
	}
}
