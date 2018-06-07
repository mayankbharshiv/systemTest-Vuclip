package com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

	public static long getTimeInMillis(String dd, String mm, String yyyy, String HH, String MM, String SS) {

		LocalDateTime localDateTime = LocalDateTime.parse(yyyy + "/" + mm + "/" + dd + " " + HH + ":" + MM + ":" + SS,
				DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
		return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

	}

	public static long getTimeInMillisAddDays(int days) {
		LocalDateTime s = LocalDateTime.now().plusDays(5);
		return s.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

	}

	public static long getTimeInMillisMinusDays(int days) {
		LocalDateTime s = LocalDateTime.now().minusDays(5);
		return s.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

	}

}
