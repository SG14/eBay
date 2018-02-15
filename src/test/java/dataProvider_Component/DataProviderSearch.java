package dataProvider_Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import generic_Component.ExcelReadWrite;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.testng.annotations.DataProvider;

public class DataProviderSearch {
	
	//getting the search data (valid)
	@DataProvider(name="dp_validSearch")
	public static Iterator<Object[]> getValidSearchData() throws IOException
	{
		List<Object[]> obj = flagRowCount("ValidSearch");
		return obj.iterator();
	}
	
//	//getting the search data (Invalid)
//	public void getInValidSearchData()
//	{
//		
//	}
	
	
	//Common Method for valid and invalid data to read from the Excel File
	public static List<Object[]> flagRowCount(String scriptname) throws IOException
	{
		ExcelReadWrite xl = new ExcelReadWrite("D:\\EbayFramework\\TestData\\TestData.xls");
		HSSFSheet Sheet1 = xl.Setsheet("Sheet1");
		int getrowcount = xl.getrowcount(Sheet1);
		int colcount = xl.getcolcount(Sheet1, 0);
		
		List<Object[]> arr_list = new ArrayList<Object[]>();
		
		for(int i = 1;i<=getrowcount;i++)
		{
			String Search_Item = xl.Readvalue(Sheet1, i, "Search_Item");
			String Exp_Result = xl.Readvalue(Sheet1, i, "Exp_Result");
			String Script_name = xl.Readvalue(Sheet1, i, "Script_name");
			
			
			if(Script_name.equals(scriptname))
			{
				Map<String, String> dmap = new HashMap<String, String>();
				for(int j=0;j<colcount;j++)
				{
					String key = xl.Readvalue(Sheet1, 0, j);
					String value = xl.Readvalue(Sheet1, i, j);
					dmap.put(key, value);
					
				}
				
				Object[] x = new Object[1];
				x[0]=dmap;
				arr_list.add(x);
			}
			
		
	}
		return arr_list;

}}
