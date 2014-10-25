package com.jm.swcz.dao;

import java.util.List;

import android.text.TextUtils;

import com.jm.swcz.db.DBMgr;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.Material;

/**
 * 物料持久层
 * @author lenovo
 *
 */
public class MaterialDao{
	
	private DBMgr dbMgr = (DBMgr) BeanFactory.getInstance().getBean(DBMgr.class);

	public boolean saveMaterial(Material material){
		String sql = "insert into t_material (material_id,material_type_id,material_code," +
				"material_name_cn,material_name_en,specifications," +
				"material_no,size_weight,reality_storage," +
				"legal_storage,amount,storage_up_limit,storage_down_limit,storage_id," +
				"manufacturer_code,manufacturer_name," +
				"dept_id,duty_person,remark,user_id,operate_time)" +
				" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] bindArgs = new Object[]{material.getMaterial_id(),material.getMaterial_type_id(),material.getMaterial_code(),
				material.getMaterial_name_cn(),material.getMaterial_name_en(),material.getSpecifications(),
				material.getMaterial_no(),material.getSize_weight(),material.getReality_storage(),
				material.getLegal_storage(),material.getAmount(),material.getStorage_up_limit(),material.getStorage_down_limit(),
				material.getStorage_id(),material.getManufacturer_code(),material.getManufacturer_name(),
				material.getDept_id(),material.getDuty_person(),material.getRemark(),material.getUser_id(),material.getOperate_time()};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public boolean updateMaterial(Material material){
		String sql = "update t_material set material_type_id=?,material_code=?," +
				"material_name_cn=?,material_name_en=?,specifications=?," +
				"material_no=?,size_weight=?,reality_storage=?," +
				"legal_storage=?,amount=?,storage_up_limit=?,storage_down_limit=?,storage_id=?," +
				"manufacturer_code=?,manufacturer_name=?," +
				"dept_id=?,duty_person=?,remark=?,user_id=?,operate_time=? where material_id=?";
		Object[] bindArgs = new Object[]{material.getMaterial_type_id(),material.getMaterial_code(),
				material.getMaterial_name_cn(),material.getMaterial_name_en(),material.getSpecifications(),
				material.getMaterial_no(),material.getSize_weight(),material.getReality_storage(),
				material.getLegal_storage(),material.getAmount(),material.getStorage_up_limit(),material.getStorage_down_limit(),
				material.getStorage_id(),material.getManufacturer_code(),material.getManufacturer_name(),
				material.getDept_id(),material.getDuty_person(),material.getRemark(),material.getUser_id(),material.getOperate_time(),material.getMaterial_id()};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public boolean deleteMaterial(String materialId){
		String sql = "delete from t_material where material_id=?";
		Object[] bindArgs = new Object[]{materialId};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public Material findMaterialById(String materialId){
		Material material = null;
		String sql = "select * from t_material where material_id=?";
		String[] selectionArgs = new String[]{};
		if(!TextUtils.isEmpty(materialId)){
			selectionArgs = new String[]{materialId};
		}
		material = dbMgr.querySingleCursor(sql, selectionArgs, Material.class);
		return material;
	}
	
	public List<Material> findMaterialList(int pageNo, int pageSize){
		List<Material> list = null;
		String sql = "select t1.* from t_material t1 limit ?,?";
		int pageStart = (pageNo-1) * pageSize;
		String[] selectionArgs = new String[]{String.valueOf(pageStart),String.valueOf(pageSize)};
		list = dbMgr.queryMultiCursor(sql, selectionArgs, Material.class);
		return list;
	}
	
	public int getRecordCount(){
		String sql = "select count(*) as count from t_material";
		int count = dbMgr.queryTotalRecords(sql, null);
		return count;
	}
}
