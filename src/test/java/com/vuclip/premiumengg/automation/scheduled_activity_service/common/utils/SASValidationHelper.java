package com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils;

import java.util.Map;

import org.apache.log4j.Logger;

import com.vuclip.premiumengg.automation.billing_package_service.common.models.QueueResponse;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.SchedulerRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.UserSubscriptionRequest;
import com.vuclip.premiumengg.automation.utils.AppAssert;

import io.restassured.response.Response;

public class SASValidationHelper {
	private static Logger logger = Log4J.getLogger("SASValidationHelper");

	public static void validate_sas_api_response(Response sasApiResponse) throws Exception {
		AppAssert.assertEqual(sasApiResponse.statusCode(), 200, "Vefiry that response status code is 200 ");
	}

	public static void validate_sas_invalid_api_response(Response sasApiResponse) throws Exception {
		AppAssert.assertEqual(sasApiResponse.statusCode(), 500, "Vefiry that response status code is 500 ");
	}
	
	public static void validate_schedular_api_response(Response schedularApiResponse) throws Exception {

		AppAssert.assertEqual(schedularApiResponse.statusCode(), 200, "Validate that response status code is 200 ");
		AppAssert.assertEqual(schedularApiResponse.getBody().asString(), "SUCCESS", "verify scheduler api call");
	}

	public static void validate_schedular_invalid_api_response(Response schedularApiResponse) throws Exception {

		AppAssert.assertEqual(schedularApiResponse.statusCode(), 500, "Validate that response status code is 500 ");
		AppAssert.assertEqual(schedularApiResponse.getBody().asString(), "FAILURE","verify scheduler api call");
	}
	public static void validateQueueMessage(QueueResponse queueResponse, SchedulerRequest schedulerRequest,
			UserSubscriptionRequest userSubscriptionRequest, String activityType) {
		logger.info("verification for RabbitMQ");
		AppAssert.assertEqual(queueResponse.getSubscriptionId().toString(),
				userSubscriptionRequest.getSubscriptionInfo().getSubscriptionId().toString(), "Verify subscription ID");
		AppAssert.assertEqual(queueResponse.getCountryCode().toUpperCase(),
				userSubscriptionRequest.getSubscriptionInfo().getCountry().toUpperCase(), "Verify country");
		AppAssert.assertEqual(queueResponse.getPartnerId().toString().toUpperCase(),
				schedulerRequest.getPartnerId().toString().toUpperCase(), "Verify partner ID");
		AppAssert.assertEqual(queueResponse.getProductId().toString().toUpperCase(),
				schedulerRequest.getPartnerId().toString().toUpperCase(), "Verify product ID");
		AppAssert.assertEqual(queueResponse.getActivitType().toString().toUpperCase(), activityType.toUpperCase(),
				"Verify activity type");
	}

	public static void validateTableRecord(Map<String, Object> actualrecord, Map<String, String> expectedRecord) {
		for (String key : expectedRecord.keySet()) {
			AppAssert.assertEqual(actualrecord.get(key).toString().toLowerCase(), expectedRecord.get(key).toLowerCase(),
					"Verify table fields");
		}
	}

	public static void validateQueueMessage(QueueResponse queueResponse, int productId, int partnerId,
			int subscriptionId, String countryCode, String actionTable) {
		logger.info("verification for RabbitMQ");

		AppAssert.assertEqual(queueResponse.getProductId().toString().toUpperCase(),
				String.valueOf(productId).toUpperCase(), "Verify product ID");
		AppAssert.assertEqual(queueResponse.getPartnerId().toString().toUpperCase(),
				String.valueOf(partnerId).toUpperCase(), "Verify partner ID");
		AppAssert.assertEqual(queueResponse.getSubscriptionId().toString(), String.valueOf(subscriptionId),
				"Verify subscription ID");
		AppAssert.assertEqual(queueResponse.getCountryCode().toUpperCase(), countryCode.toUpperCase(),
				"Verify country");
		if (actionTable.toUpperCase().equalsIgnoreCase("RENEWAL_RETRY"))
			AppAssert.assertEqual(queueResponse.getActivitType().toString().toUpperCase(), "RENEWAL",
					"Verify activity type");
		else
			AppAssert.assertEqual(queueResponse.getActivitType().toString().toUpperCase(), actionTable.toUpperCase(),
					"Verify activity type");
	}
}
