package com.vuclip.premiumengg.automation.schedular_service.common.utils;

import java.util.Map;

import org.apache.log4j.Logger;

import com.vuclip.premiumengg.automation.billing_package_service.common.models.QueueResponse;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.schedular_service.common.models.JobMessage;
import com.vuclip.premiumengg.automation.schedular_service.common.models.SchedulerSaveProductRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.SchedulerRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.UserSubscriptionRequest;
import com.vuclip.premiumengg.automation.utils.AppAssert;

import io.restassured.response.Response;

public class SSValidationHelper {
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
        AppAssert.assertEqual(schedularApiResponse.getBody().asString(), "FAILURE", "verify scheduler api call");
    }

    public static void validateQueueMessage(QueueResponse queueResponse, SchedulerRequest schedulerRequest,
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

    public static void validateQueueMessage(QueueResponse queueResponse, int productId, int partnerId,
                                            int subscriptionId, String countryCode, String actionTable) throws InterruptedException {
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

    public static void verifyJobRulesRecord(Map<String, Object> record, SchedulerSaveProductRequest message) {

        AppAssert.assertEqual(record.get("partner_id").toString(), message.getRetry().get(0).getPartnerId().toString());
        AppAssert.assertEqual(record.get("product_id").toString(), message.getRetry().get(0).getProductId().toString());
        AppAssert.assertEqual(record.get("frequency_in_minutes").toString(),
                message.getRetry().get(0).getSchedulingFrequencyInMinutes().toString());
        AppAssert.assertEqual(record.get("activity_type").toString(), message.getRetry().get(0).getActivityType());
        AppAssert.assertEqual(record.get("country").toString(), message.getRetry().get(0).getCountryCode());
        AppAssert.assertEqual(record.get("executing_days").toString(), message.getRetry().get(0).getExecutingDays());
        AppAssert.assertEqual(record.get("active").toString(), String.valueOf(true));
        // AppAssert.assertEqual(record.get("last_updated_on").toString(),
        // String.valueOf(null));

    }

    public static void verifyJobRuleTimeWindowRecord(Map<String, Object> record, SchedulerSaveProductRequest message) {

        String[] timeWindow = message.getRetry().get(0).getExecutingTimeWindow().split("-");
        AppAssert.assertEqual(record.get("start_time").toString(), timeWindow[0]);
        AppAssert.assertEqual(record.get("end_time").toString(), timeWindow[1]);

    }

    public static void verifyQueueMEssage(JobMessage responseJobMessage, SchedulerSaveProductRequest message) {

        String[] timeWindow = message.getRetry().get(0).getExecutingTimeWindow().split("-");

        AppAssert.assertEqual(responseJobMessage.getTimeWindow().get(0).getStartime().toString(), timeWindow[0]);
        AppAssert.assertEqual(responseJobMessage.getTimeWindow().get(0).getEndTime().toString(), timeWindow[1]);
        AppAssert.assertEqual(responseJobMessage.getPartnerId().toString(),
                message.getRetry().get(0).getPartnerId().toString());
        AppAssert.assertEqual(responseJobMessage.getProductId().toString(), message.getRetry().get(0).getProductId().toString());

        AppAssert.assertEqual(responseJobMessage.getActivityType(), message.getRetry().get(0).getActivityType());
        AppAssert.assertEqual(responseJobMessage.getCountry(), message.getRetry().get(0).getCountryCode());
        AppAssert.assertEqual(responseJobMessage.getExecutingDays(), message.getRetry().get(0).getExecutingDays());

    }

}
