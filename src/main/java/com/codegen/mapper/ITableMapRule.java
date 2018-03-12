package com.codegen.mapper;

/**
 * @description 表名映射规则
 * @author ShiJie Chi
 * @created 2010-4-25 下午03:40:10	
 */
public interface ITableMapRule {

	/**
	 * @description 对应java类名
	 * @return
	 */
	public String convert(String tableName);
}
