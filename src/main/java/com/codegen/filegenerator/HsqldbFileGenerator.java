package com.codegen.filegenerator;

import java.io.File;
import java.util.ArrayList;
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
import com.codegen.util.StringUtil;

public class HsqldbFileGenerator implements IFileGenerator {

	private static final String TEMPLATE_NAME = "hsqldb.vm";
	
	private static final String FILE_CHILD_DIR = "hsqldb";
	
	private ICodeGenContext codeGenContext;
	
	private String fullTemplateName;
	
	private File printDir;
	
	public HsqldbFileGenerator(ICodeGenContext context) {
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
			
			ICodeGenParser parser = new VelocityCodeGenParser();
			
			List<Map> list = new ArrayList<Map>();
			for(TableMetaData tMetaData : tableMetaDatas){
				
				Map<Object,Object> params = new HashMap<Object, Object>();
				params.put("StringUtil", new StringUtil());
				params.put("basePackage", codeGenContext.getBasePackage());
				params.put("entityName", tMapRule.convert(tMetaData.getTableName()));
				params.put("tMapperMetaData", new TableMapperMetaData(tMetaData));
				
				list.add(params);
			}
			
			Map params = new HashMap();
			params.put("params", list);
			
			parser.parse(params, fullTemplateName, printDir, "hsqldb.xml");
		}
	}

}
