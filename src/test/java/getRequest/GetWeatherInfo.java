package getRequest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import restclient.ClientRequest;
import testutils.ExcelUtils;
import testutils.TestBase;

public class GetWeatherInfo extends TestBase {

	String filepath = "C:\\Users\\rahul.patidar\\Documents\\RestAssuredFW\\RestAssuredTest\\ApplicationTestData\\APITestData.xlsx";
	String sheetname = "WeatherInfo";

	@DataProvider
	public Object[][] getData() {
		Object[][] testData = ExcelUtils.getDataFromExcel(filepath, sheetname);
		return testData;
	}

	String baseURI = "http://restapi.demoqa.com";
	String token = null;
	String contentType = "JSON";
	String httpMethod = "GET";
	String endpointURL = "/utilities/weather";
	boolean log = false;

	@Test(dataProvider = "getData")
	public void getDataFromExcel(String city, String HTTPMethod, String humidity, String temperature,
			String weatherdescription, String windspeed, String winddirectiondegree) {

		Response response = ClientRequest.doGet(baseURI + "/" + city, token, contentType, endpointURL, log);
		System.out.println(ClientRequest.getStatusCode(response));

	}
}
