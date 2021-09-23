package com.accenture.APITestingFrameWork.testCases;

/**
 * @author sunil.kumar.battula
 * 
 * 
 * 		This test case is to get the data thru the data provider by getting all differnet test data in one sheet
 *
 */

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.accenture.APITestingFrameWork.APIS.CreateCustomerAPI;
import com.accenture.APITestingFrameWork.base.BaseTest;
import com.accenture.APITestingFrameWork.listeners.ExtentListeners;
import com.accenture.APITestingFrameWork.utility.util;

import io.restassured.response.Response;

public class CreateCustomerWithAllInOneSheetHashTableTest extends BaseTest {

	// Test case with valid key and here passing all the columns by using hash table
	@Test(dataProviderClass = util.class, dataProvider = "allTestdata", priority = 2)
	public void ValidingCustomerWithValidSecrectkey(Hashtable<String, String> data) {
		ExtentListeners.testReport.get().info("Creating a Customer with a valid secrect key !....");
		Response response = CreateCustomerAPI.sendPostRequestWithValidSecrectKey(data);
		ExtentListeners.testReport.get().info("Customer is created!...");
		ExtentListeners.testReport.get().info("Customer data of the new Customer is : " + data.toString());

		response.prettyPrint();
//				one way of assertion
//		String actual_name=response.jsonPath().get("name").toString();
//		Assert.assertEquals(actual_name, data.get("name"),"name is not matching");
//		

		// second way assertion by using JSONObject

		Assert.assertTrue(util.jsonHasKey(response.asString(), "id"), "id key is not present in the Json");
		// Assert.assertTrue(util.jsonHasKey(response.asString(), "$.address.line1"),
		// "address line1 key is not present in the Json");

		String actual_id = util.getJsonKeyValue(response.asString(), "id");
		ExtentListeners.testReport.get().info("Customer id of the new Customer is : " + actual_id);

		Assert.assertEquals((util.getJsonKeyValue(response.asString(), "email")), data.get("email"),
				"email is mismatching");
		// Assert.assertEquals((util.getJsonKeyValue(response.asString(),
		// "$.address.line1")), data.get("line1"),"address line1 is mismatching");

		Assert.assertEquals(200, response.statusCode());
	}

	// Test Case to check the invalid key and we are passing partial columns(not all
	// which we can do) by using Hash Table

	@Test(dataProviderClass = util.class, dataProvider = "allTestdata", priority = 1)
	public void inValidingCustomerWithValidSecrectkey(Hashtable<String, String> data) {
		ExtentListeners.testReport.get().info("Creating a Customer with a Invalid secrect key !....");
		Response response = CreateCustomerAPI.sendPostRequestWithInValidSecrectKey(data);
		ExtentListeners.testReport.get().info("Customer is created!...");
		ExtentListeners.testReport.get().info("Customer data of the new Customer is : " + data.toString());

		response.prettyPrint();

		// one way of assertion
//		String actual_name=response.jsonPath().get("name").toString();
//		Assert.assertEquals(actual_name, data.get("name"),"name is not matching");

		// second way assertion by using JSONObject
		Assert.assertTrue(util.jsonHasKey(response.asString(), "id"), "id key is not present in the Json");
		String actual_id = util.getJsonKeyValue(response.asString(), "id");
		ExtentListeners.testReport.get().info("Customer id of the new Customer is : " + actual_id);

		Assert.assertEquals((util.getJsonKeyValue(response.asString(), "email")), data.get("email"),
				"email is mismatching");
		Assert.assertEquals(200, response.statusCode());

//		System.out.println("presence of check of object key is: " +util.jsonHasKey(response.asString(), "object"));
//		System.out.println("presence of check of deleted key is: " +util.jsonHasKey(response.asString(), "deleted"));
//		System.out.println("presence of check of id key is: " +util.jsonHasKey(response.asString(), "id"));
//		
//		System.out.println("object key value is : " + util.getJsonKeyValue(response.asString(), "object"));
//		System.out.println("deleted key value is : " + util.getJsonKeyValue(response.asString(), "deleted"));
//		System.out.println("id key value is : " + util.getJsonKeyValue(response.asString(), "id"));

	}

}
