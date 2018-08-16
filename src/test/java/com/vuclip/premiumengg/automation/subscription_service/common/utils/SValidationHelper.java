package com.vuclip.premiumengg.automation.subscription_service.common.utils;

import java.text.ParseException;
import java.util.Map;

import org.apache.log4j.Logger;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.subscription_service.common.models.GetUserStatusResponse;
import com.vuclip.premiumengg.automation.subscription_service.common.models.RabbitMessageResponse;
import com.vuclip.premiumengg.automation.utils.AppAssert;

import io.restassured.response.Response;

public class SValidationHelper {
	private static Logger logger = Log4J.getLogger("SSValidationHelper");

	public static void validate_ss_api_response(Response ssApiResponse) {
		AppAssert.assertEqual(ssApiResponse.statusCode(), 200, "Vefiry that response status code is 200 ");
	}

	public static void validate_ss_invalid_api_response(Response ssApiResponse) {
		AppAssert.assertEqual(ssApiResponse.statusCode(), 500, "Vefiry that response status code is 500 ");
	}

	public static void validate_ss_jsonBody(Response ssApiResponse, String path, String expectedValue) {
		if (ssApiResponse.then().extract().body().path(path) == null) {
			AppAssert.assertTrue(false, "JSON Body Attribute " + path + " is null or missing");

		} else {
			String actualValue = ssApiResponse.then().extract().body().path(path).toString();
			AppAssert.assertEqual(actualValue, expectedValue, "Verify JSON Body Attribute");

		}
	}

	public static void validate_ss_jsonBody(Response ssApiResponse) {
		if (ssApiResponse.then().extract().body().path("status") == null
				&& ssApiResponse.then().extract().body().path("successful").equals(true)) {
			AppAssert.assertTrue(true);
		} else {
			AppAssert.assertTrue(false);
		}
	}

	public static void validateTableRecord(Map<String, Object> actualrecord, Map<String, String> expectedRecord) {
		for (String key : expectedRecord.keySet()) {
			if (actualrecord.get(key) != null)
				AppAssert.assertEqual(actualrecord.get(key).toString().toLowerCase(),
						expectedRecord.get(key).toLowerCase(), "Verify table field:" + key);
		}
	}

	public static void validate_ss_jsonBody(Response response, boolean blacklistValue) {
		if (response.then().extract().body().path("status") == null
				&& response.then().extract().body().path("blacklisted").equals(blacklistValue)) {
			AppAssert.assertTrue(true);
		} else {
			AppAssert.assertTrue(false);
		}

	}

	public static void validateQueueMessage(RabbitMessageResponse queueResponse, String action, String activity,
			String preState, String curState, String transactState, Long endDate)
			throws InterruptedException {
		logger.info("verification for RabbitMQ");
		AppAssert.assertEqual(queueResponse.getActivityInfo().getActionType().toString().toUpperCase(),
				String.valueOf(action).toUpperCase(), "Verify Action Type");
		AppAssert.assertEqual(queueResponse.getActivityInfo().getActivityType().toString().toUpperCase(),
				String.valueOf(activity).toUpperCase(), "Verify Activity Type");
		AppAssert.assertEqual(queueResponse.getActivityInfo().getPreviousSubscriptionState().toString().toUpperCase(),
				String.valueOf(preState).toUpperCase(), "Verify Previous Subscription State");
		AppAssert.assertEqual(queueResponse.getActivityInfo().getCurrentSubscriptionState().toString().toUpperCase(),
				String.valueOf(curState).toUpperCase(), "Verify Current Subscription State");
		AppAssert.assertEqual(queueResponse.getActivityInfo().getActivityResult().toString().toUpperCase(),
				String.valueOf(transactState).toUpperCase(), "Verify Action Result");
		AppAssert.assertEqual(queueResponse.getSubscriptionInfo().getEndDate().toString().toUpperCase(),
				String.valueOf(endDate).toUpperCase(), "Verify End Date");

	}

	public static void validateGetUserStatus(GetUserStatusResponse getUserStatusResponse, Long startDate, Long endDate,
			Long nextBillingDate, Double chargedPrice, Long activationDate, Long deactivationDate, String summary,
			String subscriptionStatus, String sBC, String cBC, String country, String userSource, String mode,
			boolean paid) throws ParseException {

		if (getUserStatusResponse.getStatus().getActivationDate() == null
				|| getUserStatusResponse.getStatus().getCountry() == null
				|| getUserStatusResponse.getStatus().getMode() == null
				|| getUserStatusResponse.getStatus().getUserSource() == null) {
			AppAssert.assertTrue(false, "JSON field is null");
		} else {

			AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getStartDate()),
					String.valueOf(startDate), "Verify Start Date");
			AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getEndDate()),
					String.valueOf(endDate), "Verify End Date");
			AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getNextBillingDate()),
					String.valueOf(nextBillingDate), "Verify Next Billing Date");
			AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getChargedPrice()),
					String.valueOf(chargedPrice), "Verify Charged Price");
			AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getSummary()),
					String.valueOf(summary), "Verify Summary");
			AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getSubscriptionStatus()),
					String.valueOf(subscriptionStatus), "Verify Subscription Status");
			AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getSubscriptionBillingCode()),
					String.valueOf(sBC), "Verify Subscription Billing Code");
			AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getChargedBillingCode()),
					String.valueOf(cBC), "Verify Charged Billing Code");
			AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getPaid()), String.valueOf(paid),
					"Verify Renewal Allowed");
			AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getCountry()),
					String.valueOf(country), "Verify Country");
			if (deactivationDate != null) {
				AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getDeactivationDate()),
						String.valueOf(deactivationDate), "Verify Deactivation Date");
			}
			AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getMode()), String.valueOf(mode),
					"Verify Mode");
			AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getActivationDate()),
					String.valueOf(activationDate), "Verify Activation Date");
		}
	}

	public static void validateGetUserStatusGeneric(GetUserStatusResponse getUserStatusResponse, Long startDate,
			Long endDate, Long nextBillingDate, Double chargedPrice, Long activationDate, Long deactivationDate,
			String summary, String subscriptionStatus, String sBC, String cBC, String country, String userSource,
			String mode, boolean paid) throws ParseException {

		AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getStartDate()),
				String.valueOf(startDate), "Verify Start Date");
		AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getEndDate()), String.valueOf(endDate),
				"Verify End Date");
		AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getNextBillingDate()),
				String.valueOf(nextBillingDate), "Verify Next Billing Date");
		AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getChargedPrice()),
				String.valueOf(chargedPrice), "Verify Charged Price");
		AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getSummary()), String.valueOf(summary),
				"Verify Summary");
		AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getSubscriptionStatus()),
				String.valueOf(subscriptionStatus), "Verify Subscription Status");
		AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getSubscriptionBillingCode()),
				String.valueOf(sBC), "Verify Subscription Billing Code");
		AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getChargedBillingCode()),
				String.valueOf(cBC), "Verify Charged Billing Code");
		AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getPaid()), String.valueOf(paid),
				"Verify Renewal Allowed");
		AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getCountry()), String.valueOf(country),
				"Verify Country");
		if (deactivationDate != null) {
			AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getDeactivationDate()),
					String.valueOf(deactivationDate), "Verify Deactivation Date");
		}
		// AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getMode()),
		// String.valueOf(mode),"Verify Mode");
		// AppAssert.assertEqual(String.valueOf(getUserStatusResponse.getStatus().getActivationDate()),String.valueOf(activationDate),
		// "Verify Activation Date");

	}

}
