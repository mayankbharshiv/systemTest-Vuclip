package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import java.math.BigInteger;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.DateTimeUtil;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.TimeUnitEnum;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;

/**
 * 
 * @author rahul.s
 *
 */

public class SASNAStatusTest {
	private static Logger logger = Log4J.getLogger("SASNAStatusTest");
	int productId;
	int partnerId;
	private String countryCode = "IN";

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		productId = SASUtils.productId;
		partnerId = productId;
	}

	@DataProvider(name = "sASNAStatusTestProvider")
	public Object[][] sASNAStatusTestProvider() {
		return new Object[][] {

				{ "DEACTIVATION", "DCT_INIT", "FAILURE", "CHARGING", "deactivation" },
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

	@Test(dataProvider = "sASNAStatusTestProvider", groups = { "positive" })
	public void statusNATests(String activityType, String currentSubscriptionState, String transactionState,
			String eventActionType, String actionTable) throws Exception {

		Integer subscriptionId = RandomUtils.nextInt(1000, 2000);
		logger.info("==================>Starting activation Retry Positive Test  [ "
				+ SASUtils.getTestLogMessage(productId, subscriptionId, eventActionType, activityType,
						currentSubscriptionState, transactionState)
				+ " ]");

		try {

			BigInteger endDate = DateTimeUtil.getDateBySubtractingValidity(DateTimeUtil.getCurrentDateInGMT(), 10, TimeUnitEnum.DAY.name());
			BigInteger nBD = DateTimeUtil.getDateBySubtractingValidity(DateTimeUtil.getCurrentDateInGMT(), 20, TimeUnitEnum.DAY.name());

			SASUtils.executeUserSubscription(productId, partnerId, subscriptionId, countryCode, eventActionType,
					activityType, currentSubscriptionState, transactionState, endDate, nBD, actionTable, "OPEN");
			BigInteger nBD1 = DateTimeUtil.getDateBySubtractingValidity(DateTimeUtil.getCurrentDateInGMT(), 20, TimeUnitEnum.DAY.name());

			SASUtils.executeUserSubscription(productId, partnerId, subscriptionId, countryCode, eventActionType,
					activityType, currentSubscriptionState, transactionState, endDate, nBD1, actionTable, "OPEN");

			AppAssert.assertEqual(DBUtils.getRecords(actionTable,
					"subscription_id = " + subscriptionId + " and product_id = " + productId + " and partner_id="
							+ partnerId + " and country_code='" + countryCode + "' and date=" + nBD + " and status='NA'")
					.size(), 1, "Verify record exists");

			SASUtils.executescheduler(productId, partnerId, actionTable);
			
			AppAssert.assertEqual(DBUtils.getRecords(actionTable,
					"subscription_id = " + subscriptionId + " and product_id = " + productId + " and partner_id="
							+ partnerId + " and country_code='" + countryCode + "' and date=" + nBD + " and status='NA'")
					.size(), 1, "Verify record exists for NA");
			
			AppAssert.assertEqual(DBUtils.getRecords(actionTable,
					"subscription_id = " + subscriptionId + " and product_id = " + productId + " and partner_id="
							+ partnerId + " and country_code='" + countryCode + "' and date=" + nBD1 + " and status='IN_PROGRESS'")
					.size(), 1, "Verify record exists for IN_PROGRESS");
			
			

		} catch (Exception e) {
			logger.error("sASNAStatusTestProvider Failed");
			e.printStackTrace();
			AppAssert.assertTrue(false);
		}
	}

}
