package com.accenture.APITestingFrameWork.utility;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;

import com.accenture.APITestingFrameWork.base.BaseTest;
import com.accenture.APITestingFrameWork.listeners.ExtentListeners;

public class util extends BaseTest {

	// This method is used to get the data using data provider by sheet name
	public static Object[][] getData(String sheetName) {
		int rowCount = excel.getRowCount(sheetName);
		int columnCount = excel.getColumnCount(sheetName);
		Object[][] data = new Object[rowCount - 1][columnCount];

		for (int rows = 2; rows <= rowCount; rows++) {
			for (int cols = 0; cols < columnCount; cols++) {

				data[rows - 2][cols] = excel.getCellData(sheetName, cols, rows);
			}

		}

		return data;

	}

	// This method is used to get the data using data provider by sheet name same as
	// test mehtod name
	@DataProvider(name = "data")
	public static Object[][] getTestData(Method m) {
		String sheetName = m.getName();
		int rowCount = excel.getRowCount(sheetName);
		int columnCount = excel.getColumnCount(sheetName);
		Object[][] data = new Object[rowCount - 1][columnCount];

		for (int rows = 2; rows <= rowCount; rows++) {
			for (int cols = 0; cols < columnCount; cols++) {

				data[rows - 2][cols] = excel.getCellData(sheetName, cols, rows);
			}

		}

		return data;
	}

	// This method to get the data by dataProvider Using HASH TABLE
	@DataProvider(name = "allTestdata", parallel = true)
	public static Object[][] getAllTestData(Method m) {

		final String DATA_SHEET = "TestData";
		ExcelReader excel = new ExcelReader(".\\TestData\\Test_Data.xlsx");
		int rows = excel.getRowCount(DATA_SHEET);

		// To find the tese case row number
		String testName = m.getName();
		System.out.println("Method Name is:" + testName);
		int testcaseRowNum = 1;

		for (testcaseRowNum = 1; testcaseRowNum <= rows; testcaseRowNum++) {
			String testCaseName = excel.getCellData(DATA_SHEET, 0, testcaseRowNum);
			if (testCaseName.equalsIgnoreCase(testName)) {
				break;
			}
		}
		System.out.println("Test case row number is:" + testcaseRowNum); // 12

		// To find total rows in test case
		int dataStartRowNum = testcaseRowNum + 2; // 14
		System.out.println("data startRowNumber is:" + dataStartRowNum);
		int testrows = 0;
		while (!excel.getCellData(DATA_SHEET, 0, dataStartRowNum + testrows).equals("")) { // 1,15
			testrows++;

		}
		System.out.println("Total number of test data rows:" + testrows);

		// To find total columns in test case
		int colStartColNum = testcaseRowNum + 1;
		int testColumns = 0;
		while (!excel.getCellData(DATA_SHEET, testColumns, colStartColNum).equals("")) {
			testColumns++;
		}
		System.out.println("Total number of test data  Columns:" + testColumns);

		// To print all the data members
		Object data[][] = new Object[testrows][1]; // Since in the test method we have only one argumnet i.e. data
		int i = 0;
		for (int rowNum = dataStartRowNum; rowNum < (dataStartRowNum + testrows); rowNum++) {

			Hashtable<String, String> table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < testColumns; colNum++) {
				// System.out.println(excel.getCellData(DATA_SHEET, colNum, rowNum));
				// data[rowNum - dataStartRowNum][colNum] = excel.getCellData(DATA_SHEET,
				// colNum, rowNum);

				String testData = excel.getCellData(DATA_SHEET, colNum, rowNum); // here we are fetching test data and
																					// it is value in hash table
				String colName = excel.getCellData(DATA_SHEET, colNum, colStartColNum); // here we are fetching the
																						// column and it is key in hash
																						// table
				table.put(colName, testData);

			}
			data[i][0] = table; // creating a hashtable for each test data
			i++;
		}
		return data;

	}

	public static boolean jsonHasKey(String json, String key) {
		JSONObject jsonobject = new JSONObject(json);
		ExtentListeners.testReport.get().info("Validating the Presence of the key : " + key);
		return jsonobject.has(key);

	}

	public static String getJsonKeyValue(String json, String key) {
		JSONObject jsonobject = new JSONObject(json);
		ExtentListeners.testReport.get().info("Validating the value of the key: " + key);

		return jsonobject.get(key).toString();

	}

}
