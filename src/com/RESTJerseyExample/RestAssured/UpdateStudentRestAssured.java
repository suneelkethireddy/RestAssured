package com.RESTJerseyExample.RestAssured;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class UpdateStudentRestAssured {

	@Test
	public void updateStudentRecordHappyPath(){
		Response response=RestAssured.given().post("http://localhost:8007/RESTJerseyExample/student/send");
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
		Assert.assertEquals(response.getContentType(),"text/html;charset=utf-8");
		System.out.println(response.print());
		System.out.println(response.getStatusLine());
	}
	@Test
	public void updateStudentRecordNegativeTestWithSpellingMistake(){
		Response response=RestAssured.given().post("http://localhost:8007/RESTJerseyExample/student/sendd");
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_METHOD_NOT_ALLOWED);
		//System.out.println(response.print());
		Assert.assertEquals(response.getContentType(), "text/html;charset=utf-8");
	}
	@Test
	public void updateTestStudentRecordNegativeUri(){
		Response response=RestAssured.given().post("http://localhost:8007/RESTJerseyExample/student/send/1");
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
		//System.out.println(response.print());
		Assert.assertEquals(response.getContentType(),"text/html;charset=utf-8");
	}
	@Test
	public void updateStudentRecordNegativeWithID(){
		Response response=RestAssured.given().post("http://localhost:8007/RESTJerseyExample/student/send/Id=19989");
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
		//System.out.println(response.print());
		Assert.assertEquals(response.getContentType(),"text/html;charset=utf-8");
	}
	@Test
	public void updateStudentRecordNegativeTestNull(){
		Response response=RestAssured.given().post("http://localhost:8007/RESTJerseyExample/student/null");
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_METHOD_NOT_ALLOWED);
		//System.out.println(response.print());
		Assert.assertEquals(response.getContentType(), "text/html;charset=utf-8");
	}
	@Test
	public void updateStudentRecordNegativeTestNegativeNumber(){
		Response response=RestAssured.given().post("http://localhost:8007/RESTJerseyExample/student/-1");
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_METHOD_NOT_ALLOWED);
		//System.out.println(response.print());
		Assert.assertEquals(response.getContentType(),"text/html;charset=utf-8");
	}


}
