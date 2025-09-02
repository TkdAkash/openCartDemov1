package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class AccountRegisterationTest extends BaseClass {

	@Test(groups = {"regression","master"})
	public void verify_account_registeration() {

		logger.info("***** Started AccountRegisterationTest *****");

		try {

			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Cliced MY account.....");

			hp.clickRegister();
			logger.info("Clicked Register.....");

			AccountRegisterationPage accRegister = new AccountRegisterationPage(driver);
//		Approach 1 - Hard code value
			/*
			 * accRegister.setFirstName("aaaa"); accRegister.setLastName("bbbb");
			 * accRegister.setEmail("akakak@gmail.com"); accRegister.setTelephone("12345");
			 * accRegister.setPassword("12345"); accRegister.setConfirmPassword("12345");
			 * accRegister.clickPrivacyPolicy(); accRegister.clickContinue();
			 */

//		Approach 2 - Random values
			accRegister.setFirstName(gererateRandomString().toUpperCase());
			logger.info("First name entered");
			accRegister.setLastName(generateRandomNumber().toUpperCase());
			logger.info("Last name entered");
			accRegister.setEmail(gererateRandomString() + "@gmail.com");
			logger.info("Email entered");
			accRegister.setTelephone(generateRandomNumber());
			logger.info("Phone number entered");

			String pass = gererateAphaNumeric();
			System.out.println(pass);
			accRegister.setPassword(pass);
			accRegister.setConfirmPassword(pass);
			logger.info("Password enetred");

			accRegister.clickPrivacyPolicy();
			logger.info("Policy checked");

			accRegister.clickContinue();
			logger.info("Clicked continue");

			if (accRegister.getConfirmationMessage().equals("Your Account Has Been Created!")) {

				Assert.assertTrue(true);
			} else {
				logger.error("Failed");
				logger.debug("Debug log");
				Assert.fail();
			}

//			Assert.assertEquals(accRegister.getConfirmationMessage(), "Your Account Has Been Created!");

			logger.info("***** Passed " + this.getClass() + " *****");
		} catch (Exception e) {

			logger.error("Failed");
			logger.debug("Debug log");
			Assert.fail();
		}

	}

}
