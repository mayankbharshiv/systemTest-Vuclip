package com.vuclip.premiumengg.automation.configuration_service.common.models;

public enum CriterionName {
	billing_code, language_id, activity, activity_result, action;
	
	public static CriterionName fromValue(String val) {
		for (CriterionName name : CriterionName.values()) {
			if (name.toString().equals(val)) {
				return name;
			}
		}
		return null;
	}
}
