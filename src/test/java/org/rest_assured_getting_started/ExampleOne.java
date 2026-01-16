package org.rest_assured_getting_started;

import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import org.hamcrest.*;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class ExampleOne {

	@Test
	public void Automate_GET_Request_Assert_Status_Code() throws Exception {

		given().baseUri("https://gorest.co.in/").contentType(ContentType.JSON).log().all().when().get("public/v2/users")
				.then().assertThat().statusCode(200).log().all();
	}
	@Test
	public void Assert_Response_Body() throws Exception {
		
		given().baseUri("https://gorest.co.in/").contentType(ContentType.JSON).log().all().when().get("public/v2/users")
		.then().assertThat().body("name[1]",equalTo("Bhadra Dwivedi")).body("id[2]",equalTo(8333172)).body("id",hasItems(8333305,8333304)).
		body("gender[1]", is(equalTo("female"))).body("id.size()",equalTo(10)).
		statusCode(200).log().all();
	}
	
}
