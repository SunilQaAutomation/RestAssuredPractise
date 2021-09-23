package com.accenture.APITestingFrameWork.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;
//	public static ExtentHtmlReporter htmlReporter;

	public static ExtentReports createInstance(String fileName) {
		ExtentHtmlReporter htmlReporter= new ExtentHtmlReporter(fileName);

		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle("Automaton Report");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("V14_Regression");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", "Sunil Battula");
		extent.setSystemInfo("Organization", "Accenture");
		extent.setSystemInfo("Build no", "MD1");

		return extent;
	}

	/*
	 * public static String screenshotPath; public static String screenshotName;
	 * 
	 * public static void captureScreenshot() {
	 * 
	 * File scrFile = ((TakesScreenshot)
	 * DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
	 * 
	 * Date d = new Date(); screenshotName = d.toString().replace(":",
	 * "_").replace(" ", "_") + ".jpg";
	 * 
	 * try { FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") +
	 * "\\reports\\" + screenshotName)); } catch (IOException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * 
	 * }
	 */

}
