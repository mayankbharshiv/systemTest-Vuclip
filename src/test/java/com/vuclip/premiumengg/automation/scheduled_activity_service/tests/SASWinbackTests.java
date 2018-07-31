package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;

/**
 * @author mayank.bharshiv
 */

public class SASWinbackTests {
	private static Logger logger = Log4J.getLogger("WinbackTests");
	int productId;
	int partnerId;

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		productId = SASUtils.productId;
		partnerId = productId;
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
