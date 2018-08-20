package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.ActivityType;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author mayank.bharshiv
 */

public class BlackoutTests {
    private static Logger logger = Log4J.getLogger("BlackoutWindowTests");
    PublishConfigRequest publishConfigRequest = null;
    String activityType = "WINBACK";
    String previousSubscriptionState = "PARKING";
    String currentSubscriptionState = "ACTIVATED";
    String transactionState = "SUCCESS";
    String actionType = "CHARGING";
    Integer subscriptionId;
    String actionTable = "renewal";
    private SASHelper sasHelper;
    private int productId = 0;
    private int partnerId = 0;

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        sasHelper = new SASHelper();
        productId = RandomUtils.nextInt(79000, 80000);
        partnerId = productId;
    }

    @DataProvider(name = "setupBlackoutWindowConfig")
    public Object[][] setupBlackoutWindowConfig() {
        logger.info("========================Setting up config Data===========================");
        return new Object[][]{{ActivityType.RENEWAL_TYPE, "00:01-23:59"}};

    }

    @Test(dataProvider = "setupBlackoutWindowConfig", groups = {"positive"})
    public void createBlackoutWindowConfigData(String activityType, String blackoutWindow) {
        try {
            publishConfigRequest = SASUtils.generateSaveProductConfig(productId, partnerId, activityType);
            publishConfigRequest.getBlackouts().get(0).setBlackoutWindow(blackoutWindow);
            SASValidationHelper.validate_sas_api_response(sasHelper.saveProduct(publishConfigRequest));
        } catch (Exception e) {
            e.printStackTrace();
            Log4J.getLogger().info("Not able to create config for blackout");
        }

    }

    @Test(dependsOnMethods = "createBlackoutWindowConfigData", groups = {"positive"})
    public void invalidBlackoutWindowFieldValue() throws Exception {

        subscriptionId = RandomUtils.nextInt(24000, 25000);
        String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
                + currentSubscriptionState + " " + transactionState + " " + actionType;
        logger.info("==================>Starting Invalid BlackOut Window Field Value test  [ " + testMessage + " ]");

        try {

            SASValidationHelper.validate_sas_api_response(
                    sasHelper.userSubscription(SASUtils.generateUserSubscriptionRequest(productId, partnerId,
                            activityType, previousSubscriptionState, currentSubscriptionState, transactionState,
                            actionType, subscriptionId)));

            SASValidationHelper.validate_schedular_invalid_api_response(
                    sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, actionTable)));

        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }
}
