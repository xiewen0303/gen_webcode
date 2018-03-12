package com.codegen.metadata;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 表以及映射信息 元数据
 * @author ShiJie Chi
 * @created 2010-4-25 下午08:29:39	
 */
public class TableMapperMetaData {

	private TableMetaData tMetaData;
	
	private List<ColumnMapperMetaData> othersCMetaDatas = new ArrayList<ColumnMapperMetaData>();
	
	private List<ColumnMapperMetaData> primaryCMetaDatas = new ArrayList<ColumnMapperMetaData>();
	
	public TableMapperMetaData(TableMetaData tMetaData) {
		this.tMetaData = tMetaData;
		
		List<ColumnMetaData> pCMetaDatas = tMetaData.getPrimaryColumns();
		for(ColumnMetaData cMetaData : pCMetaDatas){
			primaryCMetaDatas.add(new ColumnMapperMetaData(cMetaData));
		}
		
		List<ColumnMetaData> cMetaDatas = tMetaData.getOtherColumns();
		for(ColumnMetaData cMetaData : cMetaDatas){
			othersCMetaDatas.add(new ColumnMapperMetaData(cMetaData));
		}
		
	}
	
	public List<ColumnMapperMetaData> getOthersCMetaDatas() {
		return othersCMetaDatas;
	}

	public List<ColumnMapperMetaData> getPrimaryCMetaDatas() {
		return primaryCMetaDatas;
	}
	
	public List<ColumnMapperMetaData> getAllCMapperMetaDatas(){
		List<ColumnMapperMetaData> allCMapperMetaDatas = new ArrayList<ColumnMapperMetaData>();
		allCMapperMetaDatas.addAll(primaryCMetaDatas);
		allCMapperMetaDatas.addAll(othersCMetaDatas);
		return allCMapperMetaDatas;
	}

	public String getTableName() {
		return tMetaData.getTableName();
	}

	public String getCatagory() {
		return tMetaData.getCatagory();
	}
	
}
