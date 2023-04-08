package com.crm.qa.base;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


import com.beust.jcommander.converters.PathConverter;

import io.github.bonigarcia.wdm.WebDriverManager;
public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public static Logger logger;
	
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
	
	
	

	
	
	
	public TestBase() throws IOException{
		try {
			
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/qa"
					+ "/crm/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound");
		}
	}
	
	
	
		
		public static void initialization() throws Exception {
			String browserName = prop.getProperty("browser");
			
			if(browserName.equals("chrome")){
				
			WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(); 
		
			}
			
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			
			
			driver.get(prop.getProperty("url"));
	
	}

		public String getScreenshot() {
			File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
			File destination = new File(path);
			try {
				FileUtils.copyFile(src, destination);
			} catch (IOException e) {
				System.out.println("Capture Failed " + e.getMessage());
			}
			return path;
			
			
		}

/*
		@AfterMethod
		public void tearDown()
		{
			driver.close();
		}
	*/	
		
		
		

		public static WebDriver getDriver() {
			return tdriver.get();
		}
}
