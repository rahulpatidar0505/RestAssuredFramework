package restclient;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import testutils.TestUtils;

public class ClientRequest {

	public static Response doGet(String baseURI, String token, String contentType, String endpointURL, boolean log) {

		createBaseURI(baseURI);
		createAuthorization(token);
		// setAuthorization("OAuth2", null, null, null, null, null, null);
		RequestSpecification request = createRequest(contentType, log);
		Response response = getResponse("GET", request, endpointURL);
		return response;

	}

	public static Response doPost(String baseURI, String token, String contentType, String endpointURL, boolean log, Object obj) {
		createBaseURI(baseURI);
		createAuthorization(token);		
		RequestSpecification request = createRequest(contentType, log);
		String jsonPayload=TestUtils.getSerializedJson(obj);
		request.body(jsonPayload);
		Response response = getResponse("POST", request, endpointURL);
		return response;
	}

	public static Response doPut(String baseURI, String token, String contentType, String endpointURL, boolean log, Object obj) {
		createBaseURI(baseURI);
		createAuthorization(token);		
		RequestSpecification request = createRequest(contentType, log);
		String jsonPayload=TestUtils.getSerializedJson(obj);
		request.body(jsonPayload);
		Response response = getResponse("PUT", request, endpointURL);
		return response;
	}

	public static Response doDelete(String baseURI, String token, String contentType, String endpointURL, boolean log, Object obj) {
		createBaseURI(baseURI);
		createAuthorization(token);		
		RequestSpecification request = createRequest(contentType, log);
		String jsonPayload=TestUtils.getSerializedJson(obj);
		request.body(jsonPayload);
		Response response = getResponse("DELETE", request, endpointURL);
		return response;
	}

	public static void createBaseURI(String baseURI) {
		RestAssured.baseURI = baseURI;
	}

	public static RequestSpecification createRequest(String contentType, boolean log) {
		RequestSpecification request;
		if (log) {
			request = RestAssured.given().log().all();
		} else {
			request = RestAssured.given();
		}

		if (contentType.equalsIgnoreCase("JSON")) {
			request.contentType(ContentType.JSON);
		} else if (contentType.equalsIgnoreCase("XML")) {
			request.contentType(ContentType.XML);
		} else if (contentType.equalsIgnoreCase("TEXT")) {
			request.contentType(ContentType.TEXT);
		}
		return request;
	}

	public static RequestSpecification createAuthorization(String token) {
		if (token != null) {
			return RestAssured.given().auth().oauth2(token);
		}
		return null;

	}

	public static RequestSpecification setAuthorization(String token, String consumerKey, String consumerSecret,
			String accessToken, String secretToken, String userName, String password) {

		RequestSpecification request = null;

		if (token.equalsIgnoreCase("OAuth2")) {
			request = RestAssured.given().auth().oauth2(token);

		} else if (token.equalsIgnoreCase("OAth1")) {
			request = RestAssured.given().auth().oauth(consumerKey, consumerSecret, accessToken, secretToken);
		} else if (token.equalsIgnoreCase("Basic")) {
			request = RestAssured.given().auth().basic(userName, password);
		} else {
			System.out.println("Authorization is not required");
		}
		return request;

	}

	private static Response executeAPI(String httpMethod, RequestSpecification request, String endpointURL) {
		Response response = null;
		if (httpMethod.equalsIgnoreCase("GET")) {
			response = request.get(endpointURL);
		} else if (httpMethod.equalsIgnoreCase("POST")) {
			response = request.post(endpointURL);
		} else if (httpMethod.equalsIgnoreCase("PUT")) {
			response = request.put(endpointURL);
		} else if (httpMethod.equalsIgnoreCase("DELETE")) {
			response = request.delete(endpointURL);
		} else {
			System.out.println("Please select correct HTTP Method");
		}
		return response;
	}
	
	public static Response getResponse(String httpMethod, RequestSpecification request, String endpointURL) {
		return executeAPI(httpMethod, request, endpointURL);
	}
	
	public static int getStatusCode(Response response) {
		return response.getStatusCode();
	}

	public static String getStatusLine(Response response) {
		return response.getStatusLine();
	}
	
	public static String getSessionId(Response response) {
		return response.getSessionId();
	}
	
	public static String getHeaderValue(Response response, String headerName) {
		return response.getHeader(headerName);
	}

	public static List<Header> getAllHeaders(Response response) {
		Headers headers = response.getHeaders();
		List<Header> headerList = headers.asList();
		return headerList;
	}

	public static String getBody(Response response) {
		return response.getBody().prettyPrint().toString();
	}

	public JsonPath getJSONpath(Response response) {
		return response.jsonPath();

	}

}
