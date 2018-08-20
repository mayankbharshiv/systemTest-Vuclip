package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.RabbitUtil;
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
                 * String eventActionType, String activityType, String currentSubscriptionState,
                 * String transactionState, String actionTable, String beforeSchedularStatus,
                 * String afterSchedularStatus, String afteNewEventStatus,String
                 * schedularActivityType, String queueName,String queueActivity, String
                 * newEventActionType, String newActivityType, String
                 * newCurrentSubscriptionState, String newTransactionState, String
                 * newActionTable, String newBeforeSchedularStatus, String
                 * newAfterSchedularStatus,String newSchedularActivityType, String
                 * newQueueName,String newQueueActivityType
                 */
                /* SECONDTIMEFIX */{"CHARGING", "ACTIVATION", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS",
                "SUCCESS", "RENEWAL", "RENEWAL", "RENEWAL", "CHARGING", "RENEWAL", "ACTIVATED", "SUCCESS",
                "renewal", "OPEN", "IN_PROGRESS", "RENEWAL", "RENEWAL", "RENEWAL"},

//				/* SECONDTIMEFIX */{ "CHARGING", "ACTIVATION", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS",
//						"ERROR", "RENEWAL", "RENEWAL", "RENEWAL", "CHARGING", "RENEWAL", "ACTIVATED", "ERROR",
//						"renewal", "OPEN", "IN_PROGRESS", "RENEWAL", "RENEWAL", "RENEWAL" },
//
//				/* SECONDTIMEFIX */{ "CHARGING", "ACTIVATION", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS",
//						"FAILURE", "RENEWAL", "RENEWAL", "RENEWAL", "CHARGING", "RENEWAL", "ACTIVATED", "FAILURE",
//						"renewal", "OPEN", "IN_PROGRESS", "RENEWAL", "RENEWAL", "RENEWAL" },

                /* SECONDTIMEFIX */{"CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE", "activation", "OPEN",
                "IN_PROGRESS", "SUCCESS", "ACTIVATION_RETRY", "ACTIVATION_RETRY", "ACTIVATION_RETRY",
                "CHARGING", "ACTIVATION_RETRY", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS",
                "RENEWAL", "RENEWAL", "RENEWAL"},

                /* SECONDTIMEFIX */{"CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE", "activation", "OPEN",
                "IN_PROGRESS", "FAILURE", "ACTIVATION_RETRY", "ACTIVATION_RETRY", "ACTIVATION_RETRY",
                "CHARGING", "ACTIVATION_RETRY", "ACT_INIT", "FAILURE", "activation", "OPEN", "IN_PROGRESS",
                "ACTIVATION_RETRY", "ACTIVATION_RETRY", "ACTIVATION_RETRY"},
                /* SECONDTIMEFIX */{"CHARGING", "ACTIVATION", "ACT_INIT", "FAILURE", "activation", "OPEN",
                "IN_PROGRESS", "ERROR", "ACTIVATION_RETRY", "ACTIVATION_RETRY", "ACTIVATION_RETRY", "CHARGING",
                "ACTIVATION_RETRY", "ACT_INIT", "ERROR", "activation", "OPEN", "IN_PROGRESS",
                "ACTIVATION_RETRY", "ACTIVATION_RETRY", "ACTIVATION_RETRY"},

                /* SECONDTIMEFIX */{"CHARGING", "ACTIVATION", "PARKING", "LOW_BALANCE", "winback", "OPEN",
                "IN_PROGRESS", "ERROR", "WINBACK", "WINBACK", "WINBACK", "CHARGING", "WINBACK", "PARKING",
                "ERROR", "winback", "OPEN", "IN_PROGRESS", "WINBACK", "WINBACK", "WINBACK"},
                /* SECONDTIMEFIX */{"CHARGING", "ACTIVATION", "PARKING", "LOW_BALANCE", "winback", "OPEN",
                "IN_PROGRESS", "SUCCESS", "WINBACK", "WINBACK", "WINBACK", "CHARGING", "WINBACK", "ACTIVATED",
                "SUCCESS", "renewal", "OPEN", "IN_PROGRESS", "RENEWAL", "RENEWAL", "RENEWAL"},

                /* SECONDTIMEFIX */{"CHARGING", "ACTIVATION", "PARKING", "LOW_BALANCE", "winback", "OPEN",
                "IN_PROGRESS", "LOW_BALANCE", "WINBACK", "WINBACK", "WINBACK", "CHARGING", "WINBACK", "PARKING",
                "LOW_BALANCE", "winback", "OPEN", "IN_PROGRESS", "WINBACK", "WINBACK", "WINBACK"},

        };
    }

    @Test(dataProvider = "activationDeactivationPostiveDataProvider", groups = {"bug"})
    public void activationDeactivationSuccessPositiveTests(String eventActionType, String activityType,
                                                           String currentSubscriptionState, String transactionState, String actionTable, String beforeSchedularStatus,
                                                           String afterSchedularStatus, String afteNewEventStatus, String schedularActivityType, String queueName,
                                                           String queueActivity, String newEventActionType, String newActivityType, String newCurrentSubscriptionState,
                                                           String newTransactionState, String newActionTable, String newBeforeSchedularStatus,
                                                           String newAfterSchedularStatus, String newSchedularActivityType, String newQueueName,
                                                           String newQueueActivityType) {

        Integer subscriptionId = RandomUtils.nextInt(58000, 59000);
        String testMessage = subscriptionId + " " + activityType + " " + currentSubscriptionState + " "
                + transactionState + " " + actionTable + " " + newCurrentSubscriptionState + " " + newTransactionState
                + " " + newActionTable;
        logger.info("***************Starting activationDeactivationSuccessPositiveTests  [ " + testMessage + " ]");
        RabbitUtil.purgeAllActivityQueue(productId, partnerId, countryCode);
        try {

            SASUtils.executeActivityFlows(subscriptionId, productId, partnerId, countryCode, eventActionType,
                    activityType, currentSubscriptionState, transactionState, actionTable, beforeSchedularStatus,
                    afterSchedularStatus, afteNewEventStatus, schedularActivityType, queueName, queueActivity,
                    newEventActionType, newActivityType, newCurrentSubscriptionState, newTransactionState,
                    newActionTable, newBeforeSchedularStatus, newAfterSchedularStatus, newSchedularActivityType,
                    newQueueName, newQueueActivityType);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("=========>ERROR due to exception");

            Assert.fail(e.getMessage());
        }
    }

}
