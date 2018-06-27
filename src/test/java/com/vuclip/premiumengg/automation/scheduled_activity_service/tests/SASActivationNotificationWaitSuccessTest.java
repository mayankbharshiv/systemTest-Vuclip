package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.UserSubscriptionRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.DateTimeUtil;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.TimeUnitEnum;
import com.vuclip.premiumengg.automation.utils.DBUtils;

public class SASActivationNotificationWaitSuccessTest {

	private static Logger logger = Log4J.getLogger("SASActivationNotificationWaitSuccessTest");
	int productId;
	int partnerId;
	private SASHelper sasHelper;
	private String countryCode = "IN";

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		sasHelper = new SASHelper();
		productId = SASUtils.productId;
		partnerId = productId;
	}

	@DataProvider(name = "activationNotificationWaitDataProvider")
	public Object[][] activationNotificationWaitDataProvider() {
		return new Object[][] {

				/* PASS */{ "CHARGING", "ACTIVATION", "ACT_INIT", "NOTIFICATION_WAIT", "activation", "OPEN", "NA",
						"CHARGING", "ACTIVATION", "ACTIVATED", "SUCCESS", "renewal", "OPEN" },

				/* PASSED */{ "CHARGING", "ACTIVATION", "ACT_INIT", "NOTIFICATION_WAIT", "activation", "OPEN", "NA",
						"CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE", "activation", "OPEN" },

				/* PASSED */{ "CHARGING", "ACTIVATION", "ACT_INIT", "NOTIFICATION_WAIT", "activation", "OPEN", "NA",
						"CHARGING", "ACTIVATION", "ACT_INIT", "ERROR", "activation", "OPEN" } };
	}

	@Test(dataProvider = "activationNotificationWaitDataProvider", groups = { "once" })
	public void activationNotificationWaitTest(String eventActionType, String activityType,
			String currentSubscriptionState, String transactionState, String actionTable, String beforeSchedularStatus,
			String afteNewEventStatus, String newEventActionType, String newActivityType,
			String newCurrentSubscriptionState, String newTransactionState, String newActionTable,
			String newBeforeSchedularStatus) {

		Integer subscriptionId = RandomUtils.nextInt(58000, 59000);
		String testMessage = subscriptionId + " " + activityType + " " + currentSubscriptionState + " "
				+ transactionState + " " + actionTable + " " + newCurrentSubscriptionState;
		logger.info("***************Starting success Two Flow Notification Wait Tests  [ " + testMessage + " ]");

		try {

			logger.info("=========>First time user subscription event getting trigger");
			UserSubscriptionRequest uSRequest = SASUtils.generateUserSubscriptionRequest(productId, partnerId,
					activityType, "", currentSubscriptionState, transactionState, eventActionType, subscriptionId);
			SASValidationHelper.validate_sas_api_response(sasHelper.userSubscription(uSRequest));

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

			logger.info("=========>Second time user subscription event getting trigger");
			UserSubscriptionRequest newSubscriptionRequest = SASUtils.generateUserSubscriptionRequest(productId,
					partnerId, newActivityType.toUpperCase(), currentSubscriptionState, newCurrentSubscriptionState,
					newTransactionState, newEventActionType, subscriptionId);
			newSubscriptionRequest.getSubscriptionInfo().setNextBillingDate(DateTimeUtil.getDateByAddingValidity(
					newSubscriptionRequest.getSubscriptionInfo().getNextBillingDate(), 1, TimeUnitEnum.DAY.name()));
			newSubscriptionRequest.getActivityEvent().setNextBillingDate(DateTimeUtil.getDateByAddingValidity(
					newSubscriptionRequest.getActivityEvent().getNextBillingDate(), 1, TimeUnitEnum.DAY.name()));

			SASValidationHelper.validate_sas_api_response(sasHelper.userSubscription(newSubscriptionRequest));

			logger.info("=========>Second event: previous Event's DB verification");

			expectedRecords.put("status", afteNewEventStatus);
			SASValidationHelper.validateTableRecord(DBUtils
					.getRecord(actionTable,
							"subscription_id = " + subscriptionId + " and product_id=" + productId + " and partner_id="
									+ partnerId + " and date=" + uSRequest.getSubscriptionInfo().getNextBillingDate())
					.get(0), expectedRecords);

			logger.info("=========>Second event: DB verification");
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

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}
