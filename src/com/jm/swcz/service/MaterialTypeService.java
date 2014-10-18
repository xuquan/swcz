package com.jm.swcz.service;

import com.jm.swcz.dao.MaterialTypeDao;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.MaterialType;

/**
 * 物料类别服务层
 * @author lenovo
 *
 */
public class MaterialTypeService {
	
	private MaterialTypeDao materialTypeDao = (MaterialTypeDao) BeanFactory.getInstance().getBean(MaterialTypeDao.class);
	
	public boolean saveMaterialType(MaterialType materialType){
		return materialTypeDao.saveMaterialType(materialType);
	}
	
	public boolean updateMaterialType(MaterialType materialType){
		return materialTypeDao.updateMaterialType(materialType);
	}
	
	public boolean deleteMaterialType(String materialTypeId){
		return materialTypeDao.deleteMaterialType(materialTypeId);
	}
	
	public MaterialType findMaterialTypeById(String materialTypeId){
		return materialTypeDao.findMaterialTypeById(materialTypeId);
	}
}
