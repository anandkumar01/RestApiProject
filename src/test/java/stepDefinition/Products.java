package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import static org.junit.Assert.*;

import org.json.simple.JSONObject;

public class Products {

	public int statusCode;
	public RequestSpecification httpRequest;
	public Response response;
	public int responseCode;
	public ResponseBody body;
	public JSONObject requestParams;

	@Given("I hit the url of get products api endpoint")
	public void i_hit_the_url_of_get_products_api_endpoint() {
		RestAssured.baseURI = "https://fakestoreapi.com/";
	}

	@When("I pass the url of products in request")
	public void i_pass_the_url_of_products_in_request() {
		httpRequest = RestAssured.given();
		response = httpRequest.get("products");
	}

	@Then("I receive the response code as {int}")
	public void i_receive_the_response_code_as(Integer statusCode) {
		responseCode = response.getStatusCode();
		assertEquals(responseCode, 200);
	}

	@Then("I verify that the rate of the product is {}")
	public void i_verify_that_the_rate_of_the_product_is(String rate) {

		body = response.getBody();

		// Convert Response body to string
		String responseBody = body.asString();

		// JSON representation from Response body
		JsonPath jsnpath = response.jsonPath();

		String s = jsnpath.getJsonObject("rating[0].rate").toString();
		assertEquals(rate, s);
	}

	@Given("I hit the url of post product api endpoint")
	public void i_hit_the_url_of_post_product_api_endpoint() {

		RestAssured.baseURI = "https://fakestoreapi.com/";
		httpRequest = RestAssured.given();
		requestParams = new JSONObject();
	}

	@When("I pass the request body of product title {}")
	public void i_pass_the_request_body_of_product_title(String title) {

		requestParams.put("title", title);
		requestParams.put("price", 13.5);
		requestParams.put("description", "Shoes is from Nike");
		requestParams.put("category", "men's footware");
		requestParams.put("image", "https://fakestoreapi.com/img/81fPKd._AC_SL500.jpg");

		httpRequest.body(requestParams.toJSONString());
		Response response = httpRequest.post("products");
		ResponseBody body = response.getBody();
		
		System.out.println(response.getStatusLine());
		System.out.println(body.asString());
	}
	
	@Then("I receive the response body with id as {}")
	public void i_receive_the_response_body_with_id(String id) {
	    
//		JsonPath jsnpath = response.jsonPath();
//		String s = jsnpath.getJsonObject("id").toString();
//		assertEquals("21", s);
	}
}
