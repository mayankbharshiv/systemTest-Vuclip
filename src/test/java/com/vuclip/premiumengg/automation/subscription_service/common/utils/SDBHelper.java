package com.vuclip.premiumengg.automation.subscription_service.common.utils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.vuclip.premiumengg.automation.common.JDBCTemplate;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;

public class SDBHelper {

	static Logger logger = Log4J.getLogger("SSDBHelper");

	public static void verifyNoActivityRecordPresent(String tableName, int productId, int partnerId, String userId)
			throws SQLException {

		AppAssert
				.assertTrue(
						DBUtils.getRecord(tableName,
								"user_id =" + userId + " and product_id =" + String.valueOf(productId)
										+ " and partner_id=" + String.valueOf(partnerId))
								.size() == 0,
						"verify no entry in " + tableName);
	}

	public static boolean getFlag(String flagType, String userId) throws SQLException {
		logger.info("get flag value for " + flagType);
		List<Map<String, Object>> getFlag = DBUtils.getRecord("user_subscription", "user_id=" + userId);
		boolean flag = (boolean) getFlag.get(0).get(flagType);
		logger.info(flagType + ":" + flag);
		return flag;
	}

	public static Object getColumnValueUsingUserId(String tableName, String columnName, String whereClause)
			throws SQLException {
		logger.info("get value for " + columnName + " from " + tableName);
		List<Map<String, Object>> getValue = DBUtils.getRecord(tableName, whereClause);
		Object value = (Object) getValue.get(0).get(columnName);
		logger.info(columnName + ":" + value.toString());
		return value;
	}

	public static int getValidityDaysFromProductPartner(String validityType) throws SQLException {
		logger.info("get validity days for " + validityType);
		List<Map<String, Object>> dateRecord = DBUtils.getRecord("product_partner_country_mapping",
				"product_id=" + SUtils.productId);
		Integer days = (Integer) dateRecord.get(0).get(validityType);
		logger.info(validityType + ":" + days);
		return days;
	}

	public static int getValidityDaysFromBillingPackage(String validityType, String billingCode) throws SQLException {
		logger.info("get validity days for " + validityType);
		List<Map<String, Object>> dateRecord = DBUtils.getRecord("billing_package",
				"billing_code=" + SUtils.dbReadableFormat(billingCode));
		Integer days = (Integer) dateRecord.get(0).get(validityType);
		logger.info(validityType + ":" + days);
		return days;
	}

	public static Integer validateDates(String tableName, String whereClause, String startDateCoulmnName,
			String endDateColumnName) throws SQLException {
		logger.info("Get day difference between " + startDateCoulmnName + " and " + endDateColumnName);
		List<Map<String, Object>> dateRecord = DBUtils.getRecord(tableName, whereClause);
		Long startDate = (Long) dateRecord.get(0).get(startDateCoulmnName);
		Long endDate = (Long) dateRecord.get(0).get(endDateColumnName);
		Integer days = (int) DateUtils.getDaysBetweenJavaDates(DateUtils.convertLongToStringDate(startDate),
				DateUtils.convertLongToStringDate(endDate));
		logger.info("Days:" + days);
		return days;
	}

	public static Integer validateDatesBlockedUser(String tableName, String whereClause, String startDateCoulmnName,
			String endDateColumnName) throws SQLException {
		logger.info("Get day difference between " + startDateCoulmnName + " and " + endDateColumnName);
		List<Map<String, Object>> dateRecord = DBUtils.getRecord(tableName, whereClause);
		Long startDate = (Long) dateRecord.get(0).get(startDateCoulmnName);
		Long endDate = (Long) dateRecord.get(0).get(endDateColumnName);
		Integer days = (int) DateUtils.getDaysBetweenJavaDates(startDate.toString(), endDate.toString());
		logger.info("Days:" + days);
		return days;
	}

	public static void cleanAllTables(String whereClause) {
		DBUtils.cleanTable("product_partner_country_mapping", whereClause);
		DBUtils.cleanTable("user_subscription", whereClause);
		DBUtils.cleanTable("blocked_user", whereClause);
		DBUtils.cleanTable("free_trial_history", whereClause);
		DBUtils.cleanTable("billing_package", whereClause);
		DBUtils.cleanTable("blocked_user", whereClause);
	}

	public static void validateTable(String tableName, String userId, int productId, int partnerId,
			Long nextBillingDate, String userSubAuthKey, String subscriptionStatus) throws SQLException {

		logger.info("=========>Validate Database");
		Map<String, String> expectedRecords = new HashMap<String, String>();
		expectedRecords.put("next_billing_date", String.valueOf(nextBillingDate));
		expectedRecords.put("partner_id", String.valueOf(partnerId));
		expectedRecords.put("product_id", String.valueOf(productId));
		expectedRecords.put("user_id", userId);
		expectedRecords.put("user_sub_auth_key", userSubAuthKey);
		expectedRecords.put("subscription_status", subscriptionStatus);

		SValidationHelper.validateTableRecord(DBUtils.getRecord(tableName, "user_id=" + userId).get(0),
				expectedRecords);
	}

	@SuppressWarnings("rawtypes")
	public static void addRecordInTable(String tableName, Map<String, Object> tableData) {
		int size = tableData.size();
		Map.Entry pairs = null;
		String query = "INSERT INTO XXX (YYY) VALUES (ZZZ)";
		String coulmnName = "";
		String columnData = "";

		Iterator<Entry<String, Object>> it = tableData.entrySet().iterator();
		int counter = 1;
		while (it.hasNext()) {
			pairs = it.next();
			coulmnName += pairs.getKey();
			columnData += pairs.getValue();
			if (size > counter) {
				coulmnName += ", ";
				columnData += ", ";
			}
			counter++;
		}
		query = query.replace("XXX", tableName).replace("YYY", coulmnName).replace("ZZZ", columnData);
		try {
			Log4J.getLogger("DBLogger").info(query);
			Log4J.getLogger("DBLogger").info("Updated " + JDBCTemplate.getDbConnection().update(query) + " Records");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}
