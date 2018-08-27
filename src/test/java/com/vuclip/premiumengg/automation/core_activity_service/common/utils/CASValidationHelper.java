package com.vuclip.premiumengg.automation.core_activity_service.common.utils;

import java.util.Map;

import com.vuclip.premiumengg.automation.configuration_service.common.models.AdNetworkResponseVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.BlockedUserResponseVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.ChargedUserResponseVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.DeactivateUserResponseVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.UnBlockedUserResponseVO;
import com.vuclip.premiumengg.automation.utils.AppAssert;

import io.restassured.response.Response;

public class CASValidationHelper {

	public static void validateResponse(Response response) {
		AppAssert.assertEqual(response.statusCode(), 200, "Vefiry that response status code is 200 ");
	}

	public static void validateTableRecord(Map<String, Object> map, AdNetworkResponseVO adNetwotkResponseVO) {

		AppAssert.assertEqual(map.get("name").toString(), adNetwotkResponseVO.getAdNetwork().getName().toString());
		AppAssert.assertEqual(map.get("retry_limit").toString(),
				adNetwotkResponseVO.getAdNetwork().getRetryLimit().toString());
		AppAssert.assertEqual(map.get("request_param_name").toString(),
				adNetwotkResponseVO.getAdNetwork().getRequestParamName());
		AppAssert.assertEqual(map.get("churn_notification_url").toString(),
				adNetwotkResponseVO.getAdNetwork().getChurnNotificationUrl());
		AppAssert.assertEqual(map.get("notification_url").toString(),
				adNetwotkResponseVO.getAdNetwork().getNotificationUrl());
		AppAssert.assertEqual(map.get("http_method").toString(), adNetwotkResponseVO.getAdNetwork().getHttpMethod());
		AppAssert.assertEqual(map.get("notify_on_activity").toString(),
				adNetwotkResponseVO.getAdNetwork().getNotifyOnActivity());
		AppAssert.assertEqual(map.get("status").toString(), adNetwotkResponseVO.getAdNetwork().getStatus());
		AppAssert.assertEqual(map.get("source_identifier").toString(),
				adNetwotkResponseVO.getAdNetwork().getSourceIdentifier());

	}

	public static void validateChargedUserResponse(ChargedUserResponseVO actual, ChargedUserResponseVO expected) {

		AppAssert.assertEqual(actual.getResultVO().getMessage(), expected.getResultVO().getMessage(),
				"Verification for message");
		AppAssert.assertEqual(actual.getResultVO().getResponseCode(), expected.getResultVO().getResponseCode(),
				"verification for respose code");
		AppAssert.assertEqual(actual.getResultVO().getStatus(), expected.getResultVO().getStatus(),
				"Verification  for Status");

	}

	public static void validateBlockedUserResponse(BlockedUserResponseVO actual, BlockedUserResponseVO expected) {

		AppAssert.assertEqual(actual.getResultVO().getMessage().toLowerCase(),
				expected.getResultVO().getMessage().toLowerCase(), "Verification for message");
		AppAssert.assertEqual(actual.getResultVO().getResponseCode().toLowerCase(),
				expected.getResultVO().getResponseCode().toLowerCase(), "verification for respose code");
		AppAssert.assertEqual(actual.getResultVO().getStatus().toLowerCase(),
				expected.getResultVO().getStatus().toLowerCase(), "Verification  for Status");
		AppAssert.assertEqual(actual.getBlockedUserData().getBlockType(), expected.getBlockedUserData().getBlockType(),
				"Verification  for BlockType");
		if (actual.getUserStatus() != null) {
			AppAssert.assertEqual(actual.getUserStatus().getSubscriptionStatus(),
					expected.getUserStatus().getSubscriptionStatus(), "Verification  for Subscription Status");
			AppAssert.assertEqual(actual.getUserStatus().getSummary(), expected.getUserStatus().getSummary(),
					"Verification  for Subscription Summary");
			AppAssert.assertEqual(actual.getUserStatus().getPartnerId(), expected.getUserStatus().getPartnerId(),
					"Verification  for Partner Id");
			AppAssert.assertEqual(actual.getUserStatus().getProductId(), expected.getUserStatus().getProductId(),
					"Verification  for Product Id");
			AppAssert.assertEqual(actual.getUserStatus().getUserId(), expected.getUserStatus().getUserId(),
					"Verification  for User Id");
			AppAssert.assertEqual(actual.getUserStatus().getMsisdn(), expected.getUserStatus().getMsisdn(),
					"Verification  for Msisdn");
		}
	}

	public static void validateUnBlockedUserResponse(UnBlockedUserResponseVO actual, UnBlockedUserResponseVO expected) {

		AppAssert.assertEqual(actual.getResponse().getMessage(), expected.getResponse().getMessage(),
				"Verification for message");
		AppAssert.assertEqual(actual.getResponse().getResponseCode(), expected.getResponse().getResponseCode(),
				"verification for respose code");
		AppAssert.assertEqual(actual.getResponse().getSuccessful().toString(),
				expected.getResponse().getSuccessful().toString(), "Verification  for Successful");

	}

	public static void validateDeactivateUserResponse(DeactivateUserResponseVO actual,
			DeactivateUserResponseVO expected) {

		AppAssert.assertEqual(actual.getResultVO().getMessage().toLowerCase(),
				expected.getResultVO().getMessage().toLowerCase(), "Verification for message");
		AppAssert.assertEqual(actual.getResultVO().getResponseCode().toLowerCase(),
				expected.getResultVO().getResponseCode().toLowerCase(), "verification for respose code");
		AppAssert.assertEqual(actual.getResultVO().getStatus().toLowerCase(),
				expected.getResultVO().getStatus().toLowerCase(), "Verification  for Status");
		if (actual.getUserStatus() != null) {
			AppAssert.assertEqual(actual.getUserStatus().getSubscriptionStatus(),
					expected.getUserStatus().getSubscriptionStatus(), "Verification  for Subscription Status");
			AppAssert.assertEqual(actual.getUserStatus().getSummary(), expected.getUserStatus().getSummary(),
					"Verification  for Subscription Summary");
			AppAssert.assertEqual(actual.getUserStatus().getPartnerId(), expected.getUserStatus().getPartnerId(),
					"Verification  for Partner Id");
			AppAssert.assertEqual(actual.getUserStatus().getProductId(), expected.getUserStatus().getProductId(),
					"Verification  for Product Id");
			AppAssert.assertEqual(actual.getUserStatus().getUserId(), expected.getUserStatus().getUserId(),
					"Verification  for User Id");
			AppAssert.assertEqual(actual.getUserStatus().getMsisdn(), expected.getUserStatus().getMsisdn(),
					"Verification  for Msisdn");
		}
	}
}
