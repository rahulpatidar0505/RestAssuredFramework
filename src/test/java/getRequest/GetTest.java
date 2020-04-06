package getRequest;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import restclient.ClientRequest;

public class GetTest {

	String baseURI = "https://gorest.co.in";
	String token = "oeU4QXetefCKNmv-5p89k6rfbtJsq1mBpnRn";
	String contentType = "JSON";
	String httpMethod = "GET";
	String basePath = "/public-api/users";
	boolean log = false;

	@Test
	public void getTest() {

		Response response=ClientRequest.doGet(baseURI, token, contentType, basePath, log);
		System.out.println(ClientRequest.getStatusCode(response));
		System.out.println(ClientRequest.getAllHeaders(response));
		System.out.println(ClientRequest.getBody(response));

	}
}
