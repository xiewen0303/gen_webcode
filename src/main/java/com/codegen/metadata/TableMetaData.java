package com.codegen.metadata;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description 库表元数据
 * @author ShiJie Chi
 * @created 2010-4-22 下午08:26:06	
 */
public class TableMetaData {
	
	private DatabaseMetaData dbMetaData;
	
	private String tableName;
	
	private String catagory;
	
	private List<ColumnMetaData> primaryColumns = new ArrayList<ColumnMetaData>();
	
	private List<ColumnMetaData> otherColumns = new ArrayList<ColumnMetaData>();
	
	public TableMetaData(DatabaseMetaData dbMetaData,String catagory,String tableName) {
		this.dbMetaData = dbMetaData;
		this.tableName = tableName;
		this.catagory = catagory;
		try {
			initialize();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void initialize() throws SQLException {
		
		//get primarykeys
		List<String> primaryKeys = new ArrayList<String>();
		ResultSet primaryColumnRs = dbMetaData.getPrimaryKeys(null, null, tableName);
		while(primaryColumnRs.next()){
			String cName = primaryColumnRs.getString("COLUMN_NAME");
			primaryKeys.add(cName);
		}
		
		//build all columns
		ResultSet columnRs = dbMetaData.getColumns(null, null, tableName, "%");
		while(columnRs.next()){
			String column = columnRs.getString("COLUMN_NAME");
			int sqlType = columnRs.getInt("DATA_TYPE");
			String sqlTypeName = columnRs.getString("TYPE_NAME");
			int columnSize = columnRs.getInt("COLUMN_SIZE");
			ColumnMetaData cMetaData = new ColumnMetaData(column,columnSize, sqlType, sqlTypeName);
			
			if(primaryKeys.contains(column)){
				primaryColumns.add(cMetaData);
			}else{
				otherColumns.add(cMetaData);
			}
		}
		
	}

	public List<ColumnMetaData> getPrimaryColumns() {
		return primaryColumns;
	}

	public List<ColumnMetaData> getOtherColumns() {
		return otherColumns;
	}

	public String getTableName() {
		return tableName;
	}

	public String getCatagory() {
		return catagory;
	}
	
	
}
