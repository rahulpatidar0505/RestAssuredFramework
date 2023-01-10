package e2eTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GoogleAPI_Add_Delete_place_pojo {

	@Test
	public void add_delete_Place() {

		RestAssured.baseURI = "http://216.10.245.166";

		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");

		Map<String, Object> jsonmap = new HashMap<String, Object>();

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("lat", "-38.383494");
		map.put("lng", "33.427362");
		jsonmap.put("location", map);

		jsonmap.put("accuracy", "50");
		jsonmap.put("name", "Frontline house");
		jsonmap.put("phone_number", "(+91) 983 893 3937");
		jsonmap.put("address", "29, side layout, cohen 09");

		List<String> list = new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");
		jsonmap.put("types", list);

		jsonmap.put("website", "http://google.com");
		jsonmap.put("language", "French-IN");

		JSONObject json = new JSONObject(jsonmap);
		request.body(json.toJSONString());

		Response response = request.post("/maps/api/place/add/json");

		System.out.println("Response is : " + response.getStatusCode());
		JsonPath jp=response.jsonPath();
		String placeid=jp.getString("place_id");
		System.out.println("Place id is : "+placeid);
		System.out.println("Body is : "+response.getBody().asString());
		
		JSONObject deletejson=new JSONObject();
		deletejson.put("place_id", placeid);
		request.body(deletejson.toJSONString());
		Response deleteresponse=request.delete("/maps/api/place/delete/json?key=qaclick123");
		System.out.println(deleteresponse.getStatusCode());
	}

}
