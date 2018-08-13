package com.vuclip.premiumengg.automation.configuration_service.common.models;
public enum TypeOfCycle {
	HOUR, CALANDER_DAY;

	public static TypeOfCycle fromValue(String val) {
		for (TypeOfCycle cycle : TypeOfCycle.values()) {
			if (cycle.toString().equals(val)) {
				return cycle;

			}
		}
		return null;
	}
}
