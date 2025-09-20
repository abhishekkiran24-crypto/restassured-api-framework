package com.example.tests;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;


public class GetUserTest extends BaseTest {
	
	@Test
	public void getSingleUser()
	{
		RestAssured.given()
		          .spec(reqSpec)
		          .when()
		          .get("/api/users/2")
		          .then()
		          .log().ifValidationFails()
		          .statusCode(200)
		          .body("data.id",equalTo(2));
	}

}
