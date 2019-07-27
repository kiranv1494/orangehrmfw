package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.orangehrm.utilities.Log;

public class AddEmployeePage extends BasePage {

	@FindBy(linkText="Add Employee")
	private WebElement linkAddEmployee;
	
	@FindBy(id="firstName")
	private WebElement txtFirstName;
	
	@FindBy(id="lastName")
	private WebElement txtLastName;
	
	@FindBy(id="btnSave")
	private WebElement btnSave;
	
	@FindBy(id="employeeId")
	private WebElement txtEmployeeId;
	
	

	
	public AddEmployeePage(WebDriver driver) {
		super(driver);
	}
	
	public void navigateToAddEmployee(){
		try{
			linkAddEmployee.click();
		}
		catch(Exception e){
			Log.error("Failed to Navigate Add Employee." +e.getCause());
		}
		
	}
	
	public String addEmployeeDetails(String firstName,String lastName){
		txtFirstName.sendKeys(firstName);
		txtLastName.sendKeys(lastName);
		String expectedEmpId= txtEmployeeId.getAttribute("value");
		btnSave.click();
		return expectedEmpId;
	}
	
	
}
