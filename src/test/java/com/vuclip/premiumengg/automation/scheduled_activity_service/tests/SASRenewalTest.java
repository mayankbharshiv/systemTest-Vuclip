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

public class SASRenewalTest {
    private static Logger logger = Log4J.getLogger("RenewalTests");
    int productId;
    int partnerId;
    PublishConfigRequest publishConfigRequest = null;


    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        productId = SASUtils.productId;
        partnerId = productId;
    }


    @DataProvider(name = "renewalNegativeDataProvider")
    public Object[][] renewalNegativeDataProvider() {
        return new Object[][]{{"RENEWAL", "SUSPEND", "IN_PROGRESS", "CHARGING", "winback", "OPEN"},
                {"RENEWAL", "SUSPEND", "SUCCESS", "CHARGING", "activation", "OPEN"},

        };
    }

    @Test(dataProvider = "renewalNegativeTestType", groups = {"negative"})
    public void renewalNegativeTest(String activityType, String currentSubscriptionState, String transactionState,
                                    String actionType, String actionTable, String status) throws Exception {
        int subscriptionId = RandomUtils.nextInt(53000, 54000);
        String testMessage = subscriptionId + " " + activityType + " " + currentSubscriptionState + " "
                + transactionState + " " + actionType;
        logger.info("==================>Starting Renewal Negative Test  [ " + testMessage + " ]");

        SASValidationHelper.negativeFlow(productId, partnerId, activityType, currentSubscriptionState, transactionState,
                actionType, subscriptionId);

    }

}
