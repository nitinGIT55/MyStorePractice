package applicationPages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class accountCreationPage {
	
	WebDriver driver;
	
	//Objects on the CREATE AN ACCOUNT page
	
	//YOUR PERSONAL INFORMATION id_gender2
	
	@FindBy(css="input#id_gender1") public WebElement genderMr;
	@FindBy(css="input#id_gender2") public WebElement genderMRS;
	@FindBy(css="input#customer_firstname") public WebElement customerFirstName;
	@FindBy(css="input#customer_lastname") public WebElement customerLastName;
	@FindBy(css="input#email") public WebElement prePopulatedEmail;
	@FindBy(css="input#passwd") public WebElement customerPassword;
	@FindBy(css="select#days") public WebElement dobDay;
	@FindBy(css="select#months")public WebElement dobMonth;
	@FindBy(css="select#years")public WebElement dobYear;
	@FindBy(css="input#newsletter")public WebElement checkNewsLetter;
	@FindBy(css="input#optin")public WebElement checkSpecialOffers;
	
	//YOUR ADDRESS 
	
	@FindBy(css="input#firstname") public WebElement addFirstName;
	@FindBy(css="input#lastname") public WebElement addLastName;
	@FindBy(css="input#company") public WebElement addCompanyName;
	@FindBy(css="input#address1") public WebElement cmpnyAddressL1;
	@FindBy(css="input#address2") public WebElement cmpnyAddressL2;
	@FindBy(css="input#city") public WebElement addCity;
	@FindBy(css="select#id_state") public WebElement addSelectState;
	@FindBy(css="input#postcode") public WebElement addZip;
	@FindBy(css="select#id_country") public WebElement addSelectCountry;
	@FindBy(css="textarea#other") public WebElement additionalInfo;
	@FindBy(css="input#phone") public WebElement addHomePhone;
	@FindBy(css="input#phone_mobile") public WebElement addMobNumber;
	@FindBy(css="input#alias") public WebElement addressAliasName;
	@FindBy(css="button#submitAccount") public WebElement registerButton;
	
	
	//Basic constructor
	public accountCreationPage(WebDriver driver2) {
		this.driver=driver2;
	}
	//enterYourInformation()
	
	@SuppressWarnings("resource")
	public void enterYourInformation() throws Exception {
		FileInputStream inputStream= new FileInputStream("./ExcelSheets/MyData.xlsx");
		XSSFWorkbook wrkBook= new XSSFWorkbook(inputStream);
		XSSFSheet mySheet1= wrkBook.getSheetAt(0);
		XSSFSheet mySheet2=wrkBook.getSheetAt(1);
		XSSFCell cellValue=mySheet1.getRow(1).getCell(0);
		String gender=cellValue.toString();
		if(gender!="M") {
			System.out.println("Value of gender is "+gender);
			genderMr.click();
		}
		else
		{
			System.out.println("Value of gender is "+gender);
			genderMRS.click();
		}
		
		cellValue=mySheet1.getRow(1).getCell(1);
		customerFirstName.sendKeys(cellValue.toString());
		cellValue=mySheet1.getRow(1).getCell(2);
		customerLastName.sendKeys(cellValue.toString());
		cellValue=mySheet1.getRow(1).getCell(3);
		String printedMail=cellValue.toString();
		String mail=prePopulatedEmail.getAttribute("value");
		if (mail==printedMail) {
			System.out.println("Email Id is prepopulated corretly as "+printedMail);
		}
		else {
			System.out.println("Incorrect Email Id is populated. Please check Id "+printedMail);
		}
		cellValue=mySheet1.getRow(1).getCell(4);
		customerPassword.sendKeys(cellValue.toString());
		cellValue=mySheet1.getRow(1).getCell(5);
		Select day=new Select(dobDay);
		String day1=cellValue.toString();
		day.selectByValue("10");;
		cellValue=mySheet1.getRow(1).getCell(6);
		Select month=new Select(dobMonth);
		String month1=cellValue.toString();
		month.selectByValue("6");
		cellValue=mySheet1.getRow(1).getCell(7);
		Select year=new Select(dobYear);
		String year1=cellValue.toString();
		year.selectByValue("1999");
		cellValue=mySheet1.getRow(1).getCell(8);
		String select1=cellValue.toString();
		if(select1!="Yes") {
		checkNewsLetter.click();
		}
		cellValue=mySheet1.getRow(1).getCell(9);
		String select2=cellValue.toString();
		if(select2!="Yes") {
		checkSpecialOffers.click();
		}
		
	}
	
	
	public void enterYourAddress() throws IOException {
		FileInputStream inputStream= new FileInputStream("./ExcelSheets/MyData.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook wrkBook= new XSSFWorkbook(inputStream);
		XSSFSheet mySheet2=wrkBook.getSheetAt(1);
		XSSFCell cellValue2;
		
		cellValue2=mySheet2.getRow(1).getCell(3);
		addCompanyName.sendKeys(cellValue2.toString());
		
		cellValue2=mySheet2.getRow(1).getCell(4);
		cmpnyAddressL1.sendKeys(cellValue2.toString());
		
		cellValue2=mySheet2.getRow(1).getCell(5);
		cmpnyAddressL2.sendKeys(cellValue2.toString());
		
		cellValue2=mySheet2.getRow(1).getCell(6);
		addCity.sendKeys(cellValue2.toString());
		
		//int stateindx=(int)mySheet2.getRow(1).getCell(7).getNumericCellValue();
		Select state = new Select(addSelectState);
		//state.selectByIndex(stateindx);
		state.selectByValue("1");
		
		cellValue2=mySheet2.getRow(1).getCell(8);
		addZip.sendKeys(cellValue2.toString());
		
		//int cntryindx=(int)mySheet2.getRow(1).getCell(9).getNumericCellValue();
		Select country = new Select(addSelectCountry);
		//country.selectByIndex(cntryindx);
		country.selectByValue("21");
		
		cellValue2=mySheet2.getRow(1).getCell(10);
		additionalInfo.sendKeys(cellValue2.toString());
		
		cellValue2=mySheet2.getRow(1).getCell(11);
		addHomePhone.sendKeys(cellValue2.toString());
		
		cellValue2=mySheet2.getRow(1).getCell(12);
		addMobNumber.sendKeys(cellValue2.toString());
		
		cellValue2=mySheet2.getRow(1).getCell(13);
		addressAliasName.sendKeys(cellValue2.toString());
		
		//clicking on Register button after all the information is filled
		registerButton.click();
		
		
		}
	
	
	public void testingExcelReader() throws IOException {
		FileInputStream inputStream= new FileInputStream("./ExcelSheets/MyData.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook wrkBook= new XSSFWorkbook(inputStream);
		XSSFSheet mySheet2=wrkBook.getSheetAt(0);
		XSSFRow row = mySheet2.getRow(0);
		int columncount=row.getLastCellNum();
		System.out.println("The total number of columns in the excel is "+columncount);
		int rowcount =mySheet2.getLastRowNum()+1;
		System.out.println("The total number of rows in the excel is "+rowcount);
		XSSFCell cellValue2;
		
		for (int column=0;column<columncount;column++)
		{
		cellValue2=mySheet2.getRow(1).getCell(column);
		System.out.println(cellValue2.toString()+"||");
		}
	}
	
	
	
	}
	
	


