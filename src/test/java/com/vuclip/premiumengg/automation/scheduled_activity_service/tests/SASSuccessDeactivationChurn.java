package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.UserSubscriptionRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASDBHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;
import com.vuclip.premiumengg.automation.utils.DateTimeUtil;
import com.vuclip.premiumengg.automation.utils.TimeUnitEnum;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author rahul.sahu
 */

public class SASSuccessDeactivationChurn {
    private static Logger logger = Log4J.getLogger("SASSuccessDeactivationChurn");
    int productId;
    int partnerId;
    PublishConfigRequest publishConfigRequest = null;
    private String countryCode = "IN";

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        productId = SASUtils.productId;
        partnerId = productId;
    }

    @DataProvider(name = "successTwoFlowPositiveDataProvider")
    public Object[][] successTwoFlowPositiveDataProvider() {
        return new Object[][]{

                /* PASSED */ {"DEACTIVATE_CONSENT", "DEACTIVATION", "DCT_INIT", "FAILURE", "deactivation", "OPEN",
                "IN_PROGRESS", "SUCCESS", "DEACTIVATION_RETRY", "DEACTIVATION_RETRY", "DEACTIVATION_RETRY",
                "DEACTIVATE_CONSENT", "DEACTIVATION_RETRY", "DEACTIVATED", "SUCCESS"},
                /* RAHUL
                 * {"DEACTIVATE_CONSENT", "SYSTEM_CHURN", "DCT_INIT", "IN_PROGRESS", "churn",
                 * "OPEN", "IN_PROGRESS", "SUCCESS",
                 * "SYSTEM_CHURN","SYSTEM_CHURN","SYSTEM_CHURN", "DEACTIVATE_CONSENT",
                 * "SYSTEM_CHURN", "DEACTIVATED","SUCCESS"},
                 */};
    }

    @Test(dataProvider = "successTwoFlowPositiveDataProvider", groups = {"positive"})
    public void successTwoFlowPositiveTests(String eventActionType, String activityType,
                                            String currentSubscriptionState, String transactionState, String actionTable, String beforeSchedularStatus,
                                            String afterSchedularStatus, String afteNewEventStatus, String schedularActivityType, String queueName,
                                            String queueActivity, String newEventActionType, String newActivityType, String newCurrentSubscriptionState,
                                            String newTransactionState) {

        Integer subscriptionId = RandomUtils.nextInt(58000, 59000);
        String testMessage = subscriptionId + " " + activityType + " " + currentSubscriptionState + " "
                + transactionState + " " + actionTable + " " + newCurrentSubscriptionState;
        logger.info("***************Starting success Two Flow Positive Tests  [ " + testMessage + " ]");

        try {

            SASHelper sasHelper = new SASHelper();
            Logger logger = Log4J.getLogger("SAS FLOW");

            logger.info("=========>First time user subscription event getting trigger");
            UserSubscriptionRequest firstUserSubscriptionRequest = SASUtils.generateUserSubscriptionRequest(productId,
                    partnerId, activityType, currentSubscriptionState, transactionState, eventActionType,
                    subscriptionId);
            SASValidationHelper.validate_sas_api_response(sasHelper.userSubscription(firstUserSubscriptionRequest));

            SASDBHelper.showAllActivityTableData("FIRST ", String.valueOf(subscriptionId));

            logger.info("=========>First event: Verify DB after event trigger");
            SASValidationHelper.verifyEventTable(actionTable, subscriptionId, productId, partnerId,
                    firstUserSubscriptionRequest.getSubscriptionInfo().getNextBillingDate(), beforeSchedularStatus,
                    countryCode);

            logger.info("=========>First Event: scheduale call ");
            SASValidationHelper.validate_schedular_api_response(
                    sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, actionTable)));

            SASDBHelper.showAllActivityTableData("second", String.valueOf(subscriptionId));
            logger.info("=========>First Event: Vefiry DB After Schedular Call ");
            SASValidationHelper.verifyEventTable(actionTable, subscriptionId, productId, partnerId,
                    firstUserSubscriptionRequest.getSubscriptionInfo().getNextBillingDate(), afterSchedularStatus,
                    countryCode);

            logger.info("=========>First Event: Queue verification : ");
            SASValidationHelper.validateQueue(queueName, queueActivity, productId, partnerId, subscriptionId,
                    countryCode);

            logger.info("=========>Second time user subscription event getting trigger");
            UserSubscriptionRequest newSubscriptionRequest = SASUtils.generateUserSubscriptionRequest(productId,
                    partnerId, newActivityType.toUpperCase(), newCurrentSubscriptionState, newTransactionState,
                    newEventActionType, subscriptionId,
                    DateTimeUtil.getDateByAddingValidity(
                            firstUserSubscriptionRequest.getSubscriptionInfo().getNextBillingDate(), 1,
                            TimeUnitEnum.DAY.name()));
            SASValidationHelper.validate_sas_api_response(sasHelper.userSubscription(newSubscriptionRequest));

            SASDBHelper.showAllActivityTableData("THIRD", String.valueOf(subscriptionId));

            logger.info("=========>Second event: previous Event's DB verification");
            SASValidationHelper.verifyEventTable(actionTable, subscriptionId, productId, partnerId,
                    firstUserSubscriptionRequest.getSubscriptionInfo().getNextBillingDate(), afteNewEventStatus,
                    countryCode);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

}
