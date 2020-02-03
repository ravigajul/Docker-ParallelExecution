package com.utilities;

import java.util.Date;

public class TestConfig {

	static Date d = new Date();
	static String sub=d.toString().replace(":","_").replace(" ", "_");
	public static String server = "smtp.gmail.com";
	public static String from = "ravi.gajul@broadcom.com";
	public static String password = "Krishna@22";
	public static String[] to = {"ravi.gajul@broadcom.com","habeeb.rehaman@broadcom.com","venkata.uthanur@broadcom.com","sandeep.arora@broadcom.com","rajesh.pola@broadcom.com","avinashbabu.mynampati@broadcom.com","satish.nainakanti@broadcom.com","ravi.bandikanti@broadcom.com","rajasekhar.pechetti@broadcom.com"};
	public static String subject = "Automation Test Execution Report"+"_"+sub;

	public static String messageBody = "Please find the test execution report below and attached";
	public static String attachmentPath = "c:\\screenshot\\2017_10_3_14_49_9.jpg";
	public static String attachmentName = "error.jpg";


}
