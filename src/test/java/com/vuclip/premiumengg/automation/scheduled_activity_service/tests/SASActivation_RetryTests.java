package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASDBHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;

/**
 * 
 * @author rahul.s
 *
 */

public class SASActivation_RetryTests {
	private static Logger logger = Log4J.getLogger("Activation_RetrySASTests");
	private SASHelper sasHelper;
	int productId;
	int partnerId;
	PublishConfigRequest publishConfigRequest = null;
//	private String countryCode = "IN";

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		sasHelper = new SASHelper();
		productId = RandomUtils.nextInt(6000, 7000);
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

	@DataProvider(name = "activationPostiveTestType")
	public Object[][] activationPostiveTestType() {
		return new Object[][] {

				{ "ACTIVATION", "ACT_INIT", "SUCCESS", "CHARGING", "activation" },
				{ "ACTIVATION_RETRY", "ACTIVATED", "SUCCESS", "CHARGING", "activation" },
				{ "ACTIVATION_RETRY", "ACTIVATED", "LOW_BALANCE", "CHARGING", "activation" },
				{ "ACTIVATION_RETRY", "ACTIVATED", "FAILURE", "CHARGING", "activation" },
				{ "ACTIVATION_RETRY", "ACTIVATED", "ERROR", "CHARGING", "activation" },
				{ "ACTIVATION_RETRY", "ACTIVATED", "IN_PROGRESS", "CHARGING", "activation" },

				{ "ACTIVATION_RETRY", "ACT_INIT", "LOW_BALANCE", "CHARGING", "activation" },
				{ "ACTIVATION_RETRY", "ACT_INIT", "FAILURE", "CHARGING", "activation" },
				{ "ACTIVATION_RETRY", "ACT_INIT", "ERROR", "CHARGING", "activation" },
				{ "ACTIVATION_RETRY", "ACT_INIT", "IN_PROGRESS", "CHARGING", "activation" },
				{ "ACTIVATION_RETRY", "ACT_INIT", "SUCCESS", "CHARGING", "activation" },

				{ "ACTIVATION_RETRY", "PARKING", "SUCCESS", "CHARGING", "activation" },
				{ "ACTIVATION_RETRY", "PARKING", "LOW_BALANCE", "CHARGING", "activation" },
				{ "ACTIVATION_RETRY", "PARKING", "FAILURE", "CHARGING", "activation" },
				{ "ACTIVATION_RETRY", "PARKING", "ERROR", "CHARGING", "activation" },
				{ "ACTIVATION_RETRY", "PARKING", "IN_PROGRESS", "CHARGING", "activation" },

				{ "ACTIVATION_RETRY", "SUSPEND", "SUCCESS", "CHARGING", "activation" },
				{ "ACTIVATION_RETRY", "SUSPEND", "LOW_BALANCE", "CHARGING", "activation" },
				{ "ACTIVATION_RETRY", "SUSPEND", "FAILURE", "CHARGING", "activation" },
				{ "ACTIVATION_RETRY", "SUSPEND", "ERROR", "CHARGING", "activation" },
				{ "ACTIVATION_RETRY", "SUSPEND", "IN_PROGRESS", "CHARGING", "activation" },

		};

	}

	@Test(dependsOnMethods = "createConfigData", dataProvider = "activationPostiveTestType")
	public void activationRenewalPositiveRetryTests(String activityType, String currentSubscriptionState,
			String transactionState, String actionType,String tableName) throws Exception {

		Integer subscriptionId = RandomUtils.nextInt(11000, 12000);
		// //SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = "" + productId + " " + partnerId + " " + subscriptionId + " " + activityType + " "
				 + " " + currentSubscriptionState + " " + transactionState + " "
				+ actionType;
		logger.info("==================>Starting positive activation retry test  [ " + testMessage + " ]");

		try {

			SASValidationHelper.validate_sas_api_response(
					sasHelper.userSubscription(SASUtils.generateUserSubscriptionRequest(productId, partnerId,
							activityType, "", currentSubscriptionState, transactionState,
							actionType, subscriptionId)));

			// Map<String, String> expectedRecords = new HashMap<String, String>();
			// expectedRecords.put("status", "OPEN");
			// expectedRecords.put("product_id", String.valueOf(productId));
			// expectedRecords.put("partner_id", String.valueOf(partnerId));
			// expectedRecords.put("subscription_id", String.valueOf(subscriptionId));
			// expectedRecords.put("country_code", countryCode);

			// SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable,
			// "subscription_id = " + subscriptionId
			// + " and product_id = " + productId + " and partner_id=" + partnerId).get(0),
			// expectedRecords);
			SASDBHelper.showAllActivityTableData("Before ", "subscription_id = " + subscriptionId + " and product_id = "
					+ productId + " and partner_id=" + partnerId);
			// SASValidationHelper.validate_schedular_api_response(
			// sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId,
			// actionTable)));
			//
			// expectedRecords.put("status", "IN_PROGRESS");
			//
			// SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable,
			// "subscription_id = " + subscriptionId
			// + " and product_id=" + productId + " and partner_id=" + partnerId).get(0),
			// expectedRecords);
			// SASDBHelper.showAllActivityTableData("after ",
			// String.valueOf(subscriptionId));

			// Message message = RabbitMQConnection.getRabbitTemplate()
			// .receive(productId + "_" + partnerId + "_" + actionTable.toUpperCase() +
			// "_REQUEST_BACKEND", 25000);
			// SASValidationHelper.validateQueueMessage(
			// ObjectMapperUtils.readValueFromString(new String(message.getBody()),
			// QueueResponse.class),
			// productId, partnerId, subscriptionId, countryCode,
			// actionTable.toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @DataProvider(name = "activationNegativeTestType")
	// public Object[][] activationNegativeTestType() {
	// return new Object[][] {
	// { "ACTIVATION", "ACT_INIT", "ACTIVATED", "LOW_BALANCE", "CHARGING", "activation", 102,
	// "renewal", "OPEN" },
	// { "ACTIVATION", "ACT_INIT", "ACTIVATED", "FAILURE", "CHARGING", "activation", 103,
	// "renewal", "OPEN" },
	// { "ACTIVATION", "ACT_INIT", "ACTIVATED", "ERROR", "CHARGING", "activation", 104, "renewal",
	// "OPEN" },
	// { "ACTIVATION", "ACT_INIT", "ACT_INIT", "SUCCESS", "CHARGING", "activation", 105,
	// "activation", "OPEN" },
	// { "ACTIVATION", "ACT_INIT", "ACT_INIT", "LOW_BALANCE", "CHARGING", "activation", 106,
	// "activation", "OPEN" },
	// { "ACTIVATION", "ACT_INIT", "PARKING", "SUCCESS", "CHARGING", "activation", 109, "winback",
	// "OPEN" },
	// { "ACTIVATION", "ACT_INIT", "PARKING", "FAILURE", "CHARGING", "activation", 110, "winback",
	// "OPEN" },
	// { "ACTIVATION", "ACT_INIT", "PARKING", "ERROR", "CHARGING", "activation", 112, "winback",
	// "OPEN" }
	//
	// };
	// }
	//
	// @Test(dependsOnMethods = "createConfigData", dataProvider =
	// "activationNegativeTestType")
	// public void activationNegativeTestType(String activityType, String
	// previousSubscriptionState,
	// String currentSubscriptionState, String transactionState, String actionType,
	// Integer subscriptionId,
	// String actionTable, String status) throws Exception {
	// subscriptionId = RandomUtils.nextInt(3000, 4000);
	// //SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
	// String testMessage = subscriptionId + " " + activityType + " " +
	// previousSubscriptionState + " "
	// + currentSubscriptionState + " " + transactionState + " " + actionType;
	// logger.info("==================>Starting Negative activation retry test [ " +
	// testMessage + " ]");
	//
	// try {
	// SASValidationHelper.validate_sas_api_response(
	// sasHelper.userSubscription(SASUtils.generateUserSubscriptionRequest(productId,
	// partnerId,
	// activityType, previousSubscriptionState,currentSubscriptionState,
	// transactionState, actionType, subscriptionId)));
	//
	// AppAssert
	// .assertEqual(
	// DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId + " and
	// product_id = "
	// + productId + " and partner_id=" + partnerId).size(),
	// 0, "Verify no record created");
	//
	// Response schedulerResponse = sasHelper
	// .scheduler(SASUtils.generateSchedulerRequest(productId, partnerId,
	// actionTable));
	// SASValidationHelper.validate_schedular_api_response(schedulerResponse);
	//
	// AppAssert
	// .assertEqual(
	// DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId + " and
	// product_id = "
	// + productId + " and partner_id=" + partnerId).size(),
	// 0, "Verify no record created");
	//
	// Message message = RabbitMQConnection.getRabbitTemplate()
	// .receive(productId + "_" + partnerId + "_" + actionTable.toUpperCase() +
	// "_REQUEST_BACKEND", 10000);
	// AppAssert.assertTrue(message == null, "Verify there is no record in queue for
	// subscription");
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }

}
