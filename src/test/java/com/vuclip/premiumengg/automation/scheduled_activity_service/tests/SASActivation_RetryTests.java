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
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;

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
	private String countryCode = "IN";

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		sasHelper = new SASHelper();
		productId = SASUtils.productId;//RandomUtils.nextInt(6000, 7000);
		partnerId = productId;
	}

	@DataProvider(name = "activationPostiveTestType")
	public Object[][] activationPostiveTestType() {
		return new Object[][] {

				{ "ACTIVATION_RETRY", "ACTIVATED", "SUCCESS", "CHARGING", "renewal" },
				/**
				 * failed{ "ACTIVATION_RETRY", "ACT_INIT", "FAILURE", "CHARGING", "activation"
				 * },
				 */
				/**
				 * failed { "ACTIVATION_RETRY", "ACT_INIT", "ERROR", "CHARGING", "activation" },
				 */
				/**
				 * failed { "ACTIVATION_RETRY", "ACT_INIT", "IN_PROGRESS", "CHARGING",
				 * "activation" },
				 */
				//{ "ACTIVATION_RETRY", "PARKING", "LOW_BALANCE", "CHARGING", "winback" },

		};

	}

	@Test(/**dependsOnMethods = "createConfigData",**/ dataProvider = "activationPostiveTestType",groups= {"positive"})
	public void activationRenewalPositiveRetryTests(String activityType, String currentSubscriptionState,
			String transactionState, String actionType, String actionTable) throws Exception {

		Integer subscriptionId = RandomUtils.nextInt(1000, 2000);
		// //SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = "" + productId + " " + partnerId + " " + subscriptionId + " " + activityType + " " + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting positive activation retry test  [ " + testMessage + " ]");

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
			AppAssert.assertTrue(false, "Test case fail " + e.getMessage());
			e.printStackTrace();
		}
	}

	@DataProvider(name = "activation_RetryNegativeTestType")
	public Object[][] activation_Retry_NegativeTestType() {
		return new Object[][] { { "ACTIVATION_RETRY", "ACTIVATED", "LOW_BALANCE", "CHARGING", "renewal" },
				{ "ACTIVATION_RETRY", "ACTIVATED", "FAILURE", "CHARGING", "renewal" },
				{ "ACTIVATION_RETRY", "ACTIVATED", "ERROR", "CHARGING", "renewal" },
				{ "ACTIVATION_RETRY", "ACTIVATED", "IN_PROGRESS", "CHARGING", "renewal" },

				{ "ACTIVATION_RETRY", "ACT_INIT", "LOW_BALANCE", "CHARGING", "renewal" },
				{ "ACTIVATION_RETRY", "ACT_INIT", "SUCCESS", "CHARGING", "activation" },

				{ "ACTIVATION_RETRY", "PARKING", "SUCCESS", "CHARGING", "winback" },
				{ "ACTIVATION_RETRY", "PARKING", "FAILURE", "CHARGING", "winback" },
				{ "ACTIVATION_RETRY", "PARKING", "ERROR", "CHARGING", "winback" },
				{ "ACTIVATION_RETRY", "PARKING", "IN_PROGRESS", "CHARGING", "winback" } };
	}

	@Test(/**dependsOnMethods = "createConfigData",**/ dataProvider = "activation_RetryNegativeTestType",groups= {"negative"})
	public void activation_Retry_NegativeTestType(String activityType, String currentSubscriptionState,
			String transactionState, String actionType, String actionTable) throws Exception {
		Integer subscriptionId = (RandomUtils.nextInt(3000, 4000));

		String testMessage = subscriptionId + " " + activityType + " " + currentSubscriptionState + " "
				+ transactionState + " " + actionType;
		logger.info("==================>Starting Negative activation retry test [ " + testMessage + " ]");

		SASValidationHelper.negativeFlow(productId,partnerId, activityType, currentSubscriptionState, transactionState,
				actionType, subscriptionId);

	}

}
