package com.vuclip.premiumengg.automation.configuration_service.common.models;

public enum GroupingOperator {
	AND("&&"), OR("||"), NONE("NONE");

	private final String value;

	GroupingOperator(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static GroupingOperator fromValue(String val) {
		for (GroupingOperator type : GroupingOperator.values()) {
			if (type.getValue().equals(val)) {
				return type;

			}
		}
		return null;
	}
}
