package RestAssured.ExcelDriven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelDataSupplier {

	@DataProvider(name="testData")
	public String[][] getData() throws IOException {
		
		File excelfile = new File("./src/main/java/resourse/Daily Tasks.xlsx");
		//System.out.println(excelfile.exists());
		FileInputStream fis = new FileInputStream(excelfile);

		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet =  workbook.getSheet("Sheet4");
		int noOfRows = sheet.getPhysicalNumberOfRows();
		System.out.println(noOfRows);
		//int noOfRows1 = sheet.getLastRowNum();
		//System.out.println(noOfRows1);
		int noOfColumns = sheet.getRow(0).getLastCellNum();
		
		String [][] data = new String[noOfRows-1][noOfColumns];
		
		for(int i=0;i<noOfRows-1;i++) {
			for(int j=0;j<noOfColumns;j++) {
				DataFormatter df = new DataFormatter();
				data[i][j] =df.formatCellValue(sheet.getRow(i+1).getCell(j));
				System.out.println(">>>>>>>>"+df.formatCellValue(sheet.getRow(i+1).getCell(j)));
				//System.out.println(sheet.getRow(i).getCell(j).getStringCellValue());
				
			}
			
		}
		workbook.close();
		fis.close();
		

		//		for(String[] dataArr: data) {
//			System.out.println(Arrays.toString(dataArr) );
//		}
		return data;
	}

}
