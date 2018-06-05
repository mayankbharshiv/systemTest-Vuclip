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

import com.vuclip.premiumengg.automation.billing_package_service.common.models.QueueResponse;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RabbitMQConnection;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;

import io.restassured.response.Response;

/**
 * 
 * @author mayank.bharshiv
 *
 */

public class SASDeactivationRetryTests {
	private static Logger logger = Log4J.getLogger("ActivationRetryTests");
	private SASHelper sasHelper;
	int productId;
	int partnerId;
	PublishConfigRequest publishConfigRequest = null;
	private String countryCode = "IN";

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

	@DataProvider(name = "deactivationRetryPositiveTestType")
	public Object[][] deactivationRetryPositiveTestType() {
		return new Object[][] {
				{ "DEACTIVATION_RETRY", "DCT_INIT", "DCT_INIT", "ERROR", "DEACTIVATE_CONSENT", 101, "deactivation",
						"OPEN" },
				{ "DEACTIVATION_RETRY", "DCT_INIT", "DCT_INIT", "FAILURE", "DEACTIVATE_CONSENT", 102, "deactivation",
						"OPEN" } };
	}

	@Test(dependsOnMethods = "createConfigData", dataProvider = "deactivationRetryPositiveTestType")
	public void RetryDeactivationPositiveRetryTests(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String actionType, Integer subscriptionId,
			String actionTable, String status) throws Exception {

		subscriptionId = RandomUtils.nextInt(100, 200);
		//SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting positive Deactivation_Retry retry test  [ " + testMessage + " ]");

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

			SASValidationHelper.validate_schedular_api_response(
					sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, actionTable)));

			expectedRecords.put("status", "IN_PROGRESS");

			SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId
					+ " and product_id=" + productId + " and partner_id=" + partnerId).get(0), expectedRecords);

			Message message = RabbitMQConnection.getRabbitTemplate()
					.receive(productId + "_" + partnerId + "_" + actionTable.toUpperCase() + "_REQUEST_BACKEND", 25000);
			SASValidationHelper.validateQueueMessage(
					ObjectMapperUtils.readValueFromString(new String(message.getBody()), QueueResponse.class),
					productId, partnerId, subscriptionId, countryCode, actionTable.toUpperCase());
		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}

	@DataProvider(name = "deactivationRetryNegativeTestType")
	public Object[][] deactivationRetryNegativeTestType() {
		return new Object[][] {

				{ "DEACTIVATION_RETRY", "DCT_INIT", "DEACTIVATED", "SUCCESS", "DEACTIVATE_CONSENT", 103, "deactivation",
						"OPEN" },
				{ "DEACTIVATION_RETRY", "DCT_INIT", "DEACTIVATED", "ERROR", "DEACTIVATE_CONSENT", 104, "deactivation",
						"OPEN" },
				{ "DEACTIVATION_RETRY", "DCT_INIT", "DEACTIVATED", "FAILURE", "DEACTIVATE_CONSENT", 105, "deactivation",
						"OPEN" },
				{ "DEACTIVATION_RETRY", "DCT_INIT", "DCT_INIT", "SUCCESS", "DEACTIVATE_CONSENT", 106, "deactivation",
						"OPEN" } };
	}

	@Test(dependsOnMethods = "createConfigData", dataProvider = "deactivationRetryNegativeTestType")
	public void RetryDeactivationNegativeTestType(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String actionType, Integer subscriptionId,
			String actionTable, String status) throws Exception {
		subscriptionId = RandomUtils.nextInt(3000, 4000);
		//SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting Negative RetryDeactivationTests retry test  [ " + testMessage + " ]");

		try {
			SASValidationHelper.validate_sas_api_response(
					sasHelper.userSubscription(SASUtils.generateUserSubscriptionRequest(productId, partnerId,
							activityType, previousSubscriptionState, currentSubscriptionState, transactionState,
							actionType, subscriptionId)));

			AppAssert
					.assertEqual(
							DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId + " and product_id = "
									+ productId + " and partner_id=" + partnerId).size(),
							0, "Verify no record created");

			Response schedulerResponse = sasHelper
					.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, actionTable));
			SASValidationHelper.validate_schedular_api_response(schedulerResponse);

			AppAssert
					.assertEqual(
							DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId + " and product_id = "
									+ productId + " and partner_id=" + partnerId).size(),
							0, "Verify no record created");

			Message message = RabbitMQConnection.getRabbitTemplate()
					.receive(productId + "_" + partnerId + "_" + actionTable.toUpperCase() + "_REQUEST_BACKEND", 10000);
			AppAssert.assertTrue(message == null, "Verify there is no record in queue for subscription");
		} catch (Exception e) {
			Assert.fail(e.toString());
		}

	}

}
