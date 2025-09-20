package com.example.tests;

import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.example.utils.ConfigReader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CreateUserTest extends BaseTest{
	
	@Test
	public void createUser()
	{
		System.out.println("Base URL: " + ConfigReader.get("base.url"));
	Map<String,Object>payload=new HashMap();
	payload.put("name","morpheus");
	payload.put("job","leader");
	
	Response response=RestAssured.given()
			        .spec(reqSpec)
			        .contentType(ContentType.JSON)
			        .body(payload)
			        .when()
			        .post("/api/users")
			        .then()
			        .log().all()
			        .statusCode(201)
			        .assertThat()
			        .body(matchesJsonSchemaInClasspath("schemas/createUserSchema.json"))
			        .body("id",notNullValue())
			        .extract().response();
	               
	String id=response.jsonPath().getString("id");
	String createdAt=response.jsonPath().getString("createdAt");
	System.out.println("Created id: " + id + ", createdAt: " + createdAt);
	
	

	}

}

