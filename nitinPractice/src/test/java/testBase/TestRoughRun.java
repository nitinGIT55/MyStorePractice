package testBase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import applicationPages.accountCreationPage;
import applicationPages.landing_nd_signupPage;

public class TestRoughRun {

	String baseurl="http://automationpractice.com/index.php";
	static landing_nd_signupPage onLandingPage;
	static accountCreationPage onAccountCreationPage; 
	WebDriver driver;
	
	@Test (priority =0)
	public void browserSetup(){
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(baseurl);
		onLandingPage=PageFactory.initElements(driver, landing_nd_signupPage.class);
		onAccountCreationPage=PageFactory.initElements(driver, accountCreationPage.class);
		String title=driver.getTitle();
		Assert.assertEquals("My Store", title);
		System.out.println("Test Case 1:Launched the browser and landed on base page successfully ");
		}
	
	
	@Test (priority=1)
	public void clickSingIn() {
		String title1=onLandingPage.clickOnSignIn();
		Assert.assertEquals("Login - My Store", title1);
		System.out.println("Test Case 2:Clicked on the SignIn button successfully and next page title is "+title1);
		
	}
	
	@Test (priority=2)
		public void enterEmailAddressandClickCreateAccount() {
		Boolean x=onLandingPage.enterEmail("nitin.shukla5@gmail.com");
		Boolean y=onLandingPage.clickCreateButton();
		Boolean z=x&&y;
		Assert.assertTrue(z);
		System.out.println("Test Case 3:Entered my email address and clicked create account successfully");
	}
	
	@Test (priority=3, dependsOnMethods= {"enterEmailAddressandClickCreateAccount"} )
	public void enterDataInYourInformationSection() throws Exception {
		onAccountCreationPage.enterYourInformation();
		onAccountCreationPage.enterYourAddress();
	}

}
