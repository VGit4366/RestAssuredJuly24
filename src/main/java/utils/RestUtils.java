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
	
	public static Response taPost(String baseUri, HashMap<String, String> headers, Object payload) {
		RestAssured.baseURI = baseUri;
		Response res = RestAssured.given().headers(headers).when().body(payload).post();
		return res;
	}
	
	public static Response taPost(String sbaseUri, HashMap<String, String> headers, String payload) {
		RestAssured.baseURI = sbaseUri;
		Response res = RestAssured.given().headers(headers).when().body(payload).post();
		return res;
	}
	
	public static Response taPut(String sbaseUri, HashMap<String, String> headers, String payload) {
		RestAssured.baseURI = sbaseUri;
		Response res = RestAssured.given().headers(headers).when().body(payload).put();
		return res;
	}
	
	public static Response get(String sbaseUri, HashMap<String, String> headers) {
		RestAssured.baseURI = sbaseUri;
		Response res = RestAssured.given().headers(headers).when().get();
		return res;
	}
		
	public static Response taDelete(String sbaseUri, HashMap<String, String> headers, String payload) {
		RestAssured.baseURI = sbaseUri;
		Response res = RestAssured.given().headers(headers).when().body(payload).delete();
		return res;
		}
}
	
