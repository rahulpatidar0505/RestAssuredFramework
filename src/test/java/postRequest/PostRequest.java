package postRequest;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import pojo.User;
import restclient.ClientRequest;
import testutils.ExcelUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class PostRequest {

	String baseURI = "https://reqres.in";
	String token = null;
	String contentType = "JSON";
	String httpMethod = "POST";
	String basePath = "/api/users";
	boolean log = false;

	String filepath = "./ApplicationTestData/APITestData.xlsx";
	String sheetname = "User";

	@DataProvider
	public Object[][] getData() {
		Object[][] testData = ExcelUtils.getDataFromExcel(filepath,sheetname);
		return testData;
	}

	////	Get payload using xls file
	@Test(dataProvider = "getData")
	public void addUserUsingExcelFile(String name, String job) {
		User user = new User(name, job);
		Response response = ClientRequest.doPost(baseURI, token, contentType, basePath, log, user);
		System.out.println(ClientRequest.getStatusCode(response));
		System.out.println(ClientRequest.getBody(response));
	}


////	Create payload using Hashmap
	@Test
	public void addUserUsingHashMap() {
		HashMap user = new HashMap<>();
		user.put("rahul", "qa");
		user.put("tom", "dev");
		Response response = ClientRequest.doPost(baseURI, token, contentType, basePath, log, user);
		System.out.println(ClientRequest.getStatusCode(response));
		System.out.println(ClientRequest.getBody(response));
	}



	////	Create payload using org.json
	@Test
	public void addUserUingJSONObject() {
		JSONObject user =  new JSONObject();
		user.put("Rahul", "QA");
		String payload = user.toString();
		Response response = ClientRequest.doPostUsingOrgJson(baseURI, token, contentType, basePath, log, payload);
		System.out.println(ClientRequest.getStatusCode(response));
		System.out.println(ClientRequest.getBody(response));
	}



	////	read json file as payload
	@Test
	public void addUserUsingJsonFile() throws FileNotFoundException {
		File file = new File("ApplicationTestData/payload.json");
		FileReader fileReader = new FileReader(file);
		JSONTokener jsonTokener =  new JSONTokener(fileReader);
		JSONObject user =  new JSONObject(jsonTokener);
		String payload = user.toString();
		Response response = ClientRequest.doPostUsingOrgJson(baseURI, token, contentType, basePath, log, payload);
		System.out.println(ClientRequest.getStatusCode(response));
		System.out.println(ClientRequest.getBody(response));
	}

	//	Create payload using POJO
	@Test
	public void addUserUsingPOJO() {
		User user = new User("rahul", "qa");
		Response response = ClientRequest.doPost(baseURI, token, contentType, basePath, log, user);
		System.out.println(ClientRequest.getStatusCode(response));
		System.out.println(ClientRequest.getBody(response));
	}
}
