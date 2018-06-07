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
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.UserSubscriptionRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;

/**
 * 
 * @author rahul.sahu
 *
 */

public class SASTest {
	private static Logger logger = Log4J.getLogger("SASTest ETE");
	private SASHelper sasHelper;
	int productId;
	int partnerId;
	PublishConfigRequest publishConfigRequest = null;
	private String countryCode = "IN";

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		sasHelper = new SASHelper();
		productId = SASUtils.productId;// RandomUtils.nextInt(2000, 3000);
		partnerId = productId;
	}

	@DataProvider(name = "activationPostiveTestType")
	public Object[][] activationPostiveTestType() {
		return new Object[][] {
				/*
				 * FORMAT { "eventActionType", "activityType", "currentSubscriptionState",
				 * "transactionState", "actionTable", "beforeSchedularStatus",
				 * "afterSchedularStatus", "afteNewEventStatus", "queueName",
				 * "newEventActionType", "newActivityType", "newCurrentSubscriptionState",
				 * "newTransactionState", "newActionTable", "newBeforeSchedularStatus",
				 * "newAfterSchedularStatus", "newQueueName" },
				 */
				/* PASS */{ "CHARGING", "ACTIVATION", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS",
						"SUCCESS", "RENEWAL", "CHARGING", "RENEWAL", "ACTIVATED", "SUCCESS", "renewal", "OPEN",
						"IN_PROGRESS", "RENEWAL" },

				/* PASS */{ "CHARGING", "ACTIVATION", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS", "ERROR",
						"RENEWAL", "CHARGING", "RENEWAL", "ACTIVATED", "ERROR", "renewal_retry", "OPEN", "IN_PROGRESS",
						"RENEWAL_RETRY" },

				/* PASS */{ "CHARGING", "ACTIVATION", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS",
						"FAILURE", "RENEWAL", "CHARGING", "RENEWAL", "ACTIVATED", "FAILURE", "renewal_retry", "OPEN",
						"IN_PROGRESS", "RENEWAL_RETRY" },

				/* PASS */{ "CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE", "activation", "OPEN", "IN_PROGRESS",
						"SUCCESS", "ACTIVATION", "CHARGING", "ACTIVATION", "ACTIVATED", "SUCCESS", "renewal", "OPEN",
						"IN_PROGRESS", "RENEWAL" },

				/* PASSED */{ "CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE", "activation", "OPEN", "IN_PROGRESS",
						"FAILURE", "ACTIVATION", "CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE", "activation", "OPEN",
						"IN_PROGRESS", "ACTIVATION" },

				/* PASSED */{ "CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE", "activation", "OPEN", "IN_PROGRESS",
						"ERROR", "ACTIVATION", "CHARGING", "ACTIVATION", "ACT_INIT", "ERROR", "activation", "OPEN",
						"IN_PROGRESS", "ACTIVATION" },

				/* PASSED */{ "CHARGING", "ACTIVATION", "PARKING", "LOW_BALANCE", "winback", "OPEN", "IN_PROGRESS",
						"ERROR", "WINBACK", "CHARGING", "WINBACK", "PARKING", "ERROR", "winback", "OPEN", "IN_PROGRESS",
						"WINBACK" },

				{ "CHARGING", "ACTIVATION", "PARKING", "LOW_BALANCE", "winback", "OPEN", "IN_PROGRESS", "SUCCESS",
						"WINBACK", "CHARGING", "WINBACK", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS",
						"RENEWAL" },
				// //

				// // /* fail no pro */{ "CHARGING", "ACTIVATION", "PARKING", "LOW_BALANCE",
				// // "winback", "PARKING", "FAILURE",
				// // "FAILURE", "winback" },
				// //
				/* PASSED */{ "CHARGING", "ACTIVATION", "PARKING", "LOW_BALANCE", "winback", "OPEN", "IN_PROGRESS",
						"LOW_BALANCE", "WINBACK", "CHARGING", "WINBACK", "PARKING", "LOW_BALANCE", "winback", "OPEN",
						"IN_PROGRESS", "WINBACK" },
				//
				// /* fail no processor */{ "CHARGING", "ACTIVATION", "PARKING", "LOW_BALANCE",
				// "winback", "PARKING",
				// "IN_PROGRESS", "IN_PROGRESS", "winback" },
				//
				// /* fail */{ "CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE", "activation",
				// "ACT_INIT",
				// "NOTIFICATION_WAIT", "NOTIFICATION_WAIT", "activation" },
				// /* fail */{ "CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE", "activation",
				// "ACT_INIT", "IN_PROGRESS",
				// "IN_PROGRESS", "winback" },
				// /* BUG */{ "CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE", "activation",
				// "ACT_INIT", "LOW_BALANCE",
				// "LOW_BALANCE", "winback" },
				//
				// /* BUG */{ "CHARGING", "ACTIVATION", "ACT_INIT", "ERROR", "activation",
				// "ACT_INIT", "LOW_BALANCE",
				// "LOW_BALANCE", "winback" },
				//
				// /* FAIL */{ "CHARGING", "ACTIVATION", "ACT_INIT", "IN_PROGRESS", "winback",
				// "ACT_INIT", "LOW_BALANCE",
				// "LOW_BALANCE", "winback" },
				// /* FAIL */{ "CHARGING", "ACTIVATION", "ACT_INIT", "LOW_BALANCE", "winback",
				// "ACT_INIT", "LOW_BALANCE",
				// "LOW_BALANCE", "winback" },
				//
				// /* FAIL */{ "CHARGING", "ACTIVATION", "ACT_INIT", "NOTIFICATION_WAIT",
				// "winback", "ACT_INIT",
				// "LOW_BALANCE", "LOW_BALANCE", "winback" },
				// /* FAIL processor is not configured */{ "CHARGING", "ACTIVATION",
				// "ACTIVATED", "SUCCESS", "renewal",
				// "ACTIVATED", "IN_PROGRESS", "IN_PROGRESS", "renewal_retry" },
				// /* FAIL */ { "CHARGING", "ACTIVATION", "ACTIVATED", "SUCCESS", "renewal",
				// "ACTIVATED",
				// "NOTIFICATION_WAIT", "IN_PROGRESS", "renewal_retry" },
				//
				/* PASSED */{ "DEACTIVATE_CONSENT", "DEACTIVATION", "DCT_INIT", "FAILURE", "deactivation", "OPEN",
						"IN_PROGRESS", "FAILURE", "DEACTIVATION", "DEACTIVATE_CONSENT", "DEACTIVATION", "DCT_INIT",
						"FAILURE", "deactivation", "OPEN", "IN_PROGRESS", "DEACTIVATION" },
				/* PASSED */{ "DEACTIVATE_CONSENT", "DEACTIVATION", "DCT_INIT", "FAILURE", "deactivation", "OPEN",
						"IN_PROGRESS", "ERROR", "DEACTIVATION", "DEACTIVATE_CONSENT", "DEACTIVATION", "DCT_INIT",
						"ERROR", "deactivation", "OPEN", "IN_PROGRESS", "DEACTIVATION" },
				// /* no entry */{ "DEACTIVATE_CONSENT", "DEACTIVATION", "DCT_INIT", "FAILURE",
				// "deactivation", "DCT_INIT",
				// "CONFIRMED", "CONFIRMED", "deactivation" },
				// /* no entry */{ "DEACTIVATE_CONSENT", "DEACTIVATION", "DCT_INIT", "FAILURE",
				// "deactivation", "DCT_INIT",
				// "IN_PROGRESS", "IN_PROGRESS", "deactivation" },
				// /* no entry */{ "DEACTIVATE_CONSENT", "DEACTIVATION", "DCT_INIT", "FAILURE",
				// "deactivation", "DCT_INIT",
				// "NOTIFICATION_WAIT", "NOTIFICATION_WAIT", "deactivation" },
				// /* no entry */{ "DEACTIVATE_CONSENT", "DEACTIVATION", "DCT_INIT", "SUCCESS",
				// "deactivation", "DCT_INIT",
				// "FAILURE", "FAILURE", "deactivation" }

				// //RENEWAL E2E
				// /* IN_Progress */{ "CHARGING", "RENEWAL", "ACTIVATED", "SUCCESS", "renewal",
				// "ACTIVATED", "SUCCESS",
				// "SUCCESS", "renewal" },
				// /* IN_Progress */{ "CHARGING", "RENEWAL", "ACTIVATED", "SUCCESS", "renewal",
				// "ACTIVATED", "FAILURE",
				// "FAILURE", "renewal_retry" },
				{ "CHARGING", "RENEWAL", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS", "SUCCESS", "RENEWAL",
						"CHARGING", "RENEWAL", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS", "RENEWAL" },

				/* Passed */{ "CHARGING", "WINBACK", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS",
						"SUCCESS", "RENEWAL", "CHARGING", "RENEWAL", "ACTIVATED", "SUCCESS", "renewal", "OPEN",
						"IN_PROGRESS", "RENEWAL" },

				/* Passed */ { "CHARGING", "WINBACK", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS",
						"FAILURE", "RENEWAL", "CHARGING", "RENEWAL", "ACTIVATED", "FAILURE", "renewal_retry", "OPEN",
						"IN_PROGRESS", "RENEWAL_RETRY" },

				/* Passed */{ "CHARGING", "WINBACK", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS", "ERROR",
						"RENEWAL", "CHARGING", "RENEWAL", "ACTIVATED", "ERROR", "renewal_retry", "OPEN", "IN_PROGRESS",
						"RENEWAL_RETRY" },

				/* Passed */{ "CHARGING", "WINBACK", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS", "ERROR",
						"RENEWAL", "CHARGING", "RENEWAL", "SUSPEND", "ERROR", "renewal_retry", "OPEN", "IN_PROGRESS",
						"RENEWAL_RETRY" },

				/* Passed */ { "CHARGING", "WINBACK", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS",
						"FAILURE", "RENEWAL", "CHARGING", "RENEWAL", "SUSPEND", "FAILURE", "renewal_retry", "OPEN",
						"IN_PROGRESS", "RENEWAL_RETRY" },

				/* Passed */{ "CHARGING", "WINBACK", "PARKING", "LOW_BALANCE", "winback", "OPEN", "IN_PROGRESS",
						"SUCCESS", "WINBACK", "CHARGING", "WINBACK", "ACTIVATED", "SUCCESS", "renewal", "OPEN",
						"IN_PROGRESS", "RENEWAL" },

				/* Passed */{ "CHARGING", "WINBACK", "PARKING", "LOW_BALANCE", "winback", "OPEN", "IN_PROGRESS",
						"ERROR", "WINBACK", "CHARGING", "WINBACK", "PARKING", "ERROR", "winback", "OPEN", "IN_PROGRESS",
						"WINBACK" },

				/* Passed */{ "CHARGING", "WINBACK", "PARKING", "LOW_BALANCE", "winback", "OPEN", "IN_PROGRESS",
						"LOW_BALANCE", "WINBACK", "CHARGING", "WINBACK", "PARKING", "LOW_BALANCE", "winback", "OPEN",
						"IN_PROGRESS", "WINBACK" },


				

		};
	}

	@Test(/** dependsOnMethods = "createConfigData", **/
			dataProvider = "activationPostiveTestType", groups = { "positive" })
	public void activationPositiveRetryTests(String eventActionType, String activityType,
			String currentSubscriptionState, String transactionState, String actionTable, String beforeSchedularStatus,
			String afterSchedularStatus, String afteNewEventStatus, String queueName, String newEventActionType,
			String newActivityType, String newCurrentSubscriptionState, String newTransactionState,
			String newActionTable, String newBeforeSchedularStatus, String newAfterSchedularStatus, String newQueueName)
			 {

		Integer subscriptionId = RandomUtils.nextInt(58000, 59000);
		String testMessage = subscriptionId + " " + activityType + " " + currentSubscriptionState + " "
				+ transactionState + " " + actionTable + " " + newCurrentSubscriptionState + " " + newTransactionState
				+ " " + newActionTable;
		logger.info("***************Starting positive activation retry test  [ " + testMessage + " ]");

		try {
			
			logger.info("=========>First time user subscription event getting trigger");
			UserSubscriptionRequest uSRequest = SASUtils.generateUserSubscriptionRequest(productId, partnerId,
					activityType, "", currentSubscriptionState, transactionState, eventActionType, subscriptionId);
			SASValidationHelper.validate_sas_api_response(sasHelper.userSubscription(uSRequest));

			// String expectedActivityType = SASDBHelper.showAllActivityTableData("FIRST ",
			// String.valueOf(subscriptionId));
			logger.info("=========>First event: Verify DB after event trigger");

			Map<String, String> expectedRecords = new HashMap<String, String>();
			expectedRecords.put("status", beforeSchedularStatus);
			expectedRecords.put("product_id", String.valueOf(productId));
			expectedRecords.put("partner_id", String.valueOf(partnerId));
			expectedRecords.put("subscription_id", String.valueOf(subscriptionId));
			expectedRecords.put("country_code", countryCode);
			expectedRecords.put("date", String.valueOf(uSRequest.getSubscriptionInfo().getNextBillingDate()));
			SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable,
					"subscription_id = " + subscriptionId + " and product_id = " + productId + " and partner_id="
							+ partnerId + " and date=" + uSRequest.getSubscriptionInfo().getNextBillingDate())
					.get(0), expectedRecords);

			logger.info("=========>First Event: scheduale call ");
			SASValidationHelper.validate_schedular_api_response(
					sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, actionTable)));

			logger.info("=========>First Event: Vefiry DB After Schedular Call ");
			expectedRecords.put("status", afterSchedularStatus);
			SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId
					+ " and product_id=" + productId + " and partner_id=" + partnerId).get(0), expectedRecords);

			logger.info("=========>First Event: Queue verification Name: " + productId + "_" + partnerId + "_"
					+ queueName + "_REQUEST_BACKEND");

			Message message = RabbitMQConnection.getRabbitTemplate()
					.receive(productId + "_" + partnerId + "_" + queueName.toUpperCase() + "_REQUEST_BACKEND", 25000);
			SASValidationHelper.validateQueueMessage(
					ObjectMapperUtils.readValueFromString(new String(message.getBody()), QueueResponse.class),
					productId, partnerId, subscriptionId, countryCode, actionTable.toUpperCase());

			logger.info("=========>Second time user subscription event getting trigger");
			UserSubscriptionRequest newSubscriptionRequest = SASUtils.generateUserSubscriptionRequest(productId,
					partnerId, newActivityType.toUpperCase(), currentSubscriptionState, newCurrentSubscriptionState,
					newTransactionState, newEventActionType, subscriptionId);
			newSubscriptionRequest.getSubscriptionInfo()
					.setNextBillingDate(newSubscriptionRequest.getSubscriptionInfo().getNextBillingDate() + 100);
			newSubscriptionRequest.getActivityEvent()
					.setNextBillingDate(newSubscriptionRequest.getActivityEvent().getNextBillingDate() + 100);

			SASValidationHelper.validate_sas_api_response(sasHelper.userSubscription(newSubscriptionRequest));

			// SASDBHelper.showAllActivityTableData("THIRD",
			// String.valueOf(subscriptionId));

			logger.info("=========>Second event: previous Event's DB verification");
			expectedRecords.put("status", afteNewEventStatus);
			SASValidationHelper.validateTableRecord(DBUtils
					.getRecord(actionTable,
							"subscription_id = " + subscriptionId + " and product_id=" + productId + " and partner_id="
									+ partnerId + " and date=" + uSRequest.getSubscriptionInfo().getNextBillingDate())
					.get(0), expectedRecords);

			logger.info("=========>Second event: DB verification before schedular call");
			expectedRecords.put("status", newBeforeSchedularStatus);
			expectedRecords.put("date",
					String.valueOf(newSubscriptionRequest.getSubscriptionInfo().getNextBillingDate()));
			SASValidationHelper.validateTableRecord(
					DBUtils.getRecord(newActionTable,
							"subscription_id = " + subscriptionId + " and product_id=" + productId + " and partner_id="
									+ partnerId + " and date="
									+ newSubscriptionRequest.getSubscriptionInfo().getNextBillingDate())
							.get(0),
					expectedRecords);

			logger.info("=========>Second event: Schedular call");
			SASValidationHelper.validate_schedular_api_response(
					sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, newActionTable)));

			// SASDBHelper.showAllActivityTableData("FOURTH",
			// String.valueOf(subscriptionId));

			logger.info("=========>Second event: DB verification after schedular call");
			expectedRecords.put("status", newAfterSchedularStatus);
			SASValidationHelper.validateTableRecord(
					DBUtils.getRecord(newActionTable,
							"subscription_id = " + subscriptionId + " and product_id=" + productId + " and partner_id="
									+ partnerId + " and date="
									+ newSubscriptionRequest.getSubscriptionInfo().getNextBillingDate())
							.get(0),
					expectedRecords);

			logger.info("=========>Second event: Queue verification " + productId + "_" + partnerId + "_"
					+ newQueueName.toUpperCase() + "_REQUEST_BACKEND");
			message = RabbitMQConnection.getRabbitTemplate().receive(
					productId + "_" + partnerId + "_" + newQueueName.toUpperCase() + "_REQUEST_BACKEND", 25000);
			SASValidationHelper.validateQueueMessage(
					ObjectMapperUtils.readValueFromString(new String(message.getBody()), QueueResponse.class),
					productId, partnerId, subscriptionId, countryCode, newActionTable.toUpperCase());		} catch (Exception e) {
			logger.info("=========>ERROR due to exception");

			Assert.fail(e.getMessage());
		}
	}

}
