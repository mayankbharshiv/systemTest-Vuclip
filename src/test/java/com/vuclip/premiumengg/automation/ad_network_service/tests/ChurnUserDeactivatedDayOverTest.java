package com.vuclip.premiumengg.automation.ad_network_service.tests;

import com.vuclip.premiumengg.automation.ad_network_service.common.models.Message;
import com.vuclip.premiumengg.automation.ad_network_service.common.utils.ANSMessageHelper;
import com.vuclip.premiumengg.automation.ad_network_service.common.utils.ANSRedisUtils;
import com.vuclip.premiumengg.automation.ad_network_service.common.utils.ANSTestContext;
import com.vuclip.premiumengg.automation.ad_network_service.common.utils.ANSUtils;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import com.vuclip.premiumengg.automation.utils.DateTimeUtil;
import com.vuclip.premiumengg.automation.utils.TimeUnitEnum;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.Test;

import java.math.BigInteger;

/**
 * @author mayank.bharshiv
 */

public class ChurnUserDeactivatedDayOverTest {

    @Test(groups = {"pending", "bug"})
    public void churnUserDeactivatedDayOverTest() throws Exception {
        Log4J.getLogger().info("===============================>Starting test case " + ChurnUserDeactivatedDayOverTest.class);

        int productId = ANSTestContext.productId;
        String billingCode = ANSTestContext.billingCode;
        BigInteger nBD = DateTimeUtil.getDateByAddingValidity(DateTimeUtil.getCurrentDateInGMT(), 20,
                TimeUnitEnum.DAY.name());
        String userID = RandomStringUtils.random(10, false, true);
        long subscriptionId = RandomUtils.nextInt(100, 200);
        String addRow = "'" + "TRX_0bWIjr9XFh" + "'" + "," + 6561 + "," + "'" + "voluum_tidbNsEP=Xk2sPHJp8b" + "'" + "," + "'" + "SUCCESS" + "'" + "," + "'" + "NULL" + "'" + "," + "'" + "Xk2sPHJp8b" + "'" + "," + 0 + "," + "'" + "3544588762" + "'" + "," + 6561 + "," + 6561 + "," + 0 + "," + 105 + "," + "'" + "3544588762" + "'" + "," + "'" + "D_KIM114_188" + "'";
        String requestParamVal = RandomStringUtils.random(10, true, true);
        String userSource = "D_KIM" + RandomUtils.nextInt(100, 900);
        String transactionId = "CHURN_TRX_" + RandomStringUtils.random(8, true, true);

        //TODO what to add??
        DBUtils.addRecordInTable("user_adnotification", addRow);

        Message message = ANSUtils.generateMessageForQueue(productId, userID, billingCode, "50.0", "CONSENT", "ACTIVATION", "OPEN",
                "SUCCESS", subscriptionId, "ActivityEvent", nBD,
                ANSTestContext.requestParamName + "=" + requestParamVal, requestParamVal, transactionId, userSource);
        ANSMessageHelper.addMessageToQueue(message);
        System.out.println(
                "ANSRedisUtils.isKeyPresent(transactionId)   ===== >" + ANSRedisUtils.keyPresent(transactionId));

        message = ANSUtils.generateMessageForQueue(productId, userID, billingCode, "50.0", "CONSENT", "ACTIVATION", "CONFIRMED",
                "SUCCESS", subscriptionId, "ActivityEvent", nBD,
                ANSTestContext.requestParamName + "=" + requestParamVal, requestParamVal, transactionId, userSource);
        ANSMessageHelper.addMessageToQueue(message);
        System.out.println(
                "ANSRedisUtils.isKeyPresent(transactionId)   ===== >" + ANSRedisUtils.keyPresent(transactionId));

        message = ANSUtils.generateMessageForQueue(productId, userID, billingCode, "50.0", "CHARGING", "ACTIVATION", "SUCCESS",
                "SUCCESS", subscriptionId, "ActivityEvent", nBD,
                ANSTestContext.requestParamName + "=" + requestParamVal, requestParamVal, transactionId, userSource);
        ANSMessageHelper.addMessageToQueue(message);

        message = ANSUtils.generateMessageForQueue(productId, userID, billingCode, "50.0", "PROCESS_DEACTIVATE", "DEACTIVATION", "SUCCESS",
                "SUCCESS", subscriptionId, "ActivityEvent", nBD,
                ANSTestContext.requestParamName + "=" + requestParamVal, requestParamVal, transactionId, userSource);
        ANSMessageHelper.addMessageToQueue(message);
        System.out.println(
                "ANSRedisUtils.isKeyPresent(transactionId)   ===== >" + ANSRedisUtils.keyPresent(transactionId));

    }
}
