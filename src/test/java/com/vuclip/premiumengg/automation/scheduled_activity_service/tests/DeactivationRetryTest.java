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
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;

import io.restassured.response.Response;

/**
 * 
 * @author rahul s
 *
 */

public class DeactivationRetryTest {

	private static Logger logger = Log4J.getLogger("DeactivationRetryTest");

	private SASHelper sasHelper;
	int productId;
	int partnerId;
	PublishConfigRequest publishConfigRequest = null;

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {

		sasHelper = new SASHelper();
		productId = RandomUtils.nextInt(2000, 3000);
		partnerId = productId;
		logger.info("Cleanup Test Data");
		// SASDBHelper.cleanTestData("product_id=" + productId);

	}

	@DataProvider(name = "deactivationSetUpConfig")
	public Object[][] activityType() {
		return new Object[][] { { ActivityType.ACTIVATION_TYPE }, { ActivityType.ACTIVATION_RETRY_TYPE },
				{ ActivityType.DEACTIVATION }, { ActivityType.DEACTIVATION_RETRY_TYPE },
				{ ActivityType.FREETRIAL_RENEWAL_TYPE }, { ActivityType.RENEWAL_TYPE },
				{ ActivityType.SYSTEM_CHURN_TYPE }, { ActivityType.WINBACK_TYPE } };

	}

	@Test(dataProvider = "deactivationSetUpConfig")
	public void createConfigData(String activityType) throws Exception {

		// create job config for activity types( ex- Activation, deactivation)
		publishConfigRequest = ObjectMapperUtils.readValue(
				"src/test/resources/configurations/scheduled-activity-service/request/publishConfig.json",
				PublishConfigRequest.class);

		logger.info("Config JSON Body changed");
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
		logger.info("Config API Called");
		SASValidationHelper.validate_sms_api_response(sasHelper.saveProduct(publishConfigRequest));
	}

	@DataProvider(name = "testType")
	public Object[][] testType() {
		return new Object[][] {
				// { "DEACTIVATION", "ACT_INIT", "DEACTIVATED", "SUCCESS", "DEACTIVATE_CONSENT",
				// 111, "deactivation" },
				// { "DEACTIVATION", "ACT_INIT", "DCT_INIT", "IN_PROGRESS",
				// "DEACTIVATE_CONSENT", 555, "deactivation" },
				{ "DEACTIVATION", "ACT_INIT", "DCT_INIT", "FAILURE", "DEACTIVATE_CONSENT", 123, "deactivation" },
				{ "DEACTIVATION", "ACT_INIT", "DCT_INIT", "ERROR", "DEACTIVATE_CONSENT", 111, "deactivation" },
				// { "DEACTIVATION", "SUSPEND", "DEACTIVATED", "SUCCESS", "DEACTIVATE_CONSENT",
				// 111, "deactivation" },
				// { "DEACTIVATION", "SUSPEND", "DCT_INIT", "IN_PROGRESS", "DEACTIVATE_CONSENT",
				// 111, "deactivation" },
				{ "DEACTIVATION", "SUSPEND", "DCT_INIT", "FAILURE", "DEACTIVATE_CONSENT", 111, "deactivation" },
				{ "DEACTIVATION", "SUSPEND", "DCT_INIT", "ERROR", "DEACTIVATE_CONSENT", 111, "deactivation" },
				// { "DEACTIVATION", "PARKING", "DEACTIVATED", "SUCCESS", "DEACTIVATE_CONSENT",
				// 111, "deactivation" },
				// { "DEACTIVATION", "PARKING", "DCT_INIT", "IN_PROGRESS", "DEACTIVATE_CONSENT",
				// 111, "deactivation" },
				{ "DEACTIVATION", "PARKING", "DCT_INIT", "FAILURE", "DEACTIVATE_CONSENT", 111, "deactivation" },
				{ "DEACTIVATION", "PARKING", "DCT_INIT", "ERROR", "DEACTIVATE_CONSENT", 111, "deactivation" },
				// { "DEACTIVATION", "ACTIVATED", "DEACTIVATED", "SUCCESS",
				// "DEACTIVATE_CONSENT", 111, "deactivation" },
				// { "DEACTIVATION", "ACTIVATED", "DCT_INIT", "IN_PROGRESS",
				// "DEACTIVATE_CONSENT", 111, "deactivation" },
				{ "DEACTIVATION", "ACTIVATED", "DCT_INIT", "FAILURE", "DEACTIVATE_CONSENT", 111, "deactivation" },
				{ "DEACTIVATION", "ACTIVATED", "DCT_INIT", "ERROR", "DEACTIVATE_CONSENT", 111, "deactivation" },
				//{ "DEACTIVATION", "DCT_INIT", "DEACTIVATED", "SUCCESS", "DEACTIVATE_CONSENT", 111, "deactivation" },
				// { "DEACTIVATION", "DCT_INIT", "DCT_INIT", "ERROR", "DEACTIVATE_CONSENT", 111,
				// "deactivation" },
				{ "DEACTIVATION", "DCT_INIT", "DCT_INIT", "FAILURE", "DEACTIVATE_CONSENT", 111, "deactivation" }

		};

	}

	@Test(dataProvider = "testType")
	public void deactivationRetryTest(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String actionType, int subscriptionId,
			String actionTable) {

		subscriptionId = RandomUtils.nextInt(800, 990);
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
					schedulerRequest, userSubscriptionRequest, activityType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
