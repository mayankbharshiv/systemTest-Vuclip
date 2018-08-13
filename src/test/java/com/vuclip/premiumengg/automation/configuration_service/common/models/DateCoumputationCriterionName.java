package com.vuclip.premiumengg.automation.configuration_service.common.models;

public enum DateCoumputationCriterionName {
	next_billing_date, end_date;

	public static DateCoumputationCriterionName fromValue(String val) {
		for (DateCoumputationCriterionName name : DateCoumputationCriterionName.values()) {
			if (name.toString().equals(val)) {
				return name;
			}
		}
		return null;
	}
}
