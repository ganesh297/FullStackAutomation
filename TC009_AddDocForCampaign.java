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
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC009_AddDocForCampaign {

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

		//Click Sales from App Launcher
		WebElement sales=driver.findElement(By.xpath("//p[text()='Sales']"));
		wait.until(ExpectedConditions.elementToBeClickable(sales)).click();
		Thread.sleep(5000);

		//Click on Campaigns tab 
		WebElement campaigns=driver.findElement(By.xpath("//span[text()='Campaigns']//parent::a"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", campaigns);

		//wait.until(ExpectedConditions.elementToBeClickable(campaigns)).click();

		//Click Bootcamp link
		WebElement bootCampLink=driver.findElement(By.xpath("//span[text()='Campaign Name']//following::a[text()='Bootcamp']"));
		wait.until(ExpectedConditions.elementToBeClickable(bootCampLink)).click();


		WebElement uploadButton=driver.findElement(By.xpath("//div[text()='Upload Files']//parent::a"));
		wait.until(ExpectedConditions.elementToBeClickable(uploadButton)).click();

		Thread.sleep(5000);

		String filePath="D:\\sample.pdf";
		//Runtime.getRuntime().exec("D:\\fileupload.exe"+" "+ "D:\\sample.pdf");


		StringSelection strSelection = new StringSelection(filePath);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(strSelection, null);

		Robot robot = new Robot();

		robot.delay(300);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(200);
		robot.keyRelease(KeyEvent.VK_ENTER);

		Path path = Paths.get(filePath);		
		Path fileName = path.getFileName();

		String exactFileName=fileName.toString();
		System.out.println(exactFileName);

		WebElement uploadedFileName=driver.findElement(By.xpath("//div[@class='fileName slds-truncate']"));
		String UFN=wait.until(ExpectedConditions.elementToBeClickable(uploadedFileName)).getText();

		if(UFN.equals(exactFileName)){
			System.out.println("Test Case Passed");

		}
		else{			
			System.out.println("Test Case Failed");
		}

		WebElement doneButton=driver.findElement(By.xpath("//span[text()='Done']//parent::button"));
		wait.until(ExpectedConditions.elementToBeClickable(doneButton)).click();

		driver.close();

	}


}

