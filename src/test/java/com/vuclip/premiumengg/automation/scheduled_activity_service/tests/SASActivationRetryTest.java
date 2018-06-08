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
 * @author rahul.s
 *
 */

public class SASActivationRetryTest {
	private static Logger logger = Log4J.getLogger("SASActivationRetryTest");
	int productId;
	int partnerId;
	private String countryCode = "IN";

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		productId = SASUtils.productId;
		partnerId = productId;
	}

	@DataProvider(name = "activationRetryPostiveDataProvider")
	public Object[][] activationRetryPostiveDataProvider() {
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
				// { "ACTIVATION_RETRY", "PARKING", "LOW_BALANCE", "CHARGING", "winback" },

		};

	}

	@Test(dataProvider = "activationRetryPostiveDataProvider", groups = { "positive" })
	public void activationRetryPositiveTest(String activityType, String currentSubscriptionState,
			String transactionState, String eventActionType, String actionTable) throws Exception {

		Integer subscriptionId = RandomUtils.nextInt(1000, 2000);
		logger.info("==================>Starting activation Retry Positive Test  [ "
				+ SASUtils.getTestLogMessage(productId, subscriptionId, eventActionType, activityType,
						currentSubscriptionState, transactionState)
				+ " ]");

		try {

			String schedulerActivity = actionTable;
			String queueName = actionTable;
			SASUtils.executeActivityFlow(productId, partnerId, subscriptionId, countryCode, eventActionType,
					activityType, currentSubscriptionState, transactionState, actionTable, schedulerActivity, "OPEN",
					"IN_PROGRESS", queueName);
		} catch (Exception e) {
			logger.error("activationRetryPositiveTest Failed");
			e.printStackTrace();
			AppAssert.assertTrue(false);
		}
	}

	@DataProvider(name = "activationRetryNegativeDrataPovider")
	public Object[][] activationRetryNegativeDrataPovider() {
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

	@Test(dataProvider = "activationRetryNegativeDrataPovider", groups = { "negative" })
	public void activationRetryNegativeTest(String activityType, String currentSubscriptionState,
			String transactionState, String actionType, String actionTable) throws Exception {
		Integer subscriptionId = (RandomUtils.nextInt(3000, 4000));

		logger.info(
				"==================>Starting activation Retry Negative Test [ " + SASUtils.getTestLogMessage(productId,
						subscriptionId, actionType, activityType, currentSubscriptionState, transactionState) + " ]");

		SASValidationHelper.negativeFlow(productId, partnerId, activityType, currentSubscriptionState, transactionState,
				actionType, subscriptionId);

	}

}
