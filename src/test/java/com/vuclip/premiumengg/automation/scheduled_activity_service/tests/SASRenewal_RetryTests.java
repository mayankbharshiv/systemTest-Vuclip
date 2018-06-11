package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import com.vuclip.premiumengg.automation.billing_package_service.common.models.QueueResponse;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RabbitMQConnection;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASDBHelper;
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
 * @author rahul.s
 */

public class SASRenewal_RetryTests {
    private static Logger logger = Log4J.getLogger("RenewalRetryTests");
    int productId;
    int partnerId;
    PublishConfigRequest publishConfigRequest = null;
    private SASHelper sasHelper;
    private String countryCode = "IN";

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        sasHelper = new SASHelper();
        productId = SASUtils.productId;
        partnerId = productId;
    }

    @DataProvider(name = "renewalRetryPostiveDataProvider")
    public Object[][] renewalRetryPostiveDataProvider() {
        return new Object[][]{

                {"RENEWAL_RETRY", "ACTIVATED", "SUCCESS", "CHARGING", "renewal"},
                {"RENEWAL_RETRY", "ACTIVATED", "ERROR", "CHARGING", "renewal_retry"},
                {"RENEWAL_RETRY", "ACTIVATED", "FAILURE", "CHARGING", "renewal_retry"},
                {"RENEWAL_RETRY", "SUSPEND", "ERROR", "CHARGING", "renewal_retry"},
                {"RENEWAL_RETRY", "SUSPEND", "FAILURE", "CHARGING", "renewal_retry"},
                {"RENEWAL_RETRY", "SUSPEND", "LOW_BALANCE", "CHARGING", "renewal_retry"},
                {"RENEWAL_RETRY", "SUSPEND", "IN_PROGRESS", "CHARGING", "renewal_retry"},

                // NOT IN DEVELOP BRANCH{ "RENEWAL_RETRY", "SUSPEND", "NOTIFICATION_WAIT",
                // "CHARGING", "renewal" },
        };
    }

    @Test(dataProvider = "renewalRetryPostiveDataProvider", groups = {"pending"})
    public void renewalRetryPostiveTests(String activityType, String currentSubscriptionState, String transactionState,
                                         String actionType, String actionTable) throws Exception {

        Integer subscriptionId = RandomUtils.nextInt(1000, 2000);
        String testMessage = "" + productId + " " + partnerId + " " + subscriptionId + " " + activityType + " " + " "
                + currentSubscriptionState + " " + transactionState + " " + actionType;
        logger.info("==================>Starting Renewal Retry Positive test  [ " + testMessage + " ]");

        try {

            SASValidationHelper.validate_sas_api_response(
                    sasHelper.userSubscription(SASUtils.generateUserSubscriptionRequest(productId, partnerId,
                            activityType, "", currentSubscriptionState, transactionState, actionType, subscriptionId)));

            /* test */
            SASDBHelper.showAllActivityTableData("BEFORE ", String.valueOf(subscriptionId));

            Map<String, String> expectedRecords = new HashMap<String, String>();
            expectedRecords.put("status", "OPEN");
            expectedRecords.put("product_id", String.valueOf(productId));
            expectedRecords.put("partner_id", String.valueOf(partnerId));
            expectedRecords.put("subscription_id", String.valueOf(subscriptionId));
            expectedRecords.put("country_code", countryCode);

            SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId
                    + " and product_id = " + productId + " and partner_id=" + partnerId).get(0), expectedRecords);

            SASValidationHelper.validate_schedular_api_response(
                    sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, actionTable)));

            expectedRecords.put("status", "IN_PROGRESS");

            SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId
                    + " and product_id=" + productId + " and partner_id=" + partnerId).get(0), expectedRecords);

            Message message = RabbitMQConnection.getRabbitTemplate()
                    .receive(productId + "_" + partnerId + "_" + actionTable.toUpperCase() + "_REQUEST_BACKEND", 25000);
            SASValidationHelper.validateQueueMessage(
                    ObjectMapperUtils.readValueFromString(new String(message.getBody()), QueueResponse.class),
                    productId, partnerId, subscriptionId, countryCode, actionTable.toUpperCase());
        } catch (Exception e) {
            AppAssert.assertTrue(false, "Test case fail " + e.getMessage());
            e.printStackTrace();
        }
    }

    @DataProvider(name = "renewalRetryNegativeDataProvider")
    public Object[][] renewalRetryNegativeDataProvider() {
        return new Object[][]{{"RENEWAL_RETRY", "ACTIVATED", "LOW_BALANCE", "CHARGING", "renewal"},
                {"RENEWAL_RETRY", "ACTIVATED", "IN_PROGRESS", "CHARGING", "renewal"},
                {"RENEWAL_RETRY", "SUSPEND", "SUCCESS", "CHARGING", "renewal"},

        };
    }

    @Test(dataProvider = "renewalRetryNegativeDataProvider", groups = {"negative"})
    public void renewalRetryNegativeTestType(String activityType, String currentSubscriptionState,
                                             String transactionState, String actionType, String actionTable) throws Exception {
        Integer subscriptionId = RandomUtils.nextInt(13000, 14000);
        String testMessage = subscriptionId + " " + activityType + " " + " " + currentSubscriptionState + " "
                + transactionState + " " + actionType;
        logger.info("==================>Starting Renewal Retry Negative Retry test  [ " + testMessage + " ]");
        SASValidationHelper.negativeFlow(productId, partnerId, activityType, currentSubscriptionState, transactionState,
                actionType, subscriptionId);

    }

}
