package com.RESTJerseyExample.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;

public class TestCasesUsingTestNG
{
	int stcode;
	@Test
	public void accessingStudent() throws Exception{
		
		HttpUriRequest request = new HttpGet( "http://localhost:8007/RESTJerseyExample/student/1243" );
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
		stcode=httpResponse.getStatusLine().getStatusCode();
		AssertJUnit.assertEquals(stcode, 200);
	}
	@Test
	public void updateRecord() throws Exception{
		
		HttpUriRequest request=new HttpPost("http://localhost:8007/RESTJerseyExample/student/include");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
		stcode=httpResponse.getStatusLine().getStatusCode();
		AssertJUnit.assertEquals(stcode, 415);
	}
	@Test 
	public void getallRecords() throws Exception{
		
		HttpUriRequest request=new HttpGet("http://localhost:8007/RESTJerseyExample/student/retrive");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
		stcode=httpResponse.getStatusLine().getStatusCode();
		AssertJUnit.assertEquals(stcode, 200);
		
	}
	@Test(enabled = false)
	public void removeaRecord() throws Exception
	{
		
		HttpUriRequest request=new HttpDelete("http://localhost:8007/RESTJerseyExample/student/1");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
		stcode=httpResponse.getStatusLine().getStatusCode();
		AssertJUnit.assertEquals(stcode, 200);
	}
	@Test
	public void getByGiven(){
		HttpUriRequest request = new HttpGet( "http://localhost:8007/RESTJerseyExample/student/15618" );
		//Object response = given().when().get("http://localhost:8007/RESTJerseyExample/student/15618").then().statusCode(200);
	}


}