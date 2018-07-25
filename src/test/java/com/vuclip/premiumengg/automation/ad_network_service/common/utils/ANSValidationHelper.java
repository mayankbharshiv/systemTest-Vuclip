package com.vuclip.premiumengg.automation.ad_network_service.common.utils;

import com.vuclip.premiumengg.automation.ad_network_service.common.models.ANSTables;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ANSValidationHelper {
    private static Logger logger = Log4J.getLogger("ANSValidationHelper");

    public static void validate_ans_api_response(Response ansApiResponse) {
        logger.info("Validate Api Response");
        AppAssert.assertEqual(ansApiResponse.statusCode(), 200, "Vefiry that response status code is 200 ");
    }

    public static void validate_ans_invalid_api_response(Response ansApiResponse) {
        logger.info("Validate Api Response");
        AppAssert.assertEqual(ansApiResponse.statusCode(), 500, "Vefiry that response status code is 500 ");
    }

    public static void validateTableRecord(Map<String, Object> actualrecord, Map<String, String> expectedRecord) {
        for (String key : expectedRecord.keySet()) {

            if (expectedRecord.get(key) != null)
                AppAssert.assertEqual(actualrecord.get(key).toString().toLowerCase(),
                        expectedRecord.get(key).toLowerCase(), "Verify table fields");
            else {
                Assert.fail("Null value present for:" + expectedRecord.get(key));
            }
        }
    }

    private static void verifyRecord(int productId, int partnerId, String transactionID, int size) {
        logger.info("Verify Record is created in Database");
        List<String> tables = Stream.of(ANSTables.values()).map(Enum::name).collect(Collectors.toList());
        for (String tableName : tables) {
            try {
                AppAssert.assertTrue(
                        DBUtils.getRecord(tableName,
                                "transaction_id ='" + transactionID + "' and product_id = " + productId
                                        + " and partner_id=" + partnerId)
                                .size() == size,
                        "verify entry in " + tableName + " = " + size);
            } catch (SQLException e) {
                AppAssert.assertTrue(false,
                        "Error occoured while fetching data from DB " + tableName + " error message" + e.getMessage());
            }
        }
    }

    public static void validateUserAdnotificationTable(int productId, int partnerId, String transactionID, String col,
                                                       String colVal) throws SQLException {
        logger.info("Validate User AD Notification table");
        Map<String, Object> actualrecord = DBUtils.getRecord("user_adnotification", "transaction_id ='" + transactionID
                + "' and product_id = " + productId + " and partner_id=" + partnerId).get(0);
        AppAssert.assertEqual(actualrecord.get(col).toString().toUpperCase(), colVal.toString().toUpperCase(),
                "Verification for status");
    }

    public static void validateNoActionTable(int productId, int partnerId, String transactionID, String col)
            throws SQLException {
        logger.info("Validate Status in Table is not updated");
        Map<String, Object> actualrecord = DBUtils.getRecord("user_adnotification", "transaction_id ='" + transactionID
                + "' and product_id = " + productId + " and partner_id=" + partnerId).get(0);
        if (actualrecord.get(col) == null) {
            AppAssert.assertTrue(true);
        } else {
            AppAssert.assertTrue(false, "Table got updated for column " + col);
        }
    }

    public static void verifyNoActivityRecordPresent(int productId, int partnerId, String transactionID) {
        verifyRecord(productId, partnerId, transactionID, 0);
    }

    public static void verifyActivityRecordPresent(int productId, int partnerId, String transactionID) {
        verifyRecord(productId, partnerId, transactionID, 1);
    }

}
