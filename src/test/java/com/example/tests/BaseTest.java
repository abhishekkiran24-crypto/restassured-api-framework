package com.example.tests;

import org.testng.annotations.BeforeClass;

import com.example.utils.RequestSpecFactory;

import io.restassured.specification.RequestSpecification;

public class BaseTest {
	
	protected RequestSpecification reqSpec;
	
	@BeforeClass
	public void setup()
	{
		reqSpec=RequestSpecFactory.getRequestSpec();
		
	}

}
