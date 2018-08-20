package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.RabbitUtil;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author mayank.bharshiv
 */

public class SASActivationTests {
    private static Logger logger = Log4J.getLogger("SASActivationTests");
    int productId;
    int partnerId;
    private String countryCode = "IN";

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        productId = SASUtils.productId;
        partnerId = productId;
    }

    @DataProvider(name = "activationPostiveDataProvider")
    public Object[][] activationPostiveDataProvider() {
        return new Object[][]{
                // covered in sasTest{ "ACTIVATION", "ACT_INIT", "ACTIVATED", "SUCCESS",
                // "CHARGING", 101, "renewal", "OPEN" },
                // covered in sasTest{ "ACTIVATION", "ACT_INIT", "ACT_INIT", "FAILURE",
                // "CHARGING", 107, "activation", "OPEN" },

                {"ACTIVATION", "ACT_INIT", "ACT_INIT", "ERROR", "CHARGING", 108, "activation", "OPEN", "ACTIVATION_RETRY"},

                // covered in sasTest { "ACTIVATION", "ACT_INIT", "PARKING", "LOW_BALANCE",
                // "CHARGING", 111, "winback", "OPEN" },
                // Fail{ "ACTIVATION", "ACT_INIT", "ACT_INIT", "IN_PROGRESS", "CHARGING", 106,
                // "winback", "OPEN" },
                // Fail{ "ACTIVATION", "ACT_INIT", "ACT_INIT", "NOTIFICATION_WAIT", "CHARGING",
                // 106, "winback", "OPEN" }
        };

    }

    @Test(dataProvider = "activationPostiveDataProvider", groups = {"positive"})
    public void activationPositiveTest(String activityType, String previousSubscriptionState,
                                       String currentSubscriptionState, String transactionState, String eventActionType, Integer subscriptionId,
                                       String actionTable, String status, String queueName) throws Exception {

        subscriptionId = RandomUtils.nextInt(31000, 32000);

        logger.info("==================>Starting activation Positive Test  [ " + SASUtils.getTestLogMessage(productId,
                subscriptionId, eventActionType, activityType, currentSubscriptionState, transactionState) + " ]");

        RabbitUtil.purgeAllActivityQueue(productId, partnerId, countryCode);
        try {
            String schedulerActivity = actionTable;
            SASUtils.executeActivityFlow(productId, partnerId, subscriptionId, countryCode, eventActionType,
                    activityType, currentSubscriptionState, transactionState, actionTable, schedulerActivity, "OPEN",
                    "IN_PROGRESS", queueName, queueName);

        } catch (Exception e) {
            logger.error("activationPositiveTest Failed");
            e.printStackTrace();
            AppAssert.assertTrue(false);
        }
    }

    @DataProvider(name = "activationNegativeDataProvider")
    public Object[][] activationNegativeDataProvider() {
        return new Object[][]{
                {"ACTIVATION", "ACT_INIT", "ACTIVATED", "LOW_BALANCE", "CHARGING", 102, "renewal", "OPEN"},
                {"ACTIVATION", "ACT_INIT", "ACTIVATED", "FAILURE", "CHARGING", 103, "renewal", "OPEN"},
                {"ACTIVATION", "ACT_INIT", "ACTIVATED", "ERROR", "CHARGING", 104, "renewal", "OPEN"},
                {"ACTIVATION", "ACT_INIT", "ACT_INIT", "SUCCESS", "CHARGING", 105, "activation", "OPEN"},
                {"ACTIVATION", "ACT_INIT", "PARKING", "SUCCESS", "CHARGING", 109, "winback", "OPEN"},
                {"ACTIVATION", "ACT_INIT", "PARKING", "FAILURE", "CHARGING", 110, "winback", "OPEN"},
                {"ACTIVATION", "ACT_INIT", "ACT_INIT", "LOW_BALANCE", "CHARGING", 106, "winback", "OPEN"},
                {"ACTIVATION", "ACT_INIT", "PARKING", "ERROR", "CHARGING", 112, "winback", "OPEN"}

        };
    }

    @Test(dataProvider = "activationNegativeDataProvider", groups = {"positive"})
    public void activationNegativeTest(String activityType, String previousSubscriptionState,
                                       String currentSubscriptionState, String transactionState, String actionType, Integer subscriptionId,
                                       String actionTable, String status) throws Exception {

        subscriptionId = RandomUtils.nextInt(53000, 54000);

        logger.info("==================>Starting activation Negative Data Provider  [ "
                + SASUtils.getTestLogMessage(productId, subscriptionId, actionType, activityType,
                currentSubscriptionState, transactionState)
                + " ]");

        SASValidationHelper.negativeFlow(productId, partnerId, activityType, currentSubscriptionState, transactionState,
                actionType, subscriptionId);

    }

}
