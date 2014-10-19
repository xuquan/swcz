package com.jm.swcz.service;

import java.util.List;

import android.text.TextUtils;

import com.jm.swcz.dao.MaterialDao;
import com.jm.swcz.dao.MaterialTypeDao;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.Material;
import com.jm.swcz.model.MaterialType;
import com.jm.swcz.model.PageModel;

/**
 * 物料服务层
 * @author lenovo
 *
 */
public class MaterialService {
	
	private MaterialDao materialDao = (MaterialDao) BeanFactory.getInstance().getBean(MaterialDao.class);
	private MaterialTypeDao materialTypeDao = (MaterialTypeDao) BeanFactory.getInstance().getBean(MaterialTypeDao.class);
	
	public boolean saveMaterial(Material material){
		boolean flag = false;
		String materialId = material.getMaterial_id();
		if(TextUtils.isEmpty(materialId)){
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
		String materialTypeId = material.getMaterial_type_id();
		MaterialType mt = materialTypeDao.findMaterialTypeById(materialTypeId);
		material.setMaterialType(mt);
		return material;
	}
	
	public PageModel<Material> findMaterialList(int pageNo,int pageSize){
		PageModel<Material> pageModel = null;
		List<Material> list = materialDao.findMaterialList(pageNo,pageSize);
		int recordCount = materialDao.getRecordCount();
		pageModel = new PageModel<Material>();
		pageModel.setList(list);
		pageModel.setTotalRecords(recordCount);
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		return pageModel;
	}
}
