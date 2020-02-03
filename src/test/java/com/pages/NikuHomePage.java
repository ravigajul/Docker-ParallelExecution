package com.pages;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.Status;
import com.resources.testdata.TestConstants;
import com.testcases.TestBase;
import com.utilities.ExtentTestManager;
import com.utilities.RestUtil;

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

	@FindBy(how = How.CSS, using = "input[name='company_name']")
	private WebElement companyname;

	@FindBy(how = How.CSS, using = "div#page_bizdev\\.companyList_collapseFilter>div>button:nth-child(1)")
	private WebElement filter;

	@FindBy(how = How.LINK_TEXT, using = "CA On Demand")
	private WebElement caondemand;

	@FindBy(how = How.LINK_TEXT, using = "Properties")
	private WebElement properties;

	@FindBy(how = How.LINK_TEXT, using = "Shared Accounts List")
	private WebElement sharedaccountslist;

	@FindBy(how = How.LINK_TEXT, using = "Clarity od_admin account")
	private WebElement clarityodadminaccount;

	@FindBy(how = How.CSS, using = "input[name='login']")
	private WebElement loginfield;

	@FindBy(how = How.CSS, using = "input[name='password']")
	private WebElement passwordfield;

	@FindBy(how = How.CSS, using = "img.ppm_filter_expand")
	private WebElement expandfilter;

	@FindBy(how = How.ID, using = "ppm_refresh")
	private WebElement ppmrefreshbtn;

	@FindBy(how = How.CSS, using = "li#main_library>p.wrap.button")
	private WebElement librarytab;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'CA PPM')]")
	private WebElement dbconnectioncappm;

	@FindBy(how = How.XPATH, using = "//li[@id='resultsList_item8']//a[contains(text(),'Data Warehouse')]")
	private WebElement dbconnectiondatawarehouse;

	@FindBy(how = How.CSS, using = "g.raphael-group-21-dataset text tspan")
	private WebElement dataconnectionstatus;

	@FindBy(how = How.CSS, using = "p.message:nth-child(1)")
	private WebElement message;

	@FindBy(how = How.ID, using = "ppm_nav_admin")
	private WebElement admintab;

	@FindBy(how = How.LINK_TEXT, using = "Data Warehouse OData Service")
	private WebElement datawarehouseodataserviclink;

	@FindBy(how = How.CSS, using = "tr.ppm_attribute_row:nth-child(2)>td:nth-child(2)>span")
	private WebElement v4endpointurl;

	@FindBy(how = How.CSS, using = "tr.ppm_attribute_row:nth-child(3)>td:nth-child(2)>span")
	private WebElement odataauthenticator;

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

	@FindBy(how = How.LINK_TEXT, using = "PPM Schema")
	private WebElement ppmschema;

	@FindBy(how = How.LINK_TEXT, using = "cmn_db_history")
	private WebElement cmndbhistory;

	@FindBy(how = How.LINK_TEXT, using = "api_client")
	private WebElement apiclient;

	@FindBy(how = How.LINK_TEXT, using = "Last Run")
	private WebElement lastrun;

	@FindBy(how = How.CSS, using = "h2[title='Process Engines']")
	private WebElement processenginestitle;

	@FindBy(how = How.CSS, using = "h1[title='Resources']")
	private WebElement resourcestitle;

	@FindBy(how = How.CSS, using = "input[name='version']")
	private WebElement contentaddinstitle;

//	@FindAll(@FindBy(how = How.CSS, using = "#reportContainer table.jrPage tbody tr"))
//	private List<WebElement> datawarehouseschemarows;

	@FindAll(@FindBy(how = How.CSS, using = "#reportContainer>table>tbody>tr:nth-child(7)>td:nth-child(2)>span"))
	private WebElement apiclientresult;

	public NikuHomePage(WebDriver driver) {
		// this.driver = driver;

		// log.debug("Initializing page objects");
		/*
		 * AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,
		 * 30); PageFactory.initElements(factory, this);
		 */
		PageFactory.initElements(dr.get(), this);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

	public synchronized void nikuLogin(String testurl, String userName, String password)
			throws InterruptedException, IOException {
		ExtentTestManager.getTest().log(Status.INFO, "Validating login");
		// log.debug("Inside Niku Login");
		dr.get().get(testurl);
		ExtentTestManager.getTest().log(Status.INFO, "Launched niku url " + testurl);
		WaitForElement(ExpectedConditions.visibilityOf(this.userName));
		System.out.println(dr.get().getTitle());
		sendKeys(this.userName, userName);
		sendKeyswithoutreportingvalue(this.password, password);
		click(this.loginbtn);
		verifyElement(this.logout);

	}

	public synchronized void clickLearnLink() throws InterruptedException, IOException {
		ExtentTestManager.getTest().log(Status.INFO, "Clicking learn link");
		// log.debug("Clicking Learn Link");
		if (isElementPresent(this.learnlink)) {
			click(this.learnlink);
		} else {
			System.out.println("***Learn Link is not configured****");
			ReportStatus("warn", "****Learn Link is not configured****");
		}
	}

	public synchronized void ValidateLearnLink() throws InterruptedException, IOException {
		String flag = "false";
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
					verifyTrue(dr.get().getTitle().contains("trainer"));
					/*
					 * Assert.assertTrue(dr.get().getTitle().contains("trainer"),
					 * "title displayed is " + dr.get().getTitle());
					 */
					dr.get().close();
					System.out.println("Learn Link Validated Successfully");
					flag = "true";
					break;
				}
			}
		}
		dr.get().switchTo().window(windHandleBefore);
		if (flag.equalsIgnoreCase("false")) {
			ReportStatus("warn", "****Learn link window is not displayed****");
		}
	}

	public synchronized void clickAboutDialog() throws InterruptedException, IOException {
		// log.debug("Clicking about link");
		if (isElementPresent(this.aboutlink)) {
			click(this.aboutlink);
			verifyElement(this.aboutdialog);
		} else {
			ReportStatus("fail", "****About link is not displayed****");
		}
	}

	public synchronized void checkProductName() throws InterruptedException {
		// log.debug("Inside check product name");
		ExtentTestManager.getTest().log(Status.INFO, "Validating product name");
		wait.until(ExpectedConditions.visibilityOfAllElements(this.aboutinformation));
		verifyEquals(m, TestConstants.PRODUCT_NAME, this.aboutinformation.get(0).getText());
	}

	public synchronized void checkBuildVersion() throws InterruptedException {
		// log.debug("Inside Check Build Version");
		ExtentTestManager.getTest().log(Status.INFO, "Validating build version");
		verifyEquals(m, TestConstants.BUILD_VERSION, this.aboutinformation.get(1).getText());
		if (isElementPresent(this.aboutclosebtn)) {
			click(this.aboutclosebtn);
		}

	}

	public synchronized void checkJaspersoftVersion() throws InterruptedException {
		// log.debug("Inside check Jaspersoft Version");
		ExtentTestManager.getTest().log(Status.INFO, "Validating jaspersoft version");
		JavascriptExecutor jse = (JavascriptExecutor) dr.get();
		jse.executeScript("arguments[0].scrollIntoView()", this.aboutinformation.get(7));
		Thread.sleep(2);
		verifyEquals(m, TestConstants.JASPERSOFT_VERSION, this.aboutinformation.get(7).getText());
		if (isElementPresent(this.aboutclosebtn)) {
			click(this.aboutclosebtn);
		}
	}

	public synchronized void checkLastRunTimeSlicesInitialTime() throws ParseException, InterruptedException {
		// log.debug("check Last Run Time Slices initially");
		ExtentTestManager.getTest().log(Status.INFO, "Validating last run time slices for initial time");
		click(this.admintab);
		ExtentTestManager.getTest().log(Status.INFO, "Clicked Admin tab");
		WaitForElement(ExpectedConditions.visibilityOf(this.timeslices));
		this.timeslices.click();
		ExtentTestManager.getTest().log(Status.INFO, "Clicked TimeSlices");
		TimeUnit.SECONDS.sleep(2);
		WaitForElement(ExpectedConditions.visibilityOf(this.lastrun));
		sLastRun = this.tablerows.get(1).findElements(By.tagName("td")).get(10).getText();
		dLastRun = new SimpleDateFormat("MM/dd/yy hh:mm a").parse(sLastRun);
		WaitForElement(ExpectedConditions.visibilityOf(this.ppmrefreshbtn));
		ReportStatus("info", "***Initial time slice captured " + dLastRun + "****");
	}

	public synchronized void checkLastRunTimeSlicesFinalTime() throws ParseException, InterruptedException {
		// log.debug("check Last Run Time Slices");
		ExtentTestManager.getTest().log(Status.INFO, "Validating last run time slices for final time");
		click(this.admintab);
		ExtentTestManager.getTest().log(Status.INFO, "Clicked Admin tab");
		WaitForElement(ExpectedConditions.visibilityOf(this.timeslices));
		this.timeslices.click();
		ExtentTestManager.getTest().log(Status.INFO, "Clicked TimeSlices");
		// TimeUnit.SECONDS.sleep(3);
		wait.until(ExpectedConditions.visibilityOf(this.lastrun));
		sLatestRun = this.tablerows.get(1).findElements(By.tagName("td")).get(10).getText();
		dLatestRun = new SimpleDateFormat("MM/dd/yy hh:mm a").parse(sLatestRun);
		verifyTrue(dLatestRun.compareTo(dLastRun) > 0);
		/*
		 * Assert.assertTrue(dLatestRun.compareTo(dLastRun) > 0, "Latest Run is " +
		 * sLatestRun + " Last Run is " + sLastRun);
		 */
		if (dLatestRun.compareTo(dLastRun) > 0) {
			ReportStatus("pass", "****TimeSlices validated successfully Latest Run is " + sLatestRun + " Last Run is "
					+ sLastRun + "****");
		} else {
			ReportStatus("fail", "****Latest Run is " + sLatestRun + " Last Run is " + sLastRun + "****");
		}
	}

	public synchronized void checkProcessEngineColors() throws InterruptedException {
		// log.debug("Inside check process engine colors");
		ExtentTestManager.getTest().log(Status.INFO, "Validating process engine colors");
		List<WebElement> processenginerows;
		String processenginestatuscolor;
		click(this.admintab);
		if (isElementPresent(this.processengines)) {
			click(this.processengines);
			TimeUnit.SECONDS.sleep(2);
			WaitForElement(ExpectedConditions.visibilityOf(this.processenginestitle));
			wait.until(ExpectedConditions.visibilityOfAllElements(this.tablerows));
			processenginerows = this.tablerows;
			System.out.println("Process Engine Rows " + processenginerows.size());
			for (WebElement processenginerow : processenginerows) {
				processenginestatuscolor = processenginerow.findElements(By.tagName("td")).get(7)
						.findElement(By.tagName("img")).getAttribute("name");
				verifyTrue(processenginestatuscolor.equalsIgnoreCase("DiamondYellow")
						| processenginestatuscolor.equalsIgnoreCase("DiamondRed")
						| processenginestatuscolor.equalsIgnoreCase("DiamondGrey"));
				/*
				 * Assert.assertTrue( processenginestatuscolor.equalsIgnoreCase("DiamondYellow")
				 * | processenginestatuscolor.equalsIgnoreCase("DiamondRed") |
				 * processenginestatuscolor.equalsIgnoreCase("DiamondGrey"),
				 * "The status colore displayed is " + processenginestatuscolor);
				 */
				if (processenginestatuscolor.equalsIgnoreCase("DiamondYellow")
						| processenginestatuscolor.equalsIgnoreCase("DiamondRed")
						| processenginestatuscolor.equalsIgnoreCase("DiamondGrey")) {
					ReportStatus("pass", "****The status color displayed is " + processenginestatuscolor + "****");
					System.out.println("****Process Engine colors validated successfully");
				} else {
					ReportStatus("fail", "***The process Engines link is not displayed****");
				}
			}
		}
	}

	public synchronized void checkContentAddinStatus() throws InterruptedException, IOException {
		// log.debug("inside check content addin status");
		ExtentTestManager.getTest().log(Status.INFO, "Validating content addin status");
		List<WebElement> contentaddinsrows;
		String contentaddinstatus;
		click(this.admintab);
		click(this.contentaddins);
		WaitForElement(ExpectedConditions.visibilityOf(this.contentaddinstitle));
		contentaddinsrows = this.tablerows;
		for (WebElement contentaddinsrow : contentaddinsrows) {

			contentaddinstatus = contentaddinsrow.findElements(By.tagName("td")).get(5).getText();
			verifyTrue(contentaddinstatus.equalsIgnoreCase("Installed")
					| contentaddinstatus.equalsIgnoreCase("Upgrade Ready")
					| contentaddinstatus.equalsIgnoreCase("Upgrade Pending"));
			/*
			 * Assert.assertTrue( contentaddinstatus.equalsIgnoreCase("Installed") |
			 * contentaddinstatus.equalsIgnoreCase("Upgrade Ready") |
			 * contentaddinstatus.equalsIgnoreCase("Upgrade Pending"),
			 * "The content addins status displayed is " + contentaddinstatus);
			 */

			if (contentaddinstatus.equalsIgnoreCase("Installed") | contentaddinstatus.equalsIgnoreCase("Upgrade Ready")
					| contentaddinstatus.equalsIgnoreCase("Upgrade Pending")) {
				ReportStatus("pass", "****Content Addins validated successfully****");

				System.out.println("****Content Addins validated successfully*****");
			} else {
				ReportStatus("fail", "****The content addin status displayed is " + contentaddinstatus + "****");

			}
		}
	}

	public synchronized void checkResources() throws InterruptedException, IOException {
		// log.debug("inside check Resources ");
		ExtentTestManager.getTest().log(Status.INFO, "Validating resources");
		List<WebElement> resourcerows;
		String status;
		String type;
		click(this.admintab);
		WaitForElement(ExpectedConditions.visibilityOf(this.resources));
		click(this.resources);
		WaitForElement(ExpectedConditions.visibilityOf(this.resourcestitle));
		wait.until(ExpectedConditions.visibilityOfAllElements(this.tablerows));
		resourcerows = this.tablerows;
		for (WebElement resourcerow : resourcerows) {
			if (resourcerow.findElements(By.tagName("td")).get(3).getText().equalsIgnoreCase("od_admin@dummy.com")
					| resourcerow.findElements(By.tagName("td")).get(3).getText()
							.equalsIgnoreCase("ca_portal@dummy.com")) {

				status = resourcerow.findElements(By.tagName("td")).get(7).getText();
				type = resourcerow.findElements(By.tagName("td")).get(6).getText();
				verifyTrue(status.equalsIgnoreCase("Active"));
				verifyTrue(type.equalsIgnoreCase("Internal"));
				/*
				 * Assert.assertTrue(status.equalsIgnoreCase("Active"),
				 * "The status displayed is " + status);
				 * Assert.assertTrue(type.equalsIgnoreCase("Internal"), "The type displayed is "
				 * + type);
				 */
				System.out.println("****Resources Validated successfully****");
				ReportStatus("pass", "****Resources validated successfully****");
			}

		}

	}

	public synchronized void checkAdvanceReporting() throws InterruptedException, IOException {
		// log.debug("Inside advance Reporting");
		System.out.println("Inside advance Reporting");
		ExtentTestManager.getTest().log(Status.INFO, "Validating Advance Reporting");
		int i;
		int rows;
		WaitForElement(ExpectedConditions.visibilityOf(this.hometab));
		click(this.hometab);
		click(this.advancedreporting);
		TimeUnit.SECONDS.sleep(5);
		isElementPresent((this.jasperframe));
		dr.get().switchTo().frame(this.jasperframe);
		click(this.librarytab);
		TimeUnit.SECONDS.sleep(2);
		WaitForElement(ExpectedConditions.visibilityOf(this.resultsList));
		rows = this.descriptions.size();
		System.out.println("Advance Reporting Rows " + rows);
		List<WebElement> descriptions = this.descriptions;
		for (i = 2; i < (rows / 2); i = i + 2) {
			System.out.println("****" + i + descriptions.get(i).getText());
			 verifyTrue(descriptions.get(i).getText().contains("PMO Accelerator")|| descriptions.get(i).getText().contains("APM"));
			
			/*
			 * Assert.assertTrue(descriptions.get(i).getText().contains("PMO Accelerator"),
			 * "The Description had " + descriptions.get(i).getText());
			 */
		}
		System.out.println("****Advance Report Validated successfully****");
		ReportStatus("pass", "****Advance Reporting validate successfully****");
	}

	public synchronized void checkDatawarehouseSchema() throws InterruptedException, IOException {
		// log.debug("inside validate jaspersoft report");
		ExtentTestManager.getTest().log(Status.INFO, "Datawarehouse schema report");
		System.out.println("validating data ware house schema");
		click(this.datawarehouseschema);
		TimeUnit.SECONDS.sleep(2);
		WaitForElement(ExpectedConditions.visibilityOf(this.message));
		TimeUnit.SECONDS.sleep(2);
		click(this.cmndbhistory);
		TimeUnit.SECONDS.sleep(2);
		click(this.applybtn);
		WaitForElement(ExpectedConditions.visibilityOf(this.apiclientresult));
		verifyEquals(m, "cmn_db_history", this.apiclientresult.getText().trim());
		// dr.get().switchTo().defaultContent();
		System.out.println("****Datawarehouse schema validation is complete****");
		ReportStatus("pass", "****Datawarehouse schema validated successfully****");
	}

	public synchronized void checkPPMSchema() throws InterruptedException, IOException {
		// log.debug("inside validate jaspersoft report");
		ExtentTestManager.getTest().log(Status.INFO, "PPMSchema  report");
		/*
		 * System.out.println("Validating ppm schema"); click(this.hometab);
		 * click(this.advancedreporting); TimeUnit.SECONDS.sleep(10);
		 * isElementPresent((this.jasperframe));
		 * dr.get().switchTo().frame(this.jasperframe);
		 */
		click(this.librarytab);
		TimeUnit.SECONDS.sleep(2);
		click(this.ppmschema);
		TimeUnit.SECONDS.sleep(2);
		WaitForElement(ExpectedConditions.visibilityOf(this.message));
		click(this.apiclient);
		TimeUnit.SECONDS.sleep(2);
		click(this.applybtn);
		WaitForElement(ExpectedConditions.visibilityOf(this.apiclientresult));
		verifyEquals(m, "api_client", this.apiclientresult.getText().trim());
		System.out.println("****PPM  schema validation is complete****");
		ReportStatus("pass", "****PPM schema validated successfully****");
	}

	public synchronized void CheckDBConnectionCAPPM() throws InterruptedException, IOException {
		// log.debug("inside validate jaspersoft report");
		/*
		 * ExtentTestManager.getTest().log(Status.INFO, "CA PPM DB Connection  report");
		 * System.out.println("Validating CA PPM DB Connection"); click(this.hometab);
		 * click(this.advancedreporting); TimeUnit.SECONDS.sleep(10);
		 * isElementPresent((this.jasperframe));
		 * dr.get().switchTo().frame(this.jasperframe);
		 */
		click(this.librarytab);
		TimeUnit.SECONDS.sleep(2);
		click(this.dbconnectioncappm);
		TimeUnit.SECONDS.sleep(2);
		WaitForElement(ExpectedConditions.visibilityOf(this.dataconnectionstatus));
		verifyEquals(m, "Normal", this.dataconnectionstatus.getText().trim());
		ReportStatus("pass", "****CA PPM DB connection is validated successfully****");
	}

	public synchronized void CheckDBConnectionDWS() throws InterruptedException, IOException {
		// log.debug("inside validate jaspersoft report");
		ExtentTestManager.getTest().log(Status.INFO, "DWS DB Connection  report");
		/*
		 * System.out.println("Validating DWS DB Connection"); click(this.hometab);
		 * click(this.advancedreporting); TimeUnit.SECONDS.sleep(10);
		 * isElementPresent((this.jasperframe));
		 * dr.get().switchTo().frame(this.jasperframe);
		 */
		click(this.librarytab);
		TimeUnit.SECONDS.sleep(2);
		click(this.dbconnectiondatawarehouse);
		TimeUnit.SECONDS.sleep(2);
		WaitForElement(ExpectedConditions.visibilityOf(this.dataconnectionstatus));
		verifyEquals(m, "Normal", this.dataconnectionstatus.getText().trim());
		ReportStatus("pass", "****DWS DB connection is validated successfully****");
		dr.get().switchTo().defaultContent();
	}

	public void ValidateHDPCluster() throws InterruptedException, IOException {
		ExtentTestManager.getTest().log(Status.INFO, "Validating HDP cluster");
		System.out.println("Validating HDP Cluster");
		// first line to be comments later just for running individually
		// nhp.get().nikuLogin(pr.get().getProperty("nikuurl"), "rg030672",
		// "Welcome123!");
		click(this.admintab);
		if (isElementPresent(this.datawarehouseodataserviclink)) {
			click(this.datawarehouseodataserviclink);
			endpointurl = FetchInnerText(this.v4endpointurl);
			authenticator = FetchInnerText(this.odataauthenticator);
			System.out.println(endpointurl);
			/*
			 * nhp.get().nikuLogin(pr.get().getProperty("chopsurl"),
			 * pr.get().getProperty("chopsuserid"), pr.get().getProperty("chopspassword"));
			 * click(this.expandfilter); sendKeys(this.companyname, "CA On Demand");
			 * click(this.filter); click(this.caondemand); click(this.properties);
			 * click(this.sharedaccountslist); click(this.clarityodadminaccount);
			 * endpointuserid = FetchAttribute(this.loginfield, "value");
			 * System.out.println(endpointuserid+"|"+authenticator); endpointpassword =
			 * FetchAttribute(this.passwordfield, "value");
			 * System.out.println(endpointpassword); TimeUnit.SECONDS.sleep(2);
			 * dr.get().get(endpointurl);
			 */
			if (endpointurl != null) {
				RestUtil.validateHDPCall();
				ReportStatus("pass", "****HDP Clustor is validated successfully****");
				
			} else {
				System.out.println("****HDP is not configured to this instance****");
				ReportStatus("warn", "****HDP is not configured to this instance****");
			}
		} else {
			ReportStatus("warn", "****HDP is not configured to this instance****");
		}
		
	}

	public synchronized void LogOut() {
		dr.get().switchTo().defaultContent();
		this.logout.click();
		WaitForElement(ExpectedConditions.visibilityOf(this.userName));
		System.out.println("***Logged Out Successfully****");
		ReportStatus("pass", "****Logged out Successfully****");

	}

}
