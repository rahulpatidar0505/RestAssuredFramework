package deleteRequest;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import pojo.Book;
import restclient.ClientRequest;

public class LibraryAPI_E2ETest {

	String baseURI = "http://216.10.245.166";
	String token = null;
	String contentType = "JSON";
	String httpMethod = "POST";
	String basePath = "/Library/Addbook.php";
	boolean log = false;

	@Test
	public void addBook() {

		/*
		 * 1. Provide the baseURI 2. Create the request 3. Provide JSON payload 4.
		 * Insert JSON payload to body 5. Provide header 6. Send the request 7. Fetch
		 * the data
		 */

		Book book = new Book("ABdannsC", "asas", "657", "Tomna");
		
		Response response = ClientRequest.doPost(baseURI, token, contentType, basePath, log, book);
		System.out.println(ClientRequest.getStatusCode(response));

		/*
		 * 1.baseURI is already defined 2. Request is already created 3. Send the
		 * request with resource 4. Check the response code
		 */
		String isbn=book.getIsbn();
		String aisle=book.getAisle();

		//"/Library/GetBook.php?ID=fsddsa31211"
		ClientRequest.doGet(baseURI, token, contentType, "/Library/GetBook.php?ID="+isbn+""+aisle+"", log);
		System.out.println(ClientRequest.getStatusCode(response));
		String id = response.jsonPath().get("ID");
		System.out.println(id);

		/*
		 * 1. baseURI is already defined 2. Request is already created 3. Create the
		 * JSON payload and send the body 4. defined header 5. send the request 6. Get
		 * the reponse code
		 */

		ClientRequest.doDelete(baseURI, token, contentType, "/Library/DeleteBook.php", log, book);
		System.out.println(ClientRequest.getStatusCode(response));
	}

}
