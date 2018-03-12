package com.codegen.filegenerator;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.codegen.context.ICodeGenContext;
import com.codegen.filter.TableFilter;
import com.codegen.mapper.ClassNamingTableMapRule;
import com.codegen.mapper.ITableMapRule;
import com.codegen.metadata.DBMetaData;
import com.codegen.metadata.TableMapperMetaData;
import com.codegen.metadata.TableMetaData;
import com.codegen.parse.ICodeGenParser;
import com.codegen.parse.VelocityCodeGenParser;
import com.codegen.util.FileUtil;
import com.codegen.util.ModelConstant;
import com.codegen.util.StringUtil;

public class SqlmapFileGenerator implements IFileGenerator {
	private static final String TEMPLATE_NAME = "sqlmap.vm";
	
	private static final String FILE_CHILD_DIR = "sqlmap";
	
	private ICodeGenContext codeGenContext;
	
	private String fullTemplateName;
	
	private File printDir;
	
	public SqlmapFileGenerator(ICodeGenContext context) {
		this.codeGenContext = context;
		
		initialize();
	}
	
	private void initialize(){
		String templatePath = codeGenContext.getTemplatePath();
		if(null != templatePath && !"".equals(templatePath)){
			if(templatePath.lastIndexOf(FileUtil.DIR_SEPERATE) != templatePath.length() - 1){
				templatePath = templatePath + FileUtil.DIR_SEPERATE;
			}
		}
		fullTemplateName = templatePath + TEMPLATE_NAME;
		
		
		List<String> packageDirs = FileUtil.getDirs(codeGenContext.getBasePackage());
		packageDirs.add(FILE_CHILD_DIR);
		packageDirs.add(codeGenContext.getModelName());
		
		File basePrintDir = new File(codeGenContext.getPrintPath());
		printDir = FileUtil.mkDir(basePrintDir, packageDirs);
		
	}
	
	public void generate() {
		List<DataSource> dataSources = codeGenContext.getDataSources();
		for(DataSource dataSource : dataSources){
			DBMetaData dbMetaData = new DBMetaData(dataSource,new TableFilter(codeGenContext.getTablesToGen()));
			List<TableMetaData> tableMetaDatas = dbMetaData.getTableMetaDatas();

			ITableMapRule tMapRule = new ClassNamingTableMapRule();
			
			for(TableMetaData tMetaData : tableMetaDatas){
				
				String entityName = ModelConstant.convertEntityName(tMapRule.convert(tMetaData.getTableName()), codeGenContext.getModelName());
				
				Map<Object,Object> params = new HashMap<Object, Object>();
				params.put("modelName", codeGenContext.getModelName());
				params.put("StringUtil", new StringUtil());
				params.put("basePackage", codeGenContext.getBasePackage());
				params.put("entityName", entityName);
				params.put("tMapperMetaData", new TableMapperMetaData(tMetaData));
				
				ICodeGenParser parser = new VelocityCodeGenParser();
				
				parser.parse(params, fullTemplateName, printDir, getFileName(entityName));
				
			}
		}
	}
	
	private String getFileName(String entityName){
		StringBuffer buffer = new StringBuffer();
		buffer.append(entityName);
		buffer.append(".xml");
		return buffer.toString();
	}
}
