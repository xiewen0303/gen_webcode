package com.codegen.mapper;

/**
 * @description 表字段映射规则
 * @author ShiJie Chi
 * @created 2010-4-22 下午10:21:36	
 */
public interface IColumnMapRule {
	
	/**
	 * @description 输出映射变量名
	 * @return 映射变量名
	 */
	public String convert(String column);
	
}
