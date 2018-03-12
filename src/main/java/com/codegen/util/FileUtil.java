package com.codegen.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	public static File mkDir(String dir){
		
		File file = new File(dir);
		if(!file.exists()){
			file.mkdirs();
		}
		return file;
		
	}
	
	/**
	 * 生成目录
	 * @param baseDir 基本目录
	 * @param dirs 级联目录
	 * @return
	 */
	public static File mkDir(File baseDir,List<String> dirs){
		if(!baseDir.exists()){
			baseDir.mkdirs();
		}
		
		StringBuffer buffer = new StringBuffer();
		if(null != dirs && dirs.size() > 0){
			for(String dir : dirs){
				dir = dir.replace(DIR_SEPERATE, "");
				buffer.append(dir);
				buffer.append(DIR_SEPERATE);
			}
		}
		
		File result = new File(baseDir, buffer.toString());
		if(!result.exists()){
			result.mkdirs();
		}
		
		return result;
	}
	
	/**
	 * 根据包名获取级联目录
	 * @param packageName
	 * @return
	 */
	public static List<String> getDirs(String packageName){
		List<String> result = new ArrayList<String>();
		
		String[] dirs = packageName.split("\\" + PACKAGE_SEPERATE);
		for(String dir : dirs){
			result.add(dir);
		}
		return result;
	}
	
	
	public final static String DIR_SEPERATE = "/";
	
	public final static String PACKAGE_SEPERATE = ".";
	
}
