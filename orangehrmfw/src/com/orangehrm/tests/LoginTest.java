package com.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.ExcelUtil;
import com.orangehrm.utilities.Log;

public class LoginTest extends BaseTest {

	@Test
	public void verifyLoginWithBlankUserName() {		
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login(ExcelUtil.readData(1,1), ExcelUtil.readData(1,2));
		String actualErrorMsg = loginPage.getErrorMessage();
		Assert.assertEquals(actualErrorMsg, ExcelUtil.readData(1,3),
				"Verify error message if the user leaves username blank");
		Log.info("Validation successful");		
	}

	@Test
	public void verifyLoginWithBlankPassword() {		;
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login("Vishnu", "");
		String actualErrorMsg = loginPage.getErrorMessage();
		Assert.assertEquals(actualErrorMsg, "Password cannot be empty",
				"Verify error message if the user leaves Password blank");

	}

	@Test
	public void verifyLoginWithInvalidCredentials() {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login("Vishnu11", "Vishnu");
		String actualErrorMsg = loginPage.getErrorMessage();
		Assert.assertEquals(actualErrorMsg, "Invalid credentials", "Verify error message for Invalid credentials");

	}

	@Test
	public void verifyFooterText() {
		LoginPage loginPage = new LoginPage(getDriver());
		String actualFooterText = loginPage.getFooterText();
		String expectedFooterText = "OrangeHRM ver 3.0.1 © OrangeHRM Inc. 2005 - 2019 All rights reserved.";
		Assert.assertEquals(actualFooterText, expectedFooterText, "Verify Footer text");
	}
	
	@Test
	public void verifyAllFooterLinks(){
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.verifyFooterLinks();
	}

}
