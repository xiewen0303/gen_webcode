package com.codegen.metadata;


/**
 * @description 表字段元数据
 * @author ShiJie Chi
 * @created 2010-4-22 下午08:26:47	
 */
public class ColumnMetaData {
	
	private String column;
	
	private int columnSize;
	
	private int sqlType;
	
	private String sqlTypeName;
	
	/**
	 * @param column
	 * @param sqlType {@link java.sql.Types}
	 */
	public ColumnMetaData(String column,int columnSize,int sqlType,String sqlTypeName) {
		this.column = column;
		this.columnSize = columnSize;
		this.sqlType = sqlType;
		this.sqlTypeName = sqlTypeName;
	}

	public String getColumn() {
		return column;
	}

	public int getSqlType() {
		return sqlType;
	}

	public String getSqlTypeName() {
		return sqlTypeName;
	}

	public int getColumnSize() {
		return columnSize;
	}
	
}
