package com.example.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestSpecFactory {

	private static RequestSpecification requestSpec;
	private static ResponseSpecification responseSpec;
	public static RequestSpecification getRequestSpec()
	{
		if(requestSpec==null)//lazy initialization pattern. It behaves like a singleton. Ensures it's created only once ( the first time)
		{
			RequestSpecBuilder builder=new RequestSpecBuilder()
					   .setBaseUri(ConfigReader.get("base.url"))
					   .addHeader("Accept","application/json")
					   .addFilter(new RequestLoggingFilter()) //logs requests in terminal
					   .addFilter(new ResponseLoggingFilter()) //logs responses in terminal
					   .log(LogDetail.ALL);
			String apiKey=ConfigReader.get("api.key");
			if(apiKey!=null &!apiKey.isEmpty())
			{
				builder.addHeader("x-api-key",apiKey);
			}
			requestSpec=builder.build();
		}
		return requestSpec;
	}
	
	public static ResponseSpecification getResponseSpec()
	{
		if(responseSpec==null)
		{
			responseSpec=new ResponseSpecBuilder()
					    .expectContentType(ContentType.JSON)
					    .log(LogDetail.ALL)
					    .build();
		}
		return responseSpec;
	}
}
