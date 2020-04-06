package postRequest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import pojo.User;
import restclient.ClientRequest;
import testutils.ExcelUtils;

public class Post_ReqRes2 {

	String baseURI = "https://reqres.in";
	String token = null;
	String contentType = "JSON";
	String httpMethod = "POST";
	String basePath = "/api/users";
	boolean log = false;

	String filepath = "C:\\Users\\rahul.patidar\\Documents\\RestAssuredFW\\RestAssuredTest\\ApplicationTestData\\APITestData.xlsx";
	String sheetname = "User";

	@DataProvider
	public Object[][] getData() {
		Object[][] testData = ExcelUtils.getDataFromExcel(filepath,sheetname);
		return testData;
	}

	@Test(dataProvider = "getData")
	public void adduser(String name, String job) {
		User user = new User(name, job);
		Response response = ClientRequest.doPost(baseURI, token, contentType, basePath, log, user);
		System.out.println(ClientRequest.getStatusCode(response));
		System.out.println(ClientRequest.getBody(response));

	}
}
