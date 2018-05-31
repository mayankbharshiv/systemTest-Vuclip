package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import java.util.Random;

import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.ActivityType;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.SchedulerRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.UserSubscriptionRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASDBHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;

import io.restassured.response.Response;

/**
 * 
 * @author mayank.bharshiv
 *
 */

public class DeactivationRetryTest {

	private SASHelper sasHelper;
	private SASValidationHelper sasValidationHelper;
	private Random rand;
	int productId;
	int partnerId;

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		sasHelper = new SASHelper();
		sasValidationHelper = new SASValidationHelper();
		rand = new Random();
		productId = 1;// rand.nextInt(500);
		partnerId = productId;
		Log4J.getLogger().info("Cleanup Test Data");
		// SASDBHelper.cleanTestData("product_id=" + productId);

	}

	@DataProvider(name = "deactivationSetUpConfig")
	public Object[][] activityType() {
		return new Object[][] { { ActivityType.ACTIVATION_TYPE }, { ActivityType.ACTIVATION_RETRY_TYPE },
				{ ActivityType.DEACTIVATION }, { ActivityType.DEACTIVATION_RETRY_TYPE },
				{ ActivityType.FREETRIAL_RENEWAL_TYPE }, { ActivityType.RENEWAL_TYPE },
				{ ActivityType.SYSTEM_CHURN_TYPE }, { ActivityType.WINBACK_TYPE } };

	}

	/*
	 * @Test(dataProvider = "deactivationSetUpConfig") public void
	 * createConfigData(String activityType) throws Exception {
	 * 
	 * // create job config for activity types( ex- Activation, deactivation)
	 * PublishConfigRequest publishConfigRequest = ObjectMapperUtils.readValue(
	 * "src/test/resources/configurations/scheduled-activity-service/request/publishConfig.json",
	 * PublishConfigRequest.class);
	 * 
	 * Log4J.getLogger().info("Config JSON Body changed");
	 * publishConfigRequest.getProduct().setProductId(productId);
	 * publishConfigRequest.getProductPartnerMappings().get(0).setProductId(
	 * productId);
	 * publishConfigRequest.getProductPartnerMappings().get(0).setPartnerId(
	 * partnerId);
	 * publishConfigRequest.getProductCountryMapping().setProductId(productId);
	 * publishConfigRequest.getAdNetworkNotifications().get(0).setPartnerId(
	 * partnerId);
	 * publishConfigRequest.getAdNetworkNotifications().get(0).setProductId(
	 * productId);
	 * publishConfigRequest.getActivityFlows().get(0).setPartnerId(partnerId);
	 * publishConfigRequest.getActivityFlows().get(1).setPartnerId(partnerId);
	 * publishConfigRequest.getActivityFlows().get(2).setPartnerId(partnerId);
	 * publishConfigRequest.getPricePoints().get(0).setProductId(productId);
	 * publishConfigRequest.getPricePoints().get(0).setPartnerId(partnerId);
	 * publishConfigRequest.getRetry().get(0).setProductId(productId);
	 * publishConfigRequest.getRetry().get(0).setPartnerId(partnerId);
	 * publishConfigRequest.getBlackouts().get(0).setPartnerId(partnerId);
	 * publishConfigRequest.getBlackouts().get(0).setProductId(productId);
	 * publishConfigRequest.getRetry().get(0).setActivityType(activityType);
	 * Log4J.getLogger().info("Config API Called");
	 * sasValidationHelper.validate_sms_api_response(sasHelper.saveProduct(
	 * publishConfigRequest)); }
	 */

	@DataProvider(name = "testType")
	public Object[][] testType() {
		return new Object[][] {
				{ "DEACTIVATION", "ACT_INIT", "DEACTIVATED", "SUCCESS", "DEACTIVATE_CONSENT", 111, "winback" },
				{ "DEACTIVATION", "ACT_INIT", "DCT_INIT", "IN_PROGRESS", "DEACTIVATE_CONSENT", 555, "winback" },
				{ "DEACTIVATION", "ACT_INIT", "DCT_INIT", "FAILURE", "DEACTIVATE_CONSENT", 123, "deactivation" },
				{ "DEACTIVATION", "ACT_INIT", "DCT_INIT", "ERROR", "DEACTIVATE_CONSENT", 111, "winback" },
				{ "DEACTIVATION", "SUSPEND", "DEACTIVATED", "SUCCESS", "DEACTIVATE_CONSENT", 111, "winback" },
				{ "DEACTIVATION", "SUSPEND", "DCT_INIT", "IN_PROGRESS", "DEACTIVATE_CONSENT", 111, "winback" },
				{ "DEACTIVATION", "SUSPEND", "DCT_INIT", "FAILURE", "DEACTIVATE_CONSENT", 111, "winback" },
				{ "DEACTIVATION", "SUSPEND", "DCT_INIT", "ERROR", "DEACTIVATE_CONSENT", 111, "winback" },
				{ "DEACTIVATION", "PARKING", "DEACTIVATED", "SUCCESS", "DEACTIVATE_CONSENT", 111, "winback" },
				{ "DEACTIVATION", "PARKING", "DCT_INIT", "IN_PROGRESS", "DEACTIVATE_CONSENT", 111, "winback" },
				{ "DEACTIVATION", "PARKING", "DCT_INIT", "FAILURE", "DEACTIVATE_CONSENT", 111, "winback" },
				{ "DEACTIVATION", "PARKING", "DCT_INIT", "ERROR", "DEACTIVATE_CONSENT", 111, "winback" },
				{ "DEACTIVATION", "ACTIVATED", "DEACTIVATED", "SUCCESS", "DEACTIVATE_CONSENT", 111, "winback" },
				{ "DEACTIVATION", "ACTIVATED", "DCT_INIT", "IN_PROGRESS", "DEACTIVATE_CONSENT", 111, "winback" },
				{ "DEACTIVATION", "ACTIVATED", "DCT_INIT", "FAILURE", "DEACTIVATE_CONSENT", 111, "winback" },
				{ "DEACTIVATION", "ACTIVATED", "DCT_INIT", "ERROR", "DEACTIVATE_CONSENT", 111, "winback" },
				{ "DEACTIVATION", "DCT_INIT", "DEACTIVATED", "SUCCESS", "DEACTIVATE_CONSENT", 111, "winback" },
				{ "DEACTIVATION", "DCT_INIT", "DCT_INIT", "ERROR", "DEACTIVATE_CONSENT", 111, "winback" },
				{ "DEACTIVATION", "DCT_INIT", "DCT_INIT", "FAILURE", "DEACTIVATE_CONSENT", 111, "winback" }

		};

	}

	@Test(dataProvider = "testType")
	public void deactivationRetryTest(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String actionType, int subscriptionId,
			String tableEntry) throws Exception {
		subscriptionId = RandomUtils.nextInt(400, 600);
		Log4J.getLogger()
				.info("Starting test for subscription Id " + subscriptionId + " " + activityType + " "
						+ previousSubscriptionState + " " + currentSubscriptionState + " " + transactionState + " "
						+ actionType);

		try {
			UserSubscriptionRequest userSubscriptionRequest = ObjectMapperUtils.readValue(
					"src/test/resources/configurations/scheduled-activity-service/request/userSubscription.json",
					UserSubscriptionRequest.class);
			Log4J.getLogger().info("user subscription JSON Body changed");

			userSubscriptionRequest.getActivityInfo().setActivityType(activityType);
			userSubscriptionRequest.getActivityInfo().setPreviousSubscriptionState(previousSubscriptionState);
			userSubscriptionRequest.getActivityInfo().setCurrentSubscriptionState(currentSubscriptionState);
			userSubscriptionRequest.getActivityEvent().setTransactionState(transactionState);
			userSubscriptionRequest.getActivityInfo().setActionType(actionType);

			userSubscriptionRequest.getSubscriptionInfo().setSubscriptionId(subscriptionId);
			userSubscriptionRequest.getSubscriptionInfo().setProductId(productId);
			userSubscriptionRequest.getSubscriptionInfo().setPartnerId(partnerId);
			userSubscriptionRequest.getActivityEvent().setPartnerId(partnerId);
			userSubscriptionRequest.getActivityEvent().setProductId(productId);
			Response response = sasHelper.userSubscription(userSubscriptionRequest);
			Log4J.getLogger().info("user subscription API Called");
			sasValidationHelper.validate_sms_api_response(response);

			// TODO put in validation heler
			if (DBUtils.getRecord(tableEntry, "subscription_id = " + subscriptionId).size() >= 1)

				SASDBHelper.showAllTableData("subscription Id " + subscriptionId + " " + activityType + " "
						+ previousSubscriptionState + " " + currentSubscriptionState + " " + transactionState + " "
						+ actionType + "Before scheduler call ", String.valueOf(subscriptionId));

			SchedulerRequest schedulerRequest = ObjectMapperUtils.readValue(
					"src/test/resources/configurations/scheduled-activity-service/request/scheduler.json",
					SchedulerRequest.class);
			schedulerRequest.setActivityType(tableEntry.toUpperCase());
			schedulerRequest.setProductId(productId);
			schedulerRequest.setPartnerId(partnerId);
			Response schedulerResponse = sasHelper.scheduler(schedulerRequest);
			System.out.println(schedulerResponse.getStatusLine());
			// VALIDATION HELPER
			SASDBHelper.showAllTableData("subscription Id " + subscriptionId + " " + activityType + " "
					+ previousSubscriptionState + " " + currentSubscriptionState + " " + transactionState + " "
					+ actionType + "After scheduler call ", String.valueOf(subscriptionId));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
