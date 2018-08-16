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
import com.vuclip.premiumengg.automation.subscription_service.common.models.BlockStatusResponse;
import com.vuclip.premiumengg.automation.subscription_service.common.models.VerifyBlockUserStatusData;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.SHelper;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.SRedisUtils;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.SUtils;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.TestDataCreator;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;

import io.restassured.response.Response;

public class VerifyBlockUserStatus {
	private static Logger logger = Log4J.getLogger(SubscriptionBlockTests.class.toGenericString());
	Map<String, String> query = new HashMap<String, String>();

	@DataProvider(name = "subscriptionBlockTestBlackListed")
	public Iterator<Object[]> subscriptionBlockTestBlackListed() {

		ArrayList<VerifyBlockUserStatusData> verifyBlockUserStatusData = new ArrayList<VerifyBlockUserStatusData>();
		verifyBlockUserStatusData.add(VerifyBlockUserStatusData.BLACKLIST);
		verifyBlockUserStatusData.add(VerifyBlockUserStatusData.NOTBLACKLIST);
		Collection<Object[]> dp = new ArrayList<Object[]>();
		for (VerifyBlockUserStatusData sbData : verifyBlockUserStatusData) {
			dp.add(new Object[] { sbData });
		}
		return dp.iterator();
	}

	/**
	 * Subscription Block Tests for Blacklisting/Cooldown User Test
	 */

	@Test(dataProvider = "subscriptionBlockTestBlackListed", groups = { "positive" }, priority = 1)
	public void subscriptionBlockTestBlackListed(VerifyBlockUserStatusData verifyBlockUserStatusData) throws Exception {

		logger.info("Starting ================> Subscription Block Tests for BlackList");

		String user_sub_auth_key = TestDataCreator.createSubscriptionBlockUserStatus(verifyBlockUserStatusData);
		AppAssert.assertTrue(SRedisUtils.checkKey(user_sub_auth_key));

		logger.info("Check Key Present in Redis for Blocked User");
		String blockedUSerKey = "USERID_" + verifyBlockUserStatusData.getProduct_id() + "_"
				+ verifyBlockUserStatusData.getPartner_id() + "_" + verifyBlockUserStatusData.getCountry() + "_"
				+ verifyBlockUserStatusData.getUser_id();
		AppAssert.assertTrue(SRedisUtils.checkKey(blockedUSerKey));

		logger.info("Validate Blacklist User Status");
		query.put("userId", verifyBlockUserStatusData.getUser_id());
		query.put("productId", String.valueOf(verifyBlockUserStatusData.getProduct_id()));
		query.put("partnerId", String.valueOf(verifyBlockUserStatusData.getPartner_id()));
		query.put("country", verifyBlockUserStatusData.getCountry());
		Response blockStatusResponse = SHelper.subscriptionBlockStatus(query);
		BlockStatusResponse blockStatus = blockStatusResponse.getBody().as(BlockStatusResponse.class);
		AppAssert.assertEqual(blockStatus.getBlockSummary(), verifyBlockUserStatusData.getBlockListSummary());

	}

	@DataProvider(name = "subscriptionBlockTestNew")
	public Iterator<Object[]> subscriptionBlockTestNew() {

		ArrayList<VerifyBlockUserStatusData> verifyBlockUserStatusData = new ArrayList<VerifyBlockUserStatusData>();
		verifyBlockUserStatusData.add(VerifyBlockUserStatusData.BLACKLISTNEWUSER);
		Collection<Object[]> dp = new ArrayList<Object[]>();
		for (VerifyBlockUserStatusData sbData : verifyBlockUserStatusData) {
			dp.add(new Object[] { sbData });
		}
		return dp.iterator();
	}

	/**
	 * Subscription Block Tests for Blacklisting/Cooldown User Test
	 */

	@Test(dataProvider = "subscriptionBlockTestNew", groups = { "positive" })
	public void subscriptionBlockTestNew(VerifyBlockUserStatusData verifyBlockUserStatusData) throws Exception {

		logger.info("Starting ================> Subscription Block Tests for BlackList Status New User");

		logger.info("Validate Blacklist User Status New");
		query.put("userId", verifyBlockUserStatusData.getUser_id());
		query.put("productId", String.valueOf(verifyBlockUserStatusData.getProduct_id()));
		query.put("partnerId", String.valueOf(verifyBlockUserStatusData.getPartner_id()));
		query.put("country", verifyBlockUserStatusData.getCountry());
		Response blockStatusResponse = SHelper.subscriptionBlockStatus(query);
		BlockStatusResponse blockStatus = blockStatusResponse.getBody().as(BlockStatusResponse.class);
		AppAssert.assertEqual(blockStatus.getBlockSummary(), verifyBlockUserStatusData.getBlockListSummary());

	}

	@BeforeClass(alwaysRun = true)
	public void cleanUp() {
		DBUtils.cleanTable("user_subscription",
				"product_id =" + SUtils.productId + " and partner_id =" + SUtils.productId);
		DBUtils.cleanTable("blocked_user", "product_id =" + SUtils.productId + " and partner_id =" + SUtils.productId);

	}
}
