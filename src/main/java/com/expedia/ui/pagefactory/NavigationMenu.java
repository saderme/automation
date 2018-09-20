package com.expedia.ui.pagefactory;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.expedia.test.testbase.TestBase;
import com.sader.automation.helpers.configReader.ObjectReader;
import com.sader.automation.helpers.ui.WaitHelper;
import com.sader.automation.resources.LoggingFactory;


/**
 * 
 * @author Bhanu Pratap
 */
public class NavigationMenu {
	
	private WebDriver driver;
	private final Logger log = LoggingFactory.getLogger(NavigationMenu.class);
	WaitHelper waitHelper;
	
	@FindBy(xpath="//*[@id='block_top_menu']/ul/li[1]/a")
	public WebElement womenMenu;
	
	@FindBy(xpath="//*[@id='block_top_menu']/ul/li[2]/a")
	public WebElement dressesMenu;
	
	
	@FindBy(xpath="//*[@id='block_top_menu']/ul/li[3]/a")
	public WebElement tshirtsMenu;

	public NavigationMenu(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(womenMenu, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("NavigationMenu object created");
		//new TestBase().captureNavigationScreen(driver);
	}
	
	public void mouseOver(String data){
		log.info("doing mouse over on :"+data);
		TestBase.logExtentReport("doing mouse over on :"+data);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[contains(text(),'"+data+"')]"))).build().perform();
	}
	
	public ProductCategoryPage clickOnIntem(String data){
		log.info("clickin on :"+data);
		TestBase.logExtentReport("clickin on :"+data);
		driver.findElement(By.xpath("//*[contains(text(),'"+data+"')]")).click();
		return new ProductCategoryPage(driver);
	}
	
	public ProductCategoryPage clickOnMenu(WebElement element){
		log.info("clickin on : "+element.getText());
		TestBase.logExtentReport("clickin on : "+element.getText());
		element.click();
		return new ProductCategoryPage(driver);
	}

}
