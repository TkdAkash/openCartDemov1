package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
//	constructor
	public HomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
	}
	
	
//	locator

//	By myAccount_loc = By.xpath("//span[text()='My Account']");
	
	@FindBy(xpath = "//span[text()='My Account']") WebElement lnkmyAccount;
	@FindBy(linkText = "Register") WebElement lnkRegister;
	@FindBy(linkText = "Login") WebElement lnkLogin;
	
	
//	action methods
	
	public void clickMyAccount() {
		lnkmyAccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		lnkLogin.click();
	}
	
}
