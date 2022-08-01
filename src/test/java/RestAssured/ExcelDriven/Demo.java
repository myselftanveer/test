package RestAssured.ExcelDriven;

import java.util.ArrayList;

public class Demo {

	public static void main(String[] args) throws Exception {

		DataDrivenUsingExcel data = new DataDrivenUsingExcel();
		ArrayList arraylist =  data.getExcelData("Add Profile","Sheet1");
		System.out.println(arraylist.get(0));
		System.out.println(arraylist.get(1));
		System.out.println(arraylist.get(2));
		System.out.println(arraylist.get(3));
	}

}
