package getRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import restclient.ClientRequest;

public class GetCall2{


	String baseURI = "https://restcountries.eu";
	String token = "oeU4QXetefCKNmv-5p89k6rfbtJsq1mBpnRn";
	String contentType = "JSON";
	String httpMethod = "GET";
	String endpointURL = "/rest/v2/capital/Washington";
	boolean log = false;
	
	@Test
	public void getRestAPI() {
		
		Response response = ClientRequest.doGet(baseURI, token, contentType, endpointURL, log);
		System.out.println(ClientRequest.getStatusCode(response));


		String responseString = ClientRequest.getBody(response);
		System.out.println("Response string is :" + responseString);

		responseString = responseString.substring(1, responseString.length()-1);
	//	responseString = responseString.substring(0, responseString.length() - 1);
		System.out.println("actual json response string is: " + responseString);

		// String to json conversion:
		JSONObject jsonResponseObj = new JSONObject(responseString);
		System.out.println("the actual json response is: " + jsonResponseObj);

		// get the values from json object:
		String countryName = jsonResponseObj.getString("name");
		System.out.println("the country name is: " + countryName);
		Assert.assertEquals("United States of America", countryName);

		// get the values from json array having only values:
		JSONArray spellingsArray = jsonResponseObj.getJSONArray("altSpellings");
		System.out.println("values from Altspellings: " + spellingsArray);

		// get the values from json array having keys and values:
		JSONArray currenciesArray = jsonResponseObj.getJSONArray("currencies");
		System.out.println(currenciesArray.getJSONObject(0));
		System.out.println(currenciesArray.getJSONObject(0).get("code").toString());
		System.out.println(currenciesArray.getJSONObject(0).get("symbol").toString());

		// for regional blocs:
		JSONArray regionalBlocsArray = jsonResponseObj.getJSONArray("regionalBlocs");
		System.out.println(regionalBlocsArray.getJSONObject(0).get("acronym").toString());

		System.out.println(regionalBlocsArray.getJSONObject(0).get("otherNames"));
		String otherNames = regionalBlocsArray.getJSONObject(0).get("otherNames").toString();
		// ["Tratado de Libre Comercio de América del Norte","Accord de Libre-échange
		// Nord-Américain"]
		String otherNamesArray[] = otherNames.split(",");
		for (int i = 0; i < otherNamesArray.length; i++) {
			System.out.println(otherNamesArray[i]);
		}
	}
}
