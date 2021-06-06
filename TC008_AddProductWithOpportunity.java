package com.selenium.bootcamp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC008_AddProductWithOpportunity {

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

		//Click View All Key Deals in Key Deals
		WebElement keyDeals=driver.findElement(By.xpath("//span[text()='View All Key Deals']//parent::a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", keyDeals);
		Thread.sleep(3000);
		js.executeScript("arguments[0].click();", keyDeals);

		//wait.until(ExpectedConditions.elementToBeClickable(keyDeals)).click();


		WebElement opp=driver.findElement(By.xpath("//*[name()='svg' and @data-key='chevrondown']/ancestor::a"));
		wait.until(ExpectedConditions.elementToBeClickable(opp)).click();


		//Click the dropdown from Opportunities and select All Opportunities
		WebElement allOpp=driver.findElement(By.xpath("//span[text()='All Opportunities']//ancestor::a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", allOpp);
		Thread.sleep(3000);
		js.executeScript("arguments[0].click();", allOpp);
		//wait.until(ExpectedConditions.elementToBeClickable(allOpp)).click();

		Thread.sleep(5000);

		//Give SRM Steels in Search Box and search
		WebElement searchBox=driver.findElement(By.xpath("//input[@name='Opportunity-search-input']"));
		wait.until(ExpectedConditions.elementToBeClickable(searchBox)).sendKeys("SRM Steels");
		wait.until(ExpectedConditions.elementToBeClickable(searchBox)).click();
		searchBox.sendKeys(Keys.ENTER);

		Thread.sleep(5000);

		//Click on the SRM Steels under Opportunity Name
		WebElement oppName=driver.findElement(By.linkText("SRM Steels"));
		wait.until(ExpectedConditions.elementToBeClickable(oppName)).click();

		//Click on  dropdown of Products under Related and select Add Products
		WebElement addProductDownArrow=driver.findElement(By.xpath("//span[text()='Products']//following::a"));
		wait.until(ExpectedConditions.elementToBeClickable(addProductDownArrow)).click();

		WebElement addProducts=driver.findElement(By.xpath("//div[text()='Add Products']//parent::a"));
		wait.until(ExpectedConditions.elementToBeClickable(addProducts)).click();

		WebElement addProd=driver.findElement(By.xpath("//h2[text()='Add Products']"));
		wait.until(ExpectedConditions.elementToBeClickable(addProd)).click();

		//Click on List Price to sort the result and select the highest priced product
		WebElement sortDesc=driver.findElement(By.xpath("//span[text()='List Price']//ancestor::a"));
		wait.until(ExpectedConditions.elementToBeClickable(sortDesc)).click();

		Thread.sleep(5000);

		WebElement sortDesc1=driver.findElement(By.xpath("//span[text()='List Price']//ancestor::a"));
		wait.until(ExpectedConditions.elementToBeClickable(sortDesc1)).click();
		Thread.sleep(5000);

		//Click Next and give product Quantity as 560, click Save
		WebElement highestPrice=driver.findElement(By.xpath("//span[text()='List Price']//following::span[@data-aura-class='forceOutputCurrency']"));
		String HP=wait.until(ExpectedConditions.elementToBeClickable(highestPrice)).getText();
		System.out.println("Highest Price: "+HP);

		WebElement PN=driver.findElement(By.xpath("(//span[text()='Select item 1']//preceding-sibling::input)[2]//parent::label"));
		//js.executeScript("arguments[0].click();", PN);
		wait.until(ExpectedConditions.elementToBeClickable(PN)).click();

		WebElement nextButton=driver.findElement(By.xpath("//span[text()='Next']//parent::button"));
		wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();

		WebElement editButton=driver.findElement(By.xpath("(//span[text()='Quantity']//following::button)[2]"));
		wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();

		Thread.sleep(5000);

		WebElement editInput=driver.findElement(By.xpath("//input[@class='slds-grow input uiInputSmartNumber']"));
		wait.until(ExpectedConditions.elementToBeClickable(editInput)).sendKeys("560");

		WebElement saveButton=driver.findElement(By.xpath("(//span[text()='Save']//parent::button)[2]"));
		wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();

		//Verify the Sales Price and Product Name
		WebElement TM=driver.findElement(By.xpath("//span[text()='1  record was updated.']"));
		String toastMessage=wait.until(ExpectedConditions.elementToBeClickable(TM)).getText();
		System.out.println(toastMessage);

		if(toastMessage.equals("1 record was updated.")){
			System.out.println("Test Case Passed");

		}

		else{

			System.out.println("Test Case Failed");
		}		driver.close();

	}


}

