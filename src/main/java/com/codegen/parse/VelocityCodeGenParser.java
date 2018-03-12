package com.codegen.parse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 * @description velocity parser
 * @author ShiJie Chi
 * @created 2010-4-23 下午02:44:32	
 */
public class VelocityCodeGenParser implements ICodeGenParser {

//	private static Velocity velocity = new Velocity();
	
	static{
		try {
			Properties p = new Properties();
			p.load(VelocityCodeGenParser.class.getClassLoader().getResourceAsStream("velocity.properties"));
			Velocity.init(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean parse(Map<Object, Object> params,
			String templateName, File printDir, String fileName) {
		VelocityContext vContext = new VelocityContext();
		if(null != params){
			for(Iterator<Object> iter = params.keySet().iterator();iter.hasNext();){
				Object key = iter.next();
				vContext.put((String)key, params.get(key));
			}
		}
        try {
			Template template = Velocity.getTemplate(templateName);
		    
			File file = new File(printDir, fileName);
			PrintWriter writer = new PrintWriter(new FileOutputStream(file));
			
			template.merge(vContext, writer);
			writer.flush();
			writer.close();
			
			return true;
		    
        } catch (ResourceNotFoundException e) {
			e.printStackTrace();
		} catch (ParseErrorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
