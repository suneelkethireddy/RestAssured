package com.RESTJerseyExample.RestAssured;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class GetAllStudentsRestAssured {

	@Test
	public void getAllStudentsHappyPath(){
		Response response=RestAssured.given().get("http://localhost:8007/RESTJerseyExample/student/retrive");
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
		Assert.assertEquals(response.getContentType(), "application/json");
		System.out.println((response.asString()));
	}
	@Test
	public void getAllStudentsNegativeWrongUri(){
		Response response=RestAssured.given().get("http://localhost:8007/RESTJerseyExample/student/retriv");
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_NOT_FOUND);
		Assert.assertEquals(response.getContentType(), "text/html;charset=utf-8");
	}
	@Test
	public void getAllStudentsNegativeTestWithNull(){
		Response response=RestAssured.given().get("http://localhost:8007/RESTJerseyExample/student/null");
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_NOT_FOUND);
		Assert.assertEquals(response.getContentType(),"text/html;charset=utf-8");
	}
	@Test
	public void getAllStudentsNegativeTestWithSymbol(){
		Response response=RestAssured.given().get("http://localhost:8007/RESTJerseyExample/student/*");
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_NOT_FOUND);
		Assert.assertEquals(response.getContentType(), "text/html;charset=utf-8");
	}

	@Test
	public void getAllStudentsNegativeTestWithNegativeNumber(){
		Response response=RestAssured.given().get("http://localhost:8007/RESTJerseyExample/student/-1");
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
		Assert.assertEquals(response.getContentType(), "application/json");
	}
	@Test
	public void getAllStudentsNegativeTestWithPostiveNumber(){
		Response response=RestAssured.given().get("http://localhost:8007/RESTJerseyExample/student/1");
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_NOT_FOUND);
		Assert.assertEquals(response.getContentType(), "application/json");
	}


}
