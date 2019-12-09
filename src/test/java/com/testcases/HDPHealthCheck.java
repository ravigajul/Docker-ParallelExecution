package com.testcases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pages.HDBClusterHomePage;
import com.pages.NikuHomePage;
import com.utilities.FileHelper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HDPHealthCheck extends ClarityPPMHealthCheck {
	Properties prop;
	RemoteWebDriver driver;
	NikuHomePage nikuhomepage;

	

	@Test(dependsOnMethods = { "ValidateNikuLogin" }, priority =1)
	public void ValidateHDBCluster() throws InterruptedException {
			
		//Navigate to HDB Url
		driver.get(prop.getProperty("hdburl"));
		// driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(10);
		HDBClusterHomePage hdbClusterHomePage = PageFactory.initElements(driver, HDBClusterHomePage.class);
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
		driver.navigate().to(v2EndPointUrl);
		driver.switchTo().alert().sendKeys(prop.getProperty("hdbuserid") + Keys.TAB + prop.getProperty("hdbpassword"));
		TimeUnit.SECONDS.sleep(3);
		driver.switchTo().alert().accept();
	}
	

}
