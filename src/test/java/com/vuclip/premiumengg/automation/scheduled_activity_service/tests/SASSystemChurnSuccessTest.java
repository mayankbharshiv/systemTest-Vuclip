package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import com.vuclip.premiumengg.automation.common.JDBCTemplate;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RabbitMQConnection;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.SASQueueResponse;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.UserSubscriptionRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.*;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import com.vuclip.premiumengg.automation.utils.DateTimeUtil;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;
import com.vuclip.premiumengg.automation.utils.TimeUnitEnum;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mayank.bharshiv
 */
public class SASSystemChurnSuccessTest {
    private static Logger logger = Log4J.getLogger("SystemChurnSuccessTest");
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

    @DataProvider(name = "systemChurnSuccessPositiveDataProvider")
    public Object[][] systemChurnSuccessPositiveDataProvider() {
        return new Object[][]{

                /* Passed */ {"DEACTIVATE_CONSENT", "SYSTEM_CHURN", "DCT_INIT", "FAILURE", "churn", "OPEN",
                "IN_PROGRESS", "FAILURE", "SYSTEM_CHURN",
                "DEACTIVATE_CONSENT", "SYSTEM_CHURN", "DCT_INIT", "FAILURE", "churn", "OPEN", "IN_PROGRESS", "SYSTEM_CHURN"},

                /* Passed */ {"DEACTIVATE_CONSENT", "SYSTEM_CHURN", "DCT_INIT", "FAILURE", "churn", "OPEN",
                "IN_PROGRESS", "ERROR", "SYSTEM_CHURN",
                "DEACTIVATE_CONSENT", "SYSTEM_CHURN", "DCT_INIT",
                "ERROR", "churn", "OPEN", "IN_PROGRESS", "SYSTEM_CHURN"},


                /* Passed */ {"DEACTIVATE_CONSENT", "SYSTEM_CHURN", "DCT_INIT", "IN_PROGRESS", "churn", "OPEN",
                "IN_PROGRESS", "FAILURE", "SYSTEM_CHURN",
                "DEACTIVATE_CONSENT", "SYSTEM_CHURN", "DCT_INIT",
                "FAILURE", "churn", "OPEN", "IN_PROGRESS", "SYSTEM_CHURN"},

                /* Passed */ {"DEACTIVATE_CONSENT", "SYSTEM_CHURN", "DCT_INIT", "IN_PROGRESS", "churn", "OPEN",
                "IN_PROGRESS", "ERROR", "SYSTEM_CHURN", "DEACTIVATE_CONSENT", "SYSTEM_CHURN", "DCT_INIT",
                "ERROR", "churn", "OPEN", "IN_PROGRESS", "SYSTEM_CHURN"},

        };
    }

    @Test(dataProvider = "systemChurnSuccessPositiveDataProvider", groups = {"one"})
    public void systemChurnSuccessPositiveTests(String eventActionType, String activityType,
                                                String currentSubscriptionState, String transactionState, String actionTable, String beforeSchedularStatus,
                                                String afterSchedularStatus, String afteNewEventStatus, String queueName, String newEventActionType,
                                                String newActivityType, String newCurrentSubscriptionState, String newTransactionState,
                                                String newActionTable, String newBeforeSchedularStatus, String newAfterSchedularStatus, String newQueueName)
            throws Exception {

        Integer subscriptionId = RandomUtils.nextInt(900, 1000);
        String testMessage = subscriptionId + " " + activityType + " " + currentSubscriptionState + " "
                + transactionState + " " + actionTable + " " + newCurrentSubscriptionState + " " + newTransactionState
                + " " + newActionTable;
        logger.info("***************Starting System Churn Two Flows Positive Retry test  [ " + testMessage + " ]");

        try {
            JDBCTemplate.getDbConnection().update(
                    "INSERT INTO `scheduled_activity_service`.`churn` (`product_id`, `partner_id`, `country_code`, `date`, `subscription_id`, `attempt_number`, `status`) VALUES"
                            + " ('" + productId + "', '" + partnerId + "', '" + countryCode + "', '1522849806000', '"
                            + subscriptionId + "', '2', 'IN_PROGRESS');");
            RabbitUtil.purgeAllActivityQueue(productId, partnerId, countryCode);
            logger.info("=========>First time user subscription event getting trigger");
            UserSubscriptionRequest uSRequest = SASUtils.generateUserSubscriptionRequest(productId, partnerId,
                    activityType, "", currentSubscriptionState, transactionState, eventActionType, subscriptionId);
            SASValidationHelper.validate_sas_api_response(sasHelper.userSubscription(uSRequest));
            SASDBHelper.showAllActivityTableData("FIRST ", String.valueOf(subscriptionId));
            logger.info("=========>First event: Verify DB after event trigger");

            Map<String, String> expectedRecords = new HashMap<String, String>();
            expectedRecords.put("status", beforeSchedularStatus);
            expectedRecords.put("product_id", String.valueOf(productId));
            expectedRecords.put("partner_id", String.valueOf(partnerId));
            expectedRecords.put("subscription_id", String.valueOf(subscriptionId));
            expectedRecords.put("country_code", countryCode);
            expectedRecords.put("date", String.valueOf(uSRequest.getSubscriptionInfo().getNextBillingDate()));

            SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable,
                    "subscription_id = " + subscriptionId + " and product_id = " + productId + " and partner_id="
                            + partnerId + " and date=" + uSRequest.getSubscriptionInfo().getNextBillingDate())
                    .get(0), expectedRecords);

            logger.info("=========>First Event: schedular call ");

            SASValidationHelper.validate_schedular_api_response(
                    sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, activityType)));

            logger.info("=========>First Event: Vefiry DB After Schedular Call ");
            expectedRecords.put("status", afterSchedularStatus);
            SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId
                    + " and product_id=" + productId + " and partner_id=" + partnerId).get(0), expectedRecords);

            logger.info("=========>First Event: Queue verification Name: "
                    + RabbitUtil.getQueueName(productId, partnerId, countryCode, queueName));
            Message message = RabbitUtil.receive(RabbitMQConnection.getRabbitTemplate(),
                    RabbitUtil.getQueueName(productId, partnerId, countryCode, queueName), 10000);
//			Message message = RabbitMQConnection.getRabbitTemplate()
//					.receive(productId + "_" + partnerId + "_" + queueName.toUpperCase() + "ccc", 25000);

            SASValidationHelper.validateQueueMessage(
                    ObjectMapperUtils.readValueFromString(new String(message.getBody()), SASQueueResponse.class),
                    productId, partnerId, subscriptionId, countryCode, activityType);

            logger.info("=========>Second time user subscription event getting trigger");
            UserSubscriptionRequest newSubscriptionRequest = SASUtils.generateUserSubscriptionRequest(productId,
                    partnerId, newActivityType.toUpperCase(), currentSubscriptionState, newCurrentSubscriptionState,
                    newTransactionState, newEventActionType, subscriptionId);
            newSubscriptionRequest.getSubscriptionInfo().setNextBillingDate(DateTimeUtil.getDateByAddingValidity(
                    newSubscriptionRequest.getSubscriptionInfo().getNextBillingDate(), 1, TimeUnitEnum.DAY.name()));
            newSubscriptionRequest.getActivityEvent().setNextBillingDate(DateTimeUtil.getDateByAddingValidity(
                    newSubscriptionRequest.getActivityEvent().getNextBillingDate(), 1, TimeUnitEnum.DAY.name()));

            SASValidationHelper.validate_sas_api_response(sasHelper.userSubscription(newSubscriptionRequest));

            logger.info("=========>Second event: previous Event's DB verification");
            expectedRecords.put("status", afteNewEventStatus);
            SASValidationHelper.validateTableRecord(DBUtils
                    .getRecord(actionTable,
                            "subscription_id = " + subscriptionId + " and product_id=" + productId + " and partner_id="
                                    + partnerId + " and date=" + uSRequest.getSubscriptionInfo().getNextBillingDate())
                    .get(0), expectedRecords);

            logger.info("=========>Second event: DB verification before schedular call");
            expectedRecords.put("status", newBeforeSchedularStatus);
            expectedRecords.put("date",
                    String.valueOf(newSubscriptionRequest.getSubscriptionInfo().getNextBillingDate()));
            SASValidationHelper.validateTableRecord(
                    DBUtils.getRecord(newActionTable,
                            "subscription_id = " + subscriptionId + " and product_id=" + productId + " and partner_id="
                                    + partnerId + " and date="
                                    + newSubscriptionRequest.getSubscriptionInfo().getNextBillingDate())
                            .get(0),
                    expectedRecords);

            logger.info("=========>Second event: Schedular call");

            SASValidationHelper.validate_schedular_api_response(
                    sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, activityType)));

            logger.info("=========>Second event: DB verification after schedular call");
            expectedRecords.put("status", newAfterSchedularStatus);
            SASValidationHelper.validateTableRecord(
                    DBUtils.getRecord(newActionTable,
                            "subscription_id = " + subscriptionId + " and product_id=" + productId + " and partner_id="
                                    + partnerId + " and date="
                                    + newSubscriptionRequest.getSubscriptionInfo().getNextBillingDate())
                            .get(0),
                    expectedRecords);

            logger.info("=========>Second event: Queue verification " + RabbitUtil.getQueueName(productId, partnerId, countryCode, newQueueName));
            message = RabbitUtil.receive(RabbitMQConnection.getRabbitTemplate(),
                    RabbitUtil.getQueueName(productId, partnerId, countryCode, newQueueName), 10000);
			/*message = RabbitMQConnection.getRabbitTemplate().receive(
					productId + "_" + partnerId + "_" + newQueueName.toUpperCase() + "ccc", 25000);*/

            SASValidationHelper.validateQueueMessage(
                    ObjectMapperUtils.readValueFromString(new String(message.getBody()), SASQueueResponse.class),
                    productId, partnerId, subscriptionId, countryCode, newActivityType);
        } catch (Exception e) {
            logger.info("=========>ERROR due to exception");

            Assert.fail(e.getMessage());
        }
    }

}
