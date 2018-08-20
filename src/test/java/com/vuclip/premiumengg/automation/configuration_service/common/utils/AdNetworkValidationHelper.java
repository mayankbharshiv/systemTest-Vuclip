package com.vuclip.premiumengg.automation.configuration_service.common.utils;

import java.util.Map;

import com.vuclip.premiumengg.automation.configuration_service.common.models.AdNetworkResponseVO;
import com.vuclip.premiumengg.automation.utils.AppAssert;

public class AdNetworkValidationHelper {

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

	public static void validateResponse(AdNetworkResponseVO actual, AdNetworkResponseVO expected) {

		AppAssert.assertEqual(actual.getMessage(), expected.getMessage(), "Verification for message");
		AppAssert.assertEqual(actual.getResponseCode(), expected.getResponseCode(), "verification for respose code");
		AppAssert.assertEqual(actual.isSuccessful(), expected.isSuccessful());
		if (actual.getAdNetwork().getOperationType() != null) {
			AppAssert.assertEqual(actual.getAdNetwork().getName(), expected.getAdNetwork().getName());
			AppAssert.assertEqual(actual.getAdNetwork().getRetryLimit(), expected.getAdNetwork().getRetryLimit());
			AppAssert.assertEqual(actual.getAdNetwork().getRequestParamName(),
					expected.getAdNetwork().getRequestParamName());
			AppAssert.assertEqual(actual.getAdNetwork().getChurnNotificationUrl(),
					expected.getAdNetwork().getChurnNotificationUrl());
			AppAssert.assertEqual(actual.getAdNetwork().getNotificationUrl(),
					expected.getAdNetwork().getNotificationUrl());
			AppAssert.assertEqual(actual.getAdNetwork().getHttpMethod(), expected.getAdNetwork().getHttpMethod());
			AppAssert.assertEqual(actual.getAdNetwork().getNotifyOnActivity(),
					expected.getAdNetwork().getNotifyOnActivity());
			AppAssert.assertEqual(actual.getAdNetwork().getStatus(), expected.getAdNetwork().getStatus());
			AppAssert.assertEqual(actual.getAdNetwork().getSourceIdentifier(),
					expected.getAdNetwork().getSourceIdentifier());
			AppAssert.assertEqual(actual.getAdNetwork().getOperationType(), expected.getAdNetwork().getOperationType());
		} else {

			AppAssert.assertEqual(actual.getAdNetwork().getName(), expected.getAdNetwork().getName());
			AppAssert.assertEqual(actual.getAdNetwork().getRetryLimit(), expected.getAdNetwork().getRetryLimit());
			AppAssert.assertEqual(actual.getAdNetwork().getRequestParamName(),
					expected.getAdNetwork().getRequestParamName());
			AppAssert.assertEqual(actual.getAdNetwork().getChurnNotificationUrl(),
					expected.getAdNetwork().getChurnNotificationUrl());
			AppAssert.assertEqual(actual.getAdNetwork().getNotificationUrl(),
					expected.getAdNetwork().getNotificationUrl());
			AppAssert.assertEqual(actual.getAdNetwork().getHttpMethod(), expected.getAdNetwork().getHttpMethod());
			AppAssert.assertEqual(actual.getAdNetwork().getNotifyOnActivity(),
					expected.getAdNetwork().getNotifyOnActivity());
			AppAssert.assertEqual(actual.getAdNetwork().getStatus(), expected.getAdNetwork().getStatus());
			AppAssert.assertEqual(actual.getAdNetwork().getSourceIdentifier(),
					expected.getAdNetwork().getSourceIdentifier());

		}

	}
}
