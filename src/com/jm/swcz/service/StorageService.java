package com.jm.swcz.service;

import android.text.TextUtils;

import com.jm.swcz.dao.StorageDao;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.Storage;

/**
 * 库位服务层
 * @author lenovo
 *
 */
public class StorageService {
	
	private StorageDao storageDao = (StorageDao) BeanFactory.getInstance().getBean(StorageDao.class);
	
	public boolean saveStorage(Storage storage){
		boolean flag = true;
		String storageId = storage.getStorage_id();
		if(TextUtils.isEmpty(storageId)){
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
	
	public Storage findStorage(String storage_id){
		return storageDao.findStorage(storage_id);
	}
}
