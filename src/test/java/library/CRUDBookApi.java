package library;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import RestAssured.ExcelDriven.DataDrivenUsingExcel;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;




public class CRUDBookApi {
	
	String id="";
	String author="";

	@BeforeMethod
	public void BaseURI() {
		
		RestAssured.baseURI = "http://216.10.245.166";
	}
	@Test(priority = 1)
	public void AddBookApi() throws Exception {
		
		DataDrivenUsingExcel data = new DataDrivenUsingExcel();
		ArrayList arraylist =  data.getExcelData("LibraryApi","Sheet2");
		
		Map<String,Object> map = new HashMap<>();
		map.put("name", arraylist.get(1));
		map.put("isbn", arraylist.get(2));
		map.put("aisle", arraylist.get(3));
		map.put("author", arraylist.get(4));

		String addBook = given().log().all().header("Content_Type", "application/json")
				.body(map)
				.when()
				.post("/Library/Addbook.php")
				.then().log().all()
				.assertThat().statusCode(200).extract().response()
				.asString();

			System.out.println("addBook="+addBook);
			
			JsonPath js = new JsonPath(addBook);
			id=js.get("ID");
			System.out.println(id);
	}
	
	@Test(priority = 2)
	public void GetBookByID() {
		
		String getBookById=given().log().all().queryParam("ID", id).header("Content_Type", "application/json")
		.when().get("Library/GetBook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("getBookById="+getBookById);
		
		String ref=getBookById.split("\"author\":\"")[1];
		System.out.println(ref);
		author=ref.replaceAll("\"}]", "");
		System.out.println("Author="+author);

		
	}
	
	@Test(priority = 3)
	public void GetBookByAuthor() {
		
		String getBookByAuthor=given().log().all().queryParam("AuthorName",author)
		.when().get("/Library/GetBook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("getBookByAuthor="+getBookByAuthor);
		
	}
	
	@Test(priority = 4)
	public void DeleteBookApi() {
		
		
		String deleteBook=given().log().all().header("Content_Type", "application/json")
				.body("{\r\n"
						+ "	\"ID\": \""+id+"\"\r\n"
						+ "}")
				.when().post("/Library/DeleteBook.php")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("deleteBook"+deleteBook);
	}
	
	
}
