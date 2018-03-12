package com.codegen.context;

import java.util.List;

import javax.sql.DataSource;


/**
 * @description codegen context interface
 * @author ShiJie Chi
 * @created 2010-4-22 下午08:36:50	
 */
public interface ICodeGenContext {

	/**
	 * @description 获取所有数据源
	 * @return
	 */
	public List<DataSource> getDataSources();
	
	/**
	 * @description 获取生成类文件基本包名
	 * @return
	 */
	public String getBasePackage();
	
	/**
	 * @description 获取生成文件所在根目录
	 * @return
	 */
	public String getPrintPath();
	
	/**
	 * 获取模板类目录
	 * @return
	 */
	public String getTemplatePath();
	
	/**
	 * 获取模块名
	 * @return
	 */
	public String getModelName();
	
	/**
	 * 获取自定的要生成的表
	 * @return
	 */
	public List<String> getTablesToGen();
}
