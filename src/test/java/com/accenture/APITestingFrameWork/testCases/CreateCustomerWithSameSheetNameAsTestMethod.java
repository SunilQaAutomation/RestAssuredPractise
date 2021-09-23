/**
 * 
 */
package com.accenture.APITestingFrameWork.testCases;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.accenture.APITestingFrameWork.base.BaseTest;
import com.accenture.APITestingFrameWork.utility.util;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * @author sunil.kumar.battula
 * 
 * 
 *         This test case is to get the data thru the data provider using the
 *         same sheet name as method name
 *
 */
public class CreateCustomerWithSameSheetNameAsTestMethod extends BaseTest {

	// Test case with valid key
	@Test(dataProviderClass = util.class, dataProvider = "data")
	public void ValidCustomerkey(String name, String description, String email, String phone, String line1,
			String line2, String state, String preflocal1, String preflocal2) {
		Response response = given().auth().basic(prop.getProperty("validSecretKey"), "").formParam("name", name)
				.formParam("description", description).formParam("email", email).formParam("phone", phone)
				.formParam("address[line1]", line1).formParam("address[line2]", line2)
				.formParam("address[state]", state).formParam("preferred_locales[0]", preflocal1)
				.formParam("preferred_locales[1]", preflocal2).post(prop.getProperty("ctustomerAPIEndpoint"));

		response.prettyPrint();
		Assert.assertEquals(200, response.statusCode());
	}

	// Test Case to check the invalid key
	@Test(dataProviderClass = util.class, dataProvider = "data")
	public void inValidCustomerkey(String name, String description, String email, String phone, String line1,
			String line2, String state, String preflocal1, String preflocal2) {
		Response response = given().contentType(ContentType.JSON).auth().basic(prop.getProperty("inValidSecretKey"), "")
				.formParam("name", name).formParam("description", description).formParam("email", email)
				.formParam("phone", phone).formParam("address[line1]", line1).formParam("address[line2]", line2)
				.formParam("address[state]", state).formParam("preferred_locales[0]", preflocal1)
				.formParam("preferred_locales[1]", preflocal2).post(prop.getProperty("ctustomerAPIEndpoint"));

		response.prettyPrint();
		Assert.assertEquals(200, response.statusCode());

	}

}
