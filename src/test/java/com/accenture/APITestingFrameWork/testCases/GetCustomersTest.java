package com.accenture.APITestingFrameWork.testCases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.accenture.APITestingFrameWork.APIS.ListCustomersAPI;
import com.accenture.APITestingFrameWork.base.BaseTest;
import com.accenture.APITestingFrameWork.listeners.ExtentListeners;
import com.accenture.APITestingFrameWork.utility.util;
import io.restassured.response.Response;

public class GetCustomersTest extends BaseTest {

	@Test(priority = 1)
	public void listAllCustomers() {
		ExtentListeners.testReport.get().info("Fetching all Customer");
		Response response = ListCustomersAPI.sendGetRequestToListAllCustomers();

		ExtentListeners.testReport.get().info("Fetched all Customer");
		response.prettyPrint();
		Assert.assertEquals(200, response.statusCode());

	}

	@Test(dataProviderClass = util.class, dataProvider = "allTestdata", priority = 2)
	public void listSingleCustomer(Hashtable<String, String> data) {
		
		ExtentListeners.testReport.get().info("Fetching a single Customer");
		Response response = ListCustomersAPI.sendGetRequestToListSingleCustomer(data);

		ExtentListeners.testReport.get().info("Fetched a single Customer");
		ExtentListeners.testReport.get().info("id of the customer: " +data.toString());
		response.prettyPrint();
		Assert.assertEquals(200, response.statusCode());

	}

}
