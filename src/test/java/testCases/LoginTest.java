package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class LoginTest extends BaseClass {

	@Test(groups = {"sanity","master"})
	public void testLogin() throws InterruptedException {

		logger.info("***** Starting LoginTest Case ***** ");

		try {

			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked MyAccount link");
			hp.clickLogin();
			logger.info("Click login link");

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();

			MyAccountPage myp = new MyAccountPage(driver);
			boolean targetPage = myp.isTestVisible();
			Assert.assertEquals(targetPage, true, " Login Page");
			myp.clickLogout();
			logger.info("***** Finished LoginTestCase ***** ");
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Error login");
			logger.debug(e.getMessage());
			Assert.fail();
		}

	}

}
