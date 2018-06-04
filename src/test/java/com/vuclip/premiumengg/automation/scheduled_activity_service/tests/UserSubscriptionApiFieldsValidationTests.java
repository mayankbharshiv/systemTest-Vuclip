package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.ActivityType;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.UserSubscriptionRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASDBHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;
import com.vuclip.premiumengg.automation.utils.JsonHelper;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;

public class UserSubscriptionApiFieldsValidationTests {

	private static Logger logger = Log4J.getLogger("UserSubscriptionApiFieldsValidationTests");
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

	@DataProvider(name = "setupConfigJob")
	public Object[][] setupConfigJob() {
		return new Object[][] { { ActivityType.ACTIVATION_TYPE }, { ActivityType.ACTIVATION_RETRY_TYPE },
				{ ActivityType.DEACTIVATION }, { ActivityType.DEACTIVATION_RETRY_TYPE },
				{ ActivityType.FREETRIAL_RENEWAL_TYPE }, { ActivityType.RENEWAL_TYPE },
				{ ActivityType.SYSTEM_CHURN_TYPE }, { ActivityType.WINBACK_TYPE } };

	}

	@Test(dataProvider = "setupConfigJob")
	public void createConfigData(String activityType) throws Exception {

		// create job config for activity types( ex- Activation, deactivation)
		publishConfigRequest = SASUtils.generateSaveProductConfig(productId, partnerId, activityType);
		SASValidationHelper.validate_sas_api_response(sasHelper.saveProduct(publishConfigRequest));
	}

	@DataProvider(name = "invalidUserSubscriptionFields")
	public Object[][] invalidUserSubscriptionFields() {
		return new Object[][] {
				{ "ACTIVATION", "ACT_INIT", "ACT_INIT", null, "CHARGING", 108, "activation", productId, partnerId },
				{ "ACTIVATION", "ACT_INIT", "PARKING", "LOW_BALANCE", null, 111, "winback", productId, partnerId },
				{ null, "ACT_INIT", "ACT_INIT", "LOW_BALANCE", "CHARGING", 106, "winback", productId, partnerId },
				{ "ACTIVATION", "ACT_INIT", "ACT_INIT", "LOW_BALANCE", "CHARGING", 106, "winback", 0, partnerId },
				{ "ACTIVATION", "ACT_INIT", "ACT_INIT", "LOW_BALANCE", "CHARGING", 106, "winback", productId, 0 },

		};

	}

	@Test(dependsOnMethods = "createConfigData", dataProvider = "invalidUserSubscriptionFields",enabled=false)
	public void invalidUserSubscriptionFieldsValidation(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String actionType, Integer subscriptionId,
			String actionTable, Integer userProductId, Integer userPartnerId) throws Exception {

		subscriptionId = RandomUtils.nextInt(100, 200);
		SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting user subscription invalid field values test  [ " + testMessage + " ]");

		try {

			SASValidationHelper.validate_sas_invalid_api_response(
					sasHelper.userSubscription(SASUtils.generateUserSubscriptionRequest(userProductId, userPartnerId,
							activityType, previousSubscriptionState, currentSubscriptionState, transactionState,
							actionType, subscriptionId)));

		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}

	@DataProvider(name = "missingUserSubscriptionFields")
	public Object[][] missingUserSubscriptionFields() {
		return new Object[][] { { "activityType" }, /*{ "actionType" }, { "productId" }, { "partnerId" },
				{ "transactionState" }*/ };

	}

	@Test(dependsOnMethods = "createConfigData", dataProvider = "missingUserSubscriptionFields")
	public void missingUserSubscriptionFieldsValidation(String jsonElement) throws Exception {
		String activityType = "WINBACK";
		String previousSubscriptionState = "PARKING";
		String currentSubscriptionState = "ACTIVATED";
		String transactionState = "SUCCESS";
		String actionType = "CHARGING";
		Integer subscriptionId = RandomUtils.nextInt(100, 200);
		SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting user subscription missing values test  [ " + testMessage + " ]");

		try {

			UserSubscriptionRequest userSubscriptionRequest = SASUtils.generateUserSubscriptionRequest(productId, partnerId, activityType, previousSubscriptionState, currentSubscriptionState, transactionState, actionType, subscriptionId);
			String jsonString =JsonHelper.remove(UserSubscriptionRequest.class, userSubscriptionRequest, jsonElement);
			System.out.println(jsonString);
			userSubscriptionRequest= ObjectMapperUtils.readValueFromString(jsonString,UserSubscriptionRequest.class);
			SASValidationHelper.validate_sas_invalid_api_response(sasHelper.userSubscription(userSubscriptionRequest));

		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}
}
