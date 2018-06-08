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

public class SASSystemChurnTests {
	private static Logger logger = Log4J.getLogger("SystemChurnTests");
	private SASHelper sasHelper;
	int productId;
	int partnerId;
	PublishConfigRequest publishConfigRequest = null;
	private String countryCode = "IN";

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		sasHelper = new SASHelper();
		productId = SASUtils.productId;
		partnerId = productId;
	}

	@DataProvider(name = "systemChurnPostiveDataProvider")
	public Object[][] systemChurnPostiveDataProvider() {
		return new Object[][] {
				// covered in sasTest { "SYSTEM_CHURN", "ACT_INIT", "DCT_INIT",
				// "FAILURE","DEACTIVATE_CONSENT", 101, "SYSTEM_CHURN","OPEN" },
				// covered in sasTest { "SYSTEM_CHURN", "ACT_INIT", "DCT_INIT",
				// "ERROR","DEACTIVATE_CONSENT", 102, "SYSTEM_CHURN","OPEN" },
				// covered in sasTest { "SYSTEM_CHURN", "SUSPEND", "DCT_INIT", "IN_PROGRESS",
				// "DEACTIVATE_CONSENT", 109, "SYSTEM_CHURN","OPEN" },

		};
	}

	@Test(dataProvider = "systemChurnPostiveDataProvider", groups = { "positive" })
	public void systemChurnPostiveTests(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String actionType, Integer subscriptionId,
			String actionTable, String status) throws Exception {

		subscriptionId = RandomUtils.nextInt(17000, 18000);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting System Churn Positive Retry test  [ " + testMessage + " ]");

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

			SASValidationHelper.validateTableRecord(DBUtils
					.getRecord(actionTable.substring(actionTable.indexOf("_") + 1).toLowerCase(), "subscription_id = "
							+ subscriptionId + " and product_id = " + productId + " and partner_id=" + partnerId)
					.get(0), expectedRecords);

			SASValidationHelper.validate_schedular_api_response(
					sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, actionTable)));

			expectedRecords.put("status", "IN_PROGRESS");

			SASValidationHelper.validateTableRecord(DBUtils
					.getRecord(actionTable.substring(actionTable.indexOf("_") + 1).toLowerCase(), "subscription_id = "
							+ subscriptionId + " and product_id=" + productId + " and partner_id=" + partnerId)
					.get(0), expectedRecords);

			Message message = RabbitMQConnection.getRabbitTemplate()
					.receive(productId + "_" + partnerId + "_" + actionTable + "_REQUEST_BACKEND", 25000);
			SASValidationHelper.validateQueueMessage(
					ObjectMapperUtils.readValueFromString(new String(message.getBody()), QueueResponse.class),
					productId, partnerId, subscriptionId, countryCode, actionTable);

		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}

	@DataProvider(name = "systemChurnNegativeDataProvider")
	public Object[][] systemChurnNegativeDataProvider() {
		return new Object[][] {

				{ "SYSTEM_CHURN", "PARKING", "DEACTIVATED", "IN_PROGRESS", "DEACTIVATE_CONSENT", 117, "SYSTEM_CHURN",
						"OPEN" },
				{ "SYSTEM_CHURN", "PARKING", "DEACTIVATED", "FAILURE", "DEACTIVATE_CONSENT", 118, "SYSTEM_CHURN",
						"OPEN" },
				{ "SYSTEM_CHURN", "PARKING", "DEACTIVATED", "ERROR", "DEACTIVATE_CONSENT", 119, "SYSTEM_CHURN",
						"OPEN" },
				{ "SYSTEM_CHURN", "PARKING", "DCT_INIT", "SUCCESS", "DEACTIVATE_CONSENT", 119, "SYSTEM_CHURN", "OPEN" },

				{ "SYSTEM_CHURN", "ACTIVATED", "DEACTIVATED", "SUCCESS", "DEACTIVATE_CONSENT", 112, "SYSTEM_CHURN",
						"OPEN" } };
	}

	@Test(dataProvider = "systemChurnNegativeDataProvider", groups = { "negative" })
	public void systemChurnNegativeTest(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String actionType, Integer subscriptionId,
			String actionTable, String status) throws Exception {
		subscriptionId = RandomUtils.nextInt(33000, 34000);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting System Churn Negative Retry test  [ " + testMessage + " ]");

		SASValidationHelper.negativeFlow(productId, partnerId, activityType, currentSubscriptionState, transactionState,
				actionType, subscriptionId);
	}

}
