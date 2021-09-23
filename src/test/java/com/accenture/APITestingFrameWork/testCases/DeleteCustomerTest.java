package com.accenture.APITestingFrameWork.testCases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.accenture.APITestingFrameWork.APIS.DeleteCustomerAPI;
import com.accenture.APITestingFrameWork.base.BaseTest;
import com.accenture.APITestingFrameWork.listeners.ExtentListeners;
//import com.accenture.APITestingFrameWork.listeners.ExtentListeners;
import com.accenture.APITestingFrameWork.utility.util;

import io.restassured.response.Response;

public class DeleteCustomerTest extends BaseTest {

	@Test(dataProviderClass = util.class, dataProvider = "allTestdata", priority = 1)
	public void deleteCustomer(Hashtable<String, String> data) {
		ExtentListeners.testReport.get().info("Deleting a single Customer");
		Response response = DeleteCustomerAPI.sendDeleteCustomerRequestWithId(data);

		ExtentListeners.testReport.get().info("Customer is deleted!...");
		
		response.prettyPrint();

		// one way assertion
//		String actual_id = response.jsonPath().get("id").toString();
//		Assert.assertEquals(actual_id, data.get("id"), "id is not matching");

		// second way assertion by using JSONObject
		
//		System.out.println("presence of check of object key is: " +util.jsonHasKey(response.asString(), "object"));
//		System.out.println("presence of check of deleted key is: " +util.jsonHasKey(response.asString(), "deleted"));
//		System.out.println("presence of check of id key is: " +util.jsonHasKey(response.asString(), "id"));
//		
//		System.out.println("object key value is : " + util.getJsonKeyValue(response.asString(), "object"));
//		System.out.println("deleted key value is : " + util.getJsonKeyValue(response.asString(), "deleted"));
//		System.out.println("id key value is : " + util.getJsonKeyValue(response.asString(), "id"));
		
		Assert.assertTrue(util.jsonHasKey(response.asString(), "id"), "ID key is not present in the Json");
		Assert.assertEquals((util.getJsonKeyValue(response.asString(), "id")), data.get("id"),"ID is mismatching");
		ExtentListeners.testReport.get().info("Customer id is:" +data.toString());
		Assert.assertEquals(200, response.statusCode());

	}

}
