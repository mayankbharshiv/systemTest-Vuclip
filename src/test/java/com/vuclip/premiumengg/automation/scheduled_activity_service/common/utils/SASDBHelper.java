package com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.utils.DBUtils;

import java.util.List;
import java.util.Map;

public class SASDBHelper {

    /**
     * @param whereClause
     */
    public static void cleanTestData(String whereClause) {
        DBUtils.cleanTable("activation", whereClause);
        DBUtils.cleanTable("churn", whereClause);
        DBUtils.cleanTable("content_sms_user_info", whereClause);
        DBUtils.cleanTable("deactivation", whereClause);
        DBUtils.cleanTable("engagement_sms_user_info", whereClause);
        DBUtils.cleanTable("free_trail", whereClause);
        DBUtils.cleanTable("optout_sms_user_info", whereClause);
        DBUtils.cleanTable("prerenewal_sms_user_info", whereClause);
        DBUtils.cleanTable("renewal", whereClause);
        DBUtils.cleanTable("renewal_retry", whereClause);
        DBUtils.cleanTable("winback", whereClause);
    }

    /**
     * @param whereClause
     */
    public static void cleanAllTables(String whereClause) {
        DBUtils.cleanTable("job_config", whereClause);
        DBUtils.cleanTable("activation", whereClause);
        DBUtils.cleanTable("churn", whereClause);
        DBUtils.cleanTable("content_sms_user_info", whereClause);
        DBUtils.cleanTable("deactivation", whereClause);
        DBUtils.cleanTable("engagement_sms_user_info", whereClause);
        DBUtils.cleanTable("free_trail", whereClause);
        DBUtils.cleanTable("optout_sms_user_info", whereClause);
        DBUtils.cleanTable("prerenewal_sms_user_info", whereClause);
        DBUtils.cleanTable("product_partner_country_config", whereClause);
        DBUtils.cleanTable("renewal", whereClause);
        DBUtils.cleanTable("winback", whereClause);
    }

    /**
     * DO NOT DELETE THIS IS FOR DEBUG PURPOSE WHEN WE WANT TO SEE DB ACTIVITIES DURING FIXING TEST CASE
     *
     * @param message
     * @param subscriptionId
     */
    public static String showAllActivityTableData(String message, String subscriptionId) {
        String type = null;
        // try {
        // List<Map<String, Object>> record1 = DBUtils.getRecords("activation",
        // "subscription_id = " + subscriptionId);
        // if (record1.size() >= 1) {
        // Log4J.getLogger().info(message + "table name activation ");
        // logTableRecord(record1);
        //
        // type = "activation";
        // }
        // List<Map<String, Object>> record2 = DBUtils.getRecords("deactivation",
        // "subscription_id = " + subscriptionId);
        // if (record2.size() >= 1) {
        // Log4J.getLogger().info(message + "table name deactivation ");
        // logTableRecord(record2);
        //
        // type = "deactivation";
        // }
        // List<Map<String, Object>> record3 = DBUtils.getRecords("churn",
        // "subscription_id = " + subscriptionId);
        // if (record3.size() >= 1) {
        // Log4J.getLogger().info(message + "table name churn ");
        // logTableRecord(record3);
        //
        // type = "churn";
        // }
        // List<Map<String, Object>> record4 = DBUtils.getRecords("free_trail",
        // "subscription_id = " + subscriptionId);
        // if (record4.size() >= 1) {
        // Log4J.getLogger().info(message + "table name free_trial");
        // logTableRecord(record4);
        //
        // type = "free_trial";
        // }
        // List<Map<String, Object>> record5 = DBUtils.getRecords("renewal",
        // "subscription_id = " + subscriptionId);
        // if (record5.size() >= 1) {
        // Log4J.getLogger().info(message + "table name renewal ");
        // logTableRecord(record5);
        //
        // type = "renewal";
        // }
        // List<Map<String, Object>> record6 = DBUtils.getRecords("winback",
        // "subscription_id = " + subscriptionId);
        // if (record6.size() >= 1) {
        // Log4J.getLogger().info(message + "table name winback ");
        // logTableRecord(record6);
        //
        // type = "winback";
        // }
        //
        // } catch (Exception e) {
        // Log4J.getLogger().info("Error in fetching data");
        // }
        return type;

    }

    public static void logTableRecord(List<Map<String, Object>> records) {

        for (Map<String, Object> map : records) {
            Log4J.getLogger().info(map.get("date").toString() + "   " + map.get("status").toString());
        }
    }

}
