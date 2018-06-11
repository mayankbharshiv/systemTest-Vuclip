package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import com.vuclip.premiumengg.automation.billing_package_service.common.models.QueueResponse;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RabbitMQConnection;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.UserSubscriptionRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mayank.bharshiv
 */

public class SASFreeTrailRenewalTests {
    private static Logger logger = Log4J.getLogger("FreeTrailRenewalTests");
    int productId;
    int partnerId;
    private SASHelper sasHelper;
    private String countryCode = "IN";

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        sasHelper = new SASHelper();
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


        };

    }

    @Test(dataProvider = "freeTrailRenewalPostiveDataProvider", groups = {"pending"})
    public void freeTrailRenewalPostiveDataProvider(String activityType, String currentSubscriptionState,
                                                    String transactionState, String actionType, Integer subscriptionId, String actionTable, String status)
            throws Exception {
        Message message = null;
        subscriptionId = RandomUtils.nextInt(8000, 9000);
        String testMessage = subscriptionId + " " + activityType + " " + " " + currentSubscriptionState + " "
                + transactionState + " " + actionType;
        logger.info("==================>Starting positive free trail renewal test  [ " + testMessage + " ]");

        try {

            UserSubscriptionRequest userSubscriptionRequest = SASUtils.generateUserSubscriptionRequest(productId,
                    partnerId, activityType, "", currentSubscriptionState, transactionState, actionType,
                    subscriptionId);
            SASValidationHelper.validate_sas_api_response(sasHelper.userSubscription(userSubscriptionRequest));

            Map<String, String> expectedRecords = new HashMap<String, String>();
            expectedRecords.put("status", "OPEN");
            expectedRecords.put("product_id", String.valueOf(productId));
            expectedRecords.put("partner_id", String.valueOf(partnerId));
            expectedRecords.put("subscription_id", String.valueOf(subscriptionId));
            expectedRecords.put("country_code", countryCode);

            SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId
                    + " and product_id = " + productId + " and partner_id=" + partnerId).get(0), expectedRecords);

            SASValidationHelper.validate_schedular_api_response(
                    sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, activityType)));

            expectedRecords.put("status", "IN_PROGRESS");

            SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId
                    + " and product_id=" + productId + " and partner_id=" + partnerId).get(0), expectedRecords);

            message = RabbitMQConnection.getRabbitTemplate()
                    .receive(productId + "_" + partnerId + "_" + activityType + "_REQUEST_BACKEND", 25000);
            SASValidationHelper.validateQueueMessage(
                    ObjectMapperUtils.readValueFromString(new String(message.getBody()), QueueResponse.class),
                    productId, partnerId, subscriptionId, countryCode, activityType);

        } catch (Exception e) {
            AppAssert.assertTrue(false, "Test case fail " + e.getMessage());
            e.printStackTrace();
        }

    }

    @DataProvider(name = "freeTrailRenewalPestType")
    public Object[][] freeTrailRenewalPestType() {
        return new Object[][]{
                // needs to check when env gets update{ "FREETRIAL_RENEWAL", "SUSPEND",
                // "LOW_BALANCE", "CHARGING", 101, "free_trail", "OPEN" },
                // needs to check when env gets update{ "FREETRIAL_RENEWAL", "SUSPEND", "ERROR",
                // "CHARGING", 109, "free_trail", "OPEN" },
                // needs to check when env gets update{ "FREETRIAL_RENEWAL", "ACTIVATED",
                // "ERROR", "CHARGING", 111, "free_trail", "OPEN" },
                // needs to check when env gets update{ "FREETRIAL_RENEWAL", "SUSPEND",
                // "FAILURE", "CHARGING", 112, "free_trail", "OPEN" }
        };
    }

    @Test(dataProvider = "freeTrailRenewalPestType", groups = {"pending"})
    public void freeTrailRenewalPTestType(String activityType, String currentSubscriptionState, String transactionState,
                                          String actionType, Integer subscriptionId, String actionTable, String status) throws Exception {
        subscriptionId = RandomUtils.nextInt(13000, 14000);
        String testMessage = subscriptionId + " " + activityType + " " + " " + currentSubscriptionState + " "
                + transactionState + " " + actionType;
        logger.info("==================>Starting Negative free Trail Renewal retry test  [ " + testMessage + " ]");
        SASValidationHelper.negativeFlow(productId, partnerId, activityType, currentSubscriptionState, transactionState,
                actionType, subscriptionId);

    }

    @DataProvider(name = "freeTrailRenewalNegativeDataProvider")
    public Object[][] freeTrailRenewalNegativeDataProvider() {
        return new Object[][]{{"FREETRIAL_RENEWAL", "SUSPEND", "SUCCESS", "CHARGING", 110, "free_trail", "OPEN"},
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
