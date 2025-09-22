package com.example.tests;

import org.testng.annotations.Test;

import com.example.utils.RequestSpecFactory;
import com.example.utils.TokenManager;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;


public class GetUserTest extends BaseTest {
	
	@Test//(retryAnalyzer=RetryAnalyzer.class)
	public void getSingleUser()
	{
		RestAssured.given()
		          .spec(RequestSpecFactory.getRequestSpec())
		          .header("Authorization","Bearer "+TokenManager.getToken())
		          .when()
		          .get("/api/users/2")
		          .then()
		          .spec(RequestSpecFactory.getResponseSpec())
		         
		          .statusCode(200)
		          .body("data.id",equalTo(2));
	}

}
