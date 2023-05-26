package testcases;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethod;
import pages.Home;

public class TC001_CreateIndividuals extends ProjectSpecificationMethod {


	@BeforeTest
	public void setup() {
		excelfile="TC001_Individuals";
		testName="CreateIndivuduals";
		testDescription="Create individuals with valid inputs";
		testCategory="Regression";
		testAuthor="Leema Josephine";
	}

	@Test(dataProvider = "ReadExcel")
	public void CreateIndividuals(String firstname,String lastname) throws IOException, InterruptedException {
		
		new Home(driver)
		.hometoggle()
		.appLauncher()
		.induvidualTab()
		.newIndividual(lastname)
		.individualsVerification();

	}

}
