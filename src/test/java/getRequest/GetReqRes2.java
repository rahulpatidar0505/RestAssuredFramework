package getRequest;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import restclient.ClientRequest;

public class GetReqRes2 {

	String baseURI = "https://reqres.in";
	String token = "oeU4QXetefCKNmv-5p89k6rfbtJsq1mBpnRn";
	String contentType = "JSON";
	String httpMethod = "GET";
	String basePath = "/api/users/2";
	boolean log = false;

	@Test
	public void getuser() {
		Response response = ClientRequest.doGet(baseURI, token, contentType, basePath, log);
		System.out.println(ClientRequest.getStatusCode(response));
		String body = ClientRequest.getBody(response);

		JSONObject json = new JSONObject(body);

		System.out.println(json.getJSONObject("data"));

		System.out.println(json.getJSONObject("data").get("email"));

	}
}
