package com.sader.automation.helpers.configReader;

import com.sader.automation.helpers.browserConfiguration.BrowserType;
/**
 * 
 * @author Bhanu Pratap Singh
 *
 */
public interface ConfigReader {
	
	public int getImpliciteWait();
	public int getExplicitWait();
	public int getPageLoadTime();
	public BrowserType getBrowserType();
	public String getUrl();
	public String getUserName();
	public String getPassword();

}
