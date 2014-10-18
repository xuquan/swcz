package com.jm.swcz.service;

import com.jm.swcz.dao.MaterialDao;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.Material;

/**
 * 物料服务层
 * @author lenovo
 *
 */
public class MaterialService {
	
	private MaterialDao materialDao = (MaterialDao) BeanFactory.getInstance().getBean(MaterialDao.class);
	
	public boolean saveMaterial(Material material){
		return materialDao.saveMaterial(material);
	}
	
	public boolean updateMaterial(Material material){
		return materialDao.updateMaterial(material);
	}
	
	public boolean deleteMaterial(String materialId){
		return materialDao.deleteMaterial(materialId);
	}
	
	public Material findMaterialById(String materialId){
		return materialDao.findMaterialById(materialId);
	}
}
