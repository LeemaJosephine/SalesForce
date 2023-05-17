package testcases;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethod;
import pages.Home;

public class TC006_DashBoardCreation extends ProjectSpecificationMethod{

	@BeforeTest
	public void setup() {
		excelfile="TC006_DashBoard";
		testName="CreateDashboard";
		testDescription="Delete individuals and confirm";
		testCategory="Regression";
		testAuthor="Leema Josephine";
	}
	
	@Test(dataProvider = "ReadExcel")
	public void dashBoardCreation(String dashBoardName) throws IOException, InterruptedException {
		
		new Home(driver).hometoggle().appLauncherDashboard().newDashboardButton().enterDashboardName(dashBoardName);
		
	}
}
