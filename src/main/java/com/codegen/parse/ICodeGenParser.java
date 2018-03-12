package com.codegen.parse;

import java.io.File;
import java.util.Map;

/**
 * @description 解析器
 * @author ShiJie Chi
 * @created 2010-4-22 下午10:59:29	
 */
public interface ICodeGenParser {

	/**
	 * 
	 * @param context
	 * @param params
	 * @param templateName
	 * @param printDir
	 * @param fileName
	 * @return
	 */
	public boolean parse(Map<Object, Object> params,String templateName,File printDir,String fileName);
	
	
}
