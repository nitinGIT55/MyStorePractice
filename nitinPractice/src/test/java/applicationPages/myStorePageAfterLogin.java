package applicationPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class myStorePageAfterLogin {
	
	WebDriver driver;
	
	//Default constructor for this page
	public myStorePageAfterLogin( WebDriver driver2) {
		this.driver=driver2;
	}

	
	//Objects in the top menu bar
	
	@FindBy(xpath="//a[contains(@title,'Women')]") public WebElement tabWomen;
	@FindBy(xpath="//a[contains(@title,'Dresses')]") public WebElement tabDresses;
	@FindBy(xpath="//a[contains(@title,'T-shirts')]") public WebElement tabTshirt;
	@FindBy(xpath="//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[2]") public WebElement tShirtOneOneQuickView;
	@FindBy(css="a.logout") public WebElement logoutButton;
	
	
	public void selectTshirtandQuickView() {
		tabWomen.click();
		System.out.println("Clicked on Women tab with text"+tabWomen.getText());
		tShirtOneOneQuickView.click();
		System.out.println("Clicked on first TShirt with text"+tShirtOneOneQuickView.getText());
	}
	
	
	public Boolean logoutFromMyStore() {
		logoutButton.click();
		String pageName=driver.getTitle();
		Boolean isLoggedOut =pageName.contentEquals("Women - My Store");
		return isLoggedOut;
	}
}
