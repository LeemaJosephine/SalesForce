package testcases;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethod;
import pages.Home;

public class TC002_EditIndividuals extends ProjectSpecificationMethod {

	@BeforeTest
	public void setup() {
		excelfile="TC001_Individuals";
		testName="EditIndivuduals";
		testDescription="Edit individuals with valid inputs";
		testCategory="Regression";
		testAuthor="Leema Josephine";
	}
	
	@Test(dataProvider = "ReadExcel")
	public void editIndivuduals(String firstname,String lastname) throws InterruptedException, IOException {
		
		new Home(driver)
		.hometoggle()
		.appLauncher()
		.searchIndividuals(lastname)
		.clickDropDowm()
		.clickEdit()
		.editIndividuals(firstname)
		.editIndividualVerification();
	}
}
