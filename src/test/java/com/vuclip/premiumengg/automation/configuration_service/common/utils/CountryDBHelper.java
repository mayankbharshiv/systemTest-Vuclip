package com.vuclip.premiumengg.automation.configuration_service.common.utils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;

public class CountryDBHelper {

	public static void insertRecordIntoPartner(String partnerName, int partnerId) {
		Map<String, Object> insertRecord = new HashMap<String, Object>();

		insertRecord.put("has_mo_activations", 0);
		insertRecord.put("has_mo_deactivations", 0);
		insertRecord.put("is_auto_renewal_applicable", 0);
		insertRecord.put("is_balance_check_required", 1);
		insertRecord.put("step_up_charging", 1);
		insertRecord.put("user_identifier", CSDBHelper.dbReadableFormat("user4"));
		insertRecord.put("type", CSDBHelper.dbReadableFormat("type"));
		insertRecord.put("activation_managed_by", CSDBHelper.dbReadableFormat("ActivationManagedBy"));
		insertRecord.put("deactivation_managed_by", CSDBHelper.dbReadableFormat("DeActivationManagedBy"));
		insertRecord.put("description", CSDBHelper.dbReadableFormat("Description"));
		insertRecord.put("partner_activation_consent_initiation_url", CSDBHelper.dbReadableFormat("url"));
		insertRecord.put("partner_name", CSDBHelper.dbReadableFormat(partnerName));
		insertRecord.put("partner_id", partnerId);
		insertRecord.put("renewal_managed_by", CSDBHelper.dbReadableFormat("renewalManagedBy"));
		insertRecord.put("status", CSDBHelper.dbReadableFormat("ACTIVE"));
		insertRecord.put("partner_activation_consent_initiation_url", CSDBHelper.dbReadableFormat("Vuclip Url"));
		insertRecord.put("partner_activation_consent_parser_url", CSDBHelper.dbReadableFormat("Vuclip url"));
		insertRecord.put("partner_deactivation_consent_initiation_url", CSDBHelper.dbReadableFormat("Vuclip url"));
		insertRecord.put("partner_deactivation_consent_parser_url", CSDBHelper.dbReadableFormat("Vuclip url"));
		CSDBHelper.addRecordInTable("partner", insertRecord);
	}

	public static void verifyNoActivityRecordPresent(String tableName, String countryName) throws SQLException {

		AppAssert.assertTrue(DBUtils.getRecord(tableName, "country_name =" + countryName).size() == 0,
				"verify no entry in " + tableName);
	}
}
