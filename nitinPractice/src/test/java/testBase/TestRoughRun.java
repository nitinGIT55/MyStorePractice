package testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import applicationPages.accountCreationPage;
import applicationPages.landing_nd_signupPage;
import applicationPages.myStorePageAfterLogin;

public class TestRoughRun {

	String baseurl="http://automationpractice.com/index.php";
	static landing_nd_signupPage onLandingPage;
	static accountCreationPage onAccountCreationPage; 
	static myStorePageAfterLogin onMyStorePage;
	WebDriver driver;
	
	
	@BeforeMethod
	public void printBeforeMethod() {
		System.out.println("This test is from Before Method");
	}
	
	@AfterMethod
	public void printAfterMethod() {
		System.out.println("This test is from After Method");
	}
	
	@BeforeTest
	public void printBeforeTest() {
		System.out.println("This test is from Before TEST");
	}
	
	@AfterTest
	public void printAfterTest() {
		System.out.println("This test is from After TEST");
	}
	
	@BeforeSuite
	public void printBeforeSuite() {
		System.out.println("This test is from Before SUITE");
	}
	
	@AfterSuite
	public void printAfterSuite() {
		System.out.println("This test is from After SUITE");
	}
	
	
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
		onMyStorePage=PageFactory.initElements(driver, myStorePageAfterLogin.class);
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
		public void enterEmailAddressandClickCreateAccount() throws IOException {
		FileInputStream inputStream= new FileInputStream("./ExcelSheets/MyData.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook wrkBook= new XSSFWorkbook(inputStream);
		XSSFSheet mySheet1=wrkBook.getSheetAt(0);
		XSSFCell cellValueLogin;
		cellValueLogin= mySheet1.getRow(1).getCell(3);
		Boolean emailEntered=onLandingPage.enterEmail(cellValueLogin.toString());
		Boolean createButtonClicked=onLandingPage.clickCreateButton();
		Boolean success=emailEntered && createButtonClicked;
		Assert.assertTrue(success);
		System.out.println("Test Case 3:Entered my email address and clicked create account successfully");
	}
	
	@Test (priority=3, dependsOnMethods= {"enterEmailAddressandClickCreateAccount"} )
	public void enterDataInYourInformationSection() throws Exception {
		onAccountCreationPage.enterYourInformation();
		onAccountCreationPage.enterYourAddress();
		
	}

	
	@Test (priority=4)
	public void testingExcel() throws IOException {
		onAccountCreationPage.testingExcelReader();
	}


	@Test (priority=5)
	public void logoutAfterSignIn() throws IOException {
		Boolean resultPass= onMyStorePage.logoutFromMyStore();
		Assert.assertTrue(resultPass);
	}
	
	@Test (priority=6)
	public void loginWithNewAccount() throws IOException {
		onLandingPage.loginWithCredentials();
	}
	
	
	
}
