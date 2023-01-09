package getRequest;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import restclient.ClientRequest;

public class GetReqRes {

	String baseURI = "https://reqres.in";
	String token = "oeU4QXetefCKNmv-5p89k6rfbtJsq1mBpnRn";
	String contentType = "JSON";
	String httpMethod = "GET";
	String basePath = "/api/users?page=2";
	boolean log = false;
	
	@Test
	public void getuser() {
		
		Response response = ClientRequest.doGet(baseURI, token, contentType, basePath, log);
		System.out.println(ClientRequest.getStatusCode(response));
		String body=ClientRequest.getBody(response);

//     	JSONObject jsonObject=new JSONObject(body);
//		System.out.println(jsonObject.get("total"));
		
		JsonPath json=new JsonPath(body);
//		System.out.println(json.get("total"));
		List<Object>js=json.getList("data");
		for (Object value : js) {
			System.out.println("data values are :"+value);
		}
		
		System.out.println("==============================================================");
		
		JSONObject jsonObject=new JSONObject(body);
		JSONArray js1=jsonObject.getJSONArray("data");

		for (Object value : js1) {
			System.out.println("data values are :"+value);
		}
		
		System.out.println(js1.getJSONObject(0).get("id"));
		
	}
}
