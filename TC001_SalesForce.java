package com.selenium.bootcamp;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC001_SalesForce {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "D:\\Webdrivers\\chromedriver.exe");
		ChromeOptions options=new ChromeOptions();
		options.addArguments("—disable-notification");
		WebDriver driver=new ChromeDriver(options);
		

		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		driver.findElement(By.id("username")).sendKeys("mercury.bootcamp@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Bootcamp$123");
		driver.findElement(By.id("Login")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		WebDriverWait wait=new WebDriverWait(driver, 15);
		
		WebElement appLauncher=driver.findElement(By.xpath("//span[text()='App Launcher']//ancestor::button"));
		wait.until(ExpectedConditions.elementToBeClickable(appLauncher)).click();
		
		WebElement content=driver.findElement(By.xpath("//p[text()='Content']//parent::span"));
		wait.until(ExpectedConditions.elementToBeClickable(content)).click();
		
		WebElement viewAllDeals=driver.findElement(By.xpath("//span[text()='View All Key Deals']//parent::a"));
		wait.until(ExpectedConditions.elementToBeClickable(viewAllDeals)).click();
		
		Thread.sleep(5000);
		
		WebElement dropdownAllOpportunities=driver.findElement(By.xpath("//*[name()='svg' and @data-key='chevrondown']/ancestor::a"));
		wait.until(ExpectedConditions.elementToBeClickable(dropdownAllOpportunities)).click();
				
		
		WebElement allOpportunities=driver.findElement(By.xpath("//span[text()='All Opportunities']//ancestor::one-app-nav-bar-menu-item"));
		wait.until(ExpectedConditions.elementToBeClickable(allOpportunities)).click();
		
		WebElement newLink=driver.findElement(By.xpath("//div[text()='New']//ancestor::a"));
		wait.until(ExpectedConditions.elementToBeClickable(newLink)).click();		
		
		WebElement oppName=driver.findElement(By.xpath("//label[text()='Opportunity Name']//following::input[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(oppName)).sendKeys("SRM Steels");
		
		WebElement type=driver.findElement(By.xpath("//label[text()='Type']//following::input[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(type)).click();
		
		WebElement newCustomer=driver.findElement(By.xpath("//span[text()='New Customer']"));
		wait.until(ExpectedConditions.elementToBeClickable(newCustomer)).click();
		
		WebElement leadSource=driver.findElement(By.xpath("//label[text()='Lead Source']//following::input[1]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", leadSource);
		Thread.sleep(2000); 		
		wait.until(ExpectedConditions.elementToBeClickable(leadSource)).click();
		
		WebElement partnerReferral=driver.findElement(By.xpath("//span[text()='Partner Referral']"));
		wait.until(ExpectedConditions.elementToBeClickable(partnerReferral)).click();
		
		WebElement amount=driver.findElement(By.xpath("//label[text()='Amount']//following::input[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(amount)).sendKeys("75000");	
		
		WebElement closeDate=driver.findElement(By.xpath("//label[text()='Close Date']//following::input[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(closeDate)).click();
		
		WebElement nextMonth=driver.findElement(By.xpath("//*[name()='svg' and @data-key='right']/ancestor::button"));
		wait.until(ExpectedConditions.elementToBeClickable(nextMonth)).click();
		
		WebElement selectDate=driver.findElement(By.xpath("//span[text()='20']"));
		wait.until(ExpectedConditions.elementToBeClickable(selectDate)).click();
		
		WebElement stage=driver.findElement(By.xpath("//label[text()='Stage']//following::input[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(stage)).click();
		
		WebElement needsAnalysis=driver.findElement(By.xpath("//span[text()='Needs Analysis' and @title='Needs Analysis']"));
		wait.until(ExpectedConditions.elementToBeClickable(needsAnalysis)).click();
		
		
		WebElement PCS=driver.findElement(By.xpath("//label[text()='Primary Campaign Source']//following::input[1]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", PCS);
		Thread.sleep(2000); 		
		wait.until(ExpectedConditions.elementToBeClickable(PCS)).click();
		
		WebElement PCSSelectFirstValue=driver.findElement(By.xpath("//li[@role='presentation'][2]"));
		wait.until(ExpectedConditions.elementToBeClickable(PCSSelectFirstValue)).click();
		
		WebElement save=driver.findElement(By.xpath("//button[text()='Save']"));
		wait.until(ExpectedConditions.elementToBeClickable(save)).click();
		
		WebElement createdText=driver.findElement(By.xpath("//slot[@name='primaryField']//lightning-formatted-text"));
		String createOrg=wait.until(ExpectedConditions.elementToBeClickable(createdText)).getText();
		
		if(createOrg.trim().equalsIgnoreCase("SRM Steels")){
			System.out.println("Test Case Passed");
		}else{
			System.out.println("Test Case Failed");
			}
		
		driver.close();
		
	}
		
		
		
	}

