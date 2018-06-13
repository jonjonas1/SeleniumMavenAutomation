package com.dice;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import javax.management.RuntimeErrorException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

//Java+maven+selenium+git+github
public class DiceJobSearch {
	public static void main(String[] args) throws InterruptedException {
		//SetUp chrome driver path
		WebDriverManager.chromedriver().setup();
		
		//invoke selenium webdriver
		ChromeDriver driver = new ChromeDriver();
		//full screen
		driver.manage().window().maximize(); //or . fullscreen();
		//set universal wait time
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				//launch browser and navigate to webpage. 
		String url="https://www.dice.com";
		driver.get(url);
		
		String actualTitle=driver.getCurrentUrl();
		String expectedTitle="Job Search for Technology Professionals | Dice.com";
		
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("Pass \t Dice homepage successfully loaded");
		}else {
			System.out.println("Fail \t not loaded");
			throw new RuntimeException("Fail \t not loaded");
		}
		
		String keyword="java developer";
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys("Java developer");
		
		String location="78754";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		
		driver.findElement(By.id("findTechJobs")).click();
		
		String count = driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);
		
		int countResult = Integer.parseInt(count.replace(",",""));
		
		if(countResult>0) {
			System.out.println("step Pass: keyword: "+keyword+" search return"+ 
			countResult+" results in"+ location);
		}else {
			System.out.println("Step fail keyword: "+keyword+" search return"+ 
					countResult+" results in"+ location);
		}
		
		
		Thread.sleep(7000);
		driver.close();
		System.out.println("Test completed:" +LocalDateTime.now());
		
	}
	
	

}
