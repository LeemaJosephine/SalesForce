package base;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import util.ReadExcel;

public class ProjectSpecificationMethod {

	public RemoteWebDriver driver;
	public String excelfile;
	public static ExtentReports extent;
	public static ExtentTest test;
	public String testName,testDescription,testCategory,testAuthor;
	
	@BeforeSuite
	public void startReport() {
		ExtentHtmlReporter reporter = new ExtentHtmlReporter("./reports/result.html");		
		reporter.setAppendExisting(false);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}
	
	@BeforeClass
	public void testDetails() {
		test = extent.createTest(testName, testDescription);
		test.assignCategory(testCategory);
		test.assignAuthor(testAuthor);
	}
	
	public int takeSnap() throws IOException {
		
		int ranNum = (int) (Math.random() * 9999999+1000000);
		File source=driver.getScreenshotAs(OutputType.FILE);
		File target=new File("./snaps/img"+ranNum+".png");
		FileUtils.copyFile(source, target);
		
		return ranNum;
	}
	
	public void reportStep(String stepDetails,String stepStatus) throws IOException {
		
		int ranNum= takeSnap();
		if(stepStatus.equalsIgnoreCase("pass")) {
			test.pass(stepDetails, MediaEntityBuilder.createScreenCaptureFromPath(".././snaps/img"+ranNum+".png").build());
		} else if (stepStatus.equalsIgnoreCase("fail")) {
			test.fail(stepDetails, MediaEntityBuilder.createScreenCaptureFromPath(".././snaps/img"+ranNum+".png").build());
			throw new RuntimeException("See extent report for more details");
		}
	}
	@Parameters({"browser","url","username","password"})
	@BeforeMethod
	public void launchBrowser(String browser, String url, String username, String password) throws IOException {
		
		if(browser.equalsIgnoreCase("Chrome")) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--remote-allow-origins=*");
		driver=new ChromeDriver(options);
		} else if(browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		try {
			HttpURLConnection httpConnection = (HttpURLConnection) new URL(url).openConnection();
			httpConnection.connect();
			int respCode = httpConnection.getResponseCode();

			if (respCode >= 400) {
				System.out.println(url + " is broken and its responsecode is " + respCode);
				reportStep("PASS", "This " + url + " is broken");
			} else {
				System.out.println(url + " is Valid and its responsecode is " + respCode);
				reportStep("PASS", "This " + url + " is verified valid link");
			}
		} catch (Exception ex) {
			reportStep("FAIL", "Problem while Verifing the " + url + " as broken link");
			ex.printStackTrace();
		}
		
		//1. Login to https://login.salesforce.com
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("Login")).click();
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}
	
	@DataProvider(name="ReadExcel")
	public String[][] getData() throws IOException {
		String[][] readData = ReadExcel.readExcelSheet(excelfile);
		return readData;
	}
	
	@AfterSuite
	public void endReport() {
		extent.flush();
	}
	
}
