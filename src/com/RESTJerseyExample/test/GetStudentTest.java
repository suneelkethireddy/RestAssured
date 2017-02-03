package com.RESTJerseyExample.test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

/**
 * this test class has all the test
 * cases with regard to getStudent
 * @author Suneel Reddy
 *
 */
public class GetStudentTest {

	@Test
	public void testHappyPathGetStudentById() throws ClientProtocolException, IOException
	{
		HttpUriRequest request= new HttpGet("http://localhost:8007/RESTJerseyExample/student/15786");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
		int actualResponseCode = httpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(actualResponseCode, 200);
	}
	
	

	@Test
	public void testNegativeTestCaseGetStudent() throws ClientProtocolException, IOException{
		HttpUriRequest request=new HttpGet("http://localhost:8007/RESTJerseyExample/student/15615");
		HttpResponse httpResponse=HttpClientBuilder.create().build().execute( request );
		int actualResponseCode = httpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(actualResponseCode, HttpStatus.SC_NOT_FOUND);
	}

	@Test
	public void testNegativeCaseGetStudentById() throws ClientProtocolException, IOException
	{
		HttpUriRequest request=new HttpGet("http://localhost:8007/RESTJerseyExample/student/15");
		HttpResponse httpResponse=HttpClientBuilder.create().build().execute( request );
		int actualResponseCode = httpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(actualResponseCode, HttpStatus.SC_NOT_FOUND);
	}

	@Test
	public void testNegativeTestCaseGetStudentByIdIsString() throws ClientProtocolException, IOException{
		HttpUriRequest request=new HttpGet("http://localhost:8007/RESTJerseyExample/student/a");
		HttpResponse httpResponse=HttpClientBuilder.create().build().execute(request);
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
	}

	@Test
	public void testNegativeTestCaseGetStudentByIdIsNegative() throws ClientProtocolException, IOException{
		HttpUriRequest request=new HttpGet("http://localhost:8007/RESTJerseyExample/student/-1");
		HttpResponse httpResponse=HttpClientBuilder.create().build().execute(request);
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
	}

	@Test(enabled = false)
	public void testNegativeTestCaseGetStudentByIdIsEmptySpace() throws ClientProtocolException, IOException{
		HttpUriRequest request=new HttpGet("http://localhost:8007/RESTJerseyExample/student/  ");
		HttpResponse httpResponse=HttpClientBuilder.create().build().execute(request);
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_METHOD_NOT_ALLOWED);
	}

	@Test
	public void testNegativeTestCaseGetStudentIdByIdIsSpecialSymbol() throws ClientProtocolException, IOException{
		HttpUriRequest request=new HttpGet("http://localhost:8007/RESTJerseyExample/student/*");
		HttpResponse httpResponse=HttpClientBuilder.create().build().execute(request);
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
	}

	@Test
	public void testNegativeTestCaseGetStudentByIdIsBlank() throws ClientProtocolException, IOException{
		HttpUriRequest request=new HttpGet("http://localhost:8007/RESTJerseyExample/student/");
		HttpResponse httpResponse=HttpClientBuilder.create().build().execute(request);
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_METHOD_NOT_ALLOWED);
	}

}
