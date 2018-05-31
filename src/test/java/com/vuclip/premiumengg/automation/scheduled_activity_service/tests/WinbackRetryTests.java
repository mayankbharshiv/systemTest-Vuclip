package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
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

public class WinbackRetryTests {
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

	@DataProvider(name = "winbackPositiveTestType")
	public Object[][] winbackPositiveTestType() {
		return new Object[][] {
	{"WINBACK","PARKING","ACTIVATED","SUCCESS","CHARGING","renewal","OPEN"},
	{"WINBACK","PARKING","PARKING","LOW_BALANCE","CHARGING","winback","OPEN"},
	{"WINBACK","PARKING","PARKING","ERROR","CHARGING","winback","OPEN"},

		};
	}

	@Test(dependsOnMethods = "createConfigData", dataProvider = "activationPostiveTestType")
	public void winbackPositiveRetryTests(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String actionType, Integer subscriptionId,
			String actionTable, String status) throws Exception {

		subscriptionId = RandomUtils.nextInt(100, 200);
		SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		subscriptionId = RandomUtils.nextInt(2000, 3000);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting Winback activation retry test  [ " + testMessage + " ]");

		try {

			SASValidationHelper.validate_sas_api_response(
					sasHelper.userSubscription(SASUtils.generateUserSubscriptionRequest(productId, partnerId,
							activityType, previousSubscriptionState,currentSubscriptionState, transactionState, actionType, subscriptionId)));


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
			e.printStackTrace();
		}
	}

	@DataProvider(name = "winbackNegativeTestType")
	public Object[][] winbackNegativeTestType() {
		return new Object[][] {
			{"WINBACK","PARKING","ACTIVATED","LOW_BALANCE","CHARGING","renewal","OPEN"},
	{"WINBACK","PARKING","ACTIVATED","ERROR","CHARGING","renewal","OPEN"},
	{"WINBACK","PARKING","PARKING","SUCCESS","CHARGING","winback","OPEN"},
	{"WINBACK","PARKING","PARKING","ERROR","CHARGING","winback","OPEN"},
	{"WINBACK","PARKING","PARKING","SUCCESS","CHARGING","winback","OPEN"},
	{"WINBACK","PARKING","PARKING","LOW_BALANCE","CHARGING","winback","OPEN"}
		};
	}



	@Test(dependsOnMethods = "createConfigData", dataProvider = "activationNegativeTestType")
	public void winbackNegativeTestType(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String actionType, Integer subscriptionId,
			String actionTable, String status) throws Exception {
		subscriptionId = RandomUtils.nextInt(3000, 4000);
		SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting Winback activation retry test  [ " + testMessage + " ]");

		try {
			SASValidationHelper.validate_sas_api_response(
					sasHelper.userSubscription(SASUtils.generateUserSubscriptionRequest(productId, partnerId,
							activityType, previousSubscriptionState,currentSubscriptionState, transactionState, actionType, subscriptionId)));

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
			e.printStackTrace();
		}

	}

}
