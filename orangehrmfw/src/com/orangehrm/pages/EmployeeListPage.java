package com.orangehrm.pages;



import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmployeeListPage extends BasePage{

	@FindBy(linkText="Employee List")
	private WebElement linkEmployeeList;
	
	@FindBy(xpath="//*[@id='resultTable']/tbody/tr/td[2]")
	private List<WebElement> tableEmployee;
	
	@FindBy(id="btnSave")
	private WebElement btnEdit;
	
	@FindBy(id="personal_txtEmpFirstName")
	private WebElement txtFullName;
	
	@FindBy(id="personal_txtEmpLastName")
	private WebElement txtLastName;
	
	
	
	public EmployeeListPage(WebDriver driver) {
		super(driver);
	}
	
	public void navigateToEMployeeList(){
		linkEmployeeList.click();
	}
	
	public String getEmployeeRecord(String expectedEmpId){
		String actualEmpId = null;
		for(WebElement id:tableEmployee){
			actualEmpId = id.getText();
			if(actualEmpId.equals(expectedEmpId)){
				break;
			}
		}
		return actualEmpId;
	}
	
	public void clickOnEmpId(String expectedEmpId){
		
		WebElement linkEmpId = getDriver().findElement(By.xpath("//table[@id='resultTable']/descendant::a[text()='"+expectedEmpId+"']"));
	
		linkEmpId.click();
		
	}
	
	public List<String> editEmployee(String firstName,String lastName){
		btnEdit.click();
		txtFullName.sendKeys(firstName);
		txtLastName.sendKeys(lastName);
		btnEdit.click();
		List<String> expectedEmpValues = new ArrayList<String>();
		expectedEmpValues.add(firstName);
		expectedEmpValues.add(lastName);
		return expectedEmpValues;
	}
	
	public List<String> getUpdatedEmpDetails(String expectedEmpId){
		List<WebElement> empDetails =getDriver().findElements(By.xpath("//table[@id='resultTable']/descendant::a[text()='"+expectedEmpId+"']/parent::td/following-sibling::td"));
		String actualFirstName = empDetails.get(0).getText();
		String actualLastName = empDetails.get(1).getText();
		List<String> actualEmpValues = new ArrayList<String>();
		actualEmpValues.add(actualFirstName);
		actualEmpValues.add(actualLastName);
		return actualEmpValues;
	}
	
	
}
