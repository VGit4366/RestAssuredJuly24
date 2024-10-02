package test;
import java.io.IOException;
import testdata.addUser;
import java.util.HashMap;
import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import constants.FileConstants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.DataUtils;
import utils.RestUtils;

public class LoginTest extends BaseTest {

	//@Test
	public void login() throws IOException{
		RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
		
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		String env = DataUtils.readJsonFileToString(FileConstants.ENV_URI_FILE_PATH);
		String endpoint = JsonPath.read(env, "$.prod.endpoint.login");
		String creds = DataUtils.readJsonFileToString(FileConstants.USERACCOUNTS_TD_FILE_PATH);
		HashMap<String, String> credentials = new HashMap<String, String>();
		credentials.put("username", (String) JsonPath.read(creds, "$.prod.valid.username"));
		credentials.put("password", (String) JsonPath.read(creds, "$.prod.valid.password"));
		Response res = RestUtils.taPost(RestAssured.baseURI+endpoint, headers, credentials);
		RestUtils.validateSchema(res, FileConstants.LOGIN_SCHEMA_FILE_PATH);
		System.out.println(res.prettyPrint());	
	}
	
	//@Test  //Serialization
	public void addUserTest() throws IOException {
		addUser user1 = new addUser("TA-456789", "4", "567899", "434566");
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		String payload = om.writeValueAsString(user1);
		String endpoint = DataUtils.getTestData(FileConstants.ENV_URI_FILE_PATH, "$.prod.endpoint.adddata").toString(); 
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("token", generateToken());
		Response res = RestUtils.taPost(RestAssured.baseURI+endpoint, headers, payload);
		res.then().assertThat().statusCode(201);
	}
	
	@Test	//De-serialization
	public void getUsers() throws IOException {
		ObjectMapper om = new ObjectMapper();
		String endPoint = DataUtils.getTestData(FileConstants.ENV_URI_FILE_PATH, "$.prod.endpoint.getdata").toString();
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("token", generateToken());
		Response res = RestUtils.taGet(RestAssured.baseURI+endPoint, headers);
		String getUser1 = om.writeValueAsString(res.jsonPath().get("[0]"));
		System.out.println(getUser1);
		res.then().assertThat().statusCode(200);
	}
}


