package com.jm.swcz.dao;

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
				"material_no,size_weight,other_tech_data,safe_storage," +
				"legal_storage,amount,storage_up_limit,storage_down_limit,unit,storage_id," +
				"manufacturer_code,manufacturer_name,manufacturer_reference_number," +
				"internal_price,internal_price_unit,enter_price,enter_price_unit," +
				"dept_id,duty_person,remark,user_id,operate_time)" +
				" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] bindArgs = new Object[]{material.getMaterial_id(),material.getMaterial_type_id(),material.getMaterial_code(),
				material.getMaterial_name_cn(),material.getMaterial_name_en(),material.getSpecifications(),
				material.getMaterial_no(),material.getSize_weight(),material.getOther_tech_data(),material.getSafe_storage(),
				material.getLegal_storage(),material.getAmount(),material.getStorage_up_limit(),material.getStorage_down_limit(),
				material.getUnit(),material.getStorage_id(),
				material.getManufacturer_code(),material.getManufacturer_name(),material.getManufacturer_reference_number(),
				material.getInternal_price(),material.getInternal_price_unit(),material.getEnter_price(),material.getEnter_price_unit(),
				material.getDept_id(),material.getDuty_person(),material.getRemark(),material.getUser_id(),material.getOperate_time()};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public boolean updateMaterial(Material material){
		String sql = "update t_material set material_type_id=?,material_code=?," +
				"material_name_cn=?,material_name_en=?,specifications=?," +
				"material_no=?,size_weight=?,other_tech_data=?,safe_storage=?," +
				"legal_storage=?,amount=?,storage_up_limit=?,storage_down_limit=?,unit,storage_id=?," +
				"manufacturer_code=?,manufacturer_name=?,manufacturer_reference_number=?," +
				"internal_price=?,internal_price_unit=?,enter_price=?,enter_price_unit=?," +
				"dept_id=?,duty_person=?,remark=?,user_id=?,operate_time=? where material_id=?";
		Object[] bindArgs = new Object[]{material.getMaterial_type_id(),material.getMaterial_code(),
				material.getMaterial_name_cn(),material.getMaterial_name_en(),material.getSpecifications(),
				material.getMaterial_no(),material.getSize_weight(),material.getOther_tech_data(),material.getSafe_storage(),
				material.getLegal_storage(),material.getAmount(),material.getStorage_up_limit(),material.getStorage_down_limit(),
				material.getUnit(),material.getStorage_id(),
				material.getManufacturer_code(),material.getManufacturer_name(),material.getManufacturer_reference_number(),
				material.getInternal_price(),material.getInternal_price_unit(),material.getEnter_price(),material.getEnter_price_unit(),
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
		String[] selectionArgs = new String[]{materialId};
		material = dbMgr.querySingleCursor(sql, selectionArgs, Material.class);
		return material;
	}
	
}
