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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC003_EditTask {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "D:\\Webdrivers\\chromedriver.exe");
		ChromeOptions options=new ChromeOptions();
		options.addArguments("—disable-notifications");
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
		
		WebElement viewAll=driver.findElement(By.xpath("//h3[text()='Apps']//following::button[text()='View All']"));
		wait.until(ExpectedConditions.elementToBeClickable(viewAll)).click();		
		
		
		
		WebElement sales=driver.findElement(By.xpath("//p[text()='Sales']"));
		wait.until(ExpectedConditions.elementToBeClickable(sales)).click();
				Thread.sleep(5000);
				
				 JavascriptExecutor js = (JavascriptExecutor)driver;	
		WebElement tasks=driver.findElement(By.xpath("//a[@title='Tasks']"));
		 js.executeScript("arguments[0].click();", tasks);
		//wait.until(ExpectedConditions.elementToBeClickable(tasks)).click();
		
		WebElement status=driver.findElement(By.xpath("//span[text()='Waiting on someone else']"));
		wait.until(ExpectedConditions.elementToBeClickable(status)).click();
		
		Actions action = new Actions(driver);
		action.moveToElement(status).build().perform();
		
		WebElement edit=driver.findElement(By.xpath("(//button[contains(@class,'test-id__inline-edit-trigger')])[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(edit)).click();
		
		WebElement date=driver.findElement(By.xpath("//input[@class='inputDate input']"));
		wait.until(ExpectedConditions.elementToBeClickable(date)).click();
		
		WebElement todayDate=driver.findElement(By.xpath("//button[text()='Today']"));
		wait.until(ExpectedConditions.elementToBeClickable(todayDate)).click();
		
		WebElement priority=driver.findElement(By.xpath("(//a[@class='select' and @role='button'])[2]"));
		 js.executeScript("arguments[0].click();", priority);
		//wait.until(ExpectedConditions.elementToBeClickable(priority)).click();
		
		WebElement low=driver.findElement(By.xpath("//a[@title='Low']"));
		wait.until(ExpectedConditions.elementToBeClickable(low)).click();
		
		WebElement save=driver.findElement(By.xpath("//span[text()='Save']/parent::button"));
		wait.until(ExpectedConditions.elementToBeClickable(save)).click();
		
		WebElement taskName=driver.findElement(By.xpath("(//div[text()='Task']//following::span[@class='uiOutputText'])[1]"));
		String TN=wait.until(ExpectedConditions.elementToBeClickable(taskName)).getText();
		
		System.out.println(TN+" is updated successfully");
		
		driver.close();
		
	}
		
		
		
	}

