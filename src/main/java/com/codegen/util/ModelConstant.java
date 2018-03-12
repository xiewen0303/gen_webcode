package com.codegen.util;

public class ModelConstant {
	
	private static final String MODEL_NAME = "xl";
	
	
	private static final String MODEL_NAME_UP = "Xl";
	
	
	public static String convertEntityName(String entityName, String modelName){
		if( MODEL_NAME.equals(modelName) ){
			return MODEL_NAME_UP + entityName;
		}
		return entityName;
	}
}
