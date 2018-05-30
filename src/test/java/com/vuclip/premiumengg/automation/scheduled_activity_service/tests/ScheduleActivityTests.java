package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import java.util.Random;

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

public class ScheduleActivityTests {

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
		productId = 1;// rand.nextInt(10);
		partnerId = productId;
		Log4J.getLogger().info("Cleanup Test Data");
		SASDBHelper.cleanTestData("product_id=" + String.valueOf(productId));

	}

	@DataProvider(name = "activityType")
	public Object[][] activityType() {
		return new Object[][] { { ActivityType.ACTIVATION_TYPE }, { ActivityType.ACTIVATION_RETRY_TYPE },
				{ ActivityType.DEACTIVATION }, { ActivityType.DEACTIVATION_RETRY_TYPE },
				{ ActivityType.FREETRIAL_RENEWAL_TYPE }, { ActivityType.RENEWAL_TYPE },
				{ ActivityType.SYSTEM_CHURN_TYPE }, { ActivityType.WINBACK_TYPE } };

	}

	@BeforeMethod

	@Test(dataProvider = "activityType")
	public void setupConfigJob(String activityType) throws Exception {

		// create job config for activity types( ex- Activation, deactivation)
		PublishConfigRequest publishConfigRequest = ObjectMapperUtils.readValue(
				"src/test/resources/configurations/scheduled-activity-service/request/publishConfig.json",
				PublishConfigRequest.class);

		Log4J.getLogger().info("Config JSON Body changed");
		publishConfigRequest.getProduct().setProductId(productId);
		publishConfigRequest.getProductPartnerMappings().get(0).setProductId(productId);
		publishConfigRequest.getProductPartnerMappings().get(0).setPartnerId(partnerId);
		publishConfigRequest.getProductCountryMapping().setProductId(productId);
		publishConfigRequest.getAdNetworkNotifications().get(0).setPartnerId(partnerId);
		publishConfigRequest.getAdNetworkNotifications().get(0).setProductId(productId);
		publishConfigRequest.getActivityFlows().get(0).setPartnerId(partnerId);
		publishConfigRequest.getActivityFlows().get(1).setPartnerId(partnerId);
		publishConfigRequest.getActivityFlows().get(2).setPartnerId(partnerId);
		publishConfigRequest.getPricePoints().get(0).setProductId(productId);
		publishConfigRequest.getPricePoints().get(0).setPartnerId(partnerId);
		publishConfigRequest.getRetry().get(0).setProductId(productId);
		publishConfigRequest.getRetry().get(0).setPartnerId(partnerId);
		publishConfigRequest.getBlackouts().get(0).setPartnerId(partnerId);
		publishConfigRequest.getBlackouts().get(0).setProductId(productId);
		publishConfigRequest.getRetry().get(0).setActivityType(activityType);
		Log4J.getLogger().info("Config API Called");
		sasValidationHelper.validate_sms_api_response(sasHelper.saveProduct(publishConfigRequest));
	}

	@DataProvider(name = "testType")
	public Object[][] testType() {
		return new Object[][] {
				{ "ACTIVATION", "ACT_INIT", "ACTIVATED", "SUCCESS", "CHARGING", "123", "renewal", "OPEN", } };

	}

	@Test(dependsOnMethods = "setupConfigJob", enabled = false, dataProvider = "testType")
	public void scheduleActivityTests(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String actionType, Integer subscriptionId,
			String tableEntry, String status) throws Exception {
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

		//TODO put in validation heler
		System.out.println(DBUtils.getRecord(tableEntry, "subscription_id = " + subscriptionId).toString());

		SchedulerRequest schedulerRequest = ObjectMapperUtils.readValue(
				"src/test/resources/configurations/scheduled-activity-service/request/scheduler.json",
				SchedulerRequest.class);
		schedulerRequest.setActivityType(tableEntry.toUpperCase());
		Response schedulerResponse = sasHelper.scheduler(schedulerRequest);
		//VALIDATION HELPER

	}

}
