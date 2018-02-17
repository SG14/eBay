package pageObject_Component;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;

public class PageObjectSearch {
	
	public AppiumDriver driver;
	
	//1st Section - Initialize Page factory
		public PageObjectSearch(AppiumDriver driver)
		{
			PageFactory.initElements(driver, this);
		}
		
		
	
	//2nd Section - Collect the elements using @FindBy annotation
	@FindBy(id="com.ebay.mobile:id/search_box")
	public WebElement eBaySearch;
	
	
	@FindBy(id="com.ebay.mobile:id/search_src_text")
	public WebElement ebaySearchtxtbox;
	
	@FindBy(id="com.ebay.mobile:id/search_src_text")
	public WebElement searchresult;
	
	@FindBy(id="com.ebay.mobile:id/textview_item_count")
	public WebElement result;
	
	
	
	//3rd Section - performing action on the elements collected
	public void clicksearch()
	{   
		eBaySearch.click();
	}
	public void enterSearchvalue(String value)
	{
		ebaySearchtxtbox.sendKeys(value+"\n");
	}
	
	
	public String getsearchresultcount()
	{
			System.out.println(result.getText());
		return result.getText();
	}
	

}
