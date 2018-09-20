package com.expedia.ui.pagefactory;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.expedia.test.testbase.TestBase;
import com.sader.automation.helpers.configReader.ObjectReader;
import com.sader.automation.helpers.configReader.PropertyReader;
import com.sader.automation.helpers.ui.AssertionHelper;
import com.sader.automation.helpers.ui.JavaScriptHelper;
import com.sader.automation.helpers.ui.VerificationHelper;
import com.sader.automation.helpers.ui.WaitHelper;
import com.sader.automation.resources.ExtentManager;
import com.sader.automation.resources.LoggingFactory;
import com.sader.automation.resources.WebDriverFactory;

public class LoginPageFactory{
	
	private WebDriver driver;
	private final Logger log = LoggingFactory.getLogger(LoginPageFactory.class);
	WaitHelper waitHelper;
	
	@FindBy(xpath="//button[@id=\'header-account-menu\']")
	WebElement accountlink;
	
	@FindBy(xpath="//button[@id=\'header-account-signin-button\']")
	WebElement expandedsignin;
	
	@FindBy(xpath="//input[@id='signin-loginid']")
	WebElement emailAddress;

	@FindBy(xpath="//input[@id='signin-password']")
	WebElement password;
	
	@FindBy(xpath="//button[@id='submitButton']")
	WebElement signin;
	
	@FindBy(id="header-account-menu-signed-in")
	WebElement successMsgObject;
	
/*	@FindBy(xpath="//*[@id='email_create']")
	WebElement registrationEmailAddress;
	
	@FindBy(xpath="//*[@id='SubmitCreate']")
	WebElement createAnAccount;
	
	@FindBy(xpath="//*[@id='center_column']/h1")
	WebElement authenticate;
	
	@FindBy(xpath="//*[@id='create-account_form']/div/p")
	WebElement createAnAccountMessage;*/
	
	@FindBy(xpath="//*[@id='header']/div[2]/div/div/nav/div[2]/a")
	WebElement logout;

	public LoginPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(accountlink,ObjectReader.reader.getExplicitWait());
		//new TestBase().captureNavigationScreen(driver);
		logExtentReport("Login Page Object Created");
	}

	public void clickOnAccountLink(){
		accountlink.click();		
		log.info("clicked on account link...");
		logExtentReport("clicked on account link...");

	}
	
	public void clickOnExpandedSignInLink(){
		expandedsignin.click();	
		log.info("clicked on expanded sign in link...");
		logExtentReport("clicked on expanded sign in link...");
	}
	
	public void enterEmailAddress(String emailAddress){
		this.emailAddress.sendKeys(emailAddress);	
		log.info("entering email address...." + emailAddress);
		logExtentReport("entering email address...." + emailAddress);
	}
	
	public void enterPassword(String password){
		this.password.sendKeys(password);	
		log.info("entering password...." + password);
		logExtentReport("entering password...." + password);
	}
	
	public void clickOnSignInLink(){
		signin.click();		
		log.info("clicked on sign in link...");
		logExtentReport("clicked on sign in link...");
	}
	
	public boolean verifySuccessLoginMsg(){
		return new VerificationHelper(driver).isDisplayed(successMsgObject);
	}
	
	public void loginToApplication(String emailAddress, String password){
		clickOnAccountLink();
		clickOnExpandedSignInLink();
		enterEmailAddress(emailAddress);
		enterPassword(password);
		clickOnSubmitButton();
	}
	
	public NavigationMenu clickOnSubmitButton(){
		log.info("clicking on submit button...");
		logExtentReport("clicking on submit button...");
		JavaScriptHelper javaScriptHelper = new JavaScriptHelper(driver);
		javaScriptHelper.scrollDownVertically();
		//new JavaScriptHelper(driver).scrollDownVertically();
		signin.click();
		return new NavigationMenu(driver);
	}
	
	/*	public void enterRegistrationEmail(){
		String email = System.currentTimeMillis()+"@gmail.com";
		log.info("entering registration email.."+email);
		registrationEmailAddress.sendKeys(email);	
	}
	
	public RegistrationPage clickOnCreateAnAccount(){
		createAnAccount.click();
		return new RegistrationPage(driver);
	}*/
	
	public void logout(){
		logout.click();
		log.info("clicked on logout link");
		waitHelper.waitForElement(signin, ObjectReader.reader.getExplicitWait());
	}
	
	public void logExtentReport(String s1){
		TestBase.test.log(Status.INFO, s1);
	}

	public static void main(String[] args) {

		TestBase tb = new TestBase();
		Logger log = (Logger) LoggingFactory.getLogger(TestBase.class);
		
		tb.extent = ExtentManager.getInstance();
		
		ObjectReader.reader = new PropertyReader();
//		File reportDirectory = new File(ResourceHelper.getResourcePath("src\\main\\resources\\screenShots"));
		
		try {
			tb.driver = new WebDriverFactory().getDriver(ObjectReader.reader.getBrowserType());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tb.test = tb.extent.createTest(tb.getClass().getSimpleName());
		tb.getApplicationUrl("http:\\www.expedia.co.uk");

		LoginPageFactory lf = new LoginPageFactory(tb.driver);
		lf.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
			
		boolean status = lf.verifySuccessLoginMsg();
		AssertionHelper.updateTestStatus(status);
		
	}
	
}
