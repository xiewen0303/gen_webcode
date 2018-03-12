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
import com.codegen.util.ModelConstant;
import com.codegen.util.StringUtil;

public class JspFileGenerator implements IFileGenerator {

	private static final String TEMPLATE_NAME1 = "jsplist.vm";
	private static final String TEMPLATE_NAME2 = "jspadd.vm";
	private static final String TEMPLATE_NAME3 = "jspupdate.vm";
	private static final String TEMPLATE_NAME4 = "jspinfo.vm";
	
	private ICodeGenContext codeGenContext;
	
	private String fullTemplateName1;
	private String fullTemplateName2;
	private String fullTemplateName3;
	private String fullTemplateName4;
	
	private File basePrintDir;
	
	public JspFileGenerator(ICodeGenContext context) {
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
		fullTemplateName1 = templatePath + TEMPLATE_NAME1;
		fullTemplateName2 = templatePath + TEMPLATE_NAME2;
		fullTemplateName3 = templatePath + TEMPLATE_NAME3;
		fullTemplateName4 = templatePath + TEMPLATE_NAME4;
		

		basePrintDir = new File(codeGenContext.getPrintPath());
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

				List<String> table = new ArrayList<String>();
				table.add("jsp");
				table.add(tMetaData.getTableName());
				File printDir = FileUtil.mkDir(basePrintDir, table);

				parser.parse(params, fullTemplateName1, printDir, getFileName("list"));
				parser.parse(params, fullTemplateName2, printDir, getFileName("add"));
				parser.parse(params, fullTemplateName3, printDir, getFileName("update"));
				parser.parse(params, fullTemplateName4, printDir, getFileName("info"));
				
			}
	}
}

	private String getFileName(String str){
		StringBuffer buffer = new StringBuffer();
		buffer.append(str)
			  .append(".jsp");
		return buffer.toString();
	}
	
}
