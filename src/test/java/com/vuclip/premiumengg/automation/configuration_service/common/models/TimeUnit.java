package com.vuclip.premiumengg.automation.configuration_service.common.models;
public enum TimeUnit {

	SECOND, MINUTE, HOUR, DAY, WEEK, MONTH, YEAR;

	public static TimeUnit fromValue(String val) {
		for (TimeUnit unit : TimeUnit.values()) {
			if (unit.toString().equals(val)) {
				return unit;

			}
		}
		return null;
	}

}
