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

public class TC002_CreateTask {

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
		
		WebElement globalActions=driver.findElement(By.xpath("//*[name()='svg' and @data-key='add']/ancestor::a"));
		wait.until(ExpectedConditions.elementToBeClickable(globalActions)).click();
		
		WebElement newTask=driver.findElement(By.xpath("//span[text()='New Task']//ancestor::a"));
		wait.until(ExpectedConditions.elementToBeClickable(newTask)).click();
		
		WebElement subject=driver.findElement(By.xpath("//label[text()='Subject']//following::input[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(subject)).sendKeys("Bootcamp");
		
		String name="Sarath M";
		
		WebElement searchContacts=driver.findElement(By.xpath("//input[@title='Search Contacts']"));
		wait.until(ExpectedConditions.elementToBeClickable(searchContacts)).sendKeys(name);
		
		/*int searchSize=driver.findElements(By.xpath("//mark[@class='data-match']")).size();
		List<WebElement> s=driver.findElements(By.xpath("//mark[@class='data-match']"));	
		for(int i=0;i<searchSize;i++){
			String text=s.get(i).getText();
			if(text.equals("Sarath M")){
				s.get(i).sendKeys(Keys.ENTER);
				break;
			}
			
		}*/
		
	
		try{
		WebElement textFind=driver.findElement(By.xpath("//div[@title='"+name+"']"));
		wait.until(ExpectedConditions.elementToBeClickable(textFind)).click();
		}
		catch(Exception e){
			System.out.println("Search Text is not Found");
		}
		
		
		WebElement status=driver.findElement(By.xpath("//span[text()='Status']//following::a"));
		wait.until(ExpectedConditions.elementToBeClickable(status)).click();		
		
		WebElement selectValueInStatus=driver.findElement(By.xpath("//a[@title='Waiting on someone else']"));
		wait.until(ExpectedConditions.elementToBeClickable(selectValueInStatus)).click();
		
		WebElement save=driver.findElement(By.xpath("(//span[text()='Save']/parent::button)[2]"));
		wait.until(ExpectedConditions.elementToBeClickable(save)).click();
		
		WebElement toastMessage=driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]"));
		String TM=wait.until(ExpectedConditions.elementToBeClickable(toastMessage)).getText();
		
		System.out.println(TM);
		
		if(TM.equalsIgnoreCase("Task Bootcamp was created.")){
			System.out.println("Test Case Passed");
		}else{
			System.out.println("Test Case Failed");
		}
		
		driver.close();
		
	}
		
		
		
	}

