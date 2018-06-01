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
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.ActivityType;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASDBHelper;
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

public class SystemChurnRetryTests {
	private static Logger logger = Log4J.getLogger("SystemChurnRetryTests");
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

	@DataProvider(name = "activationSetupConfigJob")
	public Object[][] activityType() {
		return new Object[][] { { ActivityType.ACTIVATION_TYPE }, { ActivityType.ACTIVATION_RETRY_TYPE },
				{ ActivityType.DEACTIVATION }, { ActivityType.DEACTIVATION_RETRY_TYPE },
				{ ActivityType.FREETRIAL_RENEWAL_TYPE }, { ActivityType.RENEWAL_TYPE },
				{ ActivityType.SYSTEM_CHURN_TYPE }, { ActivityType.WINBACK_TYPE } };

	}

	@Test(dataProvider = "activationSetupConfigJob")
	public void createConfigData(String activityType) throws Exception {

		// create job config for activity types( ex- Activation, deactivation)
		publishConfigRequest = SASUtils.generateSaveProductConfig(productId, partnerId, activityType);
		SASValidationHelper.validate_sas_api_response(sasHelper.saveProduct(publishConfigRequest));
	}

	@DataProvider(name = "churnPostiveTestType")
	public Object[][] churnPostiveTestType() {
		return new Object[][] {
				{ "SYSTEM_CHURN", "ACT_INIT", "DCT_INIT", "FAILURE", "DEACTIVATE_CONSENT", 101, "SYSTEM_CHURN", "OPEN" },
				{ "SYSTEM_CHURN", "ACT_INIT", "DCT_INIT", "ERROR", "DEACTIVATE_CONSENT", 102, "SYSTEM_CHURN", "OPEN" },
				{ "SYSTEM_CHURN", "SUSPEND", "DCT_INIT", "FAILURE", "DEACTIVATE_CONSENT", 103, "SYSTEM_CHURN", "OPEN" },
				{ "SYSTEM_CHURN", "SUSPEND", "DCT_INIT", "ERROR", "DEACTIVATE_CONSENT", 104, "SYSTEM_CHURN", "OPEN" },
				{ "SYSTEM_CHURN", "PARKING", "DCT_INIT", "FAILURE", "DEACTIVATE_CONSENT", 105, "SYSTEM_CHURN", "OPEN" },
				{ "SYSTEM_CHURN", "PARKING", "DCT_INIT", "ERROR", "DEACTIVATE_CONSENT", 106, "SYSTEM_CHURN", "OPEN" },
				{ "SYSTEM_CHURN", "ACTIVATED", "DCT_INIT", "FAILURE", "DEACTIVATE_CONSENT", 107, "SYSTEM_CHURN", "OPEN" },
				{ "SYSTEM_CHURN", "ACTIVATED", "DCT_INIT", "ERROR", "DEACTIVATE_CONSENT", 108, "SYSTEM_CHURN", "OPEN" },
				{ "SYSTEM_CHURN", "SUSPEND", "DCT_INIT", "IN_PROGRESS", "DEACTIVATE_CONSENT", 109, "SYSTEM_CHURN", "OPEN" },
				{ "SYSTEM_CHURN", "ACT_INIT", "DCT_INIT", "IN_PROGRESS", "DEACTIVATE_CONSENT", 110, "SYSTEM_CHURN", "OPEN" },
				{ "SYSTEM_CHURN", "PARKING", "DCT_INIT", "IN_PROGRESS", "DEACTIVATE_CONSENT", 111, "SYSTEM_CHURN", "OPEN" },
				{ "SYSTEM_CHURN", "ACTIVATED", "DCT_INIT", "IN_PROGRESS", "DEACTIVATE_CONSENT", 112, "SYSTEM_CHURN", "OPEN" }
		};
	
	
	}

	@Test(dependsOnMethods = "createConfigData", dataProvider = "churnPostiveTestType")
	public void churnPositiveRetryTests(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String actionType, Integer subscriptionId,
			String actionTable, String status) throws Exception {

		subscriptionId = RandomUtils.nextInt(100, 200);
		SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting positive churn retry test  [ " + testMessage + " ]");

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

			SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable.substring(actionTable.indexOf("_")+1).toLowerCase(), "subscription_id = " + subscriptionId
					+ " and product_id = " + productId + " and partner_id=" + partnerId).get(0), expectedRecords);

			SASValidationHelper.validate_schedular_api_response(
					sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, actionTable)));

			expectedRecords.put("status", "IN_PROGRESS");

			SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable.substring(actionTable.indexOf("_")+1).toLowerCase(), "subscription_id = " + subscriptionId
					+ " and product_id=" + productId + " and partner_id=" + partnerId).get(0), expectedRecords);

			Message message = RabbitMQConnection.getRabbitTemplate()
					.receive(productId + "_" + partnerId + "_" + actionTable + "_REQUEST_BACKEND", 25000);
			SASValidationHelper.validateQueueMessage(
					ObjectMapperUtils.readValueFromString(new String(message.getBody()), QueueResponse.class),
					productId, partnerId, subscriptionId, countryCode, actionTable);
		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}

	@DataProvider(name = "churnNegativeTestType")
	public Object[][] churnNegativeTestType() {
		return new Object[][] {
			/*	{ "SYSTEM_CHURN", "SUSPEND", "DEACTIVATED", "SUCCESS", "DEACTIVATE_CONSENT", 113, "SYSTEM_CHURN", "OPEN" },
				{ "SYSTEM_CHURN", "ACT_INIT", "DEACTIVATED", "SUCCESS", "DEACTIVATE_CONSENT", 114, "SYSTEM_CHURN", "OPEN" },
				{ "SYSTEM_CHURN", "PARKING", "DEACTIVATED", "SUCCESS", "DEACTIVATE_CONSENT", 115, "SYSTEM_CHURN", "OPEN" },
				{ "SYSTEM_CHURN", "ACTIVATED", "DEACTIVATED", "SUCCESS", "DEACTIVATE_CONSENT", 116, "SYSTEM_CHURN", "OPEN" },*/
				

				{ "SYSTEM_CHURN", "PARKING", "DEACTIVATED", "IN_PROGRESS", "DEACTIVATE_CONSENT", 117, "SYSTEM_CHURN", "OPEN" },
				{ "SYSTEM_CHURN", "PARKING", "DEACTIVATED", "FAILURE", "DEACTIVATE_CONSENT", 118, "SYSTEM_CHURN", "OPEN" },
				{ "SYSTEM_CHURN", "PARKING", "DEACTIVATED", "ERROR", "DEACTIVATE_CONSENT", 119, "SYSTEM_CHURN", "OPEN" },
				{ "SYSTEM_CHURN", "PARKING", "DCT_INIT", "SUCCESS", "DEACTIVATE_CONSENT", 119, "SYSTEM_CHURN", "OPEN" },
		
		};
	}

	@Test(dependsOnMethods = "createConfigData", dataProvider = "churnNegativeTestType")
	public void activationNegativeTestType(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String actionType, Integer subscriptionId,
			String actionTable, String status) throws Exception {
		subscriptionId = RandomUtils.nextInt(3000, 4000);
		SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting Negative churn retry test  [ " + testMessage + " ]");

		try {
			SASValidationHelper.validate_sas_api_response(
					sasHelper.userSubscription(SASUtils.generateUserSubscriptionRequest(productId, partnerId,
							activityType, previousSubscriptionState, currentSubscriptionState, transactionState,
							actionType, subscriptionId)));

			AppAssert
					.assertEqual(
							DBUtils.getRecord(actionTable.substring(actionTable.indexOf("_")+1).toLowerCase(), "subscription_id = " + subscriptionId + " and product_id = "
									+ productId + " and partner_id=" + partnerId).size(),
							0, "Verify no record created");

			Response schedulerResponse = sasHelper
					.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, actionTable));
			SASValidationHelper.validate_schedular_api_response(schedulerResponse);

			AppAssert
					.assertEqual(
							DBUtils.getRecord(actionTable.substring(actionTable.indexOf("_")+1).toLowerCase(), "subscription_id = " + subscriptionId + " and product_id = "
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
