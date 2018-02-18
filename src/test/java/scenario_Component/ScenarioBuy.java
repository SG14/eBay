package scenario_Component;

import generic_Component.BaseClass;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject_Component.PageObjectCart;
import pageObject_Component.PageObjectSearch;



public class ScenarioBuy extends BaseClass{
  
	
	//Logger is for Log4j report
	public static Logger log = Logger.getLogger(ScenarioBuy.class);
	
	//Object Ref variable for PageObject component - class
	PageObjectCart cartpageObject = new PageObjectCart(driver);

	
	//Method for Buying the item
	@Test(dataProvider="dp_BuyNow",dataProviderClass=dataProvider_Component.DataProviderBuy.class,groups={"eBay"})
	public void testBuyNow(Map buy) throws InterruptedException, IOException
	{
		//Collecting the data from the Map
		String testCaseId = buy.get("TC_ID").toString();
		String Search_Item = buy.get("Search_Item").toString();
		String Exp_Result = buy.get("Exp_Result").toString();
		
		
					
		
		
		//start the appium server
		Start_server();
		log.info("Executing The TestCase" + testCaseId);
		Init_App();
		
		
		System.out.println("Search for the shoes");
		searchShoe1(Search_Item);
		
		System.out.println("Click on the desired shoe");
		clickOnTheDesiredShoe();
		
		System.out.println("Screen Rotation show cased");
		ScreenRotation();
		
		System.out.println("Clicked on buy Button");
		buyShoe();
		
		validateDeliveryAddressScreen(Exp_Result,testCaseId);
	    Stop_server();
	
	}
	
	
	
	public void searchShoe1(String Search_Item)
	{
		
		
		
		PageObjectCart cartpageObject1 = new PageObjectCart(driver);
		
		
		//click on the search text box in eBay
		cartpageObject1.clicksearch();      					
		ExplicitWait(cartpageObject1.ebaySearchtxtbox, 25);
		
		
		 
		
		//enter the search vale and click on search button
		cartpageObject1.enterSearchvalue(Search_Item);         
		ExplicitWait(cartpageObject1.result, 25);
		
		 
	}
	
	public void clickOnTheDesiredShoe()
	{
		PageObjectCart cartpageObject2 = new PageObjectCart(driver);
		ExplicitWait(cartpageObject2.result, 30);
		
		//saving all the items matching the search criteria, ideally there will be 1
		List<WebElement> sizeOfLoop = cartpageObject2.sizeOfLoop();
		
		
		do{
			if((sizeOfLoop.size())>0)
			{   log.info("Shoes found successfully, therefore breaking out of while loop");
			cartpageObject2.clickShoe();
				break;
			}
			
			//Vertical Scroll till the desired item is found
			verticalScroll();
			
		}while((cartpageObject2.ShoesArePresent).isDisplayed());
		
		
	}
	
	public void ScreenRotation() throws InterruptedException
	{   //This is just to show case screen Orientation, therefore used Thread.sleep. In Real time use Implicit/Explicit wait
		Thread.sleep(5000);
		driver.rotate(ScreenOrientation.LANDSCAPE);
		Thread.sleep(5000);
		driver.rotate(ScreenOrientation.PORTRAIT);
		Thread.sleep(3000);
	}
	
	public void buyShoe()
	{
		PageObjectCart cartpageObject3 = new PageObjectCart(driver);
		cartpageObject3.sDropdown(); 
		cartpageObject3.shoesize(); 
		cartpageObject3.buybtn(); 
		
	}
	
	public void validateDeliveryAddressScreen(String Exp_Result, String testCaseId) throws IOException, InterruptedException
	{
		PageObjectCart cartpageObject4 = new PageObjectCart(driver);
        ExplicitWait(cartpageObject4.deliveryAddress, 40); 
     
        System.out.println(cartpageObject4.getaddress());
        snapshot1(testCaseId, driver);
      //Check if this page is the expected page as per excel sheet
        try{
        	 Assert.assertEquals(cartpageObject4.getaddress(), Exp_Result, "Expected result = "+ Exp_Result +" and actual result =" +cartpageObject4.getaddress() +", Hence Fail");
        }
        catch(AssertionError e){
        	
        	snapshot1(testCaseId, driver);
        	throw e;
        }
        finally{
			System.out.println("Finally block starts here");
			Stop_server();}

	}
}
