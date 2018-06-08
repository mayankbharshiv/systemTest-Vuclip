package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;
import com.vuclip.premiumengg.automation.utils.AppAssert;

/**
 * 
 * @author rahul s
 *
 */

public class SASDeactivationTest {

	private static Logger logger = Log4J.getLogger("SASDeactivationTest");

	int productId;
	int partnerId;

	private String countryCode = "IN";

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {

		productId = SASUtils.productId;
		partnerId = productId;

	}

	@DataProvider(name = "deactivationDataProvider")
	public Object[][] deactivationDataProvider() {
		return new Object[][] {
				// covered in sasTest{ "DEACTIVATION", "ACT_INIT", "DCT_INIT", "FAILURE",
				// "DEACTIVATE_CONSENT", 123, "deactivation" },
				// covered in sasTest{ "DEACTIVATION", "ACT_INIT", "DCT_INIT", "ERROR",
				// "DEACTIVATE_CONSENT", 111, "deactivation" },

		};

	}

	@Test(dataProvider = "deactivationDataProvider", groups = { "positive" })
	public void deactivationRetryTest(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String eventActionType, int subscriptionId,
			String actionTable) {

		subscriptionId = RandomUtils.nextInt(10000, 11000);

		logger.info("==================>Starting positive deactivation retry test  [ "
				+ SASUtils.getTestLogMessage(productId, subscriptionId, eventActionType, activityType,
						currentSubscriptionState, transactionState)
				+ " ]");

		try {
			String schedulerActivity = actionTable;
			String queueName = actionTable.toUpperCase();
			SASUtils.executeActivityFlow(productId, partnerId, subscriptionId, countryCode, eventActionType,
					activityType, currentSubscriptionState, transactionState, actionTable, schedulerActivity, "OPEN",
					"IN_PROGRESS", queueName);

		} catch (Exception e) {
			logger.error("activationPositiveTest Failed");
			e.printStackTrace();
			AppAssert.assertTrue(false);
		}
	}

	@DataProvider(name = "deactivationNegativeDataProvider")
	public Object[][] deactivationNegativeDataProvider() {
		return new Object[][] {
				{ "DEACTIVATION", "SUSPEND", "DCT_INIT", "CONFIRMED", "DEACTIVATE_CONSENT", 111, "deactivation" },
				{ "DEACTIVATION", "SUSPEND", "DCT_INIT", "IN_PROGRESS", "DEACTIVATE_CONSENT", 111, "deactivation" },
				{ "DEACTIVATION", "PARKING", "DCT_INIT", "NOTIFICATION_WAIT", "DEACTIVATE_CONSENT", 111,
						"deactivation" },
				{ "DEACTIVATION", "PARKING", "DEACTIVATION", "SUCCESS", "DEACTIVATE_CONSENT", 111, "deactivation" },

				{ "DEACTIVATION", "ACT_INIT", "DEACTIVATED", "FAILURE", "DEACTIVATE_CONSENT", 123, "deactivation" },
				{ "DEACTIVATION", "ACT_INIT", "DEACTIVATED", "ERROR", "DEACTIVATE_CONSENT", 111, "deactivation" },
				{ "DEACTIVATION", "SUSPEND", "DEACTIVATED", "CONFIRMED", "DEACTIVATE_CONSENT", 111, "deactivation" },
				{ "DEACTIVATION", "SUSPEND", "DEACTIVATED", "IN_PROGRESS", "DEACTIVATE_CONSENT", 111, "deactivation" },
				{ "DEACTIVATION", "PARKING", "DEACTIVATED", "NOTIFICATION_WAIT", "DEACTIVATE_CONSENT", 111,
						"deactivation" },

				{ "DEACTIVATION", "SUSPEND", "DCT_INIT", "SUCCESS", "DEACTIVATE_CONSENT", 111, "deactivation" },

		};

	}

	@Test(dataProvider = "deactivationNegativeDataProvider", groups = { "negative" })
	public void deactivationNegativeTest(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String actionType, int subscriptionId,
			String actionTable) {

		subscriptionId = RandomUtils.nextInt(26000, 27000);

		logger.info("==================>Starting deactivation Negative Test [ " + SASUtils.getTestLogMessage(productId,
				subscriptionId, actionType, activityType, currentSubscriptionState, transactionState) + " ]");

		SASValidationHelper.negativeFlow(productId, partnerId, activityType, currentSubscriptionState, transactionState,
				actionType, subscriptionId);
	}

}
