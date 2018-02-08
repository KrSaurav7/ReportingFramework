package com.wipro.atm.webdriver.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.wipro.atm.webdriver.driver.Driver;

public class CustomTestListener implements ITestListener, ISuiteListener, IInvokedMethodListener {

	String filePath = "./logging/Screenshots/";
	WebDriver driver = Driver.intializeDriver(Constants.Browser);
	Logger logger = LogManager.getRootLogger();


	public void onTestFailure(ITestResult result) {		
		logger.error("Error ocured in test " + result.getName() + " has failed"  );
		String methodName ="TestMethodFailed_" + result.getName().toString().trim();
		takeScreenShot(methodName);

	}

	public void onTestSuccess(ITestResult result) {
		String methodName = "TestSuccessMethod_" + result.getName().toString().trim();
		takeScreenShot(methodName);

	}

	public void takeScreenShot(String methodName) {

		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile,
					new File(filePath + methodName + ".png"));
			System.out.println("***Placed screen shot in " + filePath + " ***");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void onFinish(ITestContext arg0) {		

	}

	public void onStart(ITestContext arg0) {		

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {		

	}

	public void onTestSkipped(ITestResult arg0) {
		

	}

	public void onTestStart(ITestResult result) {
		

	}

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		
		
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		
		
	}

	public void onStart(ISuite suite) {
		
		
	}

	public void onFinish(ISuite suite) {
		
		
	}
}
