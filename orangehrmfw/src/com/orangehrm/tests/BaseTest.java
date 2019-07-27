package com.orangehrm.tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;


import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.ExcelUtil;
import com.orangehrm.utilities.Log;
import com.orangehrm.utilities.ReadPropertyFile;

public class BaseTest {

	private WebDriver driver;
	
	
	
	@BeforeSuite
	public void initialize() throws IOException{
		Log.error("Log Error");
		try{
			ReadPropertyFile.intializeConfig();
			ExcelUtil.initializeExcel();
		}
		catch(Exception e){
			Log.error("Failed read excel: " + e.getMessage());
		}
		finally{
			DOMConfigurator.configure("..\\orangehrmfw\\resources\\log4j.xml");
		}
		
	}
	
	

	@BeforeMethod
	public void launchBrowser(Method method) {		
		Log.startTestCase(method.getName());
		String browserName = ReadPropertyFile.readProperty("browserName");
		if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			Log.info("Successfully launch the browser");
		} else if (browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", ReadPropertyFile.readProperty("ieDriverPath"));

			driver = new InternetExplorerDriver();
			Log.info("Successfully launch the browser");
		} else if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", ReadPropertyFile.readProperty("chromeDriverPath"));

			driver = new ChromeDriver();
			Log.info("Successfully launch the browser");
		} else {		
			Log.error("Invalid Browser");		
			
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get(ReadPropertyFile.readProperty("url"));
	}

	
	@AfterMethod
	public void closeBrowser(){
		driver.quit();
		Log.info("Successfully close the browser");
		Log.endTestCase();
	}
	
	public WebDriver getDriver(){
		return driver;
	}
	
	public void login(){
		LoginPage loginPage=new LoginPage(getDriver());
		String userName = ReadPropertyFile.readProperty("userName");
		String password = ReadPropertyFile.readProperty("password");		
		loginPage.login(userName,password);
	}
}
