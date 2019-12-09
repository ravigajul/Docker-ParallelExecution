package com.testcases;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pages.HDBClusterHomePage;
import com.pages.NikuHomePage;

public class ClarityPPMHealthCheck extends TestBase{

	public NikuHomePage nikuhomepage;
	
	@Test(dataProvider="getData")
	public void HealthCheck(String userName, String password) throws InterruptedException, ParseException, IOException {
		System.out.println("Thread " +Thread.currentThread().getId()+"-->"+ userName +"-->"+password);
		nhp.get().nikuLogin("rg030672","Welcome123!");
		nhp.get().clickAboutDialog();
		nhp.get().checkProductName();
		nhp.get().checkBuildVersion();
		nhp.get().checkJaspersoftVersion();
		nhp.get().checkLastRunTimeSlicesInitialTime();
		nhp.get().checkProcessEngineColors();
		nhp.get().checkContentAddinStatus();
		nhp.get().checkResources();
		nhp.get().checkAdvanceReporting();
		nhp.get().checkJasperSoftReport();
		nhp.get().checkLastRunTimeSlicesFinalTime();
		//ValidateHDBCluster();
	}
	
/*	@Test
	public void HealthCheck3() throws InterruptedException, ParseException, IOException {
		//System.out.println("Thread " + userName +"-->"+password);
		nhp.get().nikuLogin("rg030672","Welcome123!");
		nhp.get().checkProcessEngineColors();
		nhp.get().checkAdvanceReporting();
		//ValidateHDBCluster();
	}
	
	@Test
	public void HealthCheck2() throws InterruptedException, ParseException, IOException {
		//System.out.println("Thread " + userName +"-->"+password);
		nhp.get().nikuLogin("rg030672","Welcome123!");
		nhp.get().checkContentAddinStatus();
		nhp.get().checkResources();
		//ValidateHDBCluster();
	}*/
	
	@DataProvider(name="getData",parallel=true)
	public Object[][] getData()
	{
		return new Object[][] {{"rg030672","Welcome123!"},{"rg030672","Welcome123!"},{"rg030672","Welcome123!"},{"rg030672","Welcome123!"},{"rg030672","Welcome123!"}};
	}

	public  void ValidateHDBCluster() throws InterruptedException {

		dr.get().navigate().to(prop.getProperty("hdburl"));
		// dr.get().manage().window().maximize();
		TimeUnit.SECONDS.sleep(10);
		HDBClusterHomePage hdbClusterHomePage = PageFactory.initElements(dr.get(), HDBClusterHomePage.class);
		hdbClusterHomePage.getUserName().sendKeys(prop.getProperty("hdbuserid"));
		hdbClusterHomePage.getPassword().sendKeys(prop.getProperty("hdbpassword"));
		hdbClusterHomePage.getLoginbtn().click();
		TimeUnit.SECONDS.sleep(5);
		hdbClusterHomePage.getAdmintab().click();
		TimeUnit.SECONDS.sleep(3);
		hdbClusterHomePage.getDatawarehouseodataservice().click();
		TimeUnit.SECONDS.sleep(10);
		String v2EndPointUrl = hdbClusterHomePage.getDatawarehourservices().get(0).getText();
		String v4EndPointUrl = hdbClusterHomePage.getDatawarehourservices().get(1).getText();
		String oDataAuthenticator = hdbClusterHomePage.getDatawarehourservices().get(1).getText() + "|Admin";
		dr.get().navigate().to(v2EndPointUrl);
		dr.get().switchTo().alert().sendKeys(prop.getProperty("hdbuserid") + Keys.TAB + prop.getProperty("hdbpassword"));
		TimeUnit.SECONDS.sleep(3);
		dr.get().switchTo().alert().accept();
	}

	
}
