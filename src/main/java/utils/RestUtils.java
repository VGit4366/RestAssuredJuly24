package utils;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestUtils {
	
	public static Response taPost(String baseUri, HashMap<String, String> headers, Object payload) {
		RestAssured.baseURI = baseUri;
		Response res = RestAssured.given().headers(headers).when().body(payload).post();
		System.out.println("TA Post Response : "+res);
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
	
