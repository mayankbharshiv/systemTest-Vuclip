package com.vuclip.premiumengg.automation.ad_network_service.tests;

import java.math.BigInteger;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.ad_network_service.common.models.Message;
import com.vuclip.premiumengg.automation.ad_network_service.common.utils.ANSHelper;
import com.vuclip.premiumengg.automation.ad_network_service.common.utils.ANSMessageHelper;
import com.vuclip.premiumengg.automation.ad_network_service.common.utils.ANSRedisUtils;
import com.vuclip.premiumengg.automation.ad_network_service.common.utils.ANSTestContext;
import com.vuclip.premiumengg.automation.ad_network_service.common.utils.ANSUtils;
import com.vuclip.premiumengg.automation.ad_network_service.common.utils.ANSValidationHelper;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.DateTimeUtil;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.TimeUnitEnum;
import com.vuclip.premiumengg.automation.utils.AppAssert;

public class ConsentFailureTests {

	@Test(groups = { "positive" })
	public void consentFailureTests() throws Exception {
		Log4J.getLogger().info("===============================>Starting test case "+ConsentFailureTests.class);

		int productId = ANSTestContext.productId;
		int partnerId = productId;
		String billingCode = ANSTestContext.billingCode;
		BigInteger nBD = DateTimeUtil.getDateByAddingValidity(DateTimeUtil.getCurrentDateInGMT(), 20,
				TimeUnitEnum.DAY.name());
		String userID = RandomStringUtils.random(10, false, true);
		long subscriptionId = RandomUtils.nextInt(100, 200);
		String requestParamVal = RandomStringUtils.random(10, true, true);
		String transactionId = "TRX_" + RandomStringUtils.random(10, true, true);
		String sourceIdentifier = "D_KIM"+RandomUtils.nextInt(100, 900);
		String userSource=sourceIdentifier+"_"+RandomUtils.nextInt(100, 900);
		
		ANSTestContext.requestParamName = "voluum_tid" + RandomStringUtils.random(5, true, true);

		ANSValidationHelper.validate_ans_api_response(
				ANSHelper.saveAdNetwork(ANSTestContext.adNetworkId, ANSTestContext.requestParamName,sourceIdentifier));

		Message message = ANSUtils.generateMessageForQueue(productId, userID, billingCode, "50.0", "CONSENT", "ACTIVATION", "OPEN",
				"SUCCESS", subscriptionId, "ActivityEvent", nBD,
				ANSTestContext.requestParamName + "=" + requestParamVal,requestParamVal, transactionId,userSource);
		ANSMessageHelper.addMessageToQueue(message);
		AppAssert.assertTrue(ANSRedisUtils.keyPresent(transactionId), "Check Key Present");

		 message = ANSUtils.generateMessageForQueue(productId, userID, billingCode, "50.0", "CONSENT", "ACTIVATION", "FAILURE",
				"FAILURE", subscriptionId, "ActivityEvent", nBD,
				null, null, transactionId,userSource);
		ANSMessageHelper.addMessageToQueue(message);
		AppAssert.assertTrue(ANSRedisUtils.keyPresent(transactionId), "Check Key Present");		
		ANSValidationHelper.verifyNoActivityRecordPresent(productId, partnerId, transactionId);

	}
}
