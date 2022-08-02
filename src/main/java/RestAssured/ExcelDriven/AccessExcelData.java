package RestAssured.ExcelDriven;

import org.testng.annotations.Test;

public class AccessExcelData {

	@Test(priority=1,dataProvider = "testData",dataProviderClass = ExcelDataSupplier.class)
	
	public void getdata(String userName,String password) {
		
		System.out.println(userName);
		
	}
}
