package com.utilities;

import java.util.Date;

public class TestConfig {

	static Date d = new Date();
	static String sub=d.toString().replace(":","_").replace(" ", "_");
	public static String server = "smtp.gmail.com";
	public static String from = "ravi.gajul@broadcom.com";
	public static String password = "Krishna@22";
	public static String[] to = {"ravi.gajul@broadcom.com","habeeb.rehaman@broadcom.com","satish.nainakanti@broadcom.com"};
	public static String subject = "Automation Test Execution Report"+"_"+sub;

	public static String messageBody = "Please find the test execution report below";
	public static String attachmentPath = "c:\\screenshot\\2017_10_3_14_49_9.jpg";
	public static String attachmentName = "error.jpg";

	// SQL DATABASE DETAILS
	public static String driver = "net.sourceforge.jtds.jdbc.Driver";
	public static String dbConnectionUrl = "jdbc:jtds:sqlserver://192.101.44.22;DatabaseName=monitor_eval";
	public static String dbUserName = "sa";
	public static String dbPassword = "$ql$!!1";

	// MYSQL DATABASE DETAILS
	public static String mysqldriver = "com.mysql.jdbc.Driver";
	public static String mysqluserName = "root";
	public static String mysqlpassword = "selenium";
	public static String mysqlurl = "jdbc:mysql://localhost:3306/acs";

}
