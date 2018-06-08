package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;
import com.vuclip.premiumengg.automation.utils.AppAssert;

/**
 * 
 * @author mayank.bharshiv
 *
 */

public class SASDeactivationRetryTests {
	private static Logger logger = Log4J.getLogger("SASDeactivationRetryTests");
	int productId;
	int partnerId;
	PublishConfigRequest publishConfigRequest = null;
	private String countryCode = "IN";

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		productId = SASUtils.productId;
		partnerId = productId;
	}

	@DataProvider(name = "deactivationRetryPositiveDataProvider")
	public Object[][] deactivationRetryPositiveDataProvider() {
		return new Object[][] {
				{ "DEACTIVATION_RETRY", "DCT_INIT", "DCT_INIT", "ERROR", "DEACTIVATE_CONSENT", 101, "deactivation",
						"OPEN" },
				{ "DEACTIVATION_RETRY", "DCT_INIT", "DCT_INIT", "FAILURE", "DEACTIVATE_CONSENT", 102, "deactivation",
						"OPEN" } };
	}

	@Test(dataProvider = "deactivationRetryPositiveDataProvider", groups = { "positive" })
	public void DeactivationRetryPositiveTest(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String eventActionType, Integer subscriptionId,
			String actionTable, String status) throws Exception {

		subscriptionId = RandomUtils.nextInt(14000, 15000);
		// SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + eventActionType;
		logger.info("==================>Starting Deactivation Retry Positive Test  [ " + testMessage + " ]");

		try {
			String schedulerActivity = actionTable;
			String queueName = actionTable;
			SASUtils.executeActivityFlow(productId, partnerId, subscriptionId, countryCode, eventActionType,
					activityType, currentSubscriptionState, transactionState, actionTable, schedulerActivity, "OPEN",
					"IN_PROGRESS", queueName);

		} catch (Exception e) {
			logger.error("activationPositiveTest Failed");
			e.printStackTrace();
			AppAssert.assertTrue(false);
		}
	}

	@DataProvider(name = "deactivationRetryNegativeDataProvider")
	public Object[][] deactivationRetryNegativeDataProvider() {
		return new Object[][] {

				{ "DEACTIVATION_RETRY", "DCT_INIT", "DEACTIVATED", "SUCCESS", "DEACTIVATE_CONSENT", 103, "deactivation",
						"OPEN" },
				{ "DEACTIVATION_RETRY", "DCT_INIT", "DEACTIVATED", "ERROR", "DEACTIVATE_CONSENT", 104, "deactivation",
						"OPEN" },
				{ "DEACTIVATION_RETRY", "DCT_INIT", "DEACTIVATED", "FAILURE", "DEACTIVATE_CONSENT", 105, "deactivation",
						"OPEN" },
				{ "DEACTIVATION_RETRY", "DCT_INIT", "DCT_INIT", "SUCCESS", "DEACTIVATE_CONSENT", 106, "deactivation",
						"OPEN" } };
	}

	@Test(dataProvider = "deactivationRetryNegativeDataProvider", groups = { "negative" })
	public void deactivationRetryNegativeTest(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String actionType, Integer subscriptionId,
			String actionTable, String status) throws Exception {
		subscriptionId = RandomUtils.nextInt(3000, 4000);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting deactivation Retry NegativeTest  [ " + testMessage + " ]");
		SASValidationHelper.negativeFlow(productId, partnerId, activityType, currentSubscriptionState, transactionState,
				actionType, subscriptionId);

	}

}
