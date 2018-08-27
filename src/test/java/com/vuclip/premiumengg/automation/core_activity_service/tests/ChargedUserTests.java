package com.vuclip.premiumengg.automation.core_activity_service.tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RedisTemplateConnection;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.ChargedUserRequestVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.ChargedUserResponseVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.ResultVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.utils.CASHelper;
import com.vuclip.premiumengg.automation.core_activity_service.common.utils.CASRedisUtils;
import com.vuclip.premiumengg.automation.core_activity_service.common.utils.CASUtils;
import com.vuclip.premiumengg.automation.core_activity_service.common.utils.CASValidationHelper;
import com.vuclip.premiumengg.automation.utils.AppAssert;

import io.restassured.response.Response;

public class ChargedUserTests {

	Logger logger = Log4J.getLogger("ChargedUserTests");

	@BeforeClass(alwaysRun=true)
	public void setUp() {
		logger.info("Flush Redis");
		RedisTemplateConnection.getRedisConnection().getConnectionFactory().getConnection().flushAll();
	}

	@Test(groups = { "positive" })
	public void verify_existing_chargedUser() {
		try {
			String activationConsentUrl = "http://35.192.236.11:8080/ubsdemo/consent.jsp?billingCode=b1&cgImageUrl=http%3A%2F%2Fwww.google.com%2F&msisdn=997505060&url=http%3A%2F%2F64.71.156.241%2Fbaas%2FCGController%2FconsentParser%2F58%3Ftransid%3DTX20171218102625772553&transactionId=TRANSACTION_KEY201806191536027";
			logger.info("================> Starting Charged User for ACT INIT User Test");
			ChargedUserRequestVO request = CASUtils.createMockRequestVO("1", "12",
					"http://64.71.156.241/baas/CGController/consentParser/58?transid=TX20171218102625772553",
					"http://www.google.com/", "http://192.168.250.72:8080/home", "1234567890", "1234567890", "MH", "en",
					"123", "123456", "WAP", "b1", "DNP890", "direct");

			logger.info("Validate Response");
			Response response = CASHelper.chargedUser(request);
			ChargedUserResponseVO actualResposne = response.getBody().as(ChargedUserResponseVO.class);
			ResultVO resultVO = ResultVO.builder().message("200").status("SUCCESS").responseCode("200").build();
			ChargedUserResponseVO expectedResposne = CASUtils.getChargedUSerMockResponse(activationConsentUrl, resultVO, null);
			CASValidationHelper.validateChargedUserResponse(actualResposne, expectedResposne);

			logger.info("Validate Redis");
			AppAssert.assertTrue(CASRedisUtils.checkKey(actualResposne.getBillingTransactionId()),
					"Verify Key Present");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

	@Test(groups = { "positive" })
	public void verify_new_chargedUser() {
		try {
			String activationConsentUrl = "http://35.192.236.11:8080/ubsdemo/consent.jsp?billingCode=b1&cgImageUrl=http%3A%2F%2Fwww.google.com%2F&msisdn=997505060&url=http%3A%2F%2F64.71.156.241%2Fbaas%2FCGController%2FconsentParser%2F58%3Ftransid%3DTX20171218102625772553&transactionId=TRANSACTION_KEY201806191536027";
			logger.info("================> Starting Charged User for New User Test");
			ChargedUserRequestVO request = CASUtils.createMockRequestVO("1", "12",
					"http://64.71.156.241/baas/CGController/consentParser/58?transid=TX20171218102625772553",
					"http://www.google.com/", "http://192.168.250.72:8080/home", "1234567891", "1234567891", "MH", "en",
					"123", "123456", "WAP", "b1", "DNP891", "direct");

			logger.info("Validate Response");
			Response response = CASHelper.chargedUser(request);
			ChargedUserResponseVO actualResposne = response.getBody().as(ChargedUserResponseVO.class);
			ResultVO resultVO = ResultVO.builder().message("200").status("SUCCESS").responseCode("200").build();
			ChargedUserResponseVO expectedResposne = CASUtils.getChargedUSerMockResponse(activationConsentUrl, resultVO, null);
			CASValidationHelper.validateChargedUserResponse(actualResposne, expectedResposne);

			logger.info("Validate Redis");
			AppAssert.assertTrue(CASRedisUtils.checkKey(actualResposne.getBillingTransactionId()),
					"Verify Key Present");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

	@Test(groups = { "positive" })
	public void verify_activated_chargedUser() {
		try {
			logger.info("================> Starting Charged User for Activated User Test");
			ChargedUserRequestVO request = CASUtils.createMockRequestVO("1", "12",
					"http://64.71.156.241/baas/CGController/consentParser/58?transid=TX20171218102625772553",
					"http://www.google.com/", "http://192.168.250.72:8080/home", "1234567892", "1234567892", "MH", "en",
					"123", "123456", "WAP", "b1", "DNP892", "direct");

			logger.info("Validate Response");
			Response response = CASHelper.chargedUser(request);
			ChargedUserResponseVO actualResposne = response.getBody().as(ChargedUserResponseVO.class);
			ResultVO resultVO = ResultVO.builder().message("User already subscribed.").status("FAIL")
					.responseCode("USR01").build();
			ChargedUserResponseVO expectedResposne = CASUtils.getChargedUSerMockResponse(null, resultVO, null);
			CASValidationHelper.validateChargedUserResponse(actualResposne, expectedResposne);

			logger.info("Validate Redis");
			AppAssert.assertTrue(!CASRedisUtils.checkKey(actualResposne.getBillingTransactionId()),
					"Verify Key Not Present");
		} catch (Exception e) {
			e.printStackTrace();
			AppAssert.assertTrue(false, "Test fail");
		}

	}

}
