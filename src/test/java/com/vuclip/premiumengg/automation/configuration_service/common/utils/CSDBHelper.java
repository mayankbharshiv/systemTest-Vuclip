package com.vuclip.premiumengg.automation.configuration_service.common.utils;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.vuclip.premiumengg.automation.common.JDBCTemplate;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;

public class CSDBHelper {

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
	
	public static String dbReadableFormat(String key) {
		return ("'" + key + "'");
	}
	
	public static void verifyNoActivityRecordPresent(String tableName, String where) throws SQLException {

		AppAssert.assertTrue(DBUtils.getRecord(tableName,where).size() == 0,
				"verify no entry in " + tableName);
	}
	
	
	public static int getNumberOfRecordsInTable(String tableName, String whereClause) throws SQLException {
        List<Map<String, Object>> record = getRecords(tableName, whereClause);
            return record.size();
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

