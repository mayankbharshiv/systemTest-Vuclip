package com.vuclip.premiumengg.automation.subscription_service.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.subscription_service.common.models.GetUserStatusResponse;
import com.vuclip.premiumengg.automation.subscription_service.common.models.GetUserStatusTestData;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.DateUtils;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.SHelper;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.SRedisUtils;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.SValidationHelper;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.TestDataCreator;
import com.vuclip.premiumengg.automation.utils.AppAssert;

import io.restassured.response.Response;

public class VerifyGetUSerStatusResponse {


	Map<String, String> query = new HashMap<String, String>();
	private static Logger logger = Log4J.getLogger("GetUserStatus");

	@DataProvider(name = "insertRecordActivation")
	public Iterator<Object[]> insertRecordActivation() {

		ArrayList<GetUserStatusTestData> getUserStatus = new ArrayList<GetUserStatusTestData>();
		getUserStatus.add(GetUserStatusTestData.ACTINIT);
		Collection<Object[]> dp = new ArrayList<Object[]>();
		for (GetUserStatusTestData sbData : getUserStatus) {
			dp.add(new Object[] { sbData });
		}
		return dp.iterator();
	}

	@Test(dataProvider = "insertRecordActivation", groups = { "negative" })
	public void getUserStatusActivationTest(GetUserStatusTestData getUserStatus) throws Exception {

		logger.info("Starting ================> GetUserStatus Activation  Test");

		String user_sub_auth_key = TestDataCreator.createUserStatusActivationTestData(getUserStatus);

		logger.info("Check Key Present in Redis");
		AppAssert.assertTrue(SRedisUtils.checkKey(user_sub_auth_key));

		logger.info("Get User Status for UserId: " + getUserStatus.getUser_id() + " and MSISDN: "
				+ getUserStatus.getMsisdn());
		query.put("msisdn", getUserStatus.getUser_id());
		query.put("userid", getUserStatus.getMsisdn());
		Response getUserResponse = SHelper.getUserStatus(query);
		SValidationHelper.validate_ss_api_response(getUserResponse);

		GetUserStatusResponse getUserStatusResponse = getUserResponse.getBody().as(GetUserStatusResponse.class);
		SValidationHelper.validateGetUserStatus(getUserStatusResponse, getUserStatus.getStart_date(),
				getUserStatus.getEnd_date(), getUserStatus.getNext_billing_date(), getUserStatus.getChargedPrice(),
				getUserStatus.getActivation_date(), getUserStatus.getDeactivation_date(), getUserStatus.getSummary(),
				getUserStatus.getSubscriptionStatus(), getUserStatus.getSubscription_billing_code(),
				getUserStatus.getCharged_billing_code(), getUserStatus.getCountry(), getUserStatus.getUserSource(),
				getUserStatus.getMode(), DateUtils.convertIntToBoolean(getUserStatus.getPaid()));
	}

}
