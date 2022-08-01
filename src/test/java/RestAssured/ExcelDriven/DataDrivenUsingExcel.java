package RestAssured.ExcelDriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenUsingExcel {

	public ArrayList getExcelData(String testCaseName,String sheetName) throws Exception {

		ArrayList<String> array = new ArrayList<String>();

		FileInputStream fis = new FileInputStream("./src/main/java/resourse/Daily Tasks.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();

		for (int i = 0; i < sheets; i++) {

			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				Iterator<Row> row = sheet.rowIterator();

				Row firstrow = row.next();

				Iterator<Cell> cell = firstrow.cellIterator();

				int k = 0;
				int column = 0;
				while (cell.hasNext()) {

					Cell cellvalue = cell.next();
					if (cellvalue.getStringCellValue().equalsIgnoreCase("Testcases")) {
						column = k;
					}
					k++;
				}
				System.out.println("Column Number>>>>>>>>>>>>" + column);

				while (row.hasNext()) {
					Row rowvalue = row.next();
					if (rowvalue.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
						Iterator<Cell> rowcellvalue = rowvalue.cellIterator();
						while (rowcellvalue.hasNext()) {
							// System.out.println(rowcellvalue.next().getStringCellValue());
							Cell c = rowcellvalue.next();

							if (c.getCellType() == CellType.STRING) {
								array.add(c.getStringCellValue());
							} else {
								array.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}

						}
					}
				}
			}

		}
		return array;

	}

	public static void main(String[] args) {

	}

	

}
