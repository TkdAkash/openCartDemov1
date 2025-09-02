package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "datadriven")
	public void verify_loginDDT(String email, String password, String exp) throws InterruptedException {

		logger.info("***** Starting TC003_loginDDT ***** ");
		try {

			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked MyAccount link");
			hp.clickLogin();
			logger.info("Click login link");

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(password);
			lp.clickLogin();

			System.out.println("email: "+email+"   password: "+password+"   res"+exp);
			MyAccountPage myp = new MyAccountPage(driver);
			boolean targetPage = myp.isTestVisible();
//			Thread.sleep(5000);

			/*
			 * Data is valid - login success - test pass - logout Data is valid -- login
			 * failed - test fail
			 * 
			 * Data is invalid - login success - test fail - logout Data is invalid -- login
			 * failed - test pass
			 */

			if(exp.equalsIgnoreCase("Valid"))
			{
				if(targetPage==true)
				{
					myp.clickLogout();
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			
			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(targetPage==true)
				{
					myp.clickLogout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Expection occured" + e.getMessage());
			Assert.fail();
		}

		logger.info("***** Finished TC003_loginDDT ***** ");

	}
}
