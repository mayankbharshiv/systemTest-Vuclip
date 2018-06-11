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

public class SASFreeTrialRenewalSuccessTest {
    private static Logger logger = Log4J.getLogger("SASFreeTrialRenewalSuccessTest");
    int productId;
    int partnerId;
    private String countryCode = "IN";

    //TODO need to automate once we have running flow on 101Fs
    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        productId = SASUtils.productId;
        partnerId = productId;
    }

    @DataProvider(name = "freeTrialRenewalSuccessDataProvider")
    public Object[][] freeTrialRenewalSuccessDataProvider() {
        return new Object[][]{
                /*
                 * FORMAT { "eventActionType", "activityType", "currentSubscriptionState",
                 * "transactionState", "actionTable", "beforeSchedularStatus",
                 * "afterSchedularStatus", "afteNewEventStatus", "queueName",
                 * "newEventActionType", "newActivityType", "newCurrentSubscriptionState",
                 * "newTransactionState", "newActionTable", "newBeforeSchedularStatus",
                 * "newAfterSchedularStatus", "newQueueName" },
                 */

        };
    }

    /**
     * @param eventActionType
     * @param activityType
     * @param currentSubscriptionState
     * @param transactionState
     * @param actionTable
     * @param beforeSchedularStatus
     * @param afterSchedularStatus
     * @param afteNewEventStatus
     * @param queueName
     * @param newEventActionType
     * @param newActivityType
     * @param newCurrentSubscriptionState
     * @param newTransactionState
     * @param newActionTable
     * @param newBeforeSchedularStatus
     * @param newAfterSchedularStatus
     * @param newQueueName
     */
    @Test(dataProvider = "freeTrialRenewalSuccessDataProvider", groups = {"positive"})
    public void reeTrialRenewalSuccessTests(String eventActionType, String activityType,
                                            String currentSubscriptionState, String transactionState, String actionTable, String beforeSchedularStatus,
                                            String afterSchedularStatus, String afteNewEventStatus, String queueName, String newEventActionType,
                                            String newActivityType, String newCurrentSubscriptionState, String newTransactionState,
                                            String newActionTable, String newBeforeSchedularStatus, String newAfterSchedularStatus,
                                            String newQueueName) {

        Integer subscriptionId = RandomUtils.nextInt(58000, 59000);
        String testMessage = subscriptionId + " " + activityType + " " + currentSubscriptionState + " "
                + transactionState + " " + actionTable + " " + newCurrentSubscriptionState + " " + newTransactionState
                + " " + newActionTable;
        logger.info("***************Starting SASFreeTrialRenewalSuccessTest  [ " + testMessage + " ]");

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
