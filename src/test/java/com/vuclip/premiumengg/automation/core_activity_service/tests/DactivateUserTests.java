package com.vuclip.premiumengg.automation.core_activity_service.tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RedisTemplateConnection;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.DeactivateUserRequestVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.DeactivateUserResponseVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.ResultVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.UserStatus;
import com.vuclip.premiumengg.automation.core_activity_service.common.utils.CASHelper;
import com.vuclip.premiumengg.automation.core_activity_service.common.utils.CASRedisUtils;
import com.vuclip.premiumengg.automation.core_activity_service.common.utils.CASUtils;
import com.vuclip.premiumengg.automation.core_activity_service.common.utils.CASValidationHelper;
import com.vuclip.premiumengg.automation.utils.AppAssert;

import io.restassured.response.Response;

public class DactivateUserTests {
	Logger logger = Log4J.getLogger("BlockUserTests");
	private String alreadyDeactivatedRedisKey;

	@BeforeClass(alwaysRun = true)
	public void setUp() {
		logger.info("Flush Redis");
		RedisTemplateConnection.getRedisConnection().getConnectionFactory().getConnection().flushAll();
	}

	@Test(groups = { "positive" }, priority = 0)
	public void verify_deactivate_existingUser() {
		try {
			logger.info("======================>Starting deactivate existing user test");
			DeactivateUserRequestVO request = CASUtils.createDeactivateMockRequestVO("1234567890", "1234567890", 1, "1",
					"12", null, "WAP");

			logger.info("Validate Response");
			Response response = CASHelper.deactivate(request);
			DeactivateUserResponseVO actualResposne = response.getBody().as(DeactivateUserResponseVO.class);
			ResultVO resultVO = ResultVO.builder().message("Success").status("SUCCESS").responseCode("SUCCESS").build();
			UserStatus userStatus = UserStatus.builder().summary("DEACTIVATION_IN_PROGRESS")
					.subscriptionStatus("DCT_INIT").productId(1).partnerId(1).userId("1234567890").msisdn("1234567890")
					.build();
			DeactivateUserResponseVO expectedResposne = CASUtils.getDeactivateMockResponse(userStatus, resultVO);
			CASValidationHelper.validateDeactivateUserResponse(actualResposne, expectedResposne);

			logger.info("Validate Redis");
			alreadyDeactivatedRedisKey = "ACTIVITY_TRANSACTION_KEY_" + request.getProductId() + "_"
					+ actualResposne.getUserStatus().getProductId() + "_" + request.getMsisdn();
			AppAssert.assertTrue(CASRedisUtils.checkKey(alreadyDeactivatedRedisKey), "Verify Key Present");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();

		}
	}

	@Test(groups = { "positive" }, priority = 1)
	public void verify_deactivate_alreadyDeactivatedUser() {
		try {
			logger.info("======================>Starting deactivate already deactivated user test");
			DeactivateUserRequestVO request = CASUtils.createDeactivateMockRequestVO("1234567890", "1234567890", 1, "1",
					"12", null, "WAP");

			logger.info("Validate Response");
			Response response = CASHelper.deactivate(request);
			DeactivateUserResponseVO actualResposne = response.getBody().as(DeactivateUserResponseVO.class);
			ResultVO resultVO = ResultVO.builder().message("Deactivation is in progress").status("FAIL")
					.responseCode("TRX08").build();
			UserStatus userStatus = null;
			DeactivateUserResponseVO expectedResposne = CASUtils.getDeactivateMockResponse(userStatus, resultVO);
			CASValidationHelper.validateDeactivateUserResponse(actualResposne, expectedResposne);

			logger.info("Validate Redis");
			AppAssert.assertTrue(CASRedisUtils.checkKey(alreadyDeactivatedRedisKey), "Verify Key Present");

		} catch (Exception e) {
			e.printStackTrace();
			AppAssert.assertTrue(false, "Test fail");

		}
	}
}
