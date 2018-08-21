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
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DateTimeUtil;
import com.vuclip.premiumengg.automation.utils.TimeUnitEnum;

public class MoreThanOneRecordTests {

    @Test(groups = {"positive"})
    public void moreThanOneRecordTests() throws Exception {
        Log4J.getLogger().info("===============================>Starting test case " + MoreThanOneRecordTests.class);

        int productId = ANSTestContext.productId;
        int partnerId = productId;
        String billingCode = ANSTestContext.billingCode;
        BigInteger nBD = DateTimeUtil.getDateByAddingValidity(DateTimeUtil.getCurrentDateInGMT(), 20,
                TimeUnitEnum.DAY.name());
        String userID = RandomStringUtils.random(10, false, true);
        long subscriptionId = RandomUtils.nextInt(100, 200);
        String requestParamVal = RandomStringUtils.random(10, true, true);
        String AnotherRequestParamVal = RandomStringUtils.random(10, true, true);
        String transactionID = "TRX_" + RandomStringUtils.random(10, true, true);
        String secondTransactionId = RandomStringUtils.random(10, true, true);
        String sourceIdentifier = "D_KIM" + RandomUtils.nextInt(100, 900);
        String userSource = sourceIdentifier + "_" + RandomUtils.nextInt(100, 900);
        ANSTestContext.requestParamName = "voluum_tid" + RandomStringUtils.random(5, true, true);

        ANSValidationHelper.validate_ans_api_response(
                ANSHelper.saveAdNetwork(ANSTestContext.adNetworkId, ANSTestContext.requestParamName, sourceIdentifier));

        Message message = ANSUtils.generateMessageForQueue(productId, userID, billingCode, "50.0", "CONSENT", "ACTIVATION", "OPEN",
                "SUCCESS", subscriptionId, nBD,
                ANSTestContext.requestParamName + "=" + requestParamVal, requestParamVal, transactionID, userSource);
        ANSMessageHelper.addMessageToQueue(message);
        AppAssert.assertTrue(ANSRedisUtils.keyPresent(transactionID), "Check Key Present");

        message = ANSUtils.generateMessageForQueue(productId, userID, billingCode, "50.0", "CONSENT", "ACTIVATION", "OPEN",
                "SUCCESS", subscriptionId, nBD,
                ANSTestContext.requestParamName + "=" + AnotherRequestParamVal, requestParamVal,
                secondTransactionId, userSource);
        ANSMessageHelper.addMessageToQueue(message);
        AppAssert.assertTrue(ANSRedisUtils.keyPresent(secondTransactionId), "Check Key Present");

        message = ANSUtils.generateMessageForQueue(productId, userID, billingCode, "50.0", "CONSENT", "ACTIVATION", "CONFIRMED",
                "SUCCESS", subscriptionId, nBD, null, null, transactionID, userSource);

        ANSMessageHelper.addMessageToQueue(message);
        AppAssert.assertTrue(!ANSRedisUtils.keyNotPresent(transactionID), "Check Key not Present");
        ANSValidationHelper.verifyActivityRecordPresent(productId, partnerId, transactionID);

        message = ANSUtils.generateMessageForQueue(productId, userID, billingCode, "50.0", "CHARGING", "ACTIVATION", "SUCCESS",
                "SUCCESS", subscriptionId, nBD, null, null, transactionID, userSource);
        ANSMessageHelper.addMessageToQueue(message);
        ANSValidationHelper.validateUserAdnotificationTable(productId, partnerId, transactionID,
                "ad_notification_status", "SUCCESS");
    }
}
