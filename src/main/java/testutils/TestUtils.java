package testutils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

	public static int HTTP_STATUS_CODE_200 = 200;
	public static int HTTP_STATUS_CODE_201 = 201;
	public static int HTTP_STATUS_CODE_400 = 400;
	public static int HTTP_STATUS_CODE_401 = 401;
	public static int HTTP_STATUS_CODE_500 = 500;
	
	/**
	 * This method is used to convert POJO to JSON
	 * 
	 * @return
	 */
	public static String getSerializedJson(Object obj) {
		String jsonString = null;
		// ObjectMapper : This class comes from jackson library
		ObjectMapper mapper = new ObjectMapper();

		try {
			jsonString = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return jsonString;
		}
		return jsonString;
	}
}
