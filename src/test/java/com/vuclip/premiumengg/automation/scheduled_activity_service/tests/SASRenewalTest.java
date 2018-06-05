package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.billing_package_service.common.models.QueueResponse;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RabbitMQConnection;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASDBHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;

/**
 * 
 * @author mayank.bharshiv
 *
 */

public class SASRenewalTest {
	private static Logger logger = Log4J.getLogger("ActivationRetryTests");
	private SASHelper sasHelper;
	int productId;
	int partnerId;
	PublishConfigRequest publishConfigRequest = null;
	private String countryCode = "IN";

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		sasHelper = new SASHelper();
		productId = SASUtils.productId;//RandomUtils.nextInt(2000, 3000);
		partnerId = productId;
	}

/*	@DataProvider(name = "getProductConfig")
	public Object[][] getProductConfig() {
		logger.info("========================Setting up config Data===========================");

		return SASUtils.getALLActivityType();

	}

	@Test(dataProvider = "getProductConfig")
	public void createConfigData(String activityType) throws Exception {

		publishConfigRequest = SASUtils.generateSaveProductConfig(productId, partnerId, activityType);
		SASValidationHelper.validate_sas_api_response(sasHelper.saveProduct(publishConfigRequest));
	}*/

	@DataProvider(name = "renewalPostiveTestType")
	public Object[][] renewalPostiveTestType() {
		return new Object[][] {
				{ "RENEWAL",  "ACTIVATED", "SUCCESS", "CHARGING",  "renewal", "OPEN" },
				{ "RENEWAL",  "ACTIVATED", "FAILURE", "CHARGING",  "renewal_retry", "OPEN" },
				{ "RENEWAL",  "ACTIVATED", "ERROR", "CHARGING",  "renewal_retry", "OPEN" },
				//fail{ "RENEWAL",  "ACTIVATED", "IN_PROGRESS", "CHARGING",  "winback", "OPEN" },
				//fail{ "RENEWAL",  "ACTIVATED", "NOTIFICATION_WAIT", "CHARGING",  "winback", "OPEN" } ,
				
				{ "RENEWAL",  "SUSPEND", "FAILURE", "CHARGING",  "renewal_retry", "OPEN" },
				{ "RENEWAL",  "SUSPEND", "ERROR", "CHARGING",  "renewal_retry", "OPEN" },
				//Fail{ "RENEWAL",  "SUSPEND", "NOTIFICATION_WAIT", "CHARGING",  "winback", "OPEN" } 	,
				//Fail{ "RENEWAL",  "SUSPEND", "LOW_BALANCE", "CHARGING",  "winback", "OPEN" }

		};

	}

	@Test(/**dependsOnMethods = "createConfigData",**/ dataProvider = "renewalPostiveTestType")
	public void renewalPositiveTests(String activityType,
			String currentSubscriptionState, String transactionState, String actionType,
			String actionTable, String status) throws Exception {

	int	subscriptionId = RandomUtils.nextInt(31000, 32000);
		//SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = subscriptionId + " " + activityType  + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting positive activation retry test  [ " + testMessage + " ]");

		try {

			SASValidationHelper.validate_sas_api_response(
					sasHelper.userSubscription(SASUtils.generateUserSubscriptionRequest(productId, partnerId,
							activityType, "", currentSubscriptionState, transactionState,
							actionType, subscriptionId)));

			Map<String, String> expectedRecords = new HashMap<String, String>();
			expectedRecords.put("status", "OPEN");
			expectedRecords.put("product_id", String.valueOf(productId));
			expectedRecords.put("partner_id", String.valueOf(partnerId));
			expectedRecords.put("subscription_id", String.valueOf(subscriptionId));
			expectedRecords.put("country_code", countryCode);

			SASDBHelper.showAllActivityTableData(testMessage, String.valueOf(subscriptionId));
			SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId
					+ " and product_id = " + productId + " and partner_id=" + partnerId).get(0), expectedRecords);

			SASValidationHelper.validate_schedular_api_response(
					sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, actionTable)));

			SASDBHelper.showAllActivityTableData(testMessage, String.valueOf(subscriptionId));

			expectedRecords.put("status", "IN_PROGRESS");

			SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId
					+ " and product_id=" + productId + " and partner_id=" + partnerId).get(0), expectedRecords);

			System.out.println("QUEUE NAME : "+productId + "_" + partnerId + "_" + actionTable.toUpperCase() + "_REQUEST_BACKEND");
			Message message = RabbitMQConnection.getRabbitTemplate()
					.receive(productId + "_" + partnerId + "_" + actionTable.toUpperCase() + "_REQUEST_BACKEND", 25000);
			SASValidationHelper.validateQueueMessage(
					ObjectMapperUtils.readValueFromString(new String(message.getBody()), QueueResponse.class),
					productId, partnerId, subscriptionId, countryCode, actionTable.toUpperCase());
		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}

	@DataProvider(name = "renewalNegativeTestType")
	public Object[][] renewalNegativeTestType() {
		return new Object[][] {
	{ "RENEWAL",  "SUSPEND", "IN_PROGRESS", "CHARGING",  "winback", "OPEN" },
	{ "RENEWAL",  "SUSPEND", "SUCCESS", "CHARGING",  "activation", "OPEN" },

						};
	}

	@Test(/**dependsOnMethods = "createConfigData",**/ dataProvider = "renewalNegativeTestType")
	public void renewalNegativeTest(String activityType,
			String currentSubscriptionState, String transactionState, String actionType,
			String actionTable, String status)throws Exception {
		int subscriptionId = RandomUtils.nextInt(53000, 54000);
		//SASDBHelper.cleanTestData("subscription_id=" + subscriptionId);
		String testMessage = subscriptionId + " " + activityType  + " "
				+ currentSubscriptionState + " " + transactionState + " " + actionType;
		logger.info("==================>Starting Negative activation retry test  [ " + testMessage + " ]");

		SASValidationHelper.negativeFlow(productId,partnerId, activityType, currentSubscriptionState, transactionState,
				actionType, subscriptionId);

	}

}
