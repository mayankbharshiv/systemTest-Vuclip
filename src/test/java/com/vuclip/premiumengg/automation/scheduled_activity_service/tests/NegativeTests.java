package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import java.math.BigInteger;
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
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.TimeUnitEnum;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;

/**
 * @author rahul.s
 */

public class NegativeTests {
	private static Logger logger = Log4J.getLogger("BlackoutWindowTests");
	String countryCode = "IN";
	private SASHelper sasHelper;

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

		int productId = SASUtils.productId;
		int partnerId = productId;

		// PublishConfigRequest publishConfigRequest = SASUtils.productConfig;
		// PublishConfigRequest publishConfigRequest =
		// SASUtils.loadJson("publishConfigVO.json",
		// PublishConfigRequest.class);
		// String jsonString =
		// ObjectMapperUtils.writeValueAsString(publishConfigRequest);
		// jsonString = jsonString.replaceAll("1111", String.valueOf(productId));
		// publishConfigRequest = ObjectMapperUtils.readValueFromString(jsonString,
		// PublishConfigRequest.class);
		//
		// SASValidationHelper.validate_sas_api_response(new
		// SASHelper().saveProduct(publishConfigRequest));

		Integer subscriptionId = RandomUtils.nextInt(91000, 91500);

		try {
			UserSubscriptionRequest userSubscriptionRequest = SASUtils.generateUserSubscriptionRequest(productId,
					partnerId, activityType, "", currentSubscriptionState, transactionState, actionType,
					subscriptionId);

			BigInteger nBD = DateTimeUtil.getDateByAddingValidity(DateTimeUtil.getCurrentDateInGMT(), 15,
					TimeUnitEnum.DAY.name());
			BigInteger endDate = DateTimeUtil.getDateByAddingValidity(DateTimeUtil.getCurrentDateInGMT(), 35,
					TimeUnitEnum.DAY.name());
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

		int productId = RandomUtils.nextInt(88500, 88600);
		int partnerId = productId;
		String countryCode = "IN";

		PublishConfigRequest publishConfigRequest = SASUtils.loadJson("publishConfigVO.json",
				PublishConfigRequest.class);
		String jsonString = ObjectMapperUtils.writeValueAsString(publishConfigRequest);
		jsonString = jsonString.replaceAll("1111", String.valueOf(productId));
		jsonString = jsonString.replaceAll("9999", String.valueOf(partnerId));
		jsonString = jsonString.replaceAll("CCDE", countryCode);
		publishConfigRequest = ObjectMapperUtils.readValueFromString(jsonString, PublishConfigRequest.class);
		publishConfigRequest = SASUtils.changeBatchSize(publishConfigRequest, 1);
		SASValidationHelper.validate_sas_api_response(new SASHelper().saveProduct(publishConfigRequest));

		Integer subscriptionId = RandomUtils.nextInt(92000, 92500);

		String testMessage = subscriptionId + " " + activityType + " " + currentSubscriptionState + " "
				+ transactionState + " " + actionType;
		logger.info("==================>Starting Invalid BlackOut Window Field Value test  [ " + testMessage + " ]");

		try {
			UserSubscriptionRequest userSubscriptionRequest = SASUtils.generateUserSubscriptionRequest(productId,
					partnerId, activityType, "", currentSubscriptionState, transactionState, actionType,
					subscriptionId);
			BigInteger nBD = DateTimeUtil.getDateByAddingValidity(DateTimeUtil.getCurrentDateInGMT(), 35,
					TimeUnitEnum.DAY.name());
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
					sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, "RENEWAL")));

			List<Map<String, Object>> records = DBUtils.getRecords(actionTable, " product_id = " + productId
					+ " and partner_id=" + partnerId + " and date=" + nBD + " and country_code='" + countryCode + "'");

			AppAssert.assertEqual(records.size(), 2, "Verify two record present");

			boolean /* isInProgress = false, */ isOpen = false;
			for (Map<String, Object> map : records) {
				isOpen = false;
				System.out.println(map.get("status").toString());
				if (map.get("status").toString().equalsIgnoreCase("OPEN"))
					isOpen = true;

			}
			AppAssert.assertTrue(isOpen, "One Open record present");

		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}

}
