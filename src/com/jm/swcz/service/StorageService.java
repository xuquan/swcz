package com.jm.swcz.service;

import java.util.List;
import java.util.UUID;

import android.text.TextUtils;

import com.jm.swcz.dao.StorageDao;
import com.jm.swcz.dao.UserDao;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.Storage;
import com.jm.swcz.model.User;

/**
 * 库位服务层
 * @author lenovo
 *
 */
public class StorageService {
	
	private StorageDao storageDao = (StorageDao) BeanFactory.getInstance().getBean(StorageDao.class);
	private UserDao userDao = (UserDao) BeanFactory.getInstance().getBean(UserDao.class);
	
	public boolean saveStorage(Storage storage){
		boolean flag = true;
		String storageId = storage.getStorage_id();
		if(TextUtils.isEmpty(storageId)){
			storage.setStorage_id(UUID.randomUUID().toString());
			flag = storageDao.saveStorage(storage);
		}else{
			flag = storageDao.updateStorage(storage);
		}
		return flag;
	}
	
	public boolean updateStorage(Storage storage){
		return storageDao.updateStorage(storage);
	}
	
	public boolean deleteStorage(String storage_id){
		return storageDao.deleteStorage(storage_id);
	}
	
	public Storage findStorageById(String storage_id){
		Storage storage = storageDao.findStorageById(storage_id);
		loadUser(storage);
		return storage;
	}

	public List<Storage> findStorageList() {
		List<Storage> list = storageDao.findStorageList();
		if(list!=null && list.size()>0){
			for(Storage storage : list){
				loadUser(storage);
			}
		}
		return list;
	}
	
	private void loadUser(Storage storage){
		if(storage!=null){
			String userId = storage.getUser_id();
			User user = userDao.findUserById(userId);
			storage.setUser(user);
		}
	}
}
