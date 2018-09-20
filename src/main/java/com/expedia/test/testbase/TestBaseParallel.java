package com.expedia.test.testbase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.sader.automation.helpers.browserConfiguration.BrowserType;
import com.sader.automation.helpers.configReader.ObjectReader;
import com.sader.automation.helpers.configReader.PropertyReader;
import com.sader.automation.helpers.ui.ExcelHelper;
import com.sader.automation.helpers.ui.JavaScriptHelper;
import com.sader.automation.helpers.ui.ResourceHelper;
import com.sader.automation.resources.ExtentManager;
import com.sader.automation.resources.LoggingFactory;
import com.sader.automation.resources.ScreenCaptureFactory;
import com.sader.automation.resources.WebDriverFactory;

public class TestBaseParallel {
	
	public static ExtentReports extent;
	//protected ExtentTest test;
	public static WebDriverFactory wdf;
	public static ScreenCaptureFactory scf;
	
	public WebDriver driver;
	protected static Logger log = (Logger) LoggingFactory.getLogger(TestBaseParallel.class);
	public static File reportDirectory;
	protected String screengrab;	
	@BeforeSuite
	public void beforeSuite() throws Exception{
		extent = ExtentManager.getInstance();
	}
	
	
	@AfterTest
	public void afterTest() throws Exception{
		if(driver!=null){
			driver.quit();
		}
	}
	
	public Object[][] getExcelData(String excelName, String sheetName){
		String excelLocation = ResourceHelper.getResourcePath("src\\main/resoures\\configfile")+excelName;
		log.info("excel location "+excelLocation);
		ExcelHelper excelHelper = new ExcelHelper();
		Object[][] data = excelHelper.getExcelData(excelLocation, sheetName);
		return data;
	}
}
