package com.expedia.test.testscripts.homepagetests;

import org.apache.logging.log4j.Logger;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.expedia.test.testbase.TestBase;
import com.expedia.ui.pagefactory.HomePageFactory;
import com.sader.automation.helpers.configReader.ObjectReader;
import com.sader.automation.resources.LoggingFactory;

public class TestNG_HomePage_TestSuite extends TestBase  {

	private final Logger log = LoggingFactory.getLogger(TestNG_HomePage_TestSuite.class);

	@Test(description="Fill Basic Info and Search Test")
	public void fillBasicInfo() throws Exception {
		
		getApplicationUrl(ObjectReader.reader.getUrl());
		System.out.println("\nTestNG_TestSuite -> test fillBasicInfo1");
		HomePageFactory homePage = new HomePageFactory(driver);
		
		System.out.println("\nTestNG_TestSuite -> test fillBasicInfo2");
		homePage.clickFlightsTab();
		homePage.setOriginCity("New York");
		homePage.setDestinationCity("San Francisco");
		homePage.setDepartureDate("10/28/2015");
		homePage.setReturnDate("10/31/2015");
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
