package com.utilities;

import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import com.resources.json.SlicingHeartBeat;
import com.resources.testdata.TestConstants;
import com.testcases.TestBase;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class RestUtil extends TestBase {

	public static void validateHDPCall() {
		ExtentTestManager.getTest().log(Status.INFO, "Running HDP get REST call");
		RestAssured.baseURI = endpointurl;
		RequestSpecification httpRequest = RestAssured.given().auth()
				.basic(TestConstants.NIKU_USER_ID + "|" + authenticator, TestConstants.NIKU_PASSWORD);
		Response response = httpRequest.request(Method.GET);
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertFalse(responseBody.isEmpty(),"****Response body is empty****");
	}
	
	public static void ValidateLastSlicingNHeartBeat(String hostname) {
		
		
		RestAssured.baseURI = "https://"+hostname+"/" ;
		RequestSpecification httpRequest = RestAssured.given().auth()
				.basic(TestConstants.NIKU_USER_ID, TestConstants.NIKU_PASSWORD);
		Response response = httpRequest.request(Method.GET,"niku/serverstatus/status?run=LAST_SLICING,PE_HEARTBEAT");
		String responseBody = response.getBody().asString();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertFalse(responseBody.isEmpty(),"****Response body is empty****");
		System.out.println("Response Body is =>  " + responseBody);
		Gson gson= new Gson();
		SlicingHeartBeat slicingheartbeat=gson.fromJson(responseBody,SlicingHeartBeat.class);
		System.out.println("PE_HEARTBEAT value is "+slicingheartbeat.getPE_HEARTBEAT().get(0).getValue());
		System.out.println("LAST_SLICING vaue is " +slicingheartbeat.getLAST_SLICING().get(0).getValue());
		Assert.assertTrue(Integer.parseInt(slicingheartbeat.getLAST_SLICING().get(0).getValue())<60, "LAST_SLICING vaue is " +slicingheartbeat.getLAST_SLICING().get(0).getValue());
		ReportStatus("info","PE_HEARTBEAT value is "+slicingheartbeat.getPE_HEARTBEAT().get(0).getValue());
		Assert.assertTrue(Integer.parseInt(slicingheartbeat.getPE_HEARTBEAT().get(0).getValue())<300,"LAST_SLICING vaue is " +slicingheartbeat.getLAST_SLICING().get(0).getValue());
		ReportStatus("info","LAST_SLICING vaue is " +slicingheartbeat.getLAST_SLICING().get(0).getValue());
		ReportStatus("pass", "****Heart beat and Last Slice API validate for value under 300 and 60 respectively****");
		
	}
}
