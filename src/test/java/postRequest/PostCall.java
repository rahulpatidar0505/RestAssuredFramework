
package postRequest;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import pojo.Customer;
import responceobject.CustomerResponceOnFailure;
import responceobject.CustomerResponceOnSuccess;
import restclient.ClientRequest;

public class PostCall {

	String baseURI = "http://restapi.demoqa.com";
	String token=null;
	String contentType = "JSON";
	String httpMethod = "POST";
	String basePath = "/customer/register";
	boolean log = false;
	
	@Test
	public void postRequestAPI() {
		
		Customer customer = new Customer("Rahuldda", "Patdasidar", "rdsadasahul", "rahuldas@123", "rahudal@gmailcom");
		Response response=ClientRequest.doPost(baseURI, token, contentType, basePath, log, customer);
	
		// Deserialization-convert Json object into Java object
		if (response.getStatusCode() == 201) {
			CustomerResponceOnSuccess customerResponceOnSuccess = response.as(CustomerResponceOnSuccess.class);
			System.out.println(customerResponceOnSuccess.SuccessCode);
			System.out.println(customerResponceOnSuccess.Message);
		} else if (response.statusCode() == 200) {
			CustomerResponceOnFailure customerResponce = response.as(CustomerResponceOnFailure.class);
			System.out.println(customerResponce.FaultID);
			System.out.println(customerResponce.fault);

		}

	}
}
