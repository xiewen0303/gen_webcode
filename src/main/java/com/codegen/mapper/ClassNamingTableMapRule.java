package com.codegen.mapper;

/**
 * @description 按通常java类命名规则映射
 * @author ShiJie Chi
 * @created 2010-4-25 下午03:44:49	
 */
public class ClassNamingTableMapRule implements ITableMapRule {

	private static final String UNDERLINE = "_";
	
	public String convert(String tableName) {
		StringBuffer buffer = new StringBuffer();
		
		if(null != tableName && tableName.length() > 1){
			int columnLength = tableName.length();
			
			String firstChar = tableName.substring(0, 1);
			buffer.append(firstChar.toUpperCase());
			
			if(columnLength > 1){
				String preChar = firstChar;
				for(int i = 1 ; i < columnLength ; i++){
					String curChar = tableName.substring(i,i+1);
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
	
	public static void main(String[] args) {
		ITableMapRule rule = new ClassNamingTableMapRule();
//		IColumnMapRule rule = new BeanPropertyColumnMapRule();
		System.out.println(rule.convert("c_pw_user"));
	}

}
