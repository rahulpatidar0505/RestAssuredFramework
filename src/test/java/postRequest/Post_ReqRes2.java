package postRequest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import pojo.User;
import restclient.ClientRequest;
import testutils.ExcelUtils;

import java.util.HashMap;

public class Post_ReqRes2 {

	String baseURI = "https://reqres.in";
	String token = null;
	String contentType = "JSON";
	String httpMethod = "POST";
	String basePath = "/api/users";
	boolean log = false;

	String filepath = "./RestAssuredFrameworkApril2020/ApplicationTestData/APITestData.xlsx";
	String sheetname = "User";

//	@DataProvider
//	public Object[][] getData() {
//		Object[][] testData = ExcelUtils.getDataFromExcel(filepath,sheetname);
//		return testData;
//	}

//	@Test(dataProvider = "getData")
//	public void adduser(String name, String job) {
//		User user = new User(name, job);
//		Response response = ClientRequest.doPost(baseURI, token, contentType, basePath, log, user);
//		System.out.println(ClientRequest.getStatusCode(response));
//		System.out.println(ClientRequest.getBody(response));
//	}

	@Test
	public void adduser() {
		HashMap user = new HashMap<>();
		user.put("rahul", "qa");
		user.put("tom", "dev");
		Response response = ClientRequest.doPost(baseURI, token, contentType, basePath, log, user);
		System.out.println(ClientRequest.getStatusCode(response));
		System.out.println(ClientRequest.getBody(response));
	}
}
