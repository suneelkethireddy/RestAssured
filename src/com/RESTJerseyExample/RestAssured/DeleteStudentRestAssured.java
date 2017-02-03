package com.RESTJerseyExample.RestAssured;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class DeleteStudentRestAssured {

	@Test(enabled = false)
	public void deleteStudentTestHappyPath(){
		Response response=RestAssured.given().delete("http://localhost:8007/RESTJerseyExample/student/12536");
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);
		Assert.assertEquals(response.getContentType(),"application/json");
		String expected=JsonPath.from(response.asString()).getString("studentName");
		System.out.println(expected);
	}
	@Test
	public void deleteStudentRestAssuredNegativeTest(){
		Response response=RestAssured.given().delete("http://localhost:8007/RESTJerseyExample/student/125");
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST);
		Assert.assertEquals(response.getContentType(),"application/json");
	}
	@Test
	public void deleteStudentRestAssuredNegativeTestString(){
		Response response=RestAssured.given().delete("http://localhost:8007/RESTJerseyExample/student/fffff");
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
		Assert.assertEquals(response.getContentType(),"application/json");
	}
	@Test
	public void deleteStudentRestAssuredNegativeTestNotInDatabase(){
		Response response=RestAssured.given().delete("http://localhost:8007/RESTJerseyExample/student/123456");
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST);
		Assert.assertEquals(response.getContentType(), "application/json");
	}
	@Test
	public void deleteStudentRestAsssuredNegativeTestSymbol(){
		Response response=RestAssured.given().delete("http://localhost:8007/RESTJerseyExample/student/*");
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_NOT_FOUND);
		Assert.assertEquals(response.getContentType(), "application/json");
	}
	@Test
	public void deleteStudentRestAssuredNegativeTestNegativeNumber(){
		Response response=RestAssured.given().delete("http://localhost:8007/RESTJerseyExample/student/-1");
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_BAD_REQUEST);
		Assert.assertEquals(response.getContentType(), "application/json");
	}
	@Test
	public void deleteStudentRestAssuredNegativeTestWithSpace(){
		Response response=RestAssured.given().delete("http://localhost:8007/RESTJerseyExample/student/  12536");
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_NOT_FOUND);
		Assert.assertEquals(response.getContentType(),"application/json");

	}


}
