package getRequest;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import restclient.ClientRequest;

public class GetCall {

	String baseURI = "http://restapi.demoqa.com";
	String token = "oeU4QXetefCKNmv-5p89k6rfbtJsq1mBpnRn";
	String contentType = "JSON";
	String httpMethod = "GET";
	String endpointURL = "/utilities/weather/city/Hyderabad";
	boolean log = false;

	@Test
	public void getRestAPI() {

		Response response = ClientRequest.doGet(baseURI, token, contentType, endpointURL, log);
		System.out.println(ClientRequest.getStatusCode(response));
		System.out.println(ClientRequest.getAllHeaders(response));
		System.out.println(ClientRequest.getHeaderValue(response, "Content-Type"));
		System.out.println(ClientRequest.getBody(response));
		System.out.println(ClientRequest.getStatusLine(response));
		System.out.println(ClientRequest.getSessionId(response));

		JsonPath jsonPath = response.jsonPath();
		System.out.println(jsonPath.get("City"));
	}
}
