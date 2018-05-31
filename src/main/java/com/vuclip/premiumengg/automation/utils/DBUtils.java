package com.vuclip.premiumengg.automation.utils;

import com.vuclip.premiumengg.automation.common.JDBCTemplate;
import com.vuclip.premiumengg.automation.common.Log4J;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DBUtils {

	public static List<Map<String, Object>> getRecord(String tableName, String whereClause) throws SQLException {
		List<Map<String, Object>> record = getRecords(tableName, whereClause);
		if (record.size() > 1)
			throw new SQLException("There are more then one records for query");
		else
			return record;
	}

	public static void cleanTable(String tableName, String whereClause) {
		String query = "delete from " + tableName;
		if (whereClause != null)
			query += " where " + whereClause;
		try {
			Log4J.getLogger("DBLogger").info(query);
			JDBCTemplate.getDbConnection().execute(query);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static List<Map<String, Object>> getRecords(String tableName, String whereClause) {
		String query = "select * from " + tableName;
		if (whereClause != null)
			query += " where " + whereClause;
		try {
			Log4J.getLogger("DBLogger").info(query);
			return JDBCTemplate.getDbConnection().queryForList(query);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
}
