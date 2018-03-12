package com.codegen.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.codegen.filter.TableFilter;

/**
 * @description 数据库元数据
 * @author ShiJie Chi
 * @created 2010-4-22 下午08:24:59	
 */
public class DBMetaData {

	private DatabaseMetaData dbMetaData;
	
	private List<TableMetaData> tableMetaDatas = new ArrayList<TableMetaData>();
	
	private TableFilter tableFilter;
	
	public DBMetaData(DataSource dataSource,TableFilter tableFilter) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			this.dbMetaData = conn.getMetaData();
			this.tableFilter = tableFilter;
			
			initialize();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(null != conn){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void initialize() throws SQLException{
		
		//get tableMetaDatas
		ResultSet tableRs = dbMetaData.getTables(null, null, "%", new String[]{"TABLE"});
		while(tableRs.next()){
			String tableName = tableRs.getString("TABLE_NAME");
			
			if(tableFilter.contain(tableName)){
				String tableCat = tableRs.getString("TABLE_CAT");
				TableMetaData tMetaData = new TableMetaData(dbMetaData,tableCat,tableName);
				
				tableMetaDatas.add(tMetaData);
			}
		}
		
	}

	public List<TableMetaData> getTableMetaDatas() {
		return tableMetaDatas;
	}
	
}
