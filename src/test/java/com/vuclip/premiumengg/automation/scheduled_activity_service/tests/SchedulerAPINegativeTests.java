package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.SchedulerRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;
import com.vuclip.premiumengg.automation.utils.JsonHelper;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;

public class SchedulerAPINegativeTests {
	private static Logger logger = Log4J.getLogger("SchedulerApiFieldsValidation");
	private SASHelper sasHelper;
	int productId;
	int partnerId;
	private String countryCode = "IN";
	String activityType = "WINBACK";
	String previousSubscriptionState = "PARKING";
	String currentSubscriptionState = "ACTIVATED";
	String transactionState = "SUCCESS";
	String actionType = "CHARGING";
	Integer subscriptionId;
	String actionTable = "renewal";

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		sasHelper = new SASHelper();
		productId = RandomUtils.nextInt(77000, 77900);
		partnerId = productId;
	}

	@DataProvider(name = "schedulerApiInvalidfieldValues")
	public Object[][] schedulerApiInvalidfieldValues() {
		return new Object[][] { // { 0, 0, countryCode,"RENEWAL" },
				{ 0, 0, null, null },
				// { productId, 0, countryCode,"RENEWAL" },
				{ partnerId, productId, countryCode, null },
				// { 0, partnerId, countryCode,"RENEWAL" },
				// { partnerId, productId, null,"RENEWAL" },

		};
	}

	@Test(dataProvider = "schedulerApiInvalidfieldValues",groups="negative")
	public void schedulerApiInvalidFieldsValidation(Integer sproductId, Integer spartnerId, String scountryCode,
			String sactivityType) throws Exception {
		subscriptionId = RandomUtils.nextInt(100, 200);
		// SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info(
				"==================>Starting scheduler api invalid fields validation test  [ " + testMessage + " ]");

		try {

			SASValidationHelper.validate_sas_api_response(
					sasHelper.userSubscription(SASUtils.generateUserSubscriptionRequest(productId, partnerId,
							activityType, previousSubscriptionState, currentSubscriptionState, transactionState,
							actionType, subscriptionId)));

			SchedulerRequest generateSchedulerRequest = SASUtils.generateSchedulerRequest(sproductId, spartnerId,
					actionTable);
			generateSchedulerRequest.setCountry(scountryCode);
			generateSchedulerRequest.setActivityType(sactivityType);
			SASValidationHelper.validate_schedular_invalid_api_response(sasHelper.scheduler(generateSchedulerRequest));

		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}

	@DataProvider(name = "schedulerApiMissingFields")
	public Object[][] schedulerApiMissingFields() {
		return new Object[][] { //{ "productId" },
			//{ "partnerId" },
			//{ "country" }
			{ "activityType" }
		};
	}

	@Test(dataProvider = "schedulerApiMissingFields",groups="negative")
	public void schedulerApiMissingFieldsValidation(String jsonElement) throws Exception {
		subscriptionId = RandomUtils.nextInt(100, 200);
		// SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting scheduler api missing fields test  [ " + testMessage + " ]");

		try {

			SASValidationHelper.validate_sas_api_response(
					sasHelper.userSubscription(SASUtils.generateUserSubscriptionRequest(productId, partnerId,
							activityType, previousSubscriptionState, currentSubscriptionState, transactionState,
							actionType, subscriptionId)));
			SchedulerRequest generateSchedulerRequest = SASUtils.generateSchedulerRequest(productId, partnerId,
					actionTable);
			String jsonString = JsonHelper.remove(SchedulerRequest.class, generateSchedulerRequest, jsonElement);
			generateSchedulerRequest = ObjectMapperUtils.readValueFromString(jsonString, SchedulerRequest.class);
			SASValidationHelper.validate_schedular_invalid_api_response(sasHelper.scheduler(generateSchedulerRequest));
		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}}

