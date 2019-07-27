package com.orangehrm.tests;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.pages.AddEmployeePage;
import com.orangehrm.pages.EmployeeListPage;
import com.orangehrm.pages.LoginPage;


public class AddEmployeeTest extends BaseTest {

	@Test
	public void verifyAddEmployee(){		
		login();
		AddEmployeePage addEmployeePage=new AddEmployeePage(getDriver());
		addEmployeePage.navigateToAddEmployee();
		String expectedEmpId= addEmployeePage.addEmployeeDetails("Rahul","Reddy");
		EmployeeListPage employeeListPage=new EmployeeListPage(getDriver());
		employeeListPage.navigateToEMployeeList();
		String actualEmpId = employeeListPage.getEmployeeRecord(expectedEmpId);
		Assert.assertEquals(actualEmpId, expectedEmpId,"Verify Employee Id exist in WebTable");
	}
	
	@Test
	public void verifyEditEmployee(){		
		login();
		AddEmployeePage addEmployeePage=new AddEmployeePage(getDriver());
		addEmployeePage.navigateToAddEmployee();
		String expectedEmpId= addEmployeePage.addEmployeeDetails("Rahul","Reddy");
		EmployeeListPage employeeListPage=new EmployeeListPage(getDriver());
		employeeListPage.navigateToEMployeeList();
		String actualEmpId = employeeListPage.getEmployeeRecord(expectedEmpId);
		Assert.assertEquals(actualEmpId, expectedEmpId,"Verify Employee Id exist in WebTable");
		employeeListPage.clickOnEmpId(expectedEmpId);
		List<String> expectedEmpValues= employeeListPage.editEmployee("Rahul1","Reddy1");
		employeeListPage.navigateToEMployeeList();
		List<String> actualEmpValues= employeeListPage.getUpdatedEmpDetails(expectedEmpId);
	    Assert.assertEquals(actualEmpValues,expectedEmpValues,"Verify record after edit");
	}
}
