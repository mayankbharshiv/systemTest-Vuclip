package com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils;

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
}
