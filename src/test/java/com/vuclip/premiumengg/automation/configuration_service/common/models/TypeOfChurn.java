package com.vuclip.premiumengg.automation.configuration_service.common.models;
public enum TypeOfChurn {
	HOUR, CALENDAR_DAY, ALL, NA;

	public static TypeOfChurn fromValue(String val) {
		for (TypeOfChurn typeOfchurn : TypeOfChurn.values()) {
			if (typeOfchurn.toString().equals(val)) {
				return typeOfchurn;
			}
		}
		return null;
	}
}
