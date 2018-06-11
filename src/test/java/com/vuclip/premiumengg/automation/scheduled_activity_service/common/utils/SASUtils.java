package com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils;

import com.vuclip.premiumengg.automation.billing_package_service.common.models.QueueResponse;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RabbitMQConnection;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.ActivityType;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.SchedulerRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.UserSubscriptionRequest;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class SASUtils {
    public static int productId;
    public static PublishConfigRequest productConfig = null;

    /**
     * @param JsonFileName
     * @param type
     * @return
     */
    public static <T> T loadJson(String JsonFileName, Class<T> type) {
        return ObjectMapperUtils.readValue(
                "src/test/resources/configurations/scheduled-activity-service/request/" + JsonFileName, type);
    }

    public static PublishConfigRequest generateSaveProductConfig(Integer productId, Integer partnerId,
                                                                 String activityType) {

        PublishConfigRequest publishConfigRequest = loadJson("publishConfig.json", PublishConfigRequest.class);

        publishConfigRequest.getProduct().setProductId(productId);
        publishConfigRequest.getProductPartnerMappings().get(0).setProductId(productId);
        publishConfigRequest.getProductPartnerMappings().get(0).setPartnerId(partnerId);
        publishConfigRequest.getProductCountryMapping().setProductId(productId);
        publishConfigRequest.getAdNetworkNotifications().get(0).setPartnerId(partnerId);
        publishConfigRequest.getAdNetworkNotifications().get(0).setProductId(productId);
        publishConfigRequest.getActivityFlows().get(0).setPartnerId(partnerId);
        publishConfigRequest.getActivityFlows().get(1).setPartnerId(partnerId);
        publishConfigRequest.getActivityFlows().get(2).setPartnerId(partnerId);
        publishConfigRequest.getPricePoints().get(0).setProductId(productId);
        publishConfigRequest.getPricePoints().get(0).setPartnerId(partnerId);
        publishConfigRequest.getRetry().get(0).setProductId(productId);
        publishConfigRequest.getRetry().get(0).setPartnerId(partnerId);
        publishConfigRequest.getBlackouts().get(0).setPartnerId(partnerId);
        publishConfigRequest.getBlackouts().get(0).setProductId(productId);
        publishConfigRequest.getRetry().get(0).setActivityType(activityType);
        return publishConfigRequest;
    }

    public static SchedulerRequest generateSchedulerRequest(Integer productId, Integer partnerId, String actionTable) {
        SchedulerRequest schedulerRequest = loadJson("scheduler.json", SchedulerRequest.class);
        schedulerRequest.setActivityType(actionTable.toUpperCase());
        schedulerRequest.setProductId(productId);
        schedulerRequest.setPartnerId(partnerId);
        return schedulerRequest;
    }

    public static UserSubscriptionRequest generateUserSubscriptionRequest(Integer productId, Integer partnerId,
                                                                          String activityType, String previousSubscriptionState, String currentSubscriptionState,
                                                                          String transactionState, String actionType, int subscriptionId) {
        UserSubscriptionRequest userSubscriptionRequest = loadJson("userSubscription.json",
                UserSubscriptionRequest.class);

        userSubscriptionRequest.getActivityInfo().setActivityType(activityType);
        userSubscriptionRequest.getActivityInfo().setPreviousSubscriptionState(previousSubscriptionState);
        userSubscriptionRequest.getActivityInfo().setCurrentSubscriptionState(currentSubscriptionState);
        userSubscriptionRequest.getActivityEvent().setTransactionState(transactionState);
        userSubscriptionRequest.getActivityInfo().setActionType(actionType);
        userSubscriptionRequest.getSubscriptionInfo().setSubscriptionId(subscriptionId);
        userSubscriptionRequest.getActivityEvent().setSubscriptionId(subscriptionId);
        userSubscriptionRequest.getSubscriptionInfo().setProductId(productId);
        userSubscriptionRequest.getSubscriptionInfo().setPartnerId(partnerId);
        userSubscriptionRequest.getActivityEvent().setPartnerId(partnerId);
        userSubscriptionRequest.getActivityEvent().setProductId(productId);
        return userSubscriptionRequest;
    }

    public static Object[][] getALLActivityType() {
        return new Object[][]{{ActivityType.ACTIVATION_TYPE}, {ActivityType.ACTIVATION_RETRY_TYPE},
                {ActivityType.DEACTIVATION}, {ActivityType.DEACTIVATION_RETRY_TYPE},
                {ActivityType.FREETRIAL_RENEWAL_TYPE}, {ActivityType.RENEWAL_TYPE},
                {ActivityType.SYSTEM_CHURN_TYPE}, {ActivityType.WINBACK_TYPE},
                {ActivityType.RENEWAL_RETRY_TYPE}};
    }

    public static PublishConfigRequest changeBatchSize(PublishConfigRequest publishConfigRequest, int batchSize) {
        for (int i = 0; i < publishConfigRequest.getRetry().size(); i++) {
            publishConfigRequest.getRetry().get(i).setBatchSize(batchSize);
        }
        return publishConfigRequest;
    }

    public static PublishConfigRequest changeActionDefaultEiligible(PublishConfigRequest publishConfigRequest,
                                                                    Boolean isActionDefaultEiligible) {
        for (int i = 0; i < publishConfigRequest.getRetry().size(); i++) {
            publishConfigRequest.getRetry().get(i).setActionDefaultEiligible(isActionDefaultEiligible);
        }
        return publishConfigRequest;
    }

    public static PublishConfigRequest changeRetryCount(PublishConfigRequest publishConfigRequest, int maxRetryCount) {
        for (int i = 0; i < publishConfigRequest.getRetry().size(); i++) {
            publishConfigRequest.getRetry().get(i).setMaxRetryCount(maxRetryCount);
        }
        return publishConfigRequest;
    }

    public static void executeActivityFlows(Integer productId, Integer partnerId, int subscriptionId,
                                            String countryCode, String eventActionType, String activityType, String currentSubscriptionState,
                                            String transactionState, String actionTable, String beforeSchedularStatus, String afterSchedularStatus,
                                            String afteNewEventStatus, String queueName, String newEventActionType, String newActivityType,
                                            String newCurrentSubscriptionState, String newTransactionState, String newActionTable,
                                            String newBeforeSchedularStatus, String newAfterSchedularStatus, String newQueueName) throws Exception {
        SASHelper sasHelper = new SASHelper();
        Logger logger = Log4J.getLogger("SAS FLOW");

        logger.info("=========>First time user subscription event getting trigger");
        UserSubscriptionRequest uSRequest = SASUtils.generateUserSubscriptionRequest(productId, partnerId, activityType,
                "", currentSubscriptionState, transactionState, eventActionType, subscriptionId);
        SASValidationHelper.validate_sas_api_response(sasHelper.userSubscription(uSRequest));

        // String expectedActivityType = SASDBHelper.showAllActivityTableData("FIRST ",
        // String.valueOf(subscriptionId));
        logger.info("=========>First event: Verify DB after event trigger");

        Map<String, String> expectedRecords = new HashMap<String, String>();
        expectedRecords.put("status", beforeSchedularStatus);
        expectedRecords.put("product_id", String.valueOf(productId));
        expectedRecords.put("partner_id", String.valueOf(partnerId));
        expectedRecords.put("subscription_id", String.valueOf(subscriptionId));
        expectedRecords.put("country_code", countryCode);
        expectedRecords.put("date", String.valueOf(uSRequest.getSubscriptionInfo().getNextBillingDate()));
        SASValidationHelper.validateTableRecord(DBUtils
                .getRecord(actionTable,
                        "subscription_id = " + subscriptionId + " and product_id = " + productId + " and partner_id="
                                + partnerId + " and date=" + uSRequest.getSubscriptionInfo().getNextBillingDate())
                .get(0), expectedRecords);

        logger.info("=========>First Event: scheduale call ");
        SASValidationHelper.validate_schedular_api_response(
                sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, actionTable)));

        logger.info("=========>First Event: Vefiry DB After Schedular Call ");
        expectedRecords.put("status", afterSchedularStatus);
        SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable,
                "subscription_id = " + subscriptionId + " and product_id=" + productId + " and partner_id=" + partnerId)
                .get(0), expectedRecords);

        logger.info("=========>First Event: Queue verification Name: " + productId + "_" + partnerId + "_" + queueName
                + "_REQUEST_BACKEND");

        Message message = RabbitMQConnection.getRabbitTemplate()
                .receive(productId + "_" + partnerId + "_" + queueName.toUpperCase() + "_REQUEST_BACKEND", 25000);
        SASValidationHelper.validateQueueMessage(
                ObjectMapperUtils.readValueFromString(new String(message.getBody()), QueueResponse.class), productId,
                partnerId, subscriptionId, countryCode, actionTable.toUpperCase());

        logger.info("=========>Second time user subscription event getting trigger");
        UserSubscriptionRequest newSubscriptionRequest = SASUtils.generateUserSubscriptionRequest(productId, partnerId,
                newActivityType.toUpperCase(), currentSubscriptionState, newCurrentSubscriptionState,
                newTransactionState, newEventActionType, subscriptionId);
        newSubscriptionRequest.getSubscriptionInfo()
                .setNextBillingDate(DateTimeUtil.getDateByAddingValidity(newSubscriptionRequest.getSubscriptionInfo().getNextBillingDate(), 1, TimeUnitEnum.DAY.name()));
        newSubscriptionRequest.getActivityEvent()
                .setNextBillingDate(DateTimeUtil.getDateByAddingValidity(newSubscriptionRequest.getActivityEvent().getNextBillingDate(), 1, TimeUnitEnum.DAY.name()));

        SASValidationHelper.validate_sas_api_response(sasHelper.userSubscription(newSubscriptionRequest));

        // SASDBHelper.showAllActivityTableData("THIRD",
        // String.valueOf(subscriptionId));

        logger.info("=========>Second event: previous Event's DB verification");
        expectedRecords.put("status", afteNewEventStatus);
        SASValidationHelper.validateTableRecord(DBUtils
                .getRecord(actionTable,
                        "subscription_id = " + subscriptionId + " and product_id=" + productId + " and partner_id="
                                + partnerId + " and date=" + uSRequest.getSubscriptionInfo().getNextBillingDate())
                .get(0), expectedRecords);

        logger.info("=========>Second event: DB verification before schedular call");
        expectedRecords.put("status", newBeforeSchedularStatus);
        expectedRecords.put("date", String.valueOf(newSubscriptionRequest.getSubscriptionInfo().getNextBillingDate()));
        SASValidationHelper.validateTableRecord(DBUtils.getRecord(newActionTable,
                "subscription_id = " + subscriptionId + " and product_id=" + productId + " and partner_id=" + partnerId
                        + " and date=" + newSubscriptionRequest.getSubscriptionInfo().getNextBillingDate())
                .get(0), expectedRecords);

        logger.info("=========>Second event: Schedular call");
        SASValidationHelper.validate_schedular_api_response(
                sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, newActionTable)));

        // SASDBHelper.showAllActivityTableData("FOURTH",
        // String.valueOf(subscriptionId));

        logger.info("=========>Second event: DB verification after schedular call");
        expectedRecords.put("status", newAfterSchedularStatus);
        SASValidationHelper.validateTableRecord(DBUtils.getRecord(newActionTable,
                "subscription_id = " + subscriptionId + " and product_id=" + productId + " and partner_id=" + partnerId
                        + " and date=" + newSubscriptionRequest.getSubscriptionInfo().getNextBillingDate())
                .get(0), expectedRecords);

        logger.info("=========>Second event: Queue verification " + productId + "_" + partnerId + "_"
                + newQueueName.toUpperCase() + "_REQUEST_BACKEND");
        message = RabbitMQConnection.getRabbitTemplate()
                .receive(productId + "_" + partnerId + "_" + newQueueName.toUpperCase() + "_REQUEST_BACKEND", 25000);
        SASValidationHelper.validateQueueMessage(
                ObjectMapperUtils.readValueFromString(new String(message.getBody()), QueueResponse.class), productId,
                partnerId, subscriptionId, countryCode, newActionTable.toUpperCase());
    }

    public static void executeActivityFlow(Integer productId, Integer partnerId, int subscriptionId, String countryCode,
                                           String eventActionType, String activityType, String currentSubscriptionState, String transactionState,
                                           String actionTable, String schedulerActivity, String beforeSchedularStatus, String afterSchedularStatus,
                                           String queueName) throws Exception {

        SASHelper sasHelper = new SASHelper();

        SASValidationHelper.validate_sas_api_response(
                sasHelper.userSubscription(SASUtils.generateUserSubscriptionRequest(productId, partnerId, activityType,
                        "", currentSubscriptionState, transactionState, eventActionType, subscriptionId)));

        Map<String, String> expectedRecords = new HashMap<String, String>();
        expectedRecords.put("status", beforeSchedularStatus);
        expectedRecords.put("product_id", String.valueOf(productId));
        expectedRecords.put("partner_id", String.valueOf(partnerId));
        expectedRecords.put("subscription_id", String.valueOf(subscriptionId));
        expectedRecords.put("country_code", countryCode);

        SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable, "subscription_id = " + subscriptionId
                + " and product_id = " + productId + " and partner_id=" + partnerId).get(0), expectedRecords);

        SASValidationHelper.validate_schedular_api_response(
                sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, schedulerActivity)));

        expectedRecords.put("status", afterSchedularStatus);

        SASValidationHelper.validateTableRecord(DBUtils.getRecord(actionTable,
                "subscription_id = " + subscriptionId + " and product_id=" + productId + " and partner_id=" + partnerId)
                .get(0), expectedRecords);

        Message message = RabbitMQConnection.getRabbitTemplate()
                .receive(productId + "_" + partnerId + "_" + queueName + "_REQUEST_BACKEND", 25000);
        SASValidationHelper.validateQueueMessage(
                ObjectMapperUtils.readValueFromString(new String(message.getBody()), QueueResponse.class), productId,
                partnerId, subscriptionId, countryCode, actionTable.toUpperCase());
    }

    public static String getTestLogMessage(Integer productId, int subscriptionId, String actionType,
                                           String activityType, String currentSubscriptionState, String transactionState) {
        return "Product ID: " + productId + ", subscription ID: " + subscriptionId + ", Activity Type: " + activityType
                + ", CSS:  " + currentSubscriptionState + ", Transaction State: " + transactionState + ", Event Type: "
                + actionType;
    }

    public static void executeUserSubscription(Integer productId, Integer partnerId, int subscriptionId,
                                               String countryCode, String eventActionType, String activityType, String currentSubscriptionState,
                                               String transactionState, BigInteger endDate, BigInteger nBD, String actionTable, String status) throws Exception {

        SASHelper sasHelper = new SASHelper();
        UserSubscriptionRequest userSubscriptionRequest = SASUtils.generateUserSubscriptionRequest(productId, partnerId,
                activityType, "", currentSubscriptionState, transactionState, eventActionType, subscriptionId);
        userSubscriptionRequest.getSubscriptionInfo().setEndDate(endDate);
        userSubscriptionRequest.getSubscriptionInfo().setNextBillingDate(nBD);
        SASValidationHelper.validate_sas_api_response(sasHelper.userSubscription(userSubscriptionRequest));

        Map<String, String> expectedRecords = new HashMap<String, String>();
        expectedRecords.put("status", status);
        expectedRecords.put("product_id", String.valueOf(productId));
        expectedRecords.put("partner_id", String.valueOf(partnerId));
        expectedRecords.put("subscription_id", String.valueOf(subscriptionId));
        expectedRecords.put("country_code", countryCode);
        expectedRecords.put("date", countryCode);

        AppAssert.assertEqual(
                DBUtils.getRecords(actionTable,
                        "subscription_id = " + subscriptionId + " and product_id = " + productId + " and partner_id="
                                + partnerId + " and country_code='" + countryCode + "' and date=" + nBD + " and status='" + status + "'")
                        .size(),
                1, "Verify record exists");

    }

    public static void executescheduler(Integer productId, Integer partnerId, String schedulerActivity) throws Exception {
        SASHelper sasHelper = new SASHelper();

        SASValidationHelper.validate_schedular_api_response(
                sasHelper.scheduler(SASUtils.generateSchedulerRequest(productId, partnerId, schedulerActivity)));

    }

}