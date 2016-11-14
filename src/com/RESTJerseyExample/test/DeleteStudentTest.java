package com.RESTJerseyExample.test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteStudentTest {

	@Test(enabled = false)
	public void deleteStudentHappyPath() throws ClientProtocolException, IOException{
		HttpUriRequest request= new HttpDelete("http://localhost:8007/RESTJerseyExample/student/15616");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
		int actualResponseCode = httpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(actualResponseCode, 200);	
	}

	@Test
	public void deleteStudentIsAString() throws ClientProtocolException, IOException{
		HttpUriRequest request= new HttpDelete("http://localhost:8007/RESTJerseyExample/student/f");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
	}

	@Test
	public void deleteStudentNotInDatabase() throws ClientProtocolException, IOException{
		HttpUriRequest request= new HttpDelete("http://localhost:8007/RESTJerseyExample/student/999999");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_BAD_REQUEST);
	}

	@Test
	public void deleteStudentIsASpecialCharacter() throws ClientProtocolException, IOException{
		HttpUriRequest request= new HttpDelete("http://localhost:8007/RESTJerseyExample/student/*");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
	}

	@Test(enabled = false)
	public void deleteStudentWithEmptySpace() throws ClientProtocolException, IOException{
		HttpUriRequest request= new HttpDelete("http://localhost:8007/RESTJerseyExample/student/ 111111");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_METHOD_NOT_ALLOWED);
	}
	
	@Test
	public void deleteStudentWithNegativeId() throws ClientProtocolException, IOException{
		HttpUriRequest request= new HttpDelete("http://localhost:8007/RESTJerseyExample/student/-1");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_BAD_REQUEST);
	}

}
