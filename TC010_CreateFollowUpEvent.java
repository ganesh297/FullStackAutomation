package com.selenium.bootcamp;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC010_CreateFollowUpEvent {

	public static void main(String[] args) throws InterruptedException, IOException, AWTException {

		System.setProperty("webdriver.chrome.driver", "D:\\Webdrivers\\chromedriver.exe");
		ChromeOptions options=new ChromeOptions();
		options.addArguments("—disable-notifications");
		WebDriver driver=new ChromeDriver(options);


		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		driver.findElement(By.id("username")).sendKeys("mercury.bootcamp@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Bootcamp@123");
		driver.findElement(By.id("Login")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebDriverWait wait=new WebDriverWait(driver, 15);

		WebElement appLauncher=driver.findElement(By.xpath("//span[text()='App Launcher']//ancestor::button"));
		wait.until(ExpectedConditions.elementToBeClickable(appLauncher)).click();

		//Click view All 
		WebElement viewAll=driver.findElement(By.xpath("//h3[text()='Apps']//following::button[text()='View All']"));
		wait.until(ExpectedConditions.elementToBeClickable(viewAll)).click();		

		//Click Sales from Content App
		WebElement content=driver.findElement(By.xpath("//p[text()='Content']"));
		wait.until(ExpectedConditions.elementToBeClickable(content)).click();
		Thread.sleep(5000);

		//Click on View All under Today's tab 
		WebElement viewAllTasks=driver.findElement(By.xpath("//span[text()='Today’s Tasks']//following::a[@aria-label='View All Tasks']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewAllTasks);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", viewAllTasks);


		WebElement splitView=driver.findElement(By.xpath("//button[@title='Display as Split View']"));
		wait.until(ExpectedConditions.elementToBeClickable(splitView)).click();


		WebElement table=driver.findElement(By.xpath("//*[name()='svg' and @data-key='table']/ancestor::a"));
		wait.until(ExpectedConditions.elementToBeClickable(table)).click();

		Thread.sleep(5000);

		WebElement showMoreActions=driver.findElement(By.xpath("//span[text()='Show more actions']//ancestor::a"));

		js.executeScript("arguments[0].click();", showMoreActions);

		//wait.until(ExpectedConditions.elementToBeClickable(showMoreActions)).click();

		WebElement createNewEvent=driver.findElement(By.xpath("//div[text()='Create Follow-Up Event']//parent::a"));
		wait.until(ExpectedConditions.elementToBeClickable(createNewEvent)).click();

		Thread.sleep(5000);

		WebElement subject=driver.findElement(By.xpath("//label[text()='Subject']//following::input[@role='combobox']"));
		wait.until(ExpectedConditions.elementToBeClickable(subject)).click();

		WebElement meetingData=driver.findElement(By.xpath("//span[text()='Subject']//following::span[text()='Meeting']"));
		//js.executeScript("arguments[0].click();", meetingData);
		wait.until(ExpectedConditions.elementToBeClickable(meetingData)).click();

		WebElement removeName=driver.findElement(By.xpath("//label[text()='Subject']//following::span[text()='Name']//following::a[@class='deleteAction']"));
		wait.until(ExpectedConditions.elementToBeClickable(removeName)).click();



		WebElement selectName=driver.findElement(By.xpath("//h2[text()='Follow Up Event']//following::span[text()='Name']//following::input"));

		js.executeScript("arguments[0].click();", selectName);

		//wait.until(ExpectedConditions.elementToBeClickable(selectName)).click();

		WebElement naming=driver.findElement(By.xpath("//h2[text()='Follow Up Event']//following::span[text()='Name']//following::input//following::div[@class='primaryLabel slds-truncate slds-lookup__result-text']"));
		wait.until(ExpectedConditions.elementToBeClickable(naming)).click();

		WebElement removeRelatedTo=driver.findElement(By.xpath("//label[text()='Subject']//following::span[text()='Related To']//following::a[@class='deleteAction']"));
		wait.until(ExpectedConditions.elementToBeClickable(removeRelatedTo)).click();

		WebElement relatedTo=driver.findElement(By.xpath("//h2[text()='Follow Up Event']//following::span[text()='Related To']//following::input"));
		wait.until(ExpectedConditions.elementToBeClickable(relatedTo)).click();

		WebElement relatedToName=driver.findElement(By.xpath("//h2[text()='Follow Up Event']//following::span[text()='Related To']//following::input//following::div[@class='primaryLabel slds-truncate slds-lookup__result-text']"));
		wait.until(ExpectedConditions.elementToBeClickable(relatedToName)).click();

		WebElement startDate=driver.findElement(By.xpath("//legend[text()='Start']//following::label[text()='Date']//following::input"));
		wait.until(ExpectedConditions.elementToBeClickable(startDate)).click();

		LocalDate currentdate = LocalDate.now();
		Month currentMonthJava = currentdate.getMonth();

		WebElement CM=driver.findElement(By.xpath("//h2[contains(@id,'month-title')]"));
		String currentMonth=wait.until(ExpectedConditions.elementToBeClickable(CM)).getText(); 

		if(currentMonth.equalsIgnoreCase(currentMonthJava.toString())){
			WebElement nextMonth=driver.findElement(By.xpath("//*[name()='svg' and @data-key='right']"));
			wait.until(ExpectedConditions.elementToBeClickable(nextMonth)).click();


			List<WebElement> dates=driver.findElements(By.xpath("//span[@class='slds-day']"));	

			for(WebElement date:dates){

				String d=date.getText();
				if(d.equals("5")){
					date.click();
					break;

				}

			}

		}




		WebElement startTime=driver.findElement(By.xpath("//legend[text()='Start']//following::label[text()='Time']//following::input"));
		wait.until(ExpectedConditions.elementToBeClickable(startTime)).click();

		List<WebElement> stLists=driver.findElements(By.xpath("//lightning-base-combobox-item//following::span/span"));	

		for(WebElement stList:stLists){
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", stList);
			Thread.sleep(2000);
			String t=stList.getText();
			System.out.println(t);
			if(t.equals("8:00 AM")){
				stList.click();
				break;

			}

		}

		//END DATE

		WebElement endDate=driver.findElement(By.xpath("//legend[text()='End']//following::label[text()='Date']//following::input"));
		wait.until(ExpectedConditions.elementToBeClickable(endDate)).click();


		List<WebElement> dates=driver.findElements(By.xpath("//span[@class='slds-day']"));	

		for(WebElement date:dates){

			String d=date.getText();
			if(d.equals("15")){
				date.click();
				break;

			}

		}




		//END TIME
		WebElement endTime=driver.findElement(By.xpath("//legend[text()='End']//following::label[text()='Time']//following::input"));
		wait.until(ExpectedConditions.elementToBeClickable(endTime)).click();

		List<WebElement> stLists1=driver.findElements(By.xpath("//legend[text()='End']//following::label[text()='Time']//following::lightning-base-combobox-item//following::span/span"));	

		for(WebElement stList:stLists1){
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", stList);
			Thread.sleep(2000);
			String t=stList.getText();
			System.out.println(t);
			if(t.equals("3:00 PM")){
				stList.click();
				break;

			}

		}
		WebElement saveButton=driver.findElement(By.xpath("(//span[text()='Save']//parent::button)[2]"));
		wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();

		WebElement toastMessage=driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"));
		String TM=wait.until(ExpectedConditions.elementToBeClickable(toastMessage)).getText();
		System.out.println(TM);
		if(TM.contains(" was created.")){
			System.out.println("Test Case Passed");
		}else{
			System.out.println("Test Case Failed");

		}


		driver.close();

	}

}




