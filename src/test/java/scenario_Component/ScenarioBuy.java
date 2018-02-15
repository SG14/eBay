package scenario_Component;

import generic_Component.BaseClass;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject_Component.PageObjectCart;


public class ScenarioBuy extends BaseClass{

	public static Logger log = Logger.getLogger(ScenarioBuy.class);
	SoftAssert sAssert = new SoftAssert();
	
	@Test(dataProvider="dp_BuyNow",dataProviderClass=dataProvider_Component.DataProviderBuy.class)
	public void testBuyNow(Map buy) throws MalformedURLException, InterruptedException
	{
		String TC_ID = buy.get("TC_ID").toString();
		String Search_Item = buy.get("Search_Item").toString();
		String Exp_Result = buy.get("Exp_Result").toString();
		
//		Start_server();
		log.info("Executing The TestCase" + TC_ID);
		Init_App();
		
		
		PageObjectCart pob1 = new PageObjectCart(driver);
		//ExplicitWait(pob.eBaySearch, 25);
		
		
		pob1.clicksearch();
		
		ExplicitWait(pob1.ebaySearchtxtbox, 25);
		pob1.enterSearchvalue(Search_Item);
		
		ExplicitWait(pob1.searchresult, 25);
		List ele = pob1.sizeOfLoop();
		
		//for swipe action
		//This will handle any screen size.
		Dimension size = driver.manage().window().getSize();
		int starty=(int)(size.height*0.90);
		int endy=(int)(size.height*0.20);
		int startx=(int)(size.width*0.50);
		
		do
		{
			if (ele.size()>0)
				{
				pob1.clickShoe();
				log.info("Shoes found successfully, therefore breaking out of while loop");
				break;
				}
			
			
		driver.swipe(startx, starty, startx, endy, 1000);
		Thread.sleep(3000);
		}while((pob1.ShoesArePresent).isDisplayed());
	
		//To showcase screen Orientation/Rotation
		Thread.sleep(5000);
		
		driver.rotate(ScreenOrientation.LANDSCAPE);
		Thread.sleep(5000);
		driver.rotate(ScreenOrientation.PORTRAIT);
		Thread.sleep(3000);
		
		pob1.sDropdown();
		pob1.shoesize();
		pob1.buybtn();
		
		ExplicitWait(pob1.DeliveryAddress, 25);
		
		if(pob1.DeliveryAddress.equals(Exp_Result))
		{
			log.info("Delivery Address page found");
		}
		else
		{
			log.info("Expected result and actual result are not same");
			sAssert.fail("Failed due to expected and actual result not matching");
		}
		
	
	sAssert.assertAll();
	
	}
	
	
	
	
}
