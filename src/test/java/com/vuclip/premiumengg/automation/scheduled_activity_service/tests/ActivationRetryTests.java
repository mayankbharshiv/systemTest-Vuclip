package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.billing_package_service.common.models.QueueResponse;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RabbitMQConnection;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.ActivityType;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.SchedulerRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.UserSubscriptionRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASDBHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;

import io.restassured.response.Response;

/**
 * 
 * @author mayank.bharshiv
 *
 */

public class ActivationRetryTests {
	private static Logger logger = Log4J.getLogger("ActivationRetryTests");
	private SASHelper sasHelper;
	int productId;
	int partnerId;
	PublishConfigRequest publishConfigRequest = null;

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		sasHelper = new SASHelper();
		productId = RandomUtils.nextInt(2000, 3000);
		partnerId = productId;
	}

	@DataProvider(name = "activationSetupConfigJob")
	public Object[][] activityType() {
		return new Object[][] { { ActivityType.ACTIVATION_TYPE }, { ActivityType.ACTIVATION_RETRY_TYPE },
				{ ActivityType.DEACTIVATION }, { ActivityType.DEACTIVATION_RETRY_TYPE },
				{ ActivityType.FREETRIAL_RENEWAL_TYPE }, { ActivityType.RENEWAL_TYPE },
				{ ActivityType.SYSTEM_CHURN_TYPE }, { ActivityType.WINBACK_TYPE } };

	}

	@Test(dataProvider = "activationSetupConfigJob")
	public void createConfigData(String activityType) throws Exception {

		// create job config for activity types( ex- Activation, deactivation)
		publishConfigRequest = SASUtils.loadJson("publishConfig.json", PublishConfigRequest.class);

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
		SASValidationHelper.validate_sms_api_response(sasHelper.saveProduct(publishConfigRequest));
	}

	@DataProvider(name = "activationPostiveTestType")
	public Object[][] activationPostiveTestType() {
		return new Object[][] {
				{ "ACTIVATION", "ACT_INIT", "ACTIVATED", "SUCCESS", "CHARGING", 101, "renewal", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "ACT_INIT", "FAILURE", "CHARGING", 107, "activation", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "ACT_INIT", "ERROR", "CHARGING", 108, "activation", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "PARKING", "LOW_BALANCE", "CHARGING", 111, "winback", "OPEN" }, };

	}

	@Test(dependsOnMethods = "createConfigData", dataProvider = "activationPostiveTestType")
	public void activationPositiveRetryTests(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String actionType, Integer subscriptionId,
			String actionTable, String status) throws Exception {

		subscriptionId = RandomUtils.nextInt(100, 200);
		SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("Starting test for " + testMessage);

		try {
			UserSubscriptionRequest userSubscriptionRequest = SASUtils.loadJson("userSubscription.json",
					UserSubscriptionRequest.class);

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
			SASValidationHelper.validate_sms_api_response(sasHelper.userSubscription(userSubscriptionRequest));

			Map<String, String> expectedRecords = new HashMap<String, String>();
			expectedRecords.put("status", "OPEN");
			expectedRecords.put("product_id", String.valueOf(productId));
			expectedRecords.put("partner_id", String.valueOf(partnerId));
			expectedRecords.put("subscription_id", String.valueOf(subscriptionId));
			expectedRecords.put("country_code",
					publishConfigRequest.getProductCountryMapping().getCountries().get(0).getCountryCode());

			SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId
					+ " and product_id = " + productId + " and partner_id=" + partnerId).get(0), expectedRecords);

			SchedulerRequest schedulerRequest = SASUtils.loadJson("scheduler.json", SchedulerRequest.class);

			schedulerRequest.setActivityType(actionTable.toUpperCase());
			schedulerRequest.setProductId(productId);
			schedulerRequest.setPartnerId(partnerId);
			Response schedulerResponse = sasHelper.scheduler(schedulerRequest);
			SASValidationHelper.validate_schedular_api_response(schedulerResponse);
			System.out.println(schedulerResponse.getStatusLine());

			expectedRecords.put("status", "IN_PROGRESS");

			SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId
					+ " and product_id=" + productId + " and partner_id=" + partnerId).get(0), expectedRecords);

			Message message = RabbitMQConnection.getRabbitTemplate()
					.receive(productId + "_" + partnerId + "_" + actionTable.toUpperCase() + "_REQUEST_BACKEND", 25000);
			SASValidationHelper.validateQueueMessage(
					ObjectMapperUtils.readValueFromString(new String(message.getBody()), QueueResponse.class),
					schedulerRequest, userSubscriptionRequest, actionTable.toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DataProvider(name = "activationNegativeTestType")
	public Object[][] activationNegativeTestType() {
		return new Object[][] {
				{ "ACTIVATION", "ACT_INIT", "ACTIVATED", "LOW_BALANCE", "CHARGING", 102, "renewal", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "ACTIVATED", "FAILURE", "CHARGING", 103, "renewal", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "ACTIVATED", "ERROR", "CHARGING", 104, "renewal", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "ACT_INIT", "SUCCESS", "CHARGING", 105, "activation", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "ACT_INIT", "LOW_BALANCE", "CHARGING", 106, "activation", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "PARKING", "SUCCESS", "CHARGING", 109, "winback", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "PARKING", "FAILURE", "CHARGING", 110, "winback", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "PARKING", "ERROR", "CHARGING", 112, "winback", "OPEN" }

		};
	}

	@Test(dependsOnMethods = "createConfigData", dataProvider = "activationNegativeTestType")
	public void activationNegativeTestType(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String actionType, Integer subscriptionId,
			String actionTable, String status) throws Exception {

	}

}
