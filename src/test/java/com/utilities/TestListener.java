package com.utilities;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.testcases.TestBase;

public class TestListener extends TestBase implements ITestListener, ISuiteListener {

	public String messageBody;

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

	public void onFinish(ISuite arg0) {

		MonitoringMail mail = new MonitoringMail();
		
		try {
			messageBody = "Hi All,"+"<br><br>"+" Please find the Test Execution Report Below"+"<br>"+ "http://" + InetAddress.getLocalHost().getHostAddress()
					+ ":8000/TestReport/Test-Automaton-Report.html"+"<br><br><br>"+ "Regards,"+"<br>"+"HealthCheck Automation";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
			System.out.println("Mail sent successfully");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestStart(ITestResult result) {
		System.out.println(("****Running test method " + result.getMethod().getMethodName() + "****"));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	public synchronized void onTestSuccess(ITestResult result) {
		System.out.println("****Executed " + result.getMethod().getMethodName() + " test successfully****");
		ITestContext context = result.getTestContext();
		driver = (WebDriver) context.getAttribute("driver");
		ReportStatus("pass", "****" + result.getMethod().getMethodName() + " Passed****");
	}

	public synchronized void onTestFailure(ITestResult result) {
		System.out.println("****Test execution " + result.getMethod().getMethodName() + " failed****");
		System.out.println((result.getMethod().getMethodName() + " failed!"));

		ITestContext context = result.getTestContext();
		driver = (WebDriver) context.getAttribute("driver");
		ReportStatus("fail", result.getThrowable() + " failed");
//		try {
//			ExtentTestManager.getTest().fail("Screenshot", MediaEntityBuilder
//					.createScreenCaptureFromPath("screenshots/" + captureScreenshot(dr.get())).build());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable() + " failed");
	}

	public synchronized void onTestSkipped(ITestResult result) {
		System.out.println("****Test " + result.getMethod().getMethodName() + " skipped****");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

}
