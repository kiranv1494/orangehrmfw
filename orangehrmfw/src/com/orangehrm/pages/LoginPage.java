package com.orangehrm.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangehrm.tests.BaseTest;
import com.orangehrm.utilities.Log;

public class LoginPage extends BasePage {

	@FindBy(xpath = "//input[@id='txtUsername']")
	private WebElement txtUserName;

	@FindBy(xpath = "//input[@id='txtPassword']")
	private WebElement txtPassword;

	@FindBy(xpath = "//input[@id='btnLogin']")
	private WebElement btnLogin;

	@FindBy(partialLinkText = "Welcome")
	private WebElement linkWelCome;

	@FindBy(linkText = "Logout")
	private WebElement linkLogout;

	@FindBy(id = "spanMessage")
	private WebElement lblErrorMsg;

	@FindBy(id = "spanCopyright")
	private WebElement lblFooter;

	@FindBy(xpath = "//span[@id='spanSocialMedia']/a")
	private List<WebElement> linksFooter;
	


	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void login(String userName, String password) {

		enterText(txtUserName, userName);
		txtPassword.sendKeys(password);
		btnLogin.click();
		Log.info("Successfully login into the application");

	}

	public void logout() {
		linkWelCome.click();
		linkLogout.click();

	}

	public String getErrorMessage() {
		String errorMsg = lblErrorMsg.getText();
		Log.info("Successfully get the Text: " +errorMsg);
		return errorMsg;
	}

	public String getFooterText() {
		return lblFooter.getText();
	}

	public void verifyFooterLinks() {
		for (WebElement link : linksFooter) {
			link.click();
		}
	}

}
