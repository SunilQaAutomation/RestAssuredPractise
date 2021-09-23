package com.accenture.APITestingFrameWork.APIS;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import com.accenture.APITestingFrameWork.base.BaseTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateCustomerAPI extends BaseTest {

	public static Response sendPostRequestWithValidSecrectKey(Hashtable<String, String> data) {

		Response response = given().auth().basic(prop.getProperty("validSecretKey"), "")
				.formParam("name", data.get("name")).formParam("description", data.get("description"))
				.formParam("email", data.get("email")).formParam("phone", data.get("phone"))
				.formParam("address[line1]", data.get("line1")).formParam("address[line2]", data.get("line2"))
				.formParam("address[state]", data.get("state"))
				.formParam("preferred_locales[0]", data.get("preferred_locales1"))
				.formParam("preferred_locales[1]", data.get("preferred_locales2"))
				.post(prop.getProperty("ctustomerAPIEndpoint"));

		return response;

	}

	public static Response sendPostRequestWithInValidSecrectKey(Hashtable<String, String> data) {

		Response response = given().contentType(ContentType.JSON).auth().basic(prop.getProperty("inValidSecretKey"), "")
				.formParam("name", data.get("name")).formParam("description", data.get("description"))
				.formParam("address[state]", data.get("state"))
				.formParam("preferred_locales[0]", data.get("preferred_locales1"))
				.post(prop.getProperty("ctustomerAPIEndpoint")); 
		
		return response;

	}

}
