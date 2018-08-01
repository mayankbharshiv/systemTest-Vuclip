package com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RabbitMQConnection;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.SASQueueResponse;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.SASTables;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.SchedulerRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.UserSubscriptionRequest;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SASValidationHelper {
    private static Logger logger = Log4J.getLogger("SASValidationHelper");
    // private static Integer partnerId;

    public static void validate_sas_api_response(Response sasApiResponse) {
        AppAssert.assertEqual(sasApiResponse.statusCode(), 200, "Vefiry that response status code is 200 ");
    }

    public static void validate_sas_invalid_api_response(Response sasApiResponse) {
        AppAssert.assertEqual(sasApiResponse.statusCode(), 500, "Vefiry that response status code is 500 ");
    }

    public static void validate_schedular_api_response(Response schedularApiResponse) {

        AppAssert.assertEqual(schedularApiResponse.statusCode(), 200, "Validate that response status code is 200 ");
        AppAssert.assertEqual(schedularApiResponse.getBody().asString(), "SUCCESS", "verify scheduler api call");
    }

    public static void validate_schedular_invalid_api_response(Response schedularApiResponse) {

        AppAssert.assertEqual(schedularApiResponse.statusCode(), 500, "Validate that response status code is 500 ");
    }

    public static void validateQueueMessage(SASQueueResponse queueResponse, SchedulerRequest schedulerRequest,
                                            UserSubscriptionRequest userSubscriptionRequest, String activityType) {
        logger.info("verification for RabbitMQ");
        AppAssert.assertEqual(queueResponse.getSubscriptionId().toString(),
                userSubscriptionRequest.getSubscriptionInfo().getSubscriptionId().toString(), "Verify subscription ID");
        AppAssert.assertEqual(queueResponse.getCountryCode().toUpperCase(),
                userSubscriptionRequest.getSubscriptionInfo().getCountry().toUpperCase(), "Verify country");
        AppAssert.assertEqual(queueResponse.getPartnerId().toString().toUpperCase(),
                schedulerRequest.getPartnerId().toString().toUpperCase(), "Verify partner ID");
        AppAssert.assertEqual(queueResponse.getProductId().toString().toUpperCase(),
                schedulerRequest.getPartnerId().toString().toUpperCase(), "Verify product ID");
        AppAssert.assertEqual(queueResponse.getActivitType().toString().toUpperCase(), activityType.toUpperCase(),
                "Verify activity type");
    }

    public static void validateTableRecord(Map<String, Object> actualrecord, Map<String, String> expectedRecord) {
        for (String key : expectedRecord.keySet()) {
            AppAssert.assertEqual(actualrecord.get(key).toString().toLowerCase(), expectedRecord.get(key).toLowerCase(),
                    "Verify table fields");
        }
    }

    public static void validateQueueMessage(SASQueueResponse queueResponse, int productId, int partnerId,
                                            int subscriptionId, String countryCode, String actionTable) {
        logger.info("verification for RabbitMQ");
        AppAssert.assertEqual(queueResponse.getProductId().toString().toUpperCase(),
                String.valueOf(productId).toUpperCase(), "Verify product ID");
        AppAssert.assertEqual(queueResponse.getPartnerId().toString().toUpperCase(),
                String.valueOf(partnerId).toUpperCase(), "Verify partner ID");
        AppAssert.assertEqual(queueResponse.getSubscriptionId().toString(), String.valueOf(subscriptionId),
                "Verify subscription ID");
        AppAssert.assertEqual(queueResponse.getCountryCode().toUpperCase(), countryCode.toUpperCase(),
                "Verify country");
        if (actionTable.toUpperCase().equalsIgnoreCase("RENEWAL_RETRY"))
            AppAssert.assertEqual(queueResponse.getActivitType().toString().toUpperCase(), "RENEWAL",
                    "Verify activity type");
        else
            AppAssert.assertEqual(queueResponse.getActivitType().toString().toUpperCase(), actionTable.toUpperCase(),
                    "Verify activity type");
    }

    public static void verifyNoActivityRecordPresent(int productId, int partnerId, Integer subscriptionId,
                                                     BigInteger date) {
        List<String> tables = Stream.of(SASTables.values()).map(Enum::name).collect(Collectors.toList());
        for (String tableName : tables) {
            try {
                AppAssert.assertTrue(
                        DBUtils.getRecord(tableName,
                                " subscription_id =" + String.valueOf(subscriptionId) + " and product_id ="
                                        + String.valueOf(productId) + " and partner_id=" + String.valueOf(partnerId)
                                        + " and date=" + String.valueOf(date))
                                .size() == 0,
                        "verify no entry in " + tableName);
            } catch (SQLException e) {
                AppAssert.assertTrue(false,
                        "Error occoured while fetching data from DB " + tableName + " error message" + e.getMessage());
            }
        }
    }

    public static void negativeFlow(Integer productId, Integer partnerId, String activityType,
                                    String currentSubscriptionState, String transactionState, String actionType, int subscriptionId) {
        UserSubscriptionRequest request = SASUtils.generateUserSubscriptionRequest(productId, partnerId, activityType,
                "", currentSubscriptionState, transactionState, actionType, subscriptionId);
        SASHelper sasHelper = new SASHelper();
        try {
            validate_sas_api_response(sasHelper.userSubscription(request));
        } catch (Exception e) {
            AppAssert.assertTrue(false, "error in api call");
        }

        SASValidationHelper.verifyNoActivityRecordPresent(productId, partnerId, subscriptionId,
                request.getSubscriptionInfo().getNextBillingDate());
    }

    public static String whereClause(int subscriptionId, int productId, int partnerId, String nextBillingDate,
                                     String Status, String countryCode) {
        return "product_id='" + productId + "' and partner_id='" + partnerId + "' and country_code='" + countryCode
                + "' and subscription_id='" + subscriptionId + "' and status='" + Status + "';";
    }

    public static void verifyEventTable(String actionTable, int subscriptionId, int productId, int partnerId,
                                        BigInteger billingDate, String status, String countryCode) {
        try {
            AppAssert.assertTrue(DBUtils
                    .getRecord(actionTable,
                            whereClause(subscriptionId, productId, partnerId, "" + billingDate, status, countryCode))
                    .size() == 1);
        } catch (SQLException e) {

            e.printStackTrace();
            AppAssert.assertTrue(false, "not able to fetch record from " + actionTable);
        }
    }

    public static void validateQueue(String queue, String activity, Integer productId, Integer partnerId,
                                     int subscriptionId, String countryCode) {
        Message message = RabbitUtil.receive(RabbitMQConnection.getRabbitTemplate(),
                RabbitUtil.getQueueName(productId, partnerId, countryCode, queue), 10000);

        validateQueueMessage(ObjectMapperUtils.readValueFromString(new String(message.getBody()), SASQueueResponse.class),
                productId, partnerId, subscriptionId, countryCode, activity);
    }

}
