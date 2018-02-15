package dataProvider_Component;

import generic_Component.ExcelReadWrite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.testng.annotations.DataProvider;

public class DataProviderBuy {
	
	//getting the search data (valid)
		@DataProvider(name="dp_BuyNow")
		public static Iterator<Object[]> getBuyNowData() throws IOException
		{
			List<Object[]> obj = flagRowCount("BuyNow");
			return obj.iterator();
		}
		
		//getting the search data (Invalid)
//		public void getBuyNowData2()
//		{
//			
//		}
		
		
		//Common Method for valid and invalid data to read from the Excel File
		public static List<Object[]> flagRowCount(String scriptname) throws IOException
		{
			ExcelReadWrite xl = new ExcelReadWrite("D:\\EbayFramework\\TestData\\TestData.xls");
			HSSFSheet Sheet2 = xl.Setsheet("Sheet2");
			int getrowcount = xl.getrowcount(Sheet2);
			int colcount = xl.getcolcount(Sheet2, 0);
			
			List<Object[]> arr_list = new ArrayList<Object[]>();
			
			for(int i = 1;i<=getrowcount;i++)
			{
				String Search_Item = xl.Readvalue(Sheet2, i, "Search_Item");
				String Exp_Result = xl.Readvalue(Sheet2, i, "Exp_Result");
				String Script_name = xl.Readvalue(Sheet2, i, "Script_name");
				
				
				if(Script_name.equals(scriptname))
				{
					Map<String, String> dmap = new HashMap<String, String>();
					for(int j=0;j<colcount;j++)
					{
						String key = xl.Readvalue(Sheet2, 0, j);
						String value = xl.Readvalue(Sheet2, i, j);
						dmap.put(key, value);
						
					}
					
					Object[] x = new Object[1];
					x[0]=dmap;
					arr_list.add(x);
				}
				
			
		}
			return arr_list;

	}

}
