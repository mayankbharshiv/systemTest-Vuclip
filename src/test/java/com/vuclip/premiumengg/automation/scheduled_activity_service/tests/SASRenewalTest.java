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
import com.vuclip.premiumengg.automation.utils.DBUtils;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;

/**
 * 
 * @author mayank.bharshiv
 *
 */

public class SASRenewalTest {
	private static Logger logger = Log4J.getLogger("ActivationRetryTests");
	private SASHelper sasHelper;
	int productId;
	int partnerId;
	PublishConfigRequest publishConfigRequest = null;
	private String countryCode = "IN";

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		sasHelper = new SASHelper();
		productId = SASUtils.productId;//RandomUtils.nextInt(2000, 3000);
		partnerId = productId;
	}

/*	@DataProvider(name = "getProductConfig")
	public Object[][] getProductConfig() {
		logger.info("========================Setting up config Data===========================");

		return SASUtils.getALLActivityType();

	}

	@Test(dataProvider = "getProductConfig")
	public void createConfigData(String activityType) throws Exception {

		publishConfigRequest = SASUtils.generateSaveProductConfig(productId, partnerId, activityType);
		SASValidationHelper.validate_sas_api_response(sasHelper.saveProduct(publishConfigRequest));
	}*/

	@DataProvider(name = "activationPostiveTestType")
	public Object[][] activationPostiveTestType() {
		return new Object[][] {
				{ "ACTIVATION", "ACT_INIT", "ACTIVATED", "SUCCESS", "CHARGING", 101, "renewal", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "ACT_INIT", "FAILURE", "CHARGING", 107, "activation", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "ACT_INIT", "ERROR", "CHARGING", 108, "activation", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "PARKING", "LOW_BALANCE", "CHARGING", 111, "winback", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "ACT_INIT", "LOW_BALANCE", "CHARGING", 106, "winback", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "ACT_INIT", "IN_PROGRESS", "CHARGING", 106, "winback", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "ACT_INIT", "NOTIFICATION_WAIT", "CHARGING", 106, "winback", "OPEN" } };

	}

	@Test(/**dependsOnMethods = "createConfigData",**/ dataProvider = "activationPostiveTestType")
	public void activationPositiveRetryTests(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String actionType, Integer subscriptionId,
			String actionTable, String status) throws Exception {

		subscriptionId = RandomUtils.nextInt(31000, 32000);
		//SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting positive activation retry test  [ " + testMessage + " ]");

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

	@DataProvider(name = "activationNegativeTestType")
	public Object[][] activationNegativeTestType() {
		return new Object[][] {
				{ "ACTIVATION", "ACT_INIT", "ACTIVATED", "LOW_BALANCE", "CHARGING", 102, "renewal", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "ACTIVATED", "FAILURE", "CHARGING", 103, "renewal", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "ACTIVATED", "ERROR", "CHARGING", 104, "renewal", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "ACT_INIT", "SUCCESS", "CHARGING", 105, "activation", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "PARKING", "SUCCESS", "CHARGING", 109, "winback", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "PARKING", "FAILURE", "CHARGING", 110, "winback", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "PARKING", "ERROR", "CHARGING", 112, "winback", "OPEN" }

		};
	}

	@Test(/**dependsOnMethods = "createConfigData",**/ dataProvider = "activationNegativeTestType")
	public void activationNegativeTestType(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String actionType, Integer subscriptionId,
			String actionTable, String status) throws Exception {
		subscriptionId = RandomUtils.nextInt(53000, 54000);
		//SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting Negative activation retry test  [ " + testMessage + " ]");

		SASValidationHelper.negativeFlow(productId, activityType, currentSubscriptionState, transactionState,
				actionType, subscriptionId);

	}

}
