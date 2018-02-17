package generic_Component;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class BaseClass {
	

	public static Process process;
	public static AndroidDriver driver;
	
	//starting the appium
	
	public static void Start_server() throws IOException, InterruptedException
	{
		 ProcessBuilder pb = new ProcessBuilder("D:\\Appium\\node.exe", "D:\\Appium\\node_modules\\appium\\bin\\appium.js");

	        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
	        pb.redirectError(ProcessBuilder.Redirect.INHERIT);
	        Process process = pb.start();
			
			if(process!=null)
			{
				System.out.println("Started the Appium Server");
			}
			else
			{
				System.out.println("NOT able Start the Server");
			}
			
			Thread.sleep(12000);
	}
	
	
	//Initializing the app
	
	public static void Init_App() throws InterruptedException, MalformedURLException
	{
		System.out.println("Inside Init app");
DesiredCapabilities capabilities= new DesiredCapabilities();
		
		//device details
		
		capabilities.setCapability("deviceName", "MI 3W");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "6.0.1");
				
				
		
		//app details
		capabilities.setCapability("appPackage","com.ebay.mobile");
		capabilities.setCapability("appActivity","com.ebay.mobile.activities.MainActivity");
		
		
		//appium server details
	 driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		Thread.sleep(8000);
	}

	//Explicit wait
	public void ExplicitWait(WebElement ele, long t)
	{
		WebDriverWait wait = new WebDriverWait(driver, t);
		wait.until(ExpectedConditions.visibilityOf(ele)).isDisplayed();
		System.out.println(ExpectedConditions.visibilityOf(ele));
	}
	
	//For Swipe
	public void verticalScroll()
	{
		Dimension size = driver.manage().window().getSize();
		int starty=(int)(size.height*0.90);
		int endy=(int)(size.height*0.20);
		int startx=(int)(size.width*0.50);
		driver.swipe(startx, starty, startx, endy, 1000);
	}
	
	
	//For Screenshot
	public void snapshot1(String TC_ID) throws IOException
	{
		Date date= new Date();
		SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		File file= new File(df.format(date)+".png");
		
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File("D:\\EbayFramework\\ScreenShot\\ "+TC_ID+"-"+file));
	}

//*********************************
	//Stop the server
	public static void Stop_server() throws InterruptedException
	{
		if(process!=null)
		{
			Thread.sleep(4000);
			process.destroy();
			System.out.println("Stopped the appium Server");
			
		}
		
	}
	
	
	
	

}
