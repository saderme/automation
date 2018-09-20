package com.sader.automation.resources;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;

import com.sader.automation.helpers.browserConfiguration.BrowserType;
import com.sader.automation.helpers.browserConfiguration.ChromeBrowser;
import com.sader.automation.helpers.browserConfiguration.FirefoxBrowser;
import com.sader.automation.helpers.browserConfiguration.IExploreBrowser;
import com.sader.automation.helpers.configReader.ObjectReader;
import com.sader.automation.helpers.ui.WaitHelper;

public class WebDriverFactory {

	private static WebDriver driver;
	private static ChromeBrowser chrome;
	private static FirefoxBrowser firefox;
	private static IExploreBrowser ie;
	
	public WebDriverFactory() {

	}
	
	public static WebDriver getDriver(BrowserType btype) throws Exception {
		try {
			driver = getrelevantBrowserDriver(btype);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WaitHelper wait = new WaitHelper(driver);
		wait.setImplicitWait(ObjectReader.reader.getImpliciteWait(), TimeUnit.SECONDS);
		wait.pageLoadTime(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);
		driver.manage().window().maximize();		
		
		return driver;
	}
	
	public static WebDriver getrelevantBrowserDriver(BrowserType btype) throws Exception {

		try {
			switch (btype) {
			case Chrome:
				// get object of ChromeBrowser class
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				ChromeOptions option = chrome.getChromeOptions();
				return chrome.getChromeDriver(option);
			case Firefox:
				FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
				FirefoxOptions options = firefox.getFirefoxOptions();
				return firefox.getFirefoxDriver(options);

			case Iexplorer:
				IExploreBrowser ie = IExploreBrowser.class.newInstance();
				InternetExplorerOptions cap = ie.getIExplorerCapabilities();
				return ie.getIExplorerDriver(cap);
			default:
				throw new Exception("Driver not Found: " + btype.name());
			}
		} catch (Exception e) {
			//log.info(e.getMessage());
			throw e;
		}
	}

	public static void main(String[] args) {
		
		try {
			chrome = ChromeBrowser.class.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ChromeOptions option = chrome.getChromeOptions();
		WebDriver driver =  chrome.getChromeDriver(option);
		
		driver.get("http://www.expedia.co.uk");
	}
	

}
