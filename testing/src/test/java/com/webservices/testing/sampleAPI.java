package com.webservices.testing;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class sampleAPI {
@Test(priority=1)
	public void firstapitest() {
		// TODO Auto-generated method stub
	// get url
		Response resp=RestAssured.get("http://dummy.restapiexample.com/api/v1/employees");
		
		// response data 
		String data=resp.asString();
		System.out.println("Data contained at the end point:" +data);
		
		// Response code 
		int statuscode=resp.getStatusCode();
		System.out.println("statuscode of the API:"+statuscode);
		Assert.assertEquals(statuscode, 200);

		//content type
		String content=resp.contentType();
		System.out.println("the content type of the resquest:" +content);
		Assert.assertEquals(content, "text/html; charset=UTF-8");
		
		Headers header=resp.headers();
		System.out.println("header of the API:" +header);
		
		//response time in ms
		long resptime=resp.getTime();
		System.out.println("response time of the API:" +resptime);


		

	}

@Test(priority=2)
public void postmethod() {
	RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
	
	//Response resp=RestAssured.post("http://dummy.restapiexample.com/api/v1/create");
	//create request object to send body in the request
	RequestSpecification http=RestAssured.given();
	
	// create object for  post 
	JSONObject reqparam= new JSONObject();
	reqparam.put("name", "postman5");
	reqparam.put("salary", "5005");
	reqparam.put("age", "235");
	
	//to get this we need request specifications
	http.headers("Content-Type","application/json");
	
	//add jason to body
	
	http.body(reqparam.toJSONString());
	
	// post request and capture
	Response respbodypost=http.request(Method.POST,"/create");
	
	//capture response body
	String bodyresponse= respbodypost.getBody().asString();
	System.out.println("post body of the API:"+bodyresponse);
	
	Assert.assertEquals(bodyresponse.contains("man"),true);
	
	
	
	
	int statuscode1=respbodypost.getStatusCode();
	System.out.println("statuscode of the API:"+statuscode1);
	Assert.assertEquals(statuscode1, 200);
}

}
