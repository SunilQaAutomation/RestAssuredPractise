package com.accenture.APITestingFrameWork.APIS;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import com.accenture.APITestingFrameWork.base.BaseTest;

import io.restassured.response.Response;

public  class ListCustomersAPI extends BaseTest {

	public static Response sendGetRequestToListAllCustomers() {
		Response response = given().auth().basic(prop.getProperty("validSecretKey"), "")
				.get(prop.getProperty("ctustomerAPIEndpoint"));
		return response;

	}
	
	public static Response sendGetRequestToListSingleCustomer(Hashtable<String,String> data) {
		Response response = given().auth().basic(prop.getProperty("validSecretKey"), "")
				.get(prop.getProperty("ctustomerAPIEndpoint") +"/"+data.get("id"));
		return response;
		
	//	https://api.stripe.com/v1/customers/cus_8epDebVEl8Bs2V
	}

}