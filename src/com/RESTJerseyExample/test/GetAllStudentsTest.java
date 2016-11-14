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

public class GetAllStudentsTest {
	
	@Test
	public void getAllStudentsHappyPath() throws ClientProtocolException, IOException{
		HttpUriRequest request= new HttpGet("http://localhost:8007/RESTJerseyExample/student/retrive");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
		int actualResponseCode = httpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(actualResponseCode, 200);
	}
	
	@Test
	public void getAllStudentsWithWrongPath() throws ClientProtocolException, IOException{
		HttpUriRequest request= new HttpGet("http://localhost:8007/RESTJerseyExample/student/retriv");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
	}

}
