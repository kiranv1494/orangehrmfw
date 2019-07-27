package com.orangehrm.pages;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	private WebDriver driver=null;
	
	public BasePage(WebDriver driver){
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public WebDriver getDriver(){
		return driver;
	}
	
	public void switchWindow(){
		String parentWindow= driver.getWindowHandle();
		Set<String> windows= driver.getWindowHandles();
		for(String currentWindow:windows){
			if(!currentWindow.equals(parentWindow)){
				driver.switchTo().window(currentWindow);				
				
			}
		}
	}
	
	public void enterText(WebElement element,String value){
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(value);
	}
	
}
