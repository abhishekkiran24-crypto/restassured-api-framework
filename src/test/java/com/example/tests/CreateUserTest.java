package com.example.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.example.utils.ConfigReader;
import com.example.utils.RequestSpecFactory;
import com.example.utils.TokenManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CreateUserTest extends BaseTest{
	
	@DataProvider(name="createUserData")
	public Object[][] createUserData()
	{
		return new Object[][] {
			{"morpheus","leader"},
			{"neo","the one"}
		};
	}
	
	@Test(dataProvider="createUserData",groups= {"smoke"})
	public void createUser_dataDriven(String name, String job)
	{
		System.out.println("Base URL: " + ConfigReader.get("base.url"));
	Map<String,Object>payload=new HashMap();
	payload.put("name",name);
	payload.put("job",job);
	
	                given()
                    .spec(RequestSpecFactory.getRequestSpec())
                    .header("Authorization","Bearer "+TokenManager.getToken())
			        .contentType(ContentType.JSON)
			        .body(payload)
			        .when()
			        .post("/api/users")
			        .then()
			        .spec(RequestSpecFactory.getResponseSpec())
			        .statusCode(201)
			        .body("id",notNullValue())
			        .body("createdAt",notNullValue());
	               
	//String id=response.jsonPath().getString("id");
	//String createdAt=response.jsonPath().getString("createdAt");
	//System.out.println("Created id: " + id + ", createdAt: " + createdAt);
	
	

	}

}

