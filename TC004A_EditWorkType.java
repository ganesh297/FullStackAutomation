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

public class TC004A_EditWorkType {

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
		
		WebDriverWait wait=new WebDriverWait(driver, 30);
		
		WebElement appLauncher=driver.findElement(By.xpath("//span[text()='App Launcher']//ancestor::button"));
		wait.until(ExpectedConditions.elementToBeClickable(appLauncher)).click();
		
		WebElement viewAll=driver.findElement(By.xpath("//h3[text()='Apps']//following::button[text()='View All']"));
		wait.until(ExpectedConditions.elementToBeClickable(viewAll)).click();		
		
		WebElement worktypes=driver.findElement(By.xpath("//p[text()='Work Types']//ancestor::a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", worktypes);
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", worktypes);
		
		WebElement actElement=driver.findElement(By.xpath("//span[text()='Show Actions']//ancestor::a"));
		wait.until(ExpectedConditions.elementToBeClickable(actElement));
		Thread.sleep(5000);
		js.executeScript("arguments[0].click();", actElement);
		
		WebElement edit=driver.findElement(By.xpath("//a[@title='Edit']"));
		wait.until(ExpectedConditions.elementToBeClickable(edit)).click();
		
		WebElement startTime=driver.findElement(By.xpath("//span[text()='Timeframe Start']//following::input"));
		wait.until(ExpectedConditions.elementToBeClickable(startTime)).sendKeys("9");
		
		WebElement endTime=driver.findElement(By.xpath("//span[text()='Timeframe End']//following::input"));
		wait.until(ExpectedConditions.elementToBeClickable(endTime)).sendKeys("18");
		
		WebElement save=driver.findElement(By.xpath("(//span[text()='Save']//parent::button)[2]"));
		wait.until(ExpectedConditions.elementToBeClickable(save)).click();
		
		WebElement errorMessage=driver.findElement(By.xpath("//span[contains(@class,'toastMessage slds')]"));
		String EM=wait.until(ExpectedConditions.elementToBeClickable(errorMessage)).getText();
		if(EM.contains(" was saved.")){
			System.out.println("Test Case Passed");
			
		}
		else{
			
			System.out.println("Test Case Failed");
		}
		driver.close();
		
	}
		
		
		
}

