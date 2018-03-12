package com.codegen.context;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

/**
 * @description implement(recommend property inject)
 * @author ShiJie Chi
 * @created 2010-4-22 下午08:43:13	
 */
public class CodeGenContext implements ICodeGenContext {

	private List<DataSource> dataSources;
	
	private String basePackage;

	private String templatePath;
	
	private String printPath;
	
	private String modelName;
	
	private List<String> tablesToGen;
	
	public void setDataSources(List<DataSource> dataSources) {
		this.dataSources = dataSources;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}
	
	public void setPrintPath(String printPath) {
		this.printPath = printPath;
	}

	public String getBasePackage() {
		return basePackage;
	}
	
	public List<DataSource> getDataSources() {
		return dataSources;
	}
	
	public String getPrintPath() {
		return printPath;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public String getModelName() {
		if(null == modelName){
			return "";
		}
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public List<String> getTablesToGen() {
		if(null == tablesToGen){
			return new ArrayList<String>();
		}
		return tablesToGen;
	}

	public void setTablesToGen(List<String> tablesToGen) {
		this.tablesToGen = tablesToGen;
	}
	
}
