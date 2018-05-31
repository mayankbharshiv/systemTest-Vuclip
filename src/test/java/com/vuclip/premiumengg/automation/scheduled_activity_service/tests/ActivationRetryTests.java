package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import java.util.Random;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.ActivityType;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.SchedulerRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.UserSubscriptionRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;

/**
 * 
 * @author mayank.bharshiv
 *
 */

public class ActivationRetryTests {

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
		productId = rand.nextInt(10)+1;
		partnerId = productId;
	}
	
/*	@BeforeMethod
	public void cleanTestData()
	{
		Log4J.getLogger().info("Cleanup Test Data");
		SASDBHelper.cleanTestData("product_id=" + productId);
	}
*/

	@DataProvider(name = "setupConfigJob")
	public Object[][] activityType() {
		return new Object[][] { { ActivityType.ACTIVATION_TYPE }, { ActivityType.ACTIVATION_RETRY_TYPE },
				{ ActivityType.DEACTIVATION }, { ActivityType.DEACTIVATION_RETRY_TYPE },
				{ ActivityType.FREETRIAL_RENEWAL_TYPE }, { ActivityType.RENEWAL_TYPE },
				{ ActivityType.SYSTEM_CHURN_TYPE }, { ActivityType.WINBACK_TYPE } };

	}

	@Test(dataProvider = "setupConfigJob",groups="{config}")
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
				    { "ACTIVATION", "ACT_INIT", "ACTIVATED", "SUCCESS", "CHARGING", 101, "renewal", "OPEN"},
					{ "ACTIVATION", "ACT_INIT", "ACT_INIT", "FAILURE", "CHARGING", 107,"activation", "OPEN"},
					{ "ACTIVATION", "ACT_INIT", "ACT_INIT", "ERROR", "CHARGING", 108,"activation", "OPEN"},
		  		    { "ACTIVATION", "ACT_INIT", "PARKING", "LOW_BALANCE", "CHARGING", 111, "winback", "OPEN"},
				/*{ "ACTIVATION", "ACT_INIT", "ACTIVATED", "LOW_BALANCE", "CHARGING", 102, "renewal", "OPEN"},
				{ "ACTIVATION", "ACT_INIT", "ACTIVATED", "FAILURE", "CHARGING", 103, "renewal", "OPEN"},
				{ "ACTIVATION", "ACT_INIT", "ACTIVATED", "ERROR", "CHARGING", 104, "renewal", "OPEN"},
				{ "ACTIVATION", "ACT_INIT", "ACT_INIT", "SUCCESS", "CHARGING", 105, "activation", "OPEN"},
				{ "ACTIVATION", "ACT_INIT", "ACT_INIT", "LOW_BALANCE", "CHARGING", 106,"activation", "OPEN"},
				{ "ACTIVATION", "ACT_INIT", "PARKING", "SUCCESS", "CHARGING", 109, "winback", "OPEN"},
				{ "ACTIVATION", "ACT_INIT", "PARKING", "FAILURE", "CHARGING", 110, "winback", "OPEN"},
				{ "ACTIVATION", "ACT_INIT", "PARKING", "ERROR", "CHARGING", 112, "winback", "OPEN"}		
		 no entry*/
		};

	}

	@Test(dependsOnMethods = "setupConfigJob",dataProvider = "testType",groups="{tests}")
	public void activationRetryTests(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String actionType, Integer  subscriptionId,
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
		Log4J.getLogger().info("user subscription API Called");
		sasValidationHelper.validate_sms_api_response(sasHelper.userSubscription(userSubscriptionRequest));

		System.out.println("*****************************"+DBUtils.getRecord(tableEntry, "subscription_id = " + subscriptionId).get(0).get("status").toString());

		SchedulerRequest schedulerRequest = ObjectMapperUtils.readValue(
				"src/test/resources/configurations/scheduled-activity-service/request/scheduler.json",
				SchedulerRequest.class);
		schedulerRequest.setPartnerId(partnerId);
		schedulerRequest.setProductId(productId);
		schedulerRequest.setActivityType(tableEntry.toUpperCase());
		
		
		Log4J.getLogger().info("scheduler API Called");
		sasValidationHelper.validate_sms_api_response(sasHelper.scheduler(schedulerRequest));
		
		System.out.println("****************************"+DBUtils.getRecord("renewal", "subscription_id = " + subscriptionId).get(0).get("status").toString());
		
		//VALIDATION HELPER

	}

}
