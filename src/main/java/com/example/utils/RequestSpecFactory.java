package com.example.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecFactory {

	private static RequestSpecification requestSpec;
	public static RequestSpecification getRequestSpec()
	{
		if(requestSpec==null)//lazy initialization pattern. It behaves like a singleton. Ensures it's created only once ( the first time)
		{
			RequestSpecBuilder builder=new RequestSpecBuilder()
					   .setBaseUri(ConfigReader.get("base.url"))
					   .addHeader("Accept","application/json");
			String apiKey=ConfigReader.get("api.key");
			if(apiKey!=null &!apiKey.isEmpty())
			{
				builder.addHeader("x-api-key",apiKey);
			}
			requestSpec=builder.build();
		}
		return requestSpec;
	}
}
