package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.ProjectSpecificationMethod;

public class RecentlyViewed extends ProjectSpecificationMethod {

	public RecentlyViewed(RemoteWebDriver driver) {
		
		this.driver=driver;
	}
	
		//TC001 - 4. Click on the Dropdown icon in the Individuals tab 
	
	public RecentlyViewed induvidualTab() {
		WebElement click1 = driver.findElement(By.xpath("(//div[@class='slds-context-bar__label-action slds-p-left--none slds-p-right--x-small']//a)[1]"));
		driver.executeScript("arguments[0].click();", click1);
		
		//TC001 , TC004 - 5. Click on New Individual
		
		WebElement click = driver.findElement(By.xpath("//span[text()='New Individual']"));
		driver.executeScript("arguments[0].click();", click);
		return this;
	}
	
		//TC001 - 6. Enter the Last Name as 'Kumar'
	
	public RecentlyViewed newIndividual(String lastname) {
		
		driver.findElement(By.xpath("//input[contains(@class,'lastName compound')]")).sendKeys(lastname);
		driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		
		return this;
		
	}
	
		//TC001 - 7. Verify individuals created
	
	public RecentlyViewed individualsVerification() throws IOException {
		String message = driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']")).getText();
		
		if(message.contains("Kumar")){
			reportStep("Individuals created", "pass");
		} else reportStep("Error in creating the individuals", "fail");
		
		return this;
		
	}
	
		//TC002 - 5. Search the Individuals 'Kumar'
	
	public RecentlyViewed searchIndividuals(String lastname) throws InterruptedException {
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@class='slds-input']")).sendKeys(lastname,Keys.ENTER);
		return this;
		
	}
	
		//TC002, TC003 - 6. Click on the Dropdown icon and Select Edit
	
	public RecentlyViewed clickDropDowm() throws InterruptedException {
		Thread.sleep(3000);
		WebElement click = driver.findElement(By.xpath("(//div[@class='forceVirtualActionMarker forceVirtualAction']/a[@role='button'])[1]"));
		driver.executeScript("arguments[0].click();", click);
		
		return this;
	}
		
	public RecentlyViewed clickEdit() throws InterruptedException {
		Thread.sleep(3000);
		WebElement clk = driver.findElement(By.xpath("//a[@title='Edit']"));
		driver.executeScript("arguments[0].click();", clk);
		return this;
	}
	
		//TC002 , TC004 - 7.Select Salutation as 'Mr' 8.Enter the first name as 'Ganesh' and click on save
	
	public RecentlyViewed editIndividuals(String firstname) {
		
		WebElement edit = driver.findElement(By.xpath("(//a[@class='select'])[1]"));
		driver.executeScript("arguments[0].click();", edit);
		driver.findElement(By.xpath("//a[text()='Mr.']")).click();
		driver.findElement(By.xpath("//input[contains(@class,'firstName')]")).sendKeys(firstname);
		driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		
		return this;
	}
	
	//TC002 - 9. Verify the first name as 'Ganesh'
	public RecentlyViewed editIndividualVerification() throws InterruptedException, IOException {
		
		Thread.sleep(2000);
		String msg = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
		
		if(msg.contains("Ganesh")){
			reportStep("Ganesh name is present", "pass");
		} else reportStep("Ganesh name is not present", "fail");
		
		return this;
	}
	
	//TC003 - 6. Click on the Dropdown icon and Select Delete
	
	public RecentlyViewed clickDelete() throws InterruptedException {
		
		WebElement delete = driver.findElement(By.xpath("//a[@role='menuitem']/div[@title='Delete']"));
		driver.executeScript("arguments[0].click();", delete);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Delete']")).click();
		
		return this;
	}
	
	//TC003 - 8. Verify Whether Individual is Deleted using Individual last name
	public void deleteIndividualVerification(String lastname) throws InterruptedException, IOException {
		
		String msg = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
		System.out.println(msg);
		driver.findElement(By.xpath("//input[@class='slds-input']")).sendKeys(lastname,Keys.ENTER);
		Thread.sleep(3000);
		String verify = driver.findElement(By.xpath("//span[text()='No items to display.']")).getText();
		System.out.println(verify);
		if(msg.contains("deleted")) {
			reportStep("Indiviuals deleted", "pass");
		} else reportStep("Indiviuals not deleted", "fail");
	}
	
	//TC004 10. Verify the Alert message
	
	public void verifyAlert() throws IOException {
		String alertMessage = driver.findElement(By.xpath("//ul[@class='errorsList']//li")).getText();
		if(alertMessage.contains("These required fields must be completed")) {
			reportStep("Alert displayed", "pass");
		} else reportStep("Alert not displayed", "fail");
	}
	
	//TC005 - Sort Induviduals by name
	
	public RecentlyViewed sortIndivuduals() throws IOException {
		 driver.findElement(By.xpath("(//a[@class='toggle slds-th__action slds-text-link--reset '])[1]")).click();
		 reportStep("Indiviuals sorted", "pass");
		 return this;
	}
	
	//TC006 - Click on the New Dashboards button
	
	public RecentlyViewed newDashboardButton() {
		driver.findElement(By.xpath("//div[text()='New Dashboard']")).click();
		return this;
	}
	
	//TC006 - Input Dashboard name and save 
	
	public RecentlyViewed enterDashboardName(String dashBoardName) throws IOException {
		WebElement dashboardName = driver.findElement(By.xpath("//input[@id='dashboardNameInput']"));
		dashboardName.sendKeys(dashBoardName);
		driver.findElement(By.xpath("//button[@id='submitBtn']")).click();
		String title = driver.getTitle();
		if(title.contains(dashBoardName)) {
			reportStep("DashBoard Created", "pass");
		} else reportStep("DashBoard not Created", "fail");
		return this;
	}
}
