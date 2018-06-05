package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RabbitMQConnection;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.SchedulerRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import com.vuclip.premiumengg.automation.utils.JsonHelper;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;

public class SchedulerAPINegativeTests {
	private static Logger logger = Log4J.getLogger("SchedulerApiFieldsValidation");
	private SASHelper sasHelper;
	int productId;
	int partnerId;
	PublishConfigRequest publishConfigRequest = null;
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
		productId = RandomUtils.nextInt(2000, 3000);
		partnerId = productId;
	}

	@DataProvider(name = "getProductConfig")
	public Object[][] getProductConfig() {
		logger.info("========================Setting up config Data===========================");

		return SASUtils.getALLActivityType();

	}

	@Test(dataProvider = "getProductConfig")
	public void createConfigData(String activityType) throws Exception {

		publishConfigRequest = SASUtils.generateSaveProductConfig(productId, partnerId, activityType);
		SASValidationHelper.validate_sas_api_response(sasHelper.saveProduct(publishConfigRequest));
	}

	@DataProvider(name = "schedulerApiInvalidfieldValues")
	public Object[][] schedulerApiInvalidfieldValues() {
		return new Object[][] { { 0, 0, countryCode }, { 0, 0, null }, { productId, 0, countryCode },
				{ 0, partnerId, countryCode }, { partnerId, productId, null },

		};
	}

	@Test(dependsOnMethods = "createConfigData", dataProvider = "schedulerApiInvalidfieldValues",enabled=false)
	public void schedulerApiInvalidFieldsValidation(Integer sproductId, Integer spartnerId, String scountryCode)
			throws Exception {
		subscriptionId = RandomUtils.nextInt(100, 200);
		//SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting scheduler api invalid fields validation test  [ " + testMessage + " ]");

		try {

			SASValidationHelper.validate_sas_api_response(
					sasHelper.userSubscription(SASUtils.generateUserSubscriptionRequest(productId, partnerId,
							activityType, previousSubscriptionState, currentSubscriptionState, transactionState,
							actionType, subscriptionId)));

			Map<String, String> expectedRecords = new HashMap<String, String>();
			expectedRecords.put("status", "OPEN");
			expectedRecords.put("product_id", String.valueOf(productId));
			expectedRecords.put("partner_id", String.valueOf(partnerId));
			expectedRecords.put("subscription_id", String.valueOf(subscriptionId));
			expectedRecords.put("country_code", countryCode);

			SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId
					+ " and product_id = " + productId + " and partner_id=" + partnerId).get(0), expectedRecords);

			SchedulerRequest generateSchedulerRequest = SASUtils.generateSchedulerRequest(sproductId, spartnerId,
					actionTable);
			generateSchedulerRequest.setCountry(scountryCode);
			SASValidationHelper.validate_schedular_api_response(sasHelper.scheduler(generateSchedulerRequest));

			SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId
					+ " and product_id=" + productId + " and partner_id=" + partnerId).get(0), expectedRecords);

			Message message = RabbitMQConnection.getRabbitTemplate()
					.receive(productId + "_" + partnerId + "_" + actionTable.toUpperCase() + "_REQUEST_BACKEND", 10000);
			AppAssert.assertTrue(message == null, "Verify there is no record in queue for subscription");
		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}

	@DataProvider(name = "schedulerApiMissingFields")
	public Object[][] schedulerApiMissingFields() {
		return new Object[][] { 
			{"productId"}, {"partnerId" }, {"country"}
		};
	}

	@Test(dependsOnMethods = "createConfigData", dataProvider = "schedulerApiMissingFields",enabled=false)
	public void schedulerApiMissingFieldsValidation(String jsonElement)
			throws Exception {
		subscriptionId = RandomUtils.nextInt(100, 200);
		//SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting scheduler api missing fields test  [ " + testMessage + " ]");

		try {

			SASValidationHelper.validate_sas_api_response(
					sasHelper.userSubscription(SASUtils.generateUserSubscriptionRequest(productId, partnerId,
							activityType, previousSubscriptionState, currentSubscriptionState, transactionState,
							actionType, subscriptionId)));

			Map<String, String> expectedRecords = new HashMap<String, String>();
			expectedRecords.put("status", "OPEN");
			expectedRecords.put("product_id", String.valueOf(productId));
			expectedRecords.put("partner_id", String.valueOf(partnerId));
			expectedRecords.put("subscription_id", String.valueOf(subscriptionId));
			expectedRecords.put("country_code", countryCode);

			SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId
					+ " and product_id = " + productId + " and partner_id=" + partnerId).get(0), expectedRecords);

			SchedulerRequest generateSchedulerRequest = SASUtils.generateSchedulerRequest(productId, partnerId,
					actionTable);
			String jsonString =JsonHelper.remove(SchedulerRequest.class, generateSchedulerRequest, jsonElement);
			generateSchedulerRequest= ObjectMapperUtils.readValueFromString(jsonString,SchedulerRequest.class);
			SASValidationHelper.validate_schedular_api_response(sasHelper.scheduler(generateSchedulerRequest));

			SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId
					+ " and product_id=" + productId + " and partner_id=" + partnerId).get(0), expectedRecords);

			Message message = RabbitMQConnection.getRabbitTemplate()
					.receive(productId + "_" + partnerId + "_" + actionTable.toUpperCase() + "_REQUEST_BACKEND", 10000);
			AppAssert.assertTrue(message == null, "Verify there is no record in queue for subscription");
		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}
	

	@Test(dependsOnMethods = "createConfigData")
	public void invalidActivityTypeFieldValue() throws Exception {
		subscriptionId = RandomUtils.nextInt(100, 200);
		//SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting Invalid Activity Type Field Value test  [ " + testMessage + " ]");

		try {

			SASValidationHelper.validate_sas_api_response(
					sasHelper.userSubscription(SASUtils.generateUserSubscriptionRequest(productId, partnerId,
							activityType, previousSubscriptionState, currentSubscriptionState, transactionState,
							actionType, subscriptionId)));
			
			SchedulerRequest generateSchedulerRequest = SASUtils.generateSchedulerRequest(productId, partnerId,
					actionTable);
			generateSchedulerRequest.setActivityType(null);

			SASValidationHelper.validate_schedular_invalid_api_response(
					sasHelper.scheduler(generateSchedulerRequest));

		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}
	@Test(dependsOnMethods = "createConfigData")
	public void missingActivityTypeFieldValue() throws Exception {
		subscriptionId = RandomUtils.nextInt(100, 200);
		//SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting Invalid BlackOut Window Field Value test  [ " + testMessage + " ]");

		try {

			SASValidationHelper.validate_sas_api_response(
					sasHelper.userSubscription(SASUtils.generateUserSubscriptionRequest(productId, partnerId,
							activityType, previousSubscriptionState, currentSubscriptionState, transactionState,
							actionType, subscriptionId)));
			
			SchedulerRequest generateSchedulerRequest = SASUtils.generateSchedulerRequest(productId, partnerId,
					actionTable);
			String jsonString =JsonHelper.remove(SchedulerRequest.class, generateSchedulerRequest, "activityType");
			generateSchedulerRequest= ObjectMapperUtils.readValueFromString(jsonString,SchedulerRequest.class);

			SASValidationHelper.validate_schedular_invalid_api_response(
					sasHelper.scheduler(generateSchedulerRequest));
			
		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}
}
