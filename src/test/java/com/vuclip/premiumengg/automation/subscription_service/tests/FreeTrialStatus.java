package com.vuclip.premiumengg.automation.subscription_service.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.subscription_service.common.models.FreeTrialUserData;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.SHelper;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.SUtils;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.SValidationHelper;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;

import io.restassured.response.Response;

public class FreeTrialStatus {

	private static Logger logger = Log4J.getLogger("FreeTrialStatus");
	Map<String, String> query = new HashMap<String, String>();

	@Test(groups = { "positive" })
	public void getFreeTrialNewUser() throws Exception {

		logger.info("Starting ================> Free Trial Status New User Test");
		query.put("userId", "8180817044");
		query.put("productId", String.valueOf(SUtils.productId));
		query.put("partnerId", String.valueOf(SUtils.productId));
		query.put("country", "MY");
		Response response = SHelper.getFreeTrialStatus(query);
		SValidationHelper.validate_ss_api_response(response);
		AppAssert.assertTrue(response.then().extract().body().path("freeTrialEligibility").equals(true));

	}

	@DataProvider(name = "insertRecordFreeTrialValidUser")
	public Iterator<Object[]> insertRecordFreeTrialValidUser() {

		ArrayList<FreeTrialUserData> insertRecordFreeTrialInvalidUser = new ArrayList<FreeTrialUserData>();
		insertRecordFreeTrialInvalidUser.add(FreeTrialUserData.VALIDUSERFREE);
		// ***BUGGER***//insertRecordFreeTrialInvalidUser.add(FreeTrialUserData.INVALIDUSERFREE);
		Collection<Object[]> dp = new ArrayList<Object[]>();
		for (FreeTrialUserData sbData : insertRecordFreeTrialInvalidUser) {
			dp.add(new Object[] { sbData });
		}
		return dp.iterator();
	}

	@Test(dataProvider = "insertRecordFreeTrialValidUser", groups = { "positive" })
	public void getFreeTrialValidUser(FreeTrialUserData freeTrialUserData) throws Exception {

		logger.info("Starting ================> Free Trial Status Valid/Invalid User Test====>"
				+ freeTrialUserData.toString());

		int id = freeTrialUserData.getId();
		String created_on = freeTrialUserData.getCreated_on();
		String last_free_trial_date = freeTrialUserData.getLast_free_trial_date();
		int availed_free_trial_count = freeTrialUserData.getAvailed_free_trial_count();
		String last_free_trial_billing_code = freeTrialUserData.getLast_free_trial_billing_code();
		int product_id = freeTrialUserData.getProduct_id();
		int partner_id = freeTrialUserData.getPartner_id();
		String user_id = freeTrialUserData.getUser_id();
		boolean expectedResult = freeTrialUserData.getExpectedResult();

		SUtils.generateFreeTrialTableData(id, created_on, last_free_trial_date, availed_free_trial_count,
				last_free_trial_billing_code, product_id, partner_id, user_id);

		query.put("userId", user_id);
		query.put("productId", String.valueOf(product_id));
		query.put("partnerId", String.valueOf(partner_id));
		query.put("country", freeTrialUserData.getCountry());
		Response response = SHelper.getFreeTrialStatus(query);
		SValidationHelper.validate_ss_api_response(response);

		AppAssert.assertTrue(response.then().extract().body().path("freeTrialEligibility").equals(expectedResult));

	}

	@BeforeClass(alwaysRun = true)
	public void cleanUp() {
		DBUtils.cleanTable("free_trial_history",
				"product_id =" + SUtils.productId + " and partner_id =" + SUtils.productId);
	}
}
