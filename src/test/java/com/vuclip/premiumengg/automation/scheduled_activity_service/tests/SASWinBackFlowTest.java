package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.JDBCTemplate;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.RabbitUtil;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;

/**
 * @author rahul.sahu
 */

public class SASWinBackFlowTest {
    private static Logger logger = Log4J.getLogger("SASActivationDeactivationSuccessTest");
    int productId;
    int partnerId;
    private String countryCode = "IN";

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        productId = SASUtils.productId;
        partnerId = productId;
    }

    @DataProvider(name = "SASWinBackFlowTestdp")
    public Object[][] activationDeactivationPostiveDataProvider() {
        return new Object[][]{

                /* SECONDTIMEFIX */{"CHARGING", "WINBACK", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS",
                "SUCCESS", "RENEWAL", "RENEWAL", "RENEWAL", "CHARGING", "RENEWAL", "ACTIVATED", "SUCCESS",
                "renewal", "OPEN", "IN_PROGRESS", "RENEWAL", "RENEWAL", "RENEWAL"},

//                /* SECONDTIMEFIX */ {"CHARGING", "WINBACK", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS",
//                "FAILURE", "RENEWAL", "RENEWAL", "RENEWAL", "CHARGING", "RENEWAL", "ACTIVATED", "FAILURE",
//                "renewal", "OPEN", "IN_PROGRESS", "RENEWAL", "RENEWAL", "RENEWAL"},
//
//                /* SECONDTIMEFIX */{"CHARGING", "WINBACK", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS",
//                "ERROR", "RENEWAL", "RENEWAL", "RENEWAL", "CHARGING", "RENEWAL", "ACTIVATED", "ERROR",
//                "renewal", "OPEN", "IN_PROGRESS", "RENEWAL", "RENEWAL", "RENEWAL"},
//
//                /* SECONDTIMEFIX */{"CHARGING", "WINBACK", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS",
//                "ERROR", "RENEWAL", "RENEWAL", "RENEWAL", "CHARGING", "RENEWAL", "SUSPEND", "ERROR", "renewal",
//                "OPEN", "IN_PROGRESS", "RENEWAL", "RENEWAL", "RENEWAL"},
//
//                /* SECONDTIMEFIX */ {"CHARGING", "WINBACK", "ACTIVATED", "SUCCESS", "renewal", "OPEN", "IN_PROGRESS",
//                "FAILURE", "RENEWAL", "RENEWAL", "RENEWAL", "CHARGING", "RENEWAL", "SUSPEND", "FAILURE",
//                "renewal", "OPEN", "IN_PROGRESS", "RENEWAL", "RENEWAL", "RENEWAL"},

                /* SECONDTIMEFIX */{"CHARGING", "WINBACK", "PARKING", "LOW_BALANCE", "winback", "OPEN", "IN_PROGRESS",
                "SUCCESS", "WINBACK", "WINBACK", "WINBACK", "CHARGING", "WINBACK", "ACTIVATED", "SUCCESS",
                "renewal", "OPEN", "IN_PROGRESS", "RENEWAL", "RENEWAL", "RENEWAL"},

                /* SECONDTIMEFIX */{"CHARGING", "WINBACK", "PARKING", "LOW_BALANCE", "winback", "OPEN", "IN_PROGRESS",
                "ERROR", "WINBACK", "WINBACK", "WINBACK", "CHARGING", "WINBACK", "PARKING", "ERROR", "winback",
                "OPEN", "IN_PROGRESS", "WINBACK", "WINBACK", "WINBACK"},

                /* SECONDTIMEFIX */{"CHARGING", "WINBACK", "PARKING", "LOW_BALANCE", "winback", "OPEN", "IN_PROGRESS",
                "LOW_BALANCE", "WINBACK", "WINBACK", "WINBACK", "CHARGING", "WINBACK", "PARKING", "LOW_BALANCE",
                "winback", "OPEN", "IN_PROGRESS", "WINBACK", "WINBACK", "WINBACK"},

        };
    }

    @Test(dataProvider = "SASWinBackFlowTestdp", groups = {"bug"})
    public void SASWinBackFlowTests(String eventActionType, String activityType, String currentSubscriptionState,
                                    String transactionState, String actionTable, String beforeSchedularStatus, String afterSchedularStatus,
                                    String afteNewEventStatus, String schedularActivityType, String queueName, String queueActivity,
                                    String newEventActionType, String newActivityType, String newCurrentSubscriptionState,
                                    String newTransactionState, String newActionTable, String newBeforeSchedularStatus,
                                    String newAfterSchedularStatus, String newSchedularActivityType, String newQueueName,
                                    String newQueueActivityType) {

        Integer subscriptionId = RandomUtils.nextInt(58000, 59000);
        String testMessage = subscriptionId + " " + activityType + " " + currentSubscriptionState + " "
                + transactionState + " " + actionTable + " " + newCurrentSubscriptionState + " " + newTransactionState
                + " " + newActionTable;
        logger.info("***************Starting SASWinBackFlowTest  [ " + testMessage + " ]");

        RabbitUtil.purgeAllActivityQueue(productId, partnerId, countryCode);
        try {
            JDBCTemplate.getDbConnection().update(
                    "INSERT INTO `scheduled_activity_service`.`winback` (`product_id`, `partner_id`, `country_code`, `date`, `subscription_id`, `attempt_number`, `is_eligible`, `status`, `user_id`) VALUES"
                            + " ('" + productId + "', '" + partnerId + "', '" + countryCode + "', '1522849806000', '"
                            + subscriptionId + "', '2', '1', 'IN_PROGRESS', 'u-23xyz');");

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
