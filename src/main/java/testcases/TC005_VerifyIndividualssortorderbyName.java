package testcases;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethod;
import pages.Home;

public class TC005_VerifyIndividualssortorderbyName extends ProjectSpecificationMethod {

	@BeforeTest
	public void setup() {
		testName="SortCreatedIndivuduals";
		testDescription="Sort created individuals and verify";
		testCategory="Regression";
		testAuthor="Leema Josephine";
	}
	@Test
	public void verifySort() throws InterruptedException, IOException {
		new Home(driver)
		.hometoggle()
		.appLauncher()
		.sortIndivuduals();
	}
}
