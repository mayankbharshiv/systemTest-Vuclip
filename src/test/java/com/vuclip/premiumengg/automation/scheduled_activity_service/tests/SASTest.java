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
			
			 { "CHARGING", "FREETRIAL_RENEWAL", "ACTIVATED", "SUCCESS", "free_trail", "OPEN", "IN_PROGRESS","LOW_BALANCE", "FREETRIAL_RENEWAL", "CHARGING", "FREETRIAL_RENEWAL", "SUSPEND", "LOW_BALANCE", "renewal_retry", "OPEN","IN_PROGRESS", "RENEWAL_RETRY" },

			 { "CHARGING", "FREETRIAL_RENEWAL", "ACTIVATED", "SUCCESS", "free_trail", "OPEN", "IN_PROGRESS","ERROR", "FREETRIAL_RENEWAL", "CHARGING", "FREETRIAL_RENEWAL", "SUSPEND", "ERROR", "renewal_retry", "OPEN","IN_PROGRESS", "RENEWAL_RETRY" },

			 { "CHARGING", "FREETRIAL_RENEWAL", "ACTIVATED", "SUCCESS", "free_trail", "OPEN", "IN_PROGRESS","FAILURE", "FREETRIAL_RENEWAL", "CHARGING", "FREETRIAL_RENEWAL", "SUSPEND", "FAILURE", "renewal_retry", "OPEN","IN_PROGRESS", "RENEWAL_RETRY" },

			 { "CHARGING", "FREETRIAL_RENEWAL", "ACTIVATED", "SUCCESS", "free_trail", "OPEN", "IN_PROGRESS","ERROR", "FREETRIAL_RENEWAL", "CHARGING", "FREETRIAL_RENEWAL", "ACTIVATED", "ERROR", "renewal_retry", "OPEN","IN_PROGRESS", "RENEWAL_RETRY" },

			 { "CHARGING", "FREETRIAL_RENEWAL", "SUSPEND", "LOW_BALANCE", "renewal_retry", "OPEN", "IN_PROGRESS","FAILURE", "RENEWAL_RETRY", "CHARGING", "RENEWAL_RETRY", "ACTIVATED", "FAILURE", "renewal_retry", "OPEN","IN_PROGRESS", "RENEWAL_RETRY" },

			{ "CHARGING", "FREETRIAL_RENEWAL", "SUSPEND", "ERROR", "renewal_retry", "OPEN", "IN_PROGRESS","SUCCESS", "RENEWAL_RETRY", "CHARGING", "RENEWAL_RETRY", "ACTIVATED", "SUCCESS", "renewal", "OPEN","IN_PROGRESS", "RENEWAL" },

		};
	}


		@Test(/** dependsOnMethods = "createConfigData", **/dataProvider = "activationPostiveTestType",groups = {"positive"})
	public void activationPositiveRetryTests(String eventActionType, String activityType,
			String currentSubscriptionState, String transactionState, String actionTable, String beforeSchedularStatus,
			String afterSchedularStatus, String afteNewEventStatus, String queueName, String newEventActionType,
			String newActivityType, String newCurrentSubscriptionState, String newTransactionState,
			String newActionTable, String newBeforeSchedularStatus, String newAfterSchedularStatus, String newQueueName)
			throws Exception {

		UserSubscriptionRequest uSRequest =null;
		UserSubscriptionRequest newSubscriptionRequest=null;
		Integer subscriptionId = RandomUtils.nextInt(900, 1000);
		String testMessage = subscriptionId + " " + activityType + " " + currentSubscriptionState + " "
				+ transactionState + " " + actionTable + " " + newCurrentSubscriptionState + " " + newTransactionState
				+ " " + newActionTable;
		logger.info("***************Starting positive activation retry test  [ " + testMessage + " ]");

		try {

			logger.info("=========>First time user subscription event getting trigger");
		if(activityType=="FREETRIAL_RENEWAL"){
			 uSRequest = SASUtils.generateUserSubscriptionRequest(productId,
					partnerId, activityType, "", currentSubscriptionState, transactionState, eventActionType,
					subscriptionId);
			uSRequest.getUserInfo().setFreeTrialUser(true);
			SASValidationHelper.validate_sas_api_response(sasHelper.userSubscription(uSRequest));

		}
		else
		{
			 uSRequest = SASUtils.generateUserSubscriptionRequest(productId, partnerId,
					activityType, "", currentSubscriptionState, transactionState, eventActionType, subscriptionId);
			SASValidationHelper.validate_sas_api_response(sasHelper.userSubscription(uSRequest));
		}
	

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
			if (actionTable == "churn") {
				SASValidationHelper.validate_schedular_api_response(
						sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, activityType)));
			}
				
				else if (actionTable == "free_trail") {
					SASValidationHelper.validate_schedular_api_response(
							sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, activityType)));
			} 
				else
				{
				SASValidationHelper.validate_schedular_api_response(
						sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, actionTable)));
			}

			logger.info("=========>First Event: Vefiry DB After Schedular Call ");
			expectedRecords.put("status", afterSchedularStatus);
			SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId
					+ " and product_id=" + productId + " and partner_id=" + partnerId).get(0), expectedRecords);

			logger.info("=========>First Event: Queue verification Name: " + productId + "_" + partnerId + "_"
					+ queueName + "_REQUEST_BACKEND");
			Message message = RabbitMQConnection.getRabbitTemplate()
					.receive(productId + "_" + partnerId + "_" + queueName.toUpperCase() + "_REQUEST_BACKEND", 30000);
			
			if (actionTable == "churn") {
				SASValidationHelper.validateQueueMessage(
						ObjectMapperUtils.readValueFromString(new String(message.getBody()), QueueResponse.class),
						productId, partnerId, subscriptionId, countryCode, activityType);
			} 
			else if (actionTable == "free_trail") {
				SASValidationHelper.validateQueueMessage(
						ObjectMapperUtils.readValueFromString(new String(message.getBody()), QueueResponse.class),
						productId, partnerId, subscriptionId, countryCode, activityType);
			} 
			
			else {
				SASValidationHelper.validateQueueMessage(
						ObjectMapperUtils.readValueFromString(new String(message.getBody()), QueueResponse.class),
						productId, partnerId, subscriptionId, countryCode, actionTable.toUpperCase());
			}

			logger.info("=========>Second time user subscription event getting trigger");
			
			
			if(activityType=="FREETRIAL_RENEWAL"){
				newSubscriptionRequest = SASUtils.generateUserSubscriptionRequest(productId,
						partnerId, activityType, "", currentSubscriptionState, transactionState, eventActionType,
						subscriptionId);
				uSRequest.getUserInfo().setFreeTrialUser(true);
				SASValidationHelper.validate_sas_api_response(sasHelper.userSubscription(uSRequest));

			}
			else
			{
				newSubscriptionRequest = SASUtils.generateUserSubscriptionRequest(productId, partnerId,
						activityType, "", currentSubscriptionState, transactionState, eventActionType, subscriptionId);
				SASValidationHelper.validate_sas_api_response(sasHelper.userSubscription(uSRequest));
			}
			
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
			if (newActionTable == "churn") {
				SASValidationHelper.validate_schedular_api_response(
						sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, newActivityType)));
			} 
			
			else if (newActionTable == "free_trail") {
				SASValidationHelper.validate_schedular_api_response(
						sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, newActivityType)));
			}
			else {
				SASValidationHelper.validate_schedular_api_response(
						sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, newActionTable)));
			}

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
			if (newActionTable == "churn") {
				SASValidationHelper.validateQueueMessage(
						ObjectMapperUtils.readValueFromString(new String(message.getBody()), QueueResponse.class),
						productId, partnerId, subscriptionId, countryCode, newActivityType);
			}
			else if (newActionTable == "free_trail") {
				SASValidationHelper.validateQueueMessage(
						ObjectMapperUtils.readValueFromString(new String(message.getBody()), QueueResponse.class),
						productId, partnerId, subscriptionId, countryCode, newActivityType);
			}
			else {
				SASValidationHelper.validateQueueMessage(
						ObjectMapperUtils.readValueFromString(new String(message.getBody()), QueueResponse.class),
						productId, partnerId, subscriptionId, countryCode, newActionTable.toUpperCase());
			}
		} catch (Exception e) {
			logger.info("=========>ERROR due to exception");

			Assert.fail(e.getMessage());
		}
	}

}
