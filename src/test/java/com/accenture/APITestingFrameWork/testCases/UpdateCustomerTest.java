package com.accenture.APITestingFrameWork.testCases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.accenture.APITestingFrameWork.APIS.UpdateCustomerAPI;
import com.accenture.APITestingFrameWork.base.BaseTest;
import com.accenture.APITestingFrameWork.listeners.ExtentListeners;
import com.accenture.APITestingFrameWork.utility.util;

import io.restassured.response.Response;

public class UpdateCustomerTest extends BaseTest {

	@Test(dataProviderClass = util.class, dataProvider = "allTestdata", priority = 2)
	public void updateSingleCustomer(Hashtable<String, String> data) {
		ExtentListeners.testReport.get().info("Updating a Single Customer");
		Response response = UpdateCustomerAPI.sendPostRequestToUpdateACustomer(data);

		ExtentListeners.testReport.get().info("Updated the Customer");

		response.prettyPrint();

		// one way assertion
//		String actual_id = response.jsonPath().get("id").toString();
//		Assert.assertEquals(actual_id, data.get("id"), "id is not matching");

		// second way assertion by using JSONObject
		Assert.assertTrue(util.jsonHasKey(response.asString(), "id"), "ID key is not present in the Json");
		String actual_id = util.getJsonKeyValue(response.asString(), "id");

		Assert.assertEquals(actual_id, data.get("id"), "ID is mismatching");
		ExtentListeners.testReport.get().info("Id of the updated Customer : " + actual_id);
		Assert.assertEquals(200, response.statusCode());

	}

}
