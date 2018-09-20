package com.expedia.test.testscripts.logintests;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.expedia.test.testbase.TestBase;
import com.expedia.ui.pagefactory.LoginPageFactory;
import com.sader.automation.helpers.configReader.ObjectReader;
import com.sader.automation.helpers.ui.AssertionHelper;
import com.sader.automation.resources.LoggingFactory;
import com.sader.automation.resources.ScreenCaptureFactory;

public class TestNG_LoginTests_TestSuite extends TestBase{
	//private final Logger log = LoggingFactory.getLogger(TestNG_LoginTests_TestSuite.class);
	
	@Test(description="Login test with valid credentials")
	public void testLoginToApplication(){
		getApplicationUrl(ObjectReader.reader.getUrl());
		
		LoginPageFactory loginPage = new LoginPageFactory(driver);
		
		screengrab = ScreenCaptureFactory.captureNavigationScreen("", driver, reportDirectory, log);
		 try {
			test.addScreenCaptureFromPath(screengrab);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		
		boolean status = loginPage.verifySuccessLoginMsg();
		
		AssertionHelper.updateTestStatus(status);
	}
}
