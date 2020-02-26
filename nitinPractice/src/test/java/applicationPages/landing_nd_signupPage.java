package applicationPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.testng.Reporter;

public class landing_nd_signupPage {
	
	WebDriver driver;
	String failed;
	//WebDriverWait wait =new WebDriverWait(page1driver,20);
	
	
	
	//Objects on the Landing Page
	@FindBy(css="a.login") public WebElement signinbutton;
	
	//Objects on the Sign In Page
	@FindBy(css="input#email_create") public WebElement emailAddress;
	@FindBy(css="button#SubmitCreate") public WebElement createAccountButton;
	
	
	
	
	//Constructor for this page object
	public landing_nd_signupPage(WebDriver page1driver){
		this.driver =page1driver;
	}
	
	public String clickOnSignIn() {
		//wait.until(ExpectedConditions.visibilityOf(signinbutton));
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
