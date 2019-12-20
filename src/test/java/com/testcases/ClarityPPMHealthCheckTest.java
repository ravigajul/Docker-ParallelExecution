package com.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Properties;
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
import com.resources.testdata.TestConstants;
import com.utilities.RestUtil;

import junit.framework.Assert;

public class ClarityPPMHealthCheckTest extends TestBase {

	public NikuHomePage nikuhomepage;
	public String testurl;

	@Test(dataProvider = "getData")
	public void HealthCheck(Hashtable ht) throws InterruptedException, ParseException, IOException {
		
		System.out.println(ht.get("NIKUNAMESPACE"));
		System.out.println("Thread " + Thread.currentThread().getId());
		testurl = "https://" + ht.get("NIKUNAMESPACE") + TestConstants.EXTERNAL_DOMAIN;
		nhp.get().nikuLogin(testurl, TestConstants.NIKU_USER_ID, TestConstants.NIKU_PASSWORD);
		nhp.get().clickLearnLink();
		nhp.get().clickAboutDialog();
		nhp.get().checkProductName();
		nhp.get().checkBuildVersion();
		// nhp.get().checkJaspersoftVersion();
		nhp.get().checkLastRunTimeSlicesInitialTime();
		nhp.get().checkProcessEngineColors();
		nhp.get().checkContentAddinStatus();
		nhp.get().checkResources();
		nhp.get().checkAdvanceReporting();
		nhp.get().checkDatawarehouseSchema();
		nhp.get().checkPPMSchema();
		nhp.get().checkLastRunTimeSlicesFinalTime();
		nhp.get().ValidateLearnLink();
		nhp.get().ValidateHDBCluster();

	}

	@DataProvider(name = "getData", parallel = true)
	public Object[][] getData() {
		File file = new File(currentdirectorypath + "/src/test/java/com/properties/env.properties");
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
		System.out.println(pr.get().getProperty("NIKUNAMESPACE"));
		String[] namespaces = pr.get().getProperty("NIKUNAMESPACE").split(",");
		Object[][] obj =new Object[namespaces.length][1];		
		for (int j = 0; j < namespaces.length; j++) {
			Hashtable<String,String> hashtable= new Hashtable<String,String>();
			hashtable.put("NIKUNAMESPACE", namespaces[j]);
			obj[j][0]=hashtable;
		}
		return obj;
	}
}
