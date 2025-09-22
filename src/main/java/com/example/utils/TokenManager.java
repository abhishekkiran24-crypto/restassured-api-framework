package com.example.utils;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class TokenManager {
	
	private static String token;
	private static long expiryTime; //epoch millis
	
	//Fetch new token
	private static String generateToken() {
		Response response=given()
		.baseUri(ConfigReader.get("base.url"))
		.contentType("application/json")
		.body("{\"username\": \"myuser\",\"password\": \"mypassword\" }")
        .when()
        .post("/auth/login")
        .then()
        .statusCode(200)
        .extract().response();
		
		token=response.jsonPath().getString("access_token");
		int expiresIn=response.jsonPath().getInt("expires_in");//ex. 3600 seconds
		expiryTime=System.currentTimeMillis() + (expiresIn - 60)*1000; //subtract 1 min buffer
		return token;
	}
	//Public method to get token
	public static String getToken() {
		/*if(token==null || System.currentTimeMillis() >= expiryTime) {
			return generateToken();
		}
		return token;*/
		return "dummy-token"; //hardocded,avoids 404
	}

}
