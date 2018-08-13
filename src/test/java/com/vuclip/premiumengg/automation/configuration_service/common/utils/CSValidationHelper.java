package com.vuclip.premiumengg.automation.configuration_service.common.utils;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import com.vuclip.premiumengg.automation.billing_package_service.common.models.QueueResponse;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.SASTables;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;

import io.restassured.response.Response;

public class CSValidationHelper {
    private static Logger logger = Log4J.getLogger("CSValidationHelper");

    public static void validate_cs_api_response(Response sasApiResponse) {
        AppAssert.assertEqual(sasApiResponse.statusCode(), 200, "Vefiry that response status code is 200 ");
    }

    public static void validate_sas_invalid_api_response(Response sasApiResponse) {
        AppAssert.assertEqual(sasApiResponse.statusCode(), 500, "Vefiry that response status code is 500 ");
    }

    public static void validate_schedular_invalid_api_response(Response schedularApiResponse) {

        AppAssert.assertEqual(schedularApiResponse.statusCode(), 500, "Validate that response status code is 500 ");
        AppAssert.assertEqual(schedularApiResponse.getBody().asString(), "FAILURE", "verify scheduler api call");
    }

    
    public static void validateTableRecord(List<Map<String, Object>> list, Map<String, Object> expectedRecord) {
        for (String key : expectedRecord.keySet()) {
        	if (list.get(0).get(key) != null)
            AppAssert.assertEqual(list.get(0).get(key).toString().toLowerCase(),  expectedRecord.get(key).toString().toLowerCase(),
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

    public static void verifyNoActivityRecordPresent(int productId, int partnerId, Integer subscriptionId, BigInteger date) {
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
}
