package com.RESTJerseyExample.test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class CreateStudentTest {

	@Test
	public void insertStudentHappyPath() throws ClientProtocolException, IOException{
		HttpUriRequest request=new HttpPut("http://localhost:8007/RESTJerseyExample/student/include");
		HttpResponse httpResponse=HttpClientBuilder.create().build().execute(request);
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
	}

	
	@Test
	public void insertStudentInCorretPath() throws ClientProtocolException, IOException{
		HttpUriRequest request=new HttpPut("http://localhost:8007/RESTJerseyExample/student/includ");
		HttpResponse httpResponse=HttpClientBuilder.create().build().execute(request);
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_BAD_REQUEST);
	}

	@Test
	public void insertStudentIsInteger() throws ClientProtocolException, IOException{
		HttpUriRequest request=new HttpPut("http://localhost:8007/RESTJerseyExample/student/1");
		HttpResponse httpResponse=HttpClientBuilder.create().build().execute(request);
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
	}

	@Test
	public void insertStudentIsNull() throws ClientProtocolException, IOException{
		HttpUriRequest request=new HttpPut("http://localhost:8007/RESTJerseyExample/student/null");
		HttpResponse httpResponse=HttpClientBuilder.create().build().execute(request);
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
	}

	@Test
	public void insertStudentZero() throws ClientProtocolException, IOException{
		HttpUriRequest request=new HttpPut("http://localhost:8007/RESTJerseyExample/student/0");
		HttpResponse httpResponse=HttpClientBuilder.create().build().execute(request);
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
	}

	@Test
	public void insertStudentIsSapce() throws ClientProtocolException, IOException{
		HttpUriRequest request=new HttpPut("http://localhost:8007/RESTJerseyExample/student/  ");
		HttpResponse httpResponse=HttpClientBuilder.create().build().execute(request);
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
	}

	@Test
	public void insertStudentIsSame() throws ClientProtocolException, IOException{
		HttpUriRequest request=new HttpPut("http://localhost:8007/RESTJerseyExample/student/includeee");
		HttpResponse httpResponse=HttpClientBuilder.create().build().execute(request);
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_BAD_REQUEST);
	}
}
