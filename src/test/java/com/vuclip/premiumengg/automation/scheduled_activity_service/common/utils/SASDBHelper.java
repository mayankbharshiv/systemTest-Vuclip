package com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.utils.DBUtils;

public class SASDBHelper {

	/**
	 * after save product validation form these two table SELECT * FROM
	 * scheduled_activity_service.product_partner_country_config; SELECT * FROM
	 * scheduled_activity_service.job_config;
	 */

	public static void cleanTestData(String product_id) {
		DBUtils.cleanTable("activation", product_id);
		DBUtils.cleanTable("churn", product_id);
		DBUtils.cleanTable("content_sms_user_info", product_id);
		DBUtils.cleanTable("deactivation", product_id);
		DBUtils.cleanTable("engagement_sms_user_info", product_id);
		DBUtils.cleanTable("free_trail", product_id);
		DBUtils.cleanTable("optout_sms_user_info", product_id);
		DBUtils.cleanTable("prerenewal_sms_user_info", product_id);
		DBUtils.cleanTable("renewal", product_id);
		DBUtils.cleanTable("renewal_retry", product_id);
		DBUtils.cleanTable("winback", product_id);
	}

	public static void cleanAllTables(String product_id) {
		DBUtils.cleanTable("job_config", product_id);
		DBUtils.cleanTable("activation", product_id);
		DBUtils.cleanTable("churn", product_id);
		DBUtils.cleanTable("content_sms_user_info", product_id);
		DBUtils.cleanTable("deactivation", product_id);
		DBUtils.cleanTable("engagement_sms_user_info", product_id);
		DBUtils.cleanTable("free_trail", product_id);
		DBUtils.cleanTable("optout_sms_user_info", product_id);
		DBUtils.cleanTable("prerenewal_sms_user_info", product_id);
		DBUtils.cleanTable("product_partner_country_config", product_id);
		DBUtils.cleanTable("renewal", product_id);
		DBUtils.cleanTable("renewal_retry", product_id);
		DBUtils.cleanTable("winback", product_id);
	}

	public static void showAllTableData(String message, String subscriptionId) {

		try {
			List<Map<String, Object>> record1 = DBUtils.getRecord("activation", "subscription_id = " + subscriptionId);
			if (record1.size() >= 1)
				Log4J.getLogger().info(message + "table name  activation " + record1.get(0).get("status").toString());

			List<Map<String, Object>> record2 = DBUtils.getRecord("deactivation",
					"subscription_id = " + subscriptionId);
			if (record2.size() >= 1)
				Log4J.getLogger().info(message + "table name  deactivation " + record2.get(0).get("status").toString());

			List<Map<String, Object>> record3 = DBUtils.getRecord("churn", "subscription_id = " + subscriptionId);
			if (record3.size() >= 1)
				Log4J.getLogger().info(message + "table name  churn " + record3.get(0).get("status").toString());

			List<Map<String, Object>> record4 = DBUtils.getRecord("free_trail", "subscription_id = " + subscriptionId);
			if (record4.size() >= 1)
				Log4J.getLogger().info(message + "table name  free_trial" + record4.get(0).get("status").toString());

			List<Map<String, Object>> record5 = DBUtils.getRecord("renewal", "subscription_id = " + subscriptionId);
			if (record5.size() >= 1)
				Log4J.getLogger().info(message + "table name  renewal " + record5.get(0).get("status").toString());

			List<Map<String, Object>> record6 = DBUtils.getRecord("winback", "subscription_id = " + subscriptionId);
			if (record6.size() >= 1)
				Log4J.getLogger().info(message + "table name  winback " + record6.get(0).get("status").toString());

			List<Map<String, Object>> record7 = DBUtils.getRecord("renewal_retry",
					"subscription_id = " + subscriptionId);
			if (record7.size() >= 1)
				Log4J.getLogger().info(message + "table name  renewal_retry" + record7.get(0).get("status").toString());
		} catch (Exception e) {
			Log4J.getLogger().info("Error in fetching data");
		}

	}
}
