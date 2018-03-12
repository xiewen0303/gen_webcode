package com.codegen.metadata;

import com.codegen.mapper.BeanPropertyColumnMapRule;
import com.codegen.mapper.IColumnMapRule;
import com.codegen.mapper.IColumnTypeMapRule;
import com.codegen.mapper.JTypeColumnTypeMapRule;

/**
 * @description 表字段以及映射信息 元数据
 * @author ShiJie Chi
 * @created 2010-4-22 下午08:32:08	
 */
public class ColumnMapperMetaData {
	
	private String beanPropertyName; 
	
	private Class javaType;
	
	private ColumnMetaData cMetaData;
	
	private IColumnMapRule cMapRule;
	
	private IColumnTypeMapRule cTypeMapRule;
	
	public ColumnMapperMetaData(ColumnMetaData cMetaData) {
		
		//Default map rule to set
		IColumnMapRule cMapRule = new BeanPropertyColumnMapRule();
		IColumnTypeMapRule cTypeMapRule = new JTypeColumnTypeMapRule();
		
		initialize(cMetaData ,cMapRule,cTypeMapRule);
	}

	public ColumnMapperMetaData(ColumnMetaData cMetaData,IColumnMapRule cMapRule,IColumnTypeMapRule cTypeMapRule) {
		initialize(cMetaData, cMapRule, cTypeMapRule);
	}
	
	private void initialize(ColumnMetaData cMetaData,IColumnMapRule cMapRule,IColumnTypeMapRule cTypeMapRule) {
		this.cMetaData = cMetaData;
		this.cMapRule = cMapRule;
		this.cTypeMapRule = cTypeMapRule;
		
		beanPropertyName = cMapRule.convert(cMetaData.getColumn());
		javaType = cTypeMapRule.convert(cMetaData.getSqlType());
	}

	public void setcMapRule(IColumnMapRule cMapRule) {
		this.cMapRule = cMapRule;
	}

	public void setcTypeMapRule(IColumnTypeMapRule cTypeMapRule) {
		this.cTypeMapRule = cTypeMapRule;
	}

	public String getJavaTypeName() {
		return javaType.getName();
	}
	
	public String getJavaTypeSimpleName(){
		return javaType.getSimpleName();
	}

	public String getBeanPropertyName() {
		return beanPropertyName;
	}
	
	public String getColumn() {
		return cMetaData.getColumn();
	}
	
	public int getColumnSize() {
		return cMetaData.getColumnSize();
	}

	public int getSqlType() {
		return cMetaData.getSqlType();
	}
	
	public String getSqlTypeName() {
		return cMetaData.getSqlTypeName();
	}
	
}
