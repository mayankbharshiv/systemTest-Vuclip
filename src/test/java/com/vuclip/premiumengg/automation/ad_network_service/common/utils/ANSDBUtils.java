package com.vuclip.premiumengg.automation.ad_network_service.common.utils;

import com.vuclip.premiumengg.automation.ad_network_service.common.models.ANSProductAdnetworkTableFields;
import com.vuclip.premiumengg.automation.ad_network_service.common.models.ANSTableNames;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class ANSDBUtils extends DBUtils {

    public static void cleanAllTable() {
        ANSTableNames tableNames[] = ANSTableNames.values();
        for (ANSTableNames tableName : tableNames) {
            cleanTable(tableName.getValue(), null);
        }

    }

    public static String showProductAdnetworkDetailsTableData(String message, String productId, String partnerId) {
        String type = null;
        try {
            List<Map<String, Object>> record1 = DBUtils.getRecords("product_adnetwork_details",
                    "product_id = " + productId + " and partner_id = " + partnerId);
            if (record1.size() >= 1) {
                Log4J.getLogger().info(message + "table name product_adnetwork_details ");
                logTableRecord(record1);

                type = "product_adnetwork_details";
            }
        } catch (Exception e) {
            Log4J.getLogger().info("Error in fetching data");
        }
        return type;

    }

    public static void logTableRecord(List<Map<String, Object>> records) {
        ANSProductAdnetworkTableFields tableFields[] = ANSProductAdnetworkTableFields.values();
        Log4J.getLogger().info("Verify All Records are getting update in table");
        for (Map<String, Object> map : records) {
            for (ANSProductAdnetworkTableFields tableField : tableFields) {
                if (map.get(tableField.getValue()) == null) {
                    Assert.fail("Api not working");
                }
            }

        }

    }

}
