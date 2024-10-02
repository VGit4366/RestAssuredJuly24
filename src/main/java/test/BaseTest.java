package test;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.BeforeTest;

import constants.FileConstants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.DataUtils;
import utils.RestUtils;

public class BaseTest {
	String token = null;
	
	@BeforeTest
	public void setup() throws IOException {
		RestAssured.baseURI = DataUtils.getTestData(FileConstants.ENV_URI_FILE_PATH, "$.prod.uri").toString();
		System.out.println("Base URI: "+RestAssured.baseURI);
	}
	
	//Generates generic token to perform transactions
	public String generateToken() throws IOException {
		//Object payload = DataUtils.getTestData(FileConstants.USERACCOUNTS_TD_FILE_PATH, "$.prod.valid");
		String payload = "{\"username\": \"july2024.varsha@tekarch.com\", \"password\": \"Admin123\"}";
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");		
		String uri = DataUtils.getTestData(FileConstants.ENV_URI_FILE_PATH, "$.prod.uri").toString();
		String endPoint = DataUtils.getTestData(FileConstants.ENV_URI_FILE_PATH, "$.prod.endpoint.login").toString();
		if(token==null) {
			Response loginResponse = RestUtils.taPost(uri + endPoint, headers, payload);
			token = loginResponse.jsonPath().get("[0].token");
		}
		else
			System.out.println("Token is already generated");
		System.out.println(token);
		return token;
	}
}
