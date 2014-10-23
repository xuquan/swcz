package com.jm.swcz.dao;

import java.util.List;

import android.text.TextUtils;

import com.jm.swcz.db.DBMgr;
import com.jm.swcz.factory.BeanFactory;
import com.jm.swcz.model.MaterialType;

/**
 * 物料类别持久层
 * @author lenovo
 *
 */
public class MaterialTypeDao{
	
	private DBMgr dbMgr = (DBMgr) BeanFactory.getInstance().getBean(DBMgr.class);

	public boolean saveMaterialType(MaterialType materialType){
		String sql = "insert into t_material_type (material_type_id,parent_material_type_id,material_type_name,remark,user_id,operate_time)" +
				" values (?,?,?,?,?,?)";
		Object[] bindArgs = new Object[]{materialType.getMaterial_type_id(),materialType.getParent_material_type_id(),
				materialType.getMaterial_type_name(),materialType.getRemark(),materialType.getUser_id(),materialType.getOperate_time()};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public boolean updateMaterialType(MaterialType materialType){
		String sql = "update t_material_type set parent_material_type_id=?,material_type_name=?,remark=?,user_id=?,operate_time=? where material_type_id=?";
		Object[] bindArgs = new Object[]{materialType.getParent_material_type_id(),
				materialType.getMaterial_type_name(),materialType.getRemark(),
				materialType.getUser_id(),materialType.getOperate_time(),
				materialType.getMaterial_type_id()};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public boolean deleteMaterialType(String materialTypeId){
		String sql = "delete from t_material_type where material_type_id=?";
		Object[] bindArgs = new Object[]{materialTypeId};
		return dbMgr.updateBySQL(sql, bindArgs);
	}
	
	public MaterialType findMaterialTypeById(String materialTypeId){
		MaterialType materialType = null;
		String sql = "select * from t_material_type where material_type_id=?";
		String[] selectionArgs = new  String[]{};
		if(!TextUtils.isEmpty(materialTypeId)){
			selectionArgs = new String[]{materialTypeId};
		}
		materialType = dbMgr.querySingleCursor(sql, selectionArgs, MaterialType.class);
		return materialType;
	}
	
	public List<MaterialType> findMaterialTypeList(){
		List<MaterialType> materialTypeList = null;
		String sql = "select * from t_material_type";
		String[] selectionArgs = new String[]{};
		materialTypeList = dbMgr.queryMultiCursor(sql, selectionArgs, MaterialType.class);
		return materialTypeList;
	}
}
