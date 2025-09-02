package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
//	locators
//	By txtemail = By.id("input-email");
//	By txtPassword = By.id("input-password");
	
	@FindBy(id = "input-email") WebElement txtemail;
	@FindBy(id = "input-password") WebElement txtPassword;
	@FindBy(css = "input[value='Login']") WebElement btnLogin;
	
//	Action methods
	public void setEmail(String email) {
//		driver.findElement(txtemail).sendKeys(email);
		txtemail.sendKeys(email);
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}

}
