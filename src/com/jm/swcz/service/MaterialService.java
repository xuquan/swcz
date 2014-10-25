package com.jm.swcz.service;

import java.util.List;
import java.util.UUID;

import android.text.TextUtils;

import com.jm.swcz.dao.DeptDao;
import com.jm.swcz.dao.MaterialDao;
import com.jm.swcz.dao.MaterialTypeDao;
import com.jm.swcz.dao.StorageDao;
import com.jm.swcz.dao.UserDao;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.Dept;
import com.jm.swcz.model.Material;
import com.jm.swcz.model.MaterialType;
import com.jm.swcz.model.PageModel;
import com.jm.swcz.model.Storage;
import com.jm.swcz.model.User;

/**
 * 物料服务层
 * @author lenovo
 *
 */
public class MaterialService {
	
	private MaterialDao materialDao = (MaterialDao) BeanFactory.getInstance().getBean(MaterialDao.class);
	private MaterialTypeDao materialTypeDao = (MaterialTypeDao) BeanFactory.getInstance().getBean(MaterialTypeDao.class);
	private UserDao userDao = (UserDao) BeanFactory.getInstance().getBean(UserDao.class);
	private DeptDao deptDao = (DeptDao) BeanFactory.getInstance().getBean(DeptDao.class);
	private StorageDao storageDao = (StorageDao) BeanFactory.getInstance().getBean(StorageDao.class);
	
	public boolean saveMaterial(Material material){
		boolean flag = false;
		String materialId = material.getMaterial_id();
		if(TextUtils.isEmpty(materialId)){
			material.setMaterial_id(UUID.randomUUID().toString());
			flag = materialDao.saveMaterial(material);
		}else{
			flag = materialDao.updateMaterial(material);
		}
		return flag;
	}
	
	public boolean updateMaterial(Material material){
		return materialDao.updateMaterial(material);
	}
	
	public boolean deleteMaterial(String materialId){
		return materialDao.deleteMaterial(materialId);
	}
	
	public Material findMaterialById(String materialId){
		Material material = materialDao.findMaterialById(materialId);
		loadData(material);
		return material;
	}
	
	public PageModel<Material> findMaterialList(int pageNo,int pageSize){
		PageModel<Material> pageModel = null;
		List<Material> list = materialDao.findMaterialList(pageNo,pageSize);
		if(list!=null && list.size()>0){
			for(Material m : list){
				loadData(m);
			}
		}
		int recordCount = materialDao.getRecordCount();
		pageModel = new PageModel<Material>();
		pageModel.setList(list);
		pageModel.setTotalRecords(recordCount);
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		return pageModel;
	}
	
	private void loadData(Material material){
		if(material!=null){
			MaterialType mt = materialTypeDao.findMaterialTypeById(material.getMaterial_type_id());
			material.setMaterialType(mt);
			User user = userDao.findUserById(material.getUser_id());
			material.setUser(user);
			Storage storage = storageDao.findStorageById(material.getStorage_id());
			material.setStorage(storage);
			Dept dept = deptDao.findDeptById(material.getDept_id());
			material.setDept(dept);
		}
	}
}
