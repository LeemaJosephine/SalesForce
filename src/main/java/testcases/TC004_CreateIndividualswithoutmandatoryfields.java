package testcases;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethod;
import pages.Home;

public class TC004_CreateIndividualswithoutmandatoryfields extends ProjectSpecificationMethod{

	@BeforeTest
	public void setup() {
		excelfile="TC001_Individuals";
		testName="MandatoryFieldCheck";
		testDescription="Create individuals without filling mandatory field and verify";
		testCategory="Regression";
		testAuthor="Leema Josephine";
	}
	@Test(dataProvider = "ReadExcel")
	public void createIndividualswithoutmandatoryfields(String firstname,String lastname) throws InterruptedException, IOException {
		
		new Home(driver)
		.hometoggle()
		.appLauncher()
		.induvidualTab()
		.editIndividuals(firstname)
		.verifyAlert();
	}
}
