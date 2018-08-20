package com.vuclip.premiumengg.automation.ad_network_service.tests;

import com.vuclip.premiumengg.automation.ad_network_service.common.models.Message;
import com.vuclip.premiumengg.automation.ad_network_service.common.utils.*;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DateTimeUtil;
import com.vuclip.premiumengg.automation.utils.TimeUnitEnum;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.Test;

import java.math.BigInteger;

/**
 * @author mayank.bharshiv
 */
public class ChurnCallSameDayTest {

    @Test(groups = {"positive"})
    public void churnCallSameDayTest() throws Exception {
        Log4J.getLogger().info("===============================>Starting test case " + ChurnCallSameDayTest.class);

        int productId = ANSTestContext.productId;
        String billingCode = ANSTestContext.billingCode;
        BigInteger nBD = DateTimeUtil.getDateByAddingValidity(DateTimeUtil.getCurrentDateInGMT(), 20,
                TimeUnitEnum.DAY.name());
        String userID = RandomStringUtils.random(10, false, true);
        long subscriptionId = RandomUtils.nextInt(100, 200);
        String requestParamVal = RandomStringUtils.random(10, true, true);
        String transactionId = "TRX_" + RandomStringUtils.random(10, true, true);
        String sourceIdentifier = "D_KIM" + RandomUtils.nextInt(100, 900);
        String userSource = sourceIdentifier + "_" + RandomUtils.nextInt(100, 900);
        ANSTestContext.requestParamName = "voluum_tid" + RandomStringUtils.random(5, true, true);

        ANSValidationHelper.validate_ans_api_response(
                ANSHelper.saveAdNetwork(ANSTestContext.adNetworkId, ANSTestContext.requestParamName, sourceIdentifier));

        Message message = ANSUtils.generateMessageForQueue(productId, userID, billingCode, "50.0", "CONSENT", "ACTIVATION", "OPEN",
                "SUCCESS", subscriptionId, "ActivityEvent", nBD,
                ANSTestContext.requestParamName + "=" + requestParamVal, requestParamVal, transactionId, userSource);
        ANSMessageHelper.addMessageToQueue(message);

        AppAssert.assertTrue(ANSRedisUtils.keyPresent(transactionId), "Check Key Present");

        message = ANSUtils.generateMessageForQueue(productId, userID, billingCode, "50.0", "CONSENT", "ACTIVATION", "CONFIRMED",
                "SUCCESS", subscriptionId, "ActivityEvent", nBD,
                null, null, transactionId, userSource);
        ANSMessageHelper.addMessageToQueue(message);

        ANSValidationHelper.verifyActivityRecordPresent(productId, productId, transactionId);
        AppAssert.assertTrue(!ANSRedisUtils.keyNotPresent(transactionId), "Check Key not Present");

        message = ANSUtils.generateMessageForQueue(productId, userID, billingCode, "50.0", "CHARGING", "ACTIVATION", "SUCCESS",
                "SUCCESS", subscriptionId, "ActivityEvent", nBD,
                null, null, transactionId, userSource);
        ANSMessageHelper.addMessageToQueue(message);
        ANSValidationHelper.validateUserAdnotificationTable(productId, productId, transactionId,
                "ad_notification_status", "SUCCESS");

        message = ANSUtils.generateMessageForQueue(productId, userID, billingCode, "50.0", "PROCESS_DEACTIVATE", "DEACTIVATION",
                "SUCCESS", "SUCCESS", subscriptionId, "ActivityEvent", nBD,
                null, null, transactionId, userSource);
        ANSMessageHelper.addMessageToQueue(message);
        ANSValidationHelper.validateUserAdnotificationTable(productId, productId, transactionId, "churn_ad_notification_status", "SUCCESS");

    }
}
