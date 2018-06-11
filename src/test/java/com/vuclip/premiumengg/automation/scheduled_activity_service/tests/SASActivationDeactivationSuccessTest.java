package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author rahul.sahu
 */

public class SASActivationDeactivationSuccessTest {
    private static Logger logger = Log4J.getLogger("SASActivationDeactivationSuccessTest");
    int productId;
    int partnerId;
    private String countryCode = "IN";

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        productId = SASUtils.productId;
        partnerId = productId;
    }

    @DataProvider(name = "activationDeactivationPostiveDataProvider")
    public Object[][] activationDeactivationPostiveDataProvider() {
        return new Object[][]{
                /*
                 * FORMAT { "eventActionType", "activityType", "currentSubscriptionState",
                 * "transactionState", "actionTable", "beforeSchedularStatus",
                 * "afterSchedularStatus", "afteNewEventStatus", "queueName",
                 * "newEventActionType", "newActivityType", "newCurrentSubscriptionState",
                 * "newTransactionState", "newActionTable", "newBeforeSchedularStatus",
                 * "newAfterSchedularStatus", "newQueueName" },
                 */
                /* PASS */{"CHARGING", "ACTIVATION", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS",
                "SUCCESS", "RENEWAL", "CHARGING", "RENEWAL", "ACTIVATED", "SUCCESS", "renewal", "OPEN",
                "IN_PROGRESS", "RENEWAL"},

                /* PASS */{"CHARGING", "ACTIVATION", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS", "ERROR",
                "RENEWAL", "CHARGING", "RENEWAL", "ACTIVATED", "ERROR", "renewal_retry", "OPEN", "IN_PROGRESS",
                "RENEWAL_RETRY"},

                /* PASS */{"CHARGING", "ACTIVATION", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS",
                "FAILURE", "RENEWAL", "CHARGING", "RENEWAL", "ACTIVATED", "FAILURE", "renewal_retry", "OPEN",
                "IN_PROGRESS", "RENEWAL_RETRY"},

                /* PASS */{"CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE", "activation", "OPEN", "IN_PROGRESS",
                "SUCCESS", "ACTIVATION", "CHARGING", "ACTIVATION", "ACTIVATED", "SUCCESS", "renewal", "OPEN",
                "IN_PROGRESS", "RENEWAL"},

                /* PASSED */{"CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE", "activation", "OPEN", "IN_PROGRESS",
                "FAILURE", "ACTIVATION", "CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE", "activation", "OPEN",
                "IN_PROGRESS", "ACTIVATION"},

                /* PASSED */{"CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE", "activation", "OPEN", "IN_PROGRESS",
                "ERROR", "ACTIVATION", "CHARGING", "ACTIVATION", "ACT_INIT", "ERROR", "activation", "OPEN",
                "IN_PROGRESS", "ACTIVATION"},

                /* PASSED */{"CHARGING", "ACTIVATION", "PARKING", "LOW_BALANCE", "winback", "OPEN", "IN_PROGRESS",
                "ERROR", "WINBACK", "CHARGING", "WINBACK", "PARKING", "ERROR", "winback", "OPEN", "IN_PROGRESS",
                "WINBACK"},

                {"CHARGING", "ACTIVATION", "PARKING", "LOW_BALANCE", "winback", "OPEN", "IN_PROGRESS", "SUCCESS",
                        "WINBACK", "CHARGING", "WINBACK", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS",
                        "RENEWAL"},
                // //

                // // /* fail no pro */{ "CHARGING", "ACTIVATION", "PARKING", "LOW_BALANCE",
                // // "winback", "PARKING", "FAILURE",
                // // "FAILURE", "winback" },
                // //
                /* PASSED */{"CHARGING", "ACTIVATION", "PARKING", "LOW_BALANCE", "winback", "OPEN", "IN_PROGRESS",
                "LOW_BALANCE", "WINBACK", "CHARGING", "WINBACK", "PARKING", "LOW_BALANCE", "winback", "OPEN",
                "IN_PROGRESS", "WINBACK"},
                //
                // /* fail no processor */{ "CHARGING", "ACTIVATION", "PARKING", "LOW_BALANCE",
                // "winback", "PARKING",
                // "IN_PROGRESS", "IN_PROGRESS", "winback" },
                //
                // /* fail */{ "CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE", "activation",
                // "ACT_INIT",
                // "NOTIFICATION_WAIT", "NOTIFICATION_WAIT", "activation" },
                // /* fail */{ "CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE", "activation",
                // "ACT_INIT", "IN_PROGRESS",
                // "IN_PROGRESS", "winback" },
                // /* BUG */{ "CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE", "activation",
                // "ACT_INIT", "LOW_BALANCE",
                // "LOW_BALANCE", "winback" },
                //
                // /* BUG */{ "CHARGING", "ACTIVATION", "ACT_INIT", "ERROR", "activation",
                // "ACT_INIT", "LOW_BALANCE",
                // "LOW_BALANCE", "winback" },
                //
                // /* FAIL */{ "CHARGING", "ACTIVATION", "ACT_INIT", "IN_PROGRESS", "winback",
                // "ACT_INIT", "LOW_BALANCE",
                // "LOW_BALANCE", "winback" },
                // /* FAIL */{ "CHARGING", "ACTIVATION", "ACT_INIT", "LOW_BALANCE", "winback",
                // "ACT_INIT", "LOW_BALANCE",
                // "LOW_BALANCE", "winback" },
                //
                // /* FAIL */{ "CHARGING", "ACTIVATION", "ACT_INIT", "NOTIFICATION_WAIT",
                // "winback", "ACT_INIT",
                // "LOW_BALANCE", "LOW_BALANCE", "winback" },
                // /* FAIL processor is not configured */{ "CHARGING", "ACTIVATION",
                // "ACTIVATED", "SUCCESS", "renewal",
                // "ACTIVATED", "IN_PROGRESS", "IN_PROGRESS", "renewal_retry" },
                // /* FAIL */ { "CHARGING", "ACTIVATION", "ACTIVATED", "SUCCESS", "renewal",
                // "ACTIVATED",
                // "NOTIFICATION_WAIT", "IN_PROGRESS", "renewal_retry" },
                //
                /* PASSED */{"DEACTIVATE_CONSENT", "DEACTIVATION", "DCT_INIT", "FAILURE", "deactivation", "OPEN",
                "IN_PROGRESS", "FAILURE", "DEACTIVATION", "DEACTIVATE_CONSENT", "DEACTIVATION", "DCT_INIT",
                "FAILURE", "deactivation", "OPEN", "IN_PROGRESS", "DEACTIVATION"},
                /* PASSED */{"DEACTIVATE_CONSENT", "DEACTIVATION", "DCT_INIT", "FAILURE", "deactivation", "OPEN",
                "IN_PROGRESS", "ERROR", "DEACTIVATION", "DEACTIVATE_CONSENT", "DEACTIVATION", "DCT_INIT",
                "ERROR", "deactivation", "OPEN", "IN_PROGRESS", "DEACTIVATION"},
                // /* no entry */{ "DEACTIVATE_CONSENT", "DEACTIVATION", "DCT_INIT", "FAILURE",
                // "deactivation", "DCT_INIT",
                // "CONFIRMED", "CONFIRMED", "deactivation" },
                // /* no entry */{ "DEACTIVATE_CONSENT", "DEACTIVATION", "DCT_INIT", "FAILURE",
                // "deactivation", "DCT_INIT",
                // "IN_PROGRESS", "IN_PROGRESS", "deactivation" },
                // /* no entry */{ "DEACTIVATE_CONSENT", "DEACTIVATION", "DCT_INIT", "FAILURE",
                // "deactivation", "DCT_INIT",
                // "NOTIFICATION_WAIT", "NOTIFICATION_WAIT", "deactivation" },
                // /* no entry */{ "DEACTIVATE_CONSENT", "DEACTIVATION", "DCT_INIT", "SUCCESS",
                // "deactivation", "DCT_INIT",
                // "FAILURE", "FAILURE", "deactivation" }

                // //RENEWAL E2E
                // /* IN_Progress */{ "CHARGING", "RENEWAL", "ACTIVATED", "SUCCESS", "renewal",
                // "ACTIVATED", "SUCCESS",
                // "SUCCESS", "renewal" },
                // /* IN_Progress */{ "CHARGING", "RENEWAL", "ACTIVATED", "SUCCESS", "renewal",
                // "ACTIVATED", "FAILURE",
                // "FAILURE", "renewal_retry" },
                {"CHARGING", "RENEWAL", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS", "SUCCESS", "RENEWAL",
                        "CHARGING", "RENEWAL", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS", "RENEWAL"},

                /* Passed */{"CHARGING", "WINBACK", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS",
                "SUCCESS", "RENEWAL", "CHARGING", "RENEWAL", "ACTIVATED", "SUCCESS", "renewal", "OPEN",
                "IN_PROGRESS", "RENEWAL"},

                /* Passed */ {"CHARGING", "WINBACK", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS",
                "FAILURE", "RENEWAL", "CHARGING", "RENEWAL", "ACTIVATED", "FAILURE", "renewal_retry", "OPEN",
                "IN_PROGRESS", "RENEWAL_RETRY"},

                /* Passed */{"CHARGING", "WINBACK", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS", "ERROR",
                "RENEWAL", "CHARGING", "RENEWAL", "ACTIVATED", "ERROR", "renewal_retry", "OPEN", "IN_PROGRESS",
                "RENEWAL_RETRY"},

                /* Passed */{"CHARGING", "WINBACK", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS", "ERROR",
                "RENEWAL", "CHARGING", "RENEWAL", "SUSPEND", "ERROR", "renewal_retry", "OPEN", "IN_PROGRESS",
                "RENEWAL_RETRY"},

                /* Passed */ {"CHARGING", "WINBACK", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS",
                "FAILURE", "RENEWAL", "CHARGING", "RENEWAL", "SUSPEND", "FAILURE", "renewal_retry", "OPEN",
                "IN_PROGRESS", "RENEWAL_RETRY"},

                /* Passed */{"CHARGING", "WINBACK", "PARKING", "LOW_BALANCE", "winback", "OPEN", "IN_PROGRESS",
                "SUCCESS", "WINBACK", "CHARGING", "WINBACK", "ACTIVATED", "SUCCESS", "renewal", "OPEN",
                "IN_PROGRESS", "RENEWAL"},

                /* Passed */{"CHARGING", "WINBACK", "PARKING", "LOW_BALANCE", "winback", "OPEN", "IN_PROGRESS",
                "ERROR", "WINBACK", "CHARGING", "WINBACK", "PARKING", "ERROR", "winback", "OPEN", "IN_PROGRESS",
                "WINBACK"},

                /* Passed */{"CHARGING", "WINBACK", "PARKING", "LOW_BALANCE", "winback", "OPEN", "IN_PROGRESS",
                "LOW_BALANCE", "WINBACK", "CHARGING", "WINBACK", "PARKING", "LOW_BALANCE", "winback", "OPEN",
                "IN_PROGRESS", "WINBACK"},

        };
    }

    @Test(dataProvider = "activationDeactivationPostiveDataProvider", groups = {"positive"})
    public void activationDeactivationSuccessPositiveTests(String eventActionType, String activityType,
                                                           String currentSubscriptionState, String transactionState, String actionTable, String beforeSchedularStatus,
                                                           String afterSchedularStatus, String afteNewEventStatus, String queueName, String newEventActionType,
                                                           String newActivityType, String newCurrentSubscriptionState, String newTransactionState,
                                                           String newActionTable, String newBeforeSchedularStatus, String newAfterSchedularStatus,
                                                           String newQueueName) {

        Integer subscriptionId = RandomUtils.nextInt(58000, 59000);
        String testMessage = subscriptionId + " " + activityType + " " + currentSubscriptionState + " "
                + transactionState + " " + actionTable + " " + newCurrentSubscriptionState + " " + newTransactionState
                + " " + newActionTable;
        logger.info("***************Starting activationDeactivationSuccessPositiveTests  [ " + testMessage + " ]");

        try {

            SASUtils.executeActivityFlows(productId, partnerId, subscriptionId, countryCode, eventActionType,
                    activityType, currentSubscriptionState, transactionState, actionTable, beforeSchedularStatus,
                    afterSchedularStatus, afteNewEventStatus, queueName, newEventActionType, newActivityType,
                    newCurrentSubscriptionState, newTransactionState, newActionTable, newBeforeSchedularStatus,
                    newAfterSchedularStatus, newQueueName);
        } catch (Exception e) {
            logger.info("=========>ERROR due to exception");

            Assert.fail(e.getMessage());
        }
    }

}
