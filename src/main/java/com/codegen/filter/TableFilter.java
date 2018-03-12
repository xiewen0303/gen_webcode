/**
 * 
 */
package com.codegen.filter;

import java.util.List;

/**
 * @description 表过滤
 * @author ShiJie Chi
 * @created 2010-5-21上午10:20:43
 */
public class TableFilter {
	
	public List<String> tablesToGen;
	
	public TableFilter(List<String> tablesToGen) {
		this.tablesToGen = tablesToGen;
	}
	
	/**
	 * 是否存在指定表
	 * @param tableName
	 * @return
	 */
	public boolean contain(String tableName){
		return tablesToGen.contains(tableName);
	}

}
