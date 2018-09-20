package com.sader.automation.resources;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.sader.automation.helpers.ui.JavaScriptHelper;

public class ScreenCaptureFactory {
	
	public ScreenCaptureFactory() {

	}
	
	public static String captureScreen(String fileName, WebDriver driver, File reportDirectory, Logger log){
		if(driver == null){
			log.info("driver is null..");
			return null;
		}
		if(fileName==""){
			fileName = "blank";
		}
		Reporter.log("captureScreen method called");
		log.info("Calling captureScreen...");
		Wait(2000);
		
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File screFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{
			destFile = new File(reportDirectory+"/"+fileName+"_"+formater.format(calendar.getTime())+".png");
			Files.copy(screFile.toPath(), destFile.toPath());
			Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><img src='"+destFile.getAbsolutePath()+"'height='100' width='100'/></a>");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return destFile.toString();
	}	

	private static void Wait(int i) {
		// TODO Auto-generated method stub
		
	}

	public static String captureNavigationScreen(String fileName, WebDriver driver,File reportDirectory, Logger log) {
		log.info("Calling captureScreenNavigation...");
		new JavaScriptHelper(driver).zoomInBy60Percentage();
		 String screen = ScreenCaptureFactory.captureScreen("", driver, reportDirectory, log);
		 new JavaScriptHelper(driver).zoomInBy100Percentage();
		 return screen;
	}
	

}
