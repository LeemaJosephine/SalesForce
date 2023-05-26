package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.ProjectSpecificationMethod;

public class Home extends ProjectSpecificationMethod {

	public Home(RemoteWebDriver driver) {
		
		this.driver=driver;
	}
	
	//TC001 ,TC002 - 2. Click on the toggle menu button from the left corner
	
	public Home hometoggle() throws InterruptedException {
		
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		return this;
		
	}
	
	//TC001 , TC002 - 3. Click View All and click Individuals from App Launcher
	
	public RecentlyViewed appLauncher() {
		
		WebElement scroll = driver.findElement(By.xpath("//p[text()='Images']"));
		driver.executeScript("arguments[0].scrollIntoView();", scroll);
		
		WebElement click = driver.findElement(By.xpath("//p[text()='Individuals']"));
		driver.executeScript("arguments[0].click();", click);
		
		return new RecentlyViewed(driver);
		
	}
	
	//TC006 - Create new Dashboards
	
	public RecentlyViewed appLauncherDashboard() {
		
		WebElement scroll = driver.findElement(By.xpath("//p[text()='Customers']"));
		driver.executeScript("arguments[0].scrollIntoView();", scroll);
		
		WebElement click = driver.findElement(By.xpath("//p[text()='Dashboards']"));
		driver.executeScript("arguments[0].click();", click);
		
		return new RecentlyViewed(driver);
		
	}
}
