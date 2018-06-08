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

public class SASWinbackTests {
	private static Logger logger = Log4J.getLogger("WinbackTests");
	private SASHelper sasHelper;
	int productId;
	int partnerId;
	private String countryCode = "IN";

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		sasHelper = new SASHelper();
		productId = SASUtils.productId;
		partnerId = productId;
	}

	@DataProvider(name = "winbackPositiveDataProvider")
	public Object[][] winbackPositiveDataProvider() {
		return new Object[][] {
				// covered in sasTest { "WINBACK", "PARKING", "ACTIVATED", "SUCCESS",
				// "CHARGING", 101, "renewal", "OPEN" },
				// covered in sasTest { "WINBACK", "PARKING", "PARKING", "LOW_BALANCE",
				// "CHARGING", 102, "winback", "OPEN" },
				// covered in sasTest { "WINBACK", "PARKING", "PARKING", "ERROR", "CHARGING",
				// 103, "winback", "OPEN" },

				/* will pass once env is updated with latest build */
				// { "WINBACK", "PARKING", "PARKING", "IN_PROGRESS","CHARGING", 103, "winback",
				// "OPEN" },

		};
	}

	@Test(dataProvider = "winbackPositiveDataProvider", groups = { "positive" })
	public void winbackPositiveTests(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String actionType, Integer subscriptionId,
			String actionTable, String status) throws Exception {

		subscriptionId = RandomUtils.nextInt(6000, 7000);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting Winback Positive Retry test  [ " + testMessage + " ]");

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
					productId, partnerId, subscriptionId, countryCode, actionTable);
		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}

	@DataProvider(name = "winbackNegativeDataProvider")
	public Object[][] winbackNegativeDataProvider() {
		return new Object[][] {
				{ "WINBACK", "PARKING", "ACTIVATED", "LOW_BALANCE", "CHARGING", 104, "renewal", "OPEN" },
				{ "WINBACK", "PARKING", "ACTIVATED", "ERROR", "CHARGING", 105, "renewal", "OPEN" },
				{ "WINBACK", "PARKING", "ACTIVATED", "IN_PROGRESS", "CHARGING", 105, "renewal", "OPEN" },
				{ "WINBACK", "PARKING", "ACTIVATED", "FAILURE", "CHARGING", 105, "renewal", "OPEN" },
				{ "WINBACK", "PARKING", "PARKING", "SUCCESS", "CHARGING", 106, "winback", "OPEN" },
				{ "WINBACK", "PARKING", "PARKING", "FAILURE", "CHARGING", 103, "winback", "OPEN" },

		};
	}

	@Test(dataProvider = "winbackNegativeDataProvider", groups = { "negative" })
	public void winbackNegativeTests(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String actionType, Integer subscriptionId,
			String actionTable, String status) throws Exception {
		subscriptionId = RandomUtils.nextInt(63000, 64000);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting Winback Negative Retry test  [ " + testMessage + " ]");

		SASValidationHelper.negativeFlow(productId, partnerId, activityType, currentSubscriptionState, transactionState,
				actionType, subscriptionId);

	}

}
