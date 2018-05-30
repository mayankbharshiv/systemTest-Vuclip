package com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.utils.AppAssert;

import io.restassured.response.Response;

public class SASValidationHelper {

	public void validate_sms_api_response(Response smsApiResponse) throws Exception {
		Log4J.getLogger().info("Validating API response " + smsApiResponse.getStatusLine());
		
		AppAssert.assertEqual(smsApiResponse.statusCode(), 200, "Validate Response: ");
	}
}
