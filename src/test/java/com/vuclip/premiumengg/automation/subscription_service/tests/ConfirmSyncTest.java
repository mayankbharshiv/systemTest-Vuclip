package com.vuclip.premiumengg.automation.subscription_service.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RabbitMQConnection;
import com.vuclip.premiumengg.automation.subscription_service.common.models.ConfirmSync;
import com.vuclip.premiumengg.automation.subscription_service.common.models.ConfirmSyncData;
import com.vuclip.premiumengg.automation.subscription_service.common.models.ConfirmSyncQueue;
import com.vuclip.premiumengg.automation.subscription_service.common.models.GetUserStatusResponse;
import com.vuclip.premiumengg.automation.subscription_service.common.models.StateTransitionResponse;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.SDBHelper;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.SHelper;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.SRabitMessageHelper;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.SRedisUtils;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.SUtils;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.SValidationHelper;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.TestDataCreator;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;

import io.restassured.response.Response;

public class ConfirmSyncTest {
	private static Logger logger = Log4J.getLogger("ConfirmSyncTest");
	Map<String, String> query = new HashMap<String, String>();

	@DataProvider(name = "confirmSyncSuccessData")
	public Iterator<Object[]> confirmSyncSuccessData() {
		List<ConfirmSyncData> confirmSuccess = new ArrayList<ConfirmSyncData>();
		confirmSuccess.add(ConfirmSyncData.CONFIRMISNOTCLOSED);
		confirmSuccess.add(ConfirmSyncData.CONFIRMISCLOSED);
		confirmSuccess.add(ConfirmSyncData.CONFIRMCONSENTCHRGCLOSED);
		Collection<Object[]> dp = new ArrayList<Object[]>();
		for (ConfirmSyncData sbData : confirmSuccess) {
			dp.add(new Object[] { sbData });
		}
		return dp.iterator();
	}

	@Test(dataProvider = "confirmSyncSuccessData", groups = { "positive" })
	public void confirmSyncSuccessTest(ConfirmSyncData confirmSuccess) {
		try {
			logger.info("Starting================> Confirm Sync Success Tests");

			ConfirmSync confirmSync = TestDataCreator.getConfirmSyncSuccessTest(confirmSuccess);

			Response response = SHelper.confirmApi(confirmSync);
			SValidationHelper.validate_ss_api_response(response);
			SValidationHelper.validate_ss_jsonBody(response, "userStatus.subscriptionStatus",
					confirmSuccess.getSubscriptionStatus());
			StateTransitionResponse stateTransition = response.getBody().as(StateTransitionResponse.class);

			logger.info("DB Validation");
			String userSubAuthKey = "USERID_" + confirmSync.getProductId() + "_" + confirmSync.getUserId();
			SDBHelper.validateTable("user_subscription", confirmSync.getUserId(), confirmSync.getProductId(),
					confirmSync.getPartnerId(), confirmSync.getNextBillingDate(), userSubAuthKey,
					confirmSuccess.getSubscriptionStatus());
			AppAssert.assertEqual(
					SDBHelper.validateDates("user_subscription", "user_id=" + confirmSync.getUserId(), "start_date",
							"end_date"),
					SDBHelper.getValidityDaysFromBillingPackage(confirmSuccess.getValidityColumn(),
							confirmSuccess.getSubscriptionBillingCode()),
					confirmSuccess.getValidityColumn() + " days is added to the required user");

			logger.info("Redis Validation");
			query.put("userid", confirmSync.getUserId());
			Response getUserResponse = SHelper.getUserStatus(query);
			SValidationHelper.validate_ss_api_response(getUserResponse);
			GetUserStatusResponse getUserStatusResponse = getUserResponse.getBody().as(GetUserStatusResponse.class);
			SValidationHelper.validateGetUserStatusGeneric(getUserStatusResponse,
					stateTransition.getUserStatus().getStartDate(), stateTransition.getUserStatus().getEndDate(),
					confirmSync.getNextBillingDate(), confirmSync.getChargedPrice(),
					stateTransition.getUserStatus().getActivationDate(), null,
					stateTransition.getUserStatus().getSummary(), confirmSuccess.getSubscriptionStatus(),
					confirmSuccess.getSubscriptionBillingCode(), confirmSuccess.getChargedBillingCode(),
					stateTransition.getUserStatus().getCountry(), confirmSuccess.getUserSource(),
					confirmSuccess.getMode(), stateTransition.getUserStatus().isPaid());

		} catch (Exception e) {
			e.printStackTrace();
			AppAssert.assertTrue(false);
		}
	}

	@DataProvider(name = "confirmSyncNotSuccessData")
	public Iterator<Object[]> confirmSyncNotSuccessData() {
		List<ConfirmSyncData> confirmNotSuccess = new ArrayList<ConfirmSyncData>();
		confirmNotSuccess.add(ConfirmSyncData.CONFIRMFAILURE);
		confirmNotSuccess.add(ConfirmSyncData.CONFIRMERROR);
		confirmNotSuccess.add(ConfirmSyncData.CONFIRMCANCELED);
		Collection<Object[]> dp = new ArrayList<Object[]>();
		for (ConfirmSyncData sbData : confirmNotSuccess) {
			dp.add(new Object[] { sbData });
		}
		return dp.iterator();
	}

	@Test(dataProvider = "confirmSyncNotSuccessData", groups = { "positive" })
	public void confirmSyncNotSuccessTest(ConfirmSyncData confirmSuccess) {
		try {
			logger.info("Starting================> Confirm Sync Not Success Tests");

			ConfirmSyncQueue confirmSync = TestDataCreator.getConfirmSyncQueueData(confirmSuccess);
			RabbitMQConnection.getRabbitTemplate().convertAndSend("core_subscription", confirmSync);
			// Response response = SHelper.confirmApi(confirmSync);
			// SValidationHelper.validate_ss_api_response(response);
			// AppAssert.assertTrue(response.then().extract().body().path("successful").equals(true));
			SRabitMessageHelper.addMessageToQueue(confirmSync);
			logger.info("Redis Validation");
			String userSubAuthKey = "USERID_" + confirmSync.getProductId() + "_" + confirmSync.getUserId();
			logger.info("Check Key Not Present in Redis");
			AppAssert.assertTrue(!SRedisUtils.checkKey(userSubAuthKey));

			logger.info("DB Validation");
			SDBHelper.verifyNoActivityRecordPresent("user_subscription", SUtils.productId, SUtils.productId,
					confirmSuccess.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
			AppAssert.assertTrue(false);
		}
	}

	@BeforeClass(alwaysRun = true)
	public void cleanUp() {
		DBUtils.cleanTable("user_subscription",
				"product_id =" + SUtils.productId + " and partner_id =" + SUtils.productId);

	}
}
