package com.selenium.bootcamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC007_CreateCampaign {

	public static void main(String[] args) throws InterruptedException {

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
		
		//Click Sales from App Launcher
		WebElement sales=driver.findElement(By.xpath("//p[text()='Sales']"));
		wait.until(ExpectedConditions.elementToBeClickable(sales)).click();
		Thread.sleep(5000);
			
		//Click on Campaigns tab 
		WebElement campaigns=driver.findElement(By.xpath("//span[text()='Campaigns']//parent::a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", campaigns);
		
		//wait.until(ExpectedConditions.elementToBeClickable(campaigns)).click();
		
		//Click on New button
		WebElement newButtton=driver.findElement(By.xpath("//div[text()='New']//parent::a"));
		wait.until(ExpectedConditions.elementToBeClickable(newButtton)).click();
		
		
		//Enter Campaign name as 'Bootcamp',Get the text and Store it 
		WebElement CN=driver.findElement(By.xpath("//input[contains(@data-aura-rendered-by,'68')]"));
		wait.until(ExpectedConditions.elementToBeClickable(CN)).sendKeys("Bootcamp");
		
		String CNValue=driver.findElement(By.xpath("//input[contains(@data-aura-rendered-by,'68')]")).getAttribute("value");
		System.out.println(CNValue);
		
		WebElement SD=driver.findElement(By.xpath("//input[contains(@data-aura-rendered-by,'265')]"));
		wait.until(ExpectedConditions.elementToBeClickable(SD)).click();
		
		int setDateForTmwPlus1 = 0;
		
		List<WebElement> dates=driver.findElements(By.xpath("//span[contains(@class,'slds-day week')]"));
		
		for(int i=1;i<=dates.size();i++){
			String dateInCalendar=driver.findElement(By.xpath("(//span[contains(@class,'slds-day week')])["+i+"]")).getText();
			
			DateFormat dateFormat = new SimpleDateFormat("dd");
			Date curD = new Date();
			//System.out.println(dateFormat.format(curD));
			String currentDate=dateFormat.format(curD);
			String currentDateWithoutZero=currentDate.replaceAll("^0+(?!$)", "");
			int tmw=Integer.parseInt(currentDateWithoutZero);
			int tmwDate=tmw+1;
			
			String tmwDateString=String.valueOf(tmwDate);
			if(dateInCalendar.equals(tmwDateString)){
				
				//Choose Start date as Tomorrow
				driver.findElement(By.xpath("(//span[contains(@class,'slds-day week')])["+i+"]")).click();
				setDateForTmwPlus1=i+1;
				//System.out.println("i value:"+i);
				//System.out.println("setDateForTmwPlus1 value:"+setDateForTmwPlus1);
				break;
			}
			
		}
		
		
		WebElement ED=driver.findElement(By.xpath("//input[contains(@data-aura-rendered-by,'306')]"));
		wait.until(ExpectedConditions.elementToBeClickable(ED)).click();
		
		//End date as Tomorrow+1
		driver.findElement(By.xpath("(//span[contains(@class,'slds-day week')])["+setDateForTmwPlus1+"]")).click();
		
		//Click Save and Verify the newly created Campaign  "
		WebElement saveButton=driver.findElement(By.xpath("(//span[text()='Save']//parent::button)[2]"));
		wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
		
				
		//New Campaign should be created with name as 'Bootcamp'
		WebElement toastMessage=driver.findElement(By.xpath("//span[contains(@class,'toastMessage slds-text-heading--small forceActionsText')]"));
		String TM=wait.until(ExpectedConditions.elementToBeClickable(toastMessage)).getText();
		System.out.println("Campaign "+"\""+CNValue+"\""+" was created.");
		if(TM.equals("Campaign "+"\""+CNValue+"\""+" was created.")){
			
			System.out.println("Test Case Passed");
		}else{
			
			System.out.println("Test Case Failed");
		}
		
		driver.close();
		
	}
		
		
}

