package com.codegen.mapper;

/**
 * @description column to beanProperty mapper
 * @author ShiJie Chi
 * @created 2010-4-23 下午04:49:37	
 */
public class BeanPropertyColumnMapRule implements IColumnMapRule {

	private static final String UNDERLINE = "_";
	
	public String convert(String column) {
		StringBuffer buffer = new StringBuffer();
		
		if(null != column && column.length() > 1){
			int columnLength = column.length();
			
			String firstChar = column.substring(0, 1);
			buffer.append(firstChar.toLowerCase());
			
			if(columnLength > 1){
				String preChar = firstChar;
				for(int i = 1 ; i < columnLength ; i++){
					String curChar = column.substring(i,i+1);
					if(preChar.equals(UNDERLINE)){
						curChar = curChar.toUpperCase();
					}else{
						curChar = curChar.toLowerCase();
					}
					if(!curChar.equals(UNDERLINE))
						buffer.append(curChar);
					
					preChar = curChar;
				}
				
			}
			
			return buffer.toString();
		}
		
		return null;
	}
	
}
