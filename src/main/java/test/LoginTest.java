package test;
import java.io.IOException;
import java.util.HashMap;
import org.testng.annotations.Test;
import com.jayway.jsonpath.JsonPath;
import constants.FileConstants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.DataUtils;
import utils.RestUtils;

public class LoginTest extends BaseTest {

	@Test
	public void login() throws IOException{
		RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
		
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		
		String env = DataUtils.readJsonFileToString(FileConstants.ENV_URI_FILE_PATH);
		String envUri = JsonPath.read(env, "$.prod.uri");
		
		String endpoint = JsonPath.read(env, "$.prod.endpoint.login");
		String creds = DataUtils.readJsonFileToString(FileConstants.USERACCOUNTS_TD_FILE_PATH);
		HashMap<String, String> credentials = new HashMap<String, String>();
		credentials.put("username", (String) JsonPath.read(creds, "$.prod.valid.username"));
		credentials.put("password", (String) JsonPath.read(creds, "$.prod.valid.password"));
		Response res = RestUtils.taPost(envUri+endpoint, headers, credentials);
		RestUtils.validateSchema(res, FileConstants.LOGIN_SCHEMA_FILE_PATH);
		System.out.println(res.prettyPrint());	
	}
	
	@Test
	public void test1() throws IOException {
		System.out.println("Token Generated: "+generateToken());
	}
}


