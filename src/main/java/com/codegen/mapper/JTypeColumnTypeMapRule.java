package com.codegen.mapper;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * @description 表字段类型到java类型映射
 * @author ShiJie Chi
 * @created 2010-4-25 下午05:39:27	
 */
public class JTypeColumnTypeMapRule implements IColumnTypeMapRule {

	public Class convert(int sqlType) {
		return MapTable.getMapClass(sqlType);
	}
	
	public static class MapTable{
		
		private static Map<Integer,Class> mapTable = new HashMap<Integer, Class>();
		
		private static final Class INTEGER_SQLTYPE = Integer.class;
		private static final Class TINYINT_SQLTYPE = Integer.class;
		private static final Class BIGINT_SQLTYPE = Long.class;
		
		private static final Class FLOAT_SQLTYPE = Float.class;
		
		private static final Class DECIMAL_SQLTYPE = Double.class;
		private static final Class DOUBLE_SQLTYPE = Double.class;
		
		private static final Class CHAR_SQLTYPE = String.class;
		private static final Class NVARCHAR_SQLTYPE = String.class;
		private static final Class VARCHAR_SQLTYPE = String.class;
		private static final Class LONGVARCHAR_SQLTYPE = String.class;
		
		private static final Class DATE_SQLTYPE = java.sql.Date.class;
		private static final Class TIME_SQLTYPE = java.sql.Timestamp.class;
		private static final Class TIMESTAMP_SQLTYPE = java.sql.Timestamp.class;
		
		static {
			mapTable.put(Types.CHAR, CHAR_SQLTYPE);
			mapTable.put(Types.DATE, TIME_SQLTYPE);
			mapTable.put(Types.DECIMAL, DECIMAL_SQLTYPE);
			mapTable.put(Types.DOUBLE, DOUBLE_SQLTYPE);
			mapTable.put(Types.FLOAT, FLOAT_SQLTYPE);
			mapTable.put(Types.INTEGER, INTEGER_SQLTYPE);
			mapTable.put(Types.NVARCHAR, NVARCHAR_SQLTYPE);
			mapTable.put(Types.TIME, TIME_SQLTYPE);
			mapTable.put(Types.TIMESTAMP, TIMESTAMP_SQLTYPE);
			mapTable.put(Types.TINYINT, TINYINT_SQLTYPE);
			mapTable.put(Types.VARCHAR, VARCHAR_SQLTYPE);
			mapTable.put(Types.BIGINT, BIGINT_SQLTYPE);
			mapTable.put(Types.LONGVARCHAR, LONGVARCHAR_SQLTYPE);
			mapTable.put(Types.REAL, FLOAT_SQLTYPE);
		}
		
		public static Class getMapClass(int sqlType){
			return mapTable.get(sqlType);
		}
		
		
	}
}


