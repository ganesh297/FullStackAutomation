package com.selenium.bootcamp;

import java.util.ArrayList;
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

public class TC005_AccountNameSorted {

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
		 WebElement accounts=driver.findElement(By.xpath("//a[@title='Accounts']"));
		 js.executeScript("arguments[0].click();", accounts);
		//wait.until(ExpectedConditions.elementToBeClickable(accounts)).click();
		
		WebElement clickOnAccountName=driver.findElement(By.xpath("//span[text()='Account Name']//parent::a"));
		wait.until(ExpectedConditions.elementToBeClickable(clickOnAccountName)).click();
		
		Thread.sleep(5000);
		
		ArrayList<String> al=new ArrayList<String>();
			
		int count;
		int totalsize;	
		int ite=1;
		WebElement element = null;
		
		
		for( ;;ite++){
			try{
			element = driver.findElement(By.xpath("(//a[contains(@data-recordid,'0015g00')])["+ite+"]"));
			}
			catch(Exception e){
				break;	
			}
			
			String n=element.getText();
			System.out.println(n);
			al.add(n);
			
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(1000); 
			
		}
		System.out.println(al);
		
		System.out.println("The Account Names are Sorted: "+checkSorting(al));
		
		driver.close();
		
	}
		
	static boolean checkSorting(ArrayList< String > arraylist){
	        boolean isSorted=true;
	        for(int i=1;i < arraylist.size();i++){
	            if(arraylist.get(i-1).compareTo(arraylist.get(i)) > 0){
	                isSorted= false;
	                break;
	            }
	        }
	        return isSorted;
	    }
		
}

