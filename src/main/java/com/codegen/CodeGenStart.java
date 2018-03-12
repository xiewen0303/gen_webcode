package com.codegen;

import com.codegen.parse.VelocityCodeGenParser;
import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.codegen.context.ICodeGenContext;
import com.codegen.filegenerator.*;

import java.io.IOException;
import java.util.Properties;


public class CodeGenStart {

		public static void main(String[] args) {
//			initProperties();
			ApplicationContext appContext = new ClassPathXmlApplicationContext("codegen/applicationContext.xml");

			ICodeGenContext codeGenContext =  (ICodeGenContext)appContext.getBean("codegenContext");

			FileGeneratorControl control = new FileGeneratorControl();
			control.addGenerator(new EntityFileGenerator(codeGenContext));
			control.addGenerator(new DaoFileGenerator(codeGenContext));
			control.addGenerator(new DaoImplFileGenerator(codeGenContext));
			control.addGenerator(new ServiceFileGenerator(codeGenContext));
			control.addGenerator(new ServiceImplFileGenerator(codeGenContext));
			control.addGenerator(new SqlmapFileGenerator(codeGenContext));
			control.addGenerator(new VoFileGenerator(codeGenContext));
			control.addGenerator(new ControllerFileGenerator(codeGenContext));
			control.addGenerator(new JspFileGenerator(codeGenContext));
			control.addGenerator(new HsqldbFileGenerator(codeGenContext));

		control.startWork();
		System.out.println("code gen end...");
	}

//	static void initProperties(){
//		Properties p = new Properties();
//		p.put("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
//		Velocity.init(p);
//	}


//	public static void main(String[] args) {
//		Properties p = new Properties();
//		p.put("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
//		Velocity.init(p);
//
//		Template xmlTemplate = Velocity.getTemplate("codegen/template/controller.vm");
//		System.out.println(xmlTemplate);
//
//	}

}
