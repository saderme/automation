package example.test.testbase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import example.test.reportfactory.ComplexReportFactory;

public class Test002P extends BaseTest {
	
	
	@AfterClass
	public void cleanUp() {
		System.out.println("\nTest002P -> This runs once after class");
	}
	
	@Test
	public void test003() {
		ExtentTest testReporter = ComplexReportFactory.getTest();
		testReporter.log(LogStatus.INFO, "Starting test test001");
		int a = 100;
		int b = 30;

		testReporter.log(LogStatus.INFO, "Loading the value of a to " + a);
		testReporter.log(LogStatus.PASS, "Loading the value of b to " + b);
	}

	@Test
	public void test004() {
		ExtentTest testReporter = ComplexReportFactory.getTest();
		testReporter.log(LogStatus.INFO, "Starting test test002");
		int a = 100;
		int b = 30;

		testReporter.log(LogStatus.INFO, "Loading the value of a to " + a);
		testReporter.log(LogStatus.FAIL, "Loading the value of b to " + b);
	}

}
