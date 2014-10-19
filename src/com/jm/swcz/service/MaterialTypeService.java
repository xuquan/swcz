package com.jm.swcz.service;

import java.util.List;
import java.util.UUID;

import android.text.TextUtils;

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
		boolean flag = false;
		String materialTypeId = materialType.getMaterial_type_id();
		if(TextUtils.isEmpty(materialTypeId)){
			materialType.setMaterial_type_id(UUID.randomUUID().toString());
			flag = materialTypeDao.saveMaterialType(materialType);
		}else{
			flag = materialTypeDao.updateMaterialType(materialType);
		}
		return flag;
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
	
	public List<MaterialType> findMaterialTypeList(){
		return materialTypeDao.findMaterialTypeList();
	}
}
