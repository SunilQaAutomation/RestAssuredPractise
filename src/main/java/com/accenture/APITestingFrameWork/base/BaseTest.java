package com.accenture.APITestingFrameWork.base;

import org.testng.annotations.BeforeSuite;

import com.accenture.APITestingFrameWork.utility.ExcelReader;

import io.restassured.RestAssured;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

	private FileInputStream fis;
	public static Properties prop;
	public static ExcelReader excel = new ExcelReader(".\\TestData\\Test_Data.xlsx");

	// Constructor to invoke the prop file
	public BaseTest() {

		prop = new Properties();
		try {
			fis = new FileInputStream(".\\config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@BeforeSuite
	public void setUp() {

		RestAssured.baseURI = prop.getProperty("baseURI");
		RestAssured.basePath = prop.getProperty("basePath");

	}

}
