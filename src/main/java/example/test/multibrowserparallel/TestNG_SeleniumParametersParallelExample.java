package example.test.multibrowserparallel;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.expedia.test.testbase.TestBaseParallel;
import com.expedia.ui.pagefactory.HomePageFactory;
import com.sader.automation.helpers.browserConfiguration.BrowserType;
import com.sader.automation.helpers.configReader.ObjectReader;
import com.sader.automation.helpers.configReader.PropertyReader;
import com.sader.automation.helpers.ui.ResourceHelper;
import com.sader.automation.resources.LoggingFactory;
import com.sader.automation.resources.ScreenCaptureFactory;
import com.sader.automation.resources.WebDriverFactory;

public class TestNG_SeleniumParametersParallelExample extends TestBaseParallel{
	

	//private WebDriverFactory wdf;
	private final Logger log = LoggingFactory.getLogger(TestNG_SeleniumParametersParallelExample.class);
	HomePageFactory hp;
	private ExtentTest test;
	
	@BeforeClass
	@Parameters({"browser"})
	public void setUp(String browser) {
		
		log.info("TestSeleniumParallel BeforeClass " + browser);
		
/*		if (browser.equalsIgnoreCase("firefox")) {
			try {
				wdf.getDriver((BrowserType.Firefox));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (browser.equalsIgnoreCase("chrome")) {
			try {
				wdf.getDriver((BrowserType.Chrome));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
/*		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver","src\\main\\resources\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","src\\main\\resources\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseURL);
		*/
		//hp = new HomePageFactory(driver);
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
	
	public void logExtentReport(String s1){
		test.log(Status.INFO, s1);
	}
	
	public void getApplicationUrl(String url){
		driver.get(url);
		logExtentReport("navigating to ..."+url);
	}
	
	@BeforeTest
	@Parameters({"browser"})
	public void beforeTest(String browser) throws Exception{
		
		log.info("TestSeleniumParallel BeforeTest " + browser);
		
		ObjectReader.reader = new PropertyReader();
		reportDirectory = new File(ResourceHelper.getResourcePath("src\\main\\resources\\screenShots"));

		if (browser.equalsIgnoreCase("firefox")) {
			try {
				driver = WebDriverFactory.getDriver(BrowserType.Firefox);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (browser.equalsIgnoreCase("chrome")) {
			try {
				driver = WebDriverFactory.getDriver(BrowserType.Chrome);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
		test = extent.createTest(getClass().getSimpleName());
	}
	
	
	
	@Test
	public void testLogin() throws InterruptedException {

		getApplicationUrl(ObjectReader.reader.getUrl());
		hp = new HomePageFactory(driver);
		//TestBase.captureNavigationScreen(driver);
		screengrab = ScreenCaptureFactory.captureNavigationScreen("", driver, reportDirectory, log);
		 try {
			test.addScreenCaptureFromPath(screengrab);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logExtentReport("Home Page Object Created");
		
		
	//	hp.getLoginLink().click();
	//	hp.getEmailField().sendKeys("test@email.com");
	//	hp.getPwdField().sendKeys("abcabc");
	//	hp.getLoginButton().click();
	}
	
	@AfterClass
	public void cleanUp() {
	//	driver.quit();
	}
}
