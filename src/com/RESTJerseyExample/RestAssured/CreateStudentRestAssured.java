package com.RESTJerseyExample.RestAssured;

import org.apache.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.RESTJerseyExample.database.StudentResource;
import com.RESTJerseyExample.service.StudentService;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class CreateStudentRestAssured {
	
	@Test
	public void createStudentRecordHappyPath(){
		Response response=RestAssured.given().put("http://localhost:8007/RESTJerseyExample/student/include");
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);
		//System.out.println(response.print());
		Assert.assertEquals(response.getContentType(),"appliation/json");

	}
	@Test
	public void createStudentRecordNegativeTestUri(){
		Response response=RestAssured.given().put("http://localhost:8007/RESTJerseyExample/student/includ");
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST);
		//System.out.println(response.print());
		Assert.assertEquals(response.getContentType(),"text/html;charset=utf-8");
	}
	@Test
	public void createStudentRecordNegativeTestNull(){
		Response response=RestAssured.given().put("http://localhost:8007/RESTJerseyExample/student/null");
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_NOT_FOUND);
		//System.out.println(response.print());
		Assert.assertEquals(response.getContentType(),"text/html;charset=utf-8");
	}
	@Test
	public void createStudentRecordRestAssuredNegativeTestId(){
		Response response=RestAssured.given().put("http://localhost:8007/RESTJerseyExample/student/include/Id=00000");
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_NOT_FOUND);
		//System.out.println(response.print());
		Assert.assertEquals(response.getContentType(),"text/html;charset=utf-8");
	}
	@Test
	public static  void testGet(){
		String jsonstr;
		Response response;
		int code = 0;
		try {
			StudentService studentService1 = null;
			
			response=(Response)StudentResource.getStudent(15618);	
			ObjectMapper mapper = new ObjectMapper();
			jsonstr=mapper.writeValueAsString(response);
			code=response.getStatusCode();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(code, 200);
	}		
}
