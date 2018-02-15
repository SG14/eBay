package scenario_Component;

import generic_Component.BaseClass;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject_Component.PageObjectSearch;


public class ScenarioSearch extends BaseClass {
	
	public static Logger log = Logger.getLogger(ScenarioBuy.class);
	SoftAssert sAssert = new SoftAssert();

	@Test(dataProvider="dp_validSearch",dataProviderClass=dataProvider_Component.DataProviderSearch.class)
	public void testValidSearch(Map Search ) throws IOException, InterruptedException
	{
		String TC_ID = Search.get("TC_ID").toString();
		String Search_Item = Search.get("Search_Item").toString();
		String Exp_Result = Search.get("Exp_Result").toString();
		
//		Start_server();
		log.info("Shoes found successfully, therefore breaking out of while loop");
		Init_App();
		
		
		PageObjectSearch pob = new PageObjectSearch(driver);
		//ExplicitWait(pob.eBaySearch, 25);
		
		
		pob.clicksearch();
		
		ExplicitWait(pob.ebaySearchtxtbox, 25);
		pob.enterSearchvalue(Search_Item);
		
		ExplicitWait(pob.searchresult, 25);
		String shoeCount = pob.getsearchresultcount();
		
		if(shoeCount.equals(Exp_Result))
		{
			log.info("Expected result and Actual result are same");
		}
		else
		{
			log.info("Expected result and actual result are not same");
		}
		sAssert.assertAll();
	}
	
	
//	public void testInvalidSearch()
//	{
//		
//	}
	
	
	
	
	
}
