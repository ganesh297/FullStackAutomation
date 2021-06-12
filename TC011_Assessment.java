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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC011_Assessment {

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

		//Click Service Console from Content App
		WebElement serviceConsole=driver.findElement(By.xpath("//p[text()='Service Console']"));
		wait.until(ExpectedConditions.elementToBeClickable(serviceConsole)).click();
		Thread.sleep(5000);

		//Finding Home is Selected
		WebElement whetherHomeIsSelected=driver.findElement(By.xpath("//span[@title='Service Console']//following::span[@class='slds-truncate']"));
		String homeText=wait.until(ExpectedConditions.elementToBeClickable(whetherHomeIsSelected)).getText();
		Thread.sleep(5000);
		
		if(!homeText.trim().contains("Home")){
		
		//Click on Drop Down
		WebElement scDropDownSelect=driver.findElement(By.xpath("//*[name()='svg' and @data-key='chevrondown']/ancestor::button"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scDropDownSelect);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", scDropDownSelect);


		WebElement home=driver.findElement(By.xpath("//span[text()='Home']"));
		wait.until(ExpectedConditions.elementToBeClickable(home)).click();

		}

		WebElement closed=driver.findElement(By.xpath("//span[text()='Closed']//following-sibling::span[@class='metricAmount uiOutputText']"));
		String closedText=wait.until(ExpectedConditions.elementToBeClickable(closed)).getText();
		String closedTextOnlyValue= closedText.replaceAll("[^0-9]", "");  
		System.out.println(closedTextOnlyValue);
		
		WebElement open=driver.findElement(By.xpath("//span[contains(text(),'Open')]//following-sibling::span[@class='metricAmount uiOutputText']"));
		String openText=wait.until(ExpectedConditions.elementToBeClickable(open)).getText();
		String openTextOnlyValue= openText.replaceAll("[^0-9]", "");  
		System.out.println(openTextOnlyValue);
		
		int closedValue=Integer.parseInt(closedTextOnlyValue);
		int openValue=Integer.parseInt(openTextOnlyValue);
		
		int total=closedValue+openValue;
		if(total<10000){
			WebElement editForGoal=driver.findElement(By.xpath("//span[text()='Goal']//following::*[name()='svg' and @data-key='edit']/ancestor::button"));
			wait.until(ExpectedConditions.elementToBeClickable(editForGoal)).click();
			
			WebElement setGoal=driver.findElement(By.xpath("//input[@class='input uiInputSmartNumber uiInput uiInput--default uiInput--input']"));
			wait.until(ExpectedConditions.elementToBeClickable(setGoal)).clear();
			setGoal.sendKeys("10000");
			
			WebElement save=driver.findElement(By.xpath("//span[text()='Save']//parent::button"));
			wait.until(ExpectedConditions.elementToBeClickable(save)).click();
			
			}
		
		
		WebElement scDropDownSelect=driver.findElement(By.xpath("//*[name()='svg' and @data-key='chevrondown']/ancestor::button"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scDropDownSelect);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", scDropDownSelect);


		WebElement dashboard=driver.findElement(By.xpath("//span[text()='Dashboards']"));
		wait.until(ExpectedConditions.elementToBeClickable(dashboard)).click();
		
		WebElement newDashboard=driver.findElement(By.xpath("//div[text()='New Dashboard']//parent::a"));
		wait.until(ExpectedConditions.elementToBeClickable(newDashboard)).click();
		
		int frameSize=driver.findElements(By.tagName("iframe")).size();
		System.out.println(frameSize);
		
		//driver.switchTo().frame(0);
		
		List<WebElement> frameNames=driver.findElements(By.tagName("iframe"));
		for(WebElement frame:frameNames){
			String FN=frame.getAttribute("name");
			if(FN.contains("sfxdash")){
				driver.switchTo().frame(FN);
				break;
			}
		}
		
		String DashboardName="Ganesh_Workout";
		WebElement newDashboardInput=driver.findElement(By.xpath("//input[@id='dashboardNameInput']"));
		wait.until(ExpectedConditions.elementToBeClickable(newDashboardInput)).sendKeys(DashboardName);
		
		WebElement folderInput=driver.findElement(By.xpath("//input[@id='folderInput']"));
		String FV=wait.until(ExpectedConditions.elementToBeClickable(folderInput)).getAttribute("value");
		
		if(!FV.trim().equalsIgnoreCase("Private Dashboards")){
			WebElement selectFolder=driver.findElement(By.xpath("//button[text()='Select Folder']"));
			wait.until(ExpectedConditions.elementToBeClickable(selectFolder)).click();
			
			WebElement privateDashboard=driver.findElement(By.xpath("//button[text()='Private Dashboards']"));
			wait.until(ExpectedConditions.elementToBeClickable(privateDashboard)).click();
			
			WebElement selectedFolder=driver.findElement(By.xpath("//button[text()='Cancel']//following::button[text()='Select Folder']"));
			wait.until(ExpectedConditions.elementToBeClickable(selectedFolder)).click();
			
		
		}
		
		
		WebElement createButton=driver.findElement(By.xpath("//button[text()='Create']"));
		wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
		
		
		int frameSize1=driver.findElements(By.tagName("iframe")).size();
		System.out.println(frameSize1);
		
		driver.switchTo().frame(0);
		WebElement done=driver.findElement(By.xpath("//button[text()='Done']"));
		wait.until(ExpectedConditions.elementToBeClickable(done)).click();
		
		
		WebElement dashboardText=driver.findElement(By.xpath("//span[@class='slds-page-header__title slds-truncate']"));
		String DT=wait.until(ExpectedConditions.elementToBeClickable(dashboardText)).getText();
		
		if(DashboardName.equalsIgnoreCase(DT)){
			System.out.println("Dashboard is properly created");
			
		}else{
			System.out.println("Dashboard is not properly created");
		}
		
			
		WebElement subscribe=driver.findElement(By.xpath("//button[text()='Subscribe']"));
		wait.until(ExpectedConditions.elementToBeClickable(subscribe)).click();
		
		try{
		int frameSize2=driver.findElements(By.tagName("iframe")).size();
		System.out.println(frameSize2);
		
		driver.switchTo().frame(0);
		}catch(Exception e){
			
		}
		WebElement daily=driver.findElement(By.xpath("//span[text()='Daily']//preceding-sibling::input"));
		wait.until(ExpectedConditions.elementToBeClickable(daily)).click();
		
		Select select=new Select(driver.findElement(By.xpath("//select[@id='time']")));
		select.selectByVisibleText("10:00 AM");
		
		WebElement saveInSub=driver.findElement(By.xpath("//span[text()='Save']//parent::button"));
		wait.until(ExpectedConditions.elementToBeClickable(saveInSub)).click();
		
		WebElement toastMessage=driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]"));
		String getToastMessageText=wait.until(ExpectedConditions.elementToBeClickable(toastMessage)).getText();
		
		if(getToastMessageText.equalsIgnoreCase("Your subscription is all set.")){
			
			System.out.println("Toast Message is displayed properly");
		}
		else{
			System.out.println("Toast Message is not displayed properly");
		}
		
		
		WebElement closeDashboard=driver.findElement(By.xpath("//span[text()='"+DashboardName+"']//following::*[name()='svg' and @data-key='close']/ancestor::button"));
		wait.until(ExpectedConditions.elementToBeClickable(closeDashboard)).click();
		
		
		WebElement selectPrivateDashboard=driver.findElement(By.xpath("//a[@title='Created by Me']//following::a[@title='Private Dashboards']"));
		wait.until(ExpectedConditions.elementToBeClickable(selectPrivateDashboard)).click();
		
		WebElement searchDashboard=driver.findElement(By.xpath("//input[contains(@class,'search-text-field slds-input input')]"));
		wait.until(ExpectedConditions.elementToBeClickable(searchDashboard)).sendKeys(DashboardName);
		
		Thread.sleep(5000);
		
		int privateDashboardCount=driver.findElements(By.xpath("//span[text()='"+DashboardName+"']")).size();
		if(privateDashboardCount>0){
			System.out.println("Successfully created");
		
		}
		
		WebElement arrow=driver.findElement(By.xpath("//*[name()='svg' and @data-key='check']//following::*[name()='svg' and @data-key='down']/ancestor::button"));
		wait.until(ExpectedConditions.elementToBeClickable(arrow)).click();
		
		WebElement clickDelete=driver.findElement(By.xpath("//span[text()='Delete']"));
		wait.until(ExpectedConditions.elementToBeClickable(clickDelete)).click();
		
		WebElement confirmDelete=driver.findElement(By.xpath("//span[text()='Cancel']//following::span[text()='Delete']"));
		wait.until(ExpectedConditions.elementToBeClickable(confirmDelete)).click();
		
		WebElement deleteToastMessage=driver.findElement(By.xpath("//span[contains(@class,'toastMessage slds-text-heading')]"));
		String getdeleteToastMessage=wait.until(ExpectedConditions.elementToBeClickable(deleteToastMessage)).getText();
		
		if(getdeleteToastMessage.trim().contains("Dashboard  was deleted.")){
			
			System.out.println("Dashboard deleted successfully");
		}
		else{
			System.out.println("Dashboard not deleted successfully");
			
		}
		
		
		driver.close();

	
		
		
		}
		
		
		
		

}




