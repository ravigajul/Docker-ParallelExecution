package com.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HDBClusterHomePage {
	
	WebDriver driver;
	@FindBy(how = How.ID, using = "ppm_login_username") 
	private WebElement userName;
	
	@FindBy(how = How.ID, using = "ppm_login_password") 
	private WebElement password;
	
	@FindBy(how = How.ID, using = "ppm_login_button") 
	private WebElement loginbtn;
	
	@FindBy(how = How.ID, using = "ppm_nav_admin") 
	private WebElement admintab;
	
	@FindBy(linkText = "Data Warehouse OData Service") 
	private WebElement datawarehouseodataservice;
	
	@FindAll(@FindBy(how = How.CSS, using = "span.ppm_read_only_value"))
	private List<WebElement> datawarehourservices;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getUserName() {
		return userName;
	}

	public void setUserName(WebElement userName) {
		this.userName = userName;
	}

	public WebElement getPassword() {
		return password;
	}

	public void setPassword(WebElement password) {
		this.password = password;
	}

	public WebElement getLoginbtn() {
		return loginbtn;
	}

	public void setLoginbtn(WebElement loginbtn) {
		this.loginbtn = loginbtn;
	}

	public WebElement getDatawarehouseodataservice() {
		return datawarehouseodataservice;
	}

	public void setDatawarehouseodataservice(WebElement datawarehouseodataservice) {
		this.datawarehouseodataservice = datawarehouseodataservice;
	}

	public List<WebElement> getDatawarehourservices() {
		return datawarehourservices;
	}

	public void setDatawarehourservices(List<WebElement> datawarehourservices) {
		this.datawarehourservices = datawarehourservices;
	}

	public WebElement getAdmintab() {
		return admintab;
	}

	public void setAdmintab(WebElement admintab) {
		this.admintab = admintab;
	} 
	
	
}
