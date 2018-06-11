package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import com.vuclip.premiumengg.automation.common.Log4J;
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

public class SASFreeTrailRenewalTests {
    private static Logger logger = Log4J.getLogger("FreeTrialRenewalTests");
    int productId;
    int partnerId;
    private String countryCode = "IN";

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        productId = SASUtils.productId;
        partnerId = productId;
    }

    @DataProvider(name = "freeTrailRenewalPostiveDataProvider")
    public Object[][] freeTrailRenewalPostiveDataProvider() {
        return new Object[][]{

                // needs to check when env gets updated { "FREETRIAL_RENEWAL", "ACTIVATED",
                // "IN_PROGRESS", "CHARGING", 102, "free_trail","OPEN" },
                // needs to check when env gets updated { "FREETRIAL_RENEWAL", "SUSPEND",
                // "IN_PROGRESS", "CHARGING", 103,"free_trail","OPEN" },
                // needs to check when env gets update{ "FREETRIAL_RENEWAL", "ACTIVATED",
                // "SUCCESS", "CHARGING", 110, "renewal", "OPEN" },
                // not in develop branch { "FREETRIAL_RENEWAL", "SUSPEND",
                // "NOTIFICATION_WAIT","CHARGING", 104,"zero","OPEN" },
                // not in develop branch { "FREETRIAL_RENEWAL", "ACTIVATED",
                // "NOTIFICATION_WAIT", "CHARGING", 105,"zero","OPEN" },
                {"FREETRIAL_RENEWAL", "SUSPEND", "LOW_BALANCE", "CHARGING", 101, "renewal_retry", "OPEN"},
                {"FREETRIAL_RENEWAL", "SUSPEND", "ERROR", "CHARGING", 109, "renewal_retry", "OPEN"},
                {"FREETRIAL_RENEWAL", "ACTIVATED", "ERROR", "CHARGING", 111, "renewal_retry", "OPEN"},
                {"FREETRIAL_RENEWAL", "SUSPEND", "FAILURE", "CHARGING", 112, "renewal_retry", "OPEN"}};

    }

    @Test(dataProvider = "freeTrailRenewalPostiveDataProvider", groups = {"pending"})
    public void freeTrailRenewalPostiveDataProvider(String activityType, String currentSubscriptionState,
                                                    String transactionState, String eventActionType, Integer subscriptionId, String actionTable, String status)
            throws Exception {
        subscriptionId = RandomUtils.nextInt(10000, 11000);

        logger.info("==================>Starting positive deactivation retry test  [ "
                + SASUtils.getTestLogMessage(productId, subscriptionId, eventActionType, activityType,
                currentSubscriptionState, transactionState)
                + " ]");

        try {
            String schedulerActivity = actionTable;
            String queueName = actionTable.toUpperCase();
            SASUtils.executeActivityFlow(productId, partnerId, subscriptionId, countryCode, eventActionType,
                    activityType, currentSubscriptionState, transactionState, actionTable, schedulerActivity, "OPEN",
                    "IN_PROGRESS", queueName);

        } catch (Exception e) {
            logger.error("Free Trial Renewal Positive Failed");
            e.printStackTrace();
            AppAssert.assertTrue(false);
        }

    }

    @DataProvider(name = "freeTrailRenewalNegativeDataProvider")
    public Object[][] freeTrailRenewalNegativeDataProvider() {
        return new Object[][]{
                {"FREETRIAL_RENEWAL", "SUSPEND", "SUCCESS", "CHARGING", 110, "free_trail", "OPEN"},
                {"FREETRIAL_RENEWAL", "ACTIVATED", "LOW_BALANCE", "CHARGING", 112, "renewal_retry", "OPEN"},
                {"FREETRIAL_RENEWAL", "ACTIVATED", "FAILURE", "CHARGING", 110, "renewal_retry", "OPEN"},

        };
    }

    @Test(dataProvider = "freeTrailRenewalNegativeDataProvider", groups = {"negative"})
    public void freeTrailRenewalNegativeTest(String activityType, String currentSubscriptionState,
                                             String transactionState, String actionType, Integer subscriptionId, String actionTable, String status)
            throws Exception {
        subscriptionId = RandomUtils.nextInt(13000, 14000);
        String testMessage = subscriptionId + " " + activityType + " " + " " + currentSubscriptionState + " "
                + transactionState + " " + actionType;
        logger.info("==================>Starting Negative free Trail Renewal test  [ " + testMessage + " ]");
        SASValidationHelper.negativeFlow(productId, partnerId, activityType, currentSubscriptionState, transactionState,
                actionType, subscriptionId);

    }

}
