package com.vuclip.premiumengg.automation.ad_network_service.tests;

import java.math.BigInteger;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.ad_network_service.common.models.Message;
import com.vuclip.premiumengg.automation.ad_network_service.common.models.SaveProduct;
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

import io.restassured.response.Response;

public class FreeTrialSuccessfulAdnotificationTest {

    @Test(groups = {"positive"})
    public void freeTrialSuccessfulAdnotificationTest() throws Exception {
        Log4J.getLogger().info(
                "===============================>Starting test case " + FreeTrialSuccessfulAdnotificationTest.class);

        int productId = RandomUtils.nextInt(33000, 34500);
        int partnerId = productId;
        int adNetworkId = productId;
        String billingCode = "BC" + RandomUtils.nextInt(10, 90);
        BigInteger nBD = DateTimeUtil.getDateByAddingValidity(DateTimeUtil.getCurrentDateInGMT(), 20,
                TimeUnitEnum.DAY.name());
        String userID = RandomStringUtils.random(10, false, true);
        long subscriptionId = RandomUtils.nextInt(100, 200);
        String requestParamVal = RandomStringUtils.random(10, true, true);
        String transactionId = "TRX_" + RandomStringUtils.random(10, true, true);
        String sourceIdentifier = "D_KIM" + RandomUtils.nextInt(100, 900);
        String userSource = sourceIdentifier + "_" + RandomUtils.nextInt(100, 900);
        boolean isSaveProductSuccess = false;

        int count = 0;
        while (isSaveProductSuccess == false && count <= 10) {
            productId = RandomUtils.nextInt(50000, 66500);
            partnerId = productId;
            adNetworkId = productId;
            billingCode = "BC" + partnerId;
            nBD = DateTimeUtil.getDateByAddingValidity(DateTimeUtil.getCurrentDateInGMT(), 20, TimeUnitEnum.DAY.name());
            userID = RandomStringUtils.random(10, false, true);
            subscriptionId = RandomUtils.nextInt(9000, 10000);
            requestParamVal = RandomStringUtils.random(5, true, true);
            transactionId = "TRX_" + RandomStringUtils.random(5, true, true);
            ANSTestContext.requestParamName = "voluum_tid" + RandomStringUtils.random(4, true, true);
            sourceIdentifier = "D_KIM" + RandomUtils.nextInt(100, 900);
            userSource = sourceIdentifier + "_" + RandomUtils.nextInt(100, 900);

            SaveProduct saveProduct = ANSUtils.generateSaveProductConfig(productId, "VIUTEST", partnerId, adNetworkId,
                    billingCode, ANSTestContext.countryCode, 100, 100, 100, true, 10);

            Response s = ANSHelper.saveProduct(saveProduct);

            if (s.getStatusCode() == 200)
                isSaveProductSuccess = true;
            count++;
        }
        ANSValidationHelper
                .validate_ans_api_response(ANSHelper.saveCountry(ANSTestContext.countryCode, ANSTestContext.timezone));
        ANSValidationHelper.validate_ans_api_response(
                ANSHelper.saveAdNetwork(adNetworkId, ANSTestContext.requestParamName, sourceIdentifier));

        Message message = ANSUtils.generateMessageForQueue(productId, userID, billingCode, "50.0", "CONSENT",
                "ACTIVATION", "OPEN", "SUCCESS", subscriptionId, nBD,
                ANSTestContext.requestParamName + "=" + requestParamVal, requestParamVal, transactionId, userSource);
        ANSMessageHelper.addMessageToQueue(message);
        AppAssert.assertTrue(ANSRedisUtils.keyPresent(transactionId), "Check Key Present");

        message = ANSUtils.generateMessageForQueue(productId, userID, billingCode, "50.0", "CONSENT", "ACTIVATION",
                "CONFIRMED", "SUCCESS", subscriptionId, nBD, null, null, transactionId, userSource);
        ANSMessageHelper.addMessageToQueue(message);
        AppAssert.assertTrue(!ANSRedisUtils.keyNotPresent(transactionId), "Check Key not Present");
        ANSValidationHelper.verifyActivityRecordPresent(productId, partnerId, transactionId);

        message = ANSUtils.generateMessageForQueue(productId, userID, billingCode, "50.0", "CHARGING", "ACTIVATION",
                "SUCCESS", "SUCCESS", subscriptionId, nBD, null, null, transactionId, userSource);
        ANSMessageHelper.addMessageToQueue(message);
        ANSValidationHelper.validateUserAdnotificationTable(productId, partnerId, transactionId,
                "ad_notification_status", "SUCCESS");
    }
}
