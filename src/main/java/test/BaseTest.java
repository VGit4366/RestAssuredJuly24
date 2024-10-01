package test;

import java.io.IOException;
import java.util.HashMap;
import constants.FileConstants;
import io.restassured.response.Response;
import utils.DataUtils;
import utils.RestUtils;

public class BaseTest {
	String token = null;
	public String generateToken() throws IOException {
		//Object payload = DataUtils.getTestData(FileConstants.USERACCOUNTS_TD_FILE_PATH, "$.prod.valid");
		String payload = "{\"username\": \"varsha.kommuri5@gmail.com\", \"password\": \"Rainbow@2033\"}";
		System.out.println("Payload ===>>> "+payload);
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");		
		String uri = DataUtils.getTestData(FileConstants.ENV_URI_FILE_PATH, "$.prod.uri").toString();
		System.out.println("URI ===>>> "+uri);
		String endPoint = DataUtils.getTestData(FileConstants.ENV_URI_FILE_PATH, "$.prod.endpoint.login").toString();
		System.out.println("End point URI ===>>> "+endPoint);
		if(token==null) {
			Response loginResponse = RestUtils.taPost(uri+endPoint, headers, payload);
			System.out.println("Login Response ===>>> "+loginResponse+"URL: "+uri+endPoint);
			token = loginResponse.jsonPath().get("[0].token");
		}
		else
			System.out.println("Token is already generated");
		System.out.println(token);
		return token;
	}
}
