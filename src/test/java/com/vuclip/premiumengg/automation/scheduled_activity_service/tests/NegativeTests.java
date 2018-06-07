package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.UserSubscriptionRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.DateTimeUtil;
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

public class NegativeTests {
	private static Logger logger = Log4J.getLogger("BlackoutWindowTests");
	private SASHelper sasHelper;
	String countryCode = "IN";

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		sasHelper = new SASHelper();
	}

	@Test(groups = { "positive" })
	public void negativeFutureNBDTest() throws Exception {
		logger.info("==================>Starting Future NBD test ");

		String activityType = "ACTIVATION";
		String currentSubscriptionState = "ACTIVATED";
		String transactionState = "SUCCESS";
		String actionType = "CHARGING";
		String actionTable = "renewal";

		int productId = RandomUtils.nextInt(91000, 91500);
		int partnerId = productId;

		PublishConfigRequest publishConfigRequest = SASUtils.loadJson("publishConfigVO.json",
				PublishConfigRequest.class);
		String jsonString = ObjectMapperUtils.writeValueAsString(publishConfigRequest);
		jsonString = jsonString.replaceAll("1111", String.valueOf(productId));
		publishConfigRequest = ObjectMapperUtils.readValueFromString(jsonString, PublishConfigRequest.class);
		SASValidationHelper.validate_sas_api_response(new SASHelper().saveProduct(publishConfigRequest));

		Integer subscriptionId = RandomUtils.nextInt(91000, 91500);

		try {
			UserSubscriptionRequest userSubscriptionRequest = SASUtils.generateUserSubscriptionRequest(productId,
					partnerId, activityType, "", currentSubscriptionState, transactionState, actionType,
					subscriptionId);

			long nBD = DateTimeUtil.getTimeInMillisAddDays(15);
			long endDate = DateTimeUtil.getTimeInMillisAddDays(35);
			userSubscriptionRequest.getSubscriptionInfo().setNextBillingDate(nBD);
			userSubscriptionRequest.getSubscriptionInfo().setEndDate(endDate);

			SASValidationHelper.validate_sas_api_response(sasHelper.userSubscription(userSubscriptionRequest));

			AppAssert
					.assertEqual(
							DBUtils.getRecords(actionTable,
									"subscription_id = " + subscriptionId + " and product_id = " + productId
											+ " and partner_id=" + partnerId + " and date=" + nBD
											+ " and country_code='" + countryCode + "' and status='OPEN'")
									.size(),
							1, "Verify record created");

			SASValidationHelper.validate_schedular_api_response(
					sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, actionTable)));
			AppAssert.assertEqual(
					DBUtils.getRecords(actionTable,
							"subscription_id = " + subscriptionId + " and product_id = " + productId
									+ " and partner_id=" + partnerId + " and date=" + nBD + " and country_code='"
									+ countryCode + "' and status='OPEN'")
							.size(),
					1, "Verify record still with same values");
		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}

	@Test(groups = { "positive" })
	public void negativeTestBatchSizeTest() throws Exception {

		String activityType = "ACTIVATION";
		String currentSubscriptionState = "ACTIVATED";
		String transactionState = "SUCCESS";
		String actionType = "CHARGING";
		String actionTable = "renewal";

		int productId = RandomUtils.nextInt(92000, 92500);
		int partnerId = productId;

		PublishConfigRequest publishConfigRequest = SASUtils.loadJson("publishConfigVO.json",
				PublishConfigRequest.class);
		String jsonString = ObjectMapperUtils.writeValueAsString(publishConfigRequest);
		jsonString = jsonString.replaceAll("1111", String.valueOf(productId));
		publishConfigRequest = ObjectMapperUtils.readValueFromString(jsonString, PublishConfigRequest.class);
		SASUtils.changeBatchSize(publishConfigRequest, 1);
		SASValidationHelper.validate_sas_api_response(new SASHelper().saveProduct(publishConfigRequest));

		Integer subscriptionId = RandomUtils.nextInt(92000, 92500);

		String testMessage = subscriptionId + " " + activityType + " " + currentSubscriptionState + " "
				+ transactionState + " " + actionType;
		logger.info("==================>Starting Invalid BlackOut Window Field Value test  [ " + testMessage + " ]");

		try {
			UserSubscriptionRequest userSubscriptionRequest = SASUtils.generateUserSubscriptionRequest(productId,
					partnerId, activityType, "", currentSubscriptionState, transactionState, actionType,
					subscriptionId);
			long nBD = DateTimeUtil.getTimeInMillis("30", "12", "2017", "00", "00", "00");
			userSubscriptionRequest.getSubscriptionInfo().setNextBillingDate(nBD);
			SASValidationHelper.validate_sas_api_response(sasHelper.userSubscription(userSubscriptionRequest));

			AppAssert.assertEqual(DBUtils.getRecords(actionTable,
					"subscription_id = " + subscriptionId + " and product_id = " + productId + " and partner_id="
							+ partnerId + " and date=" + nBD + " and country_code='" + countryCode + "'")
					.size(), 1, "Verify record created");

			Integer secondSId = RandomUtils.nextInt(92000, 92500);
			userSubscriptionRequest.getSubscriptionInfo().setSubscriptionId(secondSId);
			SASValidationHelper.validate_sas_api_response(sasHelper.userSubscription(userSubscriptionRequest));

			AppAssert.assertEqual(DBUtils
					.getRecords(actionTable,
							"subscription_id = " + secondSId + " and product_id = " + productId + " and partner_id="
									+ partnerId + " and date=" + nBD + " and country_code='" + countryCode + "'")
					.size(), 1, "Verify record created");

			SASValidationHelper.validate_schedular_api_response(
					sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, actionTable)));
			List<Map<String, Object>> records = DBUtils.getRecords(actionTable, " product_id = " + productId
					+ " and partner_id=" + partnerId + " and date=" + nBD + " and country_code='" + countryCode + "'");

			AppAssert.assertEqual(records.size(), 2, "Verify tow record present");

			boolean isInProgress = false, isOpen = false;
			for (Map<String, Object> map : records) {
				if (map.get("status").toString().equalsIgnoreCase("IN_PROGRESS"))
					isInProgress = true;
				if (map.get("status").toString().equalsIgnoreCase("OPEN"))
					isOpen = true;

			}
			AppAssert.assertTrue(isInProgress, "One in process record present");
			AppAssert.assertTrue(isOpen, "One Open record present");

		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}

}
