package com.accenture.APITestingFrameWork.APIS;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import com.accenture.APITestingFrameWork.base.BaseTest;

import io.restassured.response.Response;

public class UpdateCustomerAPI extends BaseTest {
	
	public static Response sendPostRequestToUpdateACustomer(Hashtable<String,String> data) {
		Response response = given().auth().basic(prop.getProperty("validSecretKey"), "")
				.post(prop.getProperty("ctustomerAPIEndpoint") +"/"+data.get("id"));
		return response;
		

	}


}
