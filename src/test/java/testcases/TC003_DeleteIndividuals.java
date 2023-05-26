package testcases;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethod;
import pages.Home;

public class TC003_DeleteIndividuals extends ProjectSpecificationMethod {

	@BeforeTest
	public void setup() {
		excelfile="TC001_Individuals";
		testName="DeleteIndivuduals";
		testDescription="Delete individuals and confirm";
		testCategory="Regression";
		testAuthor="Leema Josephine";
	}
	
	@Test(dataProvider = "ReadExcel")
	public void deleteIndividuals(String firstname, String lastname) throws InterruptedException, IOException {
		
		new Home(driver)
		.hometoggle()
		.appLauncher()
		.clickDropDowm()
		.clickDelete()
		.deleteIndividualVerification(lastname);
	}
}
