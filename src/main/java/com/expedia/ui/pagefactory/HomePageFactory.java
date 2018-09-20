package com.expedia.ui.pagefactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.expedia.test.testbase.TestBase;
import com.sader.automation.helpers.configReader.ObjectReader;
import com.sader.automation.helpers.ui.WaitHelper;
import com.sader.automation.resources.LoggingFactory;

public class HomePageFactory {
	
	private WebDriver driver;
	private final Logger log = LoggingFactory.getLogger(HomePageFactory.class);
	WaitHelper waitHelper;

	
	@FindBy(id="header-history")
	WebElement headerHistory;
	
	@FindBy(id="tab-flight-tab-hp")
	WebElement flightsTab;
	
	@FindBy(id="flight-origin-hp-flight")
	WebElement originCity;
	
	@FindBy(id="flight-destination-hp-flight")
	WebElement destinationCity;
	
	@FindBy(id="flight-departing-hp-flight")
	WebElement departureDate;
	
	@FindBy(id="flight-returning-hp-flight")
	WebElement returnDate;
	
	@FindBy(xpath="//form[@id='gcw-flights-form-hp-flight']//button[@type='submit']")
	WebElement searchbutton;
	
	public HomePageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(flightsTab,ObjectReader.reader.getExplicitWait());
		log.info("wait complete ...");

	}
	
	public void clickFlightsTab() {
		//headerHistory.click();
		flightsTab.click();
		log.info("clicked on flights tab ...");
		logExtentReport("clicked on flights tab ...");
	}
	
	public void setOriginCity(String origin) {
		originCity.sendKeys(origin);		
		log.info("input origin city ...");
		logExtentReport("input origin city ...");
	}
	
	public void setDestinationCity(String destination) {
		destinationCity.sendKeys(destination);		
		log.info("input destination city ...");
		logExtentReport("input destination city ...");
	}
	
	public void setDepartureDate(String departuredate) {
		departureDate.sendKeys(departuredate);		
		log.info("input departure date ...");
		logExtentReport("input departure date ...");
	}
	
	public void setReturnDate(String returndate) {
		returnDate.sendKeys(returndate);		
		log.info("input return date ...");
		logExtentReport("input return date ...");
	}
	
	public void clickSearchButton() {
		searchbutton.click();		
		log.info("clicked on search button...");
		logExtentReport("clicked on search button...");
	}
	
	public void logExtentReport(String s1){
		TestBase.test.log(Status.INFO, s1);
	}
	/**
	 * Clear all the fields on the Home page
	 */
	public void clearAllFields() {
		driver.findElement(By.id("flight-origin-hp-flight")).clear();
		driver.findElement(By.id("flight-destination-hp-flight")).clear();
		driver.findElement(By.id("flight-departing-hp-flight")).clear();
		driver.findElement(By.id("flight-returning-hp-flight")).clear();
		driver.findElement(By.id("flight-returning-hp-flight")).sendKeys(Keys.TAB);
		log.info("all fields cleared...");
		logExtentReport("all fields cleared...");
	}
	
}