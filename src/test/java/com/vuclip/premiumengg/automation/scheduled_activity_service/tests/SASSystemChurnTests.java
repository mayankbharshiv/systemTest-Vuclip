package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author mayank.bharshiv
 */

public class SASSystemChurnTests {
    private static Logger logger = Log4J.getLogger("SystemChurnTests");
    int productId;
    int partnerId;
    PublishConfigRequest publishConfigRequest = null;


    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        productId = SASUtils.productId;
        partnerId = productId;
    }


    @DataProvider(name = "systemChurnNegativeDataProvider")
    public Object[][] systemChurnNegativeDataProvider() {
        return new Object[][]{

                {"SYSTEM_CHURN", "PARKING", "DEACTIVATED", "IN_PROGRESS", "DEACTIVATE_CONSENT", 117, "SYSTEM_CHURN",
                        "OPEN"},
                {"SYSTEM_CHURN", "PARKING", "DEACTIVATED", "FAILURE", "DEACTIVATE_CONSENT", 118, "SYSTEM_CHURN",
                        "OPEN"},
                {"SYSTEM_CHURN", "PARKING", "DEACTIVATED", "ERROR", "DEACTIVATE_CONSENT", 119, "SYSTEM_CHURN",
                        "OPEN"},
                {"SYSTEM_CHURN", "PARKING", "DCT_INIT", "SUCCESS", "DEACTIVATE_CONSENT", 119, "SYSTEM_CHURN", "OPEN"},

                {"SYSTEM_CHURN", "ACTIVATED", "DEACTIVATED", "SUCCESS", "DEACTIVATE_CONSENT", 112, "SYSTEM_CHURN",
                        "OPEN"}};
    }

    @Test(dataProvider = "systemChurnNegativeDataProvider", groups = {"negative"})
    public void systemChurnNegativeTest(String activityType, String previousSubscriptionState,
                                        String currentSubscriptionState, String transactionState, String actionType, Integer subscriptionId,
                                        String actionTable, String status) throws Exception {
        subscriptionId = RandomUtils.nextInt(33000, 34000);
        String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
                + currentSubscriptionState + " " + transactionState + " " + actionType;
        logger.info("==================>Starting System Churn Negative Retry test  [ " + testMessage + " ]");

        SASValidationHelper.negativeFlow(productId, partnerId, activityType, currentSubscriptionState, transactionState,
                actionType, subscriptionId);
    }

}
