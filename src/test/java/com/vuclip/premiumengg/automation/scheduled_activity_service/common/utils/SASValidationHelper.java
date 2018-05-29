package com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils;

import org.testng.Assert;

import com.vuclip.premiumengg.automation.common.Log4J;

import io.restassured.response.Response;

public class SASValidationHelper {

	public void validate_sms_api_response(Response smsApiResponse) throws Exception {
		Log4J.getLogger().info("Validating API response" + smsApiResponse.getStatusLine());
		;
		Assert.assertEquals(smsApiResponse.statusCode(), 200);
	}
}
