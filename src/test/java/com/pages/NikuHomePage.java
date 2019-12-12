package com.pages;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.testcases.TestBase;
import com.utilities.ExtentManager;
import com.utilities.ExtentTestManager;

public class NikuHomePage extends TestBase {

	public Method m = null;
	public String sLastRun;
	private Date dLastRun;
	private String sLatestRun;
	private Date dLatestRun;

	@FindBy(how = How.ID, using = "ppm_login_username")
	private WebElement userName;

	@FindBy(how = How.ID, using = "ppm_header_logout")
	private WebElement logout;
	
	@FindBy(how = How.CSS, using = "span.loading_text")
	private WebElement loading;
	
	@FindBy(how = How.CSS, using = "h1[title='About']")
	private WebElement aboutdialog;
	
	@FindBy(how = How.ID, using = "ppm_login_password")
	private WebElement password;

	@FindBy(how = How.ID, using = "jasperframe")
	private WebElement jasperframe;

	@FindBy(how = How.ID, using = "ppm_app")
	private WebElement ppmapp;

	@FindBy(how = How.ID, using = "ppm_login_button")
	private WebElement loginbtn;

	@FindBy(how = How.ID, using = "ppm_header_about")
	private WebElement aboutlink;

	@FindBy(how = How.ID, using = "ppm_header_help")
	private WebElement helplink;

	@FindBy(how = How.ID, using = "ppm_header_learn")
	private WebElement learnlink;

	@FindBy(how = How.ID, using = "ppm_nav_app")
	private WebElement hometab;

	@FindBy(how = How.ID, using = "ppm_refresh")
	private WebElement ppmrefreshbtn;

	@FindAll(@FindBy(how = How.CSS, using = "li#main_library.leaf"))
	private WebElement librarytab;
	
	@FindBy(how = How.CSS, using = "p.message:nth-child(1)")
	private WebElement message;
	

	@FindBy(how = How.ID, using = "ppm_nav_admin")
	private WebElement admintab;

	@FindBy(how = How.ID, using = "apply")
	private WebElement applybtn;

	@FindBy(linkText = "Time Slices")
	private WebElement timeslices;

	@FindBy(linkText = "Process Engines")
	private WebElement processengines;

	@FindBy(linkText = "Content Add-Ins")
	private WebElement contentaddins;

	@FindBy(linkText = "Resources")
	private WebElement resources;

	@FindBy(linkText = "Advanced Reporting")
	private WebElement advancedreporting;
	
	@FindBy(how = How.CSS, using = "div.workflow-icon-datasource")
	private WebElement datasourceicon;
	

	@FindBy(linkText = "Resources")
	private WebElement rosources;

	@FindAll(@FindBy(how = How.CSS, using = "span.ppm_read_only_value"))
	private List<WebElement> aboutinformation; // 0 ppm 1 build version 7
												// jaspersoft version

	@FindBy(how = How.CSS, using = "button.ppm_dialog_close")
	private WebElement aboutclosebtn;

	@FindBy(how = How.CSS, using = "li#resultsList_item1.resources.first.leaf")
	private WebElement resultsList;

	@FindBy(how = How.CSS, using = "tbody.ppm_grid_content")
	private WebElement tableBody;

	@FindBy(how = How.CSS, using = "div.ppm_gridcontent")
	private WebElement contentTableBody;

	@FindBy(how = How.CSS, using = "div#results")
	private WebElement libraryResults;

	@FindAll(@FindBy(how = How.CSS, using = "div.column.two p"))
	private List<WebElement> descriptions;

	/*
	 * @FindAll(@FindBy(how = How.CSS, using = "p#null.resourceDescription"))
	 * private List<WebElement> descriptions;
	 */
	@FindAll(@FindBy(how = How.CSS, using = "tbody.ppm_grid_content tr"))
	private List<WebElement> tablerows;

	@FindBy(how = How.LINK_TEXT, using = "Data Warehouse Schema")
	private WebElement datawarehouseschema;

	@FindBy(how = How.LINK_TEXT, using = "CMN_DB_HISTORY")
	private WebElement libraryOption;
	
	@FindBy(how = How.LINK_TEXT, using = "Last Run")
	private WebElement lastrun;
	
	@FindBy(how = How.CSS, using = "h2[title='Process Engines']")
	private WebElement processenginestitle;
	
	@FindBy(how = How.CSS, using = "h1[title='Resources']")
	private WebElement resourcestitle;
	
	@FindBy(how = How.CSS, using = "input[name='version']")
	private WebElement contentaddinstitle;

	
	@FindAll(@FindBy(how = How.CSS, using = "#reportContainer table.jrPage tbody tr"))
	private List<WebElement> datawarehouseschemarows;

	public NikuHomePage(WebDriver driver) {
		// this.driver = driver;

		//log.debug("Initializing page objects");
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
		PageFactory.initElements(factory, this);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	public synchronized void nikuLogin(String userName, String password) throws InterruptedException, IOException {
		ExtentTestManager.getTest().log(Status.INFO, "Validating login");
		//log.debug("Inside Niku Login");
		dr.get().get(pr.get().getProperty("nikuurl"));
		{
			ExtentTestManager.getTest().log(Status.INFO, "Launched niku url successfully");
			dr.get().manage().window().maximize();
			wait.until(ExpectedConditions.visibilityOf(this.userName));
			System.out.println(dr.get().getTitle());
			sendKeys(this.userName, userName);
			sendKeys(this.password, password);
			click(this.loginbtn);
			verifyElement(this.logout);
		}

	}

	public synchronized void clickLearnLink() throws InterruptedException, IOException {
		ExtentTestManager.getTest().log(Status.INFO, "Clicking learn link");
		//log.debug("Clicking Learn Link");
		click(this.learnlink);
	}
	public synchronized void ValidateLearnLink() throws InterruptedException, IOException {
		ExtentTestManager.getTest().log(Status.INFO, "Validating learn link");
		String windHandleBefore = null;	
		windHandleBefore = dr.get().getWindowHandle();
		if (waitForNewWindow(dr.get(), 10)) {			
			for (String windowhandle : dr.get().getWindowHandles()) {
				dr.get().switchTo().window(windowhandle);
				if (dr.get().getTitle().contains("trainer")) {
					System.out.println(dr.get().getTitle());
					dr.get().manage().window().maximize();
					TimeUnit.SECONDS.sleep(2);
					wait.until((ExpectedConditions.invisibilityOf(this.loading)));
					ReportStatus("pass", "****Learn link validated successfully****");
					Assert.assertTrue(dr.get().getTitle().contains("trainer"),
							"title displayed is " + dr.get().getTitle());
					dr.get().close();
					System.out.println("Learn Link Validated Successfully");
					break;
				}
			}
		}
		dr.get().switchTo().window(windHandleBefore);
	}
	public synchronized void clickAboutDialog() throws InterruptedException, IOException {
		//log.debug("Clicking about link");
		click(this.aboutlink);
		verifyElement(this.aboutdialog);

	}

	public synchronized void checkProductName() throws InterruptedException {
		//log.debug("Inside check product name");
		ExtentTestManager.getTest().log(Status.INFO, "Validating product name");
		wait.until(ExpectedConditions.visibilityOfAllElements(this.aboutinformation));
		verifyEquals(m, pr.get().getProperty("productname"), this.aboutinformation.get(0).getText());
	}

	public synchronized void checkBuildVersion() {
		//log.debug("Inside Check Build Version");
		ExtentTestManager.getTest().log(Status.INFO, "Validating build version");
		verifyEquals(m, pr.get().getProperty("build_version"), this.aboutinformation.get(1).getText());

	}

	public synchronized void checkJaspersoftVersion() throws InterruptedException {
		//log.debug("Inside check Jaspersoft Version");
		ExtentTestManager.getTest().log(Status.INFO, "Validating jaspersoft version");
		JavascriptExecutor jse = (JavascriptExecutor) dr.get();
		jse.executeScript("arguments[0].scrollIntoView()", this.aboutinformation.get(7));
		Thread.sleep(2);
		verifyEquals(m, pr.get().getProperty("jaspersoft_version"), this.aboutinformation.get(7).getText());
		click(this.aboutclosebtn);
	}

	public synchronized void checkLastRunTimeSlicesInitialTime() throws ParseException, InterruptedException {
		//log.debug("check Last Run Time Slices initially");
		ExtentTestManager.getTest().log(Status.INFO, "Validating last run time slices for initial time");
		click(this.admintab);
		ExtentTestManager.getTest().log(Status.INFO, "Clicked Admin tab");
		wait.until(ExpectedConditions.visibilityOf(this.timeslices));
		this.timeslices.click();
		ExtentTestManager.getTest().log(Status.INFO, "Clicked TimeSlices");
		TimeUnit.SECONDS.sleep(3);
		wait.until(ExpectedConditions.visibilityOf(this.lastrun));
		sLastRun = this.tablerows.get(1).findElements(By.tagName("td")).get(10).getText();
		dLastRun = new SimpleDateFormat("MM/dd/yy hh:mm a").parse(sLastRun);
		wait.until(ExpectedConditions.visibilityOf(this.ppmrefreshbtn));
		ReportStatus("info", "***Initial time slice captured " + dLastRun + "****" );
	}

	public synchronized void checkLastRunTimeSlicesFinalTime() throws ParseException, InterruptedException {
		//log.debug("check Last Run Time Slices");
		ExtentTestManager.getTest().log(Status.INFO, "Validating last run time slices for final time");
		click(this.admintab);
		ExtentTestManager.getTest().log(Status.INFO, "Clicked Admin tab");
		wait.until(ExpectedConditions.visibilityOf(this.timeslices));
		this.timeslices.click();
		ExtentTestManager.getTest().log(Status.INFO, "Clicked TimeSlices");
		// TimeUnit.SECONDS.sleep(3);
		wait.until(ExpectedConditions.visibilityOf(this.lastrun));
		sLatestRun = this.tablerows.get(1).findElements(By.tagName("td")).get(10).getText();
		dLatestRun = new SimpleDateFormat("MM/dd/yy hh:mm a").parse(sLatestRun);
		Assert.assertTrue(dLatestRun.compareTo(dLastRun) > 0,
				"Latest Run is " + sLatestRun + " Last Run is " + sLastRun);
		if (dLatestRun.compareTo(dLastRun) > 0) {
			ReportStatus("pass", "****TimeSlices validated successfully Latest Run is " + sLatestRun + " Last Run is " + sLastRun + "****");
		} else {
			ReportStatus("fail", "****Latest Run is " + sLatestRun + " Last Run is " + sLastRun + "****");
		}
	}

	public synchronized void checkProcessEngineColors() throws InterruptedException {
		//log.debug("Inside check process engine colors");
		ExtentTestManager.getTest().log(Status.INFO, "Validating process engine colors");
		List<WebElement> processenginerows;
		String processenginestatuscolor;
		click(this.admintab);
		click(this.processengines);
		TimeUnit.SECONDS.sleep(3);
		wait.until(ExpectedConditions.visibilityOf(this.processenginestitle));
		wait.until(ExpectedConditions.visibilityOfAllElements(this.tablerows));
		processenginerows = this.tablerows;
		System.out.println("Process Engine Rows " + processenginerows.size());
		for (WebElement processenginerow : processenginerows) {
			processenginestatuscolor = processenginerow.findElements(By.tagName("td")).get(7)
					.findElement(By.tagName("img")).getAttribute("name");
			Assert.assertTrue(
					processenginestatuscolor.equalsIgnoreCase("DiamondYellow")
							| processenginestatuscolor.equalsIgnoreCase("DiamondRed")
							| processenginestatuscolor.equalsIgnoreCase("DiamondGrey"),
					"The status colore displayed is " + processenginestatuscolor);
			if (processenginestatuscolor.equalsIgnoreCase("DiamondYellow")
					| processenginestatuscolor.equalsIgnoreCase("DiamondRed")
					| processenginestatuscolor.equalsIgnoreCase("DiamondGrey")) {
				ReportStatus("pass","****The status color displayed is " + processenginestatuscolor + "****");
				System.out.println("****Process Engine colors validated successfully");
			} else {
				ReportStatus("fail","****The status color displayed is " + processenginestatuscolor + "****");
			}

		}
		
	}

	public synchronized void checkContentAddinStatus() throws InterruptedException, IOException {
		//log.debug("inside check content addin status");
		ExtentTestManager.getTest().log(Status.INFO, "Validating content addin status");
		List<WebElement> contentaddinsrows;
		String contentaddinstatus;
		click(this.admintab);
		click(this.contentaddins);
		wait.until(ExpectedConditions.visibilityOf(this.contentaddinstitle));
		contentaddinsrows = this.tablerows;
		for (WebElement contentaddinsrow : contentaddinsrows) {

			contentaddinstatus = contentaddinsrow.findElements(By.tagName("td")).get(5).getText();
			Assert.assertTrue(
					contentaddinstatus.equalsIgnoreCase("Installed")
							| contentaddinstatus.equalsIgnoreCase("Upgrade Ready")
							| contentaddinstatus.equalsIgnoreCase("Upgrade Pending"),
					"The content addins status displayed is " + contentaddinstatus);

			if (contentaddinstatus.equalsIgnoreCase("Installed") | contentaddinstatus.equalsIgnoreCase("Upgrade Ready")
					| contentaddinstatus.equalsIgnoreCase("Upgrade Pending")) {
				ReportStatus("pass","****Content Addins validated successfully****");

				System.out.println("****Content Addins validated successfully*****");
			} else {
				ReportStatus("fail","****The content addin status displayed is " + contentaddinstatus+ "****");

			}
		}
	}

	public synchronized void checkResources() throws InterruptedException, IOException {
		//log.debug("inside check Resources ");
		ExtentTestManager.getTest().log(Status.INFO, "Validating resources");
		List<WebElement> resourcerows;
		String status;
		String type;
		click(this.admintab);
		wait.until(ExpectedConditions.visibilityOf(this.resources));
		click(this.resources);
		wait.until(ExpectedConditions.visibilityOf(this.resourcestitle));
		wait.until(ExpectedConditions.visibilityOfAllElements(this.tablerows));
		resourcerows = this.tablerows;
		for (WebElement resourcerow : resourcerows) {
			if (resourcerow.findElements(By.tagName("td")).get(3).getText().equalsIgnoreCase("od_admin@dummy.com")
					| resourcerow.findElements(By.tagName("td")).get(3).getText()
							.equalsIgnoreCase("ca_portal@dummy.com")) {

				status = resourcerow.findElements(By.tagName("td")).get(7).getText();
				type = resourcerow.findElements(By.tagName("td")).get(6).getText();
				Assert.assertTrue(status.equalsIgnoreCase("Active"), "The status displayed is " + status);
				Assert.assertTrue(type.equalsIgnoreCase("Internal"), "The type displayed is " + type);
				System.out.println("****Resources Validated successfully****");
				ReportStatus("pass","****Resources validated successfully****");
			}

		}

	}

	public synchronized void checkAdvanceReporting() throws InterruptedException, IOException {
		//log.debug("Inside advance Reporting");
		System.out.println("Inside advance Reporting");
		ExtentTestManager.getTest().log(Status.INFO, "Validating Advance Reporting");
		int i;
		int rows;
		wait.until(ExpectedConditions.visibilityOf(this.hometab));
		click(this.hometab);
		click(this.advancedreporting);
		TimeUnit.SECONDS.sleep(10);
		isElementPresent((this.jasperframe));
		dr.get().switchTo().frame(this.jasperframe);
		click(this.librarytab);
		TimeUnit.SECONDS.sleep(10);
		wait.until(ExpectedConditions.visibilityOf(this.resultsList));
		rows = this.descriptions.size();
		System.out.println("Advance Reporting Rows " + rows);
		List<WebElement> descriptions = this.descriptions;
		for (i = 2; i < (rows / 2); i = i + 2) {
			System.out.println("****" + i + descriptions.get(i).getText());
			Assert.assertTrue(descriptions.get(i).getText().contains("PMO Accelerator"),
					"The Description had " + descriptions.get(i).getText());
		}
		System.out.println("****Advance Report Validated successfully****");
		ReportStatus("pass", "****Advance Reporting validate successfully****");
	}

	public synchronized void checkJasperSoftReport() throws InterruptedException, IOException {
		//log.debug("inside validate jaspersoft report");
		ExtentTestManager.getTest().log(Status.INFO, "Validating jasper soft report");
		click(this.datawarehouseschema);
		wait.until(ExpectedConditions.visibilityOf(this.message));
		click(this.libraryOption);
		TimeUnit.SECONDS.sleep(2);
		click(this.applybtn);
		wait.until(ExpectedConditions.visibilityOfAllElements(this.datawarehouseschemarows));
		verifyEquals(m, "CMN_DB_HISTORY", this.datawarehouseschemarows.get(12).getText().trim());
		dr.get().switchTo().defaultContent();
		System.out.println("****Jaspersoft validation is complete****");
		ReportStatus("pass","****Jasepersoft report validated successfully****");
	}

	

	public synchronized void LogOut() {
		//log.debug("Loggin out");
		dr.get().switchTo().defaultContent();
		this.logout.click();
		wait.until(ExpectedConditions.visibilityOf(this.userName));
		// ExtentTestManager.getTest().log(Status.PASS, "logged out
		// successfully");
		System.out.println("***Logged Out Successfully****");
		ReportStatus("pass","****Logged out Successfully****");
		
	}

}
