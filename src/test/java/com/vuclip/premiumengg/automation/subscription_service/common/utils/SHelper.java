package com.vuclip.premiumengg.automation.subscription_service.common.utils;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

import java.util.Map;

import org.apache.log4j.Logger;

import com.vuclip.premiumengg.automation.common.Configuration;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.subscription_service.common.models.ConfirmSync;
import com.vuclip.premiumengg.automation.subscription_service.common.models.SaveProductRequest;
import com.vuclip.premiumengg.automation.subscription_service.common.models.SubscriptionBlock;
import com.vuclip.premiumengg.automation.subscription_service.common.models.SubscriptionUnBlock;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author mayank.bharshiv
 */
public class SHelper {
	private static RequestSpecification requestSpecification;
	private static Logger logger = Log4J.getLogger("SSHelper");

	public static RequestSpecification getRequestSpecification() {
		if (requestSpecification == null) {
			requestSpecification = with().baseUri(Configuration.ssServer);
		}
		return requestSpecification;
	}

	/**
	 * 
	 * @param userData
	 * @return
	 * @throws Exception
	 */
	public static Response getUserStatus(Map<String, String> queryParam) throws Exception {
		logger.info("Get User Status Api Called");

		return subscriptionServiceRestGetcall(queryParam, "/api/subscription/get/status/{productId}",
				SUtils.productId);
	}

	/**
	 * 
	 * @param userData
	 * @return
	 * @throws Exception
	 */
	public static Response getFreeTrialStatus(Map<String, String> queryParam) throws Exception {
		logger.info("Free Trial Api Called");

		return subscriptionServiceRestGetcall(queryParam, "/api/subscription/check/freeTrialEligibility");
	}

	/**
	 * 
	 * @param userData
	 * @param endpoint
	 * @return
	 */
	private static Response subscriptionServiceRestGetcall(Map<String, String> userData, String endpoint) {
		final Response response = given(getRequestSpecification()).queryParams(userData).contentType(ContentType.JSON)
				.log().all().get(endpoint);
		response.prettyPrint();
		return response;
	}

	private static Response subscriptionServiceRestGetcall(Map<String, String> queryParam, String endpoint,
			Integer pathParam) {
		final Response response = given(getRequestSpecification()).pathParam("productId", pathParam)
				.queryParams(queryParam).contentType(ContentType.JSON).log().all().get(endpoint);
		response.prettyPrint();
		return response;
	}

	public static Response saveProduct(SaveProductRequest saveProdutRequest) throws Exception {
		logger.info("Save Product Api Called");
		final Response response = given(getRequestSpecification()).contentType(ContentType.JSON).body(saveProdutRequest)
				.post("/api/subscription/saveProduct");
		response.prettyPrint();
		return response;
	}

	public static Response subscriptionBlock(SubscriptionBlock subscriptionBlock) throws Exception {
		logger.info("Subscription Block Api Called");
		final Response response = given(getRequestSpecification()).log().all().contentType(ContentType.JSON).body(subscriptionBlock)
				.post("api/subscription/block");
		response.prettyPrint();
		return response;
	}
	
	public static Response subscriptionBlockStatus(Map<String, String> queryParam) throws Exception {
		logger.info("Subscription Block Status Api Called");
		return subscriptionServiceRestGetcall(queryParam, "/api/subscription/blockStatus");

	}
	
	public static Response subscriptionUnblock(SubscriptionUnBlock subscriptionBlock) throws Exception {
		logger.info("Subscription UnBlock Api Called");
		final Response response = given(getRequestSpecification()).log().all().contentType(ContentType.JSON).body(subscriptionBlock)
				.post("api/subscription/unblock");
		response.prettyPrint();
		return response;
	}
	public static Response confirmApi(ConfirmSync confirmSync) throws Exception {
		logger.info("Confirm Api Called");
		final Response response = given(getRequestSpecification()).log().all().contentType(ContentType.JSON).body(confirmSync)
				.post("api/subscription/confirm");
		response.prettyPrint();
		return response;
	}
}
