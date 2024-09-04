package stepDefinition;

import io.cucumber.java.en.And;
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
	public JsonPath jsnpath;

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

		// JSON representation from Response body
		jsnpath = response.jsonPath();

		String actualrate = jsnpath.getJsonObject("rating[0].rate").toString();
		assertEquals(rate, actualrate);
	}

	@Then("I print all the products rate and count")
	public void i_print_all_the_products_rate_and_count() {

		String rate = jsnpath.getJsonObject("rating.rate").toString();
		String price = jsnpath.getJsonObject("price").toString();
		System.out.println("Rates: " + rate);
		System.out.println("Price: " + price);
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

	}

	@Then("I receive the response body with id as {}")
	public void i_receive_the_response_body_with_id(String id) {
		httpRequest.body(requestParams.toJSONString());
		response = httpRequest.post("products");
		body = response.getBody();

		System.out.println(response.getStatusLine());
		System.out.println(body.asString());

		jsnpath = response.jsonPath();
		String actualId = jsnpath.getJsonObject("id").toString();
		assertEquals("21", actualId);
	}
	
	@Given("I hit the url of put product api endpoint")
	public void i_hit_the_url_of_put_product_api_endpoint() {
		RestAssured.baseURI = "https://fakestoreapi.com/";
	}

	@When("I pass the url of products in request with {}")
	public void i_pass_the_url_of_products_in_request_with(String productNo) {
		httpRequest = RestAssured.given();
		requestParams = new JSONObject();
		
		requestParams.put("title", "Test Product");
		requestParams.put("price", "20.5");
		requestParams.put("description", "Shoes is from Puma");
		requestParams.put("category", "men's footware");
		requestParams.put("image", "https://fakestoreapi.com/img/81fPKd._AG_SL500.jpg");
		
		httpRequest.body(requestParams.toJSONString());
		response = httpRequest.put("products/" + productNo);
		ResponseBody body = response.getBody();
		JsonPath jsnpath = response.jsonPath();
		String actualId = jsnpath.getJsonObject("id").toString();
		System.out.println(response.getStatusLine());
		System.out.println(body.asString());
	}
	
	@Given("I hit the url of delete product api endpoint")
	public void i_hit_the_url_of_delete_product_api_endpoint() {
		RestAssured.baseURI = "https://fakestoreapi.com/";
	}

	@When("I pass the url of delete product in request with {}")
	public void i_pass_the_url_of_delete_product_in_request_with(String productNo) {
		httpRequest = RestAssured.given();
		requestParams = new JSONObject();
		
		requestParams.put("title", "Test Product");
		requestParams.put("price", "20.5");
		requestParams.put("description", "Shoes is from Puma");
		requestParams.put("category", "men's footware");
		requestParams.put("image", "https://fakestoreapi.com/img/81fPKd._AG_SL500.jpg");
		
		httpRequest.body(requestParams.toJSONString());
		response = httpRequest.delete("products/" + productNo);
		ResponseBody body = response.getBody();
		JsonPath jsnpath = response.jsonPath();
		String actualId = jsnpath.getJsonObject("id").toString();
		System.out.println(response.getStatusLine());
		System.out.println(body.asString());
	}
}
