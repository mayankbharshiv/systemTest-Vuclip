package com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils;

import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;

public class SASUtils {
	/**
	 * 
	 * @param JsonFileName
	 * @param type
	 * @return
	 */
	public static <T> T loadJson(String JsonFileName, Class<T> type) {
		return ObjectMapperUtils.readValue(
				"src/test/resources/configurations/scheduled-activity-service/request/" + JsonFileName, type);
	}
}
