package com.selenium.bootcamp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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

public class TC006_LearnTheSkills {

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

		//Click on Learn More link in Mobile Publisher
		WebElement MobilePublisherLearnMore=driver.findElement(By.xpath("//span[text()='Learn More']//parent::button"));
		wait.until(ExpectedConditions.elementToBeClickable(MobilePublisherLearnMore)).click();

		Set<String> windowIDs=driver.getWindowHandles();
		List<String> windowIDList=new ArrayList(windowIDs);

		for(String winid: windowIDList)
		{
			String title=driver.switchTo().window(winid).getTitle();
			if(title.contains("Create and Publish Custom-Branded Mobile Apps - Salesforce.com")){
				break;

			}
		}

		// MouseHover On Resources
		WebElement resources=driver.findElement(By.xpath("//span[text()='Resources']//parent::button"));
		wait.until(ExpectedConditions.elementToBeClickable(resources));

		Actions actions=new Actions(driver);
		actions.moveToElement(resources).build().perform();

		//Select SalesForce Certification Under Learnings
		WebElement salesForceCertifications=driver.findElement(By.xpath("//span[contains(text(),'Salesforce Certification ')]//parent::a"));
		wait.until(ExpectedConditions.elementToBeClickable(salesForceCertifications)).click();		

		Set<String> windowIDs2=driver.getWindowHandles();
		List<String> windowIDList2=new ArrayList(windowIDs2);

		for(String winid: windowIDList2)
		{
			String title=driver.switchTo().window(winid).getTitle();
			if(title.contains("Certification - Administrator Overview")){
				break;

			}
		}

		//Get the List of Certifications Available
		List<WebElement> certifications=driver.findElements(By.xpath("//img[@class='cs-card_image']"));
		if(certifications.size()>0){
			System.out.println("Certifications are available");

			//Verify Whether Administrator Certification is Available
			List<WebElement> adminCertifications=driver.findElements(By.xpath("//a[text()='Administrator']"));
			if(adminCertifications.size()>0){

				//Click on Administrator Certification 
				driver.findElement(By.xpath("//a[text()='Administrator']")).click();

				List<WebElement> classAndWorkshops=driver.findElements(By.xpath("//ul[@class='bullets Fz(18px) Lh(1.5) Fw(l)']/li/a"));

				//Under Learn The Skills  -  Get the Text of Classes and Workshops
				for(WebElement classAndWorkshop:classAndWorkshops){
					System.out.println(classAndWorkshop.getText());
				}
				String pageTitle=driver.getTitle();

				//Get the Title of the Page and Verify the Title
				if(pageTitle.contains("Certification - Administrator")){
					System.out.println("Test Case Passed");

				}else{

					System.out.println("Test Case Failed");
				}

			}


		}else{

			System.out.println("Certifications are not available");
		}

		driver.quit();

	}


}

