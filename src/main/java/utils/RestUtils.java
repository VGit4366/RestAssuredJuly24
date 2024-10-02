package utils;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import java.io.File;
import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestUtils {
	
	public static void validateSchema(Response actualResponse, String schemaFilePath) {
		actualResponse.then().assertThat().body(matchesJsonSchema(new File(schemaFilePath)));
	}
	
	public static Response taPost(String endPoint, HashMap<String, String> headers, Object payload) {
		Response res = RestAssured.given().headers(headers).when().body(payload).post(endPoint);
		return res;
	}
	
	public static Response taPost(String endPoint, HashMap<String, String> headers, String payload) {
		RestAssured.useRelaxedHTTPSValidation();
		Response res = RestAssured.given().headers(headers).when().body(payload).post(endPoint);
		return res;
	}
	
	public static Response taPut(String sbaseUri, HashMap<String, String> headers, String payload) {
		RestAssured.baseURI = sbaseUri;
		Response res = RestAssured.given().headers(headers).when().body(payload).put();
		return res;
	}
	
	public static Response taGet(String sbaseUri, HashMap<String, String> header) {
		RestAssured.baseURI = sbaseUri;
		Response res = RestAssured.given().headers(header).when().get();
		System.out.println("TA GET Response ===>>> "+res);
		return res;
	}
		
	public static Response taDelete(String sbaseUri, HashMap<String, String> headers, String payload) {
		RestAssured.baseURI = sbaseUri;
		Response res = RestAssured.given().headers(headers).when().body(payload).delete();
		return res;
		}
}
	
