package com.accenture.APITestingFrameWork.APIS;

import static io.restassured.RestAssured.*;

import java.util.Hashtable;

import com.accenture.APITestingFrameWork.base.BaseTest;

import io.restassured.response.Response;

public class DeleteCustomerAPI extends BaseTest {

	public static Response sendDeleteCustomerRequestWithId(Hashtable<String, String> data) {
		Response response = given().auth().basic(prop.getProperty("validSecretKey"), "")
				.delete(prop.getProperty("ctustomerAPIEndpoint") + "/" + data.get("id"));
		return response;

	}

}
