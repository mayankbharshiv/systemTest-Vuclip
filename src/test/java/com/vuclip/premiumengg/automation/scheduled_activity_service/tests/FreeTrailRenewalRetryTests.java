package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RabbitMQConnection;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;

import io.restassured.response.Response;

/**
 * 
 * @author mayank.bharshiv
 *
 */

public class FreeTrailRenewalRetryTests {
	private static Logger logger = Log4J.getLogger("FreeTrailRenewalRetryTests");
	private SASHelper sasHelper;
	int productId;
	int partnerId;
	PublishConfigRequest publishConfigRequest = null;
	private String countryCode = "IN";

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		sasHelper = new SASHelper();
		productId = RandomUtils.nextInt(2000, 3000);
		partnerId = productId;
	}

	@DataProvider(name = "getProductConfig")
	public Object[][] getProductConfig() {
		logger.info("========================Setting up config Data===========================");

		return SASUtils.getALLActivityType();

	}

	@Test(dataProvider = "getProductConfig")
	public void createConfigData(String activityType) throws Exception {

		publishConfigRequest = SASUtils.generateSaveProductConfig(productId, partnerId, activityType);
		SASValidationHelper.validate_sas_api_response(sasHelper.saveProduct(publishConfigRequest));
	}

	@DataProvider(name = "freeTrailRenewalPostiveTestType")
	public Object[][] freeTrailRenewalPostiveTestType() {
		return new Object[][] {
				{ "FREETRIAL_RENEWAL", "ACTIVATED", "SUSPEND", "LOW_BALANCE", "CHARGING", 101, "free_trail", "OPEN" },
				{ "FREETRIAL_RENEWAL", "ACT_INIT", "ACTIVATE", "SUCCESS", "CHARGING", 102, "free_trail", "OPEN" },
				{ "FREETRIAL_RENEWAL", "ACT_INIT", "SUSPEND", "FAILURE", "CHARGING", 103, "free_trail", "OPEN" },
				{ "FREETRIAL_RENEWAL", "ACT_INIT", "SUSPEND", "LOW_BALANCE", "CHARGING", 104, "free_trail", "OPEN" },
				{ "FREETRIAL_RENEWAL", "ACT_INIT", "ACT_INIT", "ERROR", "CHARGING", 105, "free_trail", "OPEN" },
				{ "FREETRIAL_RENEWAL", "SUSPEND", "ACTIVATED", "SUCCESS", "CHARGING", 106, "free_trail", "OPEN" },
				{ "FREETRIAL_RENEWAL", "SUSPEND", "SUSPEND", "FAILURE", "CHARGING", 107, "free_trail", "OPEN" },
				{ "FREETRIAL_RENEWAL", "SUSPEND", "SUSPEND", "LOW_BALANCE", "CHARGING", 108, "free_trail", "OPEN" },
				{ "FREETRIAL_RENEWAL", "SUSPEND", "SUSPEND", "ERROR", "CHARGING", 109, "free_trail", "OPEN" },
				{ "FREETRIAL_RENEWAL", "ACTIVATED", "ACTIVATED", "SUCCESS", "CHARGING", 110, "free_trail", "OPEN" },
				{ "FREETRIAL_RENEWAL", "ACTIVATED", "ACTIVATED", "ERROR", "CHARGING", 111, "free_trail", "OPEN" },
				{ "FREETRIAL_RENEWAL", "ACTIVATED", "SUSPEND", "FAILURE", "CHARGING", 112, "free_trail", "OPEN" }

		};

	}

	@Test(dependsOnMethods = "createConfigData", dataProvider = "freeTrailRenewalPostiveTestType")
	public void freeTrailRenewalPositiveRetryTests(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String actionType, Integer subscriptionId,
			String actionTable, String status) throws Exception {

		subscriptionId = RandomUtils.nextInt(100, 200);
		// SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting positive free trail renewal retry test  [ " + testMessage + " ]");

		try {

			SASValidationHelper.validate_sas_api_response(
					sasHelper.userSubscription(SASUtils.generateUserSubscriptionRequest(productId, partnerId,
							activityType, previousSubscriptionState, currentSubscriptionState, transactionState,
							actionType, subscriptionId)));

			AppAssert
					.assertEqual(
							DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId + " and product_id = "
									+ productId + " and partner_id=" + partnerId).size(),
							0, "Verify no record created");

			Response schedulerResponse = sasHelper
					.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, "FREETRIAL_RENEWAL"));
			SASValidationHelper.validate_schedular_api_response(schedulerResponse);

			AppAssert
					.assertEqual(
							DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId + " and product_id = "
									+ productId + " and partner_id=" + partnerId).size(),
							0, "Verify no record created");

			Message message = RabbitMQConnection.getRabbitTemplate()
					.receive(productId + "_" + partnerId + "_" + "FREETRIAL_RENEWAL" + "_REQUEST_BACKEND", 10000);
			AppAssert.assertTrue(message == null, "Verify there is no record in queue for subscription");
		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}

	@DataProvider(name = "freeTrailRenewalNegativeTestType")
	public Object[][] freeTrailRenewalNegativeTestType() {
		return new Object[][] {
				{ "ACTIVATION", "ACT_INIT", "ACTIVATED", "LOW_BALANCE", "CHARGING", 102, "renewal", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "ACTIVATED", "FAILURE", "CHARGING", 103, "renewal", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "ACTIVATED", "ERROR", "CHARGING", 104, "renewal", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "ACT_INIT", "SUCCESS", "CHARGING", 105, "activation", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "PARKING", "SUCCESS", "CHARGING", 109, "winback", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "PARKING", "FAILURE", "CHARGING", 110, "winback", "OPEN" },
				{ "ACTIVATION", "ACT_INIT", "PARKING", "ERROR", "CHARGING", 112, "winback", "OPEN" }

		};
	}

	@Test(dependsOnMethods = "createConfigData", dataProvider = "freeTrailRenewalNegativeTestType", enabled = false)
	public void freeTrailRenewalNegativeTestType(String activityType, String previousSubscriptionState,
			String currentSubscriptionState, String transactionState, String actionType, Integer subscriptionId,
			String actionTable, String status) throws Exception {
		subscriptionId = RandomUtils.nextInt(3000, 4000);
		// SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting Negative free Trail Renewal retry test  [ " + testMessage + " ]");

		try {
			SASValidationHelper.validate_sas_api_response(
					sasHelper.userSubscription(SASUtils.generateUserSubscriptionRequest(productId, partnerId,
							activityType, previousSubscriptionState, currentSubscriptionState, transactionState,
							actionType, subscriptionId)));

			AppAssert
					.assertEqual(
							DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId + " and product_id = "
									+ productId + " and partner_id=" + partnerId).size(),
							0, "Verify no record created");

			Response schedulerResponse = sasHelper
					.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, "FREETRIAL_RENEWAL"));
			SASValidationHelper.validate_schedular_api_response(schedulerResponse);

			AppAssert
					.assertEqual(
							DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId + " and product_id = "
									+ productId + " and partner_id=" + partnerId).size(),
							0, "Verify no record created");

			Message message = RabbitMQConnection.getRabbitTemplate()
					.receive(productId + "_" + partnerId + "_" + "FREETRIAL_RENEWAL" + "_REQUEST_BACKEND", 10000);
			AppAssert.assertTrue(message == null, "Verify there is no record in queue for subscription");
		} catch (Exception e) {
			Assert.fail(e.toString());
		}

	}

}
