package com.vuclip.premiumengg.automation.schedular_service.common.utils;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.utils.DBUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SSDBHelper {

	public static void cleanTestData(int productId, int partnerId, String country) {
		try {
			List<Map<String, Object>> record = DBUtils.getRecords("job_rules",
					" product_id=" + productId + " and partner_id=" + partnerId + " and country='" + country + "'");
			if (record != null && record.size() > 0) {
				for (Map<String, Object> map : record) {
					Long renewalRuleId = (Long) map.get("id");
					System.out.println("Record deleted "+DBUtils.cleanTable("job_rules_time_window", " renewal_rules_id=" + renewalRuleId));
				}
				System.out.println("Record deleted "+DBUtils.cleanTable("job_rules",
						" product_id=" + productId + " and partner_id=" + partnerId + " and country='" + country + "'"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Map<String, Object> getJobRules(int productId, int partnerId, String country) {
		try {
			return DBUtils.getRecord("job_rules",
					" product_id=" + productId + " and partner_id=" + partnerId + " and country='" + country + "'")
					.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static Map<String, Object> getJobRuleTimeWindow(long renewalRuleId) {
		try {
			return DBUtils.getRecord("job_rules_time_window", " renewal_rules_id=" + renewalRuleId).get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	
	public static void logTableRecord(List<Map<String, Object>> records) {

		for (Map<String, Object> map : records) {
			Log4J.getLogger().info(map.get("date").toString() + "   " + map.get("status").toString());
		}
	}

}
