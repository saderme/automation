package com.sader.automation.resources;

import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sader.automation.helpers.ui.ResourceHelper;

public class LoggingFactory {

	private static boolean root=false;
	
	public static Logger getLogger(Class cls){
		if(root){
			return LogManager.getLogger(cls);
		}
		PropertyConfigurator.configure(ResourceHelper.getResourcePath("src\\main\\resources\\configfile\\log4j.properties"));
		root = true;
		return LogManager.getLogger(cls);
	}
	
	public static void main(String[] args) {
		Logger log = LoggingFactory.getLogger(LoggingFactory.class);
		log.info("logger is configured");
		log.info("logger is configured");
		log.info("logger is configured");
		
	}
}
