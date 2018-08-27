package com.vuclip.premiumengg.automation.core_activity_service.tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RedisTemplateConnection;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.UnBlockedUserRequestVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.UnBlockedUserResponseVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.UnblockResponse;
import com.vuclip.premiumengg.automation.core_activity_service.common.utils.CASHelper;
import com.vuclip.premiumengg.automation.core_activity_service.common.utils.CASRedisUtils;
import com.vuclip.premiumengg.automation.core_activity_service.common.utils.CASUtils;
import com.vuclip.premiumengg.automation.core_activity_service.common.utils.CASValidationHelper;
import com.vuclip.premiumengg.automation.utils.AppAssert;

import io.restassured.response.Response;

public class UnblockUserTest {

	Logger logger = Log4J.getLogger("UnblockUserTest");

	@BeforeClass(alwaysRun = true)
	public void setUp() {
		logger.info("Flush Redis");
		RedisTemplateConnection.getRedisConnection().getConnectionFactory().getConnection().flushAll();
	}

	@Test(groups = { "positive" })
	public void verify_unblock_blockedUser() {
		try {
			logger.info("================> Starting Unblock User Test");
			UnBlockedUserRequestVO request = CASUtils.createUnblockMockRequestVO("21234567890", "21234567890", 1, 1,
					"BLACKLIST", "IN");

			logger.info("Validate Response");
			Response response = CASHelper.unblocked(request);
			UnBlockedUserResponseVO actualResposne = response.getBody().as(UnBlockedUserResponseVO.class);
			UnBlockedUserResponseVO expectedResposne = CASUtils
					.getUnBlockedUserMockResponse(new UnblockResponse(true, "success", "200"));
			CASValidationHelper.validateUnBlockedUserResponse(actualResposne, expectedResposne);

			logger.info("Validate Redis");
			AppAssert.assertTrue(!CASRedisUtils.checkKey("ACTIVITY_TRANSACTION_KEY_" + request.getPartnerId() + "_"
					+ request.getProductId() + "_" + request.getMsisdn()), "Verify Key Not Present");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

	@Test(groups = { "positive" })
	public void verify_new_blockedUser() {
		try {
			logger.info("================> Starting Unblock New User Test");
			UnBlockedUserRequestVO request = CASUtils.createUnblockMockRequestVO("91234567890", "91234567890", 1, 1,
					"BLACKLIST", "IN");

			logger.info("Validate Response");
			Response response = CASHelper.unblocked(request);
			UnBlockedUserResponseVO actualResposne = response.getBody().as(UnBlockedUserResponseVO.class);
			UnBlockedUserResponseVO expectedResposne = CASUtils
					.getUnBlockedUserMockResponse(new UnblockResponse(true, "Not Blacklisted", "AB015"));
			CASValidationHelper.validateUnBlockedUserResponse(actualResposne, expectedResposne);

			logger.info("Validate Redis");
			AppAssert.assertTrue(!CASRedisUtils.checkKey("ACTIVITY_TRANSACTION_KEY_" + request.getPartnerId() + "_"
					+ request.getProductId() + "_" + request.getMsisdn()), "Verify Key Not Present");

		} catch (Exception e) {
			e.printStackTrace();
			AppAssert.assertTrue(false, "Test fail");
		}

	}

}
