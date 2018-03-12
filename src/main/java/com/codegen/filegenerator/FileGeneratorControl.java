package com.codegen.filegenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @author ShiJie Chi
 * @created 2010-5-5 下午03:59:13	
 */
public class FileGeneratorControl {

	private List<IFileGenerator> generators;

	public void setGenerators(List<IFileGenerator> generators) {
		this.generators = generators;
	}
	
	public void addGenerator(IFileGenerator generator){
		if(null == generators)
			generators = new ArrayList<IFileGenerator>();
		generators.add(generator);
	}
	
	/**
	 * 开始执行生产
	 */
	public void startWork(){
		if(null != generators){
			for(IFileGenerator generator : generators){
				generator.generate();
			}
		}
	}
	
}
