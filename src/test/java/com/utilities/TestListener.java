package com.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.testcases.TestBase;

public class TestListener extends TestBase implements ITestListener {

	public synchronized void onStart(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " started ***");
		String fileSeperator = System.getProperty("file.separator");
		String reportsPath = System.getProperty("user.dir") + fileSeperator + "TestReport" + fileSeperator
				+ "screenshots";
		File file = new File(reportsPath);
		if (file.exists()) {
			try {
				FileUtils.deleteDirectory(file);
				System.out.println(("***Screenshots folder is deleted ***"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void onFinish(ITestContext context) {
		System.out.println(("****Test Suite " + context.getName() + " ending****"));
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	public void onTestStart(ITestResult result) {
		System.out.println(("****Running test method " + result.getMethod().getMethodName() + "****"));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
		ExtentManager.getInstance().flush();
	}

	public synchronized void onTestSuccess(ITestResult result) {
		System.out.println("****Executed " + result.getMethod().getMethodName() + " test successfully****");
		ITestContext context = result.getTestContext();
		WebDriver driver = (WebDriver) context.getAttribute("driver");
		ReportStatus("pass", result.getMethod().getMethodName() + " Passed");
		ExtentManager.getInstance().flush();
	}

	public synchronized void onTestFailure(ITestResult result) {
		System.out.println("****Test execution " + result.getMethod().getMethodName() + " failed****");
		System.out.println((result.getMethod().getMethodName() + " failed!"));

		ITestContext context = result.getTestContext();
		WebDriver driver = (WebDriver) context.getAttribute("driver");
		ReportStatus("fail", result.getThrowable() + " failed");
//		try {
//			ExtentTestManager.getTest().fail("Screenshot", MediaEntityBuilder
//					.createScreenCaptureFromPath("screenshots/" + captureScreenshot(dr.get())).build());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable() + " failed");
		ExtentManager.getInstance().flush();
	}

	public synchronized void onTestSkipped(ITestResult result) {
		System.out.println("****Test " + result.getMethod().getMethodName() + " skipped****");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
		ExtentManager.getInstance().flush();
	}

	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
		ExtentManager.getInstance().flush();
	}



}
