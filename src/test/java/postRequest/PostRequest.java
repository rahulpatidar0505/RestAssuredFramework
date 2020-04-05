package postRequest;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import pojo.Customer;
import restclient.ClientRequest;

public class PostRequest {

	String baseURI = "http://restapi.demoqa.com";
//	String token = "oeU4QXetefCKNmv-5p89k6rfbtJsq1mBpnRn";
	String token=null;
	String contentType = "JSON";
	String httpMethod = "POST";
	String endpointURL = "/customer/register";
	boolean log = false;

	@Test
	public void postCall() {

		Customer customer = new Customer("Rahuldda", "Patdasidar", "rdsadasahul", "rahuldas@123", "rahudal@gmailcom");
		Response response=ClientRequest.doPost(baseURI, token, contentType, endpointURL, log, customer);
		System.out.println(ClientRequest.getBody(response));

	}
}
