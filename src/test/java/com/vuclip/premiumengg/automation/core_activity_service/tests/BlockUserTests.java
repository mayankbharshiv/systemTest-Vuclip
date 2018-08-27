package com.vuclip.premiumengg.automation.core_activity_service.tests;


import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RedisTemplateConnection;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.BlockedUserData;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.BlockedUserRequestVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.BlockedUserResponseVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.ResultVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.UserStatus;
import com.vuclip.premiumengg.automation.core_activity_service.common.utils.CASHelper;
import com.vuclip.premiumengg.automation.core_activity_service.common.utils.CASRedisUtils;
import com.vuclip.premiumengg.automation.core_activity_service.common.utils.CASUtils;
import com.vuclip.premiumengg.automation.core_activity_service.common.utils.CASValidationHelper;
import com.vuclip.premiumengg.automation.utils.AppAssert;

import io.restassured.response.Response;

public class BlockUserTests {
	Logger logger = Log4J.getLogger("BlockUserTests");

	@BeforeClass(alwaysRun = true)
	public void setUp() {
		logger.info("Flush Redis");
		RedisTemplateConnection.getRedisConnection().getConnectionFactory().getConnection().flushAll();
	}

	@Test(groups = { "positive" })
	public void verify_blocked_userStatus() throws Exception {
		logger.info("======================>Starting Blocked Active User Status Tests");
		try {
			BlockedUserRequestVO request = CASUtils.createBlockedUserMockRequestVO("1234567890", "1234567890", 1, 1,
					"IN");
			logger.info("Validate Response");
			Response response = CASHelper.blocked(request);
			BlockedUserResponseVO actualResposne = response.getBody().as(BlockedUserResponseVO.class);
			ResultVO resultVO = ResultVO.builder().message("success").status("SUCCESS").responseCode("200").build();
			BlockedUserData blockedUserData = BlockedUserData.builder().blockType("BLACKLIST").build();
			UserStatus userStatus = UserStatus.builder().summary("DEACTIVATION_IN_PROGRESS")
					.subscriptionStatus("DCT_INIT").productId(1).partnerId(1).userId("1234567890").msisdn("1234567890")
					.build();
			BlockedUserResponseVO expectedResposne = CASUtils.getBlokcedMockResponse(userStatus, blockedUserData,
					resultVO);
			CASValidationHelper.validateBlockedUserResponse(actualResposne, expectedResposne);

			logger.info("Validate Redis");
			AppAssert.assertTrue(
					CASRedisUtils.checkKey("ACTIVITY_TRANSACTION_KEY_" + request.getPartnerId() + "_"
							+ request.getProductId() + "_" + request.getUserDetails().getMsisdn()),
					"Verify Key Present");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

	@Test(groups = { "positive" })
	public void verify_blocked_newUserStatus() throws Exception {
		logger.info("======================>Starting Blocked New User Status Tests");
		try {
			BlockedUserRequestVO request = CASUtils.createBlockedUserMockRequestVO("1234567891", "1234567891", 1, 1,
					"IN");
			logger.info("Validate Response");
			Response response = CASHelper.blocked(request);
			BlockedUserResponseVO actualResposne = response.getBody().as(BlockedUserResponseVO.class);
			ResultVO resultVO = ResultVO.builder().message("success").status("SUCCESS").responseCode("200").build();
			BlockedUserData blockedUserData = BlockedUserData.builder().blockType("BLACKLIST").build();
			UserStatus userStatus = null;
			BlockedUserResponseVO expectedResposne = CASUtils.getBlokcedMockResponse(userStatus, blockedUserData,
					resultVO);
			CASValidationHelper.validateBlockedUserResponse(actualResposne, expectedResposne);

			logger.info("Validate Redis");
			AppAssert.assertTrue(
					CASRedisUtils.checkKey("ACTIVITY_TRANSACTION_KEY_" + request.getPartnerId() + "_"
							+ request.getProductId() + "_" + request.getUserDetails().getMsisdn()),
					"Verify Key Present");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

	}
}
