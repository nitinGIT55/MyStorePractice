package applicationPages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.testng.Reporter;

public class landing_nd_signupPage {
	
	WebDriver driver;
	String failed;
	
	
	
	//Objects on the Landing Page 
	@FindBy(css="a.login") public WebElement signinbutton;
	
	//Objects on the Landing page for Sign Up Scenarios
	@FindBy(css="input#email_create") public WebElement emailAddress;
	@FindBy(css="button#SubmitCreate") public WebElement createAccountButton;
	
	
	//Objects on the landing page for the Login scenarios
	@FindBy(css="input#email") public WebElement enterEmailId;
	@FindBy(css="input#passwd") public WebElement enterPassword;
	@FindBy(xpath="//a[starts-with(@title,'recover')]")public WebElement forgotPasswordLink;
	@FindBy(css="button#SubmitLogin") public WebElement signInButton;
	
	
	
	//Default constructor for this page object
	public landing_nd_signupPage(WebDriver page1driver){
		this.driver =page1driver;
	}
	
	public Boolean loginWithCredentials() throws IOException {
		signinbutton.click();
		Boolean userHasLoggedIn;
		FileInputStream inputStream= new FileInputStream("./ExcelSheets/MyData.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook wrkBook= new XSSFWorkbook(inputStream);
		XSSFSheet mySheet1=wrkBook.getSheetAt(0);
		XSSFCell cellValueLogin;
		cellValueLogin= mySheet1.getRow(1).getCell(3);
		enterEmailId.sendKeys(cellValueLogin.toString());
		cellValueLogin= mySheet1.getRow(1).getCell(4);
		enterPassword.sendKeys(cellValueLogin.toString());
		signInButton.click();
		String title=driver.getTitle();
		userHasLoggedIn=title.contains("Store");
		return userHasLoggedIn;
		
	}
		
	
	public String clickOnSignIn() {
		Boolean signInButtonIsEnabled=signinbutton.isEnabled();
		if(signInButtonIsEnabled)
		{
		signinbutton.click();
		String signInPageTitle= driver.getTitle();
		System.out.println("The page title of sign in page is "+signInPageTitle);
		Reporter.log("Clicked on the Signin button to print the page title of next page", true);
		return signInPageTitle;
		}
		else {
			System.out.println("The value for SignIn Enabled is "+signInButtonIsEnabled);
			return failed;
		}
	}
	
	public Boolean enterEmail(String email) {
		
		Boolean emailAddressEnabled= emailAddress.isEnabled();
		if (emailAddressEnabled) 
		{
			emailAddress.sendKeys(email);
			return true;
		}
		else
		{
			System.out.println("The Email Address Field is not enabled on this page");
			return false;
		}
	
	}
	
	public Boolean clickCreateButton() {
		Boolean createIsenabled=createAccountButton.isEnabled();
		//wait.until(ExpectedConditions.visibilityOf(createAccountButton));
		if (createIsenabled) {
			createAccountButton.click();
			String createAccountPageTitle=driver.getTitle();
			System.out.println("The title of create account page is "+createAccountPageTitle);
			return true;
		}
		else
		{
			System.out.println("Create account button was disabled");
			return false;
		}
	}

}
