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
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASDBHelper;
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
	private static Logger logger = Log4J.getLogger("RenewalTests");
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

	@DataProvider(name = "renewalPostiveDataProvider")
	public Object[][] renewalPostiveDataProvider() {
		return new Object[][] {
			// covered in sasTest { "RENEWAL", "ACTIVATED", "SUCCESS", "CHARGING",
			// "renewal", "OPEN" },
			// covered in sasTest { "RENEWAL", "ACTIVATED", "FAILURE", "CHARGING",
			// "renewal_retry", "OPEN" },
			// covered in sasTest { "RENEWAL", "ACTIVATED", "ERROR", "CHARGING",
			// "renewal_retry", "OPEN" },
			// covered in sasTest { "RENEWAL", "SUSPEND", "FAILURE", "CHARGING",
			// "renewal_retry", "OPEN" },
			// covered in sasTest { "RENEWAL", "SUSPEND", "ERROR", "CHARGING",
			// "renewal_retry", "OPEN" },

			// Fail{ "RENEWAL", "SUSPEND", "NOTIFICATION_WAIT", "CHARGING", "winback",
			// "OPEN" } ,
			// Fail{ "RENEWAL", "SUSPEND", "LOW_BALANCE", "CHARGING", "winback", "OPEN" }
			// fail{ "RENEWAL", "ACTIVATED", "IN_PROGRESS", "CHARGING", "winback", "OPEN" },
			// fail{ "RENEWAL", "ACTIVATED", "NOTIFICATION_WAIT", "CHARGING", "winback",
			// "OPEN" } ,

		};

	}

	@Test(dataProvider = "renewalPostiveDataProvider", groups = { "positive" })
	public void renewalPositiveTests(String activityType, String currentSubscriptionState, String transactionState,
			String actionType, String actionTable, String status) throws Exception {

		int subscriptionId = RandomUtils.nextInt(31000, 32000);
		String testMessage = subscriptionId + " " + activityType + " " + currentSubscriptionState + " "
				+ transactionState + " " + actionType;
		logger.info("==================>Starting Renewal Positive Tests  [ " + testMessage + " ]");

		try {

			SASValidationHelper.validate_sas_api_response(
					sasHelper.userSubscription(SASUtils.generateUserSubscriptionRequest(productId, partnerId,
							activityType, "", currentSubscriptionState, transactionState, actionType, subscriptionId)));

			Map<String, String> expectedRecords = new HashMap<String, String>();
			expectedRecords.put("status", "OPEN");
			expectedRecords.put("product_id", String.valueOf(productId));
			expectedRecords.put("partner_id", String.valueOf(partnerId));
			expectedRecords.put("subscription_id", String.valueOf(subscriptionId));
			expectedRecords.put("country_code", countryCode);

			SASDBHelper.showAllActivityTableData(testMessage, String.valueOf(subscriptionId));
			SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId
					+ " and product_id = " + productId + " and partner_id=" + partnerId).get(0), expectedRecords);

			SASValidationHelper.validate_schedular_api_response(
					sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, actionTable)));

			SASDBHelper.showAllActivityTableData(testMessage, String.valueOf(subscriptionId));

			expectedRecords.put("status", "IN_PROGRESS");

			SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId
					+ " and product_id=" + productId + " and partner_id=" + partnerId).get(0), expectedRecords);

			System.out.println("QUEUE NAME : " + productId + "_" + partnerId + "_" + actionTable.toUpperCase()
			+ "_REQUEST_BACKEND");
			Message message = RabbitMQConnection.getRabbitTemplate()
					.receive(productId + "_" + partnerId + "_" + actionTable.toUpperCase() + "_REQUEST_BACKEND", 25000);
			SASValidationHelper.validateQueueMessage(
					ObjectMapperUtils.readValueFromString(new String(message.getBody()), QueueResponse.class),
					productId, partnerId, subscriptionId, countryCode, actionTable.toUpperCase());
		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}

	@DataProvider(name = "renewalNegativeDataProvider")
	public Object[][] renewalNegativeDataProvider() {
		return new Object[][] { { "RENEWAL", "SUSPEND", "IN_PROGRESS", "CHARGING", "winback", "OPEN" },
			{ "RENEWAL", "SUSPEND", "SUCCESS", "CHARGING", "activation", "OPEN" },

		};
	}

	@Test(dataProvider = "renewalNegativeTestType", groups = { "negative" })
	public void renewalNegativeTest(String activityType, String currentSubscriptionState, String transactionState,
			String actionType, String actionTable, String status) throws Exception {
		int subscriptionId = RandomUtils.nextInt(53000, 54000);
		String testMessage = subscriptionId + " " + activityType + " " + currentSubscriptionState + " "
				+ transactionState + " " + actionType;
		logger.info("==================>Starting Renewal Negative Test  [ " + testMessage + " ]");

		SASValidationHelper.negativeFlow(productId, partnerId, activityType, currentSubscriptionState, transactionState,
				actionType, subscriptionId);

	}

}
