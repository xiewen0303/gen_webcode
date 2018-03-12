package com.codegen.mapper;

/**
 * @description 表字段类型映射规则
 * @author ShiJie Chi
 * @created 2010-4-22 下午10:24:12	
 */
public interface IColumnTypeMapRule {
	
	/**
	 * @description 输出映射的java类型
	 * @param {@link java.sql.Types}
	 * @return java类型
	 */
	public Class convert(int sqlType);
	
}
