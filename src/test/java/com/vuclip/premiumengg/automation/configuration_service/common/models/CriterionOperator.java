package com.vuclip.premiumengg.automation.configuration_service.common.models;

public enum CriterionOperator {

	EQUAL("=="), NOT_EQUAL("!=");

	private final String value;

	CriterionOperator(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static CriterionOperator fromValue(String val) {
		for (CriterionOperator operator : CriterionOperator.values()) {
			if (operator.getValue().equals(val)) {
				return operator;
			}
		}
		return null;
	}
}
