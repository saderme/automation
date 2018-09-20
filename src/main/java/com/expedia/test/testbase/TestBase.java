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

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.sader.automation.helpers.configReader.ObjectReader;
import com.sader.automation.helpers.configReader.PropertyReader;
import com.sader.automation.helpers.ui.ExcelHelper;
import com.sader.automation.helpers.ui.JavaScriptHelper;
import com.sader.automation.helpers.ui.ResourceHelper;
import com.sader.automation.resources.ExtentManager;
import com.sader.automation.resources.LoggingFactory;
import com.sader.automation.resources.ScreenCaptureFactory;
import com.sader.automation.resources.WebDriverFactory;

public class TestBase {
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public WebDriverFactory wdf;
	public ScreenCaptureFactory scf;
	
	public WebDriver driver;
	protected static Logger log = (Logger) LoggingFactory.getLogger(TestBase.class);
	public static File reportDirectory;
	protected String screengrab;
	
	@BeforeSuite
	public void beforeSuite() throws Exception{
		extent = ExtentManager.getInstance();
	}
	
	@BeforeTest
	public void beforeTest() throws Exception{
		ObjectReader.reader = new PropertyReader();
		reportDirectory = new File(ResourceHelper.getResourcePath("src\\main\\resources\\screenShots"));
		driver = new WebDriverFactory().getDriver(ObjectReader.reader.getBrowserType());
		test = extent.createTest(getClass().getSimpleName());
	}
	
	@BeforeMethod
	public void beforeMethod(Method method){
		test.log(Status.INFO, method.getName()+"**************test started***************");
		log.info("**************"+method.getName()+"Started***************");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException{
		if(result.getStatus() == ITestResult.FAILURE){
			test.log(Status.FAIL, result.getThrowable());
			String imagePath = ScreenCaptureFactory.captureScreen(result.getName(),driver,reportDirectory,log);
			test.addScreenCaptureFromPath(imagePath);
		}
		else if(result.getStatus() == ITestResult.SUCCESS){
			test.log(Status.PASS, result.getName()+" is pass");
			String imagePath = ScreenCaptureFactory.captureScreen(result.getName(),driver,reportDirectory,log);
			test.addScreenCaptureFromPath(imagePath);
		}
		else if(result.getStatus() == ITestResult.SKIP){
			test.log(Status.SKIP, result.getThrowable());
		}
		log.info("**************"+result.getName()+"Finished***************");
		extent.flush();
	}
	
	@AfterTest
	public void afterTest() throws Exception{
		if(driver!=null){
			driver.quit();
		}
	}
		
	public static void logExtentReport(String s1){
		test.log(Status.INFO, s1);
	}
	
	public void getApplicationUrl(String url){
		driver.get(url);
		logExtentReport("navigating to ..."+url);
	}
	
	public Object[][] getExcelData(String excelName, String sheetName){
		String excelLocation = ResourceHelper.getResourcePath("src\\main/resoures\\configfile")+excelName;
		log.info("excel location "+excelLocation);
		ExcelHelper excelHelper = new ExcelHelper();
		Object[][] data = excelHelper.getExcelData(excelLocation, sheetName);
		return data;
	}
}
