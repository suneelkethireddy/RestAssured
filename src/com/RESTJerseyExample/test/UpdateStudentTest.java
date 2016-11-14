package com.RESTJerseyExample.test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;



public class UpdateStudentTest {
	
	@Test
	public void updateStudentHappyPath() throws ClientProtocolException, IOException{
		HttpUriRequest request=new HttpPost("http://localhost:8007/RESTJerseyExample/student/send");
		HttpResponse httpResponse=HttpClientBuilder.create().build().execute(request);
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
	}
	
	@Test
	public void updateStudentNegative() throws ClientProtocolException, IOException{
		HttpUriRequest request=new HttpPost("http://localhost:8007/RESTJerseyExample/student/send/1");
		HttpResponse httpResponse=HttpClientBuilder.create().build().execute(request);
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
	}
	
	@Test
	public void updateStudentNegativeWithID() throws ClientProtocolException, IOException{
		HttpUriRequest request=new HttpPost("http://localhost:8007/RESTJerseyExample/student/send/Id=1");
		HttpResponse httpResponse=HttpClientBuilder.create().build().execute(request);
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
	}
	
	@Test
	public void updateStudentNegativeWithSymbol() throws ClientProtocolException, IOException{
		HttpUriRequest request=new HttpPost("http://localhost:8007/RESTJerseyExample/student/send/-");
		HttpResponse httpResponse=HttpClientBuilder.create().build().execute(request);
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
	}

}
