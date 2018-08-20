package com.vuclip.premiumengg.automation.configuration_service.common.models;
public enum DateCoumputationCriterionOperator {
	PLUS("+"), MINUS("-"), EVERY("every");
	private final String value;

	DateCoumputationCriterionOperator(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static DateCoumputationCriterionOperator fromValue(String val) {
		for (DateCoumputationCriterionOperator opearator : DateCoumputationCriterionOperator.values()) {
			if (opearator.getValue().equals(val)) {
				return opearator;
			}
		}
		return null;
	}
}
