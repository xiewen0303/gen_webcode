package com.codegen.util;

public class StringUtil {
	
	/**
	 * @description 首字母大写
	 * @return
	 */
	public static String capitalize(String str){
		if(null != str && str.length() > 0){
			StringBuffer buffer = new StringBuffer();
			buffer.append(str.substring(0, 1).toUpperCase());
			if(str.length() > 1){
				buffer.append(str.substring(1, str.length()));
			}
			return buffer.toString();
		}
		return null;
	}

	/**
	 * 首字母小写
	 * @param str
	 * @return
	 */
	public static String firstToLower(String str){
		if(null != str && str.length() > 0){
			StringBuffer buffer = new StringBuffer();
			buffer.append(str.substring(0, 1).toLowerCase());
			if(str.length() > 1){
				buffer.append(str.substring(1, str.length()));
			}
			return buffer.toString();
		}
		return null;
	}
	
	public static boolean contain(String orginal,String subStr){
		if(null != orginal && orginal.contains(subStr)){
			return true;
		}
		return false;
	}

	
	public static void main(String[] args) {
		StringUtil util = new StringUtil();
		System.out.println(util.capitalize("haapy"));
	}
}
