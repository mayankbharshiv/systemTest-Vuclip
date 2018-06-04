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
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.UserSubscriptionRequest;
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

public class SASTest {
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
				{ ActivityType.RENEWAL_RETRY_TYPE }, { ActivityType.SYSTEM_CHURN_TYPE },
				{ ActivityType.WINBACK_TYPE } };

	}

	@Test(dataProvider = "activationSetupConfigJob")
	public void createConfigData(String activityType) throws Exception {

		// create job config for activity types( ex- Activation, deactivation)
		publishConfigRequest = SASUtils.generateSaveProductConfig(productId, partnerId, activityType);
		SASValidationHelper.validate_sas_api_response(sasHelper.saveProduct(publishConfigRequest));
	}

	@DataProvider(name = "activationPostiveTestType")
	public Object[][] activationPostiveTestType() {
		return new Object[][] {

				/* PASS */{ "CHARGING", "ACTIVATION", "ACTIVATED", "SUCCESS", "renewal", "ACTIVATED", "SUCCESS",
						"SUCCESS", "renewal" },
				/* PASS */{ "CHARGING", "ACTIVATION", "ACTIVATED", "SUCCESS", "renewal", "ACTIVATED", "ERROR", "ERROR",
						"renewal_retry" },
				/* PASS */{ "CHARGING", "ACTIVATION", "ACTIVATED", "SUCCESS", "renewal", "ACTIVATED", "FAILURE",
						"FAILURE", "renewal_retry" },
				// FAIL processor is not configured{ "CHARGING", "ACTIVATION", "ACTIVATED",
				// "SUCCESS", "renewal","ACTIVATED", "IN_PROGRESS", "IN_PROGRESS",
				// "renewal_retry" }
				// FAIL { "CHARGING", "ACTIVATION", "ACTIVATED", "SUCCESS",
				// "renewal","ACTIVATED", "NOTIFICATION_WAIT", "IN_PROGRESS","renewal_retry" }

				/* PASS */{ "CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE", "activation", "ACTIVATED", "SUCCESS",
						"SUCCESS", "renewal" },
				/* PASS */{ "CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE", "activation", "ACT_INIT", "FAILURE",
						"FAILURE", "renewal" },
				/* PASS */{ "CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE", "activation", "ACT_INIT", "ERROR", "ERROR",
						"activation" },
				// /*fail*/{ "CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE",
				// "activation","ACT_INIT", "NOTIFICATION_WAIT",
				// "NOTIFICATION_WAIT", "activation" },
				// /*fail*/{ "CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE",
				// "activation","ACT_INIT", "IN_PROGRESS",
				// "IN_PROGRESS", "winback" },
				/* PASS */{ "CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE", "activation", "ACT_INIT", "LOW_BALANCE",
						"LOW_BALANCE", "winback" }

				// /*FAIL*/{ "CHARGING", "ACTIVATION", "ACT_INIT", "ERROR",
				// "activation","ACT_INIT", "LOW_BALANCE",
				// "LOW_BALANCE", "winback" },

				// /*FAIL*/{ "CHARGING", "ACTIVATION", "ACT_INIT", "IN_PROGRESS",
				// "winback","ACT_INIT", "LOW_BALANCE",
				// "LOW_BALANCE", "winback" },
				// /*FAIL*/{ "CHARGING", "ACTIVATION", "ACT_INIT", "LOW_BALANCE",
				// "winback","ACT_INIT", "LOW_BALANCE",
				// "LOW_BALANCE", "winback" },
				//
				// /*FAIL*/{ "CHARGING", "ACTIVATION", "ACT_INIT", "NOTIFICATION_WAIT",
				// "winback","ACT_INIT", "LOW_BALANCE",
				// "LOW_BALANCE", "winback" }

		};
	}

	@Test(dependsOnMethods = "createConfigData", dataProvider = "activationPostiveTestType")
	public void activationPositiveRetryTests(String eventActionType, String activityType,
			String currentSubscriptionState, String transactionState, String actionTable,
			String newCurrentSubscriptionState, String newTransactionState, String oldStatusValue,
			String newActionTable) throws Exception {

		Integer subscriptionId = RandomUtils.nextInt(900, 1000);
		// SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = subscriptionId + " " + activityType + " " + currentSubscriptionState + " "
				+ transactionState + " " + eventActionType;
		logger.info("==================>Starting positive activation retry test  [ " + testMessage + " ]");

		try {

			// First USE call
			UserSubscriptionRequest uSRequest = SASUtils.generateUserSubscriptionRequest(productId, partnerId,
					activityType, "ACT_INIT", currentSubscriptionState, transactionState, eventActionType,
					subscriptionId);
			SASValidationHelper.validate_sas_api_response(sasHelper.userSubscription(uSRequest));

			// // DB verification
			// String expectedActivityType = SASDBHelper.showAllActivityTableData("FIRST ",
			// String.valueOf(subscriptionId));

			Map<String, String> expectedRecords = new HashMap<String, String>();
			expectedRecords.put("status", "OPEN");
			expectedRecords.put("product_id", String.valueOf(productId));
			expectedRecords.put("partner_id", String.valueOf(partnerId));
			expectedRecords.put("subscription_id", String.valueOf(subscriptionId));
			expectedRecords.put("country_code", countryCode);
			expectedRecords.put("date", String.valueOf(uSRequest.getSubscriptionInfo().getNextBillingDate()));
			//
			SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable,
					"subscription_id = " + subscriptionId + " and product_id = " + productId + " and partner_id="
							+ partnerId + " and date=" + uSRequest.getSubscriptionInfo().getNextBillingDate())
					.get(0), expectedRecords);

			// scheduler call
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

			// new event call
			UserSubscriptionRequest newSubscriptionRequest = SASUtils.generateUserSubscriptionRequest(productId,
					partnerId, actionTable.toUpperCase(), currentSubscriptionState, newCurrentSubscriptionState,
					newTransactionState, eventActionType, subscriptionId);
			newSubscriptionRequest.getSubscriptionInfo()
					.setNextBillingDate(newSubscriptionRequest.getSubscriptionInfo().getNextBillingDate() + 100);
			newSubscriptionRequest.getActivityEvent()
					.setNextBillingDate(newSubscriptionRequest.getActivityEvent().getNextBillingDate() + 100);

			SASValidationHelper.validate_sas_api_response(sasHelper.userSubscription(newSubscriptionRequest));

			// SASDBHelper.showAllActivityTableData("THIRD",
			// String.valueOf(subscriptionId));
			expectedRecords.put("status", oldStatusValue);
			SASValidationHelper.validateTableRecord(DBUtils
					.getRecord(actionTable,
							"subscription_id = " + subscriptionId + " and product_id=" + productId + " and partner_id="
									+ partnerId + " and date=" + uSRequest.getSubscriptionInfo().getNextBillingDate())
					.get(0), expectedRecords);

			expectedRecords.put("status", "OPEN");
			expectedRecords.put("date",
					String.valueOf(newSubscriptionRequest.getSubscriptionInfo().getNextBillingDate()));

			SASValidationHelper.validateTableRecord(
					DBUtils.getRecord(newActionTable,
							"subscription_id = " + subscriptionId + " and product_id=" + productId + " and partner_id="
									+ partnerId + " and date="
									+ newSubscriptionRequest.getSubscriptionInfo().getNextBillingDate())
							.get(0),
					expectedRecords);

			// scheduler call
			SASValidationHelper.validate_schedular_api_response(
					sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, newActionTable)));

			// SASDBHelper.showAllActivityTableData("FOURTH",
			// String.valueOf(subscriptionId));

			expectedRecords.put("status", "IN_PROGRESS");

			SASValidationHelper.validateTableRecord(
					DBUtils.getRecord(newActionTable,
							"subscription_id = " + subscriptionId + " and product_id=" + productId + " and partner_id="
									+ partnerId + " and date="
									+ newSubscriptionRequest.getSubscriptionInfo().getNextBillingDate())
							.get(0),
					expectedRecords);

			message = RabbitMQConnection.getRabbitTemplate().receive(
					productId + "_" + partnerId + "_" + newActionTable.toUpperCase() + "_REQUEST_BACKEND", 25000);
			SASValidationHelper.validateQueueMessage(
					ObjectMapperUtils.readValueFromString(new String(message.getBody()), QueueResponse.class),
					productId, partnerId, subscriptionId, countryCode, newActionTable.toUpperCase());
		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}

}
