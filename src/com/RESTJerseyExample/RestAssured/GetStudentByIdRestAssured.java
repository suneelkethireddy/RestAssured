package com.RESTJerseyExample.RestAssured;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class GetStudentByIdRestAssured {
	
	@Test
	public void getStudentUsingRestAssuredHappyPath() throws Exception{
		Response response=RestAssured.given().get("http://localhost:8007/RESTJerseyExample/student/19989");
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
		Assert.assertEquals(response.getContentType(),"application/json");
		String studentsName=JsonPath.from(response.asString()).getString("name");
		System.out.println(studentsName);
	}
	@Test
	public void getStudentUsingRestAssuredNegativeUri(){
		Response response=RestAssured.given().get("http://localhost:8007/RESTJerseyExample/student/1989");
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_NOT_FOUND);
		Assert.assertEquals(response.getContentType(), "application/json");
	}
	@Test
	public void getStudentUsingRestAssuredNegativeNumber(){
		Response response=RestAssured.given().get("http://localhost:8007/RESTJerseyExample/student/-1");
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
		Assert.assertEquals(response.getContentType(), "application/json");
	}
	@Test
	public void getStudentUsingRestAssuredNegativeTestZero(){
		Response response=RestAssured.given().get("http://localhost:8007/RESTJerseyExample/student/0");
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
		Assert.assertEquals(response.contentType(), "application/json");
	}
	@Test
	public void getStudentUsingRestAssuredNegativeTestCharacter(){
		Response response=RestAssured.given().get("http://localhost:8007/RESTJerseyExample/student/aaaaaa");
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
		Assert.assertEquals(response.contentType(), "text/html;charset=utf-8");
	}
	@Test 
	public void getStudentUsingRestAssuredNegativeTestSymbol(){
		Response response=RestAssured.given().get("http://localhost:8007/RESTJerseyExample/student/*");
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
		Assert.assertEquals(response.contentType(), "text/html;charset=utf-8");
	}
	@Test
	public void getStudentUsingRestAssuredNegativeTestBlank(){
		Response response=RestAssured.given().get("http://localhost:8007/RESTJerseyExample/student/ ");
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_METHOD_NOT_ALLOWED);
		Assert.assertEquals(response.contentType(),"text/html;charset=utf-8");
	}
	
}
