package org.rest_assured_getting_started;

import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import org.hamcrest.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;

public class ExampleOne {

	RequestSpecification requestSpecification;
	@Test
	public void Automate_GET_Request_Assert_Status_Code() throws Exception {

		given().baseUri("https://gorest.co.in/").contentType(ContentType.JSON).log().all().when().get("public/v2/users")
				.then().assertThat().statusCode(200).log().all();
	}

	@Test
	public void Assert_Response_Body() throws Exception {

		given().baseUri("https://gorest.co.in/").contentType(ContentType.JSON).log().all().when().get("public/v2/users")
				.then().assertThat().body("name[1]", equalTo("Bhadra Dwivedi")).body("id[2]", equalTo(8333172))
				.body("id", hasItems(8333305, 8333304)).body("gender[1]", is(equalTo("female")))
				.body("id.size()", equalTo(10)).statusCode(200).log().all();
	}

	@Test
	public void Extract_Response() throws Exception {
		Response response = given().baseUri("https://gorest.co.in/").contentType(ContentType.JSON).when()
				.get("public/v2/users").andReturn();
		System.out.println("Pretty String Format: " + response.asPrettyString());
		System.out.println("As String Format: " + response.asString());
		System.out.println("Content Type: " + response.contentType());
		System.out.println(response.jsonPath().getString("name[3]"));
	}

	@Test
	public void hamcrestValidations() throws Exception {
		given().baseUri("https://gorest.co.in/").contentType(ContentType.JSON).log().all().when().get("public/v2/users")
				.then().assertThat().statusCode(200).body("gender", hasItems("female", "male"))
				.body("gender", everyItem(anyOf(equalTo("male"), equalTo("female")))).body("gender", not(empty()))
				.body("gender[0]",anyOf(equalTo("female"),equalTo("male")))
				.body("[0]",hasKey("id"))
				.body("[0]",hasEntry("id",8333304))
				.log()
				.all();
	}
	@Test
	public void headers() throws Exception {
		Headers headers = given().baseUri("https://gorest.co.in/").contentType(ContentType.JSON).log().ifValidationFails().when().get("public/v2/users")
		.then().assertThat().statusCode(200).header("Connection","keep-alive").log().ifStatusCodeIsEqualTo(200).extract().headers();
		System.out.println(headers.get("Connection"));
		List<String>strings = headers.getValues("connection");
		for (String string : strings) {
			System.out.println(string);
		}
	}
	@BeforeClass
	public void launch() {
		requestSpecification = given().baseUri("https://gorest.co.in/").contentType(ContentType.JSON).log().all();
	}
	@Test
	public void requestSpecification() throws Exception {
		
		Response response = given().spec(requestSpecification).when().get("public/v2/users").andReturn();
		System.out.println(response.asPrettyString());
		assertThat(response.jsonPath().get("id[0]"),equalTo(8333172));
		assertThat(response.statusCode(),equalTo(200));
	}

}
