package com.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.pages.NikuHomePage;
import com.utilities.ExtentTestManager;
import com.utilities.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static ThreadLocal<Properties> pr = new ThreadLocal<Properties>();
	public Properties prop;

	public static ThreadLocal<URL> url = new ThreadLocal<URL>();
	public URL remoteAddress;

	public ChromeOptions capabilities;

	// public static ThreadLocal<WebDriverWait> wt = new
	// ThreadLocal<WebDriverWait>();
	public static WebDriverWait wait;

	public String targetLocation;
	public File screenshotFile;
	public String screenShotName;

	//public Logger log = Logger.getLogger(TestBase.class);

	public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();
	public WebDriver driver;

	public static ThreadLocal<NikuHomePage> nhp = new ThreadLocal<NikuHomePage>();
	public NikuHomePage nikuhomepage;

	@BeforeMethod
	public synchronized void setUp(ITestContext context) throws IOException, InterruptedException {

		// Start Docker
		// Docker.StartDockerAndGrid();

		// log
		// log=Logger.getLogger("devpinoyLogger");
		//PropertyConfigurator.configure("./src/test/java/com/properties/log4j.properties");

		// softassert

		// Properties file setup
		
		//log.debug("Initializing Properties and Driver");
		String propertyfilepath = System.getProperty("user.dir");
		File file = new File(propertyfilepath + "/src/test/java/com/properties/env.properties");
		FileInputStream fileInput = null;

		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prop = new Properties();
		pr.set(prop);
		try {
			pr.get().load(fileInput);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Webdriver Setup

		
		 WebDriverManager.chromedriver().setup(); 
		 //WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
		 driver = new ChromeDriver();
		 dr.set(driver); 
		// log.debug("Chrome Driver Launched");
		 context.setAttribute("driver", driver); 
		 wait = new WebDriverWait(dr.get(), 30);
		 nikuhomepage= new NikuHomePage((dr.get())); 
		 nhp.set(nikuhomepage);
		 

		/* *********Docker Execution ********/

	/*	remoteAddress = new URL("http://localhost:4444/wd/hub");
		url.set(remoteAddress);

		ChromeOptions capabilities = new ChromeOptions(); //
		driver = new RemoteWebDriver(url.get(), capabilities);
		dr.set(driver);

		context.setAttribute("driver", dr.get());

		wait = new WebDriverWait(driver, 30);

		nikuhomepage = new NikuHomePage((dr.get()));
		nhp.set(nikuhomepage);*/

		/* *****************************************************************/

		/*
		 * ***************for Headless
		 * execution;***********************************************************
		 * ******* ChromeOptions options= new ChromeOptions();
		 * options.addArguments("--no-sandbox");
		 * options.addArguments("--headless");
		 * options.addArguments("disable-infobars"); // disabling infobars
		 * options.addArguments("--disable-extensions"); // disabling extensions
		 * options.addArguments("--disable-gpu"); // applicable to windows os
		 * only options.addArguments("--disable-dev-shm-usage"); // overcome
		 * limited resource problems //
		 * options.setBinary("/opt/google/chrome/google-chrome"); driver = new
		 * ChromeDriver(options); context.setAttribute("driver", driver);
		 ***********************************************************************************************************/

		/*
		 * *************************Html Unit
		 * Driver********************************** driver = new
		 * HtmlUnitDriver(); context.setAttribute("driver", driver);
		 *********************************************************************************/

	}

	public synchronized String captureScreenshot(WebDriver driver) {

		File file;
		File targetFile = null;
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // get
																							// timestamp
		screenShotName = timeStamp + ".png";
		String fileSeperator = System.getProperty("file.separator");
		String reportsPath = System.getProperty("user.dir") + fileSeperator + "TestReport" + fileSeperator
				+ "screenshots";
		// System.out.println("Screen shots reports path - " + reportsPath);
		try {
			file = new File(reportsPath); // Set
			if (!file.exists()) {
				if (file.mkdirs()) {
					System.out.println("Directory: " + file.getAbsolutePath() + " is created!");
				} else {
					System.out.println("Failed to create directory: " + file.getAbsolutePath());
				}
			}
			screenshotFile = ((TakesScreenshot) dr.get()).getScreenshotAs(OutputType.FILE);
			targetLocation = reportsPath + fileSeperator + fileSeperator + screenShotName;// define
			targetFile = new File(targetLocation);
			FileHandler.copy(screenshotFile, targetFile);
		} catch (FileNotFoundException e) {
			System.out.println("File not found exception occurred while taking screenshot " + e.getMessage());
		} catch (IOException e) {
			System.out.println("An exception occured while taking screenshot " + e.getCause());
		} catch (Exception e) {
			System.out.println("An exception occurred while taking screenshot " + e.getCause());
		}
		return screenShotName;
	}

	public synchronized String getTestClassName(String testName) {
		String[] reqTestClassname = testName.split("\\.");
		int i = reqTestClassname.length - 1;
		System.out.println("Required Test Name : " + reqTestClassname[i]);
		return reqTestClassname[i];
	}

	public synchronized void ReportStatus(String status, String msg) {

		if (status.equalsIgnoreCase("pass")) {

			try {
				ExtentTestManager.getTest().pass("Screenshot", MediaEntityBuilder
						.createScreenCaptureFromPath("screenshots/" + captureScreenshot(dr.get())).build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ExtentTestManager.getTest().log(Status.PASS, msg);
		} else if (status.equalsIgnoreCase("info")) {

			ExtentTestManager.getTest().log(Status.INFO, msg);
		} else {
			try {
				ExtentTestManager.getTest().fail("Screenshot", MediaEntityBuilder
						.createScreenCaptureFromPath("screenshots/" + captureScreenshot(dr.get())).build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ExtentTestManager.getTest().log(Status.FAIL, msg);
		}
	}

	public synchronized void sendKeys(WebElement element, String value) {
		if (isElementPresent(element)) {
			ExtentTestManager.getTest().log(Status.INFO, "Entered the value " + value + " in " + element.getText());
			element.sendKeys(value);
		} else {
			ReportStatus("fail", element + " is not present");
			Assert.assertTrue(false);
		}
	}

	public synchronized void click(WebElement element) throws InterruptedException {
		String elementText = null;
		if (isElementPresent(element)) {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			ExtentTestManager.getTest().log(Status.INFO, "Clicking" + element.getText());
			try {
				elementText = element.getText();
				element.click();
				ReportStatus("info", "clicked the element " + elementText);
				System.out.println("clicked " + elementText);
			} catch (ElementClickInterceptedException eie) {
				TimeUnit.SECONDS.sleep(20);
				element.click();
				System.out.println("Clicked " + elementText + " from Intercepted Exception");
			} catch (StaleElementReferenceException sere) {
				TimeUnit.SECONDS.sleep(20);
				System.out.println("Clicked " + elementText + " from Stale Element Reference Exception");
			} catch (NoSuchElementException nee) {
				TimeUnit.SECONDS.sleep(20);
				element.click();
				System.out.println("Clicked " + elementText + " from No Such Element Exception");
			} catch (Exception e) {
				e.printStackTrace();
				ReportStatus("fail", e.getMessage());
				System.out.println(e.getMessage());

			}
		}
	}

	public synchronized boolean waitForNewWindow(WebDriver driver, int timeout) {
		boolean flag = false;
		int counter = 0;
		while (!flag) {
			try {
				Set<String> winId = driver.getWindowHandles();
				if (winId.size() > 1) {

					flag = true;
					return flag;
				}
				Thread.sleep(1000);
				counter++;
				if (counter > timeout) {
					return flag;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
		}
		return flag;
	}

	public synchronized void verifyElement(WebElement element) throws IOException {
		try {
			Assert.assertTrue(isElementPresent(element), " element is not displayed");
			ReportStatus("pass", "****" + element.getText() + " is verified successfully****");
		} catch (Throwable t) {
			ReportStatus("fail", element + " is not displayed");
			System.out.println("****" + element.getText() + "Element verification failed****");
		}

	}

	public synchronized void verifyEquals(Method m, String expected, String actual) {
		try {

			Assert.assertEquals(actual, expected);
			ReportStatus("pass", "****" + expected + " is verfied successfully****");
			System.out.println(expected + " is verfied successfully");

		} catch (Throwable t) {
			ReportStatus("fail", "Expected version " +expected + " but found " + actual);
			System.out.println("Expected version " +expected + " but found " + actual);
		}
	}

	/*
	 * public void reportPass(String msg) { captureScreenshot(driver,result);
	 * ExtentTestManager.getTest().fail("Screenshot",
	 * MediaEntityBuilder.createScreenCaptureFromPath(
	 * "screenshots/ClarityPPMHealthCheck/"+screenShotName).build());
	 * ExtentTestManager.getTest().log(Status.PASS, msg); }
	 */

	public synchronized boolean isElementPresent(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			if (element.isDisplayed()) {
				return true;
			}
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	@AfterMethod
	public synchronized void teardown() {
		if (dr.get() != null) {
			dr.get().quit();
		}
	}

}